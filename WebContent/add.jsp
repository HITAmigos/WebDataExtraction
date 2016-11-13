<%@ page language="java" contentType="text/html; charset=utf-8" import="net.kuangmeng.*,java.sql.*"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>添加一行</title>
<link href="public/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="public/js/bootstrap.js"></script>
<script type="text/javascript" src="public/js/jquery-3.1.1.min.js"></script>
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
<%
String tablename = request.getParameter("tablename");
Const c = new Const();
final String DB_URL = c.getDB_URL();
final String USER = c.getUSER();
final String PASS = c.getPASS();
final String tableName = "source";
Connection conn = null;
Statement stmt = null;
Class.forName("com.mysql.jdbc.Driver").newInstance();
conn = DriverManager.getConnection(DB_URL, USER, PASS);
stmt = conn.createStatement();
String sql = "SELECT * FROM " + tableName;
ResultSet rs = stmt.executeQuery(sql);
int num = rs.getMetaData().getColumnCount();
%>
<div style="width:60%;margin:10% auto;">
<form role="form">
   <%
    for(int i=2;i<num;i++){
   %>
      <div class="form-group">
          <input type="text" class="form-control" id="name" name="<%=i-2 %>" placeholder="请输入">
      </div>
   <%
    }
   %>
   <button type="submit" class="btn btn-success" style="margin:1% auto;">提交</button>
   </form>
</div>
</body>
</html>