package com.fuwu.blog.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchUserDTO;
import com.fuwu.blog.dto.response.UserDTO;
import com.fuwu.blog.model.entity.User;

@Mapper
public interface UserMapper extends BaseCRUDMapper<User,UserDTO,FetchUserDTO> {
	public void resetPwd(@Param("id")Integer userId,@Param("loginPwd")String newPwd);
	public User fetchByLoginName(String loginName);
	public List<UserDTO> fetchByGroupId(FetchByIdDTO userGroupId);
	public List<UserDTO> fetchByDepartmentId(FetchByIdDTO departmentId);

}
