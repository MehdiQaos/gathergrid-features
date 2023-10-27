package com.gathergrid.gathergridfeatures.repository.interfacesImpl;

import com.gathergrid.gathergridfeatures.domain.Event;
import com.gathergrid.gathergridfeatures.repository.interfaces.EventRepository;
import com.gathergrid.gathergridfeatures.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.Query;

import java.util.List;

public class EventRepositoryImpl implements EventRepository {
    private final EntityManager em;

    public EventRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    public EventRepositoryImpl() {
        em = EntityManagerUtil.getEntityManager();
    }

    @Override
    public Event save(Event event) {
        em.getTransaction().begin();
        em.persist(event);
        em.getTransaction().commit();
        return event;
    }

    @Override
    public void delete(long id) {
        em.getTransaction().begin();
        Event event = em.find(Event.class, id);
        if (event != null) {
            em.remove(event);
        }
        em.getTransaction().commit();
    }

    @Override
    public Event update(Event event) {
        em.getTransaction().begin();
        em.merge(event);
        em.getTransaction().commit();
        return event;
    }

    @Override
    public Event find(long id) {
        return em.find(Event.class, id);
    }

    @Override
    public List<Event> findAll() {
        String jpql = "SELECT e FROM Event e";
        TypedQuery<Event> query = em.createQuery(jpql, Event.class);
        return query.getResultList();
    }
    @Override
    public List<Event> fetchCreatedEventOfUser(Long user_id) {
        Query query = em.createQuery("select e from Event e where e.organizer.id = :user_id", Event.class);
        query.setParameter("user_id", user_id);
        return query.getResultList();
    }



}