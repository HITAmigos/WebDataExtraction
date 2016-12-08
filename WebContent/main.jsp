<%@ page language="java"  import="java.sql.*,java.io.*,java.util.*,net.kuangmeng.LoginAction" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- 顶部加载进度条！ -->
<script src="public/js/preload.min.js"></script>
<script type="text/javascript">
//调用
$.QianLoad.PageLoading({
    sleep: 50
});
</script>
<link rel="shortcut icon" href="images/favicon.ico" />
 <!--external css-->
     <link href="admin/css/bootstrap.min.css" rel="stylesheet">
     <link href="admin/css/bootstrap-reset.css" rel="stylesheet">
    <link href="admin/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="admin/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
    <link rel="stylesheet" href="admin/css/owl.carousel.css" type="text/css">
    <!-- Custom styles for this template -->
    <link href="admin/css/style.css" rel="stylesheet">
    <link href="admin/css/style-responsive.css" rel="stylesheet" />
<!-- 按钮 -->
<link rel="stylesheet" type="text/css" href="button/css/normalize.css">
<link rel="stylesheet" type="text/css" href="button/css/vicons-font.css">
<link rel="stylesheet" type="text/css" href="button/css/base.css">
<link rel="stylesheet" type="text/css" href="button/css/buttons.css">
<link rel="stylesheet" type="text/css" href="comment/style.css">
<link rel="stylesheet" type="text/css" href="loading/zeroModal.css">
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script src="loading/zeroModal.js"></script>
<script>
	window._bd_share_config = {
		"common" : {
			"bdSnsKey" : {},
			"bdText" : "",
			"bdMini" : "1",
			"bdMiniList" : false,
			"bdPic" : "",
			"bdStyle" : "0",
			"bdSize" : "16"
		},
		"slide" : {
			"type" : "slide",
			"bdImg" : "8",
			"bdPos" : "left",
			"bdTop" : "47"
		},
		"image" : {
			"viewList" : [ "qzone", "tsina", "tqq", "renren", "weixin" ],
			"viewText" : "喜欢就分享：",
			"viewSize" : "16"
		},"selectShare" : {
			"bdContainerClass" : null,
			"bdSelectMiniList" : [ "qzone", "tsina", "tqq", "renren", "weixin" ]
		}
		
	};
	with (document)
		0[(getElementsByTagName('head')[0] || body)
				.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='
				+ ~(-new Date() / 36e5)];
</script>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="Generator" content="EditPlus®">
<meta name="Author" content="匡盟盟">
<meta name="Keywords" content="">
<meta name="Description" content="Up2U的主页">
<title>Up2U</title>
<link rel="stylesheet" type="text/css"
	href="background/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="background/css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css"
	href="background/css/component.css" />
<link rel="stylesheet" href="button/css/button.min.css" />
<!--[if IE]>
<script src="js/html5.js"></script>
<![endif]-->
<!--html5默认设置-->
<link rel="stylesheet" href="search/normalize.css">
<!--css3必要样式-->
<link rel="stylesheet" href="search/style.css" media="screen" type="text/css" />
</head>
<body>
<% 
if(String.valueOf(session.getAttribute("username")).equals("null")){
	String name=request.getParameter("username");  
	session.setAttribute("username",name);
	String User=String.valueOf(session.getAttribute("username")).trim();
}
%>
<div class="container demo-1" style="width:100%;">
<div class="content">
<div id="large-header" class="large-header">
				<!--Next button -->
<div class="svg-wrapper" style="margin-top: 10px;float:right;">
	<svg height="40" width="150" xmlns="http://www.w3.org/2000/svg">
     <rect id="shape" height="40" width="150" />
     <div id="text">
     <%
       if(String.valueOf(session.getAttribute("username")).equals("null")){
    %>
          <a href="index.jsp"><span class="spot"></span>登录/注册</a>
     <%
       }else{
    	   %>
    	   <div class="top-nav " style="padding:0">
    	   <li class="dropdown">
    	   <a data-toggle="dropdown" href="#" style="font-size:2.2em;margin-top:-10px;"><span class="spot"></span><%=String.valueOf(session.getAttribute("username")).trim() %></a>
    	    <ul class="dropdown-menu extended logout" style="float: left;background:#c0c0c0;">
                            <div class="log-arrow-up"></div>
                            <li><a href="javascript:_iframe('<%=String.valueOf(session.getAttribute("username"))%>')"><i class=" icon-suitcase"></i>我的信息</a></li>
                            <li><a href="comment.jsp"><i class="icon-bell-alt"></i>评论</a></li>
                            <li>
                            <a href="index.jsp"><i class="icon-key"></i>登出</a></li>
                            
                     </ul>
             </li>
            
           </div>
    	 <% 
       }
     %>
        </div>
      </svg>
