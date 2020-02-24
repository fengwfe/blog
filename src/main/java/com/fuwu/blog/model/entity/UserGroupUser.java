package com.fuwu.blog.model.entity;

public class UserGroupUser extends BaseEntity{
	private Integer userGroupId;
	private Integer userId;
	public Integer getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	

}
