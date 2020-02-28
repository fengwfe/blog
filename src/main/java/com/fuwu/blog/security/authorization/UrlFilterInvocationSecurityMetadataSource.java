package com.fuwu.blog.security.authorization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.security.access.SecurityConfig;

import com.fuwu.blog.dto.response.RestResourceDTO;
import com.fuwu.blog.service.PrivilegeService;


@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{

	private static final Logger logger = LoggerFactory.getLogger(UrlFilterInvocationSecurityMetadataSource.class);
	@Autowired
	PrivilegeService privilegeService;
	@Autowired
	ApplicationContext context;
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		logger.info("=======UrlFilterInvocationSecurityMetadataSource=========");
		List<String> requiredPrivileges=fetchRequiredPrivilegeCodes(((FilterInvocation) object).getRequest());
		List<ConfigAttribute> configAttributes =null;
		if(null!=requiredPrivileges) {
			configAttributes=new ArrayList<ConfigAttribute>();
			for(String privilege:requiredPrivileges) {
				  ConfigAttribute configAttribute = new SecurityConfig(privilege);
				  configAttributes.add(configAttribute);
			}
		}
		return configAttributes;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	private List<String> fetchRequiredPrivilegeCodes(HttpServletRequest request) {
		Map<RestResourceDTO, List<String>> map=privilegeService.fetchResourcePrivilegeMap();
		if(null!=map) {
			Iterator<RestResourceDTO> keys=map.keySet().iterator();
			while(keys.hasNext()) {
				RestResourceDTO key=keys.next();
				String uri=key.getUri();
				String httpMethod=key.getHttpMethod();
				AntPathRequestMatcher matcher=new AntPathRequestMatcher(uri,httpMethod);
				if(matcher.matches(request)) {
					List<String> codes= map.get(key);
					return codes;
					
				}
			}
		}
		return null;
	}
}
