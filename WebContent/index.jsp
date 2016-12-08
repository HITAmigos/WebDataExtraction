<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="login-content" data-ng-app="materialAdmin">
<head>
<link rel="shortcut icon" href="images/favicon.ico" />
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"1","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"8","bdPos":"left","bdTop":"47"},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"喜欢就分享：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
 <meta charset="UTF-8"> 
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="Generator" content="EditPlus®">
 <meta name="Author" content="匡盟盟">
 <meta name="Keywords" content="">
 <meta name="Description" content="Up2U的登录注册页">
 <link rel="stylesheet" type="text/css" href="loading/zeroModal.css" />
 <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
 <script src="loading/zeroModal.js"></script>
 <title>注册/登录</title>
 <!-- Vendor CSS -->
 <link href="loginregister/css/material-design-iconic-font/css/material-design-iconic-font.min.css" rel="stylesheet" type="text/css">
 <!-- CSS -->
 <link href="loginregister/css/app.min.1.css" rel="stylesheet" type="text/css">
 <link rel="stylesheet" type="text/css" href="comment/style.css">
 
 </head>
 <body class="login-content" data-ng-controller="loginCtrl as lctrl">
<!-- 顶部加载进度条！ -->
<script src="public/js/preload.min.js"></script>
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
				count: 300,
				color: "12, 16, 15",
				stroke: "100,200,180",
				dist: 6000,
				e_dist: 20000,
				max_conn: 10
			}
			CanvasParticle(config);
		}
</script>
<script type="text/javascript" src="background/js/canvas-particle.js"></script>

