	
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FileManager</title>
<jsp:include page="../common/common.jsp"></jsp:include>

<link href="plugin/bootstrap/css/bootstrap.min.css"
	type="text/css" rel="stylesheet">


<link rel="shortcut icon" href="images/framework/gt_favicon.png">


<link rel="stylesheet" href="css/framework/magister.css">


</head>
<body class="theme-invert">

<nav class="mainmenu">
	<div class="container">
		<div class="dropdown">
			<button type="button" class="navbar-toggle" data-toggle="dropdown"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
			<!-- <a data-toggle="dropdown" href="#">Dropdown trigger</a> -->
			<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
				<li><a href="#head" class="active">login</a></li>
				<li><a href="#themes">Themes</a></li>
				<li><a href="#about">About me</a></li>
				<li><a href="#contact">Get in touch</a></li>
			</ul>
		</div>
	</div>
</nav>


<!-- Main (Home) section -->
<section class="section" id="head">
	<div class="container">

		<div class="row">
			<div class="col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 text-center">	

				<!-- Site Title, your name, HELLO msg, etc. -->
				<h1 class="title">FileManager</h1>
				<h2 class="subtitle">welcome to fileManager system</h2>

				<!-- Short introductory (optional) -->
				<!-- <h3 class="tagline">
					Potentially, the best place to tell people why they are here.<br>
					So, this is a demo page built to showcase the beauty of the template.
				</h3> -->
				
				<!-- Nice place to describe your site in a sentence or two -->
				<form action="web/login/checkLogin.html" method="post" id="loginForm">
				<div align="center">
				  <table border="0">
				     <tr><td align="right"><span class="label" style="font-size: 14px; font-weight: normal;">登录名:</span></td><td><input name="loginName" class="input-medium" type="text" style="background:transparent;border-color: black;"/></td></tr>
				      <tr><td align="right"><span class="label" style="font-size: 14px; font-weight: normal;">密码:</span></td><td><input name="pwd" class="input-medium" type="text" style="background:transparent;border-color: black;"/></td></tr>
				  </table>
				</div>
				<p><a href="javascript:void(0)" id="loginButton" class="btn btn-default">登录</a>&nbsp;&nbsp;&nbsp;<a href="web/login/toTop.html" class="btn btn-default">注册</a></p>
	           </form>
			</div> <!-- /col -->
		</div> <!-- /row -->
	
	</div>
</section>

<!-- Second (About) section -->
<section class="section" id="about">
	<div class="container">
	
		<h2 class="text-center title">About me</h2>
		<div class="row">
			<div class="col-sm-4 col-sm-offset-2">    
				<h5><strong>Where's my lorem ipsum?<br></strong></h5>
				<p>Well, here it is: Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum, ullam, ducimus, eaque, ex autem est dolore illo similique quasi unde sint rerum magnam quod amet iste dolorem ad laudantium molestias enim quibusdam inventore totam fugit eum iusto ratione alias deleniti suscipit modi quis nostrum veniam fugiat debitis officiis impedit ipsum natus ipsa. Doloremque, id, at, corporis, libero laborum architecto mollitia molestiae maxime aut deserunt sed perspiciatis quibusdam praesentium consectetur in sint impedit voluptates! Deleniti, sequi voluptate recusandae facere nostrum?</p>    
			</div>
			<div class="col-sm-4">
				<h5><strong>More, more lipsum!<br></strong></h5>    
				<p>Tempore, eos, voluptatem minus commodi error aut eaque neque consequuntur optio nesciunt quod quibusdam. Ipsum, voluptatibus, totam, modi perspiciatis repudiandae odio ad possimus molestias culpa optio eaque itaque dicta quod cupiditate reiciendis illo illum aspernatur ducimus praesentium quae porro alias repellat quasi cum fugiat accusamus molestiae exercitationem amet fugit sint eligendi omnis adipisci corrupti. Aspernatur.</p>    
				<h5><strong>Author links<br></strong></h5>    
				<p><a href="http://be.net/pozhilov9409">Behance</a> / <a href="https://twitter.com/serggg">Twitter</a> / <a href="http://linkedin.com/pozhilov">LinkedIn</a> / <a href="https://www.facebook.com/pozhilov">Facebook</a></p>
			</div>
		</div>
	</div>
</section>

<!-- Third (Works) section -->
<section class="section" id="themes">
	<div class="container">
	
		<h2 class="text-center title">More Themes</h2>
		<p class="lead text-center">
			Huge thank you to all people who publish<br>
			their photos at <a href="http://unsplash.com">Unsplash</a>, thank you guys!
		</p>
		<div class="row">
			<div class="col-sm-4 col-sm-offset-2">
				<div class="thumbnail">
					<img src="images/framework/sshot1.jpg" alt="">
					<div class="caption">
						<h3>Thumbnail label</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Neque doloribus enim vitae nam cupiditate eius at explicabo eaque facere iste.</p>
						<p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="thumbnail">
					<img src="images/framework/sshot4.jpg" alt="">
					<div class="caption">
						<h3>Thumbnail label</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Neque doloribus enim vitae nam cupiditate eius at explicabo eaque facere iste.</p>
						<p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
					</div>
				</div>
			</div>
			<div class="col-sm-4 col-sm-offset-2">
				<div class="thumbnail">
					<img src="images/framework/sshot5.jpg" alt="">
					<div class="caption">
						<h3>Thumbnail label</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Neque doloribus enim vitae nam cupiditate eius at explicabo eaque facere iste.</p>
						<p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="thumbnail">
					<img src="images/framework/sshot3.jpg" alt="">
					<div class="caption">
						<h3>Thumbnail label</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Neque doloribus enim vitae nam cupiditate eius at explicabo eaque facere iste.</p>
						<p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
					</div>
				</div>
			</div>

		</div>

	</div>
</section>

<!-- Fourth (Contact) section -->
<section class="section" id="contact">
	<div class="container">
	
		<h2 class="text-center title">Get in touch</h2>

		<div class="row">
			<div class="col-sm-8 col-sm-offset-2 text-center">
				<p class="lead">Have a question about this template, or want to suggest a new feature?</p>
				<p>Feel free to email me, or drop me a line in Twitter!</p>
				<p><b>gt@gettemplate.com</b><br><br></p>
				<ul class="list-inline list-social">
					<li><a href="https://twitter.com/serggg" class="btn btn-link"><i class="fa fa-twitter fa-fw"></i> Twitter</a></li>
					<li><a href="https://github.com/pozhilov" class="btn btn-link"><i class="fa fa-github fa-fw"></i> Github</a></li>
					<li><a href="http://linkedin/in/pozhilov" class="btn btn-link"><i class="fa fa-linkedin fa-fw"></i> LinkedIn</a></li>
				</ul>
			</div>
		</div>

	</div>
</section>



<script src="javascript/framework/modernizr.custom.72241.js"></script>
<!-- Custom template scripts -->
<script src="javascript/framework/magister.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#loginButton").click(function(){
		$("#loginForm").submit();
	});
});


</script>
</body>
</html>