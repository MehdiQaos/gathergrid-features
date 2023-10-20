package com.gathergrid.gathergridfeatures.controller;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.ws.rs.core.Request;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServletException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/homeUser.jsp").forward(request,response);
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