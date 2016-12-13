<%@ page language="java"
	import="java.io.*,java.sql.*,java.util.*,entity.assistantEntity.SqlConst"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="button/css/normalize.css">
<link rel="stylesheet" type="text/css" href="button/css/vicons-font.css">
<link rel="stylesheet" type="text/css" href="button/css/base.css">
<link rel="stylesheet" type="text/css" href="button/css/buttons.css">
<link rel="stylesheet" type="text/css"
	href="public/css/bootstrap.min.css">
<script src="public/js/jquery-3.1.1.min.js"></script>

<link type="text/css" href="help/css/css.css" rel="stylesheet" />
<script type="text/javascript" src="help/js/jquery-1.11.0.js"></script>
<link rel="shortcut icon" href="images/favicon.ico" />

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
			"bdImg" : "7",
			"bdPos" : "right",
			"bdTop" : "113"
		},
		"image" : {
			"viewList" : [ "qzone", "tsina", "tqq", "renren", "weixin" ],
			"viewText" : "分享到：",
			"viewSize" : "24"
		},
		"selectShare" : {
			"bdContainerClass" : null,
			"bdSelectMiniList" : [ "qzone", "tsina", "tqq", "renren", "weixin" ]
		}
	};
	with (document)
		0[(getElementsByTagName('head')[0] || body)
				.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='
				+ ~(-new Date() / 36e5)];
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>抓取结果！</title>
<link rel="stylesheet" href="table/css/base.css" type="text/css" />
<link href="background/css/demo.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="background/js/jquery.min.js"></script>

<!-- 浮出层 -->
<link rel="stylesheet" type="text/css" href="loading/zeroModal.css" />
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script src="loading/zeroModal.js"></script>
<link rel="stylesheet" type="text/css" href="fenye/css/style.css"
	media="screen" />
<style>
a img {
	border: none;
	outline: none;
}
li{
   text-align:center;
}

.redth{
   background-color:green;
   font-color:#fff;
   font-size:24px;
   height:30px;
   text-align:center;
   
}
.content {
	margin: 10% auto;
	padding: 0;
	width: 80%;
	left: 10%;
	position: absolute;
}

.demo {
	margin:5% auto;
}

h1 {
	margin: 5px 30px 20px 0px;
	font-weight: 100;
}
h2 {
	font-weight: 50;
	font-size:100;
}
.pagedemo {
	width: 80%;
	margin: 2px auto;
	padding: 3px 10px 0px 10px;
	text-align: center;
    background-color: #f7f7f7;
	
}
table {
  border-collapse: collapse;
  width: 100%;
}
th, td {
  padding: 0.25rem;
  text-align: left;
  border: 1px solid #ccc;
}
.hover {
  background: yellow;
}
</style>
<link rel="stylesheet" href="searchnav/css/jq22.css">
<!-- CSS reset -->
<link rel="stylesheet" href="searchnav/css/style.css">
<!-- Resource style -->
<script src="searchnav/js/modernizr.js"></script>
<!-- Modernizr -->
<script src="public/js/jquery-3.1.1.js"></script>
<link href="public/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<script src="public/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="table/css/default.css">
<link rel="stylesheet" href="table/css/style.min.css">
<!--[if IE]>
		<script src="http://libs.baidu.com/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
<link rel="stylesheet" type="text/css"
	href="successsearch/css/search-form.css">
	<link rel="stylesheet" href="tab/css/all.css">
