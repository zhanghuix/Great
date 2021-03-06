<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="../../common/common-css.jsp"%>
<%@include file="../../common/common-js.jsp" %>
</head>
<script type="text/javascript" src="${ctx }/js/validform/Validform_v5.3.2.js"></script>
<script>
	$(function () {
		$("form").Validform({
			tiptype: 3
		});
	})
</script>
<body>
	<br />
	<br />
	<form
		action="${pageContext.servletContext.contextPath }/background/role/add.html"
		method="post">
		<table class="ttab" height="100" width="50%" border="0"
			cellpadding="0" cellspacing="1" align="center">
			<tr>
				<td height="30" colspan="2">
					<div align="center">
						<font color="blue" size="8"><b>添加信息</b></font>
					</div>
				</td>
			</tr>
			<tr>
				<td height="30" width="20%">
					<div align="right" class="STYLE1">角色名：</div>
				</td>
				<td>
					<div align="left" class="STYLE1" style="padding-left: 10px;">
						<input style="height: 20px; width: 200px" name="name" type="text" datatype="*"
							   nullmsg="*角色名称不能为空!"/>
						<span class="Validform_checktip">*角色名</span>
					</div>
				</td>
			</tr>
			<tr>
				<td height="30" width="20%">
					<div align="right" class="STYLE1">角色roleKey：</div>
				</td>
				<td>
					<div align="left" class="STYLE1" style="padding-left: 10px;">
						<input style="height: 20px; width: 200px" name="roleKey"
							   type="text" datatype="*" nullmsg="*角色roleKey不能为空!"/>
						<span class="Validform_checktip">*角色roleKey</span>
					</div>
				</td>
			</tr>
			<tr>
				<td height="30" width="20%">
					<div align="right" class="STYLE1">是否禁用：</div>
				</td>
				<td>
					<div align="left" class="STYLE1" style="padding-left: 10px;">
						<input name="enable" type="radio" value="0" />:禁用 <input
							name="enable" type="radio" checked="checked" value="1"/>:正常
					</div>
				</td>
			</tr>
			<tr>
				<td height="30" width="20%">
					<div align="right" class="STYLE1">描述：</div>
				</td>
				<td>
					<div align="left" class="STYLE1" style="padding-left: 10px;">
						<input style="height: 20px; width: 200px" name="description"
							type="text" />
					</div>
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
