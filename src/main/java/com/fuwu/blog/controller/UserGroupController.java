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
import com.fuwu.blog.dto.param.CreateUserGroupDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchUserGroupDTO;
import com.fuwu.blog.dto.param.UpdateUserGroupDTO;
import com.fuwu.blog.dto.response.RoleDTO;
import com.fuwu.blog.dto.response.UserDTO;
import com.fuwu.blog.dto.response.UserGroupDTO;
import com.fuwu.blog.service.BaseCRUDService;
import com.fuwu.blog.service.UserGroupService;
import com.fuwu.blog.service.UserService;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/userGroups")
@Api(tags = "用户组", description = "API接口")
public class UserGroupController extends BaseCRUDController<CreateUserGroupDTO, UpdateUserGroupDTO, FetchUserGroupDTO, UserGroupDTO>{
	@Autowired
	UserGroupService userGroupService;
	
	@Autowired
	UserService userService;
	
	@Override
	public BaseCRUDService getService() {
		return userGroupService;
	}
	@ApiOperation("添加用户到用户组")
	@PostMapping("/{userGroupId}/users")
	public ApiResponse<UserGroupDTO> addUserToUserGroup(@ApiParam("用户组id") @PathVariable("userGroupId")Integer userGroupId,@ApiParam("用户id")@RequestBody Integer userId){
		UserGroupDTO userGroupDTO=userGroupService.addUserToUserGroup(userId, userGroupId);
		return new ApiResponse<UserGroupDTO>(userGroupDTO);
		
	}
	@ApiOperation("从用户组中删除用户")
	@DeleteMapping("/{userGroupId}/users/{userId}")
	public ApiResponse<UserGroupDTO> removeUserFromUserGroup(@ApiParam("用户组id") @PathVariable("userGroupId")Integer userGroupId,@ApiParam("用户id")@PathVariable("userId") Integer userId){
		UserGroupDTO userGroupDTO=userGroupService.removeUserFromUserGroup(userId, userGroupId);
		return new ApiResponse<UserGroupDTO>(userGroupDTO);
	}
	@ApiOperation("获取特定用户组的用户信息")
	 @GetMapping("/{userGroupId}/users")
	 public ApiResponse<PageInfo<UserDTO>> fetchUsersByUserGroupId(@ApiParam("用户组id") @PathVariable("userGroupId") Integer userGroupId,FetchByIdDTO pageParams){
		pageParams.setId(userGroupId);
		List<UserDTO> list=userService.fetchByGroupId(pageParams);
		PageInfo<UserDTO> pageInfo=new PageInfo<>(list);
		return new ApiResponse<PageInfo<UserDTO>>(pageInfo);
	 }

}
