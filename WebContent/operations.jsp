<%@ page language="java"
	import="java.io.*,java.sql.*,java.util.*,net.kuangmeng.Const"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>对表格的操作！</title>
<script src="public/js/jquery-3.1.1.js"></script>
<link href="public/css/bootstrap.min..css" rel="stylesheet"
	type="text/css">
<script src="public/js/bootstrap.min.js"></script>
<style type="text/css">
#love{
   color:red;
}

</style>
<link rel="stylesheet" type="text/css" href="table/media/css/jquery.dataTables.css">
	<link rel="stylesheet" type="text/css" href="table/resources/syntax/shCore.css">
	<link rel="stylesheet" type="text/css" href="table/resources/demo.css">
	<style type="text/css" class="init"></style>
	<script type="text/javascript" language="javascript" src="table/media/js/jquery.js"></script>
	<script type="text/javascript" language="javascript" src="table/media/js/jquery.dataTables.js"></script>
	<script type="text/javascript" language="javascript" src="table/resources/syntax/shCore.js"></script>
	<script type="text/javascript" language="javascript" src="table/resources/demo.js"></script>
	<script type="text/javascript" language="javascript" class="init">
		$(document).ready(function() {
			$('#example').DataTable();
		});
	</script>
</head>
<body class="dt-example">
	<!-- 顶部加载进度条！ -->
	<script src="public/js/preload.min.js"></script>
	<script type="text/javascript">
		//调用
		$.QianLoad.PageLoading({
			sleep : 50
		});
	</script>
	<div class="container">
		<section>
			<table id="example" class="display" cellspacing="0" width="100%">
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
				%>
				<tfoot>
				<tr>
				<%
							for (int j = 3; j <= num; j++){
								coltag[j] = Integer.parseInt(rss.getString(j));
								if (coltag[j] == 0 || coltag[j]==10) {
				%>
				<th>
					<form action="loverowAction">
						<input type="hidden" value=<%=tablename%> name="tablename">
						<input type="hidden" value=<%=j - 2%> name="rownum"> <input
							type="submit" value="收藏">
					</form>
				</th>
				<%
								}
								}
				%>
			</tr>
			<tr>
				<%
							for (int j = 3; j <= num; j++){
								coltag[j] = Integer.parseInt(rss.getString(j));
								if (coltag[j] == 0 || coltag[j]==10) {
				%>
				<th>
					<form action="deleterowAction">
						<input type="hidden" value=<%=tablename%> name="tablename">
						<input type="hidden" value=<%=j - 2%> name="rownum"> <input
							type="submit" value="删除">
					</form>
				</th>
				<%
								}
								}
				%>
			</tr>
			</tfoot>
			<thead>
			<%
				} else {
			%>
			<tr>
				<%
					int colnum = rss.getInt(1);
							if (1 == Integer.parseInt(rss.getString(2)) || 101 == Integer.parseInt(rss.getString(2))) {
								for (int j = 3; j <= num; j++) {
									if (coltag[j] == 0 || coltag[j]==10) {
				%>
				<th>
					<%
						out.print(rss.getString(j));
					%>
				</th>
				<%
					}
								}
				%>
			</tr>
			<%
				}
			%>
			</thead>
			<tbody>		
			<%
							
			if (0 == Integer.parseInt(rss.getString(2)) || 100 == Integer.parseInt(rss.getString(2))) {
					if(100 == Integer.parseInt(rss.getString(2))){
						%>
						<tr id="love">
							<%		
					}else{
			%>
			<tr>
				<%
					}
					for (int j = 3; j <= num; j++) {
									if (coltag[j] == 0 || coltag[j]==10){
										if(coltag[j]==10){
											%>
											<td id="love">
												<%			
										}else{
				%>
				<td>
					<%
										}
						out.print(rss.getString(j));
					%>
				</td>
				<%
					}
								}
				%>
				<td>
					<form action="deletecolAction">
						<input type="hidden" value=<%=tablename%> name="tablename">
						<input type="hidden" value=<%=colnum%> name="colnum"> <input
							type="submit" value="删除">
					</form>
				</td>
				<td>
					<form action="lovecolAction">
						<input type="hidden" value=<%=tablename%> name="tablename">
						<input type="hidden" value=<%=colnum%> name="colnum"> <input
							type="submit" value="收藏">
					</form>

				</td>
				<%
					}
				%>
			</tr>
			<%
				}
			}
			%>
			</tbody>
	</table>
</section>
</div>
</body>
</html>