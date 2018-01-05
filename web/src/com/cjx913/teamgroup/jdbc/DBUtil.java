package com.cjx913.teamgroup.jdbc;

import java.sql.*;

public class DBUtil {
    public static void release(ResultSet rs) {
        if (rs != null) {
            try {
                release(rs.getStatement());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void release(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.clearBatch();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static  void release(PreparedStatement pstmt){
        if(pstmt!=null){
            try {
                pstmt.clearBatch();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void release(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
