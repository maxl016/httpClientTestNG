/*
 * NestedExceptionUtil.java
 * Copyright (c) 2015 KINJO
 * All rights reserved.
 */
package com.jason.framework.exception;

/**
 * <p>
 * 异常基础类负责拼装消息
 * </p>
 * 
 * @author jinchengYang
 * @version 1.0 2015-06-11
 * @since 1.0
 */
public abstract class NestedExceptionUtil {

	public static String buildMessage(String message, Throwable cause) {
		if (cause != null) {
			StringBuffer buf = new StringBuffer();
			if (message != null) {
				buf.append(message).append(";");
			}
			buf.append("Exception is:").append(cause);
			return buf.toString();
		} else {
			return message;
		}
	}

	public static String buildMessage(String message, int type, Throwable cause) {
		if (cause != null) {
			StringBuffer buf = new StringBuffer();
			if (message != null) {
				buf.append(message).append(",");
			}
			try {
				switch (type) {
				case ExceptionDescriptor.Exception_DEF:
					buf.append("P2PNestedException.");
				case ExceptionDescriptor.Exception_SQL:
					buf.append("P2PSQLException,SQL is: " + ((DataBaseAccessException) cause).getSql());
				case ExceptionDescriptor.Exception_CCE:
					buf.append("P2PClassCastException.");
				case ExceptionDescriptor.Exception_IOB:
					buf.append("P2PIndexOutOfBandsException.");
				case ExceptionDescriptor.Exception_NCF:
					buf.append("P2PNoClassDefFoundException.");
				case ExceptionDescriptor.Exception_SEC:
					buf.append("P2PSeccurityException.");
				case ExceptionDescriptor.Exception_NPE:
					buf.append("P2PNullPointerException.");
				case ExceptionDescriptor.Exception_MOG:// MongoDB数据库异常
					buf.append("P2PNestedMongoDbException.");
				case ExceptionDescriptor.Exception_BIZ:// 业务流程自定义异常
					buf.append("P2PNestedBusinessException,business info is: "
							+ ((NestedBusinessException) cause).getBusinessMessage());
				default:// 业务自定义异常
					buf.append("P2PNestedException");
				}
			} catch (Exception e) {
				buf.append("P2PNestedRuntimeException.");
			}
			// buf.append(" caused by :").append(cause);
			buf.append(" caused by :").append(cause.getMessage());
			return buf.toString();
		} else {
			return message;
		}
	}
}
