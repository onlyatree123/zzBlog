package com.zz.blog.base.vo;

import com.zz.blog.base.util.PageInfo;

import java.io.Serializable;

public abstract class BaseVo implements Serializable{
	protected static final long serialVersionUID = -1373760761780840081L;

	protected Long id;
	private Boolean countFlag;	//true:只做count查询
	//分页信息
	private PageInfo pageInfo;
		
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getCountFlag() {
		return countFlag;
	}

	public void setCountFlag(Boolean countFlag) {
		this.countFlag = countFlag;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
}
