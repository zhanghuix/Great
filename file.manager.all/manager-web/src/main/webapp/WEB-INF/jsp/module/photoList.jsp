<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../common/common.jsp"></jsp:include>

<!-- 照片文件列表页面 -->


<!-- photoFoleder.jsp页面提交过来的文件夹编号 -->
<input id="folderNo" value="${folderNo}" type="hidden"/>


<!-- 缩略图列表 -->
<table style="margin-left: 1%;" id="photoTable">
	<!-- js动态绑定 -->
</table>

<!-- 表格列表 -->
<table class="table table-hover" style="font-size: 12px;text-align: center;"
	id="photoTableList">
	<tr><th><input type="checkbox" onclick="main.checkboxAll(this,'photoBoxName')"></th><th>序号</th><th>照片名称</th><th>拍照地点</th><th>拍照时间</th><th>照片大小</th><th>上传时间</th><th>上传人</th>
	    <th><a href="javascript:void(0)" id="movePhoto"><img src="/images/artDialog/tb6.png"></a>
	        &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" id="delPhotoAll"><img src="/images/artDialog/delete1.png"></a>
	    </th>
	</tr>
	<!-- js动态绑定 -->
	<tbody id="photoTbody">
	
	</tbody>
</table>

<!-- 放大图片 -->
<button class="btn btn-primary" data-toggle="modal"
		data-target="#showImg" id="showImgBtn" style="display: none"></button>
<div id="showImg" class="modal maxImg" tabindex="-1" role="dialog" align="center">
    <img alt="" src="" id="viewImg">
</div>



<button class="btn btn-primary" data-toggle="modal"
		data-target="#myModal" id="modfiyBtn" style="display: none"></button>
<!-- 弹出修改照片信息框 -->
<div id="myModal" class="modal fade" tabindex="-1" role="dialog"
	aria-lableledby="myModalLabel" style="display: none;" >
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="moal-title" id="myModalLabel">照片信息</h4>
			</div>
			<div class="modal-bbody">
				<p></p>
				<form class="form-horizontal" id="photoForm">
					<input type="hidden" name="no" id="photoNo">
					<!-- 隐藏照片编号 -->
					<div class="form-group">
						<label class="control-label">名称:</label>
						<div class="col-sm-2">
							<span style="color: red;">*</span> <input type="text"
								class="form-control" id="otherName" name="otherName">
						</div>
					</div>
					<p></p>
					<div class="form-group">
						<label class="control-label">拍照地点:</label>
						<div class="col-sm-2">
							<span style="color: red;">&nbsp;</span> <input type="text"
								class="form-control" id="address" name="address">
						</div>
					</div>
					<p></p>
					<div class="form-group">
						<label class="control-label">拍照时间:</label>
						<div class="col-sm-2">
							<span style="color: red;">&nbsp;</span> <input type="text"
								class="form-control" id="phototime" name="tempPhototime">
						</div>
					</div>
					<p></p>
					<div class="form-group">
						<label class="control-label">照片描述:</label>
						<div class="col-sm-2">
							<span style="color: red;">&nbsp;</span>
							<textarea rows="3" class="form-control" id="desc"
								name="decription"></textarea>
						</div>
					</div>
					
				</form>

			</div>
			<div class="modal-footer">
				<button class="btn btn-warning" id="clearPhoto">清空</button>
				<button class="btn btn-primary" id="savePhoto">保存</button>
			</div>
		</div>
	</div>
</div>

<!-- 引入移动功能弹出框move.jsp -->
<jsp:include page="../framework/move.jsp"></jsp:include>

<script src="javascript/module/photoList.js"></script>
