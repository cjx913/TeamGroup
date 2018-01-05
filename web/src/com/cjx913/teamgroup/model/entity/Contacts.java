package com.cjx913.teamgroup.model.entity;

import java.io.Serializable;

public class Contacts implements Serializable {
    private static final long SerialVersionUID = 1L;
    private int id;
    private String contactName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
