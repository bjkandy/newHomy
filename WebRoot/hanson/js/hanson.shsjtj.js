/**
 * 活跃商户页面功能
 */

//设置表头
var headers = [{title:"公司ID",code:"id"},
 {title:"公司名称",code:"companyname"},
 {title:"粉丝数",code:"fansnumall"},
 {title:"粉丝增量",code:"fansnum"},//
 {title:"下单总金额",code:"moneyamountall"},//
 {title:"支付总金额",code:"moneyamount"},
 {title:"商品总金额",code:"moneyptotal"},
 {title:"下单总数量",code:"ordernumall"},
 {title:"支付订单数量",code:"ordernum"},
 {title:"下单照片数量",code:"photoaddall"},
 {title:"支付照片数量",code:"photoadd"}];

//初始化表格	 
var cs = new table({
    "cache":true,    //必须
    "url":"merchant/jsonlist", 
    "tableId":"cs_table", 
    "headers":headers,   //必须
    "currentPageNum":1,
    "displayNum": 10,    //必须   默认 10
   	"groupDataNum":9  //可选    默认 10
});

//为表格添加选中样式
$('#cs_table').on("click","td",function(){
	$(this).parent().addClass("active").siblings().removeClass("active");
});

//绑定-导出按钮
$(".exportExcel_btn").bind("click",function(){
	var params = $("#quick_search_from").serialize();
	location.href = "merchant/exportExcel?excel&"+params;
});

//绑定-搜索按钮
$(".search_btn").bind("click",function(){
	cs.paramData = $("#quick_search_from").serializeArray();
	cs.init(0, cs.displayNum);
});

//绑定-重置按钮
$(".resert_btn").bind("click",function(){
	$("#quick_search_from")[0].reset();
	cs.paramData = {};
	cs.init(0, cs.displayNum);
});


//初始化商户详细数据统计表格
var shsjtj_headers = [{title:"时间",code:"day"},
 {title:"粉丝数",code:"fansnumall"},
 {title:"粉丝增量",code:"fansnum"},//
 {title:"下单总金额",code:"moneyamountall"},//
 {title:"支付总金额",code:"moneyamount"},
 {title:"商品总金额",code:"moneyptotal"},
 {title:"下单总数量",code:"ordernumall"},
 {title:"支付订单数量",code:"ordernum"},
 {title:"下单照片数量",code:"photoaddall"},
 {title:"支付照片数量",code:"photoadd"}];

 //初始化商户详细数据统计表格	 
 var shxisjtj_table = new table({
     "cache":true,    //必须
     "url":"merchant/increment", 
     "tableId":"shxisjtj_table", 
     "headers":shsjtj_headers,   //必须
     "currentPageNum":1,
     "displayNum": 10,    //必须   默认 10
 });


//绑定-商户详细数据统计
$(".shxxsjtj_btn").bind("click",function(){
	//判断是否选择一项
//	console.log($('#cs_table').find("tr.active"));
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择商户");
		return false;
	}
	//判断列表页是否有选择时间
	var WdatePickerStart = $("#quick_search_from [name='WdatePickerStart']").val();
	var WdatePickerEnd = $("#quick_search_from [name='WdatePickerEnd']").val();
	
	var id = $('#cs_table').find("tr.active").attr("data-id");
	var $formInput = $("#quick_search_from_shxisjtj");
	$formInput.find("#comid").val(id);
	$formInput.find("[name='WdatePickerStart']").val(WdatePickerStart);
	$formInput.find("[name='WdatePickerEnd']").val(WdatePickerEnd);
	shxisjtj_table.paramData = $("#quick_search_from_shxisjtj").serializeArray();
	shxisjtj_table.init(0, shxisjtj_table.displayNum);
	
	//打开模态框
	$('#shxisjtjModal').modal('show');
});

//绑定-搜索按钮
$(".search_btn_shxisjtj").bind("click",function(){
	shxisjtj_table.paramData = $("#quick_search_from_shxisjtj").serializeArray();
	shxisjtj_table.init(0, shxisjtj_table.displayNum);
});

//绑定-重置按钮
$(".resert_btn_shxisjtj").bind("click",function(){
	$("#quick_search_from_shxisjtj")[0].reset();
	shxisjtj_table.paramData = {};
	shxisjtj_table.init(0, shxisjtj_table.displayNum);
});

//绑定-导出按钮
$("#exportExcel_btn_shxisjtj").bind("click",function(){
	var params = $("#quick_search_from_shxisjtj").serialize();
	location.href = "merchant/exportXlsMerchantList?excel&"+params;
});

//初始日期空间
$('.datepicker').datepicker();