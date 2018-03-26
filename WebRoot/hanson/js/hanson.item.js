//父节点单击事件
$(".left_td td:first-child").click(function(){
	console.log(this);
	var tr=$(this).parent().next();
	if(!tr.hasClass("left_td")){
		console.log(111);
		tr.toggle();
	}
});

//表单验证
$("#authoritiesInputForm").validate({
	submitHandler: function (form) {
	    form.submit();
	},
	rules:{
		name:{
			required:true
		},
		display_name:{
			required:true
		},
		sortno:{
			required:true,
			digits: true
		},
		authno:{
			required:true,
			digits: true
		}
	},
	messages:{
		name: {
	    	required: "请输入权限编码",
		},
		display_name: {
	    	required: "请输入权限名称",
		},
		sortno: {
	    	required: "请输入显示排序",
	    	digits: "请输入整数"
		},
		authno: {
	    	required: "请输入权限排序",
	    	digits: "请输入整数"
		}
	},
	errorClass: "help-inline",
	errorElement: "span",
	highlight:function(element, errorClass, validClass) {
		$(element).parents('.control-group').addClass('error');
	},
	unhighlight: function(element, errorClass, validClass) {
		$(element).parents('.control-group').removeClass('error');
		$(element).parents('.control-group').addClass('success');
	}
});

//新增保存和编辑保存
function check_form(){
	//判断是否全部通过验证
	var valid = $('#authoritiesInputForm').valid(); 
	if(!valid){
		return false;
	}
    var form_data = $('#authoritiesInputForm').serialize();
    var isEdit = $("#saveButton").attr("isEdit");
  
    //新增保存
    if(isEdit == '0'){
    	// 异步提交数据
        $.ajax({
    		url: "system/saveAuthorities",
    		data: form_data,
    		type: "POST",
    		dataType:"json",
    		success:function(data){
            	if(data != null && data.errorCode == "000000"){
            		//需要重新加载页面
            		$("#content").load("system/item.do");
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
    }
    
    //编辑保存
    if(isEdit == '1'){
    	// 异步提交数据
        $.ajax({
    		url: "system/updateAuthorities",
    		data: form_data,
    		type: "POST",
    		dataType:"json",
    		beforeSend:function(){
    		//提交之前执行，可用于禁用按钮，避免重复点击
    			return true;
    		},
    		success:function(data){
    			//重新加载表格
    			$("#content").load("system/item.do");
		    	toastr.success('保存成功');
		    	$('#myModal').modal('hide');
		    	return true;
    		},
    		error:function(){
    			toastr.error("网络错误");
    		    return false;
    		},
    		complete:function(){
    		   	//不管是否成功都执行
    		}
    	});
    }
    
	//执行失败
    return false;
}

//录入初始化
$(".add_btn").bind("click",function(){
	//清空表单元素
	$("#authoritiesInputForm").find('input,textarea,select').val("");
	//打开模态框
	$('#myModal').modal('show');
	
});


//编辑初始化
$(".edit_btn").bind("click",function(){
	//获取id
	var id = $("tr.active").attr("itemid");
	//获取表单
	var $formInput = $("#authoritiesInputForm");
	//设置编辑状态
	$("#saveButton").attr("isEdit", "1");
	
	$.ajax({
        url: "system/findAuthoritiesById",
        data:{"id":id},
        type: "POST",
        dataType:"json",
        success:function(data){
		    if(data != null && data.errorCode == "000000"){
        		var item = data.authorities;
        		$formInput.find("#id").val(item.id);
        		$formInput.find("#name").val(item.name);
        		$formInput.find("#display_name").val(item.display_name);
        		$formInput.find("#parentid").val(item.parentid);
        		$formInput.find("#authtype").val(item.authtype);
        		$formInput.find("#request").val(item.request);
        		$formInput.find("#sortno").val(item.sortno);
        		$formInput.find("#authno").val(item.authno);
        		$formInput.find("#iconcls").val(item.iconcls);
        		$formInput.find("#remark").val(item.remark);
        	}else{
        		toastr.warning(data.message);
        	}
        },
        error:function(e){
        	toastr.error('网络错误');
        },
        complete:function(){
        }
    });
	
	//打开模态框
	$('#myModal').modal('show');
});

//绑定-查看按钮
$(".make_btn").bind("click",function(){
	//设置标题
	$("#myModalLabel").text("查看权限");
	//移除保存按钮
	$("#saveButton").remove();
	//设置表单元素不可编辑
	$("#authoritiesInputForm").find('input,textarea,select').prop('readonly',true);
	$("#authoritiesInputForm").find('select').attr("disabled","disabled");
	//判断是否有选中项
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择权限");
		return false;
	}
	//获取ID
	var id = $("tr.active").attr("itemid");
	//获取表单
	var $formInput = $("#authoritiesInputForm");
	
	$.ajax({
        url: "system/findAuthoritiesById",
        data:{"id":id},
        type: "POST",
        dataType:"json",
        success:function(data){
		    if(data != null && data.errorCode == "000000"){
		    	var item = data.authorities;
        		$formInput.find("#id").val(item.id);
        		$formInput.find("#name").val(item.name);
        		$formInput.find("#display_name").val(item.display_name);
        		$formInput.find("#parentid").val(item.parentid);
        		$formInput.find("#authtype").val(item.authtype);
        		$formInput.find("#request").val(item.request);
        		$formInput.find("#sortno").val(item.sortno);
        		$formInput.find("#authno").val(item.authno);
        		$formInput.find("#iconcls").val(item.iconcls);
        		$formInput.find("#remark").val(item.remark);
        	}else{
        		toastr.warning(data.message);
        	}
        },
        error:function(e){
        	toastr.error('网络错误');
        }
    });
	//打开模态框
	$('#myModal').modal('show');
});

//绑定-删除按钮
$(".delete_btn").bind("click",function(){
	//获取id
	var id = $("tr.active").attr("itemid");
	//判断是否选择一项
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择角色");
		return false;
	}
	
	 if(confirm("是否确认删除")){
		 $.ajax({
		        url: "system/deleteAuthorities",
		        data:{"id":id},
		        type: "POST",
		        dataType:"json",
		        success:function(data){
				    if(data != null && data.errorCode == "000000"){
				    	//重新加载表格
				    	$("#content").load("system/item.do");
				    	toastr.success('删除成功');
				    	$('#myModal').modal('hide');
				    	return true;
		        	}else{
		        		toastr.warning(data.message);
		        	}
		        },
		        error:function(e){
		        	toastr.error('网络错误');
		        }
		    }); 
	 }else{
		 return false;
	 }
});


//选中效果
$('#cs_table').on("click",".left_td",function(){
	console.log(this);
	$(this).addClass("active").siblings(".left_td").removeClass("active");
	if($(this).hasClass("active")){
		$('#cs_table div table tr').removeClass("active");
	}
});
//二级目录选中
$('#cs_table div table').on("click",'tr',function(){
	$(this).addClass("active").siblings().removeClass("active");
	if($(this).hasClass("active")){
		$('#cs_table .left_td').removeClass("active");
	}
});


//$("tr.active").attr("itemid");