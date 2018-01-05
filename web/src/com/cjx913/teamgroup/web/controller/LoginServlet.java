package com.cjx913.teamgroup.web.controller;

import com.cjx913.teamgroup.model.entity.Users;
import com.cjx913.teamgroup.model.service.ICommonService;
import com.cjx913.teamgroup.model.service.ServiceFactory;
import com.cjx913.teamgroup.model.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/do/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Users user = new Users();
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        String condition = "WHERE USER_NAME='"+name+"' AND USER_PASSWORD='"+password+"'";
        user = ((UsersServiceImpl) ServiceFactory.getService("Users")).findByCondition(condition);

        if (user == null) {
            session.setAttribute("msg", "user name or password is flase.");
            resp.sendRedirect("/jsp/login.jsp");
            return;
        }
        user.setSchedulesList(ServiceFactory.getService("Schedules").findAllByID(user.getId()));
        user.setContactsList(ServiceFactory.getService("Contacts").findAllByID(user.getId()));
        user.setGroupsList(ServiceFactory.getService("Groups").findAllByID(user.getId()));

        session.setAttribute("user", user);

        resp.sendRedirect("/index.jsp");
    }
}
