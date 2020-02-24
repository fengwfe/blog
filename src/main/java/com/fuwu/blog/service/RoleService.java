package com.fuwu.blog.service;

import java.util.List;

import com.fuwu.blog.dto.param.CreateRoleDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchRoleDTO;
import com.fuwu.blog.dto.param.UpdateRoleDTO;
import com.fuwu.blog.dto.response.RoleDTO;
import com.fuwu.blog.model.entity.Role;

public interface RoleService extends BaseCRUDService<Role,CreateRoleDTO,UpdateRoleDTO,FetchRoleDTO,RoleDTO>{
	public List<RoleDTO> fetchByUserId(FetchByIdDTO userId);
	public RoleDTO addPrivilegeToRole(Integer privilegeId,Integer roleId);
	public RoleDTO removePrivilegeFromRole(Integer privilegeId,Integer roleId);
}
