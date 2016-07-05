<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td><table width="100%" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td class="STYLE4">&nbsp;&nbsp;总记录数:<span id="rowCount3">${pageView.rowCount}</span>条
						|每页显示:<span id="pageSize3">${pageView.pageSize}</span>条 | 总页数:<span
						id="pageCount3">${pageView.pageCount}</span>页
					</td>
					<td>
						<table border="0" align="right" cellpadding="0" cellspacing="0" id="pageTable3">
							
						</table>
					</td>
				</tr>
			</table></td>
	</tr>
</table>


<script type="text/javascript">
function page3(pageNow) {

	
	var pCount = parseInt($("#pageCount3").html());
	if (pageNow < 1) {
		alert(" 不 好 意 思 ， 已 经 是 第 一 页 啦  ！");
		return false;
	} else if (pCount < pageNow) {
		alert(" 没 有 下 一 页 啦 ！");
		return false;
	} else {
		return true;
	}
}
/**
 * 分页，pageData数据，callback回调函数
 */
function initPageView3(pageData,callback){
	
	$("#pageTable3 tr").remove();
	
	if(pageData ==null){
		return ;
	}
	
	
	$("#rowCount3").html(pageData.rowCount);
	$("#pageSize3").html(pageData.pageSize);
	$("#pageCount3").html(pageData.pageCount);
	
	var start = pageData.pageindex.startindex;
	var end = pageData.pageindex.endindex
	
	var td3="";
	
	for(var i=start;i<=end;i++){
		if(pageData.pageNow==i){
			td3=td3+"&nbsp;<span class='current' style='color: red; font-size: 15px;'>"+i+"</span>";
		}else{
			td3=td3+"&nbsp;<a href='javascript:void(0)' onclick='"+callback+"("+i+")'>"+i+"</a>";
		}
	}
	
	var td1="<td width='40' class='STYLE4'><a href='javascript:void(0)' onclick='"+callback+"(1)'> 首页 </a></td>";
	var td2="<td width='45' class='STYLE4'><a href='javascript:void(0)' onclick='return "+callback+"("+(pageData.pageNow - 1)+")'> 上一页 </a></td>";
	    td3="<td align='center' style='font-size:13px;'>"+td3+"&nbsp;</td>";
	var td4="<td width='45' class='STYLE4'><a href='javascript:void(0)' onclick='"+callback+"("+(pageData.pageNow + 1)+")'> 下一页 </a></td>";
	var td5="<td width='40' class='STYLE4'><a href='javascript:void(0)' onclick='"+callback+"("+pageData.pageCount+")'> 尾页 </a></td>";
	
	$("#pageTable3").append("<tr>"+td1+td2+td3+td4+td5+"</tr>");
}
</script>
