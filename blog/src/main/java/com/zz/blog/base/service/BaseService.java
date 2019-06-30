package com.zz.blog.base.service;

import com.google.common.collect.Maps;
import com.zz.blog.base.constants.MobileKey;
import com.zz.blog.base.rest.RestException;
import com.zz.blog.base.util.JsonMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.List;
import java.util.Map;

public abstract class BaseService {
	protected static JsonMapper mapper = new JsonMapper();

	/**
	 * 创建分页请求.
	 */
	protected PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)||"id".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if(sortType!=null) {
			sort = new Sort(Direction.ASC, sortType);
		}

		return PageRequest.of(pageNumber - 1, pagzSize, sort);
	}
	
	protected PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType,String directionStr) {
		Direction direction = Direction.DESC;
		if(directionStr!=null&&"asc".equals(directionStr.toLowerCase())){
			direction = Direction.ASC;
		}
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(direction, "id");
		} else if(sortType!=null) {
			sort = new Sort(direction, sortType);
		}

		return PageRequest.of(pageNumber - 1, pagzSize, sort);
	}
	
	protected PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType,Direction direction) {
		
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(direction, "id");
		} else if(sortType!=null) {
			sort = new Sort(direction, sortType);
		}

		return PageRequest.of(pageNumber - 1, pagzSize, sort);
	}
	
	protected PageRequest buildPageRequest(int pageNumber, int pagzSize, List<Order> orders) {
		Sort sort = new Sort(orders);
	
		return PageRequest.of(pageNumber - 1, pagzSize, sort);
	}

	protected static void throwException(String code,String msg){
		Map<String,String> response = Maps.newHashMap();
		response.put(MobileKey.CODE, code);
		response.put(MobileKey.MSG, msg);
		throw new RestException(response);
	}

	protected static <T> Page<T> GetPageByList(Page<?> page, List<T> volist, Class<T> destinationClass){
		return new PageImpl<T>(volist, PageRequest.of(page.getNumber(), page.getSize(), page.getSort()), page.getTotalElements());
	}
}
