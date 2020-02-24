package com.fuwu.blog.service;



import com.fuwu.blog.dto.param.CreateCategoryDTO;
import com.fuwu.blog.dto.param.FetchCategoryDTO;
import com.fuwu.blog.dto.param.UpdateCategoryDTO;
import com.fuwu.blog.dto.response.CategoryDTO;
import com.fuwu.blog.model.entity.Category;

public interface CategoryService extends BaseCRUDService<Category,CreateCategoryDTO,UpdateCategoryDTO,FetchCategoryDTO,CategoryDTO>{
	public CategoryDTO createCategory(CreateCategoryDTO categoryDTO);
	public CategoryDTO updateCategory(Integer categoryId,UpdateCategoryDTO categoryDTO);

}
