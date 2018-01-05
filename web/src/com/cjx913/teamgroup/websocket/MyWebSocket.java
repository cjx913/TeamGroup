package com.cjx913.teamgroup.websocket;

import com.cjx913.teamgroup.model.entity.ChattingMessage;
import com.cjx913.teamgroup.model.entity.Users;
import com.cjx913.teamgroup.model.service.ServiceFactory;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value = "/websockettest",configurator = GetHttpSessionConfigurator.class)
public class MyWebSocket {
    private String userId;
    private Session session;
    private final static Map<String,MyWebSocket> webSocketMap = new HashMap<>();

    public MyWebSocket() {
    }

    public Session getSession() {
        return session;
    }

    @OnOpen
    public void onOpen(Session session,EndpointConfig config){
        this.session = session;
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        Users user = (Users) httpSession.getAttribute("user");
        userId = String.valueOf(user.getId());
        if(!webSocketMap.containsKey(userId)){
            webSocketMap.put(userId,this);
        }

    }

    @OnMessage
    public void onMessage(String msg){
        String[] allMessage = msg.split("@");
        String from = userId;
        String to = allMessage[0];
        String message = allMessage[1];
        ChattingMessage chattingMessage = new ChattingMessage();
        chattingMessage.setFrom(Integer.parseInt(from));
        chattingMessage.setTo(Integer.parseInt(to));
        chattingMessage.setMessage(message);
        String sendtime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        chattingMessage.setSendTime(sendtime);
        //chattingMessage save in database
        ServiceFactory.getService("ChattingMessage").create(chattingMessage);

        MyWebSocket send = webSocketMap.get(from);
        send.getSession().getAsyncRemote().sendText(message+"@"+from+"@"+to+"@"+sendtime);
        if(webSocketMap.containsKey(to)){
            MyWebSocket receive = webSocketMap.get(to);
            receive.getSession().getAsyncRemote().sendText(message+"@"+from+"@"+to+"@"+sendtime);
        }
    }

    @OnError
    public void onError(Throwable throwable){
        /*throwable.printStackTrace();*/
    }

    @OnClose
    public void onClose(){
        webSocketMap.remove(userId);
        if(session!=null){
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
