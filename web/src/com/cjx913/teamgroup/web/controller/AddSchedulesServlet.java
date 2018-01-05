package com.cjx913.teamgroup.web.controller;

import com.cjx913.teamgroup.model.entity.Schedules;
import com.cjx913.teamgroup.model.entity.Users;
import com.cjx913.teamgroup.model.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddSchedulesServlet", urlPatterns = "/do/addSchedules")
public class AddSchedulesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");
        String add_date = req.getParameter("add_date");
        String add_time = req.getParameter("add_time");
        String add_event = req.getParameter("add_event");
        if(add_date.equals("")||add_time.equals("")||add_event.equals("")){
            req.setAttribute("msg","time or event can't null");
            req.getRequestDispatcher("/jsp/schedule_management.jsp").forward(req,resp);
            return ;
        }
        Schedules schedule = new Schedules();
        schedule.setSchedulesID(2);
        schedule.setUserID(user.getId());
        schedule.setDate(add_date+" "+add_time);
        schedule.setEvent(add_event);
        ServiceFactory.getService("Schedules").create(schedule);
        user.setSchedulesList(ServiceFactory.getService("Schedules").findAllByID(user.getId()));
        req.getSession().setAttribute("user",user);
        req.getRequestDispatcher("/jsp/schedule_management.jsp").forward(req,resp);
    }
}
