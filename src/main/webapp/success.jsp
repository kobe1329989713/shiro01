<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>${info } 欢迎你!</h1>
	
	<!-- 
		注：用下面这些标签必须要引用;
		《%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %》
		才OK。
	 -->
	<!-- hasRole 标签如果当前Subject 有角色将显示body 体内容。 
		有 admin 这个角色的才进行显示。
		
		shiro:principal 这个是：
		principal 标签显示用户身份信息，默认调用Subject.getPrincipal()获取，即Primary Principal。
	-->
	<shiro:hasRole name="admin">
		欢迎有admin角色的用户！<shiro:principal />
	</shiro:hasRole>
	
	<!-- hasPermission 标签如果当前Subject 有权限将显示body 体内容。 -->
	<shiro:hasPermission name="student:create">
		欢迎有student:create权限的用户！<shiro:principal />
	</shiro:hasPermission>


</body>
</html>