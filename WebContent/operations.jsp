<%@ page language="java"
	import="java.io.*,java.sql.*,java.util.*,net.kuangmeng.Const"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- css -->

<link
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet" />
<link rel="stylesheet" href="tablefenye/css/vendor/normalize.css" />
<link rel="stylesheet" href="tablefenye/css/styles.min.css" />
<link href="tablefenye/img/common/favicon.png" rel="shortcut icon" />

<link href="tablefenye/css/jplist.min.css" rel="stylesheet"
	type="text/css" />

<!-- js -->
<script src="tablefenye/js/vendor/jquery-1.10.0.min.js"></script>
<script src="tablefenye/js/vendor/modernizr.min.js"></script>
<script src="tablefenye/js/jplist.min.js"></script>
<script>
	$('document').ready(function() {
		$('#demo').jplist({
			itemsBox : '.demo-tbl tbody',
			itemPath : '.tbl-item',
			panelPath : '.jplist-panel'
			//save plugin state
			,
			storage : 'localstorage' //'', 'cookies', 'localstorage'			
			,
			storageName : 'jplist-table-2',
			redrawCallback : function() {
				$('.tbl-item').each(function(index, el) {
					if (index % 2 === 0) {
						$(el).addClass('even');
					} else {
						$(el).addClass('odd');
					}
				});
			}
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>对表格的操作！</title>
<script src="public/js/jquery-3.1.1.js"></script>
<link href="public/css/bootstrap.min..css" rel="stylesheet"
	type="text/css">
<script src="public/js/bootstrap.min.js"></script>

</head>
<body>
	<!-- 顶部加载进度条！ -->
	<script src="public/js/preload.min.js"></script>
	<script type="text/javascript">
		//调用
		$.QianLoad.PageLoading({
			sleep : 50
		});
	</script>
	<!-- main content -->
	<div id="main-content" class="box">
		<div class="center">
			<div id="page-content" class="box">
				<!-- demo -->
				<div id="demo" class="box jplist table-layout-2">
					<!-- panel -->
					<div class="jplist-panel box panel-top">
						<!-- reset button -->
						<button type="button" class="jplist-reset-btn"
							data-control-type="reset" data-control-name="reset"
							data-control-action="reset">
							Reset &nbsp;<i class="fa fa-share"></i>
						</button>

						<!-- items per page dropdown -->
						<div class="jplist-drop-down" data-control-type="drop-down"
							data-control-name="paging" data-control-action="paging">

							<ul>
								<li><span data-number="3"> 3 per page </span></li>
								<li><span data-number="5"> 5 per page </span></li>
								<li><span data-number="10" data-default="true"> 10
										per page </span></li>
								<li><span data-number="all"> view all </span></li>
							</ul>
						</div>
						<!-- pagination results -->
						<div class="jplist-label" data-type="Page {current} of {pages}"
							data-control-type="pagination-info" data-control-name="paging"
							data-control-action="paging"></div>

						<!-- pagination -->
						<div class="jplist-pagination" data-control-type="pagination"
							data-control-name="paging" data-control-action="paging"></div>
					</div>

					<!-- data -->
					<div class="box text-shadow">
						<table class="demo-tbl">
							<thead>
								<tr>
									<th>HEADER 1</th>
									<th>HEADER 2</th>
									<th>HEADER 1</th>
									<th>HEADER 2</th>
								</tr>
							</thead>
							<tbody>
								<%
									String sc = request.getParameter("tablename");
									session.setAttribute("tablename", sc);
									String tablename = String.valueOf(session.getAttribute("tablename")).trim();
									Const c = new Const();
									final String DB_URL = c.getDB_URL();
									final String USER = c.getUSER();
									final String PASS = c.getPASS();
									Connection conn = null;
									Statement stmt = null;
									Class.forName("com.mysql.jdbc.Driver").newInstance();
									conn = DriverManager.getConnection(DB_URL, USER, PASS);
									stmt = conn.createStatement();
									String sql = "SELECT * FROM `" + tablename + "`";
									ResultSet rss = stmt.executeQuery(sql);
									int num = rss.getMetaData().getColumnCount();
									System.out.println(num);
									int[] coltag = new int[100];
									while (rss.next()) {
										if (rss.getInt(1) == 1) {
											for (int j = 2; j <= num; j++) {
												coltag[j] = Integer.parseInt(rss.getString(j));
												System.out.println(coltag[j]);
											}
										} else {
								%>
								<tr class="tbl-item">
									<%
										if (0 == Integer.parseInt(rss.getString(2))) {
													for (int j = 3; j <= num; j++) {
														if (coltag[j] < 10)
									%>
									<td class="td-block">
										<%
											out.print(rss.getString(j));
										%>
									</td>
									<%
										}
												}
									%>
								</tr>
								<%
									}
									}
								%>
							</tbody>
						</table>
					</div>
					<!-- end of data -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>