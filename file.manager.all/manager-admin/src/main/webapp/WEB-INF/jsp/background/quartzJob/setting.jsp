<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>定时任务时间设置</title>
<%@include file="../../common/common-css.jsp"%>

<script type="text/javascript">
  function setting(){
       $.ajax({
      		async:false, //请勿改成异步，下面有些程序依赖此请数据
      		type : "POST",
      		data:{jobName:$('#jobName').val(),dateTime:$('#dateTime').val()},
      		url: "${pageContext.servletContext.contextPath }/background/quartzJob/setJobTime.html",
      		dataType:'json',
      		success: function(json){
      			if(json == "1000"){
      			window.dialogArguments.location.reload();
		          alert("充值成功！！");
		          window.close();
              	}else if(json == "1001"){
              		alert("充值失败！！");
                };
       		}
      	});
      }
  </script>

</head>

<body>
	<br />
	<br />
	<table class="ttab" height="100" width="70%" border="0" cellpadding="0"
		cellspacing="1" align="center">
		<tr>
			<td height="30" colspan="2">
				<div align="center">
					<font color="blue" size="3"><b>任务时间添加</b></font>
				</div>
			</td>
		</tr>
		<tr>
			<td height="30" width="20%">
				<div align="right" class="STYLE1">任务名称：</div>
			</td>
			<td>
				<div align="left" class="STYLE1" style="padding-left: 10px;">
					<input id="jobName" disabled="disabled" value="${jobKey }"/>
				</div>
			</td>
		</tr>
		<tr>
			<td height="30" width="20%">
				<div align="right" class="STYLE1">时间：</div>
			</td>
			<td>
				<div align="left" class="STYLE1" style="padding-left: 10px;">
					<select style="height: 20px;width: 200px" id="dateTime" name="dateTime">
					
					<c:forEach var="key" items="${list}">
						<option value="${key.jobNo}">${key.jobTime}</option>
					</c:forEach>	
					</select>*
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="padding: 10px">
				<div align="center">
					<input type="button" value="　保　存　" class="input_btn_style1"
						onclick="setting();" /> <input id="backBt" type="button"
						value="　关　闭　" class="input_btn_style1"
						onclick="javascript:window.close();" />
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
