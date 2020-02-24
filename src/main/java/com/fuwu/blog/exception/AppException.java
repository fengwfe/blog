package com.fuwu.blog.exception;

public class AppException extends RuntimeException {
	private String msg;
	private Integer code;
	public AppException(String msg) {
		super(msg);
		this.msg=msg;
	}

}
