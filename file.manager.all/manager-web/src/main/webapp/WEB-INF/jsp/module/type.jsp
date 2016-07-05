<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- 类型页面 -->
<div align="right">
	<button class="btn btn-primary" data-toggle="modal"
		data-target="#typeModal" id="addType">添加</button>
</div>

<table class="table table-hover" style="width: 80%; font-size: 12px;"
	id="typeTable">
	<tr><th>序号</th><th>名称</th><th>上级类型</th><th>创建人</th><th>创建时间</th><th width='120px' align='center'>操作</th></tr>
	<!-- js动态绑定 -->
	<tbody id="typeTbody"></tbody>
</table>
<!-- 添加类型弹出框 -->
<div id="typeModal" class="modal fade" tabindex="-1" role="dialog"
	aria-lableledby="typeModalLabel" style="display: none;" >
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="moal-title" id="typeModalLabel">添加类型</h4>
			</div>
			<div class="modal-bbody">
				<p></p>
				<form class="form-horizontal" id="typeForm">
					<input type="hidden" name="no" id="typeNo">
					<!-- 隐藏类型编号，后来用来判断新增还是保存 -->
					<div class="form-group">
						<label class="control-label">类型名称:</label>
						<div class="col-sm-2">
							<span style="color: red;">*</span> <input type="text"
								class="form-control" id="typeName" name="name">
						</div>
					</div>
					<p></p>
					<div class="form-group">
						<label class="control-label">上级类型:</label>
						<div class="col-sm-2">
							<span style="color: red;">*</span> <select class="form-control"
								id="partenNo" name="partenNo" style="width: 220px;">
								<!-- js动态绑定 -->
							</select>
						</div>
					</div>
				</form>

			</div>
			<div class="modal-footer">
				<button class="btn btn-warning" id="clearType">清空</button>
				<button class="btn btn-primary" id="saveType">保存</button>
			</div>
		</div>
	</div>
</div>
<script src="javascript/module/type.js"></script>