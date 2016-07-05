<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 文件夹页面 -->
<div align="right">
	<button class="btn btn-primary" data-toggle="modal"
		data-target="#myModal" id="addFolder">添加</button>
</div>

<table class="table table-hover" style="width: 80%; font-size: 12px;"
	id="folderTable">
	<tr><th>序号</th><th>名称</th><th>类型</th><th>创建人</th><th>创建时间</th><th>描述</th><th width='120px' align='center'>操作</th></tr>
	<!-- js动态绑定 -->
	<tbody id="folderTbody"></tbody>
</table>
<!-- 添加文件夹弹出框 -->
<div id="myModal" class="modal fade" tabindex="-1" role="dialog"
	aria-lableledby="myModalLabel" style="display: none;" >
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="moal-title" id="myModalLabel">添加文件夹</h4>
			</div>
			<div class="modal-bbody">
				<p></p>
				<form class="form-horizontal" id="folderForm">
					<input type="hidden" name="no" id="folderNo">
					<!-- 隐藏文件夹编号，后来用来判断新增还是保存 -->
					<div class="form-group">
						<label class="control-label">名称:</label>
						<div class="col-sm-1">
							<span style="color: red;">*</span><input type="text"
								class="form-control" id="name" name="name">
						</div>
					</div>
					<p></p>
					<div class="form-group">
						<label class="control-label">类型:</label>
						<div class="col-sm-2">
							<span style="color: red;">*</span><select class="form-control"
								id="type" name="type" style="width: 220px;">
								<!-- js动态绑定 -->
							</select>
						</div>
					</div>
					<p></p>
					<div class="form-group">
						<label class="control-label">描述:</label>
						<div class="col-sm-2">
							<span style="color: red;">*</span>
							<textarea rows="3" class="form-control" id="desc"
								name="description">${folder.description }</textarea>
						</div>
					</div>
					
				</form>

			</div>
			<div class="modal-footer">
				<button class="btn btn-warning" id="clearFolder">清空</button>
				<button class="btn btn-primary" id="saveFolder">保存</button>
			</div>
		</div>
	</div>
</div>
<script src="javascript/module/folder.js"></script>