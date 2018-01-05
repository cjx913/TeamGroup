package com.cjx913.teamgroup.web.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChattingServlet", urlPatterns = "/do/chatting")
public class ChattingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contactId = req.getParameter("contactId");
        int id = Integer.parseInt(contactId);
    }
}
