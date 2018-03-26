/**
 * 评价管理页面功能
 */
//初始化录入页面元素
//$("#companytype").select2({width: "200px",placeholder: "请选择"});
//$("#msgtype").select2({width: "200px",});
//$("#parentid").select2({width: "200px",placeholder: "请选择"});
//隐藏授权指派
$("#assignmentDiv").hide();
/*//隐藏商户级别
$("#merchantlevelDiv").hide();*/
//隐藏指派生产中心
$("#setCenterDiv").hide();
//隐藏其他信息
$("#otherInfo").hide();
//消息与默认
//$("#msgtype").select2("val", 1);//TODO

//点击表格添加active
//设置表头
var headers = [
 {title:"选择",code:"checkbox"},
 {title:"编号",code:"id"},
 {title:"评价",code:"content"},
 {title:"服务商户",code:"companyname"},
 {title:"商品名",code:"name"},
 {title:"订单编号",code:"orderid"},
 {title:"用户昵称",code:"nickname"},
 {title:"评价时间",code:"createtime"},
 {title:"操作",code:"action"}
 ];

//初始化表格	 
var cs = new table({
    "cache":true,    //必须
    "url":"/comment/jsonList", 
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

$('#myModal').on('hide.bs.modal',function() {
	$("#companyInputForm").find("#id").remove();
});


//绑定-星级评定搜索
$(':radio[name="optionsRadiosinline"]').bind("click",function(){
	var start=$("input[name='optionsRadiosinline']:checked").val();
	$(':input[name="start"]').val(start);
	cs.paramData = $("#quick_search_from").serializeArray();
	cs.init(0, cs.displayNum);
	CheckAll();
});

//function actionOfEvulation() {
//	var actionOfEvulation=$("#txStatus").val();
//	if(actionOfEvulation==''){
//	
//	}
//}

//绑定-搜索按钮
$("#search_btn").bind("click",function(){
	var start=$("input[name='optionsRadiosinline']:checked").val();
	$(':input[name="start"]').val(start);
	cs.paramData = $("#quick_search_from").serializeArray();
	cs.init(0, cs.displayNum);
	CheckAll();
});

//绑定-重置按钮
$("#reset").bind("click",function(){
	$("#quick_search_from")[0].reset();
//	cs.paramData = {};
	//cs.init(0, cs.displayNum);
});


function doSearch()
{
	var start=$("input[name='optionsRadiosinline']:checked").val();
	$(':input[name="start"]').val(start);
	cs.paramData = $("#quick_search_from").serializeArray();
	cs.init(0, cs.displayNum);
	CheckAll();
}




//初始日期空间 -必须最后加载，否则第二次加载时会报错
$('.datepicker').datepicker();

 function getContent() {
	 var action=$("#txStatus").val();
	 if(action=='')
       {
    	alert("请选择你要执行的操作!");
    	return false;
	       } else {
		if (action == 0) {
			action = "show";
		} else if (action == 1) {
			action = "hide";
		} else if (action == 2) {
			action = "delete";
		}
	}
	 var chk_value =[]; 
     $('input[type="checkbox"]:checked').each(function(){ 
     chk_value.push($(this).val()); 
     });
     if(chk_value.length==0){
    	 alert("你还没有选择任何内容！");
     }else{
    	 $.ajax({
    		 type:"post",
    		 dataType:"json",
    		 async:false,
    		 url:"/comment/actionOfComment",
    		 data:{"ids":JSON.stringify(chk_value),"action":action},
    	     success:function(data)
    	     {
    	    	 doSearch();
    	     }
       });	 
     }
  
 } 

 
 function CheckAll()
 {
	 $("#headCheckBox").click(function(){		 
		   if(this.checked){
		   $("input[type='checkbox']").prop("checked",true);
		   }else{
		   $("input[type='checkbox']").removeAttr("checked",false);		   
		   }
		 });  
	  
	    $("input[type='checkbox']").click(function(){
		  var len=$("input[type='checkbox']").length;
		  var len1=$("input[type='checkbox']:checked").length;
        if(len==len1){
		    $("#headCheckBox").prop("checked",true)
		  }else{
		  $("#headCheckBox").removeAttr("checked",false)
		  }
	
		});
 }

 
 
 function detail(id)
 {
	 $.ajax({
		type:"post",
		dataType:"json",
		async:false,
		url:"/comment/showCommentDetail",
		data:{"id":id},
		success:function(data){
			$("#detailOfnickName").html(data.nickname); 
			$("#detailOfcontent").html(data.content); 
			$("#detailOfcommenttab").html(data.commenttab); 
			$("#detailOfrcontent").html(data.rcontent);
			$("#detailOfnod").html(data.nod); 
			$("#additionalComments").html(data.additionalComments);//追评 
			$("#time").html(data.createtime); 
			$("#canshu").html(data.color+","+data.size);//参数
			var html = "";
			for(var i=0;i<data.starlevel;i++){
				html+='<i class="iconfont icon-star-full"></i>';
				}
			for(var j=0;j<5-data.starlevel;j++)
				{
				html+='<i class="iconfont icon-star"></i>';
				}
			$("#detailOfstarts").html(html);
			if(data.picture!=null){
				
				var images=(data.picture).split(",");
				var picture="";
				for(var k=0;k<images.length;k++){
					picture+="<div class='pull-left item'><img src='"+images[k]+"' alt=''></div>";
				}
				$("#picture").html(picture);
			};
			if(data.additionalPicture!=null)
				{
				var additionalImages=(data.additionalPicture).split(",");
				var additionalPicture="";
				for(var h=0;h<additionalImages.length;h++){
					additionalPicture+="<div class='pull-left item'><img src='"+additionalImages[h]+"' alt=''></div>";
				}
				$("#additionalPicture").html(additionalPicture);
				}
		}
	 });
	 $("#popView").show();	
 }
 

                                                                        