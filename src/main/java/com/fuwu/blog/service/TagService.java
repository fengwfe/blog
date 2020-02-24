package com.fuwu.blog.service;

import java.util.List;

import com.fuwu.blog.dto.param.CreateTagDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchTagDTO;
import com.fuwu.blog.dto.param.UpdateTagDTO;
import com.fuwu.blog.dto.response.TagDTO;
import com.fuwu.blog.model.entity.Tag;

public interface TagService extends BaseCRUDService<Tag,CreateTagDTO,UpdateTagDTO,FetchTagDTO,TagDTO>{
	public List<TagDTO> fetchTagsByArticleId(FetchByIdDTO fetchByIdDTO);
	public TagDTO createTag(CreateTagDTO tagDTO);
	public TagDTO updateTag(Integer tagId,UpdateTagDTO tagDTO);
	public int deleteByArticleId(Integer articleId);

}
