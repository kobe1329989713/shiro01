package com.tz.shiro_06.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;


public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("login doget");
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("login dopost");
		String userName=req.getParameter("userName");
		String password=req.getParameter("password");
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(userName, password);
		try{
			// 记住我，存入到 cooki 中去。isRemembered()是否被记住了。
//			if(subject.isRemembered()){
//				System.out.println("---isRememberMe---");
//			}else{
				// 为ture 就记住了。
//				token.setRememberMe(true);				
//			}
			
			subject.login(token);
			Session session=subject.getSession();
			System.out.println("sessionId:"+session.getId());
			System.out.println("sessionHost:"+session.getHost());
			System.out.println("sessionTimeout:"+session.getTimeout());
			session.setAttribute("info", "session的数据");
			resp.sendRedirect("success.jsp");
		}catch(Exception e){
			e.printStackTrace();
			req.setAttribute("errorInfo", "用户名或者密码错误");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
	
	

}
