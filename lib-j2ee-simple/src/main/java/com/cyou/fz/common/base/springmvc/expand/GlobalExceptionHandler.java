package com.cyou.fz.common.base.springmvc.expand;

import com.cyou.fz.common.base.exception.InputException;
import com.cyou.fz.common.base.exception.UnCaughtException;
import com.cyou.fz.common.base.springmvc.ajax.Response;
import com.cyou.fz.common.base.springmvc.ajax.ResponseFactory;
import com.cyou.fz.common.base.util.WebUtil;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 全局异常处理.
 * @author yangz
 * @date   2012-10-10 上午09:11:47
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {
	/**
	 * 后台请求地址.
	 */
	private String adminUrl = "admin/";
	/**
	 * 前台请求地址.
	 */
	private String frontUrl = "front/";
	/**
	 * 后台500页面
	 */
	private String adminViewName = "admin/500";
	/**
	 * 前台500页面
	 */
	private String frontViewName = "front/500";
    /**
     * 默认500页面
     */
    private String defaultViewName = "500";
	/**
	 * 处理全局异常.
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @return
	 * @author yangz
	 * @date 2012-10-10 上午09:40:02
	 */
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		String url = request.getRequestURI();
		if(ex instanceof InputException){
			LoggerFactory.getLogger(handler.getClass()).info("---非常请求, 请求地址:" + url, ex);
			return dealInputException(request, response, handler, ex);
		}else if(ex != null && ex.getCause() instanceof InputException){
			LoggerFactory.getLogger(handler.getClass()).info("---非常请求, 请求地址:" + url, ex);
			return dealInputException(request, response, handler, (Exception) ex.getCause());
		}else if(ex instanceof UnCaughtException){
			LoggerFactory.getLogger(handler.getClass()).error("---未知异常, 异常地址:" + url, ex);
		}else{
			LoggerFactory.getLogger(handler.getClass()).error("---未知异常, 异常地址:" + url, ex);
		}
		return dealException(request, response, handler, ex);
	}
	/**
	 * 处理异常.
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @return
	 * @author yangz
	 * @date 2013-5-31 下午4:10:29
	 */
	private ModelAndView dealException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
		if(WebUtil.isAjaxRequest(request)){
			Response<String> ajaxResponse = ResponseFactory.getDefaultErrorResponse(ex.getMessage());
			WebUtil.responseJson(response, ajaxResponse);
		}else{
			String url = request.getRequestURI();
			if(url.contains(adminUrl)){
				return new ModelAndView(adminViewName);
			}else if(url.contains(frontUrl)){
				return new ModelAndView(frontViewName);
			}else{
                return new ModelAndView(defaultViewName);
            }
		}
		return new ModelAndView();
	}
	/**
	 * 处理参数校验异常.
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @return
	 * @author yangz
	 * @date 2013-5-31 下午4:10:20
	 */
	private ModelAndView dealInputException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
		if(WebUtil.isAjaxRequest(request)){
            Response<String> ajaxResponse = ResponseFactory.getDefaultInputResponse();
            ajaxResponse.setMessage(ex.getMessage());
			WebUtil.responseJson(response, ajaxResponse);
		}else{
			String url = request.getRequestURI();
			if(url.contains(adminUrl)){
				return new ModelAndView(adminViewName);
			}else if(url.contains(frontUrl)){
				return new ModelAndView(frontViewName);
			}
		}
		return new ModelAndView();
	}
	public String getAdminUrl() {
		return adminUrl;
	}
	
	public void setAdminUrl(String adminUrl) {
		this.adminUrl = adminUrl;
	}
	
	public String getFrontUrl() {
		return frontUrl;
	}
	
	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}

	public String getAdminViewName() {
		return adminViewName;
	}

	public void setAdminViewName(String adminViewName) {
		this.adminViewName = adminViewName;
	}

	public String getFrontViewName() {
		return frontViewName;
	}

	public void setFrontViewName(String frontViewName) {
		this.frontViewName = frontViewName;
	}
	
}
