package com.fuwu.blog.model.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchPrivilegeDTO;
import com.fuwu.blog.dto.response.PrivilegeDTO;
import com.fuwu.blog.model.entity.Privilege;
import com.fuwu.blog.model.entity.RestResourcePrivilege;
import com.fuwu.blog.model.entity.RolePrivilege;

@Mapper
public interface PrivilegeMapper extends BaseCRUDMapper<Privilege,PrivilegeDTO,FetchPrivilegeDTO> {
	public List<PrivilegeDTO> fetchByRestResourceId(FetchByIdDTO restResourceId);
	public List<PrivilegeDTO> fetchByRoleId(FetchByIdDTO roleId);
	public List<PrivilegeDTO> fetchByRoleIdNonPagination(Integer roleId);
	public void addPrivilegeToRole(RolePrivilege rolePrivilege);
	public void removePrivilegeFromRole(RolePrivilege rolePrivilege);
	public void addPrivilegeToRestResource(RestResourcePrivilege restResourcePrivilege);
	public void removePrivilegeFromRestResource(RestResourcePrivilege restResourcePrivilege);

}
