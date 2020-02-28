package com.fuwu.blog.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.fuwu.blog.constant.CacheConstant;
import com.fuwu.blog.dto.param.CreateRestResourceDTO;
import com.fuwu.blog.dto.param.FetchRestResourceDTO;
import com.fuwu.blog.dto.param.UpdateRestResourceDTO;
import com.fuwu.blog.dto.response.RestResourceDTO;
import com.fuwu.blog.model.entity.RestResource;
import com.fuwu.blog.model.entity.RestResourcePrivilege;
import com.fuwu.blog.model.mapper.PrivilegeMapper;
import com.fuwu.blog.model.mapper.RestResourceMapper;
import com.fuwu.blog.service.RestResourceService;
import com.fuwu.blog.util.WebUtil;

@Service
public class RestResourceServiceImpl extends BaseCRUDServiceImpl<RestResource,CreateRestResourceDTO,UpdateRestResourceDTO,FetchRestResourceDTO,RestResourceDTO, RestResourceMapper> implements RestResourceService{

	@Autowired
	PrivilegeMapper privilegeMapper;
	
	
	@CacheEvict(cacheNames = CacheConstant.CACHE_NAME_RESOURCE_PRIVILEGE_MAP,allEntries = true)
	@Override
	public RestResourceDTO addPrivilegeToRestResource(Integer privilegeId, Integer restResourceId) {
		RestResourcePrivilege resourcePrivilege=new RestResourcePrivilege();
		resourcePrivilege.setPrivilegeId(privilegeId);
		resourcePrivilege.setRestResourceId(restResourceId);
		resourcePrivilege.setCreatedBy(WebUtil.getLoginUserId());
		resourcePrivilege.setUpdatedBy(WebUtil.getLoginUserId());
		privilegeMapper.addPrivilegeToRestResource(resourcePrivilege);
		return mapper.fetchById(restResourceId);
	}

	@CacheEvict(cacheNames = CacheConstant.CACHE_NAME_RESOURCE_PRIVILEGE_MAP,allEntries = true)
	@Override
	public RestResourceDTO removePrivilegeFromRestResource(Integer privilegeId, Integer restResourceId) {
		RestResourcePrivilege resourcePrivilege=new RestResourcePrivilege();
		resourcePrivilege.setPrivilegeId(privilegeId);
		resourcePrivilege.setRestResourceId(restResourceId);
		privilegeMapper.removePrivilegeFromRestResource(resourcePrivilege);
		return mapper.fetchById(restResourceId);
	}
	
}
