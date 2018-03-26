<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  <link rel="stylesheet" type="text/css" href="hanson/css/table.css"/>
  <link rel="stylesheet" href="hanson/css/common.css" />
  <link rel="stylesheet" href="hanson/css/datepicker.css" />

  </head>
  
  <body>
  	<form id="txjsSearchForm">
		<div class="quick_search">
			<span>提现编号:<input type="text" id="id" name="id" /> </span>
			<span>商户编号:<input type="text" id="qyid" name="qyid" /> </span> 
			<span>商户名称:<input type="text" id="companyname" name="companyname" /> </span> 
			<span>提现状态:
				<select id="status" name="status">
					<option value="">请选择</option>
					<option value="10">未提现</option>
					<option value="20">提现中</option>
					<option value="40">成功提现</option>
				</select> 
			</span> 
			<span>审核状态:
				<select id="checkStatus" name="checkStatus1">
					<option value="">请选择</option>
					<option value="1">未审核</option>
					<option value="2">审核中</option>
					<option value="3">已审核</option>
				</select> 
			</span> 
			<!-- <span>申请时间:<input type="text" id="kdate" name="kdate" />~<input type="text" id="jdate" name="jdate" /> </span><br />
			<span>结算时间:<input type="text" id="kdateJs" name="kdateJs" />~<input type="text" id="jdateJs" name="jdateJs" /> </span><br />  -->
			<span>申请时间:<input id="kdate" name="kdate" type="text" data-date="2017-01-01" data-date-format="yyyy-mm-dd" value="" class="datepicker "/>~<input id="jdate" name="jdate" type="text" data-date="2017-01-01" data-date-format="yyyy-mm-dd" value="" class="datepicker"/></span><br>
			<span>结算时间:<input id="kdateJs" name="kdateJs" type="text" data-date="2017-01-01" data-date-format="yyyy-mm-dd" value="" class="datepicker "/>~<input id="jdateJs" name="jdateJs" type="text" data-date="2017-01-01" data-date-format="yyyy-mm-dd" value="" class="datepicker"/></span><br>
		</div>
	</form>
  
  <div class="from_handle">
	<a href="javascript:daKuan()"><b></b>打款</a>
	<a href="javascript:exportExcel()"><b></b>导出excel</a>
	<a href="javascript:querybytj()"><b></b>查询</a>
	<a href="#"><b></b>重置</a>
</div> 
<!--表单-->
<table id="cs_table" class="datatable"></table>
<script src="hanson/js/bootstrap-datepicker.js"></script>
<script src="hanson/js/table.ajax.js" type="text/javascript" charset="utf-8"></script>
<script src="hanson/js/hanson.fxgl.js" type="text/javascript" charset="utf-8"></script>


  </body>
</html>
