package com.fuwu.blog;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import com.fuwu.blog.dto.response.RoleDTO;
import com.fuwu.blog.dto.response.UserDTO;
import com.fuwu.blog.service.PrivilegeService;
import com.fuwu.blog.service.RestResourceService;


@SpringBootTest
public class RedisTest {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@Autowired
	PrivilegeService privilegeService;
	
	@Autowired
	RestResourceService restResourceService;
	
	@Test
	public void test() {
		System.out.println("111111111111111");
		UserDTO  userDTO=new UserDTO();
		userDTO.setId(1);
		userDTO.setLoginName("fuwu03");
		userDTO.setEmail("123@123.com");
		RoleDTO roleDTO=new RoleDTO();
		roleDTO.setId(1);
		roleDTO.setName("超级管员3");
		RoleDTO roleDTO2=new RoleDTO();
		roleDTO2.setId(2);
		roleDTO2.setName("普通用户3");
		List<RoleDTO> roleDTOs=new ArrayList<>();
		roleDTOs.add(roleDTO);
		roleDTOs.add(roleDTO2);
		userDTO.setRoles(roleDTOs);
		String key="users:"+userDTO.getId();
		String key2="str01";
		List<String> keys=new ArrayList<>();
		keys.add(key);
		keys.add(key2);
	   redisTemplate.delete(keys);
		redisTemplate.opsForValue().set(key, userDTO);
		redisTemplate.opsForValue().set(key2, "hello redis");
		UserDTO newUser=(UserDTO)redisTemplate.opsForValue().get(key);
		System.out.println(newUser);
		
	}
	@Test
	public void cacheTest() {
//		privilegeService.fetchResourcePrivilegeMap();
		restResourceService.addPrivilegeToRestResource(9, 9);
	}
	

}
