/*
 * DateUtils.java
 * Copyright (c) 2015 KINJO
 * All rights reserved.
 */
package com.jason.framework.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


/**
 * 日期工具类
 * 
 * @author jinchengYang
 * @version 1.0 2015-06-11
 * @since 1.0
 * 
 */
public class DateUtils {

	private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private static final SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

	public static final SimpleDateFormat dfs = new SimpleDateFormat("yyyy/MM/dd");

	public static final SimpleDateFormat dfsFull = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	private static final SimpleDateFormat cf = new SimpleDateFormat("yyyyMMddHHmm");

	private static final SimpleDateFormat cfs = new SimpleDateFormat("yyyyMMddHHmmss");

	private static final SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	final static long daytimes = 24 * 60 * 60 * 1000;

	final static long hourtimes = 1 * 60 * 60 * 1000;

	private static Calendar c = new GregorianCalendar();

	public static Date parseAuto(String source) {
		if (StringUtils.isBlank(source))
			return null;
		try {
			if (source.indexOf('-') > 0) {// 以"-"分隔
				if (source.indexOf(':') > 0) {
					return datetimeFormat.parse(source);
				} else {
					return dateFormat.parse(source);
				}
			}
			if (source.indexOf('/') > 0) {// 以"/"分隔
				if (source.indexOf(':') > 0) {
					return dfsFull.parse(source);
				} else {
					return dfs.parse(source);
				}
			}
			try {
				return cfs.parse(source);
			} catch (Exception e) {
				return df.parse(source);
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获得当前时间
	 * <p>
	 * 时间格式HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentTime() {
		return timeFormat.format(today());
	}

	/**
	 * 获得当前日期时间
	 * <p>
	 * 日期时间格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentDatetime() {
		return datetimeFormat.format(today());
	}

	/**
	 * 获得当前日期
	 * <p>
	 * 日期格式yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String currentDate() {
		return dateFormat.format(today());
	}

	/**
	 * 获得当前时间的<code>java.util.Date</code>对象
	 * 
	 * @return Date
	 */
	public static Date today() {
		return new Date();
	}

	/**
	 * 获取格式化之后的当前日期 日期格式yyyy-MM-dd
	 */
	public static Date todayFormat() {
		try {
			return parseDate(currentDate());
		} catch (ParseException e) {
			return new Date();
		}
	}

	public static Date yesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	public static String yesterdayStr() {
		return dateFormat.format(yesterday());
	}

	/**
	 * 格式化时间
	 * <p>
	 * 时间格式HH:mm:ss
	 * 
	 * @return
	 */
	public static String formatTime(Date date) {
		return timeFormat.format(date);
	}

	/**
	 * 格式化日期
	 * <p>
	 * 日期格式yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}

	/**
	 * 格式化日期
	 * <p>
	 * 日期格式yyyyMMdd
	 * 
	 * @return
	 */
	public static String df(Date date) {
		return df.format(date);
	}

	/**
	 * 格式化日期
	 * <p>
	 * 日期格式yyyyMMddHHmm
	 * 
	 * @return
	 */
	public static String cf(Date date) {
		return cf.format(date);
	}

	/**
	 * 格式化日期
	 * <p>
	 * 日期格式yyyyMMDDhhmmss
	 * 
	 * @return
	 */
	public static String cfs(Date date) {
		return cfs.format(date);
	}

	/**
	 * 格式化日期
	 * <p>
	 * 日期格式yyyyMMdd HH:mm
	 * 
	 * @return
	 */
	public static String dt(Date date) {
		return dt.format(date);
	}

	/**
	 * 格式化日期时间
	 * <p>
	 * 日期时间格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String formatDatetime(Date date) {
		return datetimeFormat.format(date);
	}

	/**
	 * 格式化日期时间
	 * 
	 * @param date
	 * @param pattern
	 *            格式化模式，详见{@link SimpleDateFormat}构造器
	 *            <code>SimpleDateFormat(String pattern)</code>
	 * @return
	 */
	public static String formatDatetime(Date date, String pattern) {
		SimpleDateFormat customFormat = (SimpleDateFormat) datetimeFormat.clone();
		customFormat.applyPattern(pattern);
		return customFormat.format(date);
	}

	public static Calendar calendar() {
		Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		return cal;
	}

	/**
	 * 获得当前时间的毫秒数
	 * <p>
	 * 详见{@link System#currentTimeMillis()}
	 * 
	 * @return
	 */
	public static long millis() {
		return System.currentTimeMillis();
	}

	public static int year(Date date) {
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	public static int month(Date date) {
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获得月份中的第几天
	 * 
	 * @return
	 */
	public static int dayOfMonth() {
		return calendar().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 今天是星期的第几天
	 * 
	 * @return
	 */
	public static int dayOfWeek() {
		return calendar().get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 今天是年中的第几天
	 * 
	 * @return
	 */
	public static int dayOfYear() {
		return calendar().get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 判断原日期是否在目标日期之前
	 * 
	 * @param src
	 * @param dst
	 * @return
	 */
	public static boolean isBefore(Date src, Date dst) {
		return src.before(dst);
	}

	/**
	 * 判断原日期是否在目标日期之后
	 * 
	 * @param src
	 * @param dst
	 * @return
	 */
	public static boolean isAfter(Date src, Date dst) {
		return src.after(dst);
	}

	/**
	 * 判断两日期是否相同
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isEqual(Date date1, Date date2) {
		return date1.compareTo(date2) == 0;
	}

	/**
	 * 判断某个日期是否在某个日期范围
	 * 
	 * @param beginDate
	 *            日期范围开始
	 * @param endDate
	 *            日期范围结束
	 * @param src
	 *            需要判断的日期
	 * @return
	 */
	public static boolean between(Date beginDate, Date endDate, Date src) {
		return beginDate.getTime() == src.getTime() || endDate.getTime() == src.getTime()
				|| (beginDate.before(src) && endDate.after(src));
	}

	/**
	 * 获取指定日期的月份
	 * 
	 * @param date
	 * @return
	 */
	public static int MonthOfDate(Date date) {
		Calendar c = Calendar.getInstance(); // 获得一个日历的实例
		if (date == null) {
			date = new Date(); // 为空时当前时间计算
		}
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取指定日期的日期
	 * 
	 * @param date
	 * @return
	 */
	public static int DayOfDate(Date date) {
		Calendar c = Calendar.getInstance(); // 获得一个日历的实例
		if (date == null) {
			date = new Date(); // 为空时当前时间计算
		}
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取指定日期当月最后一天 date为null时为当前月
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		if (date == null) {
			date = new Date();
		}
		c.setTime(date);
		c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
		return c.getTime();
	}

	public static boolean isLastDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		if (date == null) {
			date = new Date();
		}
		c.setTime(date);
		c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
		return c.getTime().compareTo(date) == 0;
	}

	/**
	 * 获取指定日期当月第一天 date为null时为当前月
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		if (date == null) {
			date = new Date();
		}
		c.setTime(date);
		c.set(Calendar.DATE, c.getActualMinimum(Calendar.DATE));
		return c.getTime();
	}

	public static Date weekDay(int week) {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_WEEK, week);
		return cal.getTime();
	}

	/**
	 * 获得周五日期
	 * <p>
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * 
	 * @return
	 */
	public static Date friday() {
		return weekDay(Calendar.FRIDAY);
	}

	/**
	 * 获得周六日期
	 * <p>
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * 
	 * @return
	 */
	public static Date saturday() {
		return weekDay(Calendar.SATURDAY);
	}

	/**
	 * 获得周日日期
	 * <p>
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * 
	 * @return
	 */
	public static Date sunday() {
		return weekDay(Calendar.SUNDAY);
	}

	/**
	 * 将字符串日期时间转换成java.util.Date类型
	 * <p>
	 * 日期时间格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param datetime
	 * @return
	 */
	public static Date parseDatetime(String datetime) throws ParseException {
		return datetimeFormat.parse(datetime);
	}

	/**
	 * 将字符串日期转换成java.util.Date类型
	 * <p>
	 * 日期时间格式yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String date) throws ParseException {
		return dateFormat.parse(date);
	}

	/**
	 * 将字符串日期转换成java.util.Date类型
	 * <p>
	 * 时间格式 HH:mm:ss
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date parseTime(String time) throws ParseException {
		return timeFormat.parse(time);
	}

	/**
	 * 根据自定义pattern将字符串日期转换成java.util.Date类型
	 * 
	 * @param datetime
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDatetime(String datetime, String pattern) throws ParseException {
		SimpleDateFormat format = (SimpleDateFormat) datetimeFormat.clone();
		format.applyPattern(pattern);
		return format.parse(datetime);
	}

	/**
	 * 时间转换（date转string）
	 * 
	 * @param date
	 *            需要转换的date
	 * @param format
	 *            格式化类型 eg.yyyy-MM-dd
	 */
	public static String dateToString(Date date, String format) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
		if (date != null)
			return simpledateformat.format(date);
		else
			return "";
	}

	/**
	 * 时间转换（string转date）
	 * 
	 * @param str
	 *            需要转换的data，string格式
	 * @param format
	 *            格式化类型 eg.yyyy-MM-dd
	 */
	public static Date stringToDate(String str, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setLenient(Boolean.FALSE);
		Date date = sdf.parse(str);
		return date;
	}

	/**
	 * 获得当前日期的第几周
	 * 
	 * @param date
	 *            需要转换的date
	 */
	public static int getWeekOfDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd|w");
		String fmt = sdf.format(date);
		return new Integer(StringUtils.substringAfter(fmt, "|"));
	}

	/**
	 * 以自定义方式验证是否为合法日期格式
	 * 
	 * @param str
	 *            需要转换的data，string格式
	 * @param format
	 *            格式化类型 eg.yyyy-MM-dd
	 * @return
	 */
	public static boolean isValidDate(String str, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setLenient(Boolean.FALSE);
		try {
			sdf.parse(str);
			return Boolean.TRUE;
		} catch (ParseException e) {
			return Boolean.FALSE;
		}
	}

	/**
	 * 按天数计算时间
	 * 
	 * @param date
	 *            传入时间
	 * @param day
	 *            需要计算的天数
	 * @return 计算后的时间
	 */
	public static Date getCalcDay(Date date, int day) {
		Calendar c = Calendar.getInstance(); // 获得一个日历的实例
		if (date == null) {
			date = new Date(); // 为空时当前时间计算
		}
		c.setTime(date);
		c.add(Calendar.DATE, day);
		return c.getTime();
	}

	/**
	 * 在日历的天数上减少day天
	 */
	public static Date getBeforeDay(int day) throws ParseException {
		Calendar c = Calendar.getInstance();// 获得一个日历的实例
		Date date = dateFormat.parse(currentDate());// 初始日期
		c.setTime(date);// 设置日历时间
		c.add(Calendar.DATE, -day); // 在日历的天数上减少day天
		return c.getTime();
	}

	/**
	 * 在日历的月份上增加month个月
	 */
	public static Date getBeforeMonth(int month) throws ParseException {
		Calendar c = Calendar.getInstance();// 获得一个日历的实例
		Date date = dateFormat.parse(currentDate());// 初始日期
		c.setTime(date);// 设置日历时间
		c.add(Calendar.MONTH, month); // 在日历的月份上增加month个月
		return c.getTime();
	}

	/**
	 * 在指定时间上增加月个数
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMonths(Date date, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, amount);
		return c.getTime();
	}

	/**
	 * 时间转日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date timeToDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	public static Date addDay(int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(today());
		c.add(Calendar.DAY_OF_MONTH, amount);
		return c.getTime();
	}

	public static Date addHour(int hour) {
		Calendar c = Calendar.getInstance();
		c.setTime(today());
		c.add(Calendar.HOUR_OF_DAY, hour);
		return c.getTime();
	}

	/**
	 * 返回指定小时的时间
	 * 
	 * @param hour
	 * @return
	 */
	public static Date getDateByHour(int hour) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * 在指定时间上增加小时数
	 * 
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date addHour(Date date, int hour) {
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, hour);
		return c.getTime();
	}

	/**
	 * 在指定时间上增加天数
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addDay(Date date, int amount) {
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, amount);
		return c.getTime();
	}

	public static Date addDay(Date date, int calendarField, int amount) {
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	public static Timestamp addTimestamp(Timestamp date, int calendarField, int amount) {
		c.setTime(date);
		c.add(calendarField, amount);
		return new Timestamp(c.getTimeInMillis());
	}

	public static Timestamp addDay(Timestamp date, int amount) {
		return addTimestamp(date, Calendar.DAY_OF_MONTH, amount);
	}

	public static Timestamp dateTimeTodate(Timestamp src) {
		c.setTimeInMillis(src.getTime());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return new Timestamp(c.getTimeInMillis());

	}

	/**
	 * 在指定时间上增加年份
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addYear(Date date, int amount) {
		c.setTime(date);
		c.add(Calendar.YEAR, amount);
		return c.getTime();
	}

	/**
	 * 计算两个时间差精确到（xxx天xx小时xx分xx秒）
	 * 
	 * @param date1表示终止时间
	 *            , data2
	 * @return
	 * @throws ParseException
	 * 
	 */
	public static String getDateSubtract(Date date1, Date date2) throws ParseException {
		long d1 = date1.getTime();
		long d2 = date2.getTime();
		Date now = datetimeFormat.parse(formatDatetime(date1));
		Date date = datetimeFormat.parse(formatDatetime(date2));
		long l = 0l;
		if (d1 > d2) {
			l = now.getTime() - date.getTime();

		} else {
			l = date.getTime() - now.getTime();
		}
		long day = l / (daytimes);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		String dayday = "" + day + "天" + hour + "小时" + min + "分" + s + "秒";
		return dayday;
	}

	/**
	 * 计算两个时间相差天数
	 * 
	 * @param date1
	 * @param date2
	 * @return long
	 */
	public static long getDateSubDays(Date date1, Date date2) {
		long d1 = date1.getTime();
		long d2 = date2.getTime();
		long diff = 0l;
		if (d1 == d2) {
			return diff;
		} else if (d1 > d2) {
			diff = d1 - d2;
		} else {
			diff = d2 - d1;
		}
		return diff / (daytimes);
	}

	/**
	 * 计算两个时间相差月数
	 * 
	 * @param date1
	 * @param date2
	 * @return long
	 */
	public static int getDateSubMonths(Date date1, Date date2) {
		int result = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		if (date2.after(date1)) {
			c1.setTime(date1);
			c2.setTime(date2);
		} else {
			c1.setTime(date2);
			c2.setTime(date1);
		}
		result = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12
				+ (c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH));
		return result == 0 ? 1 : Math.abs(result);
	}

	/**
	 * 计算两个时间相差年数
	 * 
	 * @param date1
	 * @param date2
	 * @return long
	 */
	public static int getDateSubYear(Date date1, Date date2) {
		int result = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		if (date2.after(date1)) {
			c1.setTime(date1);
			c2.setTime(date2);
		} else {
			c1.setTime(date2);
			c2.setTime(date1);
		}
		result = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR));
		return result == 0 ? 1 : Math.abs(result);
	}

	/**
	 * 按指定格式获得日期
	 * 
	 * @param sourceDate
	 * @param format
	 * @return
	 */
	public static Date getDateByFormat(Date sourceDate, String format) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat(format);
		String dateString = f.format(sourceDate);
		Date date = f.parse(dateString);
		return date;
	}

	public static Date getDateByFormat(Date sourceDate) throws ParseException {
		String dateString = dateFormat.format(sourceDate);
		Date date = dateFormat.parse(dateString);
		return date;
	}

	/**
	 * 按指定格式获得日期
	 * 
	 * @param sourceDateStr
	 * @param format
	 * @return
	 */
	public static Date getDateByFormat(String sourceDateStr, String format) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat(format);
		Date date = f.parse(sourceDateStr);
		return date;
	}

