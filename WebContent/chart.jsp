<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.io.*,action.admin.*,java.sql.*,entity.assistantEntity.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="shortcut icon" href="images/favicon.ico" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>后台管理</title>
    <meta name="description" content="后台">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
     <link rel="stylesheet" href="assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="assets/css/admin.css">
    <link rel="stylesheet" href="assets/css/app.css">
    <script src="assets/js/echarts.min.js"></script>
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

<body data-type="chart">

      <header class="am-topbar am-topbar-inverse admin-header">
          <div class="am-topbar-brand">
              <a href="main.jsp" class="tpl-logo">
                  <img src="images/logo.png" alt="">
              </a>
          </div>
          <div class="am-icon-list tpl-header-nav-hover-ico am-fl am-margin-right">

          </div>

          <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

          <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

              <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">

                  <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
                      <a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
                          <span class="tpl-header-list-user-nick"><%=session.getAttribute("username")%></span><span class="tpl-header-list-user-ico"> <img src="assets/img/user01.png"></span>
                      </a>
                      <ul class="am-dropdown-content">

                          <li><a href="index.jsp"><span class="am-icon-power-off"></span> 退出</a></li>
                      </ul>
                  </li>
                  <li><a href="index.jsp" class="tpl-header-list-link"><span class="am-icon-sign-out tpl-header-list-ico-out-size"></span></a></li>
              </ul>
          </div>
      </header>
      <div class="tpl-page-container tpl-page-header-fixed">
          <div class="tpl-left-nav tpl-left-nav-hover">
              <div class="tpl-left-nav-title">
                  操作
              </div>
              <div class="tpl-left-nav-list">
                  <ul class="tpl-left-nav-menu">
                      <li class="tpl-left-nav-item">
                          <a href="admin.jsp" class="nav-link active">
                              <i class="am-icon-home"></i>
                              <span>主页</span>
                          </a>
                      </li>
                      <li class="tpl-left-nav-item">
                          <a href="chart.jsp" class="nav-link tpl-left-nav-link-list">
                              <i class="am-icon-bar-chart"></i>
                              <span>数据表</span>
                          </a>
                      </li>

                      <li class="tpl-left-nav-item">
                          <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                              <i class="am-icon-table"></i>
                              <span>用户</span>
                              <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                          </a>
                          <ul class="tpl-left-nav-sub-menu">
                              <li>
                                  <a href="table-font-list.jsp">
                                      <i class="am-icon-angle-right"></i>
                                      <span>管理员</span>
                                      <i class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>
                                  </a>
                                  <a href="table-images-list.jsp">
                                      <i class="am-icon-angle-right"></i>
                                      <span>普通用户</span>
                                      </a>
                              </li>
                          </ul>
                      </li>

                      <li class="tpl-left-nav-item">
                          <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                              <i class="am-icon-wpforms"></i>
                              <span>留言</span>
                              <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>
                          </a>
                          <ul class="tpl-left-nav-sub-menu" style="display: block;">
                              <li>
                                   <a href="form-amazeui.jsp">
                                  <i class="am-icon-angle-right"></i>
                                  <span>添加用户</span>
                                  <i class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>
                              </a>

                                  <a href="form-news.jsp">
                                      <i class="am-icon-angle-right"></i>
                                      <span>查看留言</span>
                                  </a>
                              </li>
                          </ul>
                      </li>
                  </ul>
              </div>
          </div>
          
          <%
		SqlConst sc = new SqlConst();
        final String DB_URL = sc.getDB_URL();
        final String PASS = sc.getPASS();
        final String USER = sc.getUSER();
        Connection conn = null;
	    Statement stmt = null;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
 	   conn = DriverManager.getConnection(DB_URL,USER,PASS);
 	   stmt = conn.createStatement();
 	   String sql = "select * from searchRecord";  
 	   ResultSet rs = stmt.executeQuery(sql);
 	  
%>

          <div class="tpl-content-wrapper">

                  <ol class="am-breadcrumb">
                      <li><a href="admin.jsp" class="am-icon-home">首页</a></li>
                      <li class="am-active">普通用户</li>
                  </ol>
                  <div class="tpl-portlet-components">

                      <div class="tpl-block">
                          <div class="am-g">
                              <div class="am-u-sm-12">
                                 
                                      <table class="am-table am-table-striped am-table-hover table-main">
                                          <thead>
                                              <tr>
                                                  <th class="table-id">ID</th>
                                                  <th class="table-title">用户名</th>
                                                  <th class="table-type">类型</th>
                                                  <th class="table-author am-hide-sm-only">时间</th>
                                                  <th class="table-date am-hide-sm-only">表名</th>
                                                  <th class="table-set">操作</th>
                                              </tr>
                                          </thead>
                                          <tbody>
                                          <%
                                            while(rs.next()){
                                          %>
                                              <tr>
                                                  <td><%=rs.getInt("id") %></td>
                                                  <%
                                                  String tn = rs.getString("tablename");
                                                  String type =null;
                                                  int num = rs.getInt("type");
                                                  if(num == 0){
                                                	  type = "Search";
                                                  }else{
                                                	  type = "Upload";
                                                  }
                                                  %>
                                                  <td><%=rs.getString("username")%></td>
                                                  <td><%=type %></td>
                                                  <td class="am-hide-sm-only"><%=rs.getDate("date") %></td>
                                                  <td class="am-hide-sm-only"><%=tn %></td>
                                                  <td>
                                                  <form action="deleteTable">
                                                              <input type="hidden" name="tablename" value=<%=tn %>>
                                                              <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
                                                     </form>
                                                  </td>
                                              </tr>
										<%
                                            }
										%>
                                          </tbody>
                                      </table>
                                      <hr>

                              </div>

                          </div>
                      </div>
                      <div class="tpl-alert"></div>
                  </div>
              </div>
          </div>
    <script src="http://www.jq22.com/jquery/jquery-2.1.1.js"></script>
    <script src="assets/js/amazeui.min.js"></script>
    <script src="assets/js/app.js"></script>
</body>
</html>