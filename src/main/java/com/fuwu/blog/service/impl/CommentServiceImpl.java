package com.fuwu.blog.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fuwu.blog.dto.param.CreateCommentDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchCommentDTO;
import com.fuwu.blog.dto.param.UpdateCommentDTO;
import com.fuwu.blog.dto.response.CommentDTO;
import com.fuwu.blog.model.entity.Comment;
import com.fuwu.blog.model.mapper.CommentMapper;
import com.fuwu.blog.service.CommentService;
import com.github.pagehelper.PageInfo;
@Service
public class CommentServiceImpl extends BaseCRUDServiceImpl<Comment,CreateCommentDTO,UpdateCommentDTO,FetchCommentDTO,CommentDTO, CommentMapper> implements CommentService{

	@Override
	public CommentDTO createComment(Integer articleId, CreateCommentDTO commentDTO) {
		Comment comment=new Comment();
		comment.setArticleId(articleId);
		comment.setAuthorId(commentDTO.getAuthorId());
		comment.setContent(commentDTO.getContent());
		return create(comment);
		 
	}

	@Override
	public PageInfo<CommentDTO> fetchCommentsByArticleId(FetchByIdDTO fetchByIdDTO) {
		List<CommentDTO> list=mapper.fetchCommentsByArticleId(fetchByIdDTO);
		PageInfo<CommentDTO> pageInfo=new PageInfo<>(list);
		return pageInfo;
	}

}
