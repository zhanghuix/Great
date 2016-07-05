/*******************************************************
 *    文件设置功能js                                       *
 *                                                     *
 *******************************************************/

var select = new Array();
	select.push("通用");//代表0
	select.push("照片");//代表1
	select.push("视频");//代表2
	select.push("文件");//代表3
	select.push("文章");//代表4
	select.push("工具");//代表5
	select.push("项目");//代表6

/**
 * 初始化入口函数
 */
$(document).ready(function() {
	
	$("#saveFolder").click(function(){
		addFolder();
	});
	
	$("#clearFolder").click(function(){
		clear();
	});
	
	$("#addFolder").click(function(){
		clear();
	});
	
	initFolderList(1);
	
	main.initSelect("type",select);
});

/**
 * 初始化列表数据
 */
function initFolderList(pageNow){
	
	//分页
	if(!page(pageNow)){
		return ;
	}
	
	var url = "web/folder/folderList.html?pageNow="+pageNow;
	
	main.ajax2(url,function(res){
		if(res.records.length>0){
			initDataForTable(res.records);
			//分页
			initPageView(res,"initFolderList");
		}else{
			$("#folderTbody tr").remove();
		}
	 });
	
}
/**
 * 绑定table数据
 * @param data
 */
function initDataForTable(data){
	
	$("#folderTbody tr").remove();
	
	var tr = "";
	
	
	if(data !="" && data.length>0){
		
		for(var i=0;i<data.length;i++){
			var folder = data[i];
			
			if(i%2==0){
				tr=tr+"<tr class='active'>";
			}else{
				tr = tr+"<tr class='warning'>"
			}
			var createtime=main.formatDateTimeNow(folder.createtime);
			
			var td = "<td><button class='btn btn-warning' data-toggle='modal' data-target='#myModal' onClick=toModfiy('"+folder.no+"')>修改</button> <a class='btn btn-danger' href='javascript:void(0)' role='button' onclick=del('"+folder.no+"')>删除</a></td>";
			
			tr=tr+"<td>"+(i+1)+"</td><td>"+folder.name+"</td><td>"+select[folder.type]+"</td><td>"+folder.userNo+"</td><td>"+createtime+"</td><td>"+folder.description+"</td>"+td+"</tr>";
		}
		$("#folderTbody").append(tr);
	}
}
/**
 * 添加或者修改保存
 */
function addFolder(){
	
	if($("#name").val()==""){
		alert("文件夹名称不能为空");
		return ;
	}
	if($("#type").val()==""){
		alert("请选择文件夹类型");
		return;
	}
	
	main.ajax("web/folder/addFolder.html",$("#folderForm").serialize(),"保存",function(){
		initFolderList(1);
	});
	
	
}
/**
 * 跳转修改
 * @param no
 */
function toModfiy(no){
	
	var url = "web/folder/getFolder.html?no="+no;
	
	main.ajax2(url,function(folder){
		
		if(folder!=null){
			$("#name").val(folder.name);
			$("#desc").val(folder.description);
			$("#folderNo").val(folder.no);//用来区别保存还是修改
			main.selectCheck(folder.type,"type");
		}else{
			alert("加载数据失败！");
		}
	});
	
}
/**
 * 删除文件夹
 * @param no
 */
function del(no){
	
	var url ="web/folder/delFolder.html?no="+no;
	main.del2(url,function(){
		initFolderList(1);
	});
	
}


function clear(){
	$("#name").val("");
	$("#desc").val("");
	main.initSelect("type",select);
}

