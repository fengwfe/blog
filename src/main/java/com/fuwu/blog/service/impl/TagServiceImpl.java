package com.fuwu.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuwu.blog.dto.param.CreateTagDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchTagDTO;
import com.fuwu.blog.dto.param.UpdateTagDTO;
import com.fuwu.blog.dto.response.TagDTO;
import com.fuwu.blog.model.entity.Tag;
import com.fuwu.blog.model.mapper.ArticleTagMapper;
import com.fuwu.blog.model.mapper.TagMapper;
import com.fuwu.blog.service.TagService;

@Service
public class TagServiceImpl extends BaseCRUDServiceImpl<Tag,CreateTagDTO,UpdateTagDTO,FetchTagDTO,TagDTO, TagMapper> implements TagService{
	
	@Autowired
	ArticleTagMapper articleTagMapper;
	
	@Override
	public List<TagDTO> fetchTagsByArticleId(FetchByIdDTO fetchByIdDTO) {
		return mapper.fetchTagsByArticleId(fetchByIdDTO);
	}

	@Override
	public TagDTO createTag(CreateTagDTO tagDTO) {
		Tag tag=new Tag();
		tag.setAuthorId(tagDTO.getAuthorId());
		tag.setName(tagDTO.getTagName());
		return create(tag);

	}

	@Override
	public TagDTO updateTag(Integer tagId, UpdateTagDTO tagDTO) {
		Tag tag=new Tag();
		tag.setId(tagId);
		tag.setName(tagDTO.getTagName());
		return update(tag);
	}

	@Override
	public int deleteByArticleId(Integer articleId) {
		return articleTagMapper.deleteTagsByArticleId(articleId);
	}

}
