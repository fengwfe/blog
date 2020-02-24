package com.fuwu.blog.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class FetchArticleDTO extends BaseFetchDTO{
	@ApiModelProperty("文章作者id")
	private Integer authorId;
	@ApiModelProperty("文章种类id")
	private Integer categoryId; 
	@ApiModelProperty("文章状态")
	private String status;
	
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
