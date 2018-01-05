package com.cjx913.teamgroup.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private final static ThreadLocal<Connection> LOCAL = new ThreadLocal<>();
    private static DataSource DATASOURSE;

    public static void setDataSourse(DataSource dataSourse) {
        ConnectionManager.DATASOURSE = dataSourse;
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = LOCAL.get();
        if(conn!=null) {
            return conn;
        }
        conn = DATASOURSE.getConnection();
        LOCAL.set(conn);

        return conn;
    }

    public static void release() {
        Connection conn = LOCAL.get();
        if(conn!=null) {
            DBUtil.release(conn);
            LOCAL.remove();
        }
    }
}