<%
session.removeAttribute("username"); 
session.invalidate(); 
%>
<div class="lc-block" id="l-login" data-ng-class="{'toggled':lctrl.login === 1}">
    	<h1 class="lean">欢迎登录</h1>
    	<form action="loginAction" method="post" name="loginform">
    	<div class="input-group m-b-20">
    		<span class="input-group-addon">
    			<i class="zmdi zmdi-account"></i>
    		</span>
    		<div class="fg-line">
    			<input type="text" class="form-control" placeholder="用户名" name="username" regex="^\w{3,16}$"/>
    		</div>
    	</div>
        <div class="input-group m-b-20">
    		<span class="input-group-addon">
    			<i class="zmdi zmdi-male"></i>
    		</span>
    		<div class="fg-line">
    			<input type="password" class="form-control" placeholder="密码" regex="^\w+" name="password"/>
    		</div>
    	</div>
    	
    	<div class="clearfix"></div>
    	
    	<div class="checkbox">
    		<label>
    			<input type="checkbox" value="" />
    			<i class="input-helper">
    				保持登录状态
    			</i>
    		</label>
    	</div>
    	<a class="btn btn-login btn-danger btn-float" id="loginload">
    		<i class="zmdi zmdi-arrow-forward"></i>
    	</a>
    	 <script>
            document.getElementById("loginload").addEventListener("click",function(e){
                e.preventDefault();
                //接下来使用js代码进行页面跳转
                _loading(3);
                void(document.loginform.submit());
            });
        </script>
        </form>
        <ul class="login-navigation">
        	<li class="bgm-red" data-ng-click="lctrl.login = 0; lctrl.register = 1">注册</li>
        	<li data-block="#l-forget-password" class="bgm-orange" data-ng-click="lctrl.login = 0; lctrl.forgot = 1">忘记密码?</li>
        </ul>
    </div>
    
    <div class="lc-block" id="l-register" data-ng-class="{ 'toggled': lctrl.register === 1 }" data-ng-if="lctrl.register === 1">
    	<h1 class="lean">马上注册</h1>
       <form action="registerAction" name="registerform" method="post">
    	<div class="input-group m-b-20">
    		<span class="input-group-addon">
    			<i class="zmdi zmdi-account"></i>
    		</span>
    		<div class="fg-line">
    			<input type="text" class="form-control"  name="username" placeholder="用户名" regex="^\w{3,16}$"/>
    		</div>
    	</div>

        <div class="input-group m-b-20">
    		<span class="input-group-addon">
    			<i class="zmdi zmdi-email"></i>
    		</span>
    		<div class="fg-line">
    			<input type="text" class="form-control" name="email"  placeholder="邮箱" regex="^\w+@\w+\.[a-zA-Z]+(\.[a-zA-Z]+)?$"/>
    		</div>
    	</div>
        <div class="input-group m-b-20">
    		<span class="input-group-addon">
    			<i class="zmdi zmdi-male"></i>
    		</span>
    		<div class="fg-line">
    			<input type="password" name="password" class="form-control" placeholder="密码" regex="^\w+"/>
    		</div>
    	</div>
    	<div class="clearfix"></div>
    	<div class="checkbox">
    		<label>
    			<input type="checkbox" value=""/>
    			<i class="input-helper"></i>
    			接受许可协议
    		</label>
    	</div>
    	<a class="btn btn-login btn-danger btn-float" id="registerload"><i class="zmdi zmdi-arrow-forward"></i></a>
    	 <script>
            document.getElementById("registerload").addEventListener("click",function(e){
                e.preventDefault();
                //接下来使用js代码进行页面跳转
                _loading(3);
                void(document.registerform.submit());
            });
        </script>
    	</form>
    	<ul class="login-navigation">
	      <li data-block="#l-login" class="bgm-green" data-ng-click="lctrl.register = 0; lctrl.login = 1">登录</li>
	      <li data-block="#l-forget-password" class="bgm-orange" data-ng-click="lctrl.register = 0; lctrl.forgot = 1">忘记密码?</li>
	    </ul>
    </div>
    <div class="lc-block" id="l-forget-password" data-ng-class="{ 'toggled': lctrl.forgot === 1 }" data-ng-if="lctrl.forgot === 1">
    	<h1 class="lean">找回密码</h1>
    	<!--<p class="text-left">欢迎使用我们的测试版找回密码功能！</p>-->
    	<form action="mailAction" name="mailform" >
    	<div class="input-group m-b-20">
	      <span class="input-group-addon"><i class="zmdi zmdi-email"></i></span>
	      <div class="fg-line">
	        <input type="text" class="form-control" placeholder="邮箱" name="email" regex="^\w+@\w+\.[a-zA-Z]+(\.[a-zA-Z]+)?$" />
	      </div>
	    </div>
	    <a id="readmore" class="btn btn-login btn-danger btn-float"><i class="zmdi zmdi-arrow-forward"></i></a>
	     <script>
            document.getElementById("readmore").addEventListener("click",function(e){
                e.preventDefault();
                //接下来使用js代码进行页面跳转
                _loading(3);
                void(document.mailform.submit());
            });
        </script>
	    </form>
	    <ul class="login-navigation">
	      <li data-block="#l-login" class="bgm-green" data-ng-click="lctrl.forgot = 0; lctrl.login = 1">登录</li>
	      <li data-block="#l-register" class="bgm-red" data-ng-click="lctrl.forgot = 0; lctrl.register = 1">注册</li>
	    </ul>
    </div>
    
    	<div class="box2">
  <ul>
    <li class="weixin">
      <div class="weixin-logo"></div>
    </li>
    <a href="comment.jsp"><li class="idea"></li></a>
  </ul>
  
</div>
<script>
    function _loading(type) {
        zeroModal.loading(type);
    }

 </script>
 </body>
 	
 	<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
	<!-- Angular -->
	<script src="loginregister/js/bower_components/angular/angular.min.js"></script>
	<script src="loginregister/js/bower_components/angular-resource/angular-resource.min.js"></script>
	<script src="loginregister/js/bower_components/angular-animate/angular-animate.min.js"></script>
	
	
	<!-- Angular Modules -->
	<script src="loginregister/js/bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
	<script src="loginregister/js/bower_components/angular-loading-bar/src/loading-bar.js"></script>
	<script src="loginregister/js/bower_components/oclazyload/dist/ocLazyLoad.min.js"></script>
	<script src="loginregister/js/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
	
	<!-- Common js -->
	<script src="loginregister/js/bower_components/angular-nouislider/src/nouislider.min.js"></script>
	<script src="loginregister/js/bower_components/ng-table/dist/ng-table.min.js"></script>
	
	<!-- Placeholder for IE9 -->
	<!--[if IE 9 ]>
	    <script src="js/bower_components/jquery-placeholder/jquery.placeholder.min.js"></script>
	<![endif]-->
	<!-- App level -->
	<script src="loginregister/js/app.js"></script>
	<script src="loginregister/js/controllers/main.js"></script>
	<script src="loginregister/js/controllers/ui-bootstrap.js"></script>
	
	
	<!-- Template Modules -->
	<script src="loginregister/js/modules/form.js"></script>

</html>