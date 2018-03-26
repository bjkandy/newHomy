<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<link rel="stylesheet" type="text/css" href="hanson/css/table.css" />
<link rel="stylesheet" href="hanson/css/common.css" />
<script type="text/javascript">
var txid = ${id};
</script>

<script src="hanson/js/table.ajax.js" type="text/javascript" charset="utf-8"></script>
<script src="hanson/js/hanson.ordersView.js" type="text/javascript" charset="utf-8"></script>

</head>

<body>
	<!-- 查询表单 -->
	<form id="orderSearchForm" name="orderSearchForm">
		<div>
			<span>订单编号:<input name="orderid" type="text" value="" placeholder="订单编号"/></span>
			</span>
		</div>
	</form>

	<!--对表单进行操作-->
	<div class="from_handle">
		<a href="javascript:back()" class="back_btn"><b></b>返回</a>
		<a href="javascript:querybytj()"><b></b>查询</a>
		<a href="javascript:void(0)" class="resert_btn" ><b></b>重置</a>
	</div>

	<!--表单-->
	<table id="cs_table" class="datatable"></table>
</body>
</html>
