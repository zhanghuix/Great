<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>FileManager主页</title>
<jsp:include page="../common/common.jsp"></jsp:include>

<!-- css在main.jsp中，为了兼容IE的加载方式 -->
<link href="plugin/jquery/css/jquery-confirm.min.css" type="text/css" rel="stylesheet">

<link href="plugin/bootstrap/css/bootstrap-responsive.min.css"
	type="text/css" rel="stylesheet">
	
<link href="plugin/bootstrap/css/bootstrap.min.css"
	type="text/css" rel="stylesheet">
	
<link href="plugin/jquery/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet">

<!-- myCSS必须放在main.jsp中 -->
<link href="css/main.css" type="text/css" rel="stylesheet">
<link href="css/module/home.css" type="text/css" rel="stylesheet">

<script type="text/javascript">
	/**控制iframe自适应高度*/
 /* 	function iFrameHeight() {

		var ifrm = document.getElementById("iframepage");
		var subWeb = document.frames ? document.frames["iframepage"].document:ifrm.contentDocument;
		if (ifrm != null && subWeb != null) {
			ifrm.height = subWeb.body.scrollHeight;
		}

	} */
	
	
	/**
	 * 控制div代替ifream
	 */
	$.get("web/login/toTop.html",function(data){
		$("#topDiv").html(data);
	});
	
	/**
	 * 控制div代替ifream
	 */
	$.get("web/login/toHome.html",function(data){
		$("#mainDiv").html(data);
	});
	
	
	/**
	 * 控制div代替ifream
	 */
	$.get("web/login/toBottom.html",function(data){
		$("#bottomDiv").html(data);
	});
	
	
</script>
</head>

<body>
  
    <div id="topDiv"></div>

	<div class="mainDiv" id="mainDiv">
		
		<!-- 动态控制需要显示的jsp页面 top.js中控制，默认显示home.jsp-->
		<!-- <iframe src="web/login/toHome.html" id="iframepage" frameborder="0" scrolling="no"
			marginheight="0" marginwidth="0" width="100%" onload="iFrameHeight()"></iframe> -->
	
	</div>
   
   
    <div id="fenyeDiv" class="fenyeDiv" style="display: none;"><jsp:include page="../common/webfenye.jsp"></jsp:include></div>
   
   
    <div class="bottomDiv" id="bottomDiv"></div>
</body>
</html>