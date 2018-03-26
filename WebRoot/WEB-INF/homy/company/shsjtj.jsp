<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="hanson/css/common.css" />
<link rel="stylesheet" type="text/css" href="hanson/css/table.css"/>
<link rel="stylesheet" href="hanson/css/datepicker.css" />
<link rel="stylesheet" href="hanson/css/toastr.css" />
<!-- 解决模态框中时间控件被遮盖 -->
<style>
.datepicker{
	z-index: 100000;
}
</style>

<!--快速查询-->
<div class="quick_search">
	<form id="quick_search_from" action="">
		<span>公司ID:<input name="id" type="text" value="" placeholder="请输入公司ID"/></span>
		<span>公司名称:<input name="companyname" type="text" value="" placeholder="请输入公司名称"/></span>
		<span>日期:<input name="WdatePickerStart" type="text" data-date="2017-01-01" data-date-format="yyyy-mm-dd" value="" class="datepicker "/>~<input name="WdatePickerEnd" type="text" data-date="2017-01-01" data-date-format="yyyy-mm-dd" value="" class="datepicker "/></span>
	</form>
</div>
<!--对表单进行操作-->
<div class="from_handle">
	<a href="javascript:void(0)" class="shxxsjtj_btn"><b></b>商户详细数据统计</a>
	<a href="javascript:void(0)" class="exportExcel_btn"><b></b>导出excel</a>
	<a href="javascript:void(0)" class="search_btn" ><b></b>查询</a>
	<a href="javascript:void(0)" class="resert_btn" ><b></b>重置</a>
</div>
<!--表格-->
<div class="widget-box" style="overflow: auto;">
<table id="cs_table" class="datatable"></table>
</div>

<!--模态-商户详细数据统计-->
<div class="modal fade" id="shxisjtjModal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel" style="width:900px">
  	<div class="modal-dialog" role="document">
    	<div class="modal-content">
      		<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="shxisjtjModalLabel">商户详细数据统计</h4>
		    </div>
		    <div class="modal-body">
		    	<div class="quick_search">
		    		<div class="quick_search">
					<form id="quick_search_from_shxisjtj" action="">
						<input id="comid" name="comid" type="hidden" value="" />
						<span>日期:<input id="date01" name="WdatePickerStart" type="text" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'date02\')||\'2050-10-01\'}'})" value=""/>~<input id="date02" name="WdatePickerEnd" type="text"  value="" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'date01\')}', maxDate:'2050-10-01'})"/></span>
					</form>
					</div>
					<!--对表单进行操作-->
					<div class="from_handle">
						<a href="javascript:void(0)" class="search_btn_shxisjtj" ><b></b>查询</a>
						<a href="javascript:void(0)" class="resert_btn_shxisjtj" ><b></b>重置</a>
					</div>
					<!--表格-->
					<div class="widget-box" style="overflow: auto;">
					<table id="shxisjtj_table" class="datatable"></table>
					</div>
				</div>
		    </div>				
		  	<div class="modal-footer">
		    	<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		    	<button id="exportExcel_btn_shxisjtj" type="button" value="Validate" class="btn btn-primary">导出EXCEL</button>
		  	</div>
		</div>
	</div>
</div>



<script src="hanson/js/table.ajax.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="http://www.my97.net/My97DatePicker/WdatePicker.js"></script>
<!-- 消息提醒 -->
<script src="hanson/js/toastr.js"></script>
<script src="hanson/js/toastr.setting.js"></script>
<!-- 页面功能JS -->
<script src="hanson/js/hanson.shsjtj.js" type="text/javascript" charset="utf-8"></script>

