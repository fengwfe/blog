package com.fuwu.blog.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class ResetPwdDTO {
	@ApiModelProperty("密码")
	private String loginPwd;
	@ApiModelProperty("确认密码")
	private String confirmPwd;
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getConfirmPwd() {
		return confirmPwd;
	}
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	

}
