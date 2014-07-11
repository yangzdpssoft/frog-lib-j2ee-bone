package com.cyou.fz.common.base.util;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;



/**
 * 日期工具类.
 * 
 * @author yangz
 * @date 2013-2-8 下午5:16:48
 */
public class DateUtil {
	/**
	 * 日期格式 '-'
	 */
	public static final String DATE = "yyyy-MM-dd";
	/**
	 * 时间格式 '-'
	 */
	public static final String TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 默认根据系统的时间格式转日期.
	 * @param date
	 * @return
	 * @author yangz
	 * @date 2013-6-4 上午10:02:46
	 */
	public static Date parseDate(String date){
		try {
			return DateTime.parse(date, DateTimeFormat.forPattern(TIME)).toDate();
		} catch (Exception e) {
			return DateTime.parse(date, DateTimeFormat.forPattern(DATE)).toDate();
		}
	}
	/**
	 * 根据格式将字符串转日期.
	 * @param date
	 * @param pattern
	 * @return
	 * @author yangz
	 * @date 2012-7-28 下午11:39:19
	 */
	public static Date parseDate(String date, String pattern){
		return DateTime.parse(date, DateTimeFormat.forPattern(pattern)).toDate();
	}
	/**
	 * 根据时间戳转时间.
	 * @param date
	 * @return
	 * @author yangz
	 * @date 2013-3-31 下午4:07:56
	 */
	public static Date parseDate(long date){
		return new Date(date);
	}
	/**
	 * 返回当前时间.
	 * @return
	 * @author yangz
	 * @date 2013-6-4 上午9:54:18
	 */
	public static Date now(){
		return DateTime.now().toDate();
	}
	/**
	 * 默认根据系统日期格式转字符串.
	 * 
	 * @author yangz
	 * @date 2013-6-4 上午9:58:53
	 */
	public static String parseDateString(Date date){
		return new DateTime(date).toString(DATE);
	}
	/**
	 * 默认根据系统时间格式转字符串.
	 * @param date
	 * @return
	 * @author yangz
	 * @date 2013-6-4 上午10:00:45
	 */
	public static String parseTimeString(Date date){
		return new DateTime(date).toString(TIME);
	}
	/**
	 * 日期转字符串.
	 * @param date
	 * @param pattern
	 * @author yangz
	 * @date 2013-6-4 上午9:58:27
	 */
	public static String parseString(Date date, String pattern){
		return new DateTime(date).toString(pattern);
	}
	
	/**
	 * 获取时间戳.
	 * @param date
	 * @return
	 * @author ChenST
	 * @date 2014年4月25日 下午6:00:34
	 */
	public static Long getTimpstamp(String date) {
		if (StringUtil.isEmpty(date)) {
			return null;
		}
		return DateUtils.toCalendar(parseDate(date)).getTimeInMillis();
	}
}
