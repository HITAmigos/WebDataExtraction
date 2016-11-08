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
	<table>
		<tbody>
		<tr>
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
				for(int i=3;i<=num;i++){
					%>
					<td>
					<form action="deleterowAction" >
			        <input type="hidden" value=<%=tablename %> name="tablename">
			        <input type="hidden" value=<%=i %> name="rownum">
			        <input type="submit" value="删除">
			        </form>
					</td>
					<%
				}
				%>
				</tr>
				<%
				int[] coltag = new int[100];
				while (rss.next()) {
					if (rss.getInt(1) == 1) {
						for (int j = 2; j <= num; j++) {
							coltag[j] = Integer.parseInt(rss.getString(j));
							System.out.println(coltag[j]);
						}
					} else {
			%>
			<tr>
				<%
				    int colnum=rss.getInt(1);
					if (0 == Integer.parseInt(rss.getString(2))){
								for (int j = 3; j <= num; j++) {
									if (coltag[j] < 10)
				%>
				<td>
					<%
						out.print(rss.getString(j));
					%>
				</td>
				<%
					}
							}
				%>
			<td>
			<form action="deletecolAction" >
			<input type="hidden" value=<%=tablename %> name="tablename">
			<input type="hidden" value=<%=colnum %> name="colnum">
			<input type="submit" value="删除">
			</form>
			</td>
			</tr>
			<%
				}
				}
			%>
		</tbody>
	</table>

</body>
</html>