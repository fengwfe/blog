package com.fuwu.blog.dto.param;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class CreateArticleDTO extends BaseCreateDTO {
	@ApiModelProperty("文章id")
	private Integer articleId;
	@ApiModelProperty("文章标题")
	private String title;
	@ApiModelProperty("文章内容")
	private String content;
	@ApiModelProperty("文章作者id")
	private Integer authorId;
	@ApiModelProperty("文章tags")
	private List<Integer> tagIds;
	@ApiModelProperty("文章种类id")
	private Integer categoryId;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public List<Integer> getTagIds() {
		return tagIds;
	}
	public void setTagIds(List<Integer> tagIds) {
		this.tagIds = tagIds;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	
}
