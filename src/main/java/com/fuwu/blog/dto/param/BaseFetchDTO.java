package com.fuwu.blog.dto.param;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class BaseFetchDTO extends PageParamDTO{
	@ApiModelProperty("创建时间_开始")
	private Date createdStart;
	@ApiModelProperty("创建时间_结束")
	private Date createdEnd;
	@ApiModelProperty("更新时间_开始")
	private Date updatedStart;
	@ApiModelProperty("更新时间_结束")
	private Date updatedEnd;
	public Date getCreatedStart() {
		return createdStart;
	}
	public void setCreatedStart(Date createdStart) {
		this.createdStart = createdStart;
	}
	public Date getCreatedEnd() {
		return createdEnd;
	}
	public void setCreatedEnd(Date createdEnd) {
		this.createdEnd = createdEnd;
	}
	public Date getUpdatedStart() {
		return updatedStart;
	}
	public void setUpdatedStart(Date updatedStart) {
		this.updatedStart = updatedStart;
	}
	public Date getUpdatedEnd() {
		return updatedEnd;
	}
	public void setUpdatedEnd(Date updatedEnd) {
		this.updatedEnd = updatedEnd;
	}
}
