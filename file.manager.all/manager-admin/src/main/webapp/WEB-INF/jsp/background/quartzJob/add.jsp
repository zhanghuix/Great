<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>定时任务时间设置</title>
<%@include file="../../common/common-css.jsp"%>

<script type="text/javascript">
  function and(){
       $.ajax({
      		async:false, //请勿改成异步，下面有些程序依赖此请数据
      		type : "POST",
      		data:{desc:$('#desc').val(),jobTime:$('#jobTime').val()},
      		url: "${pageContext.servletContext.contextPath }/background/quartzJob/addJobTime.html",
      		dataType:'json',
      		success: function(json){
      			if(json == "1000"){
      			window.dialogArguments.location.reload();
		          alert("保存成功！！");
		          window.close();
              	}else if(json == "1001"){
              		alert("保存失败！！");
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
				<div align="right" class="STYLE1">时间：</div>
			</td>
			<td>
				<div align="left" class="STYLE1" style="padding-left: 10px;">
					<input id="jobTime" />*符合quartz时间格式
				</div>
			</td>
		</tr>
		<tr>
			<td height="30" width="20%">
				<div align="right" class="STYLE1">描述：</div>
			</td>
			<td>
				<div align="left" class="STYLE1" style="padding-left: 10px;">
					<textarea rows="5" cols="1" name="desc" style="width: 200px;" id="desc"></textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="padding: 10px">
				<div align="center">
					<input type="button" value="　保　存　" class="input_btn_style1"
						onclick="and();" /> <input id="backBt" type="button"
						value="　关　闭　" class="input_btn_style1"
						onclick="javascript:window.close();" />
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
