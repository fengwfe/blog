package com.fuwu.blog.service.impl;

import org.springframework.stereotype.Service;

import com.fuwu.blog.dto.param.CreateTokenInvalidDTO;
import com.fuwu.blog.dto.param.FetchTokenInvalidDTO;
import com.fuwu.blog.dto.param.UpdateTokenInvalidDTO;
import com.fuwu.blog.dto.response.TokenInvalidDTO;
import com.fuwu.blog.model.entity.TokenInvalid;
import com.fuwu.blog.model.mapper.TokenInvalidMapper;
import com.fuwu.blog.service.TokenInvalidService;
@Service
public class TokenInvalidServiceImpl extends BaseCRUDServiceImpl<TokenInvalid, CreateTokenInvalidDTO,UpdateTokenInvalidDTO, FetchTokenInvalidDTO,TokenInvalidDTO, TokenInvalidMapper> implements TokenInvalidService{

	@Override
	public TokenInvalidDTO create(TokenInvalid entity) {
		mapper.deleteAllByUserId(entity.getUserId());
		return super.create(entity);
	}

	@Override
	public TokenInvalidDTO fetchByUserId(Integer userId) {
		return mapper.fetchByUserId(userId);
	}

	@Override
	public TokenInvalidDTO  invalidTokenByUserId(Integer userId) {
		TokenInvalid tokenInvalid=new TokenInvalid();
		tokenInvalid.setUserId(userId);
		return create(tokenInvalid);
	}
	
	
}
