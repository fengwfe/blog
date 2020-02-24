package com.fuwu.blog.model.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchDepartmentDTO;
import com.fuwu.blog.dto.response.DepartmentDTO;
import com.fuwu.blog.model.entity.Department;
import com.fuwu.blog.model.entity.DepartmentUser;

@Mapper
public interface DepartmentMapper extends BaseCRUDMapper<Department,DepartmentDTO,FetchDepartmentDTO> {
	public void addUserToDepartment(DepartmentUser departmentUser);
	public void removeUserFromDepartment(DepartmentUser departmentUser);
	public List<DepartmentDTO> fetchChildren(FetchByIdDTO parentId);
	public void addChildDepartment(Department department);
	public void removeChildDepartment(Department department);

}
