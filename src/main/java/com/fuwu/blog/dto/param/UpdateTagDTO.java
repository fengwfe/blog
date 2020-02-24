package com.fuwu.blog.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class UpdateTagDTO extends BaseUpateDTO{
	@ApiModelProperty("Tag名称")
	private String tagName;

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	

}
