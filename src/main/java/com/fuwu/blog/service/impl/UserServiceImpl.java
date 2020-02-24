package com.fuwu.blog.service.impl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fuwu.blog.dto.param.FetchByIdDTO;
import com.fuwu.blog.dto.param.FetchUserDTO;
import com.fuwu.blog.dto.param.RegisterDTO;
import com.fuwu.blog.dto.param.ResetPwdDTO;
import com.fuwu.blog.dto.param.UpdateUserDTO;
import com.fuwu.blog.dto.response.PrivilegeDTO;
import com.fuwu.blog.dto.response.RoleDTO;
import com.fuwu.blog.dto.response.UserDTO;
import com.fuwu.blog.exception.AppException;
import com.fuwu.blog.model.entity.Privilege;
import com.fuwu.blog.model.entity.User;
import com.fuwu.blog.model.entity.UserRole;
import com.fuwu.blog.model.mapper.RoleMapper;
import com.fuwu.blog.model.mapper.UserMapper;
import com.fuwu.blog.security.AppUserDetails;
import com.fuwu.blog.service.UserService;
import com.fuwu.blog.util.WebUtil;

@Service
public class UserServiceImpl extends BaseCRUDServiceImpl<User,RegisterDTO ,UpdateUserDTO,FetchUserDTO,UserDTO,UserMapper> implements UserService ,UserDetailsService{
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	RoleMapper roleMapper;
	
	@Override
	public UserDTO create(RegisterDTO dto) {
//		if(!dto.getLoginPwd().equals(dto.getConfirmPwd())){
//			throw new AppException("确认密码和密码不一致");
//		}
//		User user=new User();
//		user.setLoginName(dto.getLoginName());
//		user.setLoginPwd(passwordEncoder.encode(dto.getLoginPwd()));
//		user.setEmail(dto.getEmail());
//		return create(user);
		return null;
	}
	@Override
	public void resetPwd(Integer userId,ResetPwdDTO resetPwdDTO) {
		if(!resetPwdDTO.getLoginPwd().equals(resetPwdDTO.getConfirmPwd())){
			throw new AppException("确认密码和密码不一致");
		}
		mapper.resetPwd(userId, resetPwdDTO.getLoginPwd());
	}

	@Override
	public User fetchByLoginName(String loginName) {
		User user=mapper.fetchByLoginName(loginName);
		return user;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=fetchByLoginName(username);
		if(null==user) {
			throw new UsernameNotFoundException("该用户不存在");
		}
		Set<String> privilegeCodes=fetchAssignedPrivilegeCodes(user.getId());
		return new AppUserDetails(user,privilegeCodes);
		
	}
	
	private Set<String> fetchAssignedPrivilegeCodes(Integer userId) {
		List<PrivilegeDTO> userPrivileges=new ArrayList<>();
		List<PrivilegeDTO> userGroupPrivileges=new ArrayList<>();
		//TODO load privileges
		UserDTO userDTO=fetchById(userId);
		List<RoleDTO> roles=userDTO.getRoles();
		if(null!=roles) {
			for(RoleDTO roleDTO:roles) {
				List<PrivilegeDTO> privilegeDTOs=roleDTO.getPrivileges();
				if(null!=privilegeDTOs) {
					userPrivileges.addAll(privilegeDTOs);
				}
			}
		}
		Set<String> set=new HashSet<>();
		if(null!=userPrivileges) {
			for(PrivilegeDTO privilege:userPrivileges) {
				set.add(privilege.getCode());
			}
		}
		if(null!=userGroupPrivileges) {
			for(PrivilegeDTO privilege:userGroupPrivileges) {
				set.add(privilege.getCode());
			}
		}
		return set;
	}

	@Override
	public List<UserDTO> fetchByGroupId(FetchByIdDTO userGroupId) {
		return mapper.fetchByGroupId(userGroupId);
	}

	@Override
	public List<UserDTO> fetchByDepartmentId(FetchByIdDTO departmentId) {
		return mapper.fetchByDepartmentId(departmentId);
	}
	@Override
	public UserDTO addRoleToUser(Integer roleId, Integer userId) {
		UserRole userRole=new UserRole();
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);
		userRole.setCreatedBy(WebUtil.getLoginUserId());
		userRole.setUpdatedBy(WebUtil.getLoginUserId());
		roleMapper.addRoleToUser(userRole);
		return mapper.fetchById(userId);
		
	}

	@Override
	public UserDTO removeRoleFromUser(Integer roleId, Integer userId) {
		UserRole userRole=new UserRole();
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);
		roleMapper.removeRoleFromUser(userRole);
		return mapper.fetchById(userId);
	}

}
