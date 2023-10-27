package com.gathergrid.gathergridfeatures.service;

import com.gathergrid.gathergridfeatures.domain.Category;
import com.gathergrid.gathergridfeatures.domain.Event;
import com.gathergrid.gathergridfeatures.domain.Ticket;
import com.gathergrid.gathergridfeatures.domain.User;
import com.gathergrid.gathergridfeatures.repository.interfaces.EventRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceTest {
    EventService eventService;
    @Test
    void createEvent() {
        Event event = new Event("test", LocalDateTime.now(),"adress","desc");
        User organizer = new User();
        List<Ticket> tickets = List.of(new Ticket(), new Ticket());
        Category category = new Category();
        Event createdEvent = eventService.createEvent(event, organizer.getId(), tickets, category.getId());
        assertNotNull(createdEvent);
        assertEquals(organizer, createdEvent.getOrganizer());
        assertEquals(category, createdEvent.getCategory());
        assertEquals(tickets.size(), createdEvent.getTickets().size());

    }
}