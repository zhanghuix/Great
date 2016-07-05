<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	
<script type="text/javascript" src="<%=basePath%>plugin/prototype.js"></script>	
<script type="text/javascript" src="<%=basePath%>plugin/jquery/jquery-1.7.2-min.js"></script>
<script type="text/javascript" src="<%=basePath%>plugin/jquery/jquery.form.js"></script>
<script type="text/javascript" src="<%=basePath%>plugin/jquery/jquery-confirm.min.js"></script>
<%-- <script type="text/javascript" src="<%=basePath%>plugin/jquery/jquery.validate.js"></script> --%>
<script type="text/javascript" src="<%=basePath%>plugin/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=basePath%>plugin/jquery/jquery.ztree.core-3.5.js"></script>

<script type="text/javascript" src="<%=basePath%>plugin/ajaxfileupload/ajaxFileUpload.js"></script>
<script type="text/javascript" src="<%=basePath%>plugin/bootstrap/js/bootstrap.min.js"></script>
<!-- myJS -->
<script type="text/javascript" src="<%=basePath%>javascript/framework/main.js"></script>

<!-- 所有引入此标签页面，相当于所有请求或者链接前面加入http://localhost:8080/ -->
<base href="<%=basePath%>">

<c:set var="ctx" value="<%=basePath %>" />

<input type="hidden" id="basePath" value="<%=basePath%>"><!-- 为js中获取路径 -->