</head>
<body>
	<%
	  String User = String.valueOf(session.getAttribute("username")).trim();
	%>
	<!-- 顶部加载进度条！ -->
	<script src="public/js/preload.min.js"></script>
	<script src="public/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		//调用
		$.QianLoad.PageLoading({
			sleep : 50
		});
	</script>
	<header class="cd-main-header animate-search" style="height:70px;">
	<div class="cd-logo">
		<a href="main.jsp"><img src="images/logo.png"
			style="height: 30px; width: 80px;" alt="Logo"></a>
	</div>
	<nav class="cd-main-nav-wrapper"> <a href="#search"
		class="cd-search-trigger cd-text-replace">Search</a> </nav> <!-- .cd-main-nav-wrapper -->

	<a href="#0" class="cd-nav-trigger cd-text-replace">Menu<span></span></a>
	</header>
	<div id="search" class="cd-main-search">
		<form action="searchAction">
			<input type="search" placeholder="输入Url" name="url"> 
			<input
				type="hidden" name="username" value=<%=User%>>
		</form>
		<a href="#0" class="close cd-text-replace">Close Form</a>
	</div>
	<!-- .cd-main-search -->
	<div class="cd-cover-layer"></div>
	<!-- cover main content when search form is open -->
	<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"
		type="text/javascript"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="searchnav/js/jquery-2.1.1.min.js"><\/script>')
	</script>
	<script src="searchnav/js/main.js"></script>
	<!-- Resource jQuery -->

    <header id="title">
        <a href="success.jsp"><h1>Up2U</h1></a>
        <p class="subTitle">一个网页爬取网站！</p>
    </header>
    <ul class="kinerNav">
        <li class="active" style="width: 33.3333%;">爬取结果</li>
        <li style="width: 33.3333%;">网页分析</li>
        <li style="width: 33.3333%;">帮助</li>
    </ul>
    <div class="box">
        <div class="kinerContent">
            <div class="wrapper">
                <div class="kinerItem">
	<div id="paginationdemo" class="demo">
		<%
		            SqlConst c = new SqlConst();
					final String DB_URL = c.getDB_URL();
					final String USER = c.getUSER();
					final String PASS = c.getPASS();
					final String tableName = "SearchRecord";
					Connection conn = null;
					Statement stmt = null;
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
					stmt = conn.createStatement();
					String sc = request.getParameter("url");
					String SearchContain = new String();
					if(sc==null){
						sc = (String)request.getAttribute("url");
						if(sc != null){
						session.setAttribute("search", sc);
						}
					}else{
						session.setAttribute("search", sc);
					}
					SearchContain = String.valueOf(session.getAttribute("search")).trim();
					
					
					String sql = "SELECT * FROM " + tableName + " WHERE link = " + "\'" + SearchContain + "\' and username = \'"
							+ User + "\'";
					ResultSet rs = stmt.executeQuery(sql);
					List<String> list = new ArrayList<String>();
					String date=new String();
					while (rs.next()) {
						list.add(rs.getString("tablename"));
						date=rs.getString("date");
					}
		%>
		<%
		  for (int i = 0; i < list.size(); i++) {
						String name = list.get(i);
						if (i == 0) {
		%>
		<div id="p<%=i + 1%>" class="pagedemo _current">
			<%
			  } else {
			%>
			<div id="p<%=i + 1%>" class="pagedemo" style="display: none;">
				<%
				  }
				%>
				<div class=" ptb20">
				<h1>这是抓取的第<%=i+1 %>张表</h1>
					<div class="inner">
						<table id="process" class="tb tb-b c-100 c-t-center" id="tblGrid">
							<%
							  conn = null;
											stmt = null;
											Class.forName("com.mysql.jdbc.Driver").newInstance();
											conn = DriverManager.getConnection(DB_URL, USER, PASS);
											stmt = conn.createStatement();
											sql = "SELECT * FROM `" + name + "`";
											ResultSet rss = stmt.executeQuery(sql);
											int num = rss.getMetaData().getColumnCount();
											System.out.println(num);
											int[] coltag = new int[100];
											while (rss.next()) {
												if (rss.getInt(1) == 1) {
													for (int j = 3; j <= num; j++) {
														coltag[j] = Integer.parseInt(rss.getString(j));

													}
												} else {
							%>
							<tr>
								<%
								  if (1 == Integer.parseInt(rss.getString(2)) || 101 == Integer.parseInt(rss.getString(2))) {
															for (int j = 3; j <= num; j++) {
																if (coltag[j] == 10 || coltag[j] == 0) {
								%>
								<td class="redth"><h2>
									<%
									  out.print(rss.getString(j));
									%></h2>
								</td>
								
								<%
								  }
															}
							
														} else if (0 == Integer.parseInt(rss.getString(2))
																|| 100 == Integer.parseInt(rss.getString(2))) {
															for (int j = 3; j <= num; j++) {
																if (coltag[j] == 10 || coltag[j] == 0) {
								%>
								<td>
									<%
									  out.print(rss.getString(j));
									%>
								</td>
								<%
								  }
															}
														}
								%>
							</tr>
							<%
							  }
											}
							%>
						</table>
						<a href="javascript:_iframe('<%=name%>')"
							style="float: left; width: 20%; text-align: center; text-decoration: none;"
							class="button button--wapasha button--text-thick button--text-upper button--size-s">对该表操作</a>
						<form action="excelAction">
							<input name="tablename" value=<%=name%> type="hidden">
							<button style="float: right; width: 20%;"
								class="button button--wapasha button--text-thick button--text-upper button--size-s"
								id="searchloading">导出为Excel</button>
						</form>
					</div>
				</div>
			</div>
			<%
			  }
			%>
			<div id="demo5"
				style="margin: 20px auto; left: 42%; position: absolute;"></div>		
			<script>
				function _loading(type) {
					zeroModal.loading(type);
				}
				function _iframe(table) {
					zeroModal.show({
						title : '对表操作',
						iframe : true,
						url : 'operations.jsp?tablename=' + table,
						width : '80%',
						height : '80%',
						cancel : true
					});
				}
			</script>
			
			<script type="text/javascript" src="table/js/jquery.js"></script>
			<script type="text/javascript" src="table/js/tablesMergeCell.js"></script>
			<script type="text/javascript">
				$(document).ready(function() {
					var tr = $("#process tr").length;
					var td = $("#process td").length / tr;
					for (var i = 0; i <= td + 10; i++) {
						table_rowspan("#process", i);
					}
					for (var i = 0; i <= tr + 2; i++) {
						table_colspan("#process", i);
					}
				});
			</script>
		</div>

    </div>
    
    		<!-- /wrapper -->
        
		<script src="table/js/production/materialMenu.min.js"></script>
		<script>
			var menu = new Menu;
		</script>
		<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"
			type="text/javascript"></script>
		<script>
			window.jQuery
					|| document
							.write('<script src="js/jquery-1.11.0.min.js"><\/script>')
		</script>
		<script type="text/javascript">
			function searchToggle(obj, evt) {
				var container = $(obj).closest('.search-wrapper');
				if (!container.hasClass('active')) {
					container.addClass('active');
					evt.preventDefault();
				} else if (container.hasClass('active')
						&& $(obj).closest('.input-holder').length == 0) {
					container.removeClass('active');
					// clear input
					container.find('.search-input').val('');
					// clear and hide result container when we press close
					container.find('.result-container').fadeOut(100,
							function() {
								$(this).empty();
							});
				}
			}
			function submitFn(obj, evt) {
				value = $(obj).find('.search-input').val().trim();

				_html = "Yup yup! Your search text sounds like this: ";
				if (!value.length) {
					_html = "Yup yup! Add some text friend :D";
				} else {
					_html += "<b>" + value + "</b>";
				}

				$(obj).find('.result-container').html(
						'<span>' + _html + '</span>');
				$(obj).find('.result-container').fadeIn(100);

				evt.preventDefault();
			}
		</script>	
