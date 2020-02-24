package com.fuwu.blog.dto.param;

public class FetchCommentDTO extends BaseFetchDTO{
	private Integer articleId;
	private Integer authorId;
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

}
