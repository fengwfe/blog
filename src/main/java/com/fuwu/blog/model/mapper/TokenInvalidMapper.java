package com.fuwu.blog.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fuwu.blog.dto.param.FetchTokenInvalidDTO;
import com.fuwu.blog.dto.response.TokenInvalidDTO;
import com.fuwu.blog.model.entity.TokenInvalid;

@Mapper
public interface TokenInvalidMapper  extends BaseCRUDMapper<TokenInvalid,TokenInvalidDTO,FetchTokenInvalidDTO> {
	public int deleteAllByUserId(Integer userId);
	public TokenInvalidDTO fetchByUserId(Integer userId);
}
