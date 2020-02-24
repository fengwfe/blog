package com.fuwu.blog.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchTagDTO;
import com.fuwu.blog.dto.response.TagDTO;
import com.fuwu.blog.model.entity.Tag;
@Mapper
public interface TagMapper extends BaseCRUDMapper<Tag,TagDTO,FetchTagDTO>{
	public List<TagDTO> fetchTagsByArticleId(FetchByIdDTO fetchByIdDTO);
	public List<TagDTO> fetchTagsByAuthorId(FetchByIdDTO fetchByIdDTO);
}
