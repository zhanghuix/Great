

 var filesPath="";//全局变量文件地址

/**
 * 初始化入口函数
 */
$(document).ready(function() {
	
	//获取文件上传后的地址
	filesPath=main.initDownLoadPath("","File");//拼接下载路径File必须与上传路径中的一致

	initFilesTable(1);
	
	
	$("#saveFiles").click(function(){
		saveFiles();
	});
	
	$("#clearFiles").click(function(){
		clear();
	});
	
	$("#delFilesAll").click(function(){
		delFiles();
	});
	
	//移动文件弹出框
	$("#moveFiles").click(function(){
		move.moveModal("filesBoxName","web/files/moveFiles.html","3",function(){
			//回调函数
			initFilesTable(1);
		});//3=文件，在folder表中type=3
	});
	
	
});

/**
 *初始化数据
 */
function initFilesTable(pageNow){
	
	//分页
	if(!page(pageNow)){
		return ;
	}
	 var folderNo =$("#folderNo").val();
	 
	 var url = "web/files/filesListPage.html?folderNo="+folderNo+"&pageNow="+pageNow;
	 main.ajax2(url,function(res){
		
		 if(res.records.length>0){
				
				buidTableList(res.records);
				 
				//分页
				initPageView(res,"initFilesTable");
			}else{
				$("#filesTbody tr").remove();
			}
	 });
}


/**
 * 绑定数据，列表
 */
function buidTableList(data){

	$("#filesTbody tr").remove();
	
	var tr = "";
	
	if(data !="" && data!=null && data.length>0){
		
		for(var i=0;i<data.length;i++){
			var files = data[i];
			
			if(i%2==0){
				tr=tr+"<tr class='active'>";
			}else{
				tr = tr+"<tr class='warning'>"
			}
			
			var description="";
			if(files.description!=null){
				description=files.description;
			}
			
			var size="0";
			if(files.size!=null){
				size=files.size;
			}
			
			var tempName = "";
			if(files.otherName !=null){
				tempName = files.otherName;
			}else{
				tempName=files.name;
			}
			
			var td = "<td><button class='btn btn-warning' onClick=toModfiy('"+files.no+"')>修改</button> </td>";
			
			tr=tr+"<td><input type='checkbox' value='"+files.no+"' name='filesBoxName'/></td><td>"+(i+1)+"</td><td><a href='"+filesPath+files.name+"'>"+tempName+"</a></td><td>"+size+"</td><td>"+main.formatDateTimeNow(files.createtime)+"</td>"+"<td>"+files.userNo+"</td><td>"+description+"</td>"+td+"</tr>";
		}
		$("#filesTbody").append(tr);
	}
}


/**
 * 弹出修改文件信息
 * @param filesNo
 */
function toModfiy(filesNo){
	
	var url = "web/files/getFiles.html?filesNo="+filesNo;
	
	main.ajax2(url,function(files){
		
		if(files !=null && files !=""){
			
			$("#filesNo").val(filesNo);
			$("#otherName").val(files.otherName ==null ? files.name:files.otherName);
			$("#desc").val(files.description);
			
			main.selectCheck(files.typeNo,"filesType");
			
			$("#modfiyBtn").click();//弹出框
		}
	});
	
}

var typeData="";//文件类型缓存数据

/**
 * 初始化文件类型 暂时不用1-29
 */
function initFilesType(){
	
	typeData=main.initType("filesType",3);//3=文件可参照type.js中selectType属性
}

/**
 * 保存修改文件信息
 */
function saveFiles(){
	var url ="web/files/modfiyFiles.html";
	var data =$("#filesForm").serialize();
	main.ajax(url,data,"保存",function(){
		initFilesTable(1);
	});
	
	
}
/**
 * 删除文件
 */
function delFiles(){
	
	var checkBoxValueArray = main.fileCheckBox("filesBoxName");
	if (checkBoxValueArray.length > 0) {
		
		var url ="web/files/delFiles.html?noArray="+checkBoxValueArray;
		
		main.del2(url,function(){
			initFilesTable(1);
		});
	}
	
}

/**
 * 代码装换，从文件类型缓存中获取类型名称 废弃1-29
 * @param typeNo
 * @returns
 */
function convertFilesType(typeNo){
	
	for(var i=0;i<typeData.length;i++){
		if(typeNo == typeData[i].no){
			return typeData[i].name;
		}
	}
	
	return "";
}

function clear(){
	$("#otherName").val("");
	$("#desc").val("");
	
}