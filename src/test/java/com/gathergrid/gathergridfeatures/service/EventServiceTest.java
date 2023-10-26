package com.gathergrid.gathergridfeatures.service;

import com.gathergrid.gathergridfeatures.domain.Category;
import com.gathergrid.gathergridfeatures.domain.Event;
import com.gathergrid.gathergridfeatures.domain.Ticket;
import com.gathergrid.gathergridfeatures.domain.enums.TicketType;
import com.gathergrid.gathergridfeatures.repository.interfacesImpl.EventRepositoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;

public class EventServiceTest {


    private EventRepositoryImpl eventRepository;
    private EventService eventService;
    private CategoryService categoryService;
    private UserService userService;
    @BeforeEach
    public void init(){
        eventRepository = Mockito.mock(eventRepository.getClass());
        categoryService = Mockito.mock(categoryService.getClass());
        userService = Mockito.mock(userService.getClass());
        eventService = new EventService(eventRepository);
    }

    @Test
    void createEvent() {
        LocalDateTime eventDate = LocalDateTime.of(2023,11,11,12,00);
        Event event = new Event("aid adha", eventDate,"youssoufia hay salam rue 08","good event");
        Ticket ticket = new Ticket(12.30F, 12, TicketType.VIP);
        ticket.setEvent(event);
        Ticket ticket2 = new Ticket(12.30F, 11, TicketType.STUDENT);
        ticket2.setEvent(event);
        List<Ticket> ticketList =new ArrayList<>();
        ticketList.add(ticket);
        ticketList.add(ticket2);
        Category category = new Category("OOP");
        category.setId(1);
//        Event event2 = new Event("aid adha", eventDate,"youssoufia hay salam rue 08","good event");
//        event2.setId(1);
        Mockito.when(eventRepository.save(any(Event.class))).thenAnswer( invocation ->{
            Event savedEvent = invocation.getArgument(0);
            savedEvent.setId(1);
            return savedEvent;
        });
        Event createdEvent = eventService.createEvent(event,1, ticketList, 1);
        assertEquals(1,createdEvent.getId());
        Mockito.verify(eventRepository, atLeastOnce()).save(any(Event.class));


    }

}