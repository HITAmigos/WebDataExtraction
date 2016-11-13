<%@ page language="java"
	import="java.io.*,java.sql.*,java.util.*,net.kuangmeng.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>对表格的操作！</title>
<script src="public/js/jquery-3.1.1.js"></script>
<link href="public/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="public/js/bootstrap.js"></script>
<script src="public/js/down.js"></script>
<style type="text/css">
#love{
   color:red;
}
button{
 width:158px;
}
</style>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="public/css/style.css" media="screen" type="text/css" />
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
<table class="table  table-striped table-hover success table-bordered">
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
<ul class="nav nav-pills">
  <li class="dropdown active" id="menu1">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#menu1">
     列操作
      <b class="caret"></b>
    </a>
    <ul class="dropdown-menu">
            <li>
            		<form action="deleterowAction">
						<input type="hidden" value=<%=tablename%> name="tablename">
						<input type="hidden" value=<%=j - 2%> name="rownum">
                        <button type="submit" class="btn btn-primary">删除此列</button>      
                 </form>
            </li>
           <li>
            		<form action="loverowAction" >
						<input type="hidden" value=<%=tablename%> name="tablename">
						<input type="hidden" value=<%=j - 2%> name="rownum">
						<button type="submit" class="btn btn-success">收藏此列</button>            		
					</form>
            </li>
            <li>
            		<form action="editrowAction" >
						<input type="hidden" value=<%=tablename%> name="tablename">
						<input type="hidden" value=<%=j - 2%> name="rownum">
            			<button type="submit" class="btn btn-info">编辑此列</button> 
            		</form>
            </li>
          </ul>
        </li>
      </ul>
				</th>
				
				<%
						}
						}
				%>
				<th>
            			<a href="add.jsp?tablename=<%=tablename%>"><button type="submit" style="width:85.5px;"class="btn btn-warning">添加一行</button></a> 
				</th>
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
				<th>操作</th>
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
				<ul class="nav nav-pills">
  <li class="dropdown active" id="menu1">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#menu1">
     行操作
      <b class="caret"></b>
    </a>
    <ul class="dropdown-menu">
            <li>
            		<form action="deletecolAction">
						<input type="hidden" value=<%=tablename%> name="tablename">
						<input type="hidden" value=<%=colnum%> name="colnum">
            			<button type="submit" class="btn btn-primary">删除此行</button> 
            		</form>
            </li>
           <li>
            		<form action="lovecolAction" >
						<input type="hidden" value=<%=tablename%> name="tablename">
						<input type="hidden" value=<%=colnum%> name="colnum">
            			<button type="submit" class="btn btn-success">收藏此行</button>  
            		</form>
            </li>
            <li>
            		<form action="editcolAction" >
						<input type="hidden" value=<%=tablename%> name="tablename">
						<input type="hidden" value=<%=colnum%> name="colnum">
            			<button type="submit" class="btn btn-info">编辑此行</button> 
            		</form>
            </li>
          </ul>
        </li>
      </ul>
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
  <script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>
  <script src='http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js'></script>
  <script src="public/js/index.js"></script>
</body>
</html>