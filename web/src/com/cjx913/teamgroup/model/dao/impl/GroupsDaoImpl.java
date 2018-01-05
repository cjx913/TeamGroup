package com.cjx913.teamgroup.model.dao.impl;

import com.cjx913.teamgroup.exception.DataAccessException;
import com.cjx913.teamgroup.jdbc.JDBCTemplate;
import com.cjx913.teamgroup.jdbc.PreparedStatementSetter;
import com.cjx913.teamgroup.jdbc.RowCallBackHandler;
import com.cjx913.teamgroup.model.entity.Groups;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GroupsDaoImpl extends CommonDaoImpl<Groups> {
    @Override
    public List<Groups> findAllByID(int id) {
        try {
            return JDBCTemplate.multipeQuery("SELECT MANAGER_ID,GROUP_ID,GROUP_NAME FROM GROUPS WHERE GROUP_ID IN (SELECT GROUP_ID FROM GROUP_USER WHERE USER_ID=?)",
                    new PreparedStatementSetter() {
                        @Override
                        public void setvalues(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1,id);
                        }
                    },createHandle() );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private RowCallBackHandler<Groups> createHandle(){
        return new RowCallBackHandler<Groups>() {
            @Override
            public Groups processRow(ResultSet rs) throws SQLException {
                Groups group = new Groups();
                group.setGroupId(rs.getInt(2));
                group.setGroupName(rs.getString(3));
                group.setManagerId(rs.getInt(1));
                return group;
            }
        };
    }
}
