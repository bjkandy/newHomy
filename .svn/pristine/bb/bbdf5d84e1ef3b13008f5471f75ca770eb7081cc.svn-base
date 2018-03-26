//设置表头
var headers = [{title:"设备ID",code:"id"},
 {title:"商户id",code:"qyid"},
 {title:"设备名",code:"equipmentname"},
 {title:"省",code:"provincename"},
 {title:"市",code:"cityname"},
 {title:"区/县",code:"districtname"},
 {title:"详细地址",code:"address"}
 ];

//初始化表格	 
var cs = new table({
    "cache":true,    //必须
    "url":"/equipment/pagingQuery", 
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


//AJAX提交表单
function check_form(){
    var form_data = $('#companyInputForm').serialize();
    // 异步提交数据
    $.ajax({
		url: "/equipment/insert",
		data: form_data,
		type: "POST",
		dataType:"json",
		beforeSend:function(){
		//提交之前执行，可用于禁用按钮，避免重复点击
			return true;
		},
		success:function(data){
//			console.log(data);
        	if(data != null && data.errorCode == "000000"){
		    	//重新加载表格
		    	cs.init(0, cs.displayNum);
		    	toastr.success('保存成功');
		    	$('#myModal').modal('hide');
		    	return true;
		    }else{
		    	toastr.warning(data.message);
		        return false;
		    }
		},
		error:function(){
			toastr.error("网络错误");
		    return false;
		},
		complete:function(){
		   	//不管是否成功都执行
		}
	});
	//执行失败
    return false;
}

//绑定-二维码按钮
$(".ercode_btn").bind("click",function(){
	//判断是否选择一项
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择企业");
		return false;
	}
	var id = $('#cs_table').find("tr.active").attr("data-id");
	var $formBody = $("#companyQrcodeModal .modal-body");
	//获取数据
	$.ajax({
        url: "company/createQrcode",
        data:{"id":id},
        type: "POST",
        dataType:"json",
        success:function(data){
        	$formBody.find("h3").html(data.msg);
    		$("#images").attr("src","http://hmwc-test.homy.cc/"+ data.url);
        }
    });
	//打开模态框
	$('#companyQrcodeModal').modal('show');
});

//绑定-搜索按钮
$(".search_btn").bind("click",function(){
	cs.paramData = $("#quick_search_from").serializeArray();
	cs.init(0, cs.displayNum);
});

//绑定-导出按钮
$(".exportExcel_btn").bind("click",function(){
	var params = $("#quick_search_from").serialize();
	//location.href = "company/exportExcel?excel&"+params;
});

//初始日期空间 -必须最后加载，否则第二次加载时会报错
$('.datepicker').datepicker();