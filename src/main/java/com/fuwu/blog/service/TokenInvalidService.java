package com.fuwu.blog.service;

import com.fuwu.blog.dto.param.CreateTokenInvalidDTO;
import com.fuwu.blog.dto.param.FetchTokenInvalidDTO;
import com.fuwu.blog.dto.param.UpdateTokenInvalidDTO;
import com.fuwu.blog.dto.response.TokenInvalidDTO;
import com.fuwu.blog.model.entity.TokenInvalid;

public interface TokenInvalidService extends BaseCRUDService<TokenInvalid, CreateTokenInvalidDTO,UpdateTokenInvalidDTO, FetchTokenInvalidDTO,TokenInvalidDTO>{
	public TokenInvalidDTO fetchByUserId(Integer userId);
	public TokenInvalidDTO invalidTokenByUserId(Integer userId);
}
