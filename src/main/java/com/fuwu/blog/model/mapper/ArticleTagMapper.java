package com.fuwu.blog.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fuwu.blog.dto.param.FetchArticleTagDTO;
import com.fuwu.blog.dto.response.ArticleTagDTO;
import com.fuwu.blog.model.entity.ArticleTag;
@Mapper
public interface ArticleTagMapper extends BaseCRUDMapper<ArticleTag,ArticleTagDTO,FetchArticleTagDTO> {
	public int deleteTagsByArticleId(Integer articleId);

}
