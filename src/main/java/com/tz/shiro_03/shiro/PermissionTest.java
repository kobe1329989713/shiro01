package com.tz.shiro_03.shiro;


import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.tz.shiro_03.common.ShiroUtil;

/**
 *  基于 权限 的判断
 */
public class PermissionTest {

	@Test
	public void testIsPermitted() {
		// 登录。
		Subject currentUser=ShiroUtil.login("classpath:shiro3/shiro_permission.ini", "java1234", "123456");
		// Subject currentUser=ShiroUtil.login("classpath:shiro_permission.ini", "jack", "123");
		
		// 判断这个用户是否拥有这个权限。
		System.out.println(currentUser.isPermitted("user:select")?"有user:select这个权限":"没有user:select这个权限");
		System.out.println(currentUser.isPermitted("user:update")?"有user:update这个权限":"没有user:update这个权限");
		
		// 判断多个。
		boolean results[]=currentUser.isPermitted("user:select","user:update","user:delete");
		System.out.println(results[0]?"有user:select这个权限":"没有user:select这个权限");
		System.out.println(results[1]?"有user:update这个权限":"没有user:update这个权限");
		System.out.println(results[2]?"有user:delete这个权限":"没有user:delete这个权限");
		
		// 这些权限全部都有才返回 true。
		System.out.println(currentUser.isPermittedAll("user:select","user:update")?"有user:select,update这两个权限":"user:select,update这两个权限不全有");
		
		// 注：这个退出不要忘了。
		currentUser.logout();
	}

	
	/**
	 * checkPermission() 如果没有这个权限它就直接抛出异常。
	 *  org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [user:selectxxxx]
	 */
	@Test
	public void testCheckPermitted() {
		Subject currentUser=ShiroUtil.login("classpath:shiro3/shiro_permission.ini", "java1234", "123456");
		// Subject currentUser=ShiroUtil.login("classpath:shiro_permission.ini", "jack", "123");
		currentUser.checkPermission("user:selectxxxx");
		currentUser.checkPermissions("user:select","user:update","user:delete");
		currentUser.logout();
	}
}





