package com.fuwu.blog.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class FetchTagDTO extends BaseFetchDTO{
	@ApiModelProperty("Tag创建者id")
	private Integer authorId;

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

}
