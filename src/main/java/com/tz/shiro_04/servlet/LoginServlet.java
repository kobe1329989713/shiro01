package com.tz.shiro_04.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;


public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 请求 登录页面的URL
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("login doget");
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	// 登录过程的 URL
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("login dopost");
		String userName=req.getParameter("userName");
		String password=req.getParameter("password");
		// 一个身分验证。就是一个获取当前身分。
		Subject subject=SecurityUtils.getSubject();
		// UsernamePasswordToken 登录调用的都是 shiro.ini 这个里的配置信息哪些。以后这些东东，都是要从数据库里面查询出来的。
		UsernamePasswordToken token=new UsernamePasswordToken(userName, password);
		
		try{
			subject.login(token);	
			resp.sendRedirect("success.jsp");
		}catch(Exception e){
			e.printStackTrace();
			req.setAttribute("errorInfo", "用户名或者密码错误");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
	
	

}
