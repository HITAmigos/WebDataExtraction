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
	 <link rel="stylesheet" media="screen" href="css/particle.css">
	 <link rel="stylesheet" type="text/css" href="css/login.css">
	 <link rel="stylesheet" type="text/css" href="css/alert.css" />
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script src="js/alert.js"></script>
    <script src="js/modernizr.custom.js"></script>
	<!--[if IE]>
		<script src="http://libs.baidu.com/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
</head>
<body>
<div id="particles-js">
	<div class="container">
		<a href="#" class="trigger-custom">登录/注册</a>
	</div>
    <div id="modal-custom" class="iziModal">
        <header>
            <a href="" id="signin">立即登录</a>
            <a href="" class="active">新建账户</a>
        </header>
        <section class="hide" class="login">
            <input type="text" placeholder="用户名">
            <input class="login-field login-field-password" id="password" type="password" placeholder="密码">
            <footer>
                <button data-iziModal-close>取消</button>
                <button class="submit">登录</button>            
            </footer>
        </section>
        <section>
            <input type="text" placeholder="请填写用户名">
            <input type="text" placeholder="请输入邮箱">
            <input type="password" placeholder="请输入密码">
            <input type="password" placeholder="请输入密码">
            <label for="check"><input type="checkbox" name="checkbox" id="check" value="1"> 我同意 <a href="javascript:_alert()">注册协议</a>！</label>
            <footer>
                <button data-iziModal-close>取消</button>
                <button class="submit">注册</button>            
            </footer>
        </section>
    </div>
	<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="js/login.js" type="text/javascript"></script>
	<script>
		document.write('<script src=js/' +
			('__proto__' in {} ? 'zepto.custom' : 'jquery') +
			'.js><\/script>')
	</script>
	<script src="js/hideShowPassword.js"></script>
	<script type="text/javascript">
		$('#password').hideShowPassword({
		  // Creates a wrapper and toggle element with minimal styles.
		  innerToggle: true,
		  // Makes the toggle functional in touch browsers without
		  // the element losing focus.
		  touchSupport: Modernizr.touch
		});

	function _alert() {
        zeroModal.show({
            title: '注册协议',
            content: "<h1 style='text-align:center;font-size:30px;'>本网站/应用由哈工大软件工程小组——'你们定吧'开发!</h1>",
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
</div>
<script src="js/particles.min.js"></script>
<script src="js/app.js"></script>
<script src="http://www.jq22.com/js/jq.js"></script>
<script>
  var count_particles, stats, update;
  stats = new Stats;
  stats.setMode(0);
  stats.domElement.style.position = 'absolute';
  stats.domElement.style.left = '0px';
  stats.domElement.style.top = '0px';
  document.body.appendChild(stats.domElement);
  count_particles = document.querySelector('.js-count-particles');
  update = function() {
    stats.begin();
    stats.end();
    if (window.pJSDom[0].pJS.particles && window.pJSDom[0].pJS.particles.array) {
      count_particles.innerText = window.pJSDom[0].pJS.particles.array.length;
    }
    requestAnimationFrame(update);
  };
  requestAnimationFrame(update);
</script>
</body>
</html>