


var main = {

/**
 * 初始化文件夹列表，参数依次为:目标table名称，需要绑定的数据，点击某个文件夹请求数据的url 照片，视频，文件，文章，工具，项目文件夹页面公用绑定方法
 * 
 * @param data
 */
buidTable : function (tableName,data,detailUrl){
	
	 $("#"+tableName+" tr").remove();
	 
	 var photoTable = $("#"+tableName);
	
	 var length = data.length/11;// 计算行数，每页显示3行，每页显示33条数据
	 
	 var i=0;// 计数值，控制数据显示完毕
	
	for(var r=0;r<length;r++){
		
		var row = "<tr>";
		var colume ="";
		
		// 控制列，每列显示11个
		for(var c=0;c<=10;c++){
			
			if(i==data.length){
				break;
			}
			
			var folder = data[i];
			
			/*var checkbox ="<input type='checkbox' value='"+folder.no+"'>";
			//默认文件夹不允许删除
			if(folder.no ==0){
				checkbox="";
			}*/
			
			colume = colume+"<th class='imgTable'><div class='row'><div class='col-sm-6col-md-4'><a class='thumbnail' onClick='main.dispathList(\""+detailUrl+folder.no+"\")' href='javascript:void(0)'><img src='/images/artDialog/tb1.png'></a></div><div class='caption'><p></p><p class='folderName'>"+folder.name+"</p></div></div></th>";
			i++;
		}
		
		photoTable.append(row+colume+"</tr>");
	}
},

/**
 * 控制列表页面在ifreamdiv中显示
 */
dispathList : function(url){
	$.get(url,function(data){
		$("#mainDiv").html(data);
	});
},

/**
 * 设置cookie,全局有效
 */
setCookie : function (name,value){
	$.cookie(name,value,{path:"/"});
},
/**
 * 获取cookie
 */
getCookie : function (name){
	return $.cookie(name);
},
/**
 * 格式化时间 long 123444444444时间戳;
 */
formatDateNow : function (now){
	
	if(now =="" || now==null){
		return "";
	}
	
	now = new Date(now);
	
	var year = now.getFullYear();
	var month = now.getMonth()+1;
	var date = now.getDate();
	
	return year+"-"+month+"-"+date;
},

 /**
	 * 格式化时间 now = new Date();
	 */
formatDateTimeNow : function (now){
	
	if(now =="" || now==null){
		return "";
	}
	
	var tempNow = new Date(now);
	
	var date =main.formatDateNow(now);
	var hour = tempNow.getHours();
	var minute = tempNow.getMinutes();
	var second = tempNow.getSeconds();
	
	return date+" "+hour+":"+minute+":"+second;
},
/**
 * fileNo 文件号 uploadType 文件类型 获取下载路径
 */
initDownLoadPath : function (fileNo,uploadType){
	
	var path="";
	
	$.ajax({
		url:"web/downLoad/downFile.html?fileNo="+fileNo+"&uploadType="+uploadType,
		type:"post",
		dataType:"json",
		async: false,
		cache:false,
		success:function(res){
			
			if(res !=null && res !=""){
				path=res;
			}
			
		},
		error:function(){
			alert("加载数据失败");
		}
		
	});
	
	return path;
},

/**
 * 根据value控制下拉框选中
 * 
 * @param value
 * @param tagName
 *            标签名称
 */
selectCheck : function (value,tagName){
	
	$("#"+tagName+" option").each(function(){
		if($(this).val()==value){
			$(this).attr("selected","selected");
		}
	});
},

/**
 * 初始下拉框 tagName 标签名称 selectArray 数据--数组形
 */
initSelect : function (tagName,selectArray){
	
	$("#"+tagName+" option").remove();
	
	var op="";
	for(var i=1;i<selectArray.length;i++){
		op=op+"<option value="+i+">"+selectArray[i]+"</option>";
	}
	
	$("#"+tagName).append(op);
},

/**
 * 初始下拉框 tagName 标签名称 selectData 数据--key,value形
 */
initSelectData : function (tagName,selectData){
	
	$("#"+tagName+" option").remove();
	
	var op="";
	for(var i=0;i<selectData.length;i++){
		op=op+"<option value="+selectData[i].no+">"+selectData[i].name+"</option>";
	}
	
	$("#"+tagName).append(op);
},




/**
 * 查询文件类型
 * 
 * @param tagName
 *            显示数据的标签名称
 * @param partenNo
 *            文件类型的父类型
 */
initType : function (tagName,partenNo){
	
	var typeData="";
	
	$.ajax({
		url:"web/types/typesListAll.html?partenNo="+partenNo,
		type:"post",
		dataType:"json",
		async: false,
		cache:false,
		success:function(res){
			
			if(res !=null && res !=""){
				main.initSelectData(tagName,res);
				
				typeData=res;
			}
			
		},
		error:function(){
			alert("加载数据失败");
		}
		
	});
	
	return typeData;
},

del2 : function(url,callback){
	main.del(url,"",callback);
},

/**
 * 删除文件夹
 * @param data 数据
 * @param url controller地址
 * @param callback 回调函数
 */
del : function (url,data,callback){
	
	$.confirm({
		title:'删除',
		content:'确定要删除吗？',
		confirmButton:'YES',
		cancelButton:'NO',
		confirmButtonClass:'btn-warning',
		cancelButtonClass:'btn-info',
		confirm:function(){
			main.ajax(url,data,"删除",function(res){
				callback();
			});
			
		},
		cancel:function(){
			
		}
	});
	
},

ajax2 : function(path,callback){
	main.ajax(path,"","",callback);
},

/**
 * ajax提交
 * 
 * @param path
 * @param data
 * @param msg
 */
ajax : function (path,data,msg,callback){
	
	$.ajax({
		url:path,
		data:data,
		type:"post",
		dataType:"json",
		async: true,
		cache:false,
		success:function(res){
			
			if(msg!=""){
				if(res=="1000"){
					alert(msg+"成功");
				}else if(res=="3000"){
					alert(msg+"失败，参数为空！");
				}else{
					alert(msg+"失败！");
				}
				
				callback();
			}else{
				callback(res);
			}
			
			
		},
		error:function(mesg){
			alert(msg+"失败"+mesg);
		}
		
	});
 },
   /**
    * 全选
    * obj=this
    * tagName=checkbox的名称
    */
   checkboxAll : function(obj,tagName){
	   
	   var checkboxs = document.getElementsByName(tagName);
	   
	   for(var i=0;i<checkboxs.length;i++){
		   checkboxs[i].checked=obj.checked;
	   }
   },
   
   /**
	 * 获取通过多选框选中的文件no，并且验证是否未选择 tagName 多选框名称
	 */
	fileCheckBox : function(tagName) {
		
		var noArray = new Array();
		var checkboxArray = document.getElementsByName(tagName);
		for (var i = 0; i < checkboxArray.length; i++) {
			if (checkboxArray[i].checked == true) {
				noArray.push(checkboxArray[i].value);
			}
		}
		if (noArray.length <= 0) {
			alert("请选择一行数据");
		}
		return noArray;
	}
   
}