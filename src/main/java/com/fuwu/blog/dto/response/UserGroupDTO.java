package com.fuwu.blog.dto.response;

import java.util.List;

public class UserGroupDTO extends BaseResponseDTO{
	private String name;
	private String description;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

}
