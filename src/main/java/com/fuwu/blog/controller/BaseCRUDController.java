package com.fuwu.blog.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.fuwu.blog.dto.ApiResponse;
import com.fuwu.blog.dto.param.BaseCreateDTO;
import com.fuwu.blog.dto.param.BaseFetchDTO;
import com.fuwu.blog.dto.param.BaseUpateDTO;
import com.fuwu.blog.dto.response.BaseResponseDTO;
import com.fuwu.blog.exception.AppException;
import com.fuwu.blog.service.BaseCRUDService;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

public abstract class BaseCRUDController<CreateDTO extends BaseCreateDTO,UpdateDTO extends BaseUpateDTO,FetchDTO extends BaseFetchDTO,ResponseDTO extends BaseResponseDTO> {
	public void handleValidationResult(BindingResult bindingResult)throws AppException {
		 if(bindingResult.hasErrors()) {
			List<ObjectError> errors= bindingResult.getAllErrors();
			for(ObjectError error:errors) {
				System.out.println(error.getCode());
				System.out.println(error.getObjectName());
				System.out.println(error.getDefaultMessage());
			}
//			throw new AppException("验证未通过");
		 }
	}
	@ApiOperation("创建对象")
	@PostMapping
	public ApiResponse<ResponseDTO> create(@Valid @RequestBody CreateDTO dto,BindingResult bindingResult){
		handleValidationResult(bindingResult);
		ResponseDTO responseDTO=(ResponseDTO)getService().create(dto);
		return new ApiResponse<ResponseDTO>(responseDTO);
	}
	@ApiOperation("更新对象")
	@PatchMapping("/{id}")
	public ApiResponse<ResponseDTO> update(@ApiParam("id") @PathVariable("id")Integer id,@RequestBody UpdateDTO dto,BindingResult bindingResult){
		handleValidationResult(bindingResult);
		ResponseDTO responseDTO=(ResponseDTO)getService().update(id,dto);
		return new ApiResponse<ResponseDTO>(responseDTO);
	}
	@ApiOperation("获取单个对象")
	@GetMapping("/{id}")
	public ApiResponse<ResponseDTO> fetchById(@ApiParam("id") @PathVariable("id")Integer id){
		ResponseDTO responseDTO=(ResponseDTO)getService().fetchById(id);
		return new ApiResponse<ResponseDTO>(responseDTO);
	 }
	@ApiOperation("搜索对象")
	 @GetMapping
	 public ApiResponse<PageInfo<ResponseDTO>> fetch(FetchDTO dto){
		List<ResponseDTO> list=getService().fetch(dto);
		PageInfo<ResponseDTO> pageInfo=new PageInfo<>(list);
		return new ApiResponse<PageInfo<ResponseDTO>>(pageInfo);
	 }
	@ApiOperation("删除单个对象")
	 @DeleteMapping("/{id}")
	 public ApiResponse deleteById(@ApiParam("id") @PathVariable("id") Integer id){
		 getService().deleteById(id);
		 return ApiResponse.SUCCESS();
	 }
	@ApiOperation("批量删除对象")
	 @PostMapping("/batchDelete")
	 public ApiResponse batchDeleteTagByIds(@ApiParam("ids") @RequestBody List<Integer> ids){
		getService().batchDeleteByIds(ids);
		 return ApiResponse.SUCCESS();
	 }
	public abstract BaseCRUDService getService();
	

}
