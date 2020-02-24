package com.fuwu.blog.service.impl;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuwu.blog.dto.param.BaseCreateDTO;
import com.fuwu.blog.dto.param.BaseFetchDTO;
import com.fuwu.blog.dto.param.BaseUpateDTO;
import com.fuwu.blog.dto.response.BaseResponseDTO;
import com.fuwu.blog.model.entity.BaseEntity;
import com.fuwu.blog.model.mapper.BaseCRUDMapper;
import com.fuwu.blog.service.BaseCRUDService;

@Service
public abstract class BaseCRUDServiceImpl<ENTITY extends BaseEntity,CreatedDTO extends BaseCreateDTO,UpdateDTO extends BaseUpateDTO,FetchDTO extends BaseFetchDTO,ResponseDTO extends BaseResponseDTO,MAPPER extends BaseCRUDMapper<ENTITY,ResponseDTO,FetchDTO>> implements BaseCRUDService<ENTITY,CreatedDTO,UpdateDTO,FetchDTO,ResponseDTO>{

	@Autowired
	MAPPER mapper;

	@Override
	public ResponseDTO fetchById(Integer id) {
		return mapper.fetchById(id);
	}

	@Override
	public List<ResponseDTO> fetch(FetchDTO fetchDTO) {
		return mapper.fetch(fetchDTO);
	}

	@Override
	public ResponseDTO create(ENTITY entity) {
		entity.setCreatedBy(1);
		entity.setUpdatedBy(1);
		mapper.create(entity);
		return fetchById(entity.getId());
	}

	@Override
	public ResponseDTO update(ENTITY entity) {
		entity.setUpdatedBy(1);
		mapper.update(entity);
		return fetchById(entity.getId());
	}

	@Override
	public ResponseDTO save(ENTITY entity) {
		if(entity.isNew()){
			return create(entity);
		}else {
			return update(entity);
		}
	}

	@Override
	public int deleteById(Integer id) {
		return mapper.deleteById(id);
	}

	@Override
	public int batchDeleteByIds(List<Integer> ids) {
		return mapper.batchDeleteByIds(ids);
	}

	@Override
	public ResponseDTO create(CreatedDTO dto) {
		ENTITY entity=toEntity(dto);
		return create(entity);
	}

	@Override
	public ResponseDTO update(Integer id, UpdateDTO dto) {
		ENTITY entity=toEntity(dto);
		entity.setId(id);
		return update(entity);
	}
	
	protected ENTITY toEntity(Object dto) {
		ParameterizedType parameterizedType=(ParameterizedType)this.getClass().getGenericSuperclass();
		Type[] types=parameterizedType.getActualTypeArguments();
		Class entityClass=(Class)types[0];
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return (ENTITY)modelMapper.map(dto, entityClass);
		
	}
}
