package com.tz.shiro_06.realm;

import java.sql.Connection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.tz.shiro_06.dao.UserDao;
import com.tz.shiro_06.entity.User;
import com.tz.shiro_06.util.DbUtil;

/**
 * 自定义的 Realm
 *		就是把 当前的这个用户 赋值给 shiro
 *	然后在把当前这个 用户所拥有的 角色 及 权限 也赋值给 shiro
 *
 *	这个 自定义Realm 写完了，还需要配置下，shiro.ini ,就是把它配置进 shiro 里面去
 *	因为 shiro 一启动程序是从 shiro.ini 配置文件里面读取它所需要的数据。
 *	看 ********************* 的地方。
 */
public class MyRealm extends AuthorizingRealm{

	private UserDao userDao=new UserDao();
	private DbUtil dbUtil=new DbUtil();
	
	/**
	 * 为当前登录的用户授予角色和权限
	 * 
	 * PrincipalCollection 用户的 信息，就是封装到这个里面的。
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 它就可以获取用户信息了。
		String userName=(String)principals.getPrimaryPrincipal();
		// 用户谁的一些东东。
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		Connection con=null;
		try{
			// 获取数据库连接。
			con=dbUtil.getCon();
			// 获取这个用户有哪些角色，在赋值给 shiro
			authorizationInfo.setRoles(userDao.getRoles(con,userName));
			// 获取这个用户有哪些权限，在赋值给 shiro
			authorizationInfo.setStringPermissions(userDao.getPermissions(con,userName));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return authorizationInfo;
	}

	/**
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取当前用户信息。
		String userName=(String)token.getPrincipal();
		Connection con=null;
		try{
			con=dbUtil.getCon();
			User user=userDao.getByUserName(con, userName);
			if(user!=null){
				// 封装一个认证信息。
				AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),"xx");
				return authcInfo;
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
