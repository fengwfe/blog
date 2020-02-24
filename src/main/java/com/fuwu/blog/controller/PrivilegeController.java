package com.fuwu.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fuwu.blog.dto.param.CreatePrivilegeDTO;
import com.fuwu.blog.dto.param.FetchPrivilegeDTO;
import com.fuwu.blog.dto.param.UpdatePrivilegeDTO;
import com.fuwu.blog.dto.response.PrivilegeDTO;
import com.fuwu.blog.service.BaseCRUDService;
import com.fuwu.blog.service.PrivilegeService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/privileges")
@Api(tags = "权限", description = "API接口")
public class PrivilegeController extends BaseCRUDController<CreatePrivilegeDTO, UpdatePrivilegeDTO, FetchPrivilegeDTO, PrivilegeDTO>{

	@Autowired
	PrivilegeService privilegeService;

	@Override
	public BaseCRUDService getService() {
		return privilegeService;
	}
	
}
