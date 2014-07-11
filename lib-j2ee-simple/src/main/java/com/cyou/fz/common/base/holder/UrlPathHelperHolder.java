package com.cyou.fz.common.base.holder;


import org.springframework.web.util.UrlPathHelper;

/**
 * UrlPathHelper 单例.
 * @author yangz
 * @date 2013-6-2 下午7:50:27
 */
public class UrlPathHelperHolder {
	
	public static UrlPathHelper helper = new UrlPathHelper();
	/**
	 * 返回UrlPathHelper 单例.
	 * @return
	 * @author yangz
	 * @date 2013-6-2 下午7:52:30
	 */
	public static UrlPathHelper getInstance(){
		return helper;
	}
}
