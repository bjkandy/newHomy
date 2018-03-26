/**
 * 活跃商户页面功能
 */

//设置表头
var headers = [{title:"日期",code:"date"},
 {title:"起始商户数",code:"startcomanyamount"},
 {title:"净增商户数",code:"addcompanyamount"},
 {title:"订单增量",code:"addordernumber"},
 {title:"订单增量金额",code:"addorderamount"},
 {title:"已支付订单增强",code:"payedaddorder"},
 {title:"已支付订单增量金额",code:"payedordermoney"},
 {title:"照片增量",code:"addPhoto"},
 {title:"已支付照片增量",code:"payedAddPhoto"},
 ];

//初始化表格	 
var cs = new table({
    "cache":true,    //必须
    "url":"merchantsort/jsonlist", 
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
	location.href = "merchantsort/exportExcel?excel&"+params;
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

//初始日期空间
$('.datepicker').datepicker();


