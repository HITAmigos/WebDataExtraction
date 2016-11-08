<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>操作结果</title>
<link rel="shortcut icon" href="images/favicon.ico" />
<!-- 按钮 -->
<link rel="stylesheet" type="text/css" href="button/css/normalize.css">
<link rel="stylesheet" type="text/css" href="button/css/vicons-font.css">
<link rel="stylesheet" type="text/css" href="button/css/base.css">
<link rel="stylesheet" type="text/css" href="button/css/buttons.css">
</head>
<body>
<button class="button button--wapasha button--text-thick button--text-upper button--size-s" id="searchloading" >点击返回</button>
				<script>
                    document.getElementById("searchloading").addEventListener("click",function(e){
                     e.preventDefault();
                   //接下来使用js代码进行页面跳转
                   href.go(-2);
            });
        </script>
</body>
</html>