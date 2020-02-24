package com.fuwu.blog.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fuwu.blog.dto.param.CreateDepartmentDTO;
import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchDepartmentDTO;
import com.fuwu.blog.dto.param.UpdateDepartmentDTO;
import com.fuwu.blog.dto.response.DepartmentDTO;
import com.fuwu.blog.model.entity.Department;
import com.fuwu.blog.model.entity.DepartmentUser;
import com.fuwu.blog.model.mapper.DepartmentMapper;
import com.fuwu.blog.service.DepartmentService;
import com.fuwu.blog.util.WebUtil;
@Service
public class DepartmentServiceImpl extends BaseCRUDServiceImpl<Department,CreateDepartmentDTO,UpdateDepartmentDTO,FetchDepartmentDTO,DepartmentDTO, DepartmentMapper> implements DepartmentService{

	@Override
	public void addUserToDepartment(Integer userId, Integer departmentId) {
		DepartmentUser departmentUser=new DepartmentUser();
		departmentUser.setUserId(userId);
		departmentUser.setDepartmentId(departmentId);
		departmentUser.setCreatedBy(WebUtil.getLoginUserId());
		departmentUser.setUpdatedBy(WebUtil.getLoginUserId());
		mapper.addUserToDepartment(departmentUser);
	}

	@Override
	public void removeUserFromDepartment(Integer userId, Integer departmentId) {
		DepartmentUser departmentUser=new DepartmentUser();
		departmentUser.setUserId(userId);
		departmentUser.setDepartmentId(departmentId);
		departmentUser.setUpdatedBy(WebUtil.getLoginUserId());
		mapper.removeUserFromDepartment(departmentUser);
		
	}

	@Override
	public List<DepartmentDTO> fetchChildren(FetchByIdDTO parentId) {
		return mapper.fetchChildren(parentId);
	}

	@Override
	public void addChildDepartment(Integer childId, Integer parentId) {
		Department department=new Department();
		department.setId(childId);
		department.setParentId(parentId);
		mapper.addChildDepartment(department);
	}

	@Override
	public void removeChildDepartment(Integer childId) {
		Department department=new Department();
		department.setId(childId);
		mapper.removeChildDepartment(department);
	}

}
