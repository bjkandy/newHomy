//页面初始化
var headers = [{title:"编号",code:"id"},
			 {title:"标题",code:"title"},
			 {title:"通知类型",code:"announcetype"},
			 {title:"发布时间",code:"createtime"},
			 {title:"发布作者",code:"createUser"},
			 {title:"设置",code:"setview"},
			 {title:"操作",code:"selectview"},
			 ];

var cs = new table({
	"cache":true,    //必须
	"url":"actionCenter/jsonlist",
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
function cancelClick(id){
	$.ajax({
    	url: "actionCenter/setRecommend",
    	type:"POST",
    	dataType:"json",
    	data:{"id": id},
    	success:function(result){
    		if(result!=null && result.errorCode == "000000"){
    			toastr.success("设置成功.");
    			cs.init(0, cs.displayNum);
    		}else{
    			toastr.warning(result.msg);
    		}
    	}
    });
}
function dClick(id){
	$.ajax({
    	url: "actionCenter/deleteone",
    	type:"POST",
    	dataType:"json",
    	data:{"id": id},
    	success:function(result){
    		if(result!=null && result.errorCode == "000000"){
    			toastr.success("删除成功.");
    			cs.init(0, cs.displayNum);
    		}else{
    			toastr.warning(result.msg);
    		}
    	}
    });
}
