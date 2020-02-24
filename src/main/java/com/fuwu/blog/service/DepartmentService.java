package com.fuwu.blog.service;

import java.util.List;

import com.fuwu.blog.dto.param.CreateDepartmentDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchDepartmentDTO;
import com.fuwu.blog.dto.param.UpdateDepartmentDTO;
import com.fuwu.blog.dto.response.DepartmentDTO;
import com.fuwu.blog.model.entity.Department;

public interface DepartmentService extends BaseCRUDService<Department,CreateDepartmentDTO,UpdateDepartmentDTO,FetchDepartmentDTO,DepartmentDTO>{
	
	public void addUserToDepartment(Integer userId,Integer departmentId);
	public void removeUserFromDepartment(Integer userId,Integer departmentId);
	public List<DepartmentDTO> fetchChildren(FetchByIdDTO parentId);
	public void addChildDepartment(Integer childId,Integer parentId);
	public void removeChildDepartment(Integer childId);
	
	
}
