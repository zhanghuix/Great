<!-- 头部jsp -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../common/common.jsp"></jsp:include>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span4">
			<img alt="" src="images/framework/luckyBag_1.png"
				style="width: 20%; height: 20%">
		</div>
		<div class="span4"></div>
		<div class="span4" align="right"><img src="" id="cutoverList" style="margin-top: 20px;"></div>
	</div>
	<div class="row-fluid">
		<div class="span12" align="center">
			<form class="form-search" action="web/allFile/search.html" target="_blank">
				<input class="input-medium search-query" size="100" type="text"
					style="width: 50%;" name="content"/>
				<button class="btn" type="submit">搜索</button>
			</form>
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container-fluid">
						<!-- <a class="btn btn-navbar" data-toggle="collapse"
							data-target=".navbar-responsive-collapse"><span
							class="icon-bar"></span><span class="icon-bar"></span><span
							class="icon-bar"></span></a> <a class="brand" href="javascript:void(0)" id="allFile">FileManager</a> -->
						
						<div class="nav-collapse collapse navbar-responsive-collapse">
							<ul class="nav">
								<li class="active" id="main" style="width: 130px;" value="0"><a
									href="javascript:void(0)">主页</a></li>
								<li class="divider-vertical"></li>
								<li id="photo" style="width: 130px;" value="1"><a
									href="javascript:void(0)">照片</a></li>
								<li class="divider-vertical"></li>
								<li id="video" style="width: 130px;" value="2"><a
									href="javascript:void(0)">视频</a></li>
								<li class="divider-vertical"></li>
								<li id="file" style="width: 130px;" value="3"><a
									href="javascript:void(0)">文件</a></li>
								<li class="divider-vertical"></li>
								<li id="article" style="width: 130px;" value="4"><a
									href="javascript:void(0)">文章</a></li>
								<li class="divider-vertical"></li>
								<li id="tools" style="width: 130px;" value="5"><a
									href="javascript:void(0)">工具</a></li>
								<li class="divider-vertical"></li>
								<li id="project" style="width: 130px;" value="6"><a
									href="javascript:void(0)">项目</a></li>
								<li class="divider-vertical"></li>
								<li id="allFile" style="width: 130px;" value="7"><a
									href="javascript:void(0)">全部</a></li>
							</ul>
							<!-- <ul class="nav pull-right">
									
									<li class="divider-vertical"></li>
									<li class="dropdown"><a class="dropdown-toggle" href="#"
										data-toggle="dropdown">下拉菜单<strong class="caret"></strong></a>
										<ul class="dropdown-menu">
											<li><a href="#">下拉导航1</a></li>
											<li><a href="#">下拉导航2</a></li>
											<li><a href="#">其他</a></li>
											<li class="divider"></li>
											<li><a href="#">链接3</a></li>
										</ul></li>
								</ul> -->
						</div>

					</div>
				</div>

			</div>
		</div>
	</div>
</div>
 <script src="javascript/framework/top.js"></script>