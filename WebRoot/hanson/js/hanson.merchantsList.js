//页面初始化
var headers = [{title:"序列号",code:"id"},
			 {title:"商户名",code:"companyname"},
			 {title:"商户等级",code:"grade"},
			 {title:"充值总金额",code:"totalmoney"},
			 {title:"余额",code:"leftmoney"},
			 {title:"查看",code:"selectview"},
			 ];

var cs = new table({
	"cache":true,    //必须
	"url":"reservation/jsonlist",
    "tableId":"cs_table",    //必须
    "headers":headers,   //必须
    "currentPageNum":1,
    "displayNum": 20,    //必须   默认 10
    "groupDataNum":9    //可选    默认 10
});

//查询
function querybytj(){
	cs.paramData = $("#productSearchForm").serializeArray();
	cs.init(0, cs.displayNum);
}

//excel导出
function productExcel(){
	$("#productSearchForm").attr("action", "reservation/VipStoreExcel");
	$("#productSearchForm").submit();
}
//进入详情页
function aClick(obj){
	var url = "/reservation/storeDetail";
	localStorage.storeid = obj;
	$("#content").empty();
	$("#content").load(url);
}