<script type="text/javascript" src="fenye/jquery-1.3.2.js"></script>
<script src="tab/js/kiner-swiper-panel.min.js"></script>
<script src="fenye/jquery.paginate.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$("#demo5").paginate(
				{
					count :<%=list.size()%>,
					start : 1,
					display : 5,
					border : true,
					border_color : '#fff',
					text_color : '#fff',
					background_color : 'black',
					border_hover_color : '#ccc',
					text_hover_color : '#000',
					background_hover_color : '#fff',
					images : false,
					mouse : 'press',
					onChange : function(page){
						$('._current', '#paginationdemo').removeClass('_current').hide();
						$('#p' + page).addClass('_current').show();
					}
				});
	});
</script>
                <div class="kinerItem">
                <div style="width:70%;margin:5% 15%;background-color:#fff;">
                   <table  class="table table-striped table-bordered table-hover">
                   <caption style="text-align:center;">有关该爬取结果的信息</caption>
                   <tr>
                       <td>搜所链接</td>
                       <td><%=SearchContain %></td>
                   </tr>
                   <tr>
                   <td>用户</td>
                   <td><%=User %></td>
                   </tr>
                   <tr>
                   <td>表格数量</td>
                   <td><%=list.size()%></td>
                   </tr>
                   <tr>
                   <td>搜索时间</td>
                   <td><%=date %></td>
                   </tr>
                   </table>       
