<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="../../common/common-css.jsp"%>
<style type="text/css">
input {
	font-size: 12px
}
</style>
</head>

<body>
	<br />
	<br />
	<form
		action="${pageContext.servletContext.contextPath }/background/resources/update.html"
		method="post">
		<input type="hidden" name="no" value="${resources.no}">
		<table class="ttab" height="100" width="70%" border="0"
			cellpadding="0" cellspacing="1" align="center">
			<tr>
				<td height="30" colspan="2">
					<div align="center">
						<font color="blue" size="6"><b>资源详情</b></font>
					</div>
				</td>
			</tr>
			<tr>
				<td height="30" width="20%">
					<div align="right" class="STYLE1">上级资源：</div>
				</td>
				<td>
					<div align="left" class="STYLE1" style="padding-left: 10px;">
						<select name="parentId">
							<option value="1010">顶级菜单</option>
							<c:forEach var="key" items="${resLists}">
								<c:if test="${key.no eq resources.no}">${key.name}</c:if>
							</c:forEach>
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<td height="30" width="20%">
					<div align="right" class="STYLE1">资源名称：</div>
				</td>
				<td>
					<div align="left" class="STYLE1" style="padding-left: 10px;">
						${resources.name}</div>
				</td>
			</tr>
			<tr>
				<td height="30" width="20%">
					<div align="right" class="STYLE1">资源KEY：</div>
				</td>
				<td>
					<div align="left" class="STYLE1" style="padding-left: 10px;">
						${resources.resKey}</div>
				</td>
			</tr>
			<tr>
				<td height="30" width="20%">
					<div align="right" class="STYLE1">资源URL：</div>
				</td>
				<td>
					<div align="left" class="STYLE1" style="padding-left: 10px;">
						${resources.resUrl}</div>
				</td>
			</tr>
			<tr>
				<td height="30" width="20%">
					<div align="right" class="STYLE1">资源类型：</div>
				</td>
				<td>
					<div align="left" class="STYLE1" style="padding-left: 10px;">
						<c:if test="${resources.type eq '0'}">目录</c:if>
						<c:if test="${resources.type eq '1'}">菜单</c:if>
						<c:if test="${resources.type eq '2'}">按扭</c:if>
					</div>
				</td>
			</tr>
			<tr>
				<td height="30" width="20%">
					<div align="right" class="STYLE1">优先级：</div>
				</td>
				<td>
					<div align="left" class="STYLE1" style="padding-left: 10px;">
						${resources.level}</div>
				</td>
			</tr>
			<tr>
				<td height="30" width="20%">
					<div align="right" class="STYLE1">资源描述：</div>
				</td>
				<td>
					<div align="left" class="STYLE1" style="padding-left: 10px;">
						${resources.description}</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="padding: 10px">
					<div align="center">
						<input type="submit" value="　保　存　" class="input_btn_style1" /> <input
							id="backBt" type="button" value="　返　回　" class="input_btn_style1"
							onclick="javascript:window.location.href='javascript:history.go(-1)'" />
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>