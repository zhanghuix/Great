<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../common/common.jsp"></jsp:include>

<!-- 照片文件夹列表页面 -->

<table style="margin-left: 3%;" id="photoTable">
	
</table>



<script type="text/javascript">
/**
 * 初始化入口函数
 */
$(document).ready(function() {

	//参数依次为：table名称，文件夹类型type，分页参数
	initPhotoTable(1)
})

/**
 *初始化数据
 */
function initPhotoTable(pageNow){
	
	var photoType="1";//1代表了照片文件夹
	//分页
	if(!page(pageNow)){
		return ;
	}
	
	
	$.ajax({
		url:"web/folder/folderList.html?type="+photoType+"&pageNow="+pageNow,
		type:"post",
		dataType:"json",
		async: true,
		cache:false,
		success:function(res){
			
			if(res.records.length>0){
				//公共函数。参数依次为table名称，返回数据，进入列表页请求路径
				main.buidTable("photoTable",res.records,"web/photo/toPhotoList.html?folderNo=");
				 
				//分页
				initPageView(res,"initPhotoTable");
			}
			 
		},
		error:function(){
			alert("加载数据失败");
		}
		
	});
}

</script>
