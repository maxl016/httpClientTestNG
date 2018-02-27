/*
 * DataBaseException.java
 * Copyright (c) 2015 KINJO
 * All rights reserved.
 */
package com.jason.framework.exception;

import java.sql.SQLException;

/**
 * 数据库操作异常
 * 
 * @author jinchengYang
 * @version 1.0 2015-06-11
 * @since 1.0
 */
public class DataBaseAccessException extends NestedInspectException {

	private static final long serialVersionUID = 4669255838898730123L;

	public DataBaseAccessException(String msg) {
		super(msg);
		this.sql = "";
	}

	public DataBaseAccessException(Throwable cause) {
		super(cause);
		this.sql = "";
	}

	public DataBaseAccessException(String msg, Throwable cause) {
		super(msg, cause);
		this.sql = "";
	}

	/** SQL that led to the problem */
	private final String sql;

	/**
	 * Constructor for PangoSqlException.
	 * 
	 * @param task
	 *            name of current task
	 * @param sql
	 *            the offending SQL statement
	 * @param ex
	 *            the root cause
	 */
	public DataBaseAccessException(String task, String sql, SQLException ex) {
		super(task + "SQLException for SQL [" + sql + "]; SQL state [" + ex.getSQLState() + "]; error code ["
				+ ex.getErrorCode() + "]; " + ex.getMessage(), ex);
		this.sql = sql;
	}

	/**
	 * Return the SQLException.
	 */
	public SQLException getSQLException() {
		return (SQLException) getCause();
	}

	/**
	 * Return the SQL that led to the problem.
	 */
	public String getSql() {
		return this.sql;
	}

	/**
	 * Return the detail message, including the message from the g3check
	 * exception if there is one.
	 */
	public String getMessage() {
		return super.getMessage(ExceptionDescriptor.Exception_SQL);
	}
}
