/*
 * NestedException.java
 * Copyright (c) 2015 KINJO
 * All rights reserved.
 */
package com.jason.framework.exception;

/**
 * <p>
 * 自定义异常基类
 * </p>
 * 
 * @author jinchengYang
 * @version 1.0 2015-06-11
 * @since 1.0
 * @see com.phoenix.exception.NestedRuntimeException
 */
public class NestedException extends NestedRuntimeException {

	private static final long serialVersionUID = -4775190692869227607L;

	public NestedException(String msg) {
		super(msg);
	}

	public NestedException(Throwable cause) {
		super(cause);
	}

	public NestedException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * Return the detail message, including the message from the phoenix
	 * check exception if there is one.
	 */
	public String getMessage() {
		return super.getMessage(ExceptionDescriptor.Exception_DEF);
	}
}
