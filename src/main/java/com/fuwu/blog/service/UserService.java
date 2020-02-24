package com.fuwu.blog.service;


import java.util.List;

import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchUserDTO;
import com.fuwu.blog.dto.param.RegisterDTO;
import com.fuwu.blog.dto.param.ResetPwdDTO;
import com.fuwu.blog.dto.param.UpdateUserDTO;
import com.fuwu.blog.dto.response.UserDTO;
import com.fuwu.blog.model.entity.User;

public interface UserService extends BaseCRUDService<User,RegisterDTO ,UpdateUserDTO,FetchUserDTO,UserDTO>{
	public void resetPwd(Integer userId,ResetPwdDTO resetPwdDTO);
	public User fetchByLoginName(String loginName);
	public List<UserDTO> fetchByGroupId(FetchByIdDTO userGroupId);
	public List<UserDTO> fetchByDepartmentId(FetchByIdDTO departmentId);
	public UserDTO addRoleToUser(Integer roleId,Integer userId);
	public UserDTO removeRoleFromUser(Integer roleId,Integer userId);
}
