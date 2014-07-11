package com.cyou.fz.common.base.springmvc.ajax;
/**
 * 常用Response工厂.
 * @Company : cyou
 * @author yangz
 * @date   2012-9-29 下午02:29:23
 */
public class ResponseFactory {
	/**
	 * 获取默认操作成功Response.
	 * @return
	 * @author yangz
	 * @date 2012-9-29 下午02:30:07
	 */
	public static <T> Response<T> getDefaultSuccessResponse(){
		Response<T> response = new Response<T>();
		response.setResult(Response.RESULT_SUCCESS);
		return response;
	}
	
	/**
	 * 获取默认业务失败Response.
	 * @return
	 * @author yangz
	 * @date 2012-11-14 下午04:37:39
	 */
	public static <T> Response<T> getDefaultFailureResponse(String message){
		Response<T> response = new Response<T>();
		response.setResult(Response.RESULT_FAILURE);
        response.setMessage(message);
		return response;
	}
    /**
     * 获取默认系统错误Response.
     * @return
     * @author yangz
     * @date 2012-11-14 下午04:37:39
     */
    public static <T> Response<T> getDefaultErrorResponse(String message){
        Response<T> response = new Response<T>();
        response.setResult(Response.RESULT_ERROR);
        response.setMessage(message);
        return response;
    }
    /**
     * 提示登录.
     * @return
     * @author yangz
     * @date 2013-5-31 上午11:58:23
     */
    public static Response<String> getDefaultLoginResponse(){
        Response<String> response = new Response<String>();
        response.setResult(Response.RESULT_LOGIN);
        return response;
    }
    /**
     * 参数校验失败.
     * @return
     * @author yangz
     * @date 2013-5-31 上午11:59:27
     */
    public static <T> Response<T> getDefaultInputResponse(){
        Response<T> response = new Response<T>();
        response.setResult(Response.RESULT_INPUT);
        return response;
    }
}
