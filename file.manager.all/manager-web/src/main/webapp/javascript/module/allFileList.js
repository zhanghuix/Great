

 var fileAllPath="";//全局变量文件地址
 
 
 var folderAll;//所有文件夹folder数据
 
 var typeData="";//文件类型缓存数据

/**
 * 初始化入口函数
 */
$(document).ready(function() {
	
	//获取文件上传后的地址
	fileAllPath=main.initDownLoadPath("","File");//拼接下载路径File必须与上传路径中的一致
	
	//获取文件夹
	getFolder();

	//初始化列表
	initFileAllTable(1);
	
	$("#delFileAll").click(function(){
		delfileAll();
	});
	
});

/**
 *初始化数据
 */
function initFileAllTable(pageNow){
	
	//分页
	if(!page(pageNow)){
		return ;
	}
	 
	 var url = "web/allFile/allListPage.html?pageNow="+pageNow;
	 
	 main.ajax2(url,function(res){
		 if(res.records.length>0){
				
				buidTableList(res.records);
				 
				//分页
				initPageView(res,"initFileAllTable");
			}else{
				$("#fileAllTbody tr").remove();
			}
	 });
	 
}


/**
 * 绑定数据，列表
 */
function buidTableList(data){

	$("#fileAllTbody tr").remove();
	
	var tr = "";
	
	if(data !="" && data!=null && data.length>0){
		
		for(var i=0;i<data.length;i++){
			var fileAll = data[i];
			
			if(i%2==0){
				tr=tr+"<tr class='active'>";
			}else{
				tr = tr+"<tr class='warning'>"
			}
			
			var size="0";
			if(fileAll.size!=null){
				size=fileAll.size;
			}
			
			var type=convertFolderType(fileAll.folderNo);//得到文件所属模块
			var modelName = getFolderTypeName(type);
			
			
			var tempName = "";
			if(fileAll.otherName !=null){
				tempName = fileAll.otherName;
			}else{
				tempName=fileAll.name;
			}
			
			var delFileAllNo = fileAll.no+"-"+type;
			
			var td = "<td></td>";
			
			tr=tr+"<td><input type='checkbox' value='"+delFileAllNo+"' name='fileAllBoxName'/></td><td>"+(i+1)+"</td><td><a href='"+fileAllPath+fileAll.name+"'>"+tempName+"</a></td><td>"+convertFolder(fileAll.folderNo)+"</td><td>"+size+"</td><td>"+main.formatDateTimeNow(fileAll.createtime)+"</td>"+"<td>"+fileAll.userNo+"</td><td>"+modelName+"</td>"+td+"</tr>";
		}
		$("#fileAllTbody").append(tr);
	}
}


/**
 * 保存修改文件信息
 */
function savefileAll(){
	var url ="web/fileAll/modfiyfileAll.html";
	var data =$("#fileAllForm").serialize();
	main.ajax(url,data,"保存",function(){
		initFileAllTable(1);
	});
	
	
}
/**
 * 删除文件
 * @param folderNo
 */
function delfileAll(){
	
	
	var checkBoxValueArray = main.fileCheckBox("fileAllBoxName");
	if (checkBoxValueArray.length > 0) {
		
		//checkBoxValueArray = JSON.stringify(checkBoxValueArray);
		
		var url ="web/allFile/delfileAll.html?noArray="+checkBoxValueArray;
		
		main.del2(url,function(){
			initFileAllTable(1);
		});
	}
}

/**
 * 代码装换，从文件夹缓存中获取名称
 * @param folderNo
 * @returns
 */
function convertFolder(folderNo){
	
	for(var i=0;i<folderAll.length;i++){
		if(folderNo == folderAll[i].no){
			return folderAll[i].name;
		}
	}
	
	return folderNo;
}

/**
 * 转换文件所属模块
 * @param folderNo
 * @returns
 */
function convertFolderType(folderNo){

	for(var i=0;i<folderAll.length;i++){
		if(folderNo == folderAll[i].no){
			
			var type =folderAll[i].type;
			
			return type;
		}
	}
	
	return "";
}

/**
 * 根据编号返回名称，根类型节点
 * @param type
 * @returns
 */
function getFolderTypeName(type){
	var typeArray = new Array();
	typeArray.push("默认");
	typeArray.push("照片");
	typeArray.push("视频");
	typeArray.push("文件");
	typeArray.push("文章");
	typeArray.push("工具");
	typeArray.push("项目");
	
	if(type==""){
		return "";
	}
	
	return typeArray[type];
}


/**
 * 获取文件夹做代码转换
 */
function getFolder(){
	
	
	var url = "web/folder/folderListAll.html";
	
	 main.ajax2(url,function(folderList){
		 if(folderList !=null && folderList !=""){
				
				folderAll = folderList;
			}
	 });
}