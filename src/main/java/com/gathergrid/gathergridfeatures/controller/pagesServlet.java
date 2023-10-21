package com.gathergrid.gathergridfeatures.controller;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = {"/myBooking", "/event", "/profile", "/Dashboard"})
public class pagesServlet extends HttpServlet {

    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServletException {
        String path = request.getServletPath();
        switch (path) {
            case "/myBooking":
                request.setAttribute("url","/myBooking");
                this.getServletContext().getRequestDispatcher("/WEB-INF/booked.jsp").forward(request, response);
                break;
            case "/event":
                this.getServletContext().getRequestDispatcher("/WEB-INF/events.jsp").forward(request, response);
                request.setAttribute("url","/events");
                break;
            case "/profile":
                this.getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
                request.setAttribute("url","/profile");
                break;
            case "/Dashboard":
                request.setAttribute("url","/Dashboard");
                this.getServletContext().getRequestDispatcher("/WEB-INF/homeUser.jsp").forward(request, response);
                break;
            default:
                response.sendError(response.SC_NOT_FOUND);
                break;
        }
    }

    public void destroy() {
    }
    public static void checkSessionNotEmpty(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session==null){
            request.getServletContext().getRequestDispatcher("signin");
        }
    }
}