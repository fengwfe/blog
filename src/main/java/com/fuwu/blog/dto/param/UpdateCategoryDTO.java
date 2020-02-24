package com.fuwu.blog.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class UpdateCategoryDTO extends BaseUpateDTO{
	@ApiModelProperty("种类名称")
	private String name;
	@ApiModelProperty("种类的父亲id")
	private Integer parentId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	

}
