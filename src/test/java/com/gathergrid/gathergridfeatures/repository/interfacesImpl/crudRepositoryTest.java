package com.gathergrid.gathergridfeatures.repository.interfacesImpl;

import com.gathergrid.gathergridfeatures.domain.*;
import com.gathergrid.gathergridfeatures.domain.enums.TicketType;
import com.gathergrid.gathergridfeatures.repository.interfaces.EventRepository;
import com.gathergrid.gathergridfeatures.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class crudRepositoryTest {

    private EventService eventService;


    private EventRepository eventRepository;

    @BeforeEach
    public void setUp() {
        eventRepository=Mockito.mock(EventRepository.class);
        eventService = new EventService(eventRepository); // Instantiate the class under test
    }


    @Test
    void save() {
        Event event = new Event("ee", LocalDateTime.now(),"adress","desc");
        User organizer = new User("hello","youssef","yssef@gmail.com","1234");
        List<Ticket> tickets = List.of(new Ticket(10,1, TicketType.VIP), new Ticket(20,22, TicketType.STANDARD));
        Category category = new Category("name");

        Mockito.when(eventRepository.save(event)).thenReturn(event);

        // Act
        Event createdEvent = eventService.createEvent(event, 1, tickets, 1);

        // Assert
        assertNotNull(createdEvent);
        assertEquals(organizer, createdEvent.getOrganizer());
        assertEquals(category, createdEvent.getCategory());
        assertEquals(tickets.size(), createdEvent.getTickets().size());
        Mockito.verify(eventRepository).save(event);
    }

    @Test
    void findById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        long id =2 ;
        EventService eventService = new EventService();
        Event event = eventService.findById(2);
        Event deleted = eventService.delete(event);
        assertEquals(event,deleted);
    }

    @Test
    public void testCreatePyramid() {
        Pyramid pyramid = new Pyramid();
        String expectedOutput = "*\n**\n***\n";
        assertEquals(expectedOutput, pyramid.createPyramid(3));
    }
}