	/**
	 * 判断是否超时
	 * 
	 * @param startDate
	 *            开始时间
	 * @param intervalTime
	 *            间隔时间，以毫秒为单位
	 * @return
	 * @throws Exception
	 */
	public static boolean isTimeOut(Date startDate, long intervalTime) {
		boolean ret = false;
		Date currentDate = new Date();
		long minuteDif = currentDate.getTime() - startDate.getTime();
		if (minuteDif >= intervalTime) {
			ret = true;
		}
		return ret;
	}

	/**
	 * 判断是否是在还款时间内还款 还款时间是指一天内可进行还款操作的时间 现规定可还款时间段为04:00:00--23:30:00
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean isRepaymentTime() throws Exception {
		boolean ret = false;
		Date currentDate = new Date();
		String startDateStr = dateFormat.format(currentDate).concat(" 04:00:00");
		String ednDateStr = dateFormat.format(currentDate).concat(" 23:30:00");
		Date startDate = datetimeFormat.parse(startDateStr);
		Date endDate = datetimeFormat.parse(ednDateStr);
		if (currentDate.getTime() > startDate.getTime() && currentDate.getTime() < endDate.getTime()) {
			ret = true;
		}
		return ret;
	}

	/***
	 * 判断该字符串是否能转换成日期类型(用户查询条件的check)
	 * 
	 * @param sourceStr
	 * @return
	 */
	public static boolean isIllegalDate(String sourceStr) {
		boolean ret = false;
		if (!(StringUtils.isBlank(sourceStr)) && !(StringUtils.isEmpty(sourceStr))) {
			try {
				Date date = dateFormat.parse(sourceStr);
				if (date != null)
					return Boolean.TRUE;
			} catch (ParseException e) {
				return Boolean.FALSE;
			}
		}
		return ret;
	}

