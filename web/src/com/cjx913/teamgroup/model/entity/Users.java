package com.cjx913.teamgroup.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Users implements Serializable{
    private static final long SerialVersionUID = 1L;
    private int id;
    private String userName;
    private String userPassword;
    private String userEMail;
    private String corporation;
    private String duty;

    private List<Schedules> schedulesList = new ArrayList<>();
    private List<Contacts> contactsList = new ArrayList<>();
    private List<Groups> groupsList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEMail() {
        return userEMail;
    }

    public void setUserEMail(String userEMail) {
        this.userEMail = userEMail;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public List<Schedules> getSchedulesList() {
        return schedulesList;
    }

    public void setSchedulesList(List<Schedules> schedules) {
        this.schedulesList = schedules;
    }

    public List<Contacts> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<Contacts> contactsList) {
        this.contactsList = contactsList;
    }

    public List<Groups> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<Groups> groupsList) {
        this.groupsList = groupsList;
    }
}
