package com.fuwu.blog.dto.response;

public class TagDTO extends BaseResponseDTO{
	private Integer authorId;
	private String name;
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
