<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../common/common.jsp"></jsp:include>

<!-- 文件列表页面 -->


<!-- filesFoleder.jsp页面提交过来的文件夹编号 -->
<input id="folderNo" value="${folderNo}" type="hidden"/>


<!-- 表格列表 -->
<table class="table table-hover" style="font-size: 12px;text-align: center;"
	id="filesTableList">
	<tr><th><input type="checkbox" onclick="main.checkboxAll(this,'filesBoxName')"></th><th>序号</th><th>文件名称</th><th>文件大小</th><th>上传时间</th><th>上传人</th><th>文件描述</th><th><a href="javascript:void(0)" id="moveFiles"><img src="/images/artDialog/tb6.png"></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" id="delFilesAll"><img src="/images/artDialog/delete1.png"></a></th></tr>
	<!-- js动态绑定 -->
	<tbody id="filesTbody"></tbody>
</table>


<button class="btn btn-primary" data-toggle="modal"
		data-target="#myModal" id="modfiyBtn" style="display: none"></button>
<!-- 弹出修改文件信息框 -->
<div id="myModal" class="modal fade" tabindex="-1" role="dialog"
	aria-lableledby="myModalLabel" style="display: none;" >
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="moal-title" id="myModalLabel">文件信息</h4>
			</div>
			<div class="modal-bbody">
				<p></p>
				<form class="form-horizontal" id="filesForm">
					<input type="hidden" name="no" id="filesNo">
					<!-- 隐藏文件编号 -->
					<div class="form-group">
						<label class="control-label">名称:</label>
						<div class="col-sm-2">
							<span style="color: red;">*</span> <input type="text"
								class="form-control" id="otherName" name="otherName">
						</div>
					</div>
					<p></p>
					<div class="form-group">
						<label class="control-label">文件描述:</label>
						<div class="col-sm-2">
							<span style="color: red;">&nbsp;</span>
							<textarea rows="3" class="form-control" id="desc"
								name="description"></textarea>
						</div>
					</div>
					
				</form>

			</div>
			<div class="modal-footer">
				<button class="btn btn-warning" id="clearFiles">清空</button>
				<button class="btn btn-primary" id="saveFiles">保存</button>
			</div>
		</div>
	</div>
</div>

<!-- 引入移动功能弹出框move.jsp -->
<jsp:include page="../framework/move.jsp"></jsp:include>

<script src="javascript/module/filesList.js"></script>
