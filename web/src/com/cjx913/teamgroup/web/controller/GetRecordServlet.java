package com.cjx913.teamgroup.web.controller;

import com.cjx913.teamgroup.model.entity.ChattingMessage;
import com.cjx913.teamgroup.model.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GetRecordServlet", urlPatterns = "/do/getRecord")
public class GetRecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/xml;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        String userid = req.getParameter("userid");
        String contactid = req.getParameter("contactid");
        String condition = "WHERE (FROMID='"+userid+"' AND TOID='"+contactid+"') OR (FROMID='"+contactid+"' AND TOID='"+userid+"') ORDER BY SENDTIME";
        List<ChattingMessage> list = ServiceFactory.getService("ChattingMessage").findAllByCondition(condition);

        StringBuffer buffer = new StringBuffer();
        buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        buffer.append("<messages>");
        for(ChattingMessage cm:list){
            buffer.append("<message>");
            buffer.append("<fromid>"+cm.getFrom()+"</fromid>");
            buffer.append("<toid>"+cm.getTo()+"</toid>");
            buffer.append("<msg>"+cm.getMessage()+"</msg>");
            buffer.append("<sendtime>"+cm.getSendTime()+"</sendtime>");
            buffer.append("</message>");
        }
        buffer.append("</messages>");
        out.println(buffer.toString());
        out.close();
    }
}
