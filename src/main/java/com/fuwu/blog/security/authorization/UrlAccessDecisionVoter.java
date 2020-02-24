package com.fuwu.blog.security.authorization;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;


public class UrlAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation>{
	private static final Logger logger = LoggerFactory.getLogger(UrlAccessDecisionVoter.class);

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	
	/**
	 * 1.对未配置权限的rest API接口资源，容许访问
	 * 2.对配置权限的的rest API接口资源，用户有相应的权限才可以访问
	 * 
	 * 
	 * PS：到这里的用户应该都已经通过认证了(pass authentication)
	 */
	@Override
	public int vote(Authentication authentication, FilterInvocation object, Collection<ConfigAttribute> attributes) {
		logger.info("=========UrlAccessDecisionVoter.vote called!!!============");
		
		if(null==attributes||attributes.size()==0) {//未配置权限的rest API接口资源
			return ACCESS_GRANTED;
		}else {
			Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
			Set<String> set=new HashSet<>();
			if(null==authorities) {
				return  ACCESS_DENIED;
			}else {
				for(GrantedAuthority authority:authorities) {
					set.add(authority.getAuthority());
				}
			}
			for(ConfigAttribute attribute:attributes) {
				if(!set.contains(attribute.getAttribute())) {
					return  ACCESS_DENIED;
				}
			}
			return ACCESS_GRANTED;
		}
	}

}
