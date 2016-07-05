

 var projectPath="";//全局变量文件地址

/**
 * 初始化入口函数
 */
$(document).ready(function() {
	
	//获取文件上传后的地址
	projectPath=main.initDownLoadPath("","Project");//拼接下载路径File必须与上传路径中的一致

	initProjectTable(1);
	
	
	$("#saveProject").click(function(){
		saveProject();
	});
	
	$("#clearProject").click(function(){
		clear();
	});
	
	
	$("#delProjectAll").click(function(){
		delProject();
	});
	
	//移动文件弹出框
	$("#moveProject").click(function(){
		move.moveModal("projectBoxName","web/project/moveProject.html","6",function(){
			//回调函数
			initProjectTable(1);
		});//6=项目，在folder表中type=6
	});
	
	
});

/**
 *初始化数据
 */
function initProjectTable(pageNow){
	
	//分页
	if(!page(pageNow)){
		return ;
	}
	 var folderNo =$("#folderNo").val();
	 
	 var url = "web/project/projectListPage.html?folderNo="+folderNo+"&pageNow="+pageNow;
	 
	 main.ajax2(url,function(res){
		
		 $("#projectTbody tr").remove();
			
			if(res.records.length>0){
				
				buidTableList(res.records);
				 
				//分页
				initPageView(res,"initProjectTable");
			}
	 });
	 
}


/**
 * 绑定数据，列表
 */
function buidTableList(data){

	$("#projectTbody tr").remove();
	
	var tr = "";
	
	if(data !="" && data!=null && data.length>0){
		
		for(var i=0;i<data.length;i++){
			var project = data[i];
			
			if(i%2==0){
				tr=tr+"<tr class='active'>";
			}else{
				tr = tr+"<tr class='warning'>"
			}
			
			var description="";
			if(project.description!=null){
				description=project.description;
			}
			
			var size="0";
			if(project.size!=null){
				size=project.size;
			}
			
			var tempName = "";
			if(project.otherName !=null){
				tempName = project.otherName;
			}else{
				tempName=project.name;
			}
			
			var td = "<td><button class='btn btn-warning' onClick=toModfiy('"+project.no+"')>修改</button></td>";
			
			tr=tr+"<td><input type='checkbox' value='"+project.no+"' name='projectBoxName'/></td><td>"+(i+1)+"</td><td><a href='"+projectPath+project.name+"'>"+tempName+"</a></td><td>"+typeData[project.type]+"</td><td>"+(project.company==null?"":project.company)+"</td><td>"+size+"</td><td>"+main.formatDateTimeNow(project.createtime)+"</td><td>"+project.userNo+"</td>"+td+"</tr>";
		}
		$("#projectTbody").append(tr);
	}
}


/**
 * 弹出修改文件信息
 * @param projectNo
 */
function toModfiy(projectNo){
	
	var url = "web/project/getProject.html?projectNo="+projectNo;
	
	 main.ajax2(url,function(project){
		
		 if(project !=null && project !=""){
				
				$("#projectNo").val(projectNo);
				$("#otherName").val(project.otherName ==null ? project.name:project.otherName);
				$("#company").val(project.company);
				$("#takeOn").val(project.takeOn);
				
				main.selectCheck(project.typeNo,"projectType");
				
				$("#modfiyBtn").click();//弹出框
			}
	 });
	
}

var typeData= new Array();//文件类型缓存数据
typeData.push("普通项目");
typeData.push("组件项目");

/**
 * 初始化文件类型 暂时不用1-29
 */
function initProjectType(){
	
	main.initSelect("projectType",typeData);//5=工具可参照type.js中selectType属性
}

/**
 * 保存修改文件信息
 */
function saveProject(){
	var url ="web/project/modfiyProject.html";
	var data =$("#projectForm").serialize();
	main.ajax(url,data,"保存",function(){
		initProjectTable(1);
	});
	
	
}
/**
 * 删除文件
 */
function delProject(){
	
	var checkBoxValueArray = main.fileCheckBox("projectBoxName");
	if (checkBoxValueArray.length > 0) {
		var url ="web/project/delProject.html?noArray="+checkBoxValueArray;
		
		main.del2(url,function(){
			initProjectTable(1);
		});
	}
}


function clear(){
	$("#otherName").val("");
	$("#company").val("");
	$("#takeOn").val("");
	
}