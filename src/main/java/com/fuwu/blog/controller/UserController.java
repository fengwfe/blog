package com.fuwu.blog.controller;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fuwu.blog.dto.ApiResponse;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchUserDTO;
import com.fuwu.blog.dto.param.RegisterDTO;
import com.fuwu.blog.dto.param.ResetPwdDTO;
import com.fuwu.blog.dto.param.UpdateUserDTO;
import com.fuwu.blog.dto.response.RoleDTO;
import com.fuwu.blog.dto.response.UserDTO;
import com.fuwu.blog.service.BaseCRUDService;
import com.fuwu.blog.service.RoleService;
import com.fuwu.blog.service.UserService;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/users")
@Api(tags = "用户", description = "API接口")
public class UserController extends BaseCRUDController<RegisterDTO, UpdateUserDTO, FetchUserDTO, UserDTO> {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	
	//用户修改密码
	@ApiOperation("用户修改密码")
	@PostMapping("/{id}/resetPwd")
	public ApiResponse resetPwd(@ApiParam("用户id") @PathVariable("id") Integer id,@Valid @RequestBody ResetPwdDTO resetPwdDTO,BindingResult bindingResult ) {
		 handleValidationResult(bindingResult);
		 userService.resetPwd(id,resetPwdDTO);
		 return ApiResponse.SUCCESS();
	}
	
	@ApiOperation("添加角色到用户")
	@PostMapping("/{userId}/roles")
	public ApiResponse<UserDTO> addRoleToUser(@ApiParam("用户id") @PathVariable("userId")Integer userId,@ApiParam("角色id")@RequestBody Integer roleId){
		UserDTO dto=userService.addRoleToUser(roleId, userId);
		return new ApiResponse<UserDTO>(dto);
		
	}
	@ApiOperation("从用户中删除角色")
	@DeleteMapping("/{userId}/roles/{roleId}")
	public ApiResponse<UserDTO> removeRoleFromUser(@ApiParam("用户id") @PathVariable("userId")Integer userId,@ApiParam("角色id")@PathVariable("roleId") Integer roleId){
		UserDTO dto=userService.removeRoleFromUser(roleId, userId);
		return new ApiResponse<UserDTO>(dto);
	}
	@ApiOperation("获取特定用户的角色信息")
	 @GetMapping("/{userId}/roles")
	 public ApiResponse<PageInfo<RoleDTO>> fetchRolesByUserId(@ApiParam("用户id") @PathVariable("userId") Integer userId,FetchByIdDTO pageParams){
		pageParams.setId(userId);
		List<RoleDTO> list=roleService.fetchByUserId(pageParams);
		PageInfo<RoleDTO> pageInfo=new PageInfo<>(list);
		return new ApiResponse<PageInfo<RoleDTO>>(pageInfo);
	 }
	@Override
	public BaseCRUDService getService() {
		// TODO Auto-generated method stub
		return userService;
	}
}
