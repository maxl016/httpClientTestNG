/*
 * NestedBusinessException.java
 * Copyright (c) 2015 KINJO
 * All rights reserved
 */
package com.jason.framework.exception;

/**
 * 业务自定义异常基类
 * 
 * @author jinchengYang
 * @version 1.0 2015-06-11
 * @since 1.0
 */
public class NestedBusinessException extends NestedRuntimeException {

	private static final long serialVersionUID = 2724032065111817538L;

	String businessMes;

	public NestedBusinessException(String msg) {
		super(msg);
		businessMes = msg;
	}

	public NestedBusinessException(Throwable cause) {
		super(cause);
	}

	public NestedBusinessException(String msg, Throwable cause) {
		super(msg, cause);
		businessMes = msg;
	}

	/**
	 * Return the detail message, including the message from the business check
	 * exception if there is one.
	 */
	public String getMessage() {
		return super.getMessage(ExceptionDescriptor.Exception_BIZ);
	}

	public String getBusinessMessage() {
		return new StringBuffer("").append(businessMes).append("").toString();
	}
}
