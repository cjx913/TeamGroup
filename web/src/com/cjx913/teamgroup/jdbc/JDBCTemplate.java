package com.cjx913.teamgroup.jdbc;

import com.cjx913.teamgroup.exception.DataAccessException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTemplate {
    private JDBCTemplate(){

    }
    private static ResultSet query(String sql, PreparedStatementSetter setter) throws DataAccessException {
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            if (setter != null) {
                setter.setvalues(pstmt);
            }
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
        return rs;
    }

    public static <T> T singleQuery(String sql, PreparedStatementSetter setter, RowCallBackHandler<T> handler) throws DataAccessException {
        T t = null;
        ResultSet rs = null;
        try {
            rs = query(sql, setter);
            if (handler != null && rs.next()) {
                t = handler.processRow(rs);
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        } finally {
            DBUtil.release(rs);
        }
        return t;
    }

    public static <T> List<T> multipeQuery(String sql,PreparedStatementSetter setter,RowCallBackHandler<T> handler)throws DataAccessException{
        T t = null;
        List<T> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = query(sql,setter);
            if(handler!=null&&rs!=null){
                while (rs.next()){
                    t = handler.processRow(rs);
                    list.add(t);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        } finally {
            DBUtil.release(rs);
        }
        return list;
    }

    public static int upadte(String sql, PreparedStatementSetter setter) throws DataAccessException {
        PreparedStatement pstmt = null;
        try {
            pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            if (setter != null) {
                setter.setvalues(pstmt);
            }
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        } finally {
            DBUtil.release(pstmt);
        }
    }

    public static int[] batchUpdate(String sql, PreparedStatementSetter setter) throws DataAccessException {
        PreparedStatement pstmt = null;
        try {
            pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            if (setter != null) {
                setter.setvalues(pstmt);
            }
            return pstmt.executeBatch();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        } finally {
            DBUtil.release(pstmt);
        }
    }
}
