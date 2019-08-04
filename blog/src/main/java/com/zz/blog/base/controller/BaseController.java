package com.zz.blog.base.controller;

import com.zz.blog.base.constants.BusinessStatus;
import com.zz.blog.base.constants.MobileKey;
import com.zz.blog.base.constants.SysConstants;
import com.zz.blog.base.rest.RestException;
import com.zz.blog.base.util.JsonMapper;
import com.zz.blog.base.util.PageInfo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;

@Component
public class BaseController {
	protected static JsonMapper mapper = new JsonMapper();

	public static Map<String,String> GetSuccMap(){
		Map<String,String> map = Maps.newHashMap();
        map.put(MobileKey.CODE, BusinessStatus.OK);
		return map;
	}
	public static Map<String,Object> GetSuccMap(Long id){
		Map<String,Object> map = Maps.newHashMap();
		map.put(MobileKey.CODE, BusinessStatus.OK);
		map.put("id", id);
		return map;
	}

//
//	/**
//	 * 取出Shiro中的当前用户真实Id.
//	 */
//	public static Long getLoginUserId() {
//		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
//		if(user==null) return null;
//		return user.id;
//	}
//
//
//	/**
//	 * 取出Shiro中的当前用户显示姓名.
//	 * 当前用户为秘书且代理功能打开时，返回领导id
//	 */
//	public static String getCurrentUserName() {
//		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
//		if(user==null) return null;
//		if(isAgency()){
//			return user.secName;
//		}
//		return user.aliasName;
//	}
//
//	/**
//	 * 取出Shiro中的当前用户真实姓名.
//	 */
//	public static String getLoginUserName() {
//		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
//		if(user==null) return null;
//		return user.loginName;
//	}
//
//    public static void setAliasName(String aliasName) {
//        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
//        if(user==null) return;
//        user.aliasName = aliasName;
//    }
//

//	/**
//	 * 判断当前用户是否有某些角色.
//	 * @param role
//	 * @return
//	 */
//	public static boolean hasRole(String role){
//		return SecurityUtils.getSubject().hasRole(role);
//	}
//
//	/**
//	 * 判断当前用户是否有某些角色.
//	 * @param roles
//	 * @return
//	 */
//	public static boolean hasRole(List<String> roles){
//		boolean[] result = SecurityUtils.getSubject().hasRoles(roles);
//		for(boolean role : result){
//			if(role){
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public static boolean isAdmin(){
//		if(SecurityUtils.getSubject().hasRole(SysConstants.ADMINISTRATOR)){
//			return true;
//		}
//		return false;
//	}
//
//	/**
//	 * 判断当前用户是否有某些权限.
//	 * @param permission
//	 * @return
//	 */
//	public static boolean hasPermission(String permission){
//		return SecurityUtils.getSubject().isPermitted(permission);
//	}

//
//	public static String getCurrentRole(){
//		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
//		if(user==null) return null;
//		return user.getRoleNames();
//	}
//
//	public static String getUserImgUrl(){
//		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
//		if(user==null) return null;
//		return user.getUserImgUrl();
//	}

	protected void writer(HttpServletResponse res, String obj) throws Exception {
		res.setCharacterEncoding("UTF-8");
		PrintWriter pw=null;
		try {
			pw = res.getWriter();
			pw.print(obj);
		} catch (Exception e) {
			throw e;
		}finally{
			if(null!=pw){
				pw.flush();
				pw.close();
			}
		}
	}

	public static void throwException(String code,String msg) {
		Map<String,String> result = Maps.newHashMap();
		result.put("code", code);
		result.put("msg", msg);
		throw new RestException(result);
	}

	public static String getClientIp(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
//        System.out.println("---ip1="+ip);
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
//            System.out.println("---ip2="+ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
//            System.out.println("---ip3="+ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
//            System.out.println("---ip4="+ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
//            System.out.println("---ip5="+ip);
		}
		if(ip.contains(",")){
			ip = ip.substring(0,ip.indexOf(","));
		}


		return ip;
	}
	public static String getClientHost(HttpServletRequest request){
		//visionet.findest.com
		return request.getHeader("Host");
	}

//	public static String getClientHost(String subdomain){
//		String organization = PropsUtil.getProperty(PropsKeys.ORGANIZATION_DOMAIN);
//		if(organization.startsWith("www.")){
//			organization = organization.replaceFirst("www",subdomain);
//		}else {
//			organization = subdomain+"."+organization;
//		}
//		return organization;
//	}
//
//	public static String getCurrentOrgDomain() {
//		Long orgId = BaseController.getCurrentOrgId();
//		return UserCache.getOrgDomain(orgId);
//	}


	protected static <T> Page<T> GetPageByList(PageInfo pageInfo, List<T> volist, Class<T> destinationClass){
		if(volist == null || pageInfo == null){
			return null;
		}

		if(volist.isEmpty()){
			return new PageImpl<T>(volist, PageRequest.of(
					pageInfo.getCurrentPageNumber(), 0, new Sort(
							pageInfo.getSortType(), pageInfo.getSortColumn())),
					0);
		}else{
			return new PageImpl<T>(volist, PageRequest.of(
					pageInfo.getCurrentPageNumber(), pageInfo.getPageSize(), new Sort(
							pageInfo.getSortType(), pageInfo.getSortColumn())),
					pageInfo.getTotalCount());
		}

	}

	protected static <T> Page<T> GetPageByList(Page<?> page,List<T> volist,Class<T> destinationClass){
		return new PageImpl<T>(volist, PageRequest.of(page.getNumber(),
				page.getSize(), page.getSort()), page.getTotalElements());
	}
}
