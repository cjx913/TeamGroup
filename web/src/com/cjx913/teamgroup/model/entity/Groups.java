package com.cjx913.teamgroup.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Groups implements Serializable {
    private static final long SerialVersionUID = 1L;

    private int groupId;
    private String groupName;
    private int managerId;

    private List<Members> MenbersList = new ArrayList<>();

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public List<Members> getMenbersList() {
        return MenbersList;
    }

    public void setMenbersList(List<Members> menbersList) {
        MenbersList = menbersList;
    }
}
