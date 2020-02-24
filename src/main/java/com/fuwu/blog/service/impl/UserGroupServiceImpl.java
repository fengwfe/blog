package com.fuwu.blog.service.impl;

import org.springframework.stereotype.Service;

import com.fuwu.blog.dto.param.CreateUserGroupDTO;
import com.fuwu.blog.dto.param.FetchUserGroupDTO;
import com.fuwu.blog.dto.param.UpdateUserGroupDTO;
import com.fuwu.blog.dto.response.UserGroupDTO;
import com.fuwu.blog.model.entity.UserGroup;
import com.fuwu.blog.model.entity.UserGroupUser;
import com.fuwu.blog.model.mapper.UserGroupMapper;
import com.fuwu.blog.service.UserGroupService;
import com.fuwu.blog.util.WebUtil;

@Service
public class UserGroupServiceImpl extends BaseCRUDServiceImpl<UserGroup,CreateUserGroupDTO,UpdateUserGroupDTO,FetchUserGroupDTO,UserGroupDTO, UserGroupMapper> implements UserGroupService{

	@Override
	public UserGroupDTO addUserToUserGroup(Integer userId, Integer userGroupId) {
		UserGroupUser userGroupUser=new UserGroupUser();
		userGroupUser.setUserId(userId);
		userGroupUser.setUserGroupId(userGroupId);
		userGroupUser.setCreatedBy(WebUtil.getLoginUserId());
		userGroupUser.setUpdatedBy(WebUtil.getLoginUserId());
		mapper.addUserToUserGroup(userGroupUser);
		return fetchById(userGroupId);
	}

	@Override
	public UserGroupDTO removeUserFromUserGroup(Integer userId, Integer userGroupId) {
		UserGroupUser userGroupUser=new UserGroupUser();
		userGroupUser.setUserId(userId);
		userGroupUser.setUserGroupId(userGroupId);
		mapper.removeUserFromUserGroup(userGroupUser);
		return fetchById(userGroupId);
	}
}
