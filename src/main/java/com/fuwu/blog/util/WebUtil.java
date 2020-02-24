package com.fuwu.blog.util;

import javax.servlet.http.HttpServletRequest;

import com.fuwu.blog.security.SecurityConstants;

public class WebUtil {
	
	public static  String getToken(HttpServletRequest request){
		String authHeader = request.getHeader(SecurityConstants.TOKEN_HEADER);
	    if ( authHeader != null && authHeader.startsWith(SecurityConstants.TOKEN_PREFIX)){
	        return authHeader.substring(SecurityConstants.TOKEN_PREFIX.length());
	    }
	    return null;
	}
	
	public static Integer getLoginUserId() {
		return 1;
	}

}
