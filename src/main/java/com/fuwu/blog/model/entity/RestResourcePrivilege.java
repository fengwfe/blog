package com.fuwu.blog.model.entity;

public class RestResourcePrivilege extends BaseEntity{
	private Integer restResourceId;
	private Integer privilegeId;
	public Integer getRestResourceId() {
		return restResourceId;
	}
	public void setRestResourceId(Integer restResourceId) {
		this.restResourceId = restResourceId;
	}
	public Integer getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}
	

}
