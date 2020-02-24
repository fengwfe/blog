package com.fuwu.blog.security.authentication.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtAuthenticationToken extends AbstractAuthenticationToken{
	
	private UserDetails principle;
	private String token;
	public JwtAuthenticationToken(String token) {
		super(null);
		this.token=token;
		setAuthenticated(false);
		
	}
	public JwtAuthenticationToken(UserDetails principle,String token) {
		super(principle.getAuthorities());
		this.principle=principle;
		this.token=token;
		super.setAuthenticated(true);
		
	}
	@Override
	public Object getCredentials() {
		return token;
	}

	@Override
	public Object getPrincipal() {
		return this.principle;
	}
	public String getToken() {
		return token;
	}
}
