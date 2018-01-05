package com.cjx913.teamgroup.web.controller;

import com.cjx913.teamgroup.model.dao.DaoFactory;
import com.cjx913.teamgroup.model.dao.impl.UsersDaoImpl;
import com.cjx913.teamgroup.model.entity.Users;
import com.cjx913.teamgroup.model.service.ServiceFactory;
import com.cjx913.teamgroup.model.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/do/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        Users user = new Users();
        user.setUserName(name);
        user.setUserPassword(password);
        user.setUserEMail(email);
        user.setCorporation("your corporation");
        user.setDuty("your duty");

        int i = ((UsersServiceImpl) ServiceFactory.getService("Users")).create(user);
        HttpSession session = req.getSession();
        if(i==-1){
            session.setAttribute("msg","register failed!");
            resp.sendRedirect("/jsp/register.jsp");
            return;
        }
        String condition = "WHERE USER_NAME='"+name+"' AND USER_PASSWORD='"+password+"'";
        user = ((UsersServiceImpl) ServiceFactory.getService("Users")).findByCondition(condition);

        session.setAttribute("user",user);

        resp.sendRedirect("/index.jsp");
    }
}
