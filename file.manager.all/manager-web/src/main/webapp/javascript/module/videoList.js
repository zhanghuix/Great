

 var videoImgPath="";//全局变量缩略图图片地址

/**
 * 初始化入口函数
 */
$(document).ready(function() {
	
	//获取视频上传后的地址
	videoImgPath=main.initDownLoadPath("","Video");

	initVideoTable(1);
	
	
	$("#saveVideo").click(function(){
		saveVideo();
	});
	
	$("#clearVideo").click(function(){
		clear();
	});
	
	$("#delVideoAll").click(function(){
		delVideo();
	});
	
	//移动文件弹出框
	$("#moveVideo").click(function(){
		
		move.moveModal("videoBoxName","web/video/moveVideo.html","2",function(){
			//回调函数
			initVideoTable(1);
		});//2=视频，在folder表中type=2
	});
	
});

/**
 *初始化数据
 */
function initVideoTable(pageNow){
	//分页
	if(!page(pageNow)){
		return ;
	}
	 var folderNo =$("#folderNo").val();
	 
	 var cutover=main.getCookie("cutover");//区分1=列表或者0=缩略图
	 
	 var url ="web/video/videoListPage.html?folderNo="+folderNo+"&pageNow="+pageNow+"&cutover="+cutover;
	 
	 main.ajax2(url,function(res){
	
		 $("#videoTbody tr").remove();
		 $("#videoTable tr").remove();
			
		 if(cutover==1){
				buidTableList(res.records);
			}else{
				buidTableImg(res.records);
			}
		 
			if(res.records.length>0){
				//分页
				initPageView(res,"initVideoTable");
			}
		});
	
}




/**
 * 绑定数据，为缩略图
 * @param data
 */
function buidTableImg(data){
	
	 $("#videoTableList").hide();
	
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
			
			var video = data[i];
			
			//截断名称，不能超长
			var tempName = "";
			if(video.otherName !=null){
				tempName = video.otherName;
			}else{
				tempName=video.name;
			}
			
			var name=tempName.substr(0,7)+"...";
			
			var address=video.address;
			if(video.address!=null && video.address.length>6){
				address=video.address.substr(0,6)+"....."
			}
			
			var decription=video.decription;
			if(video.decription!=null && video.decription.length>6){
				decription=video.decription.substr(0,6)+"....."
			}
			
			colume = colume+"<th class='imgTablelist'><div class='col-sm-6 col-md-4'><div class='thumbnail'><img alt='' src='"+(videoImgPath+video.name)+"' class='smaiImg'  ondblclick='imgShowMax(\""+video.name+"\")'><div class='caption'><a href='javascript:void(0)' onClick='toModfiy(\""+video.no+"\")' title='"+tempName+"'>"+name+"</a><p class='smaiImg_p'>时间："+main.formatDateNow(video.videotime)+"<br>地点："+address+"<br>描述："+decription+"</p></div></div></div></th>";
			
			i++;
		}
		
		$("#videoTable").append(row+colume+"</tr>");
		
	}
	
}
/**
 * 绑定数据，列表
 */
function buidTableList(data){
	
	$("#videoTableList").show();

	var tr = "";
	
	if(data !="" && data!=null && data.length>0){
		
		for(var i=0;i<data.length;i++){
			var video = data[i];
			
			if(i%2==0){
				tr=tr+"<tr class='active'>";
			}else{
				tr = tr+"<tr class='warning'>"
			}
			
			var address="";
			if(video.address!=null){
				address=video.address;
			}
			
			var size="0";
			if(video.size!=null){
				size=video.size;
			}
			
			var tempName = "";
			if(video.otherName !=null){
				tempName = video.otherName;
			}else{
				tempName=video.name;
			}
						
			var td = "<td><button class='btn btn-warning' onClick=toModfiy('"+video.no+"')>修改</button></td>";
			
			tr=tr+"<td><input type='checkbox' value='"+video.no+"' name='videoBoxName'/></td><td>"+(i+1)+"</td><td>"+tempName+"</td><td>"+address+"</td><td>"+main.formatDateNow(video.videotime)+"</td><td>"+size+"</td><td>"+main.formatDateTimeNow(video.createtime)+"</td>"+"<td>"+video.userNo+"</td>"+td+"</tr>";
		}
		$("#videoTbody").append(tr);
	}
}

/**
 * 放大
 * @param videoName
 */
function imgShowMax(videoName){
	$("#showImgBtn").click();
	$("#viewImg").attr("src",videoImgPath+videoName);
}

/**
 * 弹出修改视频信息
 * @param videoNo
 */
function toModfiy(videoNo){
	
	var url="web/video/getVideo.html?videoNo="+videoNo;
	
	main.ajax2(url,function(video){
		
		if(video !=null && video !=""){
			
			$("#videoNo").val(videoNo);
			$("#otherName").val(video.otherName ==null ? video.name:video.otherName);
			$("#address").val(video.address);
			$("#videotime").val(main.formatDateNow(video.videotime));
			$("#desc").val(video.decription);
			
			main.selectCheck(video.typeNo,"videoType");
			
			$("#modfiyBtn").click();//弹出框
		}
	});
	
}

var typeData="";//视频类型缓存数据

/**
 * 初始化视频类型 暂时不用1-29
 */
function initVideoType(){
	
	typeData=main.initType("videoType",2);//2=视频可参照type.js中selectType属性
}

/**
 * 保存修改视频信息
 */
function saveVideo(){
	var url ="web/video/modfiyVideo.html";
	var data =$("#videoForm").serialize();
	main.ajax(url,data,"保存",function(){
		initVideoTable(1);
	});
	
	
}
/**
 * 删除视频
 */
function delVideo(){
	
	var checkBoxValueArray = main.fileCheckBox("videoBoxName");
	if (checkBoxValueArray.length > 0) {
		var url ="web/video/delVideo.html?noArray="+checkBoxValueArray;
		
		main.del2(url,function(){
			initVideoTable(1);
		});
	}
}

/**
 * 代码装换，从视频类型缓存中获取类型名称 废弃1-29
 * @param typeNo
 * @returns
 */
function convertVideoType(typeNo){
	
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
	$("#videotime").val("");
	$("#desc").val("");
	
}