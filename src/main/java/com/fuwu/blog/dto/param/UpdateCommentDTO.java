package com.fuwu.blog.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class UpdateCommentDTO extends BaseUpateDTO{
	@ApiModelProperty("评论内容")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

}
