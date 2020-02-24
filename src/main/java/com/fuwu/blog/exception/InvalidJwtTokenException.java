package com.fuwu.blog.exception;

public class InvalidJwtTokenException extends AppException{

	public InvalidJwtTokenException(String msg) {
		super(msg);
	}

}
