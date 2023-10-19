package com.gathergrid.gathergridfeatures.controller;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.ws.rs.core.Request;
import org.mindrot.jbcrypt.BCrypt;

public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServletException {

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