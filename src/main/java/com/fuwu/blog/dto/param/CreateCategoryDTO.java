package com.fuwu.blog.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class CreateCategoryDTO extends BaseCreateDTO{
	@ApiModelProperty("种类创建者id")
	private Integer authorId;
	@ApiModelProperty("种类名称")
	private String name;
	@ApiModelProperty("种类的父亲id")
	private Integer parentId;
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
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
