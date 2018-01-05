package com.cjx913.teamgroup.model.dao.impl;

import com.cjx913.teamgroup.exception.DataAccessException;
import com.cjx913.teamgroup.jdbc.JDBCTemplate;
import com.cjx913.teamgroup.jdbc.PreparedStatementSetter;
import com.cjx913.teamgroup.jdbc.RowCallBackHandler;
import com.cjx913.teamgroup.model.entity.Users;
import jdk.internal.org.objectweb.asm.Handle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDaoImpl extends CommonDaoImpl<Users> {
    @Override
    public int create(Users entity) {
        try {
            return JDBCTemplate.upadte("INSERT INTO USERS(USER_NAME,USER_PASSWORD,USER_EMAIL,CORPORATION,DUTY) VALUES(?,?,?,?,?)",
                    new PreparedStatementSetter() {
                        @Override
                        public void setvalues(PreparedStatement pstmt) throws SQLException {
                            pstmt.setString(1, entity.getUserName());
                            pstmt.setString(2, entity.getUserPassword());
                            pstmt.setString(3, entity.getUserEMail());
                            pstmt.setString(4,entity.getCorporation());
                            pstmt.setString(5,entity.getDuty());
                        }
                    }
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int update(Users entity) {
        try {
            return JDBCTemplate.upadte("UPDATE USERS SET USER_NAME=?,USER_PASSWORD=?,USER_EMAIL=?,CORPORATION=?,DUTY=? WHERE USER_ID=?",
                    new PreparedStatementSetter() {
                        @Override
                        public void setvalues(PreparedStatement pstmt) throws SQLException {
                            pstmt.setString(1,entity.getUserName());
                            pstmt.setString(2,entity.getUserPassword());
                            pstmt.setString(3,entity.getUserEMail());
                            pstmt.setString(4,entity.getCorporation());
                            pstmt.setString(5,entity.getDuty());
                            pstmt.setInt(6,entity.getId());
                        }
                    });
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Users findByCondition(String condition) {
        String sql = "SELECT * FROM USERS "+condition;
        try {
            return JDBCTemplate.singleQuery(
                    sql,
                    null,
                    createHandle()
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private RowCallBackHandler<Users> createHandle(){
        return new RowCallBackHandler<Users>() {
            @Override
            public Users processRow(ResultSet rs) throws SQLException {
                Users user = new Users();
                user.setId(rs.getInt("USER_ID"));
                user.setUserName(rs.getString("USER_NAME").trim());
                user.setUserPassword(rs.getString("USER_PASSWORD").trim());
                user.setUserEMail(rs.getString("USER_EMAIL").trim());
                user.setCorporation(rs.getString("CORPORATION").trim());
                user.setDuty(rs.getString("DUTY").trim());
                return user;
            }
        };
    }
}
