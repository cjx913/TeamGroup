package com.cjx913.teamgroup.jdbc;

import java.sql.SQLException;

import com.cjx913.teamgroup.exception.TransactionException;

public class TransactionManager {
	public static void begin() {
		try {
			ConnectionManager.getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			throw new TransactionException(e);
		}
	}
	
	public static void commit() {
		try {
			ConnectionManager.getConnection().commit();
		} catch (SQLException e) {
			throw new TransactionException(e);
		}finally {
			ConnectionManager.release();
		}
	}
	
	public static void rollback() {
		try {
			ConnectionManager.getConnection().rollback();
		} catch (SQLException e) {
			throw new TransactionException(e);
		}finally {
			ConnectionManager.release();
		}
	}
}
