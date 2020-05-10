
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Insert title here</title>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/color.css">
<!-- jQuery -->
<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<!-- DataTables -->
<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>

<!-- highcharts js -->
<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/js/highcharts.js"></script>
<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/js/exporting.js"></script>

<!-- easyui js -->
<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:8%;background:url(${pageContext.request.contextPath}/images/u2.png);">

</div>   
 <div data-options="region:'center'" style="background:#eee;width:100%;height:95%;">
 <%@include file="search.jsp" %>  
 
 </div>

</body>
</html>