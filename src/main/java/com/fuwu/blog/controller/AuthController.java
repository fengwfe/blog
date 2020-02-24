package com.fuwu.blog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fuwu.blog.dto.ApiResponse;
import com.fuwu.blog.dto.param.LoginDTO;
import com.fuwu.blog.security.AppUserDetails;
import com.fuwu.blog.security.SecurityConstants;
import com.fuwu.blog.service.TokenInvalidService;
import com.fuwu.blog.util.JwtTokenUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "授权", description = "API接口")
@RestController
public class AuthController{
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	TokenInvalidService tokenInvalidService;
	
	//登录
	@ApiOperation("用户登录")
	@PostMapping(SecurityConstants.LOGIN_URL)
	public void login(@Valid @RequestBody LoginDTO loginDTO){
		//login logic is handled by loginfilter
		if(SecurityConstants.ONLY_ALLOW_ONE_USER_PER_ACCOUNT) {
			AppUserDetails userDetails=(AppUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			tokenInvalidService.invalidTokenByUserId(userDetails.getId());
		}
	}
	//登出
	@ApiOperation("用户登出")
	@PostMapping(SecurityConstants.LOGOUT_URL)
	public ApiResponse logout(){
		AppUserDetails userDetails=(AppUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		tokenInvalidService.invalidTokenByUserId(userDetails.getId());
		return ApiResponse.SUCCESS();
	}
}
