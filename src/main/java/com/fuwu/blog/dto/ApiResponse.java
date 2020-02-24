package com.fuwu.blog.dto;

public class ApiResponse<T>{
	private Integer code;
	private String message;
	private T data;
	
	public ApiResponse(Integer code,String message,T data) {
		this.code=code;
		this.message=message;
		this.data=data;
	}
	public static ApiResponse SUCCESS() {
		return new ApiResponse<>(ApiStatus.SUCCESS, "success",null);
	}
	public static ApiResponse FAILED() {
		return new ApiResponse<>(ApiStatus.ERROR, "error",null);
	}
	public static ApiResponse FAILED(String message) {
		return new ApiResponse<>(ApiStatus.ERROR, message,null);
	}
	public ApiResponse(T data) {
		this.code=ApiStatus.SUCCESS;
		this.message="success";
		this.data=data;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
