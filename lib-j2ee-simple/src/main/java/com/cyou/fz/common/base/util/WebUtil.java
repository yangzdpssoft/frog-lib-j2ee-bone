package com.cyou.fz.common.base.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.UrlPathHelper;

import com.cyou.fz.common.base.exception.UnCaughtException;
import com.cyou.fz.common.base.holder.UrlPathHelperHolder;




/**
 * web工具类.
 * 
 * @author yangz
 * @date 2013-2-8 下午5:53:44
 */
public class WebUtil {
	/**
	 * 判断是否是ajax请求.
	 * @param request
	 * @return
	 * @author yangz
	 * @date 2012-7-30 下午02:59:10
	 */
	public static boolean isAjaxRequest(HttpServletRequest request){
		return !ObjectUtil.isEmpty(request.getHeader("X-Requested-With"));
	}
	
	/**
	 * 获取远程访问的IP地址.
	 * @param request
	 * @return
	 * @author yangz
	 * @date 2012-9-18 上午09:02:09
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 获取网站服务地址.
	 * @param request
	 * @return
	 * @author yangz
	 * @date 2013-5-31 上午10:06:12
	 */
	public static String getHostPath(HttpServletRequest request){
		StringBuilder sb = new StringBuilder();
		sb.append(request.getScheme()).append("://").append(request.getServerName()).append(":").append(request.getServerPort()).append(request.getContextPath());
		return sb.toString();
	}
	
	/**
	 * 获取content path之后的请求路径.
	 * @param request
	 * @return
	 * @author yangz
	 * @date 2013-6-2 下午7:49:05
	 */
	public static String getLookupPathForRequest(HttpServletRequest request){
		UrlPathHelper url = UrlPathHelperHolder.getInstance();
		return url.getLookupPathForRequest(request);
	}
	/**
	 * 获取rest风格的URL领域标识.
	 * @param request
	 * @return
	 * @author yangz
	 * @date 2013-6-2 下午8:06:26
	 */
	public static String getRestDomain(HttpServletRequest request){
		String result = "";
		String method = request.getMethod();
		String lookPath = getLookupPathForRequest(request);
		if("get".equalsIgnoreCase(method) || "delete".equalsIgnoreCase(method) || "put".equalsIgnoreCase(method)){
			result = lookPath.substring(0, lookPath.lastIndexOf('/'));
		}
		return result.substring(1);
	}
	/**
	 * 返回json请求.
	 * @param response
	 * @author yangz
	 * @date 2013-5-31 下午4:00:43
	 */
	public static void responseJson(HttpServletResponse response, Object value){
		PrintWriter writer = null;
		response.setContentType("text/plain; charset=UTF-8");
		try {
			writer = response.getWriter();
			writer.println(JsonUtil.toJson(value));
		} catch (Exception e) {
			throw new UnCaughtException(e);
		} finally {
			IOUtil.close(writer);
		}
	}
	
}
