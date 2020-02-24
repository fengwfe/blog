package com.fuwu.blog.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class CreateCommentDTO extends BaseCreateDTO{
	@ApiModelProperty("评论作者id")
	private Integer authorId;
	@ApiModelProperty("评论内容")
	private String content;
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
