package com.fuwu.blog.service;

import com.fuwu.blog.dto.param.CreateCommentDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchCommentDTO;
import com.fuwu.blog.dto.param.UpdateCommentDTO;
import com.fuwu.blog.dto.response.CommentDTO;
import com.fuwu.blog.model.entity.Comment;
import com.github.pagehelper.PageInfo;

public interface CommentService extends BaseCRUDService<Comment,CreateCommentDTO,UpdateCommentDTO,FetchCommentDTO,CommentDTO>{
	public CommentDTO createComment(Integer articleId,CreateCommentDTO commentDTO);
	public PageInfo<CommentDTO> fetchCommentsByArticleId(FetchByIdDTO fetchByIdDTO);
	

}
