package com.gathergrid.gathergridfeatures.service;

import com.gathergrid.gathergridfeatures.domain.Category;
import com.gathergrid.gathergridfeatures.domain.Event;
import com.gathergrid.gathergridfeatures.domain.Ticket;
import com.gathergrid.gathergridfeatures.domain.User;
import com.gathergrid.gathergridfeatures.repository.interfaces.EventRepository;
import com.gathergrid.gathergridfeatures.repository.interfacesImpl.EventRepositoryImpl;
import com.gathergrid.gathergridfeatures.repository.interfacesImpl.UserRepository;
import com.gathergrid.gathergridfeatures.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository<User> userRepository = new UserRepository<>();

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventService() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        eventRepository = new EventRepositoryImpl(em);
    }

    public Event createEvent(Event event, long organizerId, List<Ticket> tickets, long categoryId) {
        for (Ticket ticket: tickets)
            event.addTicket(ticket);

        CategoryService categoryService = new CategoryService();
        UserService userService = new UserService(userRepository);

        Category category = categoryService.getById(categoryId);
        User user = userService.getById(organizerId);
        event.setOrganizer(user);
        event.setCategory(category);
        return eventRepository.save(event);
    }

    public Event updateEvent(long event_id,Event event, long organizerId, List<Ticket> tickets, long categoryId) {
        Event event1 = eventRepository.find(event_id);
        for(Ticket ticket: tickets)
            event.addTicket(ticket);
        CategoryService categoryService = new CategoryService();
        UserService userService = new UserService(userRepository);
        Category category = categoryService.getById(categoryId);
        User user = userService.getById(organizerId);
        event.setOrganizer(user);
        event.setCategory(category);
        event1.setDate(event.getDate());
        event1.setName(event.getName());
        event1.setDescription(event.getDescription());
        event1.setAddress(event.getDescription());
        return eventRepository.update(event1);
    }

    public Event findById(long id) {
        return eventRepository.find(id);
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public Event delete(Event event) {
        eventRepository.delete(event.getId());
        return event;
    }

    public List<Event> fetchAllEventOfUser(Long user_id){
        return eventRepository.fetchCreatedEventOfUser(user_id);
    }
}
