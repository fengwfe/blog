package com.fuwu.blog.security.authorization;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
	private static final Logger logger = LoggerFactory.getLogger(JwtAccessDeniedHandler.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		logger.info("=========JwtAccessDeniedHandler.handle called!!!=======");
		response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
	}

}
