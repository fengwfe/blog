package com.fuwu.blog.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class FetchUserDTO extends BaseFetchDTO{
	@ApiModelProperty("用户名")
	private String loginName;
	@ApiModelProperty("昵称")
	private String nickname;
	@ApiModelProperty("邮箱地址")
	private String email;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
