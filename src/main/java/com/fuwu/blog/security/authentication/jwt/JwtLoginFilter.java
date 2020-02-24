package com.fuwu.blog.security.authentication.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fuwu.blog.security.SecurityConstants;
import com.fuwu.blog.util.WebUtil;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter{

	public JwtLoginFilter(AuthenticationManager authenticationManager,AuthenticationSuccessHandler successHandler,AuthenticationFailureHandler failureHandler) {
		super(new AntPathRequestMatcher(SecurityConstants.ALL_REQUEST_URL));
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
		String token=WebUtil.getToken(request);
		JwtAuthenticationToken authenticationToken=new JwtAuthenticationToken(token);
		return this.getAuthenticationManager().authenticate(authenticationToken);
		
	}

	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		if(null!=SecurityConstants.NON_AUTHENTICATION_URLS) {
			for(String url:SecurityConstants.NON_AUTHENTICATION_URLS) {
				AntPathRequestMatcher matcher= new AntPathRequestMatcher(url);
				if(matcher.matches(request)) {
					return false;
				}
			}
		}
		return super.requiresAuthentication(request, response);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		/**
		 * make sure to call chain.doFilter,
		 * otherwise the chain will be stopped and FilterSecurityInterceptor will not be called
		 * and this will cause AccessDecisionManager will not get called!!!
		 * 
		 */
		chain.doFilter(request, response);
	}
	
}
