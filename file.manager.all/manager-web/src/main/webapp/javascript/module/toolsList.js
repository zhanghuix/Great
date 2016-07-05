

 var toolsPath="";//全局变量文件地址

/**
 * 初始化入口函数
 */
$(document).ready(function() {
	
	//获取文件上传后的地址
	toolsPath=main.initDownLoadPath("","Tools");//拼接下载路径File必须与上传路径中的一致

	initToolsTable(1);
	
	
	$("#saveTools").click(function(){
		saveTools();
	});
	
	$("#clearTools").click(function(){
		clear();
	});
	
	$("#delToolsAll").click(function(){
		delTools();
	});
	
	//移动文件弹出框
	$("#moveTools").click(function(){
		
		move.moveModal("toolsBoxName","web/tools/moveTools.html","5",function(){
			//回调函数
			initToolsTable(1);
		});//5=工具，在folder表中type=5
	});
	
	
});

/**
 *初始化数据
 */
function initToolsTable(pageNow){
	
	//分页
	if(!page(pageNow)){
		return ;
	}
	 var folderNo =$("#folderNo").val();
	 
	 var url = "web/tools/toolsListPage.html?folderNo="+folderNo+"&pageNow="+pageNow;
	 
	 main.ajax2(url,function(res){
		 $("#toolsTbody tr").remove();
			
			if(res.records.length>0){
				
				buidTableList(res.records);
				 
				//分页
				initPageView(res,"initToolsTable");
			}
	 });
}


/**
 * 绑定数据，列表
 */
function buidTableList(data){

	$("#toolsTbody tr").remove();
	
	var tr = "";
	
	if(data !="" && data!=null && data.length>0){
		
		for(var i=0;i<data.length;i++){
			var tools = data[i];
			
			if(i%2==0){
				tr=tr+"<tr class='active'>";
			}else{
				tr = tr+"<tr class='warning'>"
			}
			
			var description="";
			if(tools.description!=null){
				description=tools.description;
			}
			
			var size="0";
			if(tools.size!=null){
				size=tools.size;
			}
			
			var tempName = "";
			if(tools.otherName !=null){
				tempName = tools.otherName;
			}else{
				tempName=tools.name;
			}
			
			var td = "<td><button class='btn btn-warning' onClick=toModfiy('"+tools.no+"')>修改</button></td>";
			
			tr=tr+"<td><input type='checkbox' value='"+tools.no+"' name='toolsBoxName'/></td><td>"+(i+1)+"</td><td><a href='"+toolsPath+tools.name+"'>"+tempName+"</a></td><td>"+(tools.version==null?"":tools.version)+"</td><td>"+size+"</td><td>"+main.formatDateTimeNow(tools.createtime)+"</td><td>"+tools.userNo+"</td>"+td+"</tr>";
		}
		$("#toolsTbody").append(tr);
	}
}


/**
 * 弹出修改文件信息
 * @param toolsNo
 */
function toModfiy(toolsNo){
	
	var url = "web/tools/getTools.html?toolsNo="+toolsNo;
	
	 main.ajax2(url,function(tools){
		
		 if(tools !=null && tools !=""){
				
				$("#toolsNo").val(toolsNo);
				$("#otherName").val(tools.otherName ==null ? tools.name:tools.otherName);
				$("#version").val(tools.version);
				$("#description").val(tools.description);
				
				main.selectCheck(tools.typeNo,"toolsType");
				
				$("#modfiyBtn").click();//弹出框
			}
	 });
}

var typeData="";//文件类型缓存数据

/**
 * 初始化文件类型 暂时不用1-29
 */
function initToolsType(){
	
	typeData=main.initType("toolsType",5);//5=工具可参照type.js中selectType属性
}

/**
 * 保存修改文件信息
 */
function saveTools(){
	var url ="web/tools/modfiyTools.html";
	var data =$("#toolsForm").serialize();
	main.ajax(url,data,"保存",function(){
		initToolsTable(1);
	});
	

}
/**
 * 删除文件
 */
function delTools(){
	
	var checkBoxValueArray = main.fileCheckBox("toolsBoxName");
	if (checkBoxValueArray.length > 0) {
		var url ="web/tools/delTools.html?noArray="+checkBoxValueArray;
		
		main.del2(url,function(){
			initToolsTable(1);
		});
	}
	
}

/**
 * 代码装换，从文件类型缓存中获取类型名称 废弃1-29
 * @param typeNo
 * @returns
 */
function convertToolsType(typeNo){
	
	for(var i=0;i<typeData.length;i++){
		if(typeNo == typeData[i].no){
			return typeData[i].name;
		}
	}
	
	return "";
}

function clear(){
	$("#otherName").val("");
	$("#version").val("");
	$("#description").val("");
	
}