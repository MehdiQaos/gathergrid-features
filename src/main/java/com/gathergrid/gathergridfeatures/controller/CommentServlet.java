package com.gathergrid.gathergridfeatures.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Comment", value = {"/createComment", "/editComment", "/updateComment", "/deleteComment", "/showComment"})
public class CommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if(path.equals("/createComment") && req.getMethod().equals("POST")){

        }else if (path.equals("/updateComment") && req.getMethod().equals("POST")) {

        }else if (path.equals("/editComment")){

        }else if(path.equals("/deleteComment")) {

        }else if(path.equals("/showComment")){

        }else {
            resp.sendError(resp.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
