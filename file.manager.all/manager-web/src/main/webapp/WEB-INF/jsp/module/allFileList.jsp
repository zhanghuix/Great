<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../common/common.jsp"></jsp:include>


<!-- 表格列表 -->
<table class="table table-hover" style="font-size: 12px;text-align: center;"
	id="fileAllTableList">
	<tr><th><input type="checkbox" onclick="main.checkboxAll(this,'fileAllBoxName')"></th><th>序号</th><th>文件名称</th><th>文件夹</th><th>文件大小</th><th>上传时间</th><th>上传人</th><th>所属模块</th><th><a href="javascript:void(0)" id="delFileAll"><img src="/images/artDialog/delete1.png"></a></th></tr>
	<!-- js动态绑定 -->
	<tbody id="fileAllTbody"></tbody>
</table>


<!-- 引入move.jsp的div，id为固定命名在main.js中引用 -->
<div id="moveDiv"></div>

<script src="javascript/module/allFileList.js"></script>
