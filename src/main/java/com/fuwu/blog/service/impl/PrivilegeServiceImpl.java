package com.fuwu.blog.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fuwu.blog.constant.CacheConstant;
import com.fuwu.blog.dto.param.CreatePrivilegeDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchPrivilegeDTO;
import com.fuwu.blog.dto.param.FetchRestResourceDTO;
import com.fuwu.blog.dto.param.UpdatePrivilegeDTO;
import com.fuwu.blog.dto.response.PrivilegeDTO;
import com.fuwu.blog.dto.response.RestResourceDTO;
import com.fuwu.blog.model.entity.Privilege;
import com.fuwu.blog.model.mapper.PrivilegeMapper;
import com.fuwu.blog.model.mapper.RestResourceMapper;
import com.fuwu.blog.service.PrivilegeService;

@Service
public class PrivilegeServiceImpl extends BaseCRUDServiceImpl<Privilege,CreatePrivilegeDTO,UpdatePrivilegeDTO,FetchPrivilegeDTO,PrivilegeDTO, PrivilegeMapper> implements PrivilegeService{

	@Autowired
	RestResourceMapper restResourceMapper;
	
	
	@Cacheable(cacheNames = CacheConstant.CACHE_NAME_RESOURCE_PRIVILEGE_MAP)
	@Override
	public Map<RestResourceDTO, List<String>> fetchResourcePrivilegeMap() {
		Map<RestResourceDTO, List<String>> map=new HashMap<>();
		FetchRestResourceDTO fetchRestResourceDTO=new FetchRestResourceDTO();
		fetchRestResourceDTO.nonPagination();
		List<RestResourceDTO> resourceDTOs=restResourceMapper.fetch(fetchRestResourceDTO);
		if(null!=resourceDTOs) {
			for(RestResourceDTO restResourceDTO:resourceDTOs) {
				FetchByIdDTO fetchByIdDTO=new FetchByIdDTO();
				fetchByIdDTO.setId(restResourceDTO.getId());
				fetchByIdDTO.nonPagination();
				List<PrivilegeDTO> privilegeDTOs=mapper.fetchByRestResourceId(fetchByIdDTO);
				List<String> codes=null;
				if(null!=privilegeDTOs) {
					codes=new ArrayList<>();
					for(PrivilegeDTO privilegeDTO:privilegeDTOs) {
						codes.add(privilegeDTO.getCode());
					}
				}
				map.put(restResourceDTO, codes);
			}
		}
		return map;
	}
	@Override
	public List<PrivilegeDTO> fetchByRoleId(FetchByIdDTO roleId) {
		return mapper.fetchByRoleId(roleId);
	}

	@Override
	public List<PrivilegeDTO> fetchByRestResourceId(FetchByIdDTO restResourceId) {
		return mapper.fetchByRestResourceId(restResourceId);
	}


}
