package com.fuwu.blog.model.mapper;

import java.util.List;

import com.fuwu.blog.dto.param.BaseFetchDTO;
import com.fuwu.blog.dto.response.BaseResponseDTO;
import com.fuwu.blog.model.entity.BaseEntity;

public interface BaseCRUDMapper<ENTITY extends BaseEntity,ResponseDTO extends BaseResponseDTO,FetchDTO extends BaseFetchDTO> {
	public ResponseDTO fetchById(Integer id);
	public List<ResponseDTO> fetch(FetchDTO fetchDTO);
	public int create(ENTITY entity);
	public int update(ENTITY entity);
	public int deleteById(Integer id);
	public int batchDeleteByIds(List<Integer> ids);

}
