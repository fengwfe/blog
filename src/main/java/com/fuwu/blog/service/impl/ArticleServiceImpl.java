package com.fuwu.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuwu.blog.constant.BlogConstant;
import com.fuwu.blog.dto.param.CreateArticleDTO;
import com.fuwu.blog.dto.param.FetchArticleDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.UpdateArticleDTO;
import com.fuwu.blog.dto.response.ArticleDTO;
import com.fuwu.blog.model.entity.Article;
import com.fuwu.blog.model.entity.ArticleTag;
import com.fuwu.blog.model.mapper.ArticleMapper;
import com.fuwu.blog.model.mapper.ArticleTagMapper;
import com.fuwu.blog.service.ArticleService;
import com.fuwu.blog.util.WebUtil;
@Service
public class ArticleServiceImpl extends BaseCRUDServiceImpl<Article,CreateArticleDTO,UpdateArticleDTO,FetchArticleDTO,ArticleDTO, ArticleMapper> implements ArticleService{
	
	@Autowired
	ArticleTagMapper articleTagMapper;

	@Override
	public ArticleDTO saveArticleAsDraft(CreateArticleDTO articleDTO) {
		ArticleDTO article= saveArticle(articleDTO, BlogConstant.ARTICLE_STATUS_DRAFT);
		return article;
	}
	@Override
	public ArticleDTO postArticle(CreateArticleDTO articleDTO) {
		ArticleDTO article=  saveArticle(articleDTO, BlogConstant.ARTICLE_STATUS_POSTED);
		return article;
	}

	private ArticleDTO saveArticle(CreateArticleDTO articleDTO,Integer status){
		Article article=new Article();
		article.setId(articleDTO.getArticleId());
		article.setStatus(status);
		article.setAuthorId(articleDTO.getAuthorId());
		article.setCategoryId(articleDTO.getCategoryId());
		article.setTitle(articleDTO.getTitle());
		article.setContent(articleDTO.getContent());
		save(article);
		articleTagMapper.deleteTagsByArticleId(article.getId());
		List<Integer> tagIds=articleDTO.getTagIds();
		if(null!=tagIds){
			for(Integer tagId:tagIds){
				ArticleTag articleTag=new ArticleTag();
				articleTag.setArticleId(article.getId());
				articleTag.setTagId(tagId);
				articleTag.setCreatedBy(WebUtil.getLoginUserId());
				articleTag.setUpdatedBy(WebUtil.getLoginUserId());
				articleTagMapper.create(articleTag);
			}
		}
		ArticleDTO newArticle=fetchById(article.getId());
		return newArticle;	
	}
	@Override
	public List<ArticleDTO> fetchArticlesByCategoryId(FetchByIdDTO categoryId) {
		return mapper.fetchArticlesByCategoryId(categoryId);
	}
}