	/**
	 * 获得指定日期的所在星期的星期一
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getMondayByDate(Date date) {
		Calendar c = calendar();
		c.setTime(date);
		c.add(Calendar.DATE, -(DateUtils.getNumByDate(date) - 1));
		return c.getTime();
	}

	/**
	 * 获得指定日期的所在星期的星期日
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getSundayByDate(Date date) {
		Calendar c = calendar();
		c.setTime(date);
		c.add(Calendar.DATE, 7 - DateUtils.getNumByDate(date));
		return c.getTime();
	}

	/**
	 * 获得当前日期为这个星期的第几天 如：1 ： 星期一 、 7 ： 星期日
	 * 
	 * @param date
	 * @return int
	 */
	public static int getNumByDate(Date date) {
		Calendar c = calendar();
		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_WEEK) - 1;
		return i != 0 ? i : 7;
	}

	/**
	 * 获取日期距离今天的天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDaysBetweenToday(Date date) {
		Calendar calendar = calendar();
		calendar.setTime(date);
		long betweenDays = (calendar.getTimeInMillis() - calendar().getTimeInMillis()) / (24 * 3600 * 1000);
		return Integer.parseInt(String.valueOf(betweenDays));
	}

	/**
	 * 判断两个时间是否是同一天
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isTheSameDay(Date date1, Date date2) {
		Calendar c1 = calendar();
		c1.setTime(date1);
		Calendar c2 = calendar();
		c2.setTime(date2);
		return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
				&& (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 判断日期是否在这之前，只比较日期
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isBeforeToday(Date date) throws ParseException {
		if (date == null) {
			return Boolean.FALSE;
		}
		Date today = parseDate(currentDate());
		if (today.getTime() > date.getTime()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * 获取当月最大天数
	 * jg
	 * @return
	 */
	public static int getDayOfMonth(){
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int day=aCalendar.getActualMaximum(Calendar.DATE);
		return day;
	}
	
}