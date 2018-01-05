package com.cjx913.teamgroup.jdbc;


import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowCallBackHandler<T> {
    T processRow(ResultSet rs) throws SQLException;
}
