package com.cyou.fz.common.base.exception;

/**
 * 参数校验异常.
 * 
 * @author yangz
 * @date 2012-7-28 下午03:07:44
 */
@SuppressWarnings("serial")
public class InputException extends RuntimeException {

	public InputException() {
		super();
	}

	public InputException(String message, Throwable cause) {
		super(message, cause);
	}

	public InputException(String message) {
		super(message);
	}

	public InputException(Throwable cause) {
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
