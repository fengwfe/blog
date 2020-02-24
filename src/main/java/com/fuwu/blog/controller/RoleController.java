package com.fuwu.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fuwu.blog.dto.ApiResponse;
import com.fuwu.blog.dto.param.CreateRoleDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchRoleDTO;
import com.fuwu.blog.dto.param.UpdateRoleDTO;
import com.fuwu.blog.dto.response.PrivilegeDTO;
import com.fuwu.blog.dto.response.RoleDTO;
import com.fuwu.blog.dto.response.UserDTO;
import com.fuwu.blog.service.BaseCRUDService;
import com.fuwu.blog.service.PrivilegeService;
import com.fuwu.blog.service.RoleService;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Api(tags = "角色", description = "API接口")
@RestController
@RequestMapping("/roles")
public class RoleController extends BaseCRUDController<CreateRoleDTO, UpdateRoleDTO, FetchRoleDTO, RoleDTO> {

	@Autowired
	RoleService roleService;
	
	@Autowired
	PrivilegeService privilegeService;
	
	
	@ApiOperation("添加权限到角色")
	@PostMapping("/{roleId}/privileges")
	public ApiResponse<RoleDTO> addPrivilegeToRole(@ApiParam("角色id") @PathVariable("roleId")Integer roleId,@ApiParam("权限id")@RequestBody Integer privilegeId){
		RoleDTO dto=roleService.addPrivilegeToRole(privilegeId, roleId);
		return new ApiResponse<RoleDTO>(dto);
		
	}
	@ApiOperation("从角色中删除权限")
	@DeleteMapping("/{roleId}/privileges/{privilegeId}")
	public ApiResponse<RoleDTO> removePrivilegeFromRole(@ApiParam("角色id") @PathVariable("roleId")Integer roleId,@ApiParam("权限id")@PathVariable("privilegeId") Integer privilegeId){
		RoleDTO dto=roleService.removePrivilegeFromRole(privilegeId, roleId);
		return new ApiResponse<RoleDTO>(dto);
	}
	@ApiOperation("获取特定角色的权限信息")
	 @GetMapping("/{roleId}/privileges")
	 public ApiResponse<PageInfo<PrivilegeDTO>> fetchPrivilegesByRoleId(@ApiParam("角色id")@PathVariable("roleId") Integer roleId,FetchByIdDTO pageParams){
		pageParams.setId(roleId);
		List<PrivilegeDTO> list=privilegeService.fetchByRoleId(pageParams);
		PageInfo<PrivilegeDTO> pageInfo=new PageInfo<>(list);
		return new ApiResponse<PageInfo<PrivilegeDTO>>(pageInfo);
	 }

	@Override
	public BaseCRUDService getService() {
		return roleService;
	}
	
}
