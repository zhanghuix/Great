<%@ page language="java" pageEncoding="UTF-8" %>


<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="../../common/common-css.jsp"%>
<%@include file="../../common/common-js.jsp" %>
	<link rel="stylesheet" href="${ctx }/js/validform/style.css" type="text/css" media="all" />

</head>

<script type="text/javascript" src="${ctx }/js/validform/Validform_v5.3.2.js"></script>
<script type="text/javascript">

$(document).ready(function(){

	$("#addform").Validform({
		tiptype:3
	});

   	   $("#saveButton").click(function(){
   		   userinfo.submits();
   	   });
   	  
     });
</script>
<body>
	<div style="height: 100%;overflow-y: auto;">
		<br /> <br />
		<form id="addform"
			action="${pageContext.servletContext.contextPath }/background/user/add.html"
			method="post">
			<table class="ttab" height="100" width="85%" border="0"
				cellpadding="0" cellspacing="1" align="center">
				<tr>
					<td height="30" colspan="4">
						<div align="center">
							<font color="blue" size="8"><b>添加信息</b>
							</font>
						</div></td>
				</tr>
				<tr>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">用户名：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" id="userLoginName" maxlength="20"
								   name="loginName" datatype="*" nullmsg="*登录名称不能为空!"/>
							<span class="Validform_checktip">*用户登录的名称</span>
						</div></td>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">电话：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input maxlength="20" style="height: 20px;width: 200px" name="tel" datatype="*" nullmsg="*电话不能为空!"/>
							<span class="Validform_checktip">*</span>
						</div></td>
				</tr>
				<tr>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">密码：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" id="userPassword" maxlength="20"
								   name="pwd"
								type="password" datatype="*" nullmsg="*密码不能为空!" />
									<span class="Validform_checktip">*用户登录的密码</span>
						</div></td>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">邮箱：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" name="email" maxlength="20" datatype="*"
								   nullmsg="*邮箱不能为空!"/>
							<span class="Validform_checktip">*</span>
						</div></td>
				</tr>
				<tr>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">真实姓名：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" maxlength="20" name="name" datatype="*"
								   nullmsg="*姓名不能为空!"/>
							<span class="Validform_checktip">*</span>
						</div></td>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">地址：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" maxlength="20" name="address"/>
						</div></td>	
					
				</tr>
				
				<tr>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">性别：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<!-- <input style="height: 20px;width: 200px" name="userSex" /> -->
							<select style="height: 20px;width: 200px" name="sex">
							    <option value="1">男</option>
							    <option value="0">女</option>
							</select>
						</div></td>
						
						<td height="30" width="10%">
						<div align="right" class="STYLE1">备注：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" maxlength="20" name="mark"/>
						</div></td>
					
				</tr>
				
				<tr>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">年龄：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" maxlength="20" name="age"/>
						</div></td>
						
					<td height="30" width="10%">
						<div align="right" class="STYLE1">用户类型：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input type="radio" name="type" value="1" checked="checked"/>：普通用户
							<input type="radio" name="type" value="0"/>：管理人员
						</div></td>
				</tr>
				
				<tr>
					<td colspan="4" style="padding: 10px">
						<div align="center">
							<input id="saveButtons" type="submit" value="　保　存　" class="input_btn_style1" /> <input
								id="backBt" type="button" value="　返　回　" class="input_btn_style1"
								onclick="javascript:window.location.href='javascript:history.go(-1)'" />
						</div></td>
				</tr>
			</table>
		</form>
	</div>
</body>


</html>
