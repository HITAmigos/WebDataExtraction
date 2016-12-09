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
			<form action="commentAction" method="post" class="bootstrap-frm">
		<h1>
			反馈意见 <span>感谢你为我们提供宝贵意见！</span>
		</h1>
		<input name="username" value=<%=String.valueOf(session.getAttribute("username")).trim() %> type="hidden">
		<label> <span>称呼:</span> <input id="name" type="text"
			name="name" placeholder=<%=String.valueOf(session.getAttribute("username")) %> />
		</label> <label> <span>邮箱:</span> <input id="email" type="email"
			name="email" placeholder="邮箱地址" />
		</label> <label> <span>留言:</span> <textarea id="message"
				name="message" placeholder="请填写留言"></textarea>
		</label> <label> <span>类型 :</span><select name="selection">
				<option value="suggestion">建议</option>
				<option value="question">吐槽</option>
		</select>
		</label> <label> <span>&nbsp;</span> 
		<input type="button"
			class="button" value="提交留言" />
		</label>
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