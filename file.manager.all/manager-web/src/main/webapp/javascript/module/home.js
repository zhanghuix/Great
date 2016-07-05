
/*******************************************************
 *    主页js，上传，文件夹下拉框功能                            *
 *                                                     *
 *******************************************************/


/** 全局变量，添加上传行的行数，数据为自然数123~~~，存放的数据拼接上传行控件的id使之唯一 */
var addPhotoRowArray = new Array();
var addVideoRowArray = new Array();
var addFileRowArray = new Array();
var addArticleRowArray = new Array();
var addToolsRowArray = new Array();
var addProjectRowArray = new Array();
/**
 * 扩展数组删除方法
 */
Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == val) {
			return i;
		}
	}
	return -1;
}

Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
}



/**
 * 初始化入口函数
 */
$(document).ready(function() {
	
	
	/**
	 * 初始化可以添加行的上传下载控件
	 */
	initRow("photo", "addPhotoRow");
	initRow("video", "addvideoRow");
	initRow("file", "addFileRow");
	initRow("article", "addArticleRow");
	initRow("tools", "addToolsRow");
	initRow("project", "addProjectRow");

	initAddrowButton();
	initUploadButton();
	initCalcelButton();
	//初始化文件夹类型
	initFolderType();
	
});

function initUploadButton() {
	/**
	 * 上传图片
	 */
	$("#photoUploadButton").click(function() {

		//photoFolderDrop来之home.jsp，代表了上传文件所属的文件夹no
		ajaxUpload("photoUploadForm",$("#photoFolderDrop").val(),"web/photo/addPhoto.html");
		
	});
	/**
	 * 上传视频
	 */
	$("#videoUploadButton").click(function() {
		
		ajaxUpload("videoUploadForm",$("#videoFolderDrop").val(),"web/video/addVideo.html");

	});
	/**
	 * 上传文件
	 */
	$("#fileUploadButton").click(function() {

		ajaxUpload("fileUploadForm",$("#fileFolderDrop").val(),"web/files/addFiles.html");

	});

	/**
	 * 上传文章
	 */
	$("#articleUploadButton").click(function() {

		ajaxUpload("articleUploadForm",$("#articleFolderDrop").val(),"web/article/addArticle.html");

	});

	/**
	 * 上传项目
	 */
	$("#projectUploadButton").click(function() {

		ajaxUpload("projectUploadForm",$("#projectFolderDrop").val(),"web/project/addProject.html");

	});

	/**
	 * 上传工具
	 */
	$("#toolsUploadButton").click(function() {

		ajaxUpload("toolsUploadForm",$("#toolsFolderDrop").val(),"web/tools/addTools.html");

	});
}

function initAddrowButton() {
	/**
	 * 添加上传行,照片
	 */
	$("#addPhotoRow").click(function() {

		if (addPhotoRowArray.length > 3) {
			alert("一次最多上传５个文件!");
			return;
		}

		addRow("photo", addPhotoRowArray);
	});

	/**
	 * 添加上传行，视频
	 */
	$("#addvideoRow").click(function() {
		if (addVideoRowArray.length > 3) {
			alert("一次最多上传５个文件!");
			return;
		}

		addRow("video", addVideoRowArray);
	});

	/**
	 * 添加上传行，文件
	 */
	$("#addFileRow").click(function() {
		if (addFileRowArray.length > 3) {
			alert("一次最多上传５个文件!");
			return;
		}

		addRow("file", addFileRowArray);
	});

	/**
	 * 添加上传行，文章
	 */
	$("#addArticleRow").click(function() {
		if (addArticleRowArray.length > 3) {
			alert("一次最多上传５个文件!");
			return;
		}

		addRow("article", addArticleRowArray);
	});

	/**
	 * 添加上传行，工具
	 */
	$("#addToolsRow").click(function() {
		if (addToolsRowArray.length > 3) {
			alert("一次最多上传５个文件!");
			return;
		}

		addRow("tools", addToolsRowArray);
	});

	/**
	 * 添加上传行，工具
	 */
	$("#addProjectRow").click(function() {
		if (addProjectRowArray.length > 3) {
			alert("一次最多上传５个文件!");
			return;
		}

		addRow("project", addProjectRowArray);
	});
}

function initCalcelButton() {
	/**
	 * 取消上传图片
	 */
	$("#photoCancelUploadButton").click(function() {

		cancleUpload();

	});
	/**
	 * 取消上传视频
	 */
	$("#videoCancelUploadButton").click(function() {

		cancleUpload();

	});
	/**
	 * 取消上传文件
	 */
	$("#fileCancelUploadButton").click(function() {

		cancleUpload();

	});

	/**
	 * 取消上传文章
	 */
	$("#articleCancelUploadButton").click(function() {

		cancleUpload();

	});

	/**
	 * 取消上传工具
	 */
	$("#toolsCancelUploadButton").click(function() {

		cancleUpload();
	});
	/**
	 * 取消上传项目
	 */
	$("#projectCancelUploadButton").click(function() {

		cancleUpload();

	});
}
/**
 * 初始化文件类型下拉框选择
 */
