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
	
	<title>预约管理</title>
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
	<script src="hanson/js/bootstrap-datepicker.js"></script>
	<script src="hanson/js/toastr.js"></script>
	<script src="hanson/js/toastr.setting.js"></script>
	
	<link rel="stylesheet" href="hanson/css/toastr.css" />
</head>

<body>
	<!--快速查询-->
	<form id="orderSearchForm">
		<div class="quick_search">
			<span>服务商户编号:<input type="text" id="qyid" name="qyid" /> </span> 
			<span>推广商户编号:<input type="text" id="tgid" name="tgid" /> </span> 
			<span>联系人:<input type="text" id="linkMan" name="linkMan" /> </span> 
			<span>订单编号:<input type="text" id="orderid" name="orderid" /> </span> 
			<!-- <span>订单日期:<input type="text" id="kdate" name="kdate" />~<input type="text" id="jdate" name="jdate" /> </span><br /> -->
			<span>预约时间:<input id="yykdate" name="yykdate" type="text" data-date="2017-01-01" data-date-format="yyyy-mm-dd" value="" class="datepicker "/>~<input id="yyjdate" name="yyjdate" type="text" data-date="2017-01-01" data-date-format="yyyy-mm-dd" value="" class="datepicker"/></span><br />
			<span>下单时间:<input id="xdkdate" name="xdkdate" type="text" data-date="2017-01-01" data-date-format="yyyy-mm-dd" value="" class="datepicker "/>~<input id="xdjdate" name="xdjdate" type="text" data-date="2017-01-01" data-date-format="yyyy-mm-dd" value="" class="datepicker"/></span><br />
			<span>订单状态:
				<select id="orderstatus" name="orderstatus">
					<option value="">请选择</option>
					<option value="">已关闭</option>
					<option value="">待支付</option>
					<option value="">待拍摄</option>
					<option value="">拍摄中</option>
					<option value="">已完成</option>
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
	<!-- 表格 -->
	<table>
		<thead>
			<tr>
				<td>服务商户编号</td>
				<td>订单编号</td>
				<td>用户昵称</td>
				<td>联系人</td>
				<td>联系电话</td>
				<td>预约时间</td>
				<td>订单金额(元)</td>
				<td>订单状态</td>
				<td>摄影师</td>
				<td>下单日期</td>
				<td>推广商户编号</td>
				<td>更多</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>11111</td>
				<td>11111</td>
				<td>11111</td>
				<td>11111</td>
				<td>11111</td>
				<td>11111</td>
				<td>11111</td>
				<td>11111</td>
				<td>11111</td>
				<td>11111</td>
				<td>11111</td>
				<td>11111</td>
				<td><a href="#">更多</a></td>
			</tr>
		</tbody>
	</table>
</body>
</html>