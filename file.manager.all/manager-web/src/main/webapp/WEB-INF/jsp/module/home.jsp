<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 主页，核心页面,上传，文件夹设置页面-->
 <jsp:include page="../common/common.jsp"></jsp:include>
 
<div class="homeDiv">
	<div id="tabs-260523" class="tabbable">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#panel-290464" data-toggle="tab">上传照片</a></li>
			<li><a href="#panel-47073" data-toggle="tab">上传视频</a></li>
			<li><a href="#panel-47074" data-toggle="tab">上传文件</a></li>
			<li><a href="#panel-47075" data-toggle="tab">上传文章</a></li>
			<li><a href="#panel-47076" data-toggle="tab">上传工具</a></li>
			<li><a href="#panel-47077" data-toggle="tab">上传项目</a></li>
			<li><a href="#panel-47078" data-toggle="tab">文件夹设置</a></li>
			<!-- <li><a href="#panel-47079" data-toggle="tab">类型设置</a></li> -->
			
		</ul>
		<div class="tab-content">
		
		<!-- 照片 -->
			<div id="panel-290464" class="tab-pane active">
              
				<form id="photoUploadForm" name="photoUploadForm" action="${ctx}fileManager/upload/uploadFile.html?uploadType=Photo"
					enctype="multipart/form-data" method="post">
					
                    <!-- 上传控件 div-->
					<div class="fileDiv" id="photoFileDiv">
						 <!-- <a href="javascript:void(0);" id="addPhotoRow"><img
							src="images/artDialog/add.png" class="addImage" /></a>

						替换上传下载控件
						<input type="text" style="width: 40%;" id="photoFileName"><input
							type="button" value="浏览..." class="btn fileTag" name="photoFileButton"
							onclick="photoFile.click()"><br> -->
					</div>
					
					<!-- 文件夹选择下拉菜单  取消改用下拉框方式-->
					<!-- <div class="btn-group drop">
					      <button id="photoLable" type="button" class="btn" style="width: 80%;">默认文件夹</button>
					      <button type="button" class="btn dropdown-toggle" data-toggle="dropdown">
					          <span class="caret"></span>
					          <span class="sr-only">选择</span>
					      </button>
					   <ul class="dropdown-menu" role="menu" id="photoFolderDrop">
					
					   </ul>
					   <input type="hidden"  id="photoFolderValue" name="folderValue" value="0">name必须与其他模块相同，方便后台获取
					</div> -->
					
					<div class="form-group drop">
						<div class="col-sm-2">
							文件夹：<select class="form-control"
								id="photoFolderDrop" name="folderValue" style="width: 150px;">
								<!-- js动态绑定 -->
							</select>
						</div>
					</div>
					
					<!-- <div style="margin-left: 50%;margin-top:-3.7%;">
						上传下载控件
						<input  type="file" name="photoFile" class="displayFileDiv"
							id="photoFile" onchange="photoFileName.value=this.value" />
					</div>  -->
					
					<!-- 进度条 -->
					<div class="progress bar">
					   <div id="statusBar" class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="background-color: #e1f3f4;">
					     
					   </div>
					</div>
				
					<!-- 上传按钮 -->
					<div align="center">
						<!-- <input type="button" id="photoUploadButton" name="photoUploadButton"
							value="开始上传" class="btn btn default up_btn"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button"
							id="photoCancelUploadButton" value="取消上传" class="btn btn default up_btn" /> -->
							
							<button type="button" id="photoUploadButton" class="btn btn-success btn-lg active up_btn">上传文件</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" id="photoCancelUploadButton" class="btn btn-warning btn-lg active up_btn">取消上传</button>
					</div>
				</form>
				
				<p id="p" style="color: red;font-size: 12px;">说明:&nbsp;&nbsp;最大上传量:10G，单个文件最大容量:2G</p>
			</div>
			<!-- 视频内容 -->
			<div id="panel-47073" class="tab-pane">
				
				<form id="videoUploadForm" name="videoUploadForm" action="${ctx}fileManager/upload/uploadFile.html?uploadType=Video"
					enctype="multipart/form-data" method="post">
					
                    <!-- 上传控件 -->
					<div class="fileDiv" id="videoFileDiv">
					
					</div>
					<!-- 所属文件夹 -->
					<div class="form-group drop">
						<div class="col-sm-2">
							文件夹：<select class="form-control"
								id="videoFolderDrop" style="width: 150px;">
								<!-- js动态绑定 -->
							</select>
						</div>
					</div>

                    <!-- 进度条 -->
					<div class="progress bar">
					   <div id="statusBar" class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="background-color: #e1f3f4;">
					     
					   </div>
					</div>
					<div align="center">
						<button type="button" id="videoUploadButton" class="btn btn-success btn-lg active up_btn">上传文件</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" id="videoCancelUploadButton" class="btn btn-warning btn-lg active up_btn">取消上传</button>
					</div>
					
				</form>
				
				<p id="p" style="color: red;font-size: 12px;">说明:&nbsp;&nbsp;最大上传量:10G，单个文件最大容量:2G</p>
			</div>
			<!-- 文件内容 -->
			<div id="panel-47074" class="tab-pane">
				
				<form id="fileUploadForm" name="fileUploadForm" action="${ctx}fileManager/upload/uploadFile.html?uploadType=File"
					enctype="multipart/form-data" method="post">

                    <!-- 上传控件 -->
					<div class="fileDiv" id="fileFileDiv">
					</div>
					
					<!-- 所属文件夹 -->
					<div class="form-group drop">
						<div class="col-sm-2">
							文件夹：<select class="form-control"
								id="fileFolderDrop" style="width: 150px;">
								<!-- js动态绑定 -->
							</select>
						</div>
					</div>

                    <!-- 进度条 -->
					<div class="progress bar">
					   <div id="statusBar" class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="background-color: #e1f3f4;">
					     
					   </div>
					</div>
					<div align="center">
						<button type="button" id="fileUploadButton" class="btn btn-success btn-lg active up_btn">上传文件</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" id="fileCancelUploadButton" class="btn btn-warning btn-lg active up_btn">取消上传</button>
					</div>
				
				</form>
				
				<p id="p" style="color: red;font-size: 12px;">说明:&nbsp;&nbsp;最大上传量:10G，单个文件最大容量:2G</p>
			</div>
			<!-- 文章内容 -->
			<div id="panel-47075" class="tab-pane">
				
				<form id="articleUploadForm" name="articleUploadForm" action="${ctx}fileManager/upload/uploadFile.html?uploadType=Article"
					enctype="multipart/form-data" method="post">
					

                    <!-- 上传控件 -->
					<div class="fileDiv" id="articleFileDiv">
					</div>
					
					<!-- 所属文件夹 -->
					<div class="form-group drop">
						<div class="col-sm-2">
							文件夹：<select class="form-control"
								id="articleFolderDrop" style="width: 150px;">
								<!-- js动态绑定 -->
							</select>
						</div>
					</div>

                    <!-- 进度条 -->
					<div class="progress bar">
					   <div id="statusBar" class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="background-color: #e1f3f4;">
					     
					   </div>
					</div>
					<div align="center">
						<button type="button" id="articleUploadButton" class="btn btn-success btn-lg active up_btn">上传文件</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" id="articleCancelUploadButton" class="btn btn-warning btn-lg active up_btn">取消上传</button>
					</div>
				
				</form>
				
				<p id="p" style="color: red;font-size: 12px;">说明:&nbsp;&nbsp;最大上传量:10G，单个文件最大容量:2G</p>
			</div>
			<!-- 工具内容 -->
			<div id="panel-47076" class="tab-pane">
				
				<form id="toolsUploadForm" name="toolsUploadForm" action="${ctx}fileManager/upload/uploadFile.html?uploadType=Tools"
					enctype="multipart/form-data" method="post">
					
                    <!-- 首行上传控件 -->
					<div class="fileDiv" id="toolsFileDiv">
					</div>
					
					<!-- 所属文件夹 -->
					<div class="form-group drop">
						<div class="col-sm-2">
							文件夹：<select class="form-control"
								id="toolsFolderDrop" style="width: 150px;">
								<!-- js动态绑定 -->
							</select>
						</div>
					</div>

                    <!-- 进度条 -->
					<div class="progress bar">
					   <div id="statusBar" class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="background-color: #e1f3f4;">
					     
					   </div>
					</div>
					<div align="center">
						<button type="button" id="toolsUploadButton" class="btn btn-success btn-lg active up_btn">上传文件</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" id="toolsCancelUploadButton" class="btn btn-warning btn-lg active up_btn">取消上传</button>
					</div>
					
				</form>
				
				<p id="p" style="color: red;font-size: 12px;">说明:&nbsp;&nbsp;最大上传量:10G，单个文件最大容量:2G</p>
			</div>
			<!-- 项目内容 -->
			<div id="panel-47077" class="tab-pane">
				
				<form id="projectUploadForm" name="projectUploadForm" action="${ctx}fileManager/upload/uploadFile.html?uploadType=Project"
					enctype="multipart/form-data" method="post">
					

                    <!-- 首行上传控件 -->
					<div class="fileDiv" id="projectFileDiv">
					</div>
					
					<!-- 所属文件夹 -->
					<div class="form-group drop">
						<div class="col-sm-2">
							文件夹：<select class="form-control"
								id="projectFolderDrop" style="width: 150px;">
								<!-- js动态绑定 -->
							</select>
						</div>
					</div>

                    <!-- 进度条 -->
					<div class="progress bar">
					   <div id="statusBar" class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="background-color: #e1f3f4;">
					     
					   </div>
					</div>
					<div align="center">
						<button type="button" id="projectUploadButton" class="btn btn-success btn-lg active up_btn">上传文件</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" id="projectCancelUploadButton" class="btn btn-warning btn-lg active up_btn">取消上传</button>
					</div>
				
				</form>
				
				<p id="p" style="color: red;font-size: 12px;">说明:&nbsp;&nbsp;最大上传量:10G，单个文件最大容量:2G</p>
			</div>
			<!-- 文件夹操作 -->
			 <div id="panel-47078" class="tab-pane" align="center">
			   
			    <jsp:include page="folder.jsp"></jsp:include>
				
				<jsp:include page="../common/webfenye.jsp"></jsp:include>
			
			</div>
			<!-- 类型操作 -->
			<%-- <div id="panel-47079" class="tab-pane" align="center">
			   
			    <jsp:include page="type.jsp"></jsp:include>
				<!-- 由于同一页中不能出现两分页，所以此分页属于单独分页 -->
				<jsp:include page="../common/webfenye3.jsp"></jsp:include>
			</div> --%> 
		</div>
	</div>
</div>
<script src="javascript/module/home.js"></script>

