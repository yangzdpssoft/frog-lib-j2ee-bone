package com.cyou.fz.common.base.exception;

/**
 * 找不到视图异常.
 * 
 * @author yangz
 * @date 2012-7-28 下午03:07:44
 */
@SuppressWarnings("serial")
public class E404Exception extends RuntimeException {

	public E404Exception() {
		super();
	}

	public E404Exception(String message, Throwable cause) {
		super(message, cause);
	}

	public E404Exception(String message) {
		super(message);
	}

	public E404Exception(Throwable cause) {
		super(cause);
	}
	/**
	 * 减少异常栈深度,提高性能.
	 * @return
	 * @author yangz
	 * @date 2013-1-31 下午3:30:30
	 */
	@Override
	public Throwable fillInStackTrace() {
		return this;
	}

}
