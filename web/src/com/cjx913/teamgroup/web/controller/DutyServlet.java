package com.cjx913.teamgroup.web.controller;

import com.cjx913.teamgroup.model.entity.Users;
import com.cjx913.teamgroup.model.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DutyServlet", urlPatterns = "/do/duty")
public class DutyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String duty = req.getParameter("duty");
        Users user = (Users) req.getSession().getAttribute("user");
        user.setDuty(duty);
        ServiceFactory.getService("Users").update(user);
    }
}
