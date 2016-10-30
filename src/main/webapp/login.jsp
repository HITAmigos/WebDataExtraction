<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8"> 
    <title>欢迎登陆/注册！</title>
    <link rel="shortcut icon" href="images/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="css/default.css">
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<!--[if IE]>
		<script src="http://libs.baidu.com/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
	
	<script src="js/jquery.js"></script>
	
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
	<article class="htmleaf-container">
		<header class="htmleaf-header">
			<div class="htmleaf-links">
			</div>
		</header>
		<form action="loginAction" method="post">
		<div class="panel-lite">
		  <div class="thumbur">
		    <div class="icon-lock"></div>
		  </div>
		  <h4>用户登录</h4>
		  <div class="form-group">
		    <input required="required" class="form-control" name="username"/>
		    <label class="form-label">用户名</label>
		  </div>
		  <div class="form-group">
		    <input type="password" required="required" class="form-control" name="password"/>
		    <label class="form-label">密　码</label>
		  </div><a href="/Up2U/register.jsp">没有账号？立即注册 </a>
		  <button class="floating-btn"><i class="icon-arrow"></i></button>
		</div>
		</form>
	</article>

</body>
</html>
