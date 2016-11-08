<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
  <link rel="shortcut icon" href="images/favicon.ico" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mosaddek">
    <meta name="keyword" content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <title>后台管理</title>
    <!-- Bootstrap core CSS -->
    <link href="admin/css/bootstrap.min.css" rel="stylesheet">
    <link href="admin/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="admin/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="admin/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
    <link rel="stylesheet" href="admin/css/owl.carousel.css" type="text/css">
    <!-- Custom styles for this template -->
    <link href="admin/css/style.css" rel="stylesheet">
    <link href="admin/css/style-responsive.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="comment/style.css">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->
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
	String name=request.getParameter("username");  
	session.setAttribute("username",name);

%>
  <section id="container" class="">
      <!--header start-->
      <header class="header white-bg">
            <div class="sidebar-toggle-box">
                <div data-original-title="Toggle Navigation" data-placement="right" class="icon-reorder tooltips"></div>
            </div>
            <!--logo start-->
            <a href="main.jsp" class="logo">Up<span>2U</span></a>
            <!--logo end-->
            <div class="top-nav ">
                <!--search & user info start-->
                <ul class="nav pull-right top-menu">
                    <li>
                        <input type="text" class="form-control search" placeholder="搜索">
                    </li>
                    <!-- user login dropdown start-->
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <img alt="" src="admin/img/avatar1_small.jpg">
                            <span class="username"><%=String.valueOf(session.getAttribute("username")) %></span>
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu extended logout">
                            <div class="log-arrow-up"></div>
                            <li><a href="#"><i class=" icon-suitcase"></i>Profile</a></li>
                            <li><a href="#"><i class="icon-cog"></i> 设置</a></li>
                            <li><a href="#"><i class="icon-bell-alt"></i> 通知</a></li>
                            <li><a href="index.jsp"><i class="icon-key"></i>登出</a></li>
                        </ul>
                    </li>
                    <!-- user login dropdown end -->
                </ul>
                <!--search & user info end-->
            </div>
        </header>
      <!--header end-->
      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu">
                  <li class="active">
                      <a class="" href="admin.jsp">
                          <i class="icon-dashboard"></i>
                          <span>主页</span>
                      </a>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="icon-cogs"></i>
                          <span>设置</span>
                          <span class="arrow"></span>
                      </a>
                      <ul class="sub">
                          <li><a class="" href="admin/html/grids.html">Grids</a></li>
                          <li><a class="" href="admin/html/calendar.html">Calendar</a></li>
                          <li><a class="" href="admin/html/charts.html">Charts</a></li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="icon-tasks"></i>
                          <span>用户</span>
                          <span class="arrow"></span>
                      </a>
                      <ul class="sub">
                          <li><a class="" href="admin/html/form_component.html">Form Components</a></li>
                          <li><a class="" href="admin/html/form_wizard.html">Form Wizard</a></li>
                          <li><a class="" href="admin/html/form_validation.html">Form Validation</a></li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="icon-th"></i>
                          <span>数据表</span>
                          <span class="arrow"></span>
                      </a>
                      <ul class="sub">
                          <li><a class="" href="admin/html/basic_table.html">Basic Table</a></li>
                          <li><a class="" href="admin/html/dynamic_table.html">Dynamic Table</a></li>
                      </ul>
                  </li>
                  <li>
                      <a class="" href="admin/html/inbox.html">
                          <i class="icon-envelope"></i>
                          <span>留言</span>
                          <span class="label label-danger pull-right mail-info"></span>
                      </a>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="icon-glass"></i>
                          <span>拓展</span>
                          <span class="arrow"></span>
                      </a>
                      <ul class="sub">
                          <li><a class="" href="admin/html/blank.html">空白页</a></li>
                          <li><a class="" href="admin/html/profile.html">Profile</a></li>
                          <li><a class="" href="admin/html/invoice.html">Invoice</a></li>
                      </ul>
                  </li>
              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
              <!--state overview start-->
              <div class="row state-overview">
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol terques">
                              <i class="icon-user"></i>
                          </div>
                          <div class="value">
                              <h1>22</h1>
                              <p>用户</p>
                          </div>
                      </section>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol red">
                              <i class="icon-tags"></i>
                          </div>
                          <div class="value">
                              <h1>140</h1>
                              <p>留言</p>
                          </div>
                      </section>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol blue">
                              <i class="icon-bar-chart"></i>
                          </div>
                          <div class="value">
                              <h1>34,500</h1>
                              <p>数据表</p>
                          </div>
                      </section>
                  </div>
              </div>
    <!-- js placed at the end of the document so the pages load faster -->
    <script src="admin/js/jquery.js"></script>
    <script src="admin/js/jquery-1.8.3.min.js"></script>
    <script src="admin/js/bootstrap.min.js"></script>
    <script src="admin/js/jquery.scrollTo.min.js"></script>
    <script src="admin/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="admin/js/jquery.sparkline.js" type="text/javascript"></script>
    <script src="admin/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
    <script src="admin/js/owl.carousel.js" ></script>
    <script src="admin/js/jquery.customSelect.min.js" ></script>
    <!--common script for all pages-->
    <script src="admin/js/common-scripts.js"></script>
    <!--script for this page-->
    <script src="admin/js/sparkline-chart.js"></script>
    <script src="admin/js/easy-pie-chart.js"></script>
  <script>
      //owl carousel

      $(document).ready(function() {
          $("#owl-demo").owlCarousel({
              navigation : true,
              slideSpeed : 300,
              paginationSpeed : 400,
              singleItem : true

          });
      });

      //custom select box

      $(function(){
          $('select.styled').customSelect();
      });

  </script>
<div class="box2">
  <ul>
    <li class="weixin">
      <div class="weixin-logo"></div>
    </li>
    <a href="comment.jsp"><li class="idea"></li></a>
  </ul>
</div>
  </body>
</html>
