<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>预存商户列表</title>
<link rel="stylesheet" type="text/css" href="hanson/css/table.css" />
<link rel="stylesheet" href="hanson/css/common.css" />
<link rel="stylesheet" href="hanson/css/toastr.css" />
<style type="text/css">
	#companyMaketable{
		width: 100%;
		text-align: center;
	}
	#companyMaketable th{text-align: center;}
	#companyMaketable input{width:100px;}
</style>
<!-- 消息提醒 -->
<script type="text/javascript" src="http://www.my97.net/My97DatePicker/WdatePicker.js"></script>
<script src="hanson/js/toastr.js"></script>
<script src="hanson/js/toastr.setting.js"></script>
<script src="hanson/js/table.ajax.js" type="text/javascript" charset="utf-8"></script>
<script src="hanson/js/hanson.merchantsList.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<!--快速查询-->
	<form id="productSearchForm">
		<div class="quick_search">
			<span>商户名:<input type="text" id="companyname" name="companyname" /></span> 
			<span>商户id:<input type="text" id="companyid" name="companyid" /></span> 
		 	<span>初次预存时间:
				<input id="date01" name="WdatePickerStart" type="text" placeholder="开始时间" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'date02\')||\'2050-10-01\'}'})" />~
				<input id="date02" name="WdatePickerEnd" type="text" placeholder="结束时间" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'date01\')}', maxDate:'2050-10-01'})"/>
			</span> 
		</div>
	</form>

	<!--对表单进行操作-->
	<div class="from_handle">
		<a href="#" style="display: none;"><b></b></a>
		<a href="javascript:productExcel()"><b></b>导出产品excel</a>
		<a href="javascript:querybytj()"><b></b>查询</a>
	</div>
	<!--表单-->
	<table id="cs_table" class="datatable"></table>
</body>
</html>