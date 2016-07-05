<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../common/common.jsp"></jsp:include>

<!-- 文章文件夹列表页面 -->

<table style="margin-left: 3%;" id="articleTable">
	
</table>
<script type="text/javascript">
/**
 * 初始化入口函数
 */
$(document).ready(function() {

	//参数依次为：table名称，文件夹类型type，分页参数
	initArticleTable(1)
})

/**
 *初始化数据
 */
function initArticleTable(pageNow){
	
	var articleType="4";//4代表了文章文件夹
	//分页
	if(!page(pageNow)){
		return ;
	}
	
	$.ajax({
		url:"web/folder/folderList.html?type="+articleType+"&pageNow="+pageNow,
		type:"post",
		dataType:"json",
		async: true,
		cache:false,
		success:function(res){
			
			if(res.records.length>0){
				 //参数依次为table名称，数据，列表页面请求地址
				main.buidTable("articleTable",res.records,"web/article/toArticleList.html?folderNo=");
				 
				//分页
				initPageView(res,"initArticleTable");
			}
			
		},
		error:function(){
			alert("加载数据失败");
		}
		
	});
}

</script>
