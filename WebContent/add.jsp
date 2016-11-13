<%@ page language="java" contentType="text/html; charset=UTF-8" import="net.kuangmeng.*,java.sql.*"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加一行</title>
</head>
<body>
<%
Const c=new Const();
final String DB_URL = c.getDB_URL();
final String USER = c.getUSER();
final String PASS = c.getPASS();
String tablename = String.valueOf(session.getAttribute("tablename")).trim();
Connection conn = null;
Statement stmt = null;
Class.forName("com.mysql.jdbc.Driver").newInstance();
conn = DriverManager.getConnection(DB_URL, USER, PASS);
stmt = conn.createStatement();
String sql = "SELECT * FROM `" + tablename + "`";
ResultSet rss = stmt.executeQuery(sql);
int num = rss.getMetaData().getColumnCount();
ConstPost cp=null;
%>
<form action="addAction" >
<s:textfield name="cp.tablename"/>
<s:textfield name="cp.num" />
<%
  for(int i=2;i<num;i++){
%>
<s:textfield name="cp.col[<%=i-2 %>]" />
<%
  }
%>
<input type="submit" value="提交">
</form>
</body>
</html>