<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>预存商户明细</title>
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
	#accountInformation{
		list-style:none;
		font-size:18px;
		overflow:hidden;
		margin:0px;
		padding-left: 10px;
    	padding-top: 10px;
	}
	#accountInformation li{float:left;margin-right:20px;}
</style>
<!-- 消息提醒 -->
<script type="text/javascript" src="http://www.my97.net/My97DatePicker/WdatePicker.js"></script>
<script src="hanson/js/toastr.js"></script>
<script src="hanson/js/toastr.setting.js"></script>
<script src="hanson/js/table.ajax.js" type="text/javascript" charset="utf-8"></script>
<script src="hanson/js/hanson.reservationDetail.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<!-- 账户信息 -->
	<ul id="accountInformation">
		<h4>账户信息</h4>
		<li>商户ID:<span class="shid">1222</span></li>
		<li>商户名:<span class="shname">汇美103</span></li>
		<li>账户余额（元）:<span class="shcore">100.00</span></li>
		<li>折扣:<span class="discount">8折</span></li>
		<li>账户状态:<span class="shstatus">正常</span></li>
		<li>初次预存时间:<span class="shtime">2017-10-19 11:00:00</span></li>
	</ul>
	<!--快速查询-->
	<form id="productSearchForm">
		<input type="hidden" id="storeid" value="${storeid}">
		<div class="quick_search">
			<h4>账户明细</h4>
			<span>订单号:<input type="text" id="orderid" name="orderid" /></span> 
			<span>交易类型:
				<select id="consumetype" name="consumetype">
					<option value="">请选择交易类型</option>
					<option value="20">生产费用抵扣</option>
			    	<option value="10">预存充值</option>
				</select>
			</span> 
		 	<span>交易时间:
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
	<!--模态-查看明细-->
	<div class="modal hide fade" id="mingxiModal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	  	<div class="modal-dialog" role="document">
	    	<div class="modal-content">
	      		<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="mingxiModalLabel">交易明细</h4>
			    </div>
			    <div class="modal-body">
					<table id="mingxitable" class="datatable">
						<thead><tr><th>产品</th><th>生产费</th></tr></thead>
						<tbody></tbody>
					</table>
			    </div>				
			  	<div class="modal-footer">
			    	<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
			  	</div>
			</div>
		</div>
	</div>
	<!--表单-->
	<table id="cs_table" class="datatable"></table>
</body>
</html>