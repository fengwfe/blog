package com.fuwu.blog.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fuwu.blog.dto.param.FetchCategoryDTO;
import com.fuwu.blog.dto.response.CategoryDTO;
import com.fuwu.blog.model.entity.Category;
@Mapper
public interface CategoryMapper extends BaseCRUDMapper<Category,CategoryDTO,FetchCategoryDTO>{
	public List<CategoryDTO> fetchCategoriesByParentId(Integer parentId);

}
