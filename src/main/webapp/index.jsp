<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>

<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"1","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"8","bdPos":"left","bdTop":"47"},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"喜欢就分享：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
	<link rel="shortcut icon" href="images/favicon.ico" />
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>欢迎来到Up2U</title>
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<!--[if IE]>
		<script src="http://libs.baidu.com/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="css/buttons.css">
	<link rel="stylesheet" type="text/css" href="css/platform-1.css">
    <script src="js/jquery.js"></script>
	
	 <!-- Bootstrap & Snippet plugin core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
		<!--[if IE]>
  		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
	<link rel="stylesheet" type="text/css" href="css/default.css">
    <link href="css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="css/search.css" type="text/css" />
    <script src="js/menu.js"></script>
	<!-- STYLES -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="css/font-awesome.min.css" media="screen">
	<link rel="stylesheet" href="css/zlight.menu.css" media="screen">
	<script>
		$(document).ready(function(){
			$('#zlight-nav').zlightMenu();
		});
	</script>
</head>
<body>
<!-- 顶部加载进度条！ -->
<script src="js/preload.min.js"></script>
<script type="text/javascript">
//调用
$.QianLoad.PageLoading({
    sleep: 50
});
</script>
<script type="text/javascript">
		window.onload = function(){
			var config = {
				vx: 4,
				vy: 4,
				height: 2,
				width: 2,
				count: 100,
				color: "121, 162, 185",
				stroke: "100,200,180",
				dist: 6000,
				e_dist: 20000,
				max_conn: 10
			}
			CanvasParticle(config);
		}
	</script>
	<script type="text/javascript" src="js/canvas-particle.js"></script>
	<nav>
	<div class="box bg" style="top:0;position:absolute;right:0;">
	<%
	   String username = request.getParameter("username");
	   if(false){
	%>
		<a href="/Up2U/login.jsp" ><button class="button button--wapasha button--text-thick button--text-upper button--size-s">登录/注册</button></a>
    <%
	   }else{
     %>
        <div class="zlight-dropdown" style="right:50px;top:30px;position:absolute;">
							<a href="#"><%=username%></a>
							<ul class="zlight-submenu" style="width:100px;background-color:white;">
								<li><a href="#">等级：</a></li>
								<li class="zlight-dropdown">
									<a href="#">搜索：</a>
									<ul class="zlight-submenu" style="width:100px;background-color:white;">
										<li><a href="#">查询：</a></li>
										<li><a href="#">上传：</a></li>
									</ul>
								</li>
								<li><a href="#">##</a></li>
								<li><a href="#">退出登录</a></li>
							</ul>
						</div>
     <%
	   }
    %>
    </div>
    </nav>	    
 <div class="header tubiao" >
      <a class="link title-ani"  data-letters="Up2U">Up2U</a>
  </div>
<div class="zySearch" id="zySearch"></div>
<script type="text/javascript" src="js/zySearch.js"></script>
<script type="text/javascript">
$("#zySearch").zySearch({
	"parentClass":"pageTitle",
	"callback":function(keyword){
		console.info("searchContain");
		console.info(keyword);
	}
});
function _loading(type) {
    zeroModal.loading(type);
}
</script>
        <div class="page-header">
            <form enctype="multipart/form-data" action="uploadAction" method="post">
                <div class="form-group">
                    <input id="file" class="file" type="file" multiple data-preview-file-type="any" data-upload-url="#" data-preview-file-icon="">
                </div>
            </form>
        </div>
    <script src="js/fileinput.js" type="text/javascript"></script>
    <script src="js/fileinput_locale_zh.js" type="text/javascript"></script>
  
</body>
</html>