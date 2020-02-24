package com.fuwu.blog.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fuwu.blog.dto.param.FetchUserGroupDTO;
import com.fuwu.blog.dto.response.UserGroupDTO;
import com.fuwu.blog.model.entity.UserGroup;
import com.fuwu.blog.model.entity.UserGroupUser;

@Mapper
public interface UserGroupMapper extends BaseCRUDMapper<UserGroup,UserGroupDTO,FetchUserGroupDTO> {
	public void addUserToUserGroup(UserGroupUser userGroupUser);
	public void removeUserFromUserGroup(UserGroupUser userGroupUser);
}
