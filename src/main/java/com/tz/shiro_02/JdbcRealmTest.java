package com.tz.shiro_02;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
/**
 * 是从数据库里面的数据了，
 *
 */
public class JdbcRealmTest {

	public static void main(String[] args) {
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro2/jdbc_realm.ini");
		SecurityManager securityManager=factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject currentUser=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken("java1234", "123456");
		try{
			currentUser.login(token);	
			System.out.println("身份认证成功！===========");
		}catch(AuthenticationException e){
			e.printStackTrace();
			System.out.println("身份认证失败！============");
		}
		// 退出
		currentUser.logout();
	}
}
