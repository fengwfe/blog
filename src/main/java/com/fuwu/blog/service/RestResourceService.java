package com.fuwu.blog.service;
import com.fuwu.blog.dto.param.CreateRestResourceDTO;
import com.fuwu.blog.dto.param.FetchRestResourceDTO;
import com.fuwu.blog.dto.param.UpdateRestResourceDTO;
import com.fuwu.blog.dto.response.RestResourceDTO;
import com.fuwu.blog.model.entity.RestResource;

public interface RestResourceService extends BaseCRUDService<RestResource,CreateRestResourceDTO,UpdateRestResourceDTO,FetchRestResourceDTO,RestResourceDTO>{
	public RestResourceDTO addPrivilegeToRestResource(Integer privilegeId,Integer restResourceId);
	public RestResourceDTO removePrivilegeFromRestResource(Integer privilegeId,Integer restResourceId);
}
