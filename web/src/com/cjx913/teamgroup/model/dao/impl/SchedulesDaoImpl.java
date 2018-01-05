package com.cjx913.teamgroup.model.dao.impl;

import com.cjx913.teamgroup.exception.DataAccessException;
import com.cjx913.teamgroup.jdbc.JDBCTemplate;
import com.cjx913.teamgroup.jdbc.PreparedStatementSetter;
import com.cjx913.teamgroup.jdbc.RowCallBackHandler;
import com.cjx913.teamgroup.model.entity.Schedules;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SchedulesDaoImpl extends CommonDaoImpl<Schedules> {
    @Override
    public int create(Schedules entity) {
        try {
            return JDBCTemplate.upadte("INSERT INTO SCHEDULES(SCHEDULES_ID,USER_ID,DATE_TIME,EVENT) VALUES(?,?,TO_Date(?,'yyyy-mm-dd hh24:mi:ss'),?)",
                    new PreparedStatementSetter() {
                        @Override
                        public void setvalues(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, entity.getSchedulesID());
                            pstmt.setInt(2, entity.getUserID());
                            pstmt.setString(3, entity.getDate().trim());
                            pstmt.setString(4, entity.getEvent().trim());
                        }
                    }
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Schedules> findAllByID(int id) {
        List<Schedules> list = new ArrayList<>();
        try {
            list = JDBCTemplate.multipeQuery(
                    /*TO_CHAR(DATE_TIME,'yyyy-mm-dd hh24:mi')*/"SELECT SCHEDULES_ID,TO_CHAR(DATE_TIME,'yyyy-mm-dd hh24:mi'),EVENT FROM SCHEDULES WHERE USER_ID=? ORDER BY DATE_TIME DESC",
                    new PreparedStatementSetter() {
                        @Override
                        public void setvalues(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, id);
                        }
                    },
                    createHandle(id)
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        } finally {
            return list;
        }

    }

    private RowCallBackHandler<Schedules> createHandle(int id) {
        return new RowCallBackHandler<Schedules>() {
            @Override
            public Schedules processRow(ResultSet rs) throws SQLException {
                Schedules schedule = new Schedules();
                schedule.setSchedulesID(rs.getInt(1));
                schedule.setUserID(id);
                schedule.setDate(rs.getString(2).trim());
                schedule.setEvent(rs.getString(3).trim());
                return schedule;
            }
        };
    }

    @Override
    public List<Schedules> findAllByCondition(String condition) {
        List<Schedules> list = new ArrayList<>();
        String sql = "SELECT SCHEDULES_ID,USER_ID,TO_CHAR(DATE_TIME,'yyyy-mm-dd hh24:mi'),EVENT FROM SCHEDULES " + condition;
        /*System.out.println(sql);*/
        try {
            list = JDBCTemplate.multipeQuery(
                    sql,
                    null,
                    createHandle()
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    private RowCallBackHandler<Schedules> createHandle() {
        return new RowCallBackHandler<Schedules>() {
            @Override
            public Schedules processRow(ResultSet rs) throws SQLException {
                Schedules schedule = new Schedules();
                schedule.setSchedulesID(rs.getInt(1));
                schedule.setUserID(rs.getInt(2));
                schedule.setDate(rs.getString(3).trim());
                schedule.setEvent(rs.getString(4).trim());
                return schedule;
            }
        };
    }

    @Override
    public int[] deleteByIDs(final int... ids) {
        try {
            return JDBCTemplate.batchUpdate("DELETE FROM SCHEDULES WHERE SCHEDULES_ID=?",
                    new PreparedStatementSetter() {
                        @Override
                        public void setvalues(PreparedStatement pstmt) throws SQLException {
                            for(int id:ids){
                                pstmt.setInt(1,id);
                                pstmt.addBatch();
                            }
                        }
                    });
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

