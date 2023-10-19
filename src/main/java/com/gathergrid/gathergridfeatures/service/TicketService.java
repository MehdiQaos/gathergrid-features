package com.gathergrid.gathergridfeatures.service;


import com.gathergrid.gathergridfeatures.domain.Ticket;
import com.gathergrid.gathergridfeatures.domain.enums.TicketType;
import com.gathergrid.gathergridfeatures.repository.TicketRepository;

import java.util.EnumSet;
import java.util.List;

public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Ticket ticket){
        return ticketRepository.update(ticket);
    }
    public void deleteTicket(long id){
        ticketRepository.delete(id);
    }

    public Ticket findTicket(long id){
        return ticketRepository.find(id);
    }

    public List<Ticket> findAllTickets(){
        return ticketRepository.findAll();
    }


}
