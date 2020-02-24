package com.fuwu.blog.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class UpdateUserDTO extends BaseUpateDTO{
	@ApiModelProperty("昵称")
	private String nickname;
	@ApiModelProperty("邮箱地址")
	private String email;
	@ApiModelProperty("头像图片地址")
	private String avatar;
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
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	

}
