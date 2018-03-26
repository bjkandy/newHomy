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
<link rel="stylesheet" href="hanson/css/datepicker.css" />

<link rel="stylesheet" href="hanson/css/bootstrap.min.css" />
		<link rel="stylesheet" href="hanson/css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="hanson/css/fullcalendar.css" />	
		<link rel="stylesheet" href="hanson/css/uniform.css" />
		<link rel="stylesheet" href="hanson/css/select2.css" />	
		<link rel="stylesheet" href="hanson/css/unicorn.main.css" />
		<link rel="stylesheet" href="hanson/css/unicorn.grey.css" class="skin-color" />
<!-- 消息提醒 -->
<script type="text/javascript" src="http://www.my97.net/My97DatePicker/WdatePicker.js"></script>
<script src="hanson/js/toastr.js"></script>
<script src="hanson/js/toastr.setting.js"></script>
<script src="hanson/js/table.ajax.js" type="text/javascript" charset="utf-8"></script>
<script src="hanson/js/hanson.ddgl.js" type="text/javascript" charset="utf-8"></script>

<link rel="stylesheet" href="hanson/css/toastr.css" />
</head>

<body>
	<!--快速查询-->
	<form id="orderSearchForm">
		<div class="quick_search">
			<span>商户编号:<input type="text" id="qyid" name="qyid" /> </span> 
			<span>订单号:<input type="text" id="orderid" name="orderid" /> </span> 
			<span>客户昵称:<input type="text" id="cuname" name="cuname" /> </span> 
			<!-- <span>订单日期:<input type="text" id="kdate" name="kdate" />~<input type="text" id="jdate" name="jdate" /> </span><br /> -->
			<span>订单日期:<input id="kdate" name="kdate" type="text" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'jdate\')||\'2050-10-01\'}'})" value=""/>~<input id="jdate" name="jdate" type="text" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'kdate\')}', maxDate:'2050-10-01'})" value=""/></span><br />
			<span>订单状态:
				<select id="orderstatus" name="orderstatus">
					<option value="">请选择</option>
					<option value="20">未生产</option>
					<option value="25">生产完毕</option>
					<option value="30">已发货</option>
				</select> 
			</span> 
			<span>支付状态:
				<select id="paystatus" name="paystatus">
					<option value="">请选择</option>
					<option value="0">待支付</option>
					<option value="10">支付成功</option>
				</select> 
			</span> 
			<span>提现状态: 
				<select id="txStatus" name="txStatus">
						<option value="">请选择</option>
						<option value="10">未提现</option>
						<option value="20">提现中</option>
						<option value="40">提现成功</option>
				</select> 
			</span>
			<span>订单类型: 
				<select id="ordertype" name="ordertype">
						<option value="">请选择</option>
						<option value="10">商城订单</option>
						<option value="30">约拍订单</option>
						<option value="20">自助订单</option>
				</select> 
			</span>
		</div>
	</form>

	<!--对表单进行操作-->
	<div class="from_handle">
		<a href="#" style="display: none;"><b></b></a>
		<!-- <a href="index.jsp"><b></b>查看</a> -->
		<!-- <a href="javascript:void()"><b></b>订单下载</a> -->
		<!-- <a href="javascript:sc()"><b></b>订单生产完毕</a>
		<a href="javascript:fahuo()"><b></b>订单已发货</a>
		<a href="javascript:void()"><b></b>订单退款</a>
		<a href="javascript:js()"><b></b>发起结算</a>  -->
		<a href="javascript:exportExcel()"><b></b>导出excel</a>
		<a href="javascript:querybytj()"><b></b>查询</a>
		<a href="javascript:void()"><b></b>重置</a>
	</div>
	
	<!--表单-->
	<table id="cs_table" class="datatable"></table>
	
	
	
</body>
</html>
