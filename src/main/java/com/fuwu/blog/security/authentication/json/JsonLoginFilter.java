package com.fuwu.blog.security.authentication.json;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fuwu.blog.security.SecurityConstants;
import com.fuwu.blog.util.JsonUtil;

public class JsonLoginFilter extends AbstractAuthenticationProcessingFilter{

	public JsonLoginFilter(AuthenticationManager authenticationManager,AuthenticationSuccessHandler successHandler,AuthenticationFailureHandler failureHandler) {
		super(new AntPathRequestMatcher(SecurityConstants.LOGIN_URL,"POST"));
		super.setAuthenticationManager(authenticationManager);
		super.setAuthenticationSuccessHandler(successHandler);
		super.setAuthenticationFailureHandler(failureHandler);
	}
	@Override
	/**
	 * 如果该方法throw以下exception,filter chain 会被终止然后failure handler 会被call到，
	 * 见AbstractAuthenticationProcessingFilter.java的doFilter源码
	 * 
	 * 1.InternalAuthenticationServiceException
	 * 2.AuthenticationException
	 */
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		Map<String, Object>map=JsonUtil.getRequestBody(request);
		Object loginName=map.get(SecurityConstants.LOGIN_NAME_KEY);
		Object loginPwd=map.get(SecurityConstants.LOGIN_PWD_KEY);
		String loginNameStr= loginName==null?"":(String)loginName;
		String loginPwdStr= loginPwd==null?"":(String)loginPwd;
		UsernamePasswordAuthenticationToken authRequest=new UsernamePasswordAuthenticationToken(loginNameStr, loginPwdStr);
		return this.getAuthenticationManager().authenticate(authRequest);
	}
}
