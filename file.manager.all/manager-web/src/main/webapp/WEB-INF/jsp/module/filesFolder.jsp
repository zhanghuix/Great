<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../common/common.jsp"></jsp:include>

<!-- 文件文件夹列表页面 -->

<table style="margin-left: 3%;" id="fileTable">
	
</table>
<script type="text/javascript">
/**
 * 初始化入口函数
 */
$(document).ready(function() {

	//参数依次为：table名称，文件夹类型type，分页参数
	initFileTable(1)
})

/**
 *初始化数据
 */
function initFileTable(pageNow){
	
	var fileType="3";//3代表了文件文件夹
	//分页
	if(!page(pageNow)){
		return ;
	}
	
	
	$.ajax({
		url:"web/folder/folderList.html?type="+fileType+"&pageNow="+pageNow,
		type:"post",
		dataType:"json",
		async: true,
		cache:false,
		success:function(res){
			
			if(res.records.length>0){
				//参数依次为table名称，数据，列表页面
				main.buidTable("fileTable",res.records,"web/files/toFilesList.html?folderNo=");
				 
				//分页,参数依次为数据，回调函数
				initPageView(res,"initFileTable");
			}
			 
		},
		error:function(){
			alert("加载数据失败");
		}
		
	});
}

</script>
