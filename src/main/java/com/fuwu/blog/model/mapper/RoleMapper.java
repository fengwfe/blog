package com.fuwu.blog.model.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchRoleDTO;
import com.fuwu.blog.dto.response.RoleDTO;
import com.fuwu.blog.model.entity.Role;
import com.fuwu.blog.model.entity.UserRole;

@Mapper
public interface RoleMapper extends BaseCRUDMapper<Role,RoleDTO,FetchRoleDTO> {
	public void addRoleToUser(UserRole userRole);
	public void removeRoleFromUser(UserRole userRole);
	public List<RoleDTO> fetchByUserId(FetchByIdDTO userId);
	public List<RoleDTO> fetchByUserIdNonPagination(Integer userId);

}
