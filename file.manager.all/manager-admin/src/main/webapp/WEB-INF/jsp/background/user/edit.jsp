<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../../common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@include file="../../common/common-css.jsp" %>
    <%@include file="../../common/common-js.jsp" %>
	  <link rel="stylesheet" href="${ctx }/js/validform/style.css" type="text/css" media="all" />
  </head>
  <script type="text/javascript" src="${ctx }/js/validform/Validform_v5.3.2.js"></script>
<script>
	$(function(){
		$("#editform").Validform({
			tiptype:3
		});
	})
</script>
  <body>
  <div style="height: 100%;overflow-y: auto;">
<br/>
<br/>  
<form id="editform" action="${pageContext.servletContext.contextPath }/background/user/update.html" method="post">
<input type="hidden" name="no" value="${user.no}">
		<table class="ttab" height="100" width="85%" border="0" cellpadding="0" cellspacing="1" align="center">
				<tr>
					<td height="30" colspan="4">
						<div align="center">
							<font color="blue" size="8"><b>修改信息</b>
							</font>
						</div></td>
				</tr>
				<tr>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">用户名：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" id="userLoginName" maxlength="20"
								   name="loginName" value="${user.loginName }" datatype="*"
								   nullmsg="*登录名称不能为空!"/>
							<span class="Validform_checktip">*用户登录的名称</span>
						</div></td>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">电话：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input maxlength="20" style="height: 20px;width: 200px" value="${user.tel }" name="tel" datatype="*" nullmsg="*电话不能为空!"/>
							<span class="Validform_checktip">*</span>
						</div></td>
				</tr>
				<tr>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">密码：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" id="userPassword" name="pwd"
								   maxlength="20"
								   type="password" value="${user.pwd }" datatype="*" nullmsg="*密码不能为空!"/>
								<span class="Validform_checktip">*用户登录的密码</span>
						</div></td>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">邮箱：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" name="email" maxlength="20"
								   value="${user.email }" datatype="*" nullmsg="*邮箱不能为空!"/>
							<span class="Validform_checktip">*</span>
						</div></td>
				</tr>
				<tr>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">真实姓名：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" name="name" maxlength="20"
								   value="${user.name }"/>
						</div></td>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">地址：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" maxlength="20" name="address" value="${user.address }"/>
						</div></td>	
				</tr>
				<tr>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">性别：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<!-- <input style="height: 20px;width: 200px" name="userSex" /> -->
							<select style="height: 20px;width: 200px" name="sex">
							    <option value="1" <c:if test="${user.sex eq 1}">selected="selected"</c:if>>男</option>
							    <option value="0" <c:if test="${user.sex eq 0}">selected="selected"</c:if>>女</option>
							</select>
						</div></td>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">备注：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" maxlength="20" name="mark" value="${user.mark }"/>
						</div></td>
				</tr>
				<tr>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">年龄：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" maxlength="20" name="age" value="${user.age }"/>
						</div></td>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">用户类型：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input type="radio" name="type" value="1"
								   <c:if test="${user.type eq 1}">checked="checked"</c:if>/>：普通用户
							<input type="radio" name="type" value="0"
								   <c:if test="${user.type eq 0}">checked="checked"</c:if>/>：管理人员
						</div></td>
				</tr>
				<tr>
				<td height="30" width="10%">
						<div align="right" class="STYLE1">用户状态：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input type="radio" name="status" value="1"
								   <c:if test="${user.status eq 'Y'}">checked="checked"</c:if>/>：启用
							<input type="radio" name="status" value="0"
								   <c:if test="${user.status eq 'N'}">checked="checked"</c:if>/>：停用
						</div></td>
						<td></td><td></td>	
				
				</tr>
			
				<tr>
					<td colspan="4" style="padding: 10px">
						<div align="center">
							<input type="submit" value="　保　存　" class="input_btn_style1" /> <input
								id="backBt" type="button" value="　返　回　" class="input_btn_style1"
								onclick="javascript:window.location.href='javascript:history.go(-1)'" />
						</div></td>
				</tr>
			</table>
</form>
</div>
  </body>
</html>
