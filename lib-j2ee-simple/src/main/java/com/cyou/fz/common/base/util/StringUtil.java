package com.cyou.fz.common.base.util;

import com.cyou.fz.common.base.exception.UnCaughtException;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




/**
 * 字符串操作工具类.
 * 
 * @author yangz
 * @date   2012-7-28 下午02:43:17
 */
public class StringUtil extends StringUtils{
	
	/**
	 * 字符串是否存在中文.
	 * @param str
	 * @return
	 * @author yangz
	 * @date 2012-9-21 下午03:24:33
	 */
	public static boolean isExistZH(String str) {
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		while (m.find()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 字符串第一个字母大写.
	 * @param s
	 * @return
	 * @author yangz
	 * @date 2012-9-27 下午03:10:46
	 */
	public static String upperFirstChar(String s) {
		if (!ObjectUtil.isEmpty(s)) {
			return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1);
		} else {
			return s;
		}
	}
	
	/**
	 * 字符串第一个字母小写.
	 * @param s
	 * @return
	 * @author yangz
	 * @date 2012-9-27 下午03:10:58
	 */
	public static String lowerFirstChar(String s) {
		if (!ObjectUtil.isEmpty(s)) {
			return String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1);
		} else {
			return s;
		}
	}
	
	/**
	 * 获取第一个大写字母.
	 * @param s
	 * @return
	 * @author yangz
	 * @date 2012-10-23 上午09:06:18
	 */
	public static String getUpperFirstChar(String s) {
		if (!ObjectUtil.isEmpty(s)) {
			return String.valueOf(s.charAt(0)).toUpperCase();
		} else {
			return s;
		}
	}
	
	/**
	 * 四舍五入并去掉科学计数法, 默认小数点2位.
	 * @param value String, double, Double, BigDecimal
	 * @return
	 * @author yangz
	 * @date 2012-7-28 下午03:44:05
	 */
	public static String toNuSicen(Object value) {
		return toNuSicen(value, 2);
	}
	
	/**
	 * 四舍五入并去掉科学计数法.
	 * @param value String, double, Double, BigDecimal
	 * @param precision 保留几位小数
	 * @return
	 * @author yangz
	 * @date 2012-7-28 下午03:47:25
	 */
	public static String toNuSicen(Object value, int precision) {
		Object result = "";
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(precision);
		df.setMaximumFractionDigits(precision);
		df.setGroupingUsed(false);
		try {
			if(value instanceof BigDecimal){
				result = value;
			}else if(value instanceof String){
				result = new BigDecimal(value.toString());
			}else if(value instanceof Number){
				result = Double.valueOf(value.toString());
			}else{
				return df.format(0);
			}
		} catch (Exception e) {
			return df.format(0);
		}
		return df.format(result);
	}
	
	/**
	 * 获取禁用正则表达式的Pattern<br>
	 *      1、'.'可代表换行符.<br>
     *      2、忽略大小写.<br>
	 *      3、正则表达式的特殊字符,依然代表普通字符.<br>
	 * @param value
	 * @return
	 * @author yangz
	 * @date 2012-10-9 下午03:32:33
	 */
	public static Pattern getInsensitivePattern(String value){
		return Pattern.compile(ValueUtil.getString(value).replaceAll("([\\+\\-\\&\\.\\|\\!\\(\\)\\{\\}\\[\\]\\^\\\"\\~\\*\\?\\:])","\\\\$1"), Pattern.CASE_INSENSITIVE + Pattern.DOTALL);
	}
	
	/**
	 * 截取字符串的尾部.
	 * @param value 源字符串
	 * @param separator 分割符
	 * @return
	 * @author yangz
	 * @date 2013-2-19 下午6:17:23
	 */
	public static String subLast(String value, String separator){
		return org.apache.commons.lang.StringUtils.substringAfterLast(value, separator);
	}
	
	/**
	 * 截取字符串的头部.
	 * @param value 源字符串
	 * @param separator 分割符
	 * @return
	 * @author yangz
	 * @date 2013-2-19 下午6:17:23
	 */
	public static String subBefore(String value, String separator){
		return org.apache.commons.lang.StringUtils.substringBeforeLast(value, separator);
	}
	/**
	 * 过滤前后全角半角空格.
	 * @return
	 * @author yangz
	 * @date 2013-5-5 下午11:58:52
	 */
	public static String trim(String value){
		if(value == null){
			return "";
		}
		int len = value.length();
        int st = 0;
        int ed = len;
        char[] val = new char[len];
        value.getChars(0, len, val, 0);    
        while ((st < len) && (val[st] == '　' || val[st] == ' ')) {
            st++;
        }
        while ((ed > st) && (val[ed - 1] == '　' || val[ed - 1] == ' ')) {
            ed--;
        }
        return st < ed ? value.substring(st, ed) : "";
	}
	
	/**
	 * 路径合并.
	 * @param basePath
	 * @param append
	 * @return
	 * @author yangz
	 * @date 2013-2-22 下午4:21:49
	 */
	public static String appendPath(String basePath, String append){
		String result = basePath;
		if(isLeftSlash(result)){
			if(result.endsWith("/") && append.startsWith("/")){
				result += append.substring(1);
			}else if(result.endsWith("/") || append.startsWith("/")){
				result += append;
			}else{
				result += "/" + append;
			}
			if(!result.endsWith("/")){
				result += "/";
			}
		}else if(isRightSlash(result)){
			if(result.endsWith("\\") && append.startsWith("\\")){
				result += append.substring(1);
			}else if(result.endsWith("\\") || append.startsWith("\\")){
				result += append;
			}else{
				result += "\\" + append;
			}
			if(!result.endsWith("\\")){
				result += "\\";
			}
		}
		return result;
	}
	/**
	 * 路径追加文件名.
	 * @param path
	 * @param fileName
	 * @return
	 * @author yangz
	 * @date 2013-2-22 下午5:14:15
	 */
	public static String appendFileName(String path, String fileName){
		String result = path;
		if(isLeftSlash(result)){
			if(result.endsWith("/") && fileName.startsWith("/")){
				result += fileName.substring(1);
			}else if(result.endsWith("/") || fileName.startsWith("/")){
				result += fileName;
			}else{
				result += "/" + fileName;
			}
		}else if(isRightSlash(result)){
			if(result.endsWith("\\") && fileName.startsWith("\\")){
				result += fileName.substring(1);
			}else if(result.endsWith("\\") || fileName.startsWith("\\")){
				result += fileName;
			}else{
				result += "\\" + fileName;
			}
		}
		return result;
	}
	
	/**
	 * 路径左斜杆形式.
	 * @return
	 * @author yangz
	 * @date 2013-2-19 下午6:34:05
	 */
	public static boolean isLeftSlash(String path){
		boolean result = path.contains("/");
		if(result && path.contains("\\")){
			throw new UnCaughtException("path is not law:" + path);
		}else{
			return result;
		}
	}
	/**
	 * 路径右斜杆形式.
	 * @param path
	 * @return
	 * @author yangz
	 * @date 2013-2-19 下午6:37:15
	 */
	public static boolean isRightSlash(String path){
		boolean result = path.contains("\\");
		if(result && path.contains("/")){
			throw new UnCaughtException("path is not law:" + path);
		}else{
			return result;
		}
	}

    /**
     * 剔除前缀.
     * @param source
     * @param prefix
     * @return
     */
    public static String clearPrefix(String source, String prefix){
        return source.replaceFirst(prefix, "");
    }

}
