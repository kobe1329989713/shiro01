package com.tz.shiro_03.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
/**
 * 通用类
 */
public class ShiroUtil {
	/**
	 * 把 验证 封装一个通用方法。
	 * @param configFile 获取哪个 xx.ini 配置文件里面的东东
	 * @param userName 用户名
	 * @param password 密码
	 * @return
	 */
	public static Subject login(String configFile,String userName,String password){
		// 读取配置文件，初始化SecurityManager工厂
		Factory<SecurityManager> factory=new IniSecurityManagerFactory(configFile);
		// 获取securityManager实例
		SecurityManager securityManager=factory.getInstance();
		// 把securityManager实例绑定到SecurityUtils
		SecurityUtils.setSecurityManager(securityManager);
		// 得到当前执行的用户
		Subject currentUser=SecurityUtils.getSubject();
		// 创建token令牌，用户名/密码
		UsernamePasswordToken token=new UsernamePasswordToken(userName, password);
		try{
			// 身份认证
			currentUser.login(token);	
			System.out.println("身份认证成功！====");
		}catch(AuthenticationException e){
			e.printStackTrace();
			System.out.println("身份认证失败！=====");
		}
		// 哪个调用完，就在哪里退出。
		return currentUser;
	}
}
