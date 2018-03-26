//页面初始化
//query("");
var headers = [
			 {title:"订单编号",code:"orderid"},
			 {title:"用户昵称",code:"cuname"},
			 {title:"商户编号",code:"qyid"},
			 {title:"订单金额",code:"totalprice"},
			 {title:"产品总金额",code:"productotallprice"},
			 {title:"邮费金额",code:"youfeijine"},
			 {title:"结算金额",code:"fyamount"},
			 /*{title:"结算方式",code:"jiesuanfangshi"},*/
			 {title:"生产状态",code:"orderstatus"},
			 {title:"配送方式",code:"expresstype"},
			 {title:"快递公司",code:"ExpressCompanyName"},
			 {title:"快递编号",code:"expressno"},
			 {title:"提现状态",code:"merchanfetstatus"},
			 {title:"下单日期",code:"createdate"},
			 ];

var cs = new table({
	"cache":true,    //必须
	"url":"txjs/txjsOrderList?id="+txid,
    "tableId":"cs_table",    //必须
    "headers":headers,   //必须
    "currentPageNum":1,
    "displayNum": 10,    //必须   默认 10
    "groupDataNum":10    //可选    默认 10
});


//excel导出
function exportExcel(){
	var kdate = $("#kdate").val();
	var jdate = $("#jdate").val();
	if(kdate == '' || jdate == ''){
		alert("请输入一个申请时间段作为导出条件");
	}else{
		$("#txjsSearchForm").attr("action", "txjs/exportExcel");
		$("#txjsSearchForm").submit();
	}
}

//选中行效果
$('#cs_table').on("click","td",function(){
	console.log(this);
	$(this).parent().addClass("active").siblings().removeClass("active");
});

//返回
function back(){
	var url = "/txjs/txjs";
	$("#content").empty();
	$("#content").load(url);
}

//查询
function querybytj(){
	cs.paramData = $("#orderSearchForm").serializeArray();
	cs.init(0, cs.displayNum);
}

