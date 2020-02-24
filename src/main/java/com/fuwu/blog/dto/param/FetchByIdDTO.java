package com.fuwu.blog.dto.param;

public class FetchByIdDTO extends PageParamDTO{
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public FetchByIdDTO() {}
	public FetchByIdDTO (PageParamDTO pageParamDTO,Integer id) {
		if(null!=pageParamDTO.getPageNum()) {
			this.setPageNum(pageParamDTO.getPageNum());
		}
		if(null!=pageParamDTO.getPageSize()) {
			this.setPageSize(pageParamDTO.getPageSize());
		}
		this.id=id;
	}
	

}
