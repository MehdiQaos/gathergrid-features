package com.gathergrid.gathergridfeatures.controller;

import com.gathergrid.gathergridfeatures.domain.Category;
import com.gathergrid.gathergridfeatures.domain.Event;
import com.gathergrid.gathergridfeatures.domain.Ticket;
import com.gathergrid.gathergridfeatures.domain.enums.TicketType;
import com.gathergrid.gathergridfeatures.service.CategoryService;
import com.gathergrid.gathergridfeatures.service.EventService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value = {"/event/add", "/event/delete", "/event/update"})
public class EventServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.getAllCategories();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/WEB-INF/addevent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "create" -> {
                Map<String, String> errors = new HashMap<>();
                int organizerId = 1;
                String path = req.getContextPath();

                String name = req.getParameter("name");
                String address = req.getParameter("address");
                String date = req.getParameter("date");
                String description = req.getParameter("description");
                String category = req.getParameter("category");

                int categoryId = Integer.parseInt(category);

                List<Ticket> tickets = getTicketsFromRequest(req, errors);

                if (tickets.isEmpty()) {
                    errors.put("tickets", "error tickets");
                }
                LocalDateTime localDateTime = LocalDateTime.now();
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                    localDateTime = LocalDateTime.parse(date, formatter);
                } catch (DateTimeParseException e) {
                    errors.put("datetime", "error date");
                }

                if (!errors.isEmpty()) {
                    req.setAttribute("errors", errors);
                    req.setAttribute("message", "error adding event");
                    this.doGet(req, resp);
                }

                try {
                    Event event = new Event(name, localDateTime, address, description);
                    EventService eventService = new EventService();
                    eventService.createEvent(event, organizerId, tickets, categoryId);
                } catch (Exception e) {
                    errors.put("server", "internal server error: event was not created");
                    this.doGet(req, resp);
                }
                resp.sendRedirect(path + "/Dashboard");
            }
            case "delete" -> {
                int userId = 1;
                String path = req.getContextPath();
                String param = req.getParameter("eventid");
                int eventId = Integer.parseInt(param);
                EventService eventService = new EventService();
                Event event = eventService.findById(eventId);
                if (event == null) {
                    req.getSession().setAttribute("error", "Event not found");
                }
                else if (userId != event.getOrganizer().getId()) {
                    req.getSession().setAttribute("error", "Unauthorized operation");
                } else {
                    eventService.delete(event);
                    req.getSession().setAttribute("message", "Event deleted successfully");
                }
                resp.sendRedirect(path + "/Dashboard");
            }
            case "update" -> {
                Map<String, String> errors = new HashMap<>();
                String path = req.getContextPath();
                String nameEvent = req.getParameter("name-event");
                String category = req.getParameter("category-edit");
                String date = req.getParameter("dateEventUpdated");
                String description = req.getParameter("descriptionEventUpdate");
                int event_id = Integer.parseInt(req.getParameter("idEvent"));
                int category_id = Integer.parseInt(category);
                LocalDateTime localDateTime = LocalDateTime.now();
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD'T'HH:mm");
                    localDateTime = LocalDateTime.parse(date,  formatter);
                } catch (DateTimeException e){
                    errors.put("dateTime","error date");
                }
                List<Ticket> tickets = new ArrayList<>();
                for(int i = 0 ; i < req.getParameterValues("ticketData[]").length ;i++){
                   int ticketIndex = Integer.parseInt(req.getParameterValues("ticketData[]")[i]);
                   float price = Float.parseFloat(req.getParameterValues("price[]")[i]);
                   int quantity = Integer.parseInt(req.getParameterValues("quantity[]")[i]);
                   Ticket ticketObj = new Ticket(price, quantity, TicketType.values()[ticketIndex]);
                   tickets.add(ticketObj);
               }
               Event event = new Event(nameEvent, localDateTime, description, description);
               EventService eventService = new EventService();
               eventService.updateEvent(event_id, event , 1, tickets, category_id);
                resp.sendRedirect(path + "/Dashboard");
            }
        }
    }

    private List<Ticket> getTicketsFromRequest(HttpServletRequest request, Map<String, String> errors) {
        List<Ticket> tickets = new ArrayList<>();
        for (TicketType ticketType: TicketType.values()) {
            String type = ticketType.name().toLowerCase();
            if (request.getParameter(type) == null)
                continue;

            String numberParam = type + "ticketsnumber";
            String priceParam = type + "ticketsprice";
            int number, price;
            try {
                number = Integer.parseInt(request.getParameter(numberParam));
                price = Integer.parseInt(request.getParameter(priceParam));
                if (number <= 0 || price < 0) {
                    errors.put(type, "number and price must be positive number");
                    continue;
                }
            } catch (NumberFormatException e) {
                errors.put(type, "all fields must be valid");
                continue;
            }

            Ticket ticket = new Ticket(price, number, ticketType);
            tickets.add(ticket);
        }
        return tickets;
    }
}
