<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>订单管理</title>
<link rel="stylesheet" type="text/css" href="hanson/css/table.css" />
<link rel="stylesheet" href="hanson/css/common.css" />
<link rel="stylesheet" href="hanson/css/toastr.css" />

<!-- 消息提醒 -->
<script src="hanson/js/toastr.js"></script>
<script src="hanson/js/toastr.setting.js"></script>
<script src="hanson/js/table.ajax.js" type="text/javascript" charset="utf-8"></script>
<script src="hanson/js/photo.js" type="text/javascript" charset="utf-8"></script>


</head>

<body>
	<!--快速查询-->
	<form id="productSearchForm">
		<div class="quick_search">
			<span>商品编号:<input type="text" id="id" name="id" /> </span> 
			<span>商品名称:<input type="text" id="name" name="name" /> </span> 
			<span>是否可用:
				<select id="status" name="status">
					<option value="">请选择</option>
					<option value="1">上架</option>
					<option value="2">下架</option>
				</select> 
			</span> 
			<span style="display:none;">商品类型:
				<select id="producttype" name="producttype">
					<option value="">请选择</option>
					<option value="10">照片</option>
					<option value="20">照片框</option>
					<option value="30">照片墙</option>
					<option value="40">相册</option>
					<option value="50">约拍</option>
				</select>
			</span><br /> 
		<!-- 	<span>创建时间:
				<input type="text" id="kdate" name="kdate" />~<input type="text" id="jdate" name="jdate" />
			</span>  -->
		</div>
	</form>

	<!--对表单进行操作-->
	<div class="from_handle">
		<a href="#" style="display: none;"><b></b></a>
		<a href="javascript:toNavigation()"><b></b>发布商品</a>
		<a href="javascript:deleteyuepaiProduct()"><b></b>删除商品</a>
		<a href="javascript:editProduct()"><b></b>编辑商品</a> 
		<a href="javascript:editPrice();"><b></b>修改价格</a>
		<a href="javascript:view()" style="display:none;"><b></b>查看</a>
		<a href="javascript:productExcel()" style="display:none;"><b></b>导出产品excel</a>
		<a href="javascript:querybytj()"><b></b>查询</a>
		<a href="javascript:void()"><b></b>重置</a>
	</div>

	<!--表单-->
	<table id="cs_table" class="datatable"></table>
	
</body>
</html>
