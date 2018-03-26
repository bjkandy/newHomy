//页面初始化
//query("");
var headers = [
			 {title:"提现编号",code:"ID"},
			 {title:"商户编号",code:"qyid"},
			 {title:"商户名称",code:"companyname"},
			 {title:"提现金额",code:"amount"},
			 {title:"返现类型",code:"txtype"},
			 {title:"订单数量",code:"orderNumber"},
			 {title:"提现状态",code:"status"},
			 {title:"审核状态",code:"checkStatus"},
			 {title:"申请时间",code:"createdate"},
			 {title:"结算时间",code:"finishdate"},
			 {title:"操作",code:"orders"},
			 ];

var cs = new table({
	"cache":true,    //必须
	"url":"txjs/txjsList",
    "tableId":"cs_table",    //必须
    "headers":headers,   //必须
    "currentPageNum":1,
    "displayNum": 10,    //必须   默认 10
    "groupDataNum":10    //可选    默认 10
});

//查询
function querybytj(){
	cs.paramData = $("#txjsSearchForm").serializeArray();
	cs.init(0, cs.displayNum);
}

//打款
function daKuan() {
	// 获得选中id
	var s = $("#cs_table .active").children().eq(5).html();
	var id = $("#cs_table .active").children().eq(0).html();
	if (s == null || s == "") {
		alert("请选择一条打款记录");
		return;
	}
	if (s == "未提现") {
		alert("未提现不能打款");
		return;
	}
	if (s == "成功提现") {
		alert("请勿重复打款");
		return;
	}
	var data = "id=" + id;
	$.ajax({
		type : "POST",
		url : "txjs/daKuan",
		data : data,
		success : function(msg) {
			alert(msg);
			querybytj();
		}
	});
}

//excel导出
function exportExcel(){
	var flag_input = false;
	var flag_select = false;
	$("#txjsSearchForm input[type='text']").each(function(i, obj) {
        if(obj.value == "") {
        	flag_input = false;
        }else{
        	flag_input = true;
        	return false;
        }
    });
	$("#txjsSearchForm select").each(function(i, obj) {
        if(obj.value == "") {
        	flag_select = false;
        }else{
        	flag_select = true;
        	return false;
        }
    });
	
	if(flag_input || flag_select){
		$("#txjsSearchForm").attr("action", "txjs/exportExcel");
		$("#txjsSearchForm").submit();
	}else{
		alert("请至少输入一个查询条件");
	}
}

//选中行效果
$('#cs_table').on("click","td",function(){
	console.log(this);
	$(this).parent().addClass("active").siblings().removeClass("active");
});

//控制超链接在主题中显示
$('#cs_table').on("click","tbody a",function(e){
	e.preventDefault();
	var url = "/txjs/ordersView?id="+$(this).attr("txid");
	console.log(url);
	$("#content").empty();
	$("#content").load(url);
});

//初始日期空间 -必须最后加载，否则第二次加载时会报错
$('.datepicker').datepicker();