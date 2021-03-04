package com.framework.commons.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public abstract class DateTimeUtils {
	private static final Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);

	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
	public static final String YYYYMMDD = "yyyy-MM-dd";

	private static final String[] WEEK = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	public static Date getDate() {
		return getCalendar().getTime();
	}

	public static long getTime() {
		return getDate().getTime();
	}

	public static Calendar toCalendar(Date date) {
		Calendar c = getCalendar();
		c.setTime(date);
		return c;
	}

	public static Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	public static Date getMonthFirst(Date date) {
		Calendar calendar = toCalendar(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date getMonthLast(Date date) {
		Calendar calendar = toCalendar(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return calendar.getTime();
	}

	public static boolean isTime(Calendar calendar) {
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		int millisecond = calendar.get(Calendar.MILLISECOND);
		return hour + minute + second + millisecond > 0;
	}

	public static boolean isTime(Date date) {
		return isTime(toCalendar(date));
	}

	public static boolean isTime(Timestamp timestamp) {
		return isTime(new Date(timestamp.getTime()));
	}

	public static String format(Date date) {
		if (DateTimeUtils.isTime(date)) {
			return DateFormatUtils.format(date, YYYYMMDDHHMMSS);
		} else {
			return DateFormatUtils.format(date, YYYYMMDD);
		}
	}

	public static String format(Timestamp timestamp) {
		return format(new Date(timestamp.getTime()));
	}

	public static Date parseDate(String s) {
		try {
			return DateUtils.parseDate(s, YYYYMMDDHHMMSS, YYYYMMDDHHMM, YYYYMMDD);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static Timestamp parseTimestamp(String s) {
		return toTimestamp(parseDate(s));
	}

	public static String getWeek(Date date) {
		int i = toCalendar(date).get(Calendar.DAY_OF_WEEK) - 1;
		return WEEK[i];
	}

}