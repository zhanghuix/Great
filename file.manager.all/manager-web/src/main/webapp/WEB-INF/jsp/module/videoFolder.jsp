<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../common/common.jsp"></jsp:include>

<!-- 视频文件夹列表页面 -->

<table style="margin-left: 3%;" id="videoTable">
	
</table>
<script type="text/javascript">
/**
 * 初始化入口函数
 */
$(document).ready(function() {

	//参数依次为：table名称，文件夹类型type，分页参数
	initVideoTable(1)
})

/**
 *初始化数据
 */
function initVideoTable(pageNow){
	
	var videoType="2";//2代表了视频文件夹
	//分页
	if(!page(pageNow)){
		return ;
	}
	
	
	$.ajax({
		url:"web/folder/folderList.html?type="+videoType+"&pageNow="+pageNow,
		type:"post",
		dataType:"json",
		async: true,
		cache:false,
		success:function(res){
			
			if(res.records.length>0){
				//参数依次为table名称，文件夹类型type，分页参数
				main.buidTable("videoTable",res.records,"web/video/toVideoList.html?folderNo=");
				 
				//分页
				initPageView(res,"initVideoTable");
			}
			 
		},
		error:function(){
			alert("加载数据失败");
		}
		
	});
}

</script>
