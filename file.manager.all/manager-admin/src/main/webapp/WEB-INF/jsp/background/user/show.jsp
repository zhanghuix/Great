<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@include file="../../common/common-css.jsp" %>
  </head>
  
  <body>
  <div style="height: 100%;overflow-y: auto;">
<br/>
<br/>  
		<table class="ttab" height="100" width="60%" border="0" cellpadding="0" cellspacing="1" align="center">
				<tr>
					<td height="30" colspan="4">
						<div align="center">
							<font color="blue" size="8"><b>用户信息</b>
							</font>
						</div></td>
				</tr>
				<tr>
					<td height="30" width="20%">
						<div align="right" class="STYLE1">用户名：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							${user.loginName}
						</div></td>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">电话：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
						${user.tel }
						</div></td>
				</tr>
				<tr>
					<td height="30" width="20%">
						<div align="right" class="STYLE1">邮箱：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							${user.email}
						</div></td>
					<td height="30" width="20%">
						<div align="right" class="STYLE1">用户状态：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<c:if test="${user.status eq 'Y'}">启用</c:if>
							<c:if test="${user.status eq 'N'}">停用</c:if>
						</div></td>
				</tr>
				<tr>
					<td height="30" width="20%">
						<div align="right" class="STYLE1">真实姓名：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							${user.name}
						</div></td>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">地址：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
						${user.address }
						</div></td>	
				</tr>
				
				<tr>
					<td height="30" width="20%">
						<div align="right" class="STYLE1">性别：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<c:if test="${user.sex eq 1}">男</c:if>
							<c:if test="${user.sex eq 0}">女</c:if>
						</div></td>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">备注：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
						${user.mark }
						</div></td>
				</tr>
				
				<tr>
					<td height="30" width="10%">
						<div align="right" class="STYLE1">年龄：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							${user.age }
						</div></td>
					<td height="30" width="20%">
						<div align="right" class="STYLE1">用户类型：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<c:if test="${user.type eq 1}">普通用户</c:if>
							<c:if test="${user.type eq 0}">管理员</c:if>
						</div></td>
				</tr>
				
				
				<tr>
					<td colspan="4" style="padding: 10px">
						<div align="center">
							<input
								id="backBt" type="button" value="　返　回　" class="input_btn_style1"
								onclick="javascript:window.location.href='javascript:history.go(-1)'" />
						</div></td>
				</tr>
			</table>
</div>
  </body>
</html>
