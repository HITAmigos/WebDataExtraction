<%@ page language="java"
	import="java.io.*,java.sql.*,java.util.*,net.kuangmeng.Const"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>我的信息</title>
<link href="tree/jquery.treemenu.css" rel="stylesheet" type="text/css">
<style>
* {
	list-style: none;
	border: none;
}

body {
	font-family: Arial;
	background-color: #2C3E50;
}

.tree {
	color: #46CFB0;
	margin: 1px auto;
}

.tree li, .tree li>a, .tree li>span {
	padding: 4pt;
	border-radius: 4px;
}

.tree li a {
	color: #46CFB0;
	text-decoration: none;
	line-height: 20pt;
	border-radius: 4px;
}

.tree li a:hover {
	background-color: #34BC9D;
	color: #fff;
}

.active {
	background-color: #34495E;
	color: white;
}

.active a {
	color: #fff;
}

.tree li a.active:hover {
	background-color: #34BC9D;
}
</style>
</head>
<body>
	<%
		String User = String.valueOf(session.getAttribute("username")).trim();
		Const c = new Const();
		final String DB_URL = c.getDB_URL();
		final String USER = c.getUSER();
		final String PASS = c.getPASS();
		final String tableName = "source";
		int searchnum = 0, uploadnum = 0;
		List<String> searchlist = new ArrayList<String>();
		List<String> uploadlist = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		String sql = "SELECT * FROM " + tableName + " WHERE username = " + "\'" + User + "\'";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int num = rs.getInt("tag");
			String things = rs.getString("link");
			if (num == 0) {
				if(!searchlist.contains(things)){
				searchnum++;
				searchlist.add(things);
				}
			} else {
				if(!uploadlist.contains(things)){
				uploadnum++;
				uploadlist.add(things);
				}
			}
		}
	%>
	<ul class="tree">
		<li><a href="">我的使用信息及相关操作</a></li>
		<li><span>使用信息</span>
			<ul>
				<li><a href="#">积分与等级</a>
					<ul>
						<li><a href="#">等级：</a></li>
						<li><a href="#">积分：</a></li>
					</ul></li>
				<li><a href="#">使用记录</a>
					<ul>
						<li><a href="#">搜索：<%=searchnum%>次
						</a>
						<%
						   if(searchnum != 0){
						%>
							<ul>
								<li>
								   <a href="#">搜索详情：</a>
								    <ul>
								      <%
								      for(int i=0;i<searchlist.size();i++){
								           %>
								           <li><a href="#">
								           <%=searchlist.get(i)%>
								           </a></li>
								        <%
								      }
								        %>
								    </ul>
								</li>
								</ul>
							<%
							}
							%>
							</li>
						<li><a href="#">上传：<%=uploadnum%>次
						</a>
						     <%
						   if(uploadnum != 0){
						%>
							<ul>
								<li>
								   <a href="#">上传详情：</a>
								    <ul>
								      <%
								      for(int i=0;i<uploadlist.size();i++){
								           %>
								           <li><a href="#">
								           <%=uploadlist.get(i)%>
								           </a></li>
								        <%
								      }
								        %>
								    </ul>
								</li>
								</ul>
							<%
							}
							%>
						
						</li>
					</ul></li>
			</ul></li>
		<li><span>相关操作</span>
			<ul> 
				<form action="deleterecordAction" name="deleteform">
				<input type="hidden" name="username" value=<%=User %>>
				<li>
				<a id="deletea">删除记录</a>
				</li>
				<script>
                    document.getElementById("deletea").addEventListener("click",function(e){
                     e.preventDefault();
                   //接下来使用js代码进行页面跳转
                   void(document.deleteform.submit());
            });
        </script>
        </form>
				<form action="backrecordAction" name="backform">
				<input type="hidden" name="username" value=<%=User %>>
				<li>
				<a id="backa">一键恢复</a>
				</li>
				<script>
                    document.getElementById("backa").addEventListener("click",function(e){
                     e.preventDefault();
                   //接下来使用js代码进行页面跳转
                   void(document.backform.submit());
            });
        </script>
        </form>
			</ul>
		</li>
	</ul>
	<script src="tree/jquery-1.11.2.min.js"></script>
	<script src="tree/jquery.treemenu.js"></script>
	<script>
		$(function() {
			$(".tree").treemenu({
				delay : 300
			}).openActive();
		});
	</script>


</body>
</html>