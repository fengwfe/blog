package com.fuwu.blog.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fuwu.blog.security.authentication.LoginFailureEntryPoint;
import com.fuwu.blog.security.authentication.LoginFailureHandler;
import com.fuwu.blog.security.authentication.LoginSuccessHandler;
import com.fuwu.blog.security.authentication.json.JsonLoginFilter;
import com.fuwu.blog.security.authentication.jwt.JwtAuthenticationProvider;
import com.fuwu.blog.security.authentication.jwt.JwtLoginFilter;
import com.fuwu.blog.security.authorization.JwtAccessDeniedHandler;
import com.fuwu.blog.security.authorization.UrlAccessDecisionVoter;
import com.fuwu.blog.security.authorization.UrlFilterInvocationSecurityMetadataSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	LoginFailureEntryPoint lginFailureEntryPoint;
	
	@Autowired
	LoginSuccessHandler loginSuccessHandler;// write token to header
	
	@Autowired
	LoginFailureHandler loginFailureHandler;//401
	
	
	@Autowired
	JwtAccessDeniedHandler accessDeniedHandler;//403
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtAuthenticationProvider jwtAuthenticationProvider;
	
	@Autowired
	UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(jwtAuthenticationProvider).authenticationProvider(daoAuthenticationProvider());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		
		
		
//		/// works
//		http
//		.authorizeRequests().accessDecisionManager(accessDecisionManager())
//		.withObjectPostProcessor(filterSecurityInterceptorPostProcessor())
//		.anyRequest().authenticated()
//		.and().csrf().disable()
//		.formLogin().disable()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		
////		.and()
////		.exceptionHandling()
//		/**
//		 * 不知道怎么配置 让authenticationEntryPoint和withObjectPostProcessor(filterSecurityInterceptorPostProcessor())一起工作
//		 * 暂时改用AuthenticationFailureHandler
//		 */ 
////		.authenticationEntryPoint(authenticationEntryPoint)
////		.accessDeniedHandler(accessDeniedHandler)
////		.withObjectPostProcessor(filterSecurityInterceptorPostProcessor())
//
//		.and()
//		.addFilterBefore(jsonLoginFilter(),UsernamePasswordAuthenticationFilter.class)
//		.addFilterBefore(jwtLoginFilter(),UsernamePasswordAuthenticationFilter.class);
		
		
		http
		.csrf().disable()
		.formLogin().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(lginFailureEntryPoint)
		.accessDeniedHandler(accessDeniedHandler)
		.and()
		.addFilterBefore(jsonLoginFilter(),UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(jwtLoginFilter(),UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests().antMatchers("/**").hasAuthority("create_user1")
		.anyRequest().authenticated()
		.withObjectPostProcessor(filterSecurityInterceptorPostProcessor())
		;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// for swagger,ignore security check
	   web.ignoring().antMatchers("/v2/api-docs",
               "/configuration/ui",
               "/swagger-resources/**",
               "/configuration/security",
               "/swagger-ui.html",
               "/webjars/**");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return  new BCryptPasswordEncoder();
	}
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	public JsonLoginFilter jsonLoginFilter() throws Exception {
		JsonLoginFilter jsonLoginFilter=new JsonLoginFilter(authenticationManagerBean(),loginSuccessHandler,loginFailureHandler);
		return jsonLoginFilter;
	}
	public JwtLoginFilter jwtLoginFilter() throws Exception{
		JwtLoginFilter jwtLoginFilter=new JwtLoginFilter(authenticationManagerBean(),loginSuccessHandler,loginFailureHandler);
		return jwtLoginFilter;
	}
	
	@Bean("daoAuthenticationProvider")
	protected AuthenticationProvider daoAuthenticationProvider() throws Exception{
		DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
		daoProvider.setUserDetailsService(userDetailsService);
		daoProvider.setPasswordEncoder(passwordEncoder());
		return daoProvider;
	}
	
	public ObjectPostProcessor<FilterSecurityInterceptor> filterSecurityInterceptorPostProcessor(){
		return new ObjectPostProcessor<FilterSecurityInterceptor>() {
	        public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
	        	fsi.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
	        	fsi.setAccessDecisionManager(accessDecisionManager());
	            return fsi;   
	        } 
	    };
	}
	
	@Bean
	public AccessDecisionManager accessDecisionManager() {
	    List<AccessDecisionVoter<? extends Object>> decisionVoters 
	      = Arrays.asList(
	        new WebExpressionVoter(),
	        new RoleVoter(),
	        new AuthenticatedVoter(),
	        new UrlAccessDecisionVoter());
	    return new UnanimousBased(decisionVoters);
	}
}
