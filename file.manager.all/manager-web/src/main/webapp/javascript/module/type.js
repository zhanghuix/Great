/*******************************************************
 *    类型设置功能js                                       *
 *                                                     *
 *******************************************************/

//父类型定义
var selectType = new Array();
selectType.push("默认");//代表0
selectType.push("照片");//代表1
selectType.push("视频");//代表2
selectType.push("文件");//代表3
selectType.push("文章");//代表4
selectType.push("工具");//代表5
selectType.push("项目");//代表6

/**
 * 初始化入口函数
 */
$(document).ready(function() {
	
	$("#saveType").click(function(){
		addType();
	});
	
	$("#clearType").click(function(){
		clearType();
	});
	
	$("#addType").click(function(){
		clearType();
	});
	
	initTypeList(1);
	
	main.initSelect("partenNo",selectType);
});

/**
 * 初始化列表数据
 */
function initTypeList(pageNow){
	
	//分页
	if(!page3(pageNow)){
		return ;
	}
	
	var url = "web/types/typesList.html?pageNow="+pageNow;
	
	 main.ajax2(url,function(res){
		 if(res.records.length>0){
				initDataForTypeTable(res.records);
				//分页
				initPageView3(res,"initTypeList");
			}
	 });
}

/**
 * 绑定table数据
 * @param data
 */
function initDataForTypeTable(data){
	
	$("#typeTbody tr").remove();
	
	var tr = "";
	
	
	if(data !="" && data.length>0){
		
		for(var i=0;i<data.length;i++){
			var types = data[i];
			
			if(i%2==0){
				tr=tr+"<tr class='active'>";
			}else{
				tr = tr+"<tr class='warning'>"
			}
			
			
			
			var td = "<td><button class='btn btn-warning' data-toggle='modal' data-target='#typeModal' onClick=toTypeModfiy('"+types.no+"')>修改</button> <a class='btn btn-danger' href='javascript:void(0)' role='button' onclick=delType('"+types.no+"')>删除</a></td>";
			
			tr=tr+"<td>"+(i+1)+"</td><td>"+types.name+"</td><td>"+selectType[types.partenNo]+"</td><td>"+types.userNo+"</td><td>"+main.formatDateTimeNow(types.createtime)+"</td>"+td+"</tr>";
		}
		$("#typeTbody").append(tr);
	}
}
/**
 * 添加或者修改保存
 */
function addType(){
	
	if($("#typeName").val()==""){
		alert("类型名称不能为空");
		return ;
	}
	if($("#partenNo").val()==""){
		alert("请选择上级类型");
		return;
	}
	
	main.ajax("web/types/addTypes.html",$("#typeForm").serialize(),"保存",function(){
		initTypeList(1);
	});
	
	
}
/**
 * 跳转修改
 * @param no
 */
function toTypeModfiy(no){
	
	var url = "web/types/getTypes.html?no="+no;
	
	main.ajax2(url,function(res){
		if(type!=null){
			$("#typeName").val(type.name);
			$("#typeNo").val(type.no);//用来区别保存还是修改
			main.selectCheck(type.partenNo,"partenNo");
		}else{
			alert("加载数据失败！");
		}
	});
	
}
/**
 * 删除
 * @param no
 */
function delType(no){
	
	var url ="web/types/delTypes.html?no="+no;
	
	main.del2(url,function(){
		initTypeList(1);
	});
	
	
}



function clearType(){
	$("#typeName").val("");
	main.initSelect("partenNo",selectType);
}


