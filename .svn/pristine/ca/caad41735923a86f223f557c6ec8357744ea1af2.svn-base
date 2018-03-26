//页面初始化
//query("");
var headers = [{title:"商品编号",code:"id"},
			 {title:"商品名称",code:"projectname"},
			 {titile:"英文名称",code:"englishname"},
			 {title:"拍摄类型",code:"shootingtype"},
			 {title:"项目总金额(单位:分)",code:"totalamount"},
			 {title:"是否可用",code:"updownframestatus"},
			 {title:"服务说明",code:"serviceinfo"},
			 {title:"用途",code:"mainfunction"},
			 {title:"专业特设内容",code:"specialcontent"},
			 {title:"增值服务",code:"addservice"},
			 {title:"服务销量",code:"salesvolume"},
			 {title:"创建时间",code:"createdate"}
			 ];

var cs = new table({
	"cache":true,    //必须
	"url":"shopping/yuepaiList",
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
	
	$("#productSearchForm").attr("action", "shopping/YuepaiExcel");
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
			url : "shopping/deleteyuepai",
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

	    var id = $("#cs_table .active").children().eq(0).html();
	    if (id == null || id == "") {
			alert("请选择一条记录");
			return ;
		}
		$("#content").empty();
		$("#content").load("shopping/toyuepaiEdit?ID="+id);
}

function editPrice(){
	var id = $("#cs_table .active").children().eq(0).html();
    if (id == null || id == "") {
		alert("请选择一条记录");
		return ;
	}
    $("#content").empty();
	$("#content").load("shopping/toyuepaiPriceEdit?ID="+id);
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


//选中效果
$('#cs_table').on("click","td",function(){
	console.log(this);
	$(this).parent().addClass("active").siblings().removeClass("active");
})

function selectprojectname(){
	var projectname = $("#projectname").val();
	if(projectname==""){
       return ;		
	}
	$.ajax({
		type : "post",
		url : "album/selectProName?projectname="+projectname,
		success : function(data) {
			var json = JSON.parse(data); 
			if(json.result > 0){
				alert(projectname+"已存在");
				$("#projectname").select();
			}
		},
		error : function(data) {
			
		}
	});
}
function toNavigation() {
	$("#content").empty();
	$("#content").load("shopping/yuepaiFrame");
}
function deleteyuepaiProduct(){
	
	var id = $("#cs_table .active").children().eq(0).html();
	if (id == null || id == "") {
		alert("请选择一条记录");
		return false;
	}
	var data = "id=" + id;
	
	if(confirm("是否确认删除")){
		$.ajax({
			type : "POST",
			url : "shopping/deleteyuepaiProduct",
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
