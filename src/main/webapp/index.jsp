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
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/hide.css">
	 <link rel="stylesheet" type="text/css" href="css/login.css">
	 <link rel="stylesheet" type="text/css" href="css/alert.css" />
    <script src="js/alert.js"></script>
	<!--[if IE]>
		<script src="http://libs.baidu.com/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="css/normalize.css">
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" type="text/css" href="css/buttons.css">
	<link rel="stylesheet" type="text/css" href="css/platform-1.css">
	<link rel="stylesheet" type="text/css" href="css/easyform.css">
    	<link rel="stylesheet" type="text/css" href="css/tab.css">
    
    <script src="js/jquery.js"></script>
     <script src="js/easyform.js"></script>
	
	 <!-- Bootstrap & Snippet plugin core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/stickup.css" rel="stylesheet">  
		<!--[if IE]>

  		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>

		<![endif]-->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/default.css">
    <link href="css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="css/search.css" type="text/css" />
    <link rel="stylesheet" type="text/css" href="js/zeroModal.css" />
    <script src="js/zeroModal.js"></script>
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
	   if(username.trim().equals("")){
	%>
		<a href="#" class="trigger-custom"><button class="button button--wapasha button--text-thick button--text-upper button--size-s">登录/注册</button></a>
    <%
	   }else{
     %>
        <div class="zlight-dropdown">
							<a href="#"><%=username%><i class="icon-angle-down"></i></a>
							<ul class="zlight-submenu">
								<li><a href="#">等级：</a></li>
								<li class="zlight-dropdown">
									<a href="#">搜索：<i class="icon-angle-right"></i></a>
									<ul class="zlight-submenu">
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
<div class="page">
    <div id="modal-custom" class="iziModal" class="form-div">
        <header>
            <a href="" id="signin">立即登录</a>
            <a href="" class="active">马上注册</a>
        </header>
        <div class="header">
       			 <a class="link title-ani"  data-letters="Up2U">Up2U</a>
    	</div>
        <section class="hide" class="login">
        <form action="LogininAction" method="post">
            <input type="text" placeholder="用户名" name="username">
            <input id="password" type="password" placeholder="密码" name="password">
            <footer>
                <button data-iziModal-close>取消</button>
                <button class="submit">登录</button>       
            </footer>
           </form>
        </section>
        <section>
         <form id="reg-form" action="RegisterAction" method="post">
            <input type="text" placeholder="请输入邮箱" id="email" type="email" name="email" data-easyform="email;real-time;easytip:false;" data-message="Email格式要正确" data-easytip="class:easy-blue;">
            <input type="text" placeholder="用户名" id="uid" name="username" data-easyform="length:4 16;char-normal;real-time;ajax:ajax_demo(1);" data-message="用户名必须为4—16位的英文字母或数字" data-easytip="position:top;class:easy-blue;" data-message-ajax="用户名已存在!">
            <input type="password" placeholder="设置密码" id="psw" name="password" data-easyform="length:6 16;" data-message="密码必须为6—16位" data-easytip="class:easy-blue;">
            <input type="password" placeholder="重复密码" id="psw2" data-easyform="length:6 16;equal:#psw1;" data-message="两次密码输入要一致" data-easytip="class:easy-blue;">
            <input type="checkbox" name="checkbox" id="check" value="1" style="margin-bottom:30px;">我同意<a href="javascript:_alert();">注册协议</a>！
            <footer>
                <button data-iziModal-close>取消</button>
                <button class="submit">提交</button>          
            </footer>
            </form>
        </section>
    </div>
    </div>
<script>
    $(document).ready(function ()
    {
        var v = $('#reg-form').easyform();
        v.is_submit = false;
        v.error = function (ef, i, r)
        {
            //console.log("Error事件：" + i.id + "对象的值不符合" + r + "规则");
        };

        v.success = function (ef)
        {
            //console.log("成功");
        };

        v.complete = function (ef)
        {
            console.log("完成");
        };
    });

    function ajax_demo(p)
    {
        $("#uid").trigger("easyform-ajax", true);
    }
</script>
	<script src="js/login.js" type="text/javascript"></script>
	<script type="text/javascript">
	function _alert() {
        zeroModal.show({
            title: '注册协议',
            content: "<h1 style='text-align:center;font-size:30px;'>本网站由哈工大软件工程小组——你们定吧设计!</h1>",
            width: '60%'
        });
    }
	    $("#modal-custom").iziModal({
	        overlayClose: false,
	        width: 600,
	        autoOpen: false,
	        overlayColor: 'rgba(0, 0, 0, 0.6)',
	        onOpened: function() {
	            console.log('onOpened');
	        },
	        onClosed: function() {
	            console.log('onClosed');  
	        }
	    });
	    $(document).on('click', '.trigger-custom', function (event) {
	        event.preventDefault();
	        $('#modal-custom').iziModal('open');
	    });
	    $("#modal-custom").on('click', 'header a', function(event) {
	        event.preventDefault();
	        var index = $(this).index();
	        $(this).addClass('active').siblings('a').removeClass('active');
	        $(this).parents("div").find("section").eq(index).removeClass('hide').siblings('section').addClass('hide');

	        if( $(this).index() === 0 ){
	            $("#modal-custom .iziModal-content .icon-close").css('background', '#ddd');
	        } else {
	            $("#modal-custom .iziModal-content .icon-close").attr('style', '');
	        }
	    });
	    $("#modal-custom").on('click', '.submit', function(event) {
	        event.preventDefault();
	        var fx = "wobble",
	            $modal = $(this).closest('.iziModal');
	        if( !$modal.hasClass(fx) ){
	            $modal.addClass(fx);
	            setTimeout(function(){
	                $modal.removeClass(fx);
	            }, 1500);
	        }
	    });
</script>
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
            <form enctype="multipart/form-data">
                <div class="form-group">
                    <input id="file" class="file" type="file" multiple data-preview-file-type="any" data-upload-url="#" data-preview-file-icon="">
                </div>
            </form>
        </div>
    <script src="js/fileinput.js" type="text/javascript"></script>
    <script src="js/fileinput_locale_zh.js" type="text/javascript"></script>
    <script>
	    $(document).ready(function() {
	        $("#test-upload").fileinput({
	            'showPreview' : false,
	            'allowedFileExtensions' : ['html', 'htm','xml'],
	            'elErrorContainer': '#errorBlock'
	        });
	    });
		</script>
<script src="js/respond.min.js"></script>
</body>
</html>