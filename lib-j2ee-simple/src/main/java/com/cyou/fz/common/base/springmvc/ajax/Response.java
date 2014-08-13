package com.cyou.fz.common.base.springmvc.ajax;

import java.io.Serializable;

/**
* @Description: 前后端交互类.
* @Company : cyou
* @author songchen  
* @date 2012-7-6 下午4:53:30 
* @version V1.0
 */
public class Response<T> implements Serializable{
	
	//成功
	public static final String RESULT_SUCCESS = "success";
	//未登录, 或者roleId获取失败, 典型的处理方式是显示完信息跳转到登录页
	public static final String RESULT_LOGIN = "login";
	//业务规则失败或者业务异常(自己在自己的程序各层抛出的异常), 比如扣款时余额不足
	public static final String RESULT_FAILURE = "failure";
	//表单格式验证失败, 表单业务规则验证失败, 典型的处理方式是显示完信息跳转回表单页
	public static final String RESULT_INPUT = "input";
	//可以预见但是不能处理的异常, 如SQLException, IOException等等
	public static final String RESULT_ERROR = "error";

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2049439550666128636L;

	// 标识变量
	private String result;

	private T data;

	public Response(){
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
