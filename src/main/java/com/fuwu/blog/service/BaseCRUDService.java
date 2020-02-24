package com.fuwu.blog.service;

import java.util.List;

import com.fuwu.blog.dto.param.BaseCreateDTO;
import com.fuwu.blog.dto.param.BaseFetchDTO;
import com.fuwu.blog.dto.param.BaseUpateDTO;
import com.fuwu.blog.dto.response.BaseResponseDTO;
import com.fuwu.blog.model.entity.BaseEntity;

/**
 * @author fuwu
 * 单表CRUD
 *
 */

public interface BaseCRUDService<ENTITY extends BaseEntity,CreatedDTO extends BaseCreateDTO,UpdateDTO extends BaseUpateDTO,FetchDTO extends BaseFetchDTO,ResponseDTO extends BaseResponseDTO>{
	public ResponseDTO fetchById(Integer id);
	public List<ResponseDTO> fetch(FetchDTO fetchDTO);
	public ResponseDTO create(ENTITY entity);
	public ResponseDTO update(ENTITY entity);
	public ResponseDTO save(ENTITY entity);
	public int deleteById(Integer id);
	public int batchDeleteByIds(List<Integer> ids);
	public ResponseDTO create(CreatedDTO dto);
	public ResponseDTO update(Integer id,UpdateDTO dto);
}
