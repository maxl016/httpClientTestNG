/*
 * ExceptionDescriptor.java
 * Copyright (c) 2015 KINJO
 * All rights reserved.
 */
package com.jason.framework.exception;

/**
 * <p>
 * 异常描叙
 * </p>
 * 
 * @author jinchengYang
 * @version 1.0 2015-06-11
 * @since 1.0
 * 
 */
public final class ExceptionDescriptor {

	// default Exception
	public static final int Exception_DEF = -1;
	// SQLException
	public static final int Exception_SQL = 0;

	// IndexOutOfBandsException
	public static final int Exception_IOB = 1;

	// ClassCastException
	public static final int Exception_CCE = 2;

	// NoClassDefFoundException
	public static final int Exception_NCF = 3;

	// SeccurityException
	public static final int Exception_SEC = 4;

	// NullPointerException
	public static final int Exception_NPE = 5;

	// mongodb Exception
	public static final int Exception_MOG = 6;

	// business Exception
	public static final int Exception_BIZ = 7;

}
