package com.fuwu.blog.dto.response;

public class CommentDTO extends BaseResponseDTO{
	private Integer articleId;
	private String content;
	private UserDTO author;
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public UserDTO getAuthor() {
		return author;
	}
	public void setAuthor(UserDTO author) {
		this.author = author;
	}
}
