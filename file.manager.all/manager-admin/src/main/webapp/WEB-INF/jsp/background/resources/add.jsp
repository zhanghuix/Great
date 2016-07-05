<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@include file="../../common/common-css.jsp" %>
    <%@include file="../../common/common-js.jsp" %>
    <style type="text/css">
      input{font-size: 12px}
    </style>
  </head>
 <script type="text/javascript">
    function submits(){
    	
    	
    	if($("#name").val()==null || $("#name").val()==""){
    		alert("资源名称不能为空");
    		return ;
    	}
    	if($("#resKey").val()==null || $("#resKey").val()==""){
    		alert("资源key不能为空");
    		return ;
    	}
    	if($("#resUrl").val()==null || $("#resUrl").val()==""){
    		alert("资源url不能为空");
    		return ;
    	}
    	
    	 var level = $('#level').val();
    	var valid = /^[0-9]*$/;
   	  if (level =="" ||!valid.test(level)) {
   		  alert("请输入1到9的数字");
   		  return;
   	  }
   	  
   	  var type = document.getElementsByName("type");
   	
   	  var check = true;
   	  
   	  for(var i=0;i<type.length;i++){
   		 if(type[i].checked==true){
   			check=true; 
   			break ;
   		 } else{
   			 check=false;
   		 }
   	  }
    	if(check==false){
    		alert("请选择类型");
    		return ;
    	}
    	
    	$("#form").submit();
    }
 
 </script>
  
  <body>
<br/>
<br/>  
<form action="${pageContext.servletContext.contextPath }/background/resources/add.html" method="post" id="form">
		<table class="ttab" height="100" width="70%" border="0" cellpadding="0" cellspacing="1"
			align="center">
			<tr>
				<td height="30"
					 colspan="2">
					<div align="center">
					<font color="blue" size="6" ><b>添加资源</b></font>
					</div>
				</td>
			</tr>
			<tr>	
					<td height="30"width="20%" >
						<div align="right" class="STYLE1" >
								上级资源：
						</div>
					</td>
					<td >
						<div align="left" class="STYLE1"  style="padding-left:10px;">
						<select name="parentId">
							<option value="1010">顶级菜单</option>
							<c:forEach var="key" items="${resources}">
								<option value="${key.no}">${key.name}</option>
							</c:forEach>
						</select>
						</div>
					</td>
				</tr>
			<tr>	
					<td height="30"width="20%" >
						<div align="right" class="STYLE1" >
								资源名称：
						</div>
					</td>
					<td >
						<div align="left" class="STYLE1"  style="padding-left:10px;">
						<input style="height: 20px;width: 200px" name="name" id="name"/>
						</div>
					</td>
				</tr>
				<tr>	
					<td height="30"width="20%" >
						<div align="right" class="STYLE1" >
								资源KEY：
						</div>
					</td>
					<td >
						<div align="left" class="STYLE1"  style="padding-left:10px;">
						<input style="height: 20px;width: 200px" name="resKey" id="resKey"/>
						</div>
					</td>
				</tr>
				<tr>	
					<td height="30"width="20%" >
						<div align="right" class="STYLE1">
								资源URL：
						</div>
					</td>
					<td>
						<div align="left" class="STYLE1"  style="padding-left:10px;">
						<input style="height: 20px;width: 200px" name="resUrl" id="resUrl"/>
						</div>
					</td>
				</tr>
				<tr>	
					<td height="30"width="20%" >
						<div align="right" class="STYLE1" >
								资源类型：
						</div>
					</td>
					<td >
						<div align="left" class="STYLE1"  style="padding-left:10px;">
						<input name="type" type="radio" value="0"/>:目录　　
						<input name="type" type="radio" value="1"/>:菜单　　
						<input name="type" type="radio" value="2"/>:按扭
						</div>
					</td>
				</tr>
				<tr>	
					<td height="30"width="20%" >
						<div align="right" class="STYLE1" >
								优先级：
						</div>
					</td>
					<td >
						<div align="left" class="STYLE1"  style="padding-left:10px;">
						<input style="height: 20px;width: 200px" name="level" id="level"/>
						</div>
					</td>
				</tr>
				<tr>	
					<td height="30"width="20%" >
						<div align="right" class="STYLE1" >
								资源描述：
						</div>
					</td>
					<td >
						<div align="left" class="STYLE1"  style="padding-left:10px;">
						<input style="height: 20px;width: 200px" name="description"/>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="padding: 10px">
						<div align="center">
			 				<input type="button" value="　保　存　" class="input_btn_style1" onclick="submits()"/>　　　　
			 				<input id="backBt" type="button" value="　返　回　" class="input_btn_style1" onclick="javascript:window.location.href='javascript:history.go(-1)'"/>
		 				</div>
					</td>
				</tr>
		</table>
</form>
  </body>
</html>
