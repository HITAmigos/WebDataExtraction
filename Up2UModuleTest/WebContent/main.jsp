<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<div class="container demo-1">
<div class="content">
<div id="large-header" class="large-header">
				<!--Next button -->
<div class="svg-wrapper" style="margin-top: 10px;float:right;">
	<svg height="40" width="150" xmlns="http://www.w3.org/2000/svg">
        <rect id="shape" height="40" width="150" />
        <div id="text">
          <a href="index.jsp"><span class="spot"></span>登录/注册</a>
        </div>
      </svg>
</div>
<canvas id="demo-canvas"></canvas>
<div class="box2">
  <ul>
    <li class="weixin">
      <div class="weixin-logo"></div>
    </li>
    <a href="comment.jsp"><li class="idea"></li></a>
  </ul>
</div>
<section class="webdesigntuts-workshop main-title">
	<form action="searchAction">		    
		<input type="searchContain" name="searchContain" placeholder="请输入url……">	
		<div class="box bg-2" style="width:28%;left:36%;position:absolute;margin-top:50px;">
				<button class="button button--wapasha button--text-thick button--text-upper button--size-s" id="searchloading">立即搜索</button>
				<a href="javascript:_iframe()" class="button button--wapasha button--text-thick button--text-upper button--size-s">上传文件</a>
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
    function _iframe() {
        zeroModal.show({
            title: '上传文件',
            iframe: true,
            content: '<form id="file_upload_id" name="file_upload_name" action="fileuploadAction" method="post" enctype="multipart/form-data">'+   
                '<input type="file" name="file"/>'+  
                '<button style="margin-left:40%;width:20%;height:3%;margin-top:8%;" class="button button--wapasha button--text-thick button--text-upper button--size-s">上传</botton></form>',
            width: '40%',
            height: '40%',
            cancel: true
        });
    }
 </script>
</body>
</html>