function initFolderType(){
	//获得下拉框中的文件夹
	$.ajax({
		type : "post",
		url : "web/folder/folderListAll.html",
		dataType : "json",
		success : function(res) {
			
			$("#photoFolderDrop option").remove();
			$("#videoFolderDrop option").remove();
			$("#fileFolderDrop option").remove();
			$("#articleFolderDrop option").remove();
			$("#toolsFolderDrop option").remove();
			$("#projectFolderDrop option").remove();
			
			if(res !=null){
				
				for(var i=0;i<res.length;i++){
					
					var folder = res[i];
					
					//1和0代表照片与默认文件夹
					if("0"==folder.type || "1"==folder.type){
						//$("#photoFolderDrop").append("<li value='"+folder.no+"'><a href='javascript:void(0)' onClick='folderDrop(\""+folder.no+"\",\""+folder.name+"\",\"photo\")'>"+folder.name+"</a></li>");
						$("#photoFolderDrop").append("<option value="+folder.no+">"+folder.name+"</option>");
					}
					//视频与默认文件夹
					if("0"==folder.type || "2"==folder.type){
						$("#videoFolderDrop").append("<option value="+folder.no+">"+folder.name+"</option>");
					}
					//文件与默认文件夹
					if("0"==folder.type || "3"==folder.type){
						$("#fileFolderDrop").append("<option value="+folder.no+">"+folder.name+"</option>");
					}
					//文章与默认文件夹
					if("0"==folder.type || "4"==folder.type){
						$("#articleFolderDrop").append("<option value="+folder.no+">"+folder.name+"</option>");
					}
					//工具与默认文件夹
					if("0"==folder.type || "5"==folder.type){
						$("#toolsFolderDrop").append("<option value="+folder.no+">"+folder.name+"</option>");
					}
					//项目与默认文件夹
					if("0"==folder.type || "6"==folder.type){
						$("#projectFolderDrop").append("<option value="+folder.no+">"+folder.name+"</option>");
					}
				}
				
			}
			
		},
		error : function(res) {

		}
	});
}
/**
 * 设置下拉框值 废弃2016-1-29
 * @param no 文件夹编号
 * @param name 文件夹名称
 * @param lable 标签名称
 */
/*function folderDrop(no,name,lable){

	$("#"+lable+"Lable").html(name);//显示选中的标签
	$("#"+lable+"FolderValue").val(no);//提交后台的标签
}*/

/**
 * 初始化上传下载行，添加行
 * 
 * @param rowName
 *            控件id
 * @param name
 *            浏览按钮的id名称
 */
function initRow(rowName, name) {

	var init = "<a href='javascript:void(0);' id='" + name+ "'><img src='images/artDialog/add.png' class='addImage' /></a>";

	var row = "<input name='addrow' type='text' class='newFileInout' style='height:5%;' id='"+ rowName+ "Name'><input type='button' id='"+ rowName+ "Button' value='浏览...' class='btn fileTag' onclick='"+ rowName+ "Upload.click()'><br id='" + rowName + "br'>";

	var file = "<input name='" + rowName+ "file' class='fileInput' style='height:8%;' type='file' id='" + rowName+ "Upload' onchange='" + rowName + "Name.value=this.value' />";

	$("#" + rowName + "FileDiv").append(init + row + file);

}
/**
 * 公共方法 动态添加上传行 通过rowName作为组件id前缀 和rowName作为组件id后缀
 * 来确定组件id全局唯一，因为是公共方法处理，所以必须传入rowName和tempAddRowArray来分别处理 rowName
 * 每个tab页下所有组件的id前缀名称，控制id唯一，并且可以区别属于什么上传类型的上传控件
 */
function addRow(rowName, tempAddRowArray) {

	var rowNo = checkAddRowArray(tempAddRowArray);

	var del = "<a href='javascript:void(0);' id='"+ rowName+ "DelRow"+ rowNo+ "' onclick='delRow("+ rowNo+ ",\""+ rowName+ "\")'><img src='images/artDialog/delete.png' class='addImage' /></a>";

	var addRow = "<input name='addrow' type='text' class='newFileInout' style='height:5%;' id='"+ rowName+ "Name"+ rowNo+ "'><input type='button' id='"+ rowName+ "Button"+ rowNo+ "' value='浏览...' class='btn fileTag' onclick='"+ rowName+ "Upload"+ rowNo+ ".click()'><br id='"+ rowName+ "br"+ rowNo+ "'>";

	var file = "<input name='file" + rowNo+ "' class='fileInput' style='height:8%;' type='file' id='" + rowName + "Upload"+ rowNo + "' onchange='" + rowName + "Name" + rowNo+ ".value=this.value' />";

	$("#" + rowName + "FileDiv").append(del + addRow + file);

}
/**
 * 公共方法
 * 动态删除上传行，通过rowName作为组件id前缀和row作为组件id后缀来确定id唯一，因为是公共方法处理，所以必须传入row和rowName来区分，分别处理
 * 
 * @param row
 *            标示id唯一后缀
 * @param rowName
 *            区分tab页下各上传组件id唯一的 前缀
 */
