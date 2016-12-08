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

.content {
	margin: 10% auto;
	padding: 0;
	width: 80%;
	left: 10%;
	position: absolute;
}

.demo {
	padding: 10px;
	margin: 5% auto;
	border: 1px solid #fff;
	background-color: #f7f7f7;
}

h1 {
	color: #404347;
	margin: 5px 30px 20px 0px;
	font-weight: 100;
}

.pagedemo {
	border: 1px solid #CCC;
	width: 80%;
	margin: 2px auto;
	padding: 50px 10px;
	text-align: center;
	background-color: white;
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
	<header class="cd-main-header animate-search">
	<div class="cd-logo">
		<a href="main.jsp"><img src="images/logo.png"
			style="height: 30px; width: 80px;" " alt="Logo"></a>
	</div>

	<nav class="cd-main-nav-wrapper"> <a href="#search"
		class="cd-search-trigger cd-text-replace">Search</a> </nav> <!-- .cd-main-nav-wrapper -->

	<a href="#0" class="cd-nav-trigger cd-text-replace">Menu<span></span></a>
	</header>
	<div id="search" class="cd-main-search">
		<form action="searchAction">
			<input type="search" placeholder="输入Url" name="Url"> <input
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
					String sc = request.getParameter("Url");
					session.setAttribute("search", sc);
					String SearchContain = new String();
					if (String.valueOf(session.getAttribute("search")).trim().equals("null")) {
						String sql = "SELECT * FROM " + tableName;
						ResultSet rs = stmt.executeQuery(sql);
						while (rs.next()) {
							if (rs.last()) {
								SearchContain = rs.getString("link");
							}
						}
					} else {
						SearchContain = String.valueOf(session.getAttribute("search")).trim();
					}
					String sql = "SELECT * FROM " + tableName + " WHERE link = " + "\'" + SearchContain + "\' and username = \'"
							+ User + "\'";
					ResultSet rs = stmt.executeQuery(sql);
					List<String> list = new ArrayList<String>();
					while (rs.next()) {
						list.add(rs.getString("tablename"));
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
								<td>
									<%
									  out.print(rss.getString(j));
									%>
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
				style="margin: 20px auto; left: 40%; position: absolute;"></div>
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
					for (var i = 1; i <= td + 10; i++) {
						table_rowspan("#process", i);
					}
					for (var i = 0; i <= tr + 2; i++) {
						table_colspan("#process", i);
					}
				});
			</script>
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
</body>
<script type="text/javascript" src="fenye/jquery-1.3.2.js"></script>
<script src="fenye/jquery.paginate.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$("#demo5").paginate(
				{
					count :
<%=list.size()%>
	,
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
					onChange : function(page) {
						$('._current', '#paginationdemo').removeClass(
								'_current').hide();
						$('#p' + page).addClass('_current').show();
					}
				});
	});
</script>
</html>