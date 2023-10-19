package com.gathergrid.gathergridfeatures.controller;

import com.gathergrid.gathergridfeatures.domain.Event;
import com.gathergrid.gathergridfeatures.domain.Ticket;
import com.gathergrid.gathergridfeatures.domain.enums.TicketType;
import com.gathergrid.gathergridfeatures.repository.TicketRepository;
import com.gathergrid.gathergridfeatures.service.TicketService;
import com.gathergrid.gathergridfeatures.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/create-ticket")
public class TicketServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String priceString = req.getParameter("ticketPrice");
        String quantityString = req.getParameter("ticketQuantity");
        String ticketTypeString = req.getParameter("ticketType").toUpperCase();

        int price, quantity;
        TicketType ticketType;
        try{
            price = validatePrice(priceString);
            quantity = validateQuantity(quantityString);
            ticketType = validateTicketType(ticketTypeString);
        }catch (IllegalArgumentException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return;
        }

        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        TicketRepository ticketRepository = new TicketRepository(entityManager);
        TicketService ticketService = new TicketService(ticketRepository);

        Ticket ticket = new Ticket(price, quantity, ticketType);
//        ticketService.createTicket(ticket);
        resp.getWriter().println(ticketService.createTicket(ticket));

//        ticketService.deleteTicket(1);

//        List<Ticket> ticketList = ticketService.findAllTickets();
//        resp.setContentType("text/html");
//        resp.getWriter().println("<h1>List all tickets :</h1>");
//        resp.getWriter().println("<ul>");
//        for(Ticket t : ticketList){
//            resp.getWriter().println("<li>Price: " + t.getPrice()+ ", Quantity: " + t.getQuantityAvailable() + ", Type: " + t.getType() + "</li>");
//        }
//        resp.getWriter().println("</ul>");
    }

    public int validatePrice(String priceString){
        try{
            if(priceString.isBlank()){
                throw new IllegalArgumentException("Price cannot be blank");
            }
            int intPrice = Integer.parseInt(priceString);
            if (intPrice < 0){
                throw new IllegalArgumentException("Price cannot be negative");
            }
            return intPrice;
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Invalid price format");
        }
    }

    public int validateQuantity(String quantityString){
        try{
            if (quantityString.isBlank()){
                throw new IllegalArgumentException("Quantity cannot be blank");
            }
            int intQuantity = Integer.parseInt(quantityString);
            if(intQuantity < 1){
                throw new IllegalArgumentException("Quantity cannot be less than 1");
            }
            return intQuantity;
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Invalid quantity format");
        }
    }

    public TicketType validateTicketType(String ticketTypeString){
        try{
            TicketType ticketType = TicketType.valueOf(ticketTypeString);
            return ticketType;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid ticket type");
        }
    }
}
