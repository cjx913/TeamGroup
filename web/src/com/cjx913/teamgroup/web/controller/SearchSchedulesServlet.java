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
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = "/do/searchSchedules")
public class SearchSchedulesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");
        String search_date = req.getParameter("search_date");
        String search_time = req.getParameter("search_time");
        String search_keyword = req.getParameter("search_keyword");
        if(search_keyword==""){
            search_keyword = null;
        }
        if(search_date==""){
            search_date = null;
        }
        if(search_time==""){
            search_time = null;
        }
        /*System.out.println(search_date+" "+search_time+" "+search_keyword);*/

        String condition = "WHERE USER_ID="+user.getId()+" AND (EVENT LIKE '%"+search_keyword+"%' OR to_char(DATE_TIME,'yyyy-mm-dd') LIKE '%"+search_date+"%' OR to_char(DATE_TIME,'hh:mi') LIKE '%"+search_time+"')";
        List<Schedules> schedulesSearchList = ServiceFactory.getService("Schedules").findAllByCondition(condition);

        req.setAttribute("schedulesSearchList",schedulesSearchList);
        req.getRequestDispatcher("/jsp/schedule_management.jsp").forward(req,resp);
    }
}
