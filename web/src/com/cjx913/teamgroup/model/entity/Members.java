package com.cjx913.teamgroup.model.entity;

import java.io.Serializable;

public class Members implements Serializable {
    private static final long SerialVersionUID = 1L;

    private int memberId;
    private int GroupId;
    private String memberName;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int groupId) {
        GroupId = groupId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
