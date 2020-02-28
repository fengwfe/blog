package com.fuwu.blog.service;

import java.util.List;
import java.util.Map;


import com.fuwu.blog.dto.param.CreatePrivilegeDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchPrivilegeDTO;
import com.fuwu.blog.dto.param.UpdatePrivilegeDTO;
import com.fuwu.blog.dto.response.PrivilegeDTO;
import com.fuwu.blog.dto.response.RestResourceDTO;
import com.fuwu.blog.model.entity.Privilege;

public interface PrivilegeService extends BaseCRUDService<Privilege,CreatePrivilegeDTO,UpdatePrivilegeDTO,FetchPrivilegeDTO,PrivilegeDTO>{
	public Map<RestResourceDTO, List<String>> fetchResourcePrivilegeMap();
	public List<PrivilegeDTO> fetchByRoleId(FetchByIdDTO roleId);
	public List<PrivilegeDTO> fetchByRestResourceId(FetchByIdDTO restResourceId);
}
