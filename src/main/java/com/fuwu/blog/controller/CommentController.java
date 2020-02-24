package com.fuwu.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fuwu.blog.dto.param.CreateCommentDTO;
import com.fuwu.blog.dto.param.FetchCommentDTO;
import com.fuwu.blog.dto.param.UpdateCommentDTO;
import com.fuwu.blog.dto.response.CommentDTO;
import com.fuwu.blog.service.BaseCRUDService;
import com.fuwu.blog.service.CommentService;
import io.swagger.annotations.Api;

@Api(tags = "评论", description = "API接口")
@RestController
@RequestMapping("/comments")
public class CommentController extends BaseCRUDController<CreateCommentDTO, UpdateCommentDTO, FetchCommentDTO, CommentDTO> {
	@Autowired
	CommentService commentService;
		
	@Override
	public BaseCRUDService getService() {
		return commentService;
	}

}
