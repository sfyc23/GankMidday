/**
 * 
 */
package com.sfyc23.gankDaily.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间相关工具类
 * */
public class TimeUtils {
	private final static String TAG = "TimeUtils";

	public final static String[] WEEK_SYMBOLS = { " ", "" };

	public static final String TIME_HTTP_GMT = "EEE, dd MMM yyyy HH:mm:ss 'GMT'";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_UNDERL = "yyyy_MM_dd";
	public static final String YYYY_MM_DD_SLASH = "yyyy/MM/dd";
	public static final String YYYY_MM_DD_CN = "yyyy年MM月dd日";
	public static final String YY_MM_DD_SLASH = "yy/MM/dd";
	public static final String MM_DD_CN = "MM月dd日";
	public static final String MM_DD_SLASH = "MM/dd";
	public static final String HH_MM_SS = "HH:mm:ss";
	public static final String HH_MM = "HH:mm";
	public static final String MM_SS = "mm:ss";

	public static final String YYYYMMDD = "yyyyMMdd";



	private final static long MILLISECONDS_MINUTE = 60 * 1000;// 1分钟
	private final static long MILLISECONDS_HOUR = 60 * MILLISECONDS_MINUTE;// 1小时
	private final static long MILLISECONDS_DAY = 24 * MILLISECONDS_HOUR;// 1天
	private final static long MILLISECONDS_MONTH = 31 * MILLISECONDS_DAY;// 月
	private final static long MILLISECONDS_YEAR = 12 * MILLISECONDS_MONTH;// 年
	/**
	 * 返回文字描述的日期
	 * @param dates
	 * @return
	 */
	public static String getTimeFormatText(String dates) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date date = null ;
		try {
			date = sdf.parse(dates);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (date == null) {
			return null;
		}
		long diff = new Date().getTime() - date.getTime();
		long r = 0;
		if (diff > MILLISECONDS_YEAR) {
			r = (diff / MILLISECONDS_YEAR);
			return r + "年前";
		}
		if (diff > MILLISECONDS_MONTH) {
			r = (diff / MILLISECONDS_MONTH);
			return r + "个月前";
		}
		if (diff > MILLISECONDS_DAY) {
			r = (diff / MILLISECONDS_DAY);
			return r + "天前";
		}
		if (diff > MILLISECONDS_HOUR) {
			r = (diff / MILLISECONDS_HOUR);
			return r + "小时前";
		}
		if (diff > MILLISECONDS_MINUTE) {
			r = (diff / MILLISECONDS_MINUTE);
			return r + "分钟前";
		}
		return "刚刚";
	}

	/**
	 * 返回文字描述的日期
	 * @param dates
	 * @return
	 */
	public static String getTimeFormatText2(String dates) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		try {
			long millisecond = sdf.parse(dates).getTime();
			long difference = new Date().getTime() - millisecond;
			int minutes = (int) (difference / (1000 * 60));

			if (minutes < 5) {
				return "刚刚";
			} else if (minutes < 60) {
				return minutes + "分钟前";
			} else if (minutes < 60 * 24) {
				return minutes / 60 + "小时前";
			} else if (minutes < 60 * 24 * 30) {
				return minutes / (60 * 24) + "天前";
			}else if (minutes < 60 * 24 * 30 * 30) {
				return minutes / (60 * 24 * 30) + "月前";
			} else {
				return "老长时间了";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "...";
	}
	
}
