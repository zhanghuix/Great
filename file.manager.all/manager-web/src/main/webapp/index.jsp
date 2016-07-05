<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
  </head>
  
  
  <script type="text/javascript">
  
  </script>
  
  <body onload="">
	 <a href="<%=basePath%>web/login/toLogin.html" id="mainForm">login</a>
	  <a href="<%=basePath%>web/login/toTop.html" id="mainForm">top</a>
  </body>
</html>
