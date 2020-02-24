package com.fuwu.blog.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fuwu.blog.model.entity.User;

public class AppUserDetails implements UserDetails{
	
	private Integer id;
	private String loginName;
	private String loginPwd;
	private Set<String> privilegeCodes;
	
	public  AppUserDetails(User user,Set<String> privilegeCodes) {
		this.id=user.getId();
		this.loginName=user.getLoginName();
		this.loginPwd=user.getLoginPwd();
		this.privilegeCodes=privilegeCodes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities=null;
		if(null!=this.privilegeCodes) {
			authorities=new ArrayList<>();
			for(String code:this.privilegeCodes) {
				SimpleGrantedAuthority authority=new SimpleGrantedAuthority(code);
				authorities.add(authority);
			}
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.loginPwd;
	}

	@Override
	public String getUsername() {
		return this.loginName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Integer getId() {
		return id;
	}
	

}
