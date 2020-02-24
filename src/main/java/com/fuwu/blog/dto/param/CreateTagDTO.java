package com.fuwu.blog.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class CreateTagDTO extends BaseCreateDTO{
	@ApiModelProperty("Tag创建者id")
	private Integer authorId;
	@ApiModelProperty("Tag名称")
	private String tagName;
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	

}
