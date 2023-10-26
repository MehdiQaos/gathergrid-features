package com.gathergrid.gathergridfeatures.service;

import com.gathergrid.gathergridfeatures.domain.Category;
import com.gathergrid.gathergridfeatures.domain.Event;
import com.gathergrid.gathergridfeatures.domain.Ticket;
import com.gathergrid.gathergridfeatures.domain.User;
import com.gathergrid.gathergridfeatures.domain.enums.TicketType;
import com.gathergrid.gathergridfeatures.repository.interfaces.CategoryRepository;
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
import static org.mockito.Mockito.*;

public class EventServiceTest {


    private EventRepositoryImpl eventRepository;
    private EventService eventService;
    private CategoryService categoryService;
    private UserService userService;
    @BeforeEach
    public void init(){
        eventRepository = Mockito.mock(EventRepositoryImpl.class);

        categoryService = Mockito.mock(CategoryService.class);

        userService = Mockito.mock(UserService.class);

        eventService = new EventService(eventRepository, categoryService, userService);
    }

    @Test
    void createEvent() {
        LocalDateTime eventDate = LocalDateTime.of(2023,11,11,12,00);
        Event event = new Event("aid adha", eventDate,"youssoufia hay salam rue 08","good event");
        Ticket ticket = new Ticket(12.30F, 12, TicketType.VIP);
        ticket.setId(0);
        ticket.setEvent(event);
        Ticket ticket2 = new Ticket(23.30F, 110, TicketType.STUDENT);
        ticket2.setId(1);
        ticket2.setEvent(event);
        List<Ticket> ticketList =new ArrayList<>();
        ticketList.add(ticket);
        ticketList.add(ticket2);
        Category category = new Category("OOP");
        category.setId(1);
        User user = new User("mohammed","moussafia","1234","m@gmail.com");
        user.setId(1);

        Mockito.when(categoryService.getById(1)).thenReturn(category);
        Mockito.when(userService.getById(1)).thenReturn(user);

        Mockito.when(eventRepository.save(any(Event.class))).thenAnswer( invocation ->{
            Event savedEvent = invocation.getArgument(0);
            savedEvent.setId(1);
            return savedEvent;
        });
        Event createdEvent = eventService.createEvent(event,1, ticketList, 1);
        assertEquals(1,createdEvent.getId());
        Mockito.verify(eventRepository, times(1)).save(any(Event.class));
    }

}