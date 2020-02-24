package com.fuwu.blog.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class UpdateUserGroupDTO extends BaseUpateDTO{
	private String name;
	private String description;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
