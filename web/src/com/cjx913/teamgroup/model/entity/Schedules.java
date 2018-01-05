package com.cjx913.teamgroup.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Schedules implements Serializable {
    private final static long SerialVersionUID = 1L;

    private int schedulesID;
    private int userID;
    private String date;
    private String event;

    private Users user;

    public int getSchedulesID() {
        return schedulesID;
    }

    public void setSchedulesID(int schedulesID) {
        this.schedulesID = schedulesID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
