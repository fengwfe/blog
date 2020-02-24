package com.fuwu.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fuwu.blog.dto.ApiResponse;
import com.fuwu.blog.dto.param.CreateRestResourceDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchRestResourceDTO;
import com.fuwu.blog.dto.param.UpdateRestResourceDTO;
import com.fuwu.blog.dto.response.PrivilegeDTO;
import com.fuwu.blog.dto.response.RestResourceDTO;
import com.fuwu.blog.dto.response.UserDTO;
import com.fuwu.blog.model.mapper.PrivilegeMapper;
import com.fuwu.blog.service.BaseCRUDService;
import com.fuwu.blog.service.PrivilegeService;
import com.fuwu.blog.service.RestResourceService;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/restResources")
@Api(tags = "rest接口资源", description = "API接口")
public class RestResourceController extends BaseCRUDController<CreateRestResourceDTO, UpdateRestResourceDTO, FetchRestResourceDTO, RestResourceDTO> {

	@Autowired
	RestResourceService restResourceService;
	
	@Autowired
	PrivilegeService privilegeService;
	
	@ApiOperation("添加权限到rest接口资源")
	@PostMapping("/{restResourceId}/privileges")
	public ApiResponse<RestResourceDTO> addPrivilegeToRestResource(@ApiParam("rest接口资源id") @PathVariable("restResourceId")Integer restResourceId,@ApiParam("权限id")@RequestBody Integer privilegeId){
		RestResourceDTO dto=restResourceService.addPrivilegeToRestResource(privilegeId, restResourceId);
		return new ApiResponse<RestResourceDTO>(dto);
		
	}
	@ApiOperation("从rest接口资源中删除权限")
	@DeleteMapping("/{restResourceId}/privileges/{privilegeId}")
	public ApiResponse<RestResourceDTO> removePrivilegeFromRestResource(@ApiParam("rest接口资源id") @PathVariable("restResourceId")Integer restResourceId,@ApiParam("权限id")@PathVariable("privilegeId") Integer privilegeId){
		RestResourceDTO dto=restResourceService.removePrivilegeFromRestResource(privilegeId, restResourceId);
		return new ApiResponse<RestResourceDTO>(dto);
	}
	@ApiOperation("获取特定rest接口资源的权限信息")
	 @GetMapping("/{restResourceId}/privileges")
	 public ApiResponse<PageInfo<PrivilegeDTO>> fetchPrivilegesByRestResourceId(@ApiParam("rest接口资源id")@PathVariable("restResourceId") Integer restResourceId,FetchByIdDTO pageParams){
		pageParams.setId(restResourceId);
		List<PrivilegeDTO> list=privilegeService.fetchByRestResourceId(pageParams);
		PageInfo<PrivilegeDTO> pageInfo=new PageInfo<>(list);
		return new ApiResponse<PageInfo<PrivilegeDTO>>(pageInfo);
	 }
	@Override
	public BaseCRUDService getService() {
		return restResourceService;
	}

	
}
