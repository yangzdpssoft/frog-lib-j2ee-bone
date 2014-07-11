package com.cyou.fz.common.base.util;

import com.cyou.fz.common.base.exception.UnCaughtException;

import java.math.BigDecimal;
import java.util.Date;



/**
 * 数据类型.
 * 
 * @author yangz
 * @date   2012-7-28 下午02:42:26
 */
public class ValueUtil {
	/**
	 * 值对象 --> String.
	 * @param value
	 * @return
	 * @author yangz
	 * @date 2012-7-28 下午03:15:11
	 */
	public static String getString(Object value){
		String result = null;
		if(!ObjectUtil.isEmpty(value)){
			String sValue = StringUtil.trim(value.toString());
			if(value instanceof Number){
				if(value instanceof Double || value instanceof BigDecimal){
					if(!"Infinity".equals(sValue) && !"NaN".equals(sValue)){
						result = StringUtil.toNuSicen(value);
					}else{
						result = null;
					}
				}else{
					result = sValue;
				}
			}else{
				result = sValue;
			}
		}
		return result;
	}
	/**
	 * 值对象 --> long.
	 * @param value
	 * @return
	 * @author yangz
	 * @date 2012-7-28 下午03:27:21
	 */
	public static long getLong(Object value){
		try {
			return Long.parseLong(getString(value));
		} catch (Exception e) {
			return 0L;
		}
	}
	/**
	 * 值对象 --> double.
	 * @param value
	 * @return
	 * @author yangz
	 * @date 2012-7-28 下午03:29:25
	 */
	public static double getDouble(Object value){
		try {
			return Double.parseDouble(getString(value));
		} catch (Exception e) {
			return 0.0;
		}
	}
	/**
	 * 值对象 --> BigDecimal.
	 * @param value
	 * @return
	 * @author yangz
	 * @date 2013-3-31 下午4:12:37
	 */
	public static BigDecimal getBigDecimal(Object value){
		return new BigDecimal(getString(value));
	}
	/**
	 * 值对象 --> int.
	 * @param value
	 * @return
	 * @author yangz
	 * @date 2012-7-28 下午03:29:35
	 */
	public static int getInt(Object value){
		try {
			return Integer.parseInt(getString(value));
		} catch (Exception e) {
			return 0;
		}
	}
	/**
	 * 值对象 --> boolean.
	 * @param value
	 * @return
	 * @author yangz
	 * @date 2012-10-12 上午09:00:16
	 */
	public static boolean getBoolean(Object value){
		try {
			String v = getString(value);
			if("1".equals(v)){
				return true;
			}else if("0".equals(v)){
				return false;
			}else if("Y".equals(v)){
				return true;
			}else if("N".equals(v)){
				return false;
			}else{
				return Boolean.parseBoolean(v);
			}
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 转时间.
	 * @param value
	 * @return
	 * @author yangz
	 * @date 2013-4-14 下午7:47:45
	 */
	public static Date getDate(Object value){
		if(value instanceof Long){
			return DateUtil.parseDate(ValueUtil.getLong(value));
		}else if(value instanceof String){
			String vTemp = ValueUtil.getString(value);
			if(StringUtil.containsWhitespace(vTemp)){
				return DateUtil.parseDate(vTemp, DateUtil.TIME);
			}else{
				return DateUtil.parseDate(vTemp, DateUtil.DATE);
			}
		}else if(value instanceof Date){
			return (Date) value;
		}else{
			throw new UnCaughtException("unknow date type:" + value);
		}
	}
	/**
	 * 强行值类型转换.
	 * @param value
	 * @param type
	 * @return
	 * @author yangz
	 * @date 2013-2-8 下午5:26:26
	 */
	public static Object castValue(Object value, Class<?> type){
		if(int.class.isAssignableFrom(type) || Integer.class.isAssignableFrom(type)){
			return ValueUtil.getInt(value);
		}else if(long.class.isAssignableFrom(type) || Long.class.isAssignableFrom(type)){
			return ValueUtil.getLong(value);
		}else if(double.class.isAssignableFrom(type) || Double.class.isAssignableFrom(type)){
			return ValueUtil.getDouble(value);
		}else if(boolean.class.isAssignableFrom(type) || Boolean.class.isAssignableFrom(type)){
			return ValueUtil.getBoolean(value);
		}else if(String.class.isAssignableFrom(type)){
			return ValueUtil.getString(value);
		}else if(BigDecimal.class.isAssignableFrom(type)){
			return ValueUtil.getBigDecimal(value);
		}else if(Date.class.isAssignableFrom(type)){
			return ValueUtil.getDate(value);
		}else if(char.class.isAssignableFrom(type) || Character.class.isAssignableFrom(type)){
			return (char)ValueUtil.getInt(value);
		}else{
			throw new UnCaughtException("unknow value type:" + type);
		}
	}
}
