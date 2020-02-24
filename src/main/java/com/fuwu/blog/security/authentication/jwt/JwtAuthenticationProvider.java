package com.fuwu.blog.security.authentication.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.fuwu.blog.security.AppUserDetails;
import com.fuwu.blog.util.JwtTokenUtil;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		JwtAuthenticationToken jwtAuthenticationToken=(JwtAuthenticationToken)authentication;
		String token=jwtAuthenticationToken.getToken();
		JwtAuthenticationToken successAuthenticated=null;
		AppUserDetails userDetails=jwtTokenUtil.validateToken(token);
		if(null!=userDetails) {
			successAuthenticated=new JwtAuthenticationToken(userDetails, token);
		}
		if(null==successAuthenticated) {
			throw new BadCredentialsException("请提供合法的Token");
		}
		 return successAuthenticated;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
