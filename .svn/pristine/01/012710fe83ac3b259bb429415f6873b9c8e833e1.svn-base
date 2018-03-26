//页面初始化
var headers = [{title:"商户流水单号",code:"storeorderid"},
             {title:"交易时间",code:"createtime"},
			 {title:"交易类型",code:"consumetype"},
			 {title:"收入/支出",code:"chargemoney"},
			 {title:"明细",code:"orderdetail"},
			 {title:"余额",code:"leftmoney"},
			 {title:"门店",code:"companyname"},
			 {title:"对应订单号",code:"orderid"},
			 ];

var cs = new table({
	"cache":true,    //必须
	"url":"reservation/SaleStoresList?storeid="+localStorage.storeid,
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

function aClick(obj){
	if(obj != null){
		$("#mingxitable tbody").empty();
		$.each(obj,function(i,v){
			if(v.producttype == 10){
				$("#mingxitable tbody").append("<tr><td>"+v.photoSize+"_"+v.photoSpec+" * "+v.photoNum+"</td><td>"+v.photoProductionCost/100+" * "+v.photoChargerebate+" * "+v.photoNum+"</td></tr>");
			}else if(v.producttype == 20){
				$("#mingxitable tbody").append("<tr><td>"+v.photoFrameSize+"_"+v.photoFrameName+" * "+v.photoFrameNum+"</td><td>"+v.photoFrameProductionCost/100+" * "+v.photoFrameChargerebate+" * "+v.photoFrameNum+"</td></tr>");
			}else if(v.producttype == 40){
				$("#mingxitable tbody").append("<tr><td>"+v.albumSize+" * "+v.albumNum+"</td><td>"+v.albumProductionCost/100+" * "+v.albumChargerebate+" * "+v.albumNum+"</td></tr>");
			}
		});
		$('#mingxiModal').modal('show');
	}else{
		alert("充值记录无法显示！");
	}
}

$.ajax({
    url: "/reservation/GetStoreInfo",
    data:{"storeid":localStorage.storeid},
    type: "POST",
    dataType:"json",
    beforeSend:function(){
        return true;
    },
    success:function(data){
    	console.log(data);
    	$(".shid").html(data.id);
    	$(".shname").html(data.companyname);
    	$(".shcore").html(data.leftmoney/100);
    	//$(".discount").html(data.leftmoney+"折");
    	$(".shstatus").html(data.normalaccount == 0 ? "不正常" : "正常");
    	$(".shtime").html(data.createtime);
    },
    error:function(){
    	toastr.error('网络错误');
    },
    complete:function(){
    	
    }
});