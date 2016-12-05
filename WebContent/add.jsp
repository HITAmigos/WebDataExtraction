<%@ page language="java" contentType="text/html; charset=utf-8" import="net.kuangmeng.*,java.sql.*"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link href="public/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="public/js/bootstrap.js"></script>
<script type="text/javascript" src="public/js/jquery-3.1.1.min.js"></script>
<link rel="shortcut icon" href="images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加一行</title>

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
String sql = "SELECT * FROM `" + tablename+"`";
ResultSet rs = stmt.executeQuery(sql);
int num = rs.getMetaData().getColumnCount();
%>

<script type="text/javascript">

function n(num2){
	var num="";
	for(var i=0;i<num2;i++){
    num += document.getElementById("num"+i).value+",";
	}
document.getElementById("result").value = num;
}

</script>
<div style="width:60%;margin:10% auto;">
<form  action="addAction">
<input type="hidden" name="tablename" value=<%=tablename %>>
<input type="hidden" name="num" value=<%=num %>>
<%
  for(int i=0;i<num-2;i++){
%>
<input type="text" id="num<%=i%>" onblur="n(<%=num-2%>)">
<%
  }
%>
<input type="hidden" name="str" id="result">
<button>确认修改</button>
</form>
</div>

</body>
</html>