

 var photoImgPath="";//全局变量图片地址

/**
 * 初始化入口函数
 */
$(document).ready(function() {
	
	//获取图片上传后的地址
	photoImgPath=main.initDownLoadPath("","Photo");

	initPhotoTable(1);
	
	
	$("#savePhoto").click(function(){
		savePhoto();
	});
	
	$("#clearPhoto").click(function(){
		clear();
	});
	
	$("#delPhotoAll").click(function(){
		delPhoto();
	});
	
	//移动文件弹出框
	$("#movePhoto").click(function(){
		
		move.moveModal("photoBoxName","web/photo/movePhoto.html","1",function(){
			//回调函数
			initPhotoTable(1);
		});//1=照片，在folder表中type=1
	});
	
	
	
});

/**
 *初始化数据
 */
function initPhotoTable(pageNow){
	
	//分页
	if(!page(pageNow)){
		return ;
	}
	 var folderNo =$("#folderNo").val();
	 
	 var cutover=main.getCookie("cutover");//区分1=列表或者0=缩略图
	 
	 var url="web/photo/photoListPage.html?folderNo="+folderNo+"&pageNow="+pageNow+"&cutover="+cutover;
	 
	 main.ajax2(url,function(res){
	
		 $("#photoTbody tr").remove();
		 $("#photoTable tr").remove();
		 
		 if(cutover==1){
				buidTableList(res.records);
			}else{
				buidTableImg(res.records);
				
			}
			
		 if(res.records.length>0){
			
			//分页
			initPageView(res,"initPhotoTable");
		}
	});
}




/**
 * 绑定数据，为缩略图
 * @param data
 */
function buidTableImg(data){
	
	 $("#photoTableList").hide();//隐藏列表
	 
	
	 if(data ==null || data == ""){
		 return ;
	 }
	
	 var length = data.length/7;//计算行数，每页显示2行，每页显示14条数据
	 
	 var i=0;//计数值，控制数据显示完毕

	for(var r=0;r<2;r++){
		
		var row = "<tr>";
		var colume ="";
		
		//控制列，每列显示7个
		for(var c=0;c<=6;c++){
			
			if(i==data.length){
				break;
			}
			
			var photo = data[i];
			
			//截断名称，不能超长
			var tempName = "";
			if(photo.otherName !=null){
				tempName = photo.otherName;
			}else{
				tempName=photo.name;
			}
			
			var name=tempName.substr(0,5)+"...";
			
			var address=photo.address;
			if(photo.address!=null && photo.address.length>6){
				address=photo.address.substr(0,6)+"....."
			}
			
			var decription=photo.decription;
			if(photo.decription!=null && photo.decription.length>6){
				decription=photo.decription.substr(0,6)+"....."
			}
			
			colume = colume+"<th class='imgTablelist'><div class='col-sm-6 col-md-4'><div class='thumbnail'><img alt='' src='"+(photoImgPath+photo.name)+"' class='smaiImg'  ondblclick='imgShowMax(\""+photo.name+"\")'><div class='caption'><a href='javascript:void(0)' onClick='toModfiy(\""+photo.no+"\")' title='"+tempName+"'>"+name+"</a><p class='smaiImg_p'>时间："+main.formatDateNow(photo.phototime)+"<br>地点："+address+"<br>描述："+decription+"</p></div></div></div></th>";
			
			i++;
		}
		
		$("#photoTable").append(row+colume+"</tr>");
		
	}
	
}
/**
 * 绑定数据，列表
 */
function buidTableList(data){
	
	$("#photoTableList").show();
	

	var tr = "";
	
	if(data !="" && data!=null && data.length>0){
		
		for(var i=0;i<data.length;i++){
			var photo = data[i];
			
			if(i%2==0){
				tr=tr+"<tr class='active'>";
			}else{
				tr = tr+"<tr class='warning'>"
			}
			
			var address="";
			if(photo.address!=null){
				address=photo.address;
			}
			
			var size="0";
			if(photo.size!=null){
				size=photo.size;
			}
			
			var tempName = "";
			if(photo.otherName !=null){
				tempName = photo.otherName;
			}else{
				tempName=photo.name;
			}
			
			var td = "<td><button class='btn btn-warning' onClick=toModfiy('"+photo.no+"')>修改</button></td>";
			
			tr=tr+"<td><input type='checkbox' value='"+photo.no+"' name='photoBoxName'/></td><td>"+(i+1)+"</td><td>"+tempName+"</td><td>"+address+"</td><td>"+main.formatDateNow(photo.phototime)+"</td><td>"+size+"</td><td>"+main.formatDateTimeNow(photo.createtime)+"</td>"+"<td>"+photo.userNo+"</td>"+td+"</tr>";
		}
		$("#photoTbody").append(tr);
	}
}

/**
 * 放大图片
 * @param photoName
 */
function imgShowMax(photoName){
	$("#showImgBtn").click();
	$("#viewImg").attr("src",photoImgPath+photoName);
}

/**
 * 弹出修改图片信息
 * @param photoNo
 */
function toModfiy(photoNo){
	
	var url = "web/photo/getPhoto.html?photoNo="+photoNo;
	
	main.ajax2(url,function(photo){
		
		if(photo !=null && photo !=""){
			
			$("#photoNo").val(photoNo);
			$("#otherName").val(photo.otherName);
			if(photo.otherName =="" || photo.otherName ==null){
				$("#otherName").val(photo.name);
			}
			$("#address").val(photo.address);
			$("#phototime").val(main.formatDateNow(photo.phototime));
			$("#desc").val(photo.decription);
			
			main.selectCheck(photo.typeNo,"photoType");
			
			$("#modfiyBtn").click();//弹出框
		}
	});
	
}

var typeData="";//照片类型缓存数据

/**
 * 初始化照片类型 暂时不用1-29
 */
function initPhotoType(){
	
	typeData=main.initType("photoType",1);//1=照片可参照type.js中selectType属性
}

/**
 * 保存修改照片信息
 */
function savePhoto(){
	var url ="web/photo/modfiyPhoto.html";
	var data =$("#photoForm").serialize();
	main.ajax(url,data,"保存",function(){
		initPhotoTable(1);
	});
	

}
/**
 * 删除照片
 */
function delPhoto(){
	
	var checkBoxValueArray = main.fileCheckBox("photoBoxName");
	if (checkBoxValueArray.length > 0) {
		
		var url ="web/photo/delPhoto.html?noArray="+checkBoxValueArray;
		
		main.del2(url,function(){
			initPhotoTable(1);
		});
	}
	
}

/**
 * 代码装换，从照片类型缓存中获取类型名称 废弃1-29
 * @param typeNo
 * @returns
 */
function convertPhotoType(typeNo){
	
	for(var i=0;i<typeData.length;i++){
		if(typeNo == typeData[i].no){
			return typeData[i].name;
		}
	}
	
	return "";
}

function clear(){
	$("#otherName").val("");
	$("#address").val("");
	$("#phototime").val("");
	$("#desc").val("");
	
}

