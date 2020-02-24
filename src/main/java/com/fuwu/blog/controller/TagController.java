package com.fuwu.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fuwu.blog.dto.param.CreateTagDTO;
import com.fuwu.blog.dto.param.FetchTagDTO;
import com.fuwu.blog.dto.param.UpdateTagDTO;
import com.fuwu.blog.dto.response.TagDTO;
import com.fuwu.blog.service.BaseCRUDService;
import com.fuwu.blog.service.TagService;
import io.swagger.annotations.Api;

@Api(tags = "Tag", description = "API接口")
@RestController
@RequestMapping("/tags")
public class TagController extends BaseCRUDController<CreateTagDTO, UpdateTagDTO, FetchTagDTO, TagDTO> {
	@Autowired
	TagService tagService;
	@Override
	public BaseCRUDService getService() {
		return tagService;
	}

}
