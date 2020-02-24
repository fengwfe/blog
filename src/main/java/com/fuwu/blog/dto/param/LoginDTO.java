package com.fuwu.blog.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class LoginDTO {
	@ApiModelProperty("用户名")
	private String loginName;
	@ApiModelProperty("密码")
	private String loginPwd;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	
	

}
