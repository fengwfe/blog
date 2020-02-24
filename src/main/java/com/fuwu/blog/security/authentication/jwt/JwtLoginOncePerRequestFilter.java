package com.fuwu.blog.security.authentication.jwt;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fuwu.blog.util.WebUtil;
//暂时不用,有空再弄
public class JwtLoginOncePerRequestFilter extends OncePerRequestFilter{
	
	private AuthenticationManager authenticationManager;
	private AuthenticationSuccessHandler successHandler;
	private AuthenticationFailureHandler failureHandler;
	
	public JwtLoginOncePerRequestFilter(AuthenticationManager authenticationManager,AuthenticationSuccessHandler successHandler,AuthenticationFailureHandler failureHandler) {
		this.authenticationManager=authenticationManager;
		this.successHandler=successHandler;
		this.failureHandler=failureHandler;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Authentication authResult=null;
		try {
			String token=WebUtil.getToken(request);
			JwtAuthenticationToken authenticationToken=new JwtAuthenticationToken(token);
			 authResult  =authenticationManager.authenticate(authenticationToken);
			if(null!=authResult && null==SecurityContextHolder.getContext().getAuthentication() ) {
				SecurityContextHolder.getContext().setAuthentication(authResult);
				successHandler.onAuthenticationSuccess(request, response, authResult);
			}
		} catch (Exception e) {
			
			//不知道怎么把AuthenticationEntryPoint和FilterSecurityInterceptor/withObjectPostProcessor(filterSecurityInterceptorPostProcessor()一起配置起来，无法一起生效...
			
			
			
			//不要throw exception,让在WebSecurityConfig 中配置的authenticationEntryPoint做统一处理
			//如果throw exception AuthenticationEntryPoint不会被call到!!!
			
			System.out.println("=====JwtLoginFilter======ERROR"+e.getMessage());
			e.printStackTrace();
			System.out.println("=====JwtLoginFilter======ERROR===========END"); 
			
		}
//		if(null==authResult) {
//			failureHandler.onAuthenticationFailure(request, response, new BadCredentialsException("请提供合法的token"));
//			return;
//		} 
//		
		if(true) {
//			throw new AuthenticationException("FFFFFFFFFFf");
		}
		filterChain.doFilter(request, response);
		
	}

}