function delRow(row, rowName) {

	$("#" + rowName + "DelRow" + row).remove();
	$("#" + rowName + "Name" + row).remove();
	$("#" + rowName + "Upload" + row).remove();
	$("#" + rowName + "Button" + row).remove();
	$("#" + rowName + "br" + row).remove();
	if ("photo" == rowName) {
		addPhotoRowArray.remove(row);
	}
	if ("video" == rowName) {
		addVideoRowArray.remove(row);
	}
	if ("file" == rowName) {
		addFileRowArray.remove(row);
	}
	if ("article" == rowName) {
		addArticleRowArray.remove(row);
	}

	if ("tools" == rowName) {
		addToolsRowArray.remove(row);
	}
	if ("project" == rowName) {
		addProjectRowArray.remove(row);
	}
}

/**
 * 控制对应上传控件，添加行的数量。
 * 
 * @param tempAddRowArray
 *            传入的添加上传行行数的数组。
 */
function checkAddRowArray(tempAddRowArray) {

	var rowNo = 0; // 数字类型，1---100，作为控件id后缀

	if (tempAddRowArray.length == 0) {
		rowNo = rowNo + 1;
	} else {
		rowNo = tempAddRowArray[tempAddRowArray.length - 1] + 1;
	}

	tempAddRowArray.push(rowNo);

	return rowNo;
}

/**
 * 保存上传完成的数据
 * @param folderType 上传文件所属的文件夹
 * @param resData 上传文件的物理路径
 */
function saveUpload(resData,folderType,url){
	
	var temp_resData = JSON.stringify(resData);
	
	$.ajax({
		type : "post",
		url : url,
		data : {"filePaths":temp_resData,"folderValue":folderType},
		dataType : "json",
		async : true,
		success : function(res) {
			if(res =="1000"){
				//alert("上传成功");
			}else{
				alert("上传异常");
			}
		},
		error : function(res) {
			alert("上传失败！");
		}
	});
}

/**
 * 公共方法，ajax提交form
 * @param folderType 所属文件夹no
 * @param uploadForm 
 * @param url 请求保存的url
 */
function ajaxUpload(uploadForm,folderType,url) {

	var isEmpty = false;

	//验证上传是否有空行
	$("#" + uploadForm + " [name='addrow']").each(function() {

		if ($(this).val() == "") {
			isEmpty = true;
			return;
		}
	});
	if (!isEmpty) {
		
		//var action =$("#"+uploadForm).attr("action")+"&folderValue=" ;
		//var folderValue=$("#"+uploadForm+" [name=folderValue]").val();

		//使用了一个类似定时器的组件，定时发送请求并获得结果，源码以被修改。
		var periodicalExe = new PeriodicalExecuter(refreshUploadStatus, 0.5,uploadForm);

		$("#" + uploadForm).ajaxSubmit({
			type : "post",
			dataType : "json",
			async : false,
			success : function(res, ress) {
				var resData = JSON.parse(res);
				//alert("上传了" + data.length + "个文件");
				if (resData.length > 0) {
					clearFile(uploadForm);
				}
				
				//上传完后保存上传文件名称以及文件物理路径
				saveUpload(resData,folderType,url)
			},
			error : function() {
				alert("上传失败,请重新上传");
			}
		});
		
	} else {
		alert("请选择上传文件");
	}

}

/**
 * 清空上传文件框
 */
function clearFile(uploadForm) {
	$("#" + uploadForm + " [name='addrow']").val("");
}

/**
 * 取消上传
 */
function cancleUpload() {

	$.ajax({
		type : "post",
		url : "fileManager/upload/cancleUpload.html",
		dataType : "json",
		success : function(res) {

		},
		error : function(res) {

		}
	});
}


/**
 * 更新上传状态，并控制状态条，
 */
function refreshUploadStatus(uploadForm) {

	var int = 0;
	$.ajax({
		type : "post",
		url : "fileManager/upload/updateStatus.html",
		dataType : "json",
		async : false, //必须同步执行，否则不能操作此函数返回值
		success : function(res) {
			var data = JSON.parse(res);
			int = data.isOver;
			statusBar(uploadForm,data);
		},
		error : function(res) {
			int = 2;
		}
	});

	if (int == 0) {
		return false;
	} else if (int == 2) {
		int=0;
		return true;
	} else {
		int=0;
		return true;
	}
}

/**
 * 控制进度条
 * @param uploadForm 使用form定位进度条
 * @param data
 */
function statusBar(uploadForm,data){
	//$("#" + uploadForm+" #statusBar").html(data.status);
	
	var size = (data.readTotalSize/data.uploadTotalSize)*100;
	
	
	$("#" + uploadForm+" #statusBar").width(Math.round(size)*3.8);//乘以3.8才能让进度条在100%的时候显示完整
	
	$("#" + uploadForm+" #statusBar").html(Math.round(size)+"%");
}