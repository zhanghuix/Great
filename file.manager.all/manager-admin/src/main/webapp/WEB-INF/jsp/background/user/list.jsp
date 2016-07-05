<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../../common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../../common/common-css.jsp" %>
<%@include file="../../common/common-js.jsp" %>


  <link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath }/css/fenyecss.css" />

<script type="text/javascript">
function permissio(id){
	 var url = "${pageContext.servletContext.contextPath }/background/resources/permissioUser.html?userId="+id;
	 var h_sp1 = 400;
	 var w_sp1 = 350;
	//兼容IE，firefox,google.模态窗口居中问题
	 var iTop2 = (window.screen.availHeight - 20 - h_sp1) / 2;
	 var iLeft2 = (window.screen.availWidth - 10 - w_sp1) / 2;
	 var params = 'menubar:no;dialogHeight=' + h_sp1 + 'px;dialogWidth=' + w_sp1 + 'px;dialogLeft=' + iLeft2 + 'px;dialogTop=' + iTop2 + 'px;resizable=yes;scrollbars=0;resizeable=0;center=yes;location:no;status:no;scroll:no'
	 window.showModalDialog(url, window, params);
	 //location.href=url;
}
function userRole(no){
	
	 var url = "${pageContext.servletContext.contextPath }/background/user/userRole.html?userNo="+no;
  diaglog("用户权限", url, 500, 500);
}
function toMoneys(){
	
	var userNo="";
	
	var istrue=false;
	
	var checks = document.getElementsByName("check");
	
	for(var i = 0; i < checks.length; i++)
	{
		if(checks[i].checked == true)
		{
			istrue=true;
			userNo=checks[i].value+","+userNo;
		}
	}
	
	if(istrue == false){
		alert("请选择要充值的用户");
		return;
	}
	
	 var url = "${pageContext.servletContext.contextPath }/background/userAccount/toMoney.html?userNo="+userNo;
  diaglog("用户充值", url, 600, 300);

}
</script>
</head>
<body>
<form id="fenye" name="fenye" action="${pageContext.servletContext.contextPath }/background/user/query.html" method="post">
<table width="100%">
  <tr>
    <td height="30" background="${pageContext.servletContext.contextPath }/images/tab_05.gif"><table width="100%">
      <tr>
        <td width="12" height="30"><img src="${pageContext.servletContext.contextPath }/images/tab_03.gif" width="12" height="30" /></td>
        <td><table width="100%">
          <tr>
            <td width="46%" valign="middle"><table width="100%">
              <tr>
                <td width="5%"><div align="center"><img src="${pageContext.servletContext.contextPath }/images/tb.gif" width="16" height="16" /></div></td>
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：系统管理-查询用户</td>
              </tr>
            </table></td>
            <td width="54%"><table align="right" >
              <tr>
                <td width="60"><table width="87%" >
                  <tr>
                    <td class="STYLE1"><div align="center">
                      <input type="checkbox" name="checkbox11" id="choseAll" onclick="selectAllCheckBox()" />
                    </div></td>
                    <td class="STYLE4">全选</td>
                  </tr>
                </table></td>
                <td width="52"><table width="88%">
                  <tr>
                    <td class="STYLE1"><div align="center"><img src="${pageContext.servletContext.contextPath }/images/11.gif" width="14" height="14" /></div></td>
                    <td class="STYLE4">
                    <a href="javascript:void(0);"  onclick="return deleteAll()">
                    	删除
                    </a>
                    	</td>
                  </tr>
                </table></td>
                <td width="60"><table width="90%">
                  <tr>
                   
                    <sec:authorize ifAnyGranted="ROLE_sys_user_addUI">
                    <td class="STYLE1"><div align="center"><img src="${pageContext.servletContext.contextPath }/images/22.gif" width="14" height="14" /></div></td>
                    <td class="STYLE1"><div align="center"> 
                    <a href="${pageContext.servletContext.contextPath }/background/user/addUI.html">
                    	新增
                    </a>
                    </div></td>
                    
                    </sec:authorize>
                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="${pageContext.servletContext.contextPath }/images/tab_07.gif" width="16" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
  <td align="center">
  <!-- 这里的表单 name 必须是fenye -->
  	<div class="search_k" align="left">
		<fieldset class="search">
			<legend><img src="${pageContext.servletContext.contextPath }/images/search_btn.gif" align="middle"/>&nbsp;<span class="STYLE1" style="color: blue;">高级查找</span></legend>
			<div class="search_content">
				用户名：<input type="text" name="loginName" value="${param.loginName}" style="height: 20px"/>　　
				真实姓名：<input type="text" name="name" value="${param.name}" style="height: 20px"/>　
				<input type="submit" value="开始查询" class="input_btn_style1"/>&nbsp;&nbsp;
				<input type="button" onclick="location.href='${pageContext.servletContext.contextPath }/background/user/query.html'"   value="重置" class="input_btn_style1"/>

			</div>
		</fieldset>
	</div>
  </td>
  </tr>
  <tr>
    <td><table class="listtable" width="100%">
      <tr>
        <td width="8" background="${pageContext.servletContext.contextPath }/images/tab_12.gif">&nbsp;</td>
        <td><table class="ttab" width="100%" cellspacing="1" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" >
              <input id="chose" type="checkbox" name="checkbox" onclick="selectAllCheckBox()" />
            </td>
 
            <td width="10%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" ><span class="STYLE1">登录名</span></td>
            <td width="10%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" ><span class="STYLE1">所属角色</span></td>
            <td width="10%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">用户姓名</td>
             <td width="10%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">用户电话</td>
              <td width="15%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">用户邮箱</td>
            <td width="5%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">用户状态</td>
            <td width="5%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">用户类型</td>
            <td width="15%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">注册时间</td>
            <td width="20%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">基本操作</td>
          </tr>
          
          <c:forEach var="key" items="${pageView.records}">
          <tr>
            <td height="20" >
              <input type="checkbox" name="check" value="${key.no}" />
            </td>
            
            <td height="20" ><span class="STYLE1"><a href="${pageContext.servletContext.contextPath }/background/user/getById.html?userNo=${key.no}&&type=0">${key.loginName}</a></span></td>
            <td height="20" ><span class="STYLE1" style="color: blue;">${key.roleName}</span></td>
            <td height="20" ><span class="STYLE1">${key.name}</span></td>
            <td height="20" ><span class="STYLE1">${key.tel }</span></td>
            <td height="20" ><span class="STYLE1">${key.email }</span></td>
            
             <td height="20" ><span class="STYLE1">
            <c:if test="${key.status eq 'Y'}">启用</c:if>
            <c:if test="${key.status eq 'N'}">停用</c:if>
            </span></td>
            
            <td height="20" ><span class="STYLE1">
            <c:if test="${key.type eq '0'}">管理员</c:if>
            <c:if test="${key.type eq '1'}">普通用户</c:if>
            </span></td>
            
            <td height="20" ><span class="STYLE1">
            <fmt:parseDate value="${key.createTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss" />
			<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss" />
            </span></td>
            <td height="20" ><span class="STYLE4">
             <sec:authorize ifAnyGranted="ROLE_sys_user_fenpeirole">
             <img src="${pageContext.servletContext.contextPath }/images/role.png" width="16" height="16" />
            	<a href="javascript:void(0);" onclick="userRole('${key.no}')">
            	分配角色</a>
            </sec:authorize>
            
            <sec:authorize ifAnyGranted="ROLE_sys_user_edit">
            <img src="${pageContext.servletContext.contextPath }/images/edt.gif" width="16" height="16" />
            <a href="${pageContext.servletContext.contextPath }/background/user/getById.html?userNo=${key.no}&&type=1">
                                     编辑
            </a>
            &nbsp; &nbsp;
           </sec:authorize>
           
           <sec:authorize ifAnyGranted="ROLE_sys_user_delete">
            <img src="${pageContext.servletContext.contextPath }/images/del.gif" width="16" height="16" />
            	<a href="javascript:void(0);" onclick="deleteId('${pageContext.servletContext.contextPath }/background/user/deleteById.html?userNo=${key.no}');">
            	删除</a>
            	</sec:authorize>
            	</span></td>
          </tr>
          </c:forEach>
        </table></td>
        <td width="8" background="${pageContext.servletContext.contextPath }/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="35" background="${pageContext.servletContext.contextPath }/images/tab_19.gif"><%@include file="../../common/webfenye.jsp" %>
    </td>
  </tr>
</table>
</form>
</body>
</html>