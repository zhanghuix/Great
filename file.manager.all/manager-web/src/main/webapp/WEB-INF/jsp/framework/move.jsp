<!-- 公共组件jsp -->

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<style type="text/css">
div.content_wrap {
	width: 600px;
	height: 380px;
}

div.content_wrap div.left {
	float: left;
	width: 50px;
}

div.content_wrap div.right {
	float: right;
	width: 140px;
}

div.zTreeDemoBackground {
	width: 250px;
	height: 362px;
	text-align: center;
}

ul.ztree {
	margin-top: 10px;
	border: 1px solid #617775;
	background: #f0f6e4;
	width: 220px;
	height: 360px;
	overflow-y: scroll;
	overflow-x: auto;
}
</style>


<button class="btn btn-primary" data-toggle="modal"
	data-target="#myMoveModal" id="moveHiddenBtn" style="display: none"></button>
<!-- 弹出修改文件信息框 -->
<div id="myMoveModal" class="modal fade" tabindex="-1" role="dialog"
	aria-lableledby="myMoveModalLabel" style="display: none;">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="moal-title" id="myMoveModalLabel">移动文件</h4>
			</div>
			<div class="modal-bbody">
				<p></p>

				<div align="center">
					<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" id="moveFileBtn">移动</button>
			</div>
		</div>
	</div>
</div>

<input type="hidden" id="checkboxNames" value="">
<input type="hidden" id="reqUrl" value="">
<script type="text/javascript">

//被选中的树节点no
var folderNo="";


var move = {
			
		//绑定移动按钮单击事件
		moveFile : function(callback) {

			$("#moveFileBtn").click(function() {
				
				var checkBoxValues = main.fileCheckBox($("#checkboxNames").val());
				if (checkBoxValues.length > 0) {
					var data = "?no=" + checkBoxValues + "&folderNo="+folderNo;
					main.ajax($("#reqUrl").val() + data, "", "移动文件",function(){
						//回调函数，执行刷新列表
						callback();
					});
					
				}
			});
		},

		//初始化树,type为folder表中文件夹主类型
		initMove : function(type,callback) {
			
			$.ajax({
				url:"/web/folder/getFolderTree.html?type="+type,
				type:"post",
				dataType:"json",
				async: true,
				cache:false,
				success:function(resData){
					
					if(resData !=null && resData !=""){
						resData = JSON.parse(resData);
						$.fn.zTree.init($("#treeDemo"), move.setting, resData);
					}else{
						alert("初始化文件夹树失败");
					}
					
				},
				error:function(mesg){
					alert(msg+"失败"+mesg);
				}
				
			});
			
			move.moveFile(callback);
		},

		//弹出移动文件筐
		moveModal : function(checkboxNames, reqUrl,type,callback) {
			
			//缓存请求地址和选中的文件
			$("#checkboxNames").val(checkboxNames);
			$("#reqUrl").val(reqUrl);
			$("#initTable").attr("href","javascript:initPhotoTable(1)");
			
			$("#moveHiddenBtn").click();

			move.initMove(type,callback);
		},

		
		/**
		 * 设置树属性
		 */
		setting : {
			data : {
				simpleData : {
					enable : true
				}
			},
			callback: {
				onClick: treeOnClick
			}
		}
		
		
		
		//示例树数据
		/* zNodes :[
		     		{ id:1, pId:0, name:"展开、折叠 自定义图标不同", open:true, iconOpen:"plugin/jquery/css/zTreeStyle/img/diy/1_open.png", iconClose:"plugin/jquery/css/zTreeStyle/img/diy/1_close.png"},
		     		{ id:11, pId:1, name:"叶子节点1", icon:"plugin/jquery/css/zTreeStyle/img/diy/2.png"},
		     		{ id:12, pId:1, name:"叶子节点2", icon:"plugin/jquery/css/zTreeStyle/img/diy/3.png"},
		     		{ id:13, pId:1, name:"叶子节点3", icon:"plugin/jquery/css/zTreeStyle/img/diy/5.png"},
		     		{ id:2, pId:0, name:"展开、折叠 自定义图标相同", open:true, icon:"plugin/jquery/css/zTreeStyle/img/diy/4.png"},
		     		{ id:21, pId:2, name:"叶子节点1", icon:"plugin/jquery/css/zTreeStyle/img/diy/6.png"},
		     		{ id:22, pId:2, name:"叶子节点2", icon:"plugin/jquery/css/zTreeStyle/img/diy/7.png"},
		     		{ id:23, pId:2, name:"叶子节点3", icon:"plugin/jquery/css/zTreeStyle/img/diy/8.png"},
		     		{ id:3, pId:0, name:"不使用自定义图标", open:true },
		     		{ id:31, pId:3, name:"叶子节点1"},
		     		{ id:32, pId:3, name:"叶子节点2"},
		     		{ id:33, pId:3, name:"叶子节点3"}

		     	] */

	}
	
	
	/**
	 * 点击数节点事件,获取选中节点的no
	 */
	function treeOnClick(event, treeId, treeNode, clickFlag){
		folderNo=treeNode.id;
	}
</script>