<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<link rel="shortcut icon" href="images/favicon.ico" />
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>欢迎来到Up2U</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/hide.css">
	 <link rel="stylesheet" type="text/css" href="css/login.css">
	 <link rel="stylesheet" type="text/css" href="css/alert.css" />
    <script src="js/jquery.js"></script>
    <script src="js/alert.js"></script>
	<!--[if IE]>
		<script src="http://libs.baidu.com/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="css/normalize.css">
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" type="text/css" href="css/buttons.css">
	<link rel="stylesheet" type="text/css" href="css/search.css">
		<!--[if IE]>

  		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>

		<![endif]-->
</head>
<body>
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
<div class="box bg" >
		<a href="#" class="trigger-custom"><button class="button button--wapasha button--text-thick button--text-upper button--size-s">登录/注册</button></a>
</div>
    <div id="modal-custom" class="iziModal">
        <header>
            <a href="" id="signin">立即登录</a>
            <a href="" class="active">马上注册</a>
        </header>
        <section class="hide" class="login">
            <input type="text" placeholder="用户名">
            <input id="password" type="password" placeholder="密码">
            <footer>
                <button data-iziModal-close>取消</button>
                <button class="submit">登录</button>            
            </footer>
        </section>
        <section>
            <input type="text" placeholder="请输入邮箱">
            <input type="text" placeholder="用户名">
            <input type="password" placeholder="设置密码">
            <input type="password" placeholder="重复密码">
            <label for="check"><input type="checkbox" name="checkbox" id="check" value="1">我同意<a href="javascript:_alert()">注册协议</a>！</label>
            <footer>
                <button data-iziModal-close>取消</button>
                <button class="submit">提交</button>            
            </footer>
        </section>
    </div>
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
</body>
</html>