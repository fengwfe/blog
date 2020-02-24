package com.fuwu.blog.service;

import com.fuwu.blog.dto.param.CreateUserGroupDTO;
import com.fuwu.blog.dto.param.FetchUserGroupDTO;
import com.fuwu.blog.dto.param.UpdateUserGroupDTO;
import com.fuwu.blog.dto.response.UserGroupDTO;
import com.fuwu.blog.model.entity.UserGroup;

public interface UserGroupService extends BaseCRUDService<UserGroup,CreateUserGroupDTO,UpdateUserGroupDTO,FetchUserGroupDTO,UserGroupDTO>{
	public UserGroupDTO addUserToUserGroup(Integer userId,Integer userGroupId);
	public UserGroupDTO removeUserFromUserGroup(Integer userId,Integer userGroupId);
}
