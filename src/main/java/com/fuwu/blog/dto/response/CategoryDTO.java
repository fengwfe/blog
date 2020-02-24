package com.fuwu.blog.dto.response;

import java.util.List;

import com.fuwu.blog.model.entity.Category;

public class CategoryDTO extends BaseResponseDTO{
	private Integer authorId;
	private String name;
	private Integer parentId;
	private List<CategoryDTO> children;
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public List<CategoryDTO> getChildren() {
		return children;
	}
	public void setChildren(List<CategoryDTO> children) {
		this.children = children;
	}
	public static CategoryDTO instance(Category category){
		CategoryDTO categoryDTO=new CategoryDTO();
		categoryDTO.setAuthorId(category.getAuthorId());
		categoryDTO.setId(category.getId());
		categoryDTO.setParentId(category.getParentId());
		categoryDTO.setName(category.getName());
		categoryDTO.setCreated(category.getCreated());
		categoryDTO.setCreatedBy(category.getCreatedBy());
		categoryDTO.setUpdated(category.getUpdated());
		categoryDTO.setUpdatedBy(category.getUpdatedBy());
		return categoryDTO;
	}
	

}
