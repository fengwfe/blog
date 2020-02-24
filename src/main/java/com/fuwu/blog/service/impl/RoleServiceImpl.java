package com.fuwu.blog.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuwu.blog.dto.param.CreateRoleDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchRoleDTO;
import com.fuwu.blog.dto.param.UpdateRoleDTO;
import com.fuwu.blog.dto.response.RoleDTO;
import com.fuwu.blog.model.entity.Role;
import com.fuwu.blog.model.entity.RolePrivilege;
import com.fuwu.blog.model.mapper.PrivilegeMapper;
import com.fuwu.blog.model.mapper.RoleMapper;
import com.fuwu.blog.service.RoleService;
import com.fuwu.blog.util.WebUtil;
@Service
public class RoleServiceImpl extends BaseCRUDServiceImpl<Role,CreateRoleDTO,UpdateRoleDTO,FetchRoleDTO,RoleDTO, RoleMapper> implements RoleService{

	@Autowired
	PrivilegeMapper privilegeMapper;
	
	@Override
	public List<RoleDTO> fetchByUserId(FetchByIdDTO userId) {
		return mapper.fetchByUserId(userId);
	}
	@Override
	public RoleDTO addPrivilegeToRole(Integer privilegeId, Integer roleId) {
		RolePrivilege rollePrivilege=new RolePrivilege();
		rollePrivilege.setRoleId(roleId);
		rollePrivilege.setPrivilegeId(privilegeId);
		rollePrivilege.setCreatedBy(WebUtil.getLoginUserId());
		rollePrivilege.setUpdatedBy(WebUtil.getLoginUserId());
		privilegeMapper.addPrivilegeToRole(rollePrivilege);
		return mapper.fetchById(roleId);
	}

	@Override
	public RoleDTO removePrivilegeFromRole(Integer privilegeId, Integer roleId) {
		RolePrivilege rollePrivilege=new RolePrivilege();
		rollePrivilege.setRoleId(roleId);
		rollePrivilege.setPrivilegeId(privilegeId);
		privilegeMapper.removePrivilegeFromRole(rollePrivilege);
		return mapper.fetchById(roleId);
	}

}
