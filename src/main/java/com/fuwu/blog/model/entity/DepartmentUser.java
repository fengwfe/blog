package com.fuwu.blog.model.entity;

public class DepartmentUser extends BaseEntity{
	private Integer departmentId;
	private Integer userId;
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	

}
