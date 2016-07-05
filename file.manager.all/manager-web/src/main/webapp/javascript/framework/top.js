/**
 * 存放所有菜单项的全局数组
 */
var menusClick = new Array()
menusClick[0] = "main";
menusClick[1] = "photo";
menusClick[2] = "video";
menusClick[3] = "file";
menusClick[4] = "article";
menusClick[5] = "tools";
menusClick[6] = "project";
menusClick[7] = "allFile";

/**全局变量---控制主页菜单点击后ifream中显示jsp的action请求跳转路径**/
var menuCheckJsp = new Array()
menuCheckJsp[0] = "login/toHome";
menuCheckJsp[1] = "photo/toPhotoFolder";
menuCheckJsp[2] = "video/toVideoFolder";
menuCheckJsp[3] = "files/toFilesFolder";
menuCheckJsp[4] = "article/toArticleFolder";
menuCheckJsp[5] = "tools/toToolsFolder";
menuCheckJsp[6] = "project/toProjectFolder";
menuCheckJsp[7] = "allFile/toAllFileList";

/**
 * jquery初始化
 */
$(document).ready(function() {
	
	// 初始化所有菜单的单击事件
	for (var i = 0; i < menusClick.length; i++) {

		var menuClick = menusClick[i];

		$("#" + menuClick).click(function() {
			
		    menuInit(this);
	    });
	};
	
	/**
	 * 初始化转换列表或者缩略图的图标样式
	 */
	if(main.getCookie("cutover")==1){
		$("#cutoverList").attr("src","/images/artDialog/d.bmp");
		$("#cutoverList").attr("title","切换至缩略图");
	}else{
		$("#cutoverList").attr("src","/images/artDialog/c.bmp");
		$("#cutoverList").attr("title","切换至列表");
	}
	
	/**
	 * 控制页面列表或者缩略图显示
	 * 1=列表或者0=缩略图
	 */
	$("#cutoverList").click(function(){
		if(main.getCookie("cutover")==1){
			main.setCookie("cutover","0");
			$("#cutoverList").attr("src","/images/artDialog/c.bmp");
			$("#cutoverList").attr("title","切换至列表");
		}else{
			main.setCookie("cutover","1");
			$("#cutoverList").attr("src","/images/artDialog/d.bmp");
			$("#cutoverList").attr("title","切换至缩略图");
		}
	});

});

/**
 * 控制点击菜单选中
 * 
 * @param _menu
 */
function menuInit(currentMenu) {

	var currentCheckMenuValue = $(currentMenu).val();// 当前选中的导航菜单
	
	for (var i = 0; i < menusClick.length; i++) {

		var menu = menusClick[i];

		if (currentCheckMenuValue == $("#" + menu).val()) {

			$("#" + menu).attr("class", "active");
			
			// 控制显示相应的jsp页面，通过请求action请求跳转，显示在ifream中

			var jspPage ="web/"+ menuCheckJsp[currentCheckMenuValue] + ".html";

			//$("#iframepage").attr("src", jspPage);去掉ifream
		
			//控制div代替ifream
			$.get(jspPage,function(data){
				
				$("#mainDiv").html(data);
			});
			//控制分页在home.jsp主页中影藏，在其他页显示
			 if(menu=="main"){
			    	$("#fenyeDiv").hide();
			    }else{
			    	$("#fenyeDiv").show();
			    }

		} else {
			$("#" + menu).removeClass("active");
		}
	}
}
