package com.duan.quotes.common;

import java.sql.Date;
import java.util.Calendar;

public class TimeUtils {

	public static Date getBeforeDate(int distance) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -distance);
		return new Date(c.getTimeInMillis());
	}

	public static Date getToday() {
		return new Date(new java.util.Date().getTime());
	}

	public static Date getStartDayOfWeek() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, 1);
		return new Date(c.getTimeInMillis());
	}

	public static Date getStartDayOfMonth() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		return new Date(c.getTimeInMillis());
	}

	public static Date getStartDayOfYear() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR, 1);
		return new Date(c.getTimeInMillis());
	}

	public static void main(String[] args) {
		System.out.println(TimeUtils.getStartDayOfYear());
	}

}
