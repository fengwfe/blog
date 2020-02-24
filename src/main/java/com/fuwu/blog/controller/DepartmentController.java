package com.fuwu.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fuwu.blog.dto.param.CreateDepartmentDTO;
import com.fuwu.blog.dto.param.FetchDepartmentDTO;
import com.fuwu.blog.dto.param.UpdateDepartmentDTO;
import com.fuwu.blog.dto.response.DepartmentDTO;
import com.fuwu.blog.service.BaseCRUDService;
import com.fuwu.blog.service.DepartmentService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/departments")
@Api(tags = "部门", description = "API接口")
public class DepartmentController extends BaseCRUDController<CreateDepartmentDTO, UpdateDepartmentDTO, FetchDepartmentDTO, DepartmentDTO>{
	@Autowired
	DepartmentService departmentService;
	@Override
	public BaseCRUDService getService() {
		return departmentService;
	}

}
