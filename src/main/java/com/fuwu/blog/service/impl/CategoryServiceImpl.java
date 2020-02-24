package com.fuwu.blog.service.impl;

import org.springframework.stereotype.Service;
import com.fuwu.blog.dto.param.CreateCategoryDTO;
import com.fuwu.blog.dto.param.FetchCategoryDTO;
import com.fuwu.blog.dto.param.UpdateCategoryDTO;
import com.fuwu.blog.dto.response.CategoryDTO;
import com.fuwu.blog.model.entity.Category;
import com.fuwu.blog.model.mapper.CategoryMapper;
import com.fuwu.blog.service.CategoryService;
@Service
public class CategoryServiceImpl extends BaseCRUDServiceImpl<Category, CreateCategoryDTO,UpdateCategoryDTO,FetchCategoryDTO,CategoryDTO,CategoryMapper> implements CategoryService{
	@Override
	public CategoryDTO createCategory(CreateCategoryDTO categoryDTO) {
		Category category=new Category();
		category.setName(categoryDTO.getName());
		category.setAuthorId(categoryDTO.getAuthorId());
		category.setParentId(categoryDTO.getParentId());
		return create(category);
	}

	@Override
	public CategoryDTO updateCategory(Integer categoryId,UpdateCategoryDTO categoryDTO) {
		Category category=new Category();
		category.setId(categoryId);
		category.setName(categoryDTO.getName());
		category.setParentId(categoryDTO.getParentId());
		return update(category);
	}

}
