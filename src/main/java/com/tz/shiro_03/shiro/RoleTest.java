package com.tz.shiro_03.shiro;

import java.util.Arrays;

import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.tz.shiro_03.common.ShiroUtil;

/**
 * 基于 角色的判断
 *
 */
public class RoleTest {

	/**
	 *  基于 角色的判断、之hasRole() 这三个重载的方法测试
	 */
	@Test
	public void testHasRole() {
		// 先登录。
		Subject currentUser = ShiroUtil.login("classpath:shiro3/shiro_role.ini", "java1234", "123456");
		// 第二个用户登录。
		// Subject currentUser=ShiroUtil.login("classpath:shiro_role.ini",
		// "jack", "123");

		// 判断 此用户是否拥有这个 角色
		System.out.println(currentUser.hasRole("role1") ? "有role1这个角色" : "没有role1这个角色");

		// 判断 此用户是否拥有这些 角色
		boolean[] results = currentUser.hasRoles(Arrays.asList("role1", "role2", "role3"));
		System.out.println(results[0] ? "有role1这个角色" : "没有role1这个角色");
		System.out.println(results[1] ? "有role2这个角色" : "没有role2这个角色");
		System.out.println(results[2] ? "有role3这个角色" : "没有role3这个角色");

		// 要拥有传入的所有的这些角色，才返回true，否则都是false
		System.out.println(currentUser.hasAllRoles(Arrays.asList("role1", "role2")) ? "role1,role2这两个角色都有"
				: "role1,role2这个两个角色不全有");

		// 退出。
		currentUser.logout();

		// 可以在用第二个用户来测试 测试。
	}

	/**
	 * checkRole() 这三个方法和上面哪个三个方法差不多，但是它是没有返回值，如果没有这人角色 它就直接 抛出
	 *   org.apache.shiro.authz.UnauthorizedException: Subject does not have role [role1xxxx]
	 * 这个异常
	 * 
	 */
	@Test
	public void testCheckRole() {
		Subject currentUser = ShiroUtil.login("classpath:shiro3/shiro_role.ini", "java1234", "123456");
		// Subject currentUser=ShiroUtil.login("classpath:shiro_role.ini",
		// "jack", "123");
		currentUser.checkRole("role1xxxx");
//		currentUser.checkRoles(Arrays.asList("role1", "role2"));
//		currentUser.checkRoles("role1", "role2", "role3");

		currentUser.logout();
	}
}








