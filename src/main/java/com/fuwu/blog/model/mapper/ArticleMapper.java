package com.fuwu.blog.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.fuwu.blog.dto.param.FetchArticleDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.response.ArticleDTO;
import com.fuwu.blog.model.entity.Article;

@Mapper
public interface ArticleMapper extends BaseCRUDMapper<Article,ArticleDTO,FetchArticleDTO>{
	public List<ArticleDTO> fetchArticlesByCategoryId(FetchByIdDTO categoryId);
}