</div>
                </div>

                <div class="kinerItem" style="background-color:#fff;">
  <div class="history">
        <div class="start-history">
            <p class="cc_history">使用指南</p>
            <p class="next_history">Up2U</p>
            <div class="history_left">
                <p class="history_L yearalmost">
                    <span class="history_2006_span blue">1</span>
                    <b class="history_2006_b blue">
                        <span class="history_l_month">功<br/>能</span>
                        <span class="history_l_text smalltext">通过输入URL进行表格抽取</span>
                    </b>
                </p>
                <p class="history_L year2011">
                    <span class="history_2006_span blue">3</span>
                    <b class="history_2006_b blue">
                        <span class="history_l_month">功<br/>能</span>
                        <span class="history_l_text">用户信息查找</span>
                    </b>
                </p>
                 <p class="history_L year2011">
                    <span class="history_2006_span blue">5</span>
                    <b class="history_2006_b blue">
                        <span class="history_l_month">功<br/>能</span>
                        <span class="history_l_text">导出Excel</span>
                    </b>
                </p>
                 <p class="history_L year2011">
                    <span class="history_2006_span blue">7</span>
                    <b class="history_2006_b blue">
                        <span class="history_l_month">功<br/>能</span>
                        <span class="history_l_text">行列操作</span>
                    </b>
                </p>
               
            </div>
            <div class="history-img">
                <img class="history_img" src="help/images/history.png" alt="">
            </div>
           <div class="history_right">
                <p class="history_R yearalmostr">
                    <span class="history_2005_span">2</span>
                    <b class="history_2005_b">
                        <span class="history_r_month">功<br/>能</span>
                        <span class="history_r_text">本地上传文件表格爬取</span>
                    </b>
                </p>
                <p class="history_R yearalmostr">
                    <span class="history_2005_span">4</span>
                    <b class="history_2005_b">
                        <span class="history_r_month">功<br/>能</span>
                        <span class="history_r_text">管理员完备后台</span>
                    </b>
                </p>
                <p class="history_R yearalmostr">
                    <span class="history_2005_span">6</span>
                    <b class="history_2005_b">
                        <span class="history_r_month">功<br/>能</span>
                        <span class="history_r_text">表格行列合并</span>
                    </b>
                </p>
               <p class="history_R yearalmostr">
                    <span class="history_2005_span">8</span>
                    <b class="history_2005_b">
                        <span class="history_r_month">功<br/>能</span>
                        <span class="history_r_text">发布评论</span>
                    </b>
                </p>
               
            </div>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </div>
<script type="text/javascript" src="help/js/new_file.js"></script>
<script type="text/javascript">
$(window).scroll(function(){
	var msg = $(".history-img");
	var item = $(".history_L");
	var items = $(".history_R");
	var windowHeight = $(window).height();
	var Scroll = $(document).scrollTop();
	if((msg.offset().top - Scroll -windowHeight)<=0){
		msg.fadeIn(1500);
	}
	for(var i=0;i<item.length;i++){
		if(($(item[i]).offset().top - Scroll - windowHeight)<= -100){
			$(item[i]).animate({marginRight:'0px'},'50','swing');
		}
	}
	for(var i=0;i<items.length;i++){
		if(($(items[i]).offset().top - Scroll - windowHeight)<= -100){
			$(items[i]).animate({marginLeft:'0px'},'50','swing');
		}
	}
});
var allCells = $("td, th");
allCells.on("mouseover", function() {
    var el = $(this),
        pos = el.index();
    el.parent().find("th, td").addClass("hover");
    allCells.filter(":nth-child(" + (pos+1) + ")").addClass("hover");
  })
  .on("mouseout", function() {
    allCells.removeClass("hover");
  });
</script>






		</div>

	</div>
                </div>
            </div>
        </div>

</body>
</html>