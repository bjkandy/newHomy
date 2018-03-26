//页面初始化
//query("");
var headers = [{title:"ID",code:"ID"},
			 {title:"商户编号",code:"qyid"},
			 {title:"订单号",code:"orderid"},
			 {title:"客户昵称",code:"cuname"},
			 /*{title:"提现编号",code:"merchanttxid"},*/
			 {title:"订单总金额",code:"totalprice"},
			 {title:"商品总金额",code:"productotallprice"},
			 {title:"邮费金额",code:"postage"},
			 /*{title:"数量",code:"number"},*/
			 {title:"订单状态",code:"orderstatus"},
			 {title:"支付状态",code:"paystatus"},
			 {title:"提现状态",code:"txStatus"},
			 {title:"订单日期",code:"createdate"},
			 {title:"支付方式",code:"paytype"},
			 {title:"更多",code:"orderdetail"}
			 
			 ];

var cs = new table({
	"cache":true,    //必须
	"url":"order/orderList",
    "tableId":"cs_table",    //必须
    "headers":headers,   //必须
    "currentPageNum":1,
    "displayNum": 20,    //必须   默认 10
    "groupDataNum":9    //可选    默认 10
});

//查询
function querybytj(){
	cs.paramData = $("#orderSearchForm").serializeArray();
	cs.init(0, cs.displayNum);
}

//excel导出
function exportExcel(){
	var flag_input = false;
	var flag_select = false;
	$("#orderSearchForm input[type='text']").each(function(i, obj) {
        if(obj.value == "") {
        	flag_input = false;
        }else{
        	flag_input = true;
        	return false;
        }
    });
	$("#orderSearchForm select").each(function(i, obj) {
        if(obj.value == "") {
        	flag_select = false;
        }else{
        	flag_select = true;
        	return false;
        }
    });
	
	if(flag_input || flag_select){
		$("#orderSearchForm").attr("action", "order/exportExcel");
		$("#orderSearchForm").submit();
	}else{
		alert("请至少输入一个查询条件");
	}
}

//控制超链接在主题中显示
$('#cs_table').on("click","tbody a[name='orderdetail']",function(e){
	
	e.preventDefault();
	var url = "/order/orderDetail?orderid="+$(this).attr("orderid");
	console.log(url);
	$("#content").empty();
	$("#content").load(url);
});

//控制超链接在主题中显示
$('#cs_table').on("click","tbody a[name='orderDownload']",function(e){
	
	e.preventDefault();
	var url = "/order/downloadOrder?orderid="+$(this).attr("orderid");
	window.location.href=url;
	/*console.log(url);
	$("#content").empty();
	$("#content").load(url);
	*/
	
	
});

//初始日期空间 -必须最后加载，否则第二次加载时会报错
$('.datepicker').datepicker();

