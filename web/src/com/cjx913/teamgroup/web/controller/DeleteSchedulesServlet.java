package com.cjx913.teamgroup.web.controller;

import com.cjx913.teamgroup.model.entity.Users;
import com.cjx913.teamgroup.model.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteSchedulesServlet", urlPatterns = "/do/deleteSchedules")
public class DeleteSchedulesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users)req.getSession().getAttribute("user");
        String[] ids = req.getParameterValues("event_checkbox");
        int[] schedulesIds = new int[ids.length];
        for(int i=0;i<ids.length;i++){
            schedulesIds[i] = Integer.parseInt(ids[i].trim());
        }
        ServiceFactory.getService("Schedules").deleteByIDs(schedulesIds);
        user.setSchedulesList(ServiceFactory.getService("Schedules").findAllByID(user.getId()));

        req.getRequestDispatcher("/jsp/schedule_management.jsp").forward(req,resp);

    }
}
