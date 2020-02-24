package com.fuwu.blog.service;
import java.util.List;

import com.fuwu.blog.dto.param.CreateArticleDTO;
import com.fuwu.blog.dto.param.FetchArticleDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.UpdateArticleDTO;
import com.fuwu.blog.dto.response.ArticleDTO;
import com.fuwu.blog.model.entity.Article;

public interface ArticleService extends BaseCRUDService<Article,CreateArticleDTO,UpdateArticleDTO,FetchArticleDTO, ArticleDTO>{
	public ArticleDTO saveArticleAsDraft(CreateArticleDTO articleDTO);
	public ArticleDTO postArticle(CreateArticleDTO articleDTO);
	public List<ArticleDTO> fetchArticlesByCategoryId(FetchByIdDTO categoryId);

}