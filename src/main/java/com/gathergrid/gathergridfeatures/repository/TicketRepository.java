package com.gathergrid.gathergridfeatures.repository;

import com.gathergrid.gathergridfeatures.domain.Ticket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TicketRepository {
    private final EntityManager entityManager;
    public TicketRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Ticket save(Ticket ticket){
        entityManager.getTransaction().begin();
        entityManager.persist(ticket);
        entityManager.getTransaction().commit();
        return ticket;
    }

    public Ticket update(Ticket ticket){
        entityManager.getTransaction().begin();
        entityManager.merge(ticket);
        entityManager.getTransaction().commit();
        return ticket;
    }

    public void delete(long id){
        Ticket ticket = find(id);
        if (ticket == null) {
            return;
        }
        entityManager.getTransaction().begin();
        entityManager.remove(ticket);
    }

    public Ticket find(long id){
        return entityManager.find(Ticket.class, id);
    }

    public List<Ticket> findAll(){
        String jpqlQuery = "SELECT t FROM Ticket t";
        TypedQuery<Ticket> query = entityManager.createQuery(jpqlQuery, Ticket.class);
        return query.getResultList();
    }

    public List<Ticket> finAllEventTickets(Long id){
        String jpqlQuery = "SELECT t FROM Ticket t join Event e on t.event.id = :id";
        TypedQuery<Ticket> query = entityManager.createQuery(jpqlQuery, Ticket.class).setParameter("id", id);
        return query.getResultList();
    }

}
