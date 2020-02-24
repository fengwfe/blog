package com.fuwu.blog.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class PageParamDTO {
	  //分页参数
	@ApiModelProperty("分页参数_第几页")
    private Integer pageNum=1;
	@ApiModelProperty("分页参数_每页条数")
    private Integer pageSize=2;
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public void nonPagination() {
		this.pageSize=0;
	}
}
