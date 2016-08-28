package com.mfbl.mofang.util;

import android.util.Log;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 把时间戳转换成 时间格式
 * 
 * @author Devis
 * 
 */
public class MyTimeUtils {

	public static String getTimeFromDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		return str;
	}

	public static int getYearFromDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		return Integer.parseInt(str.substring(0, 4));
	}

	/**
	 * @author Devis
	 * @date 2014-12-11-下午2:30:20
	 * @des 距离现在的当时的时间
	 */
	public static String getTime(int timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = null;
		try {
			Date currentdate = new Date();// 当前时间

			long i = (currentdate.getTime() / 1000 - timestamp) / (60);
			Timestamp now = new Timestamp(System.currentTimeMillis());

			String str = sdf.format(new Timestamp(IntToLong(timestamp)));
			time = str.substring(11, 16);
			String month = str.substring(5, 7);
			String day = str.substring(8, 10);
			time = getDate(month, day) + time;
			Log.d("TTT", "getTime = " + str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	public static String getTime(long timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = null;
		try {
			Date currentdate = new Date();// 当前时间

			long i = (currentdate.getTime() / 1000 - timestamp) / (60);
			Timestamp now = new Timestamp(System.currentTimeMillis());

			String str = sdf.format(new Timestamp(timestamp));
			time = str.substring(11, 16);
			String month = str.substring(5, 7);
			String day = str.substring(8, 10);
			time = getDate(month, day) + time;
			Log.d("TTT", "getTime = " + str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * @author Devis
	 * @date 2014-12-11-下午2:31:10
	 * @des
	 */
	public static String getDate(long timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String time = null;
		try {
			time = sdf.format(new Timestamp(timestamp));
			Log.d("TTT", "getYearTime = " + time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**********
	 * @author: Devis 2015-3-26 上午11:56:07
	 * @desc : 获取月日
	 **********/
	public static String getDateTime(String timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
		String time = null;
		try {
			time = sdf.format(new Timestamp(Long.parseLong(timestamp)));
			if (time.length() > 6) {
				time = time.substring(5, time.length());
			}
			Log.d("TTT", "getDateTime = " + time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * @author Devis
	 * @date 2014-12-11-下午2:15:58
	 * @des 获取年份
	 */
	public static String getYear(int timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String year = null;
		try {
			String time = sdf.format(new Timestamp(IntToLong(timestamp)));
			Log.d("TTT", "getYear = " + time);
			year = time.substring(0, 4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return year;
	}

	public static String getDate(String month, String day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
		Date d = new Date();
		;
		String str = sdf.format(d);
		String nowmonth = str.substring(5, 7);
		String nowday = str.substring(8, 10);
		String result = null;

		int temp = Integer.parseInt(nowday) - Integer.parseInt(day);
		switch (temp) {
		case -1:
			result = "明天 ";
			break;
		case 0:
			result = "今天 ";
			break;
		case 1:
			result = "昨天 ";
			break;
		case 2:
			result = "前天 ";
			break;
		default:
			StringBuilder sb = new StringBuilder();
			sb.append(Integer.parseInt(month) + "月");
			sb.append(Integer.parseInt(day) + "日 ");
			result = sb.toString();
			break;
		}
		return result;
	}

	public static Date getDateFromString(String dateString) {
		ParsePosition position = new ParsePosition(0);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateValue = simpleDateFormat.parse(dateString, position);
		return dateValue;
	}


	// java Timestamp构造函数需传入Long型
	public static long IntToLong(int i) {
		long result = (long) i;
		result *= 1000;
		return result;
	}
}
