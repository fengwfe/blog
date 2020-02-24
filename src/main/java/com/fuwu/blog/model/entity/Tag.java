package com.fuwu.blog.model.entity;

public class Tag extends BaseEntity{
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
