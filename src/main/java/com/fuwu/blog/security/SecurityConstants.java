package com.fuwu.blog.security;

public interface SecurityConstants {
	String ALL_REQUEST_URL="/**";
	String LOGIN_NAME_KEY="loginName";
	String LOGIN_PWD_KEY="loginPwd";
	String LOGIN_URL="/auth/login";
	String LOGOUT_URL="/auth/logout";
	String TOKEN_HEADER="Authorization";
	String TOKEN_PREFIX="Bearer ";
	boolean ONLY_ALLOW_ONE_USER_PER_ACCOUNT=true;
	String [] NON_AUTHENTICATION_URLS= {
		LOGIN_URL,
		LOGOUT_URL
	};
}
