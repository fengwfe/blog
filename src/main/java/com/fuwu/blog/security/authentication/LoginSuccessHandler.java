package com.fuwu.blog.security.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.fuwu.blog.security.SecurityConstants;
import com.fuwu.blog.util.JwtTokenUtil;
import com.fuwu.blog.util.WebUtil;


@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("=======LoginSuccessHandler.onAuthenticationSuccess called!!!======");
		AntPathRequestMatcher LoginURL=new AntPathRequestMatcher(SecurityConstants.LOGIN_URL,"POST");
		String token=null;
		if(LoginURL.matches(request)) {//登录时生成token
			UserDetails userDetails=(UserDetails)authentication.getPrincipal();
			token=jwtTokenUtil.generateToken(userDetails);
		}else {//其他action check if need to refresh token
			String oldToken=WebUtil.getToken(request);
		    token=jwtTokenUtil.checkAndRefresh(oldToken);
		}
		response.setHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX+token);
	}

}
