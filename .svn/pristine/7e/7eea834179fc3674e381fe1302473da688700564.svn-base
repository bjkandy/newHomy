//页面初始化
//query("");
var headers = [{title:"商品编号",code:"id"},
			 {title:"商品名称",code:"name"},
			 {title:"商品类型",code:"producttype"},
			 {title:"月销量",code:"yxl"},
			 {title:"总销量",code:"salecount"},
			 {title:"标准价（分）",code:"standardprice"},
			 {title:"是否可用",code:"status"},
			 {title:"创建时间",code:"createtime"}
			 ];

var cs = new table({
	"cache":true,    //必须
	"url":"shopping/productList",
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
	
	$("#productSearchForm").attr("action", "shopping/productExcel");
	$("#productSearchForm").submit();
}

//重置
function reset(){
	 $(':input','#productSearchForm')  
	 .not(':button, :submit, :reset, :hidden')  
	 .val('')  
	 .removeAttr('checked')  
	 .removeAttr('selected');
}

//是否有选中的行
function isSelected(){
	// 获得选中id
	var id = $("#cs_table .active").children().eq(0).html();
	if (id == null || id == "") {
		alert("请选择一条记录");
		return false;
	}else{
		return true;
	}
}

//删除记录
function deleteProduct() {
	// 获得选中id
	var id = $("#cs_table .active").children().eq(0).html();
	if (id == null || id == "") {
		alert("请选择一条记录");
		return false;
	}
	var data = "id=" + id;
	
	if(confirm("是否确认删除")){
		$.ajax({
			type : "POST",
			url : "shopping/deleteProduct",
			data : data,
			success : function(msg) {
				alert(msg);
				querybytj();
			}
		});
	}else{
		return false;
	}
	
}

//编辑商品
function editProduct(){
	if(isSelected()){
		//判断是相框还是相册
		//获取当前选中项的产品类型
		var producttype = $("#cs_table .active").children().eq(2).html();
		if(producttype == '相册'){
			var id = $("#cs_table .active").children().eq(0).html();
			$("#content").empty();
			$("#content").load("album/toXcfbEdit?ID="+id);
		}else{
			var id = $("#cs_table .active").children().eq(0).html();
			$("#content").empty();
			$("#content").load("shopping/toXkfbEdit?ID="+id);
		}
		
		
		/*var id = $("#cs_table .active").children().eq(0).html();
		$("#content").empty();
		$("#content").load("shopping/toXkfbEdit?ID="+id);*/
	}
	
}

//查看产品
function view(){
	if(isSelected()){
		//alert(1);
		var id = $("#cs_table .active").children().eq(0).html();
		$("#content").empty();
		$("#content").load("shopping/toXkfbView?ID="+id);
	}
}

//跳转到商品发过过渡页
function toNavigation() {
	//window.location.href="shopping/toNavigation";
	$("#content").empty();
	$("#content").load("shopping/toNavigation");
}


//选中效果
$('#cs_table').on("click","td",function(){
	console.log(this);
	$(this).parent().addClass("active").siblings().removeClass("active");
})
//同步生辰费用
$(".synchronizeproductfee").bind("click",function(){
	
	alert("gg");
	$.ajax({
        url: "shopping/SynchronProductFee",
        //data:{"spectids":x.slice(1)},
        type: "POST",
        dataType:"json",
        beforeSend:function(){
            return true;
        },
        success:function(result){
        	if(result!=null && result.errorCode == "000000"){
    			toastr.success("生产费用同步成功!");
    		}else{
    			toastr.warning(result.msg);
    		}   	
        	//$('#companyMakeModal').modal('hide');
        },
        error:function(){
        	toastr.error('网络错误');
        },
        complete:function(){
        	
        }
    });
	
});
//设置生产费用
$(".companyMakeModal").bind("click",function(){
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择产品");
		return false;
	}
	var id = $('#cs_table').find("tr.active").attr("data-id");
	var name = $('#cs_table').find("tr.active td:nth-child(2)").html();
	$("#productName").html(name);
	var type = $('#cs_table').find("tr.active td:nth-child(3)").html();
	//var $formBody = $("#companyMakeModal .modal-body");
	//获取数据
	$.ajax({
        url: "shopping/SettingProductFee",
        data:{"id":id},
        type: "POST",
        dataType:"json",
        beforeSend:function(){
            return true;
        },
        success:function(data){
        	console.log(data);
        	if(data.rows.length != 0){
        		$("#companyMaketable tbody").empty();
            	$("#companyMaketable thead").empty();
            	if(type == "照片框" || type == "相框"){
            		$("#companyMaketable thead").append('<tr><th>尺寸</th><th>颜色</th><th>生产费用</th></tr>');
            	}else if(type == "相册"){
            		$("#companyMaketable thead").append('<tr><th>尺寸</th><th>张数</th><th>生产费用</th></tr>');
            	}else{//照片冲印
            		$("#companyMaketable thead").append('<tr><th>尺寸</th><th>材质</th><th>是否过塑</th><th>生产费用</th></tr>');
            	}
            	$.each(data.rows,function(i,v){
            		if(type == "照片"){
                		$("#companyMaketable tbody").append(
                			'<tr><td>'+v.size+'</td><td>'+v.specinfo+'</td><td>'+(v.plastic==0?"否":"是")+'</td><td><input data-id="'+v.id+'" type="number" name="storeproductcost" value="'+v.storeproductcost/100+'" /></td></tr>'
                		);
                	}else if(type == "照片框"){
                		$("#companyMaketable tbody").append(
                			'<tr><td>'+v.size+'</td><td>'+v.color+'</td><td><input data-id="'+v.id+'" type="number" name="storeproductcost" value="'+v.storeproductcost/100+'" /></td></tr>'
                		);
                	}else if(type == "相册"){
                		$("#companyMaketable tbody").append(
                			'<tr><td>'+v.size+'</td><td>2</td><td><input data-id="'+v.id+'" type="number" name="storeproductcost" value="'+v.storeproductcost/100+'" /></td></tr>'
                		);
                	}
            	});
        	}else{
            	$('#companyMakeModal').modal('hide');
        	}
        },
        error:function(){
        	toastr.error('网络错误');
        },
        complete:function(){
        	
        }
    });
	//打开模态框
	$('#companyMakeModal').modal('show');
});

function cost(){
	var x = '';
	$.each($("#companyMaketable input"),function(i,v){
		x += ','+$(v).attr("data-id")+'_'+$(v).val();
	});

	$.ajax({
        url: "shopping/ModifyProductFee",
        data:{"spectids":x.slice(1)},
        type: "POST",
        dataType:"json",
        beforeSend:function(){
            return true;
        },
        success:function(result){
        	if(result!=null && result.errorCode == "000000"){
    			toastr.success("生产费用设置成功!");
    		}else{
    			toastr.warning(result.msg);
    		}   	
        	$('#companyMakeModal').modal('hide');
        },
        error:function(){
        	toastr.error('网络错误');
        },
        complete:function(){
        	
        }
    });
}