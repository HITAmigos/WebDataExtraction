<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="images/favicon.ico" />
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"1","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"8","bdPos":"left","bdTop":"47"},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"喜欢就分享：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>留言</title>
<link rel="stylesheet" type="text/css" href="comment/form.css">
<link rel="stylesheet" type="text/css" href="background/css/normalize.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="background/css/component.css" />
<link rel="stylesheet" type="text/css" href="comment/style.css">
<!--[if IE]>
	<script src="js/html5.js"></script>
<![endif]-->
</head>
<body>
<!-- 顶部加载进度条！ -->
<script src="public/js/preload.min.js"></script>
<script type="text/javascript">
//调用
$.QianLoad.PageLoading({
    sleep: 50
});
</script>
<div class="container demo-4" style="width:100%;height:100%;">
	<div class="content">
		<div id="large-header" class="large-header">
			<canvas id="demo-canvas"></canvas>
	<form action="commentAction" method="get" class="bootstrap-frm">
		<h1>
			反馈意见 <span>感谢<%=session.getAttribute("username")%>为我们提供宝贵意见！</span>
		</h1>
			   <input type="hidden" name="username" value="<%=session.getAttribute("username")%>">
			<ul>
				<li class="first">
					<input type="text" class="text" name="name" placeholder="<%=session.getAttribute("username")%>" >
				</li>
				<li class="first">
					<input type="text" class="text" name="email" placeholder="邮箱">
				</li>
				<li class="second">
				<textarea name="message" placeholder="请留下评论！"></textarea>
				</li>
			</ul>
			<input type="submit" class="button" style="margin-left:50px;" value="提交留言" >
	</form>
	<div class="box2">
  <ul>
    <li class="weixin">
      <div class="weixin-logo"></div>
    </li>
  </ul>
</div>
</div>
</div>
</div><!-- /container -->
<script src="background/js/rAF.js"></script>
<script src="background/js/demo-4.js"></script>

</body>
</html>