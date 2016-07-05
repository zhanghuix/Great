<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../../common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="../../common/common-css.jsp" %>
<%@include file="../../common/common-js.jsp" %>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath }/css/fenyecss.css" />
	<script type="text/javascript">
function permissio(no){
	 var url = "${pageContext.servletContext.contextPath }/background/resources/permissioRole.html?roleNo="+no;
  diaglog("分配权限", url, 350, 400);
}
</script>
	<style type="text/css">
	  input{font-size: 12px}
	</style>
</head>
<body>
<form id="fenye" name="fenye" action="${pageContext.servletContext.contextPath }/background/role/query.html" method="post">
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
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：角色管理-角色列表</td>
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
                   
                    <sec:authorize ifAnyGranted="ROLE_sys_role_add">
                    <td class="STYLE1"><div align="center"><img src="${pageContext.servletContext.contextPath }/images/22.gif" width="14" height="14" /></div></td>
                    <td class="STYLE1"><div align="center"> 
                    <a href="${pageContext.servletContext.contextPath }/background/role/addUI.html">
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
				名称：<input type="text" name="name" value="${param.name}" style="height: 20px"/>　　
				<input type="submit" value="开始查询" class="input_btn_style1"/>&nbsp;&nbsp;
              <input type="button" value="重置" class="input_btn_style1"
                     onclick="location.href='${pageContext.servletContext.contextPath }/background/role/query.html'"/>
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
 			<td width="15%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">角色名</td>
 			<td width="15%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">角色KEY</td>
            <td width="15%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" ><span class="STYLE1">是否禁用</span></td>
            <td width="15%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" ><span class="STYLE1">描述</span></td>
            <td width="15%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" ><span class="STYLE1">创建时间</span></td>
            <td width="23%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">操作</td>
          </tr>
          
          <c:forEach var="role" items="${pageView.records}">
          <tr>
            <td height="20" >
              <input type="checkbox" name="check" value="${role.no}" />
            </td>
            <td height="20" ><span class="STYLE1"><a href="${pageContext.servletContext.contextPath }/background/role/getById.html?roleNo=${role.no}&&typeKey=0"><font color="blue">${role.name}</font></a></span></td>
            <td height="20" ><span class="STYLE1">${role.roleKey}</span></td>
            <td height="20" ><span class="STYLE1">
             <c:if test="${role.enable eq '0'}">
            <font color="red">禁用</font>
            </c:if>
            <c:if test="${role.enable eq '1'}">
                                       正常
            </c:if></span></td>
            <td height="20" ><span class="STYLE1">${role.description}</span></td>
            
            <td height="20" ><span class="STYLE1">
            <fmt:parseDate value="${role.createTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss" />
			<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss" />
            </span></td>
            
            <td height="20" ><span class="STYLE4">
            <sec:authorize ifAnyGranted="ROLE_sys_role_permissions">
            <img src="${pageContext.servletContext.contextPath }/images/resc.png" width="16" height="16" />
            	<a href="javascript:void(0);" onclick="permissio('${role.no}')">分配权限</a>&nbsp;&nbsp;&nbsp;&nbsp;
            </sec:authorize>
           
            <sec:authorize ifAnyGranted="ROLE_sys_role_edit">
            <img src="${pageContext.servletContext.contextPath }/images/edt.gif" width="16" height="16" />
            <a href="${pageContext.servletContext.contextPath }/background/role/getById.html?roleNo=${role.no}&&typeKey=1">
                                     编辑
            </a>
            &nbsp; &nbsp;</sec:authorize>
            <sec:authorize ifAnyGranted="ROLE_sys_role_delete">
            <img src="${pageContext.servletContext.contextPath }/images/del.gif" width="16" height="16" />
            	<a href="javascript:void(0);" onclick="deleteId('${pageContext.servletContext.contextPath }/background/role/deleteById.html?roleNo=${role.no}')">
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