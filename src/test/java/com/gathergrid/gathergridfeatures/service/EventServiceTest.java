package com.gathergrid.gathergridfeatures.service;

import com.gathergrid.gathergridfeatures.domain.Category;
import com.gathergrid.gathergridfeatures.domain.Event;
import com.gathergrid.gathergridfeatures.domain.Ticket;
import com.gathergrid.gathergridfeatures.domain.User;
import com.gathergrid.gathergridfeatures.domain.enums.TicketType;
import com.gathergrid.gathergridfeatures.repository.interfaces.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class EventServiceTest {

     private EventRepository eventRepository;
     private EventService eventService;
     private UserService userService;
     private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        eventRepository = Mockito.mock(EventRepository.class);
        userService = Mockito.mock(UserService.class);
        categoryService = Mockito.mock(CategoryService.class);
        eventService = new EventService(eventRepository , userService, categoryService);
    }

    @Test
    void createEvent() {
        LocalDateTime localDateTime = LocalDateTime.of(2023,11, 12,12,00);
        Event event = new Event("event", localDateTime, "youssoufia hay salam", "good event");
        List<Ticket> ticketList = new ArrayList<>();
        Ticket ticket1 = new Ticket(12.30F, 12, TicketType.VIP);
        Ticket ticket2 = new Ticket(23.30F, 120, TicketType.STUDENT);
        ticketList.add(ticket1);
        ticketList.add(ticket2);
        User user = new User(null ,"moussafia","m@gmail.com","1234");
        user.setId(1);
        Mockito.when(userService.getById(1)).thenReturn(user);
        Category category = new Category("food");
        category.setId(1);
        Mockito.when(categoryService.getById(1)).thenReturn(category);
        Mockito.when(eventRepository.save(any(Event.class))).thenAnswer(invocationOnMock ->{
            Event eventSaved = event;
            eventSaved.setId(1);
            return eventSaved;
        });
        Event eventResult = eventService.createEvent(event, 1, ticketList, 1);
        assertEquals(1, eventResult.getId());
    }
}