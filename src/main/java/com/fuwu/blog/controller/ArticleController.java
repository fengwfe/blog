package com.fuwu.blog.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fuwu.blog.dto.ApiResponse;
import com.fuwu.blog.dto.param.CreateArticleDTO;
import com.fuwu.blog.dto.param.CreateCommentDTO;
import com.fuwu.blog.dto.param.FetchArticleDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.PageParamDTO;
import com.fuwu.blog.dto.param.UpdateArticleDTO;
import com.fuwu.blog.dto.response.ArticleDTO;
import com.fuwu.blog.dto.response.CommentDTO;
import com.fuwu.blog.dto.response.TagDTO;
import com.fuwu.blog.service.ArticleService;
import com.fuwu.blog.service.BaseCRUDService;
import com.fuwu.blog.service.CommentService;
import com.fuwu.blog.service.TagService;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Api(tags = "文章", description = "API接口")
@RestController
@RequestMapping("/articles")
public class ArticleController extends BaseCRUDController<CreateArticleDTO, UpdateArticleDTO, FetchArticleDTO, ArticleDTO>{
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	TagService tagService;
	
	
	//保存为草稿
	@ApiOperation("保存文章为草稿")
	@PostMapping("/saveAsDraft")
	public ApiResponse<ArticleDTO> saveArticleAsDraft(@RequestBody CreateArticleDTO createArticleDTO){
		ArticleDTO article=articleService.saveArticleAsDraft(createArticleDTO);
		return new ApiResponse<ArticleDTO>(article);
	}
	
	//发布文章
	@ApiOperation("发布文章")
	@PostMapping("/post")
	public ApiResponse<ArticleDTO> postArticle(@RequestBody CreateArticleDTO createArticleDTO){
		ArticleDTO article=articleService.postArticle(createArticleDTO);
		return new ApiResponse<ArticleDTO>(article);
	}

	//发布评论
	@ApiOperation("发布评论")
	@PostMapping("/{id}/comments")
	public ApiResponse<CommentDTO> createComment(@ApiParam("文章id") @PathVariable("id")Integer articleId,@RequestBody CreateCommentDTO commentDTO) {
		CommentDTO  comment=commentService.createComment(articleId, commentDTO);
		ApiResponse<CommentDTO> apiResponse=new ApiResponse<>(comment) ;
		return apiResponse;
	}
	//获取该文章的评论
	@ApiOperation("获取特定文章的评论")
	@GetMapping("/{id}/comments")
	public ApiResponse<PageInfo<CommentDTO>> fetchCommentsByArticleId(PageParamDTO pageParamDTO,@ApiParam("文章id") @PathVariable("id") Integer id){
		FetchByIdDTO fetchByIdDTO=new FetchByIdDTO(pageParamDTO,id);
		PageInfo<CommentDTO> pageInfo=commentService.fetchCommentsByArticleId(fetchByIdDTO);
		return new ApiResponse<PageInfo<CommentDTO>>(pageInfo);
	}
	//获取文章的tags
	@ApiOperation("获取特定文章的Tags")
	@GetMapping("/{id}/tags")
	public ApiResponse<List<TagDTO>> fetchTagsByArticleId(PageParamDTO pageParamDTO,@ApiParam("文章id") @PathVariable("id") Integer id){
		FetchByIdDTO fetchByIdDTO=new FetchByIdDTO(pageParamDTO,id);
		List<TagDTO> list =tagService.fetchTagsByArticleId(fetchByIdDTO);
		return new ApiResponse<List<TagDTO>>(list);
	}

	@Override
	public BaseCRUDService getService() {
		return articleService;
	}
}
