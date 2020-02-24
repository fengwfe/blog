package com.fuwu.blog.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fuwu.blog.dto.ApiResponse;
import com.fuwu.blog.dto.param.CreateCategoryDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchCategoryDTO;
import com.fuwu.blog.dto.param.PageParamDTO;
import com.fuwu.blog.dto.param.UpdateCategoryDTO;
import com.fuwu.blog.dto.response.ArticleDTO;
import com.fuwu.blog.dto.response.CategoryDTO;
import com.fuwu.blog.service.ArticleService;
import com.fuwu.blog.service.BaseCRUDService;
import com.fuwu.blog.service.CategoryService;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "种类", description = "API接口")
@RestController
@RequestMapping("/categories")
public class CategoryController extends BaseCRUDController<CreateCategoryDTO, UpdateCategoryDTO, FetchCategoryDTO, CategoryDTO>{
	@Autowired
	CategoryService categoryService;
	@Autowired
	ArticleService articleService;
	
	//查询特定类别的文章
	@ApiOperation("获取特定种类下的文章")
	@GetMapping("/{id}/articles")
	public ApiResponse<PageInfo<ArticleDTO>> fetchArticlesByCategoryId(PageParamDTO pageParamDTO,@ApiParam("种类id") @PathVariable("id")Integer id){
		FetchByIdDTO fetchByIdDTO=new FetchByIdDTO(pageParamDTO,id);
		List<ArticleDTO> list=articleService.fetchArticlesByCategoryId(fetchByIdDTO);
		PageInfo<ArticleDTO> pageInfo=new PageInfo<>(list);
		return new ApiResponse<PageInfo<ArticleDTO>>(pageInfo);
	}
	@Override
	public BaseCRUDService getService() {
		return categoryService;
	}

}
