<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="hanson/css/common.css" />
<link rel="stylesheet" type="text/css" href="hanson/css/table.css"/>
<link rel="stylesheet" href="hanson/css/datepicker.css" />

<!--快速查询-->
<div class="quick_search">
	<form id="quick_search_from" action="">
		<span>公司名称:<input name="companyname" type="text" value="" placeholder="请输入公司名称"/></span>
		<span>选择日期:<input name="date" type="text" data-date="2017-01" data-date-format="yyyy-mm" value="" class="datepicker "/></span>
	</form>
</div>
<!--对表单进行操作-->
<div class="from_handle">
	<a href="javascript:void(0)" class="exportExcel_btn"><b></b>导出excel</a>
	<a href="javascript:void(0)" class="search_btn" ><b></b>查询</a>
	<a href="javascript:void(0)" class="resert_btn" ><b></b>重置</a>
</div>
<!--表格-->
<div class="widget-box" style="overflow: auto;">
<table id="cs_table" class="datatable"></table>
</div>

<script src="hanson/js/table.ajax.js" type="text/javascript" charset="utf-8"></script>
<script src="hanson/js/bootstrap-datepicker.js"></script>
<!-- 页面功能JS -->
<script src="hanson/js/hanson.hysh.js" type="text/javascript" charset="utf-8"></script>

