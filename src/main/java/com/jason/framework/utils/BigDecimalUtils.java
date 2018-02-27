/*
 * BigDecimalUtils.java
 * Copyright (c) 2015 KINJO
 * All rights reserved.
 */
package com.jason.framework.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;



/**
 * BigDecimal工具类
 * 
 * @author jinchengYang
 * @version 1.0 2015-06-11
 * @since 1.0
 * 
 */
public final class BigDecimalUtils {

	/** 10000 */
	public static final BigDecimal TEN_THOUSAND = new BigDecimal("10000");
	/** 100 */
	public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");
	/** 10 */
	public static final BigDecimal TEN = BigDecimal.TEN;
	/** 3 */
	public static final BigDecimal THREE = new BigDecimal("3");
	/** 2 */
	public static final BigDecimal TWO = new BigDecimal("2");
	/** 0.5,二分之一 */
	public static final BigDecimal HAFL = new BigDecimal("0.5");
	/** 0.75,四分之三 */
	public static final BigDecimal THREE_QUARTERS = new BigDecimal("0.75");
	/** 0.55 */
	public static final BigDecimal FIFTY_FIVE_PERCENT = new BigDecimal("0.55");
	/** 0.1 */
	public static final BigDecimal TENTH = new BigDecimal("0.1");
	/** 0.01 */
	public static final BigDecimal HUNDREDTH = new BigDecimal("0.01");

	public static final DecimalFormat INTEGER = new DecimalFormat("0");

	public static final DecimalFormat TWO_DECIMAL = new DecimalFormat("0.00");

	public static final DecimalFormat AUTO_TWO = new DecimalFormat("#.##");

	public static final DecimalFormat FOUR_DECIMAL = new DecimalFormat("0.0000");

	public static final DecimalFormat AUTO_FOUR = new DecimalFormat("#.####");
	
	public static final DecimalFormat BILLOIN = new DecimalFormat("0000000000");

	public static final DecimalFormat EIGHT_DECIMAL = new DecimalFormat("0.00000000");

	public static final DecimalFormat AUTO_EIGHT = new DecimalFormat("#.########");
	
	public static final DecimalFormat AUTO_COMMA = new DecimalFormat(",##0.00");//123,123.00

	// 精确到2位 (对外返回)
	public static final int PRECISION_ROUND_SCALE = 2;
	// 精确到8位(对内计算)
	public static final int PRECISION_SCALE = 8;

	/**
	 * 返回保留两位精确的数字<br>
	 * 第三位 <=5 舍<br>
	 * >5入<br>
	 * 
	 * @param rtnAmt
	 * @return BigDecimal
	 */
	public static BigDecimal getReturnRound(BigDecimal rtnAmt) {
		if (rtnAmt == null)
			return BigDecimal.ZERO;
//		rtnAmt = rtnAmt.setScale(PRECISION_ROUND_SCALE, BigDecimal.ROUND_HALF_DOWN);
		rtnAmt = rtnAmt.setScale(PRECISION_ROUND_SCALE, BigDecimal.ROUND_HALF_UP);
		return rtnAmt;
	}

	/**
	 * 格式化 BigDecimal为 xxx.xx格式字符串
	 * 
	 * @param amt
	 * @return
	 */
	public static String format(BigDecimal amt) {
		DecimalFormat df = new DecimalFormat("#0.00");
		return df.format(amt);
	}

	/**
	 * 四舍五入保留两位精度
	 * 
	 * @param rtnAmt
	 * @return BigDecimal
	 */
	public static BigDecimal getReturnRoundUp(BigDecimal rtnAmt) {
		if (rtnAmt == null)
			return BigDecimal.ZERO;
		rtnAmt = rtnAmt.setScale(PRECISION_ROUND_SCALE, BigDecimal.ROUND_HALF_UP);
		return rtnAmt;
	}

	/**
	 * 舍掉保留两位小数
	 * 
	 * @param value
	 * @return BigDecimal
	 */
	public static BigDecimal getReturnRoundDown(BigDecimal value) {
		if (value == null)
			return BigDecimal.ZERO;
		return value.setScale(PRECISION_ROUND_SCALE, BigDecimal.ROUND_DOWN);
	}

	/**
	 * 设置精度 为2位,并截取小数点后两位 <br>
	 * 正数 舍 负数 入
	 * 
	 * @return
	 */
	public static BigDecimal setPrecisionTwoFloor(BigDecimal value) {
		if (value == null)
			return BigDecimal.ZERO;
		return value.setScale(PRECISION_ROUND_SCALE, BigDecimal.ROUND_FLOOR);
	}

	/**
	 * 根据返回策略返回两位精度
	 * 
	 * @param rtnAmt
	 * @param RoundingMode
	 * @return BigDecimal
	 */
	public static BigDecimal getReturnRound(BigDecimal rtnAmt, int RoundingMode) {
		if (rtnAmt == null)
			return BigDecimal.ZERO;
		rtnAmt = rtnAmt.setScale(PRECISION_ROUND_SCALE, RoundingMode);
		return rtnAmt;
	}

	
	/**
	 * 格式化 BigDecimal为xxx,xxx.xx格式字符串，如123123.123格式化为123,123.12
	 * @param number
	 * @return
	 */
	public static String formatComma(BigDecimal number){
		return AUTO_COMMA.format(number);
	}
	
}
