package com.fuwu.blog.model.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.fuwu.blog.dto.param.FetchRestResourceDTO;
import com.fuwu.blog.dto.response.RestResourceDTO;
import com.fuwu.blog.model.entity.RestResource;

@Mapper
public interface RestResourceMapper extends BaseCRUDMapper<RestResource,RestResourceDTO,FetchRestResourceDTO> {

}
