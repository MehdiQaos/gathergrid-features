package com.gathergrid.gathergridfeatures.repository.interfaces;

import com.gathergrid.gathergridfeatures.domain.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository {
    public Event save(Event event);
    public void delete(long id);
    public Event update(Event event);
    public Event find(long id);
    public List<Event> findAll();
    public List<Event> fetchCreatedEventOfUser(Long user_id);
    public List<Event> findEventsByCriteria(LocalDateTime fromDate, LocalDateTime toDate, String name, Long categoryId);
}
