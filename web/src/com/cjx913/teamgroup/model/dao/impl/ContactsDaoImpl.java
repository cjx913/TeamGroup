package com.cjx913.teamgroup.model.dao.impl;

import com.cjx913.teamgroup.exception.DataAccessException;
import com.cjx913.teamgroup.jdbc.JDBCTemplate;
import com.cjx913.teamgroup.jdbc.PreparedStatementSetter;
import com.cjx913.teamgroup.jdbc.RowCallBackHandler;
import com.cjx913.teamgroup.model.entity.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactsDaoImpl extends CommonDaoImpl<Contacts> {
    @Override
    public List<Contacts> findAllByID(int id) {
        List<Contacts> list = new ArrayList<>();
        try {
            list = JDBCTemplate.multipeQuery(
                    "SELECT USER_ID,USER_NAME FROM USERS WHERE USER_ID IN (SELECT CONTACT_ID FROM CONTACTS WHERE USER_ID=?)",
                    new PreparedStatementSetter() {
                        @Override
                        public void setvalues(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1,id);
                        }
                    },
                    createHandle()
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }finally {
            return list;
        }
    }

    private RowCallBackHandler<Contacts> createHandle(){
        return new RowCallBackHandler<Contacts>() {
            @Override
            public Contacts processRow(ResultSet rs) throws SQLException {
                Contacts contact = new Contacts();
                contact.setId(rs.getInt(1));
                contact.setContactName(rs.getString(2));
                return contact;
            }
        };
    }
}
