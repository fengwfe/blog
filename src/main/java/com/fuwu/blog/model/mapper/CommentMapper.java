package com.fuwu.blog.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchCommentDTO;
import com.fuwu.blog.dto.response.CommentDTO;
import com.fuwu.blog.model.entity.Comment;
@Mapper
public interface CommentMapper extends BaseCRUDMapper<Comment,CommentDTO,FetchCommentDTO>{
	public List<CommentDTO> fetchCommentsByArticleId(FetchByIdDTO fetchByIdDTO);

}
