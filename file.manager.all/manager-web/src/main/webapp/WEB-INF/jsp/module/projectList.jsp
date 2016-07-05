<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../common/common.jsp"></jsp:include>

<!-- 项目列表页面 -->


<!-- projectFoleder.jsp页面提交过来的文件夹编号 -->
<input id="folderNo" value="${folderNo}" type="hidden"/>


<!-- 表格列表 -->
<table class="table table-hover" style="font-size: 12px;text-align: center;"
	id="projectTableList">
	<tr><th><input type="checkbox" onclick="main.checkboxAll(this,'projectBoxName')"></th><th>序号</th><th>项目名称</th><th>项目类型</th><th>项目公司</th><th>项目大小</th><th>上传时间</th><th>上传人</th><th><a href="javascript:void(0)" id="moveProject"><img src="/images/artDialog/tb6.png"></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" id="delProjectAll"><img src="/images/artDialog/delete1.png"></a></th></tr>
	<!-- js动态绑定 -->
	<tbody id="projectTbody"></tbody>
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
				<form class="form-horizontal" id="projectForm">
					<input type="hidden" name="no" id="projectNo">
					<!-- 隐藏编号 -->
					<div class="form-group">
						<label class="control-label">名称:</label>
						<div class="col-sm-2">
							<span style="color: red;">*</span> <input type="text"
								class="form-control" id="otherName" name="otherName">
						</div>
					</div>
					
					<p></p>
					<div class="form-group">
						<label class="control-label">所属公司:</label>
						<div class="col-sm-2">
						    <span style="color: red;">&nbsp;</span>
							<input type="text"
								class="form-control" id="company" name="company">
						</div>
					</div>
					<p></p>
					<div class="form-group">
						<label class="control-label">描述:</label>
						<div class="col-sm-2">
							<span style="color: red;">&nbsp;</span>
							<textarea rows="3" class="form-control" id="takeOn"
								name="takeOn"></textarea>
						</div>
					</div>
					
				</form>

			</div>
			<div class="modal-footer">
				<button class="btn btn-warning" id="clearProject">清空</button>
				<button class="btn btn-primary" id="saveProject">保存</button>
			</div>
		</div>
	</div>
</div>

<!-- 引入移动功能弹出框move.jsp -->
<jsp:include page="../framework/move.jsp"></jsp:include>

<script src="javascript/module/projectList.js"></script>
