package com.cjx913.teamgroup.model.dao.impl;

import com.cjx913.teamgroup.exception.DataAccessException;
import com.cjx913.teamgroup.jdbc.JDBCTemplate;
import com.cjx913.teamgroup.jdbc.PreparedStatementSetter;
import com.cjx913.teamgroup.jdbc.RowCallBackHandler;
import com.cjx913.teamgroup.model.entity.ChattingMessage;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ChattingMessageDaoImpl extends CommonDaoImpl<ChattingMessage> {
    @Override
    public int create(ChattingMessage chattingMessage) {
        try {
            return JDBCTemplate.upadte("INSERT INTO CHATMESSAGE(FROMID,TOID,MESSAGE,SENDTIME) VALUES(?,?,?,to_Date(?,'yyyy-mm-dd hh24:mi:ss'))",
                    new PreparedStatementSetter() {
                        @Override
                        public void setvalues(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1,chattingMessage.getFrom());
                            pstmt.setInt(2,chattingMessage.getTo());
                            pstmt.setString(3,chattingMessage.getMessage());
                            pstmt.setString(4,chattingMessage.getSendTime());
                        }
                    });
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<ChattingMessage> findAllByCondition(String condition) {
        try {
            return JDBCTemplate.multipeQuery(
                    "SELECT MESSAGEID,FROMID,TOID,MESSAGE,TO_CHAR(SENDTIME,'yyyy-mm-dd hh24:mi') FROM CHATMESSAGE "+condition,
                    null,
                    createHandle()
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private RowCallBackHandler<ChattingMessage> createHandle(){
        return new RowCallBackHandler<ChattingMessage>() {
            @Override
            public ChattingMessage processRow(ResultSet rs) throws SQLException {
                ChattingMessage chattingMessage = new ChattingMessage();
                chattingMessage.setMessageId(rs.getInt(1));
                chattingMessage.setFrom(rs.getInt(2));
                chattingMessage.setTo(rs.getInt(3));
                chattingMessage.setMessage(rs.getString(4));
                chattingMessage.setSendTime(rs.getString(5));
                return chattingMessage;
            }
        };

    }
}