</div>
<canvas id="demo-canvas"></canvas>
  <!-- js placed at the end of the document so the pages load faster -->
    <script src="admin/js/jquery.js"></script>
    <script src="admin/js/jquery-1.8.3.min.js"></script>
    <script src="admin/js/bootstrap.min.js"></script>
    <script src="admin/js/jquery.scrollTo.min.js"></script>
    <script src="admin/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="admin/js/jquery.sparkline.js" type="text/javascript"></script>
    <script src="admin/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
    <script src="admin/js/owl.carousel.js" ></script>
    <script src="admin/js/jquery.customSelect.min.js" ></script>
    <!--common script for all pages-->
    <script src="admin/js/common-scripts.js"></script>
    <!--script for this page-->
    <script src="admin/js/sparkline-chart.js"></script>
    <script src="admin/js/easy-pie-chart.js"></script>
  <script>
      //owl carousel

      $(document).ready(function() {
          $("#owl-demo").owlCarousel({
              navigation : true,
              slideSpeed : 300,
              paginationSpeed : 400,
              singleItem : true

          });
      });
      //custom select box
      $(function(){
          $('select.styled').customSelect();
      });
  </script>
<div class="box2">
  <ul>
    <li class="weixin">
      <div class="weixin-logo"></div>
    </li> 
    <a href="comment.jsp">
    <li class="idea">
    </li>
    </a>
  </ul>
</div>
<div><a id="loading"><img src="images/logo.png" style="top:20%;height:10%;width:20%;left:40%;position:absolute;"></img></a></div>
<script>
                    document.getElementById("loading").addEventListener("click",function(e){
                     e.preventDefault();
                   //接下来使用js代码进行页面跳转
                   _loading(1);
                   location.href="main.jsp";  
            });
 </script>
<section class="webdesigntuts-workshop main-title">
	<form action="searchAction" name="searchform">	
	   <input name="Username" value=<%=String.valueOf(session.getAttribute("username")).trim() %> type="hidden">		    
		<input type="text" name="Url" placeholder="请输入url……">	
		<div class="box bg-2" style="width:28%;left:36%;position:absolute;margin-top:50px;">
				<button class="button button--wapasha button--text-thick button--text-upper button--size-s" id="searchloading" href="javascript:_loading(4);">立即搜索</button>
				<script>
                    document.getElementById("searchloading").addEventListener("click",function(e){
                     e.preventDefault();
                   //接下来使用js代码进行页面跳转
                   _loading(1);
                   void(document.searchform.submit());
            });
        </script>
				<a href="javascript:_iiframe('<%=String.valueOf(session.getAttribute("username")).trim() %>')" class="button button--wapasha button--text-thick button--text-upper button--size-s">上传文件</a>
		</div>    	
	</form>
</section>
</div>
</div>
</div>
	<!-- /container -->
	<script src="background/js/TweenLite.min.js"></script>
	<script src="background/js/EasePack.min.js"></script>
	<script src="background/js/rAF.js"></script>
	<script src="background/js/demo-1.js"></script>
<script>
    function _loading(type) {
        zeroModal.loading(type);
    }
    function _iiframe(name) {
        zeroModal.show({
            title: '上传文件',
            iframe: true,
            content: '<form action="fileuploadAction" method="post" enctype="multipart/form-data">'+   
                '<input type="file" name="myFile"><input type="hidden" name="username" value="'+name+'">'+ 
                '<button style="margin-left:40%;width:20%;height:3%;margin-top:8%;" class="button button--wapasha button--text-thick button--text-upper button--size-s">上传</botton></form>',
            width: '40%',
            height: '40%',
            cancel: true
        });
    }
    function _iframe(username) {
        zeroModal.show({
            title: '我的信息',
            iframe: true,
            url: 'message.jsp?username='+username,
            width: '60%',
            height: '60%',
            cancel: true
        });
    }
 </script>
</body>
</html>