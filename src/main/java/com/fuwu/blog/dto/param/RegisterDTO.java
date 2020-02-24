package com.fuwu.blog.dto.param;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import io.swagger.annotations.ApiModelProperty;
public class RegisterDTO extends BaseCreateDTO{
	@ApiModelProperty("用户名")
	@Length(min = 1,max = 30,message = "用户名长度为1到30")
	@NotBlank(message = "用户名不能为空")
	private String loginName;
	@ApiModelProperty("密码")
	@Length(min = 6,max = 30,message = "密码长度为6到30")
	@NotBlank(message = "密码不能为空")
	private String loginPwd;
	@ApiModelProperty("确认密码")
	@NotBlank(message = "确认密码不能为空")
	private String confirmPwd;
	@ApiModelProperty("邮箱地址")
	@NotBlank(message = "邮箱地址不能为空")
	@Email(message = "邮箱地址格式不正确")
	private String email;
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
	public String getConfirmPwd() {
		return confirmPwd;
	}
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
