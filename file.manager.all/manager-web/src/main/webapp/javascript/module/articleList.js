

 var articlePath="";//全局变量文件地址

/**
 * 初始化入口函数
 */
$(document).ready(function() {
	
	//获取文件上传后的地址
	articlePath=main.initDownLoadPath("","Article");//拼接下载路径File必须与上传路径中的一致

	initArticleTable(1);
	
	
	$("#saveArticle").click(function(){
		saveArticle();
	});
	
	$("#clearArticle").click(function(){
		clear();
	});
	
	$("#delArticleAll").click(function(){
		delArticle();
	});
	
	//移动文件弹出框
	$("#moveArticle").click(function(){
		move.moveModal("articleBoxName","web/article/moveArticle.html","4",function(){
			//回调函数
			initArticleTable(1);
		});//4=文章，在folder表中type=4
	});
	
	
});

/**
 *初始化数据
 */
function initArticleTable(pageNow){
	
	//分页
	if(!page(pageNow)){
		return ;
	}
	 var folderNo =$("#folderNo").val();
	 
	 var url = "web/article/articleListPage.html?folderNo="+folderNo+"&pageNow="+pageNow;
	 main.ajax2(url,function(res){
		 if(res.records.length>0){
				
				buidTableList(res.records);
				 
				//分页
				initPageView(res,"initArticleTable");
			}else{
				$("#articleTbody tr").remove();
			}
	 });
	 
}


/**
 * 绑定数据，列表
 */
function buidTableList(data){

	$("#articleTbody tr").remove();
	
	var tr = "";
	
	if(data !="" && data!=null && data.length>0){
		
		for(var i=0;i<data.length;i++){
			var article = data[i];
			
			if(i%2==0){
				tr=tr+"<tr class='active'>";
			}else{
				tr = tr+"<tr class='warning'>"
			}
			
			var description="";
			if(article.description!=null){
				description=article.description;
			}
			
			var size="0";
			if(article.size!=null){
				size=article.size;
			}
			
			var tempName = "";
			if(article.otherName !=null){
				tempName = article.otherName;
			}else{
				tempName=article.name;
			}
			
			var td = "<td><button class='btn btn-warning' onClick=toModfiy('"+article.no+"')>修改</button></td>";
			
			tr=tr+"<td><input type='checkbox' value='"+article.no+"' name='articleBoxName'/></td><td>"+(i+1)+"</td><td><a href='"+articlePath+article.name+"'>"+tempName+"</a></td><td>"+(article.source==null?"":article.source)+"</td><td>"+size+"</td><td>"+main.formatDateNow(article.writetime)+"</td><td>"+main.formatDateTimeNow(article.createtime)+"</td><td>"+article.userNo+"</td>"+td+"</tr>";
		}
		$("#articleTbody").append(tr);
	}
}


/**
 * 弹出修改文件信息
 * @param articleNo
 */
function toModfiy(articleNo){
	
	var url = "web/article/getArticle.html?articleNo="+articleNo;
	
	main.ajax2(url,function(article){
		
		if(article !=null && article !=""){
			
			$("#articleNo").val(articleNo);
			$("#otherName").val(article.otherName ==null ? article.name:article.otherName);
			$("#source").val(article.source);
			$("#writetime").val(main.formatDateNow(article.writetime));
			$("#decription").val(article.decription);
			
			main.selectCheck(article.typeNo,"articleType");
			
			$("#modfiyBtn").click();//弹出框
		}
	});
	
}

var typeData="";//文件类型缓存数据

/**
 * 初始化文件类型 暂时不用1-29
 */
function initArticleType(){
	
	typeData=main.initType("articleType",4);//4=文章可参照type.js中selectType属性
}

/**
 * 保存修改文件信息
 */
function saveArticle(){
	var url ="web/article/modfiyArticle.html";
	var data =$("#articleForm").serialize();
	main.ajax(url,data,"保存",function(){
		initArticleTable(1);
	});
	
	
}
/**
 * 删除文件
 * @param no
 */
function delArticle(){
	
	var checkBoxValueArray = main.fileCheckBox("articleBoxName");
	if (checkBoxValueArray.length > 0) {
		
		var url ="web/article/delArticle.html?noArray="+checkBoxValueArray;
		
		main.del2(url,function(){
			initArticleTable(1);
		});
	}
	
}

/**
 * 代码装换，从文件类型缓存中获取类型名称 废弃1-29
 * @param typeNo
 * @returns
 */
function convertArticleType(typeNo){
	
	for(var i=0;i<typeData.length;i++){
		if(typeNo == typeData[i].no){
			return typeData[i].name;
		}
	}
	
	return "";
}

function clear(){
	$("#otherName").val("");
	$("#source").val("");
	$("#writetime").val("");
	$("#content").val("");
	
}