package com.gathergrid.gathergridfeatures.controller;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(loadOnStartup = 1)
public class InitialServlet extends HttpServlet {
    @Override
    public void init() {
        EntityManagerFactory efm = Persistence.createEntityManagerFactory("my-persistence-unit");
    }
}