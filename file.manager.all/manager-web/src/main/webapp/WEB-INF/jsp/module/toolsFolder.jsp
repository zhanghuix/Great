<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../common/common.jsp"></jsp:include>

<!-- 视频文件夹列表页面 -->

<table style="margin-left: 3%;" id="toolsTable">
	
</table>
<script type="text/javascript">
/**
 * 初始化入口函数
 */
$(document).ready(function() {

	//参数依次为：table名称，文件夹类型type，分页参数
	initToolsTable(1)
})

/**
 *初始化数据
 */
function initToolsTable(pageNow){
	
	var toolsType="5";//5代表了工具文件夹
	//分页
	if(!page(pageNow)){
		return ;
	}
	
	
	$.ajax({
		url:"web/folder/folderList.html?type="+toolsType+"&pageNow="+pageNow,
		type:"post",
		dataType:"json",
		async: true,
		cache:false,
		success:function(res){
			
			if(res.records.length>0){
				//参数依次为table名称，文件夹类型type，分页参数
				main.buidTable("toolsTable",res.records,"web/tools/toToolsList.html?folderNo=");
				 
				//分页
				initPageView(res,"initToolsTable");
			}
			 
		},
		error:function(){
			alert("加载数据失败");
		}
		
	});
}

</script>
