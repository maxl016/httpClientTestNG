package com.jason.framework.database;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DealDate {
	public static final Date parseDateFormat(String dateStr, String formatStr) {
		try {
			if (dateStr.equals("")) {
				return null;
			}
			SimpleDateFormat format = new SimpleDateFormat(formatStr);
			return format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static final Date parseDate(String dateStr) {

		return parseDateFormat(dateStr, "yyyy-MM-dd");
	}

	public static final Date parseTime(String timeStr) {

		return parseDateFormat(timeStr, "yyyy-MM-dd HH:mm:ss");
	}

	public static final Date parseTime2(String timeStr) {

		return parseDateFormat(timeStr, "yyyy-MM-dd HH:mm:ss.SSS");
	}

	public static final String parseDateStr(Date date, String style) {

		DateFormat dateFormat = new SimpleDateFormat(style, Locale.CHINESE);
		dateFormat.setLenient(false);
		return dateFormat.format(date);
	}

	public static final String getNowDateStr() {

		String str = "";
		Date dt = getNowDate();
		str = parseDateStr(dt, "yyyy-MM-dd");
		return str;
	}

	public static final String getNowTimeStr() {

		String str = "";
		Date dt = getNowDate();
		str = parseDateStr(dt, "yyyy-MM-dd HH:mm:ss");
		return str;
	}

	public static final String getNowTimeStr2() {

		String str = "";
		Date dt = getNowDate();
		str = parseDateStr(dt, "yyyy-MM-dd HH:mm:ss.SSS");
		return str;
	}

	public static final String getNowTimeNo() {

		String str = "";
		Date dt = getNowDate();
		str = parseDateStr(dt, "yyyyMMddHHmmssSSS");
		return str;
	}

	public static final long getNowTimeLong() {
		Date dt = getNowDate();
		long num = dt.getTime();
		return num;
	}

	public static final Calendar getNowTime() {
		Calendar c = Calendar.getInstance();
		return c;
	}

	public static final Date getNowDate() {
		return getNowTime().getTime();
	}

	public static final long getNowTimeMillis() {
		return getNowTime().getTimeInMillis();
	}

	public static final int getNowYear() {
		return getNowTime().get(1);
	}

	public static final int getNowMonth() {
		return getNowTime().get(2) + 1;
	}

	public static final int getNowMonthDay() {
		return getNowTime().get(5);
	}

	public static final int getNowWeek() {
		return getNowTime().get(4);
	}

	public static final int getNowWeekDay() {
		return getNowTime().get(7) - 1;
	}

	public static final String getNowWeekDayStr() {
		String[] week = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		return week[getNowWeekDay()];
	}

	public static final int getNowHour() {

		return getNowTime().get(10);
	}

	public static final int getNowMinutes() {

		return getNowTime().get(12);
	}

	public static final int getNowSecond() {

		return getNowTime().get(13);
	}

	public static final String getDateDiffStr(int diff) {

		Calendar c = getNowTime();
		c.add(5, diff);
		return parseDateStr(c.getTime(), "yyyy-MM-dd");
	}

	public static final String getTimeDiffStr(int second) {

		Calendar c = getNowTime();
		c.add(13, second);
		return parseDateStr(c.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	public static final Date getTimeDiff(Date d, int type, int num) {

		Calendar c = getNowTime();
		c.setTime(d);
		c.add(type, num);
		return c.getTime();
	}

	public static final String getDateDiffStr(Date d, int day) {

		Date date = getTimeDiff(d, 5, day);
		return parseDateStr(date, "yyyy-MM-dd");
	}

	public static final String getTimeDiffStr(Date d, int second) {

		Date date = getTimeDiff(d, 13, second);
		return parseDateStr(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static final String getNowWeekStartStr() {

		Calendar c = getNowTime();
		c.add(5, 0 - getNowWeekDay());
		return parseDateStr(c.getTime(), "yyyy-MM-dd");
	}

	public static final String getNowMonthStartStr() {

		Calendar c = getNowTime();
		c.add(5, 0 - getNowMonthDay() + 1);
		return parseDateStr(c.getTime(), "yyyy-MM-dd");
	}

	public static final String getNowMonthEndStr() {

		Calendar c = getNowTime();
		c.add(5, 0 - getNowMonthDay() + 1);
		c.add(2, 1);
		c.add(5, -1);
		return parseDateStr(c.getTime(), "yyyy-MM-dd");
	}

	public static final String getLastMonthStartStr() {

		Calendar c = getNowTime();
		c.add(5, 0 - getNowMonthDay() + 1);
		c.add(2, -1);
		return parseDateStr(c.getTime(), "yyyy-MM-dd");
	}

	public static final String getLastMonthEndStr() {

		Calendar c = getNowTime();
		c.add(5, 0 - getNowMonthDay());
		return parseDateStr(c.getTime(), "yyyy-MM-dd");
	}

	public static final String getDateStr(Date d) {

		String str = "";
		if (d != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			str = sdf.format(d);
		}
		return str;
	}

	public static final String getTimeStr(Date d) {

		String str = "";
		if (d != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			str = sdf.format(d);
		}
		return str;
	}

	public static final String getTimeStr2(Date d) {

		String str = "";
		if (d != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss.SSS");
			str = sdf.format(d);
		}
		return str;
	}

	public static final String getTimeNo(Date d) {

		String str = "";
		str = parseDateStr(d, "yyyyMMddHHmmssSSS");
		return str;
	}

	public static final long getTimeLong(Date d) {
		long num = d.getTime();
		return num;
	}

	public static final String getTimePartStr(Date d) {

		String str = "";
		if (d != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			str = sdf.format(d);
		}
		return str;
	}

	public static final double yearCompare(String dstr1, String dstr2) {
		Date d1 = parseDate(dstr1);
		Date d2 = parseDate(dstr2);
		return yearCompare(d1, d2);
	}

	public static final double yearCompare(Date d1, Date d2) {
		if (((d1 == null ? 1 : 0) | (d2 == null ? 1 : 0)) != 0) {
			return 0.0D;
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		double years = c2.get(1) - c1.get(1);
		return years;
	}

	public static final double monthCompare(String dstr1, String dstr2) {
		Date d1 = parseDate(dstr1);
		Date d2 = parseDate(dstr2);
		return monthCompare(d1, d2);
	}

	public static final double monthCompare(Date d1, Date d2) {
		if (((d1 == null ? 1 : 0) | (d2 == null ? 1 : 0)) != 0) {
			return 0.0D;
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		double months = c2.get(1) * 12 + c2.get(2)
				- (c1.get(1) * 12 + c1.get(2));
		double days = c2.get(5) - c1.get(5);
		if (days < 0.0D) {
			months -= 1.0D;
		}
		return months;
	}

	public static final double dayCompare(String dstr1, String dstr2) {
		Date d1 = parseDate(dstr1);
		Date d2 = parseDate(dstr2);
		return dayCompare(d1, d2);
	}

	public static final double dayCompare(Date d1, Date d2) {
		if (((d1 == null ? 1 : 0) | (d2 == null ? 1 : 0)) != 0) {
			return 0.0D;
		}
		double diff = d2.getTime() - d1.getTime();
		double days = diff / 86400000.0D;
		return days;
	}

	public static final double hourCompare(String dstr1, String dstr2) {
		Date d1 = parseTime(dstr1);
		Date d2 = parseTime(dstr2);
		return hourCompare(d1, d2);
	}

	public static final double hourCompare(Date d1, Date d2) {
		if (((d1 == null ? 1 : 0) | (d2 == null ? 1 : 0)) != 0) {
			return 0.0D;
		}
		double diff = d2.getTime() - d1.getTime();
		double hours = diff / 3600000.0D;
		return hours;
	}

	public static final double minuteCompare(String dstr1, String dstr2) {
		Date d1 = parseTime(dstr1);
		Date d2 = parseTime(dstr2);
		return minuteCompare(d1, d2);
	}

	public static final double minuteCompare(Date d1, Date d2) {
		if (((d1 == null ? 1 : 0) | (d2 == null ? 1 : 0)) != 0) {
			return 0.0D;
		}
		double diff = d2.getTime() - d1.getTime();
		double minutes = diff / 60000.0D;
		return minutes;
	}

	public static final double secondCompare(String dstr1, String dstr2) {
		Date d1 = parseTime(dstr1);
		Date d2 = parseTime(dstr2);
		return secondCompare(d1, d2);
	}

	public static final double secondCompare(Date d1, Date d2) {
		if (((d1 == null ? 1 : 0) | (d2 == null ? 1 : 0)) != 0) {
			return 0.0D;
		}
		double diff = d2.getTime() - d1.getTime();
		double seconds = diff / 1000.0D;
		return seconds;
	}

	public static final String getBirthday(Date d) {

		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		str = sdf.format(d);
		return str;
	}

	public static final String getBirthdayDiff(Date d, int diff) {

		String str = getBirthday(getTimeDiff(d, 5, diff));
		return str;
	}

	public static final int getWeekDay(Date d) {

		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c.get(7) - 1;
	}

	public static final String getWeekDayStr(Date d) {

		String[] week = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		return week[getWeekDay(d)];
	}

}
