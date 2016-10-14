<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>欢迎来到Up2U</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/hide.css">
	<!--[if IE]>
		<script src="http://libs.baidu.com/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="container">
		<a href="#" class="trigger-custom">登录/注册</a>
	</div>
    <div id="modal-custom" class="iziModal">
        <header>
            <a href="" id="signin">立即登录</a>
            <a href="" class="active">新建账户</a>
        </header>
        <section class="hide">
            <input type="text" placeholder="请输入用户名……">
            <input type="password" placeholder="请输入密码……">
            <footer>
                <button data-iziModal-close>取消</button>
                <button class="submit">登录</button>            
            </footer>
        </section>
        <section>
            <input type="text" placeholder="请填写用户名……">
            <input type="text" placeholder="请输入邮箱……">
            <input type="password" placeholder="请输入密码……">
            <input type="password" placeholder="请输入密码……">
            <label for="check"><input type="checkbox" name="checkbox" id="check" value="1"> 我同意 <u>注册协议</u>！</label>
            <footer>
                <button data-iziModal-close>取消</button>
                <button class="submit">注册</button>            
            </footer>
        </section>
    </div>
	<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="js/login.js" type="text/javascript"></script>
	<script type="text/javascript">
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
	        var fx = "wobble",  //wobble shake
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