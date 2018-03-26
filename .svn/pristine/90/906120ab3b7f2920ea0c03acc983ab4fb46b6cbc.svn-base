//页面初始化
//query("");
var headers = [{title:"ID",code:"id"},
             {title:"企业ID",code:"qyid"},
			 {title:"登录名",code:"login_name"},
			 {title:"名称",code:"name"},
			 {title:"邮箱",code:"email"},
			 {title:"角色项",code:"roleName"}];

var cs = new table({
	"cache":true,    //必须
	"url":"system/usersList",
    "tableId":"cs_table",    //必须
    "headers":headers,   //必须
    "currentPageNum":1,
    "displayNum": 20,    //必须   默认 10
    "groupDataNum":9    //可选    默认 10
});

//查询
function querybytj(){
	cs.paramData = $("#usersSearchForm").serializeArray();
	cs.init(0, cs.displayNum);
}

//重置
function reset(){
	 $(':input','#usersSearchForm')  
	 .not(':button, :submit, :reset, :hidden')  
	 .val('')  
	 .removeAttr('checked')  
	 .removeAttr('selected');
}


//选中效果
$('#cs_table').on("click","td",function(){
	console.log(this);
	$(this).parent().addClass("active").siblings().removeClass("active");
})

// AJAX提交表单
function check_form(){
	//判断是否全部通过验证
	var valid = $('#usersInputForm').valid(); 
	if(!valid){
		return false;
	}
	
 	var form_url = $('#usersInputForm').attr("action");
    var form_data = $('#usersInputForm').serialize();
    
    var isEdit = $("#saveButton").attr("isEdit");
    if(isEdit == '0'){
    	// 异步提交数据
        $.ajax({
    		url: form_url,
    		data: form_data,
    		type: "POST",
    		dataType:"json",
    		beforeSend:function(){
    		//提交之前执行，可用于禁用按钮，避免重复点击
    			return true;
    		},
    		success:function(data){
    			
            	if(data != null && data.errorCode == "000000"){
    		    	//重新加载表格
            		querybytj();
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
    if(isEdit == '1'){
    	// 异步提交数据
        $.ajax({
    		url: "system/updateUsers",
    		data: form_data,
    		type: "POST",
    		dataType:"json",
    		beforeSend:function(){
    		//提交之前执行，可用于禁用按钮，避免重复点击
    			return true;
    		},
    		success:function(data){
    			//重新加载表格
    			querybytj();
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
    
    if(isEdit == '2'){
    	// 异步提交数据
        $.ajax({
    		url: "system/updatePassword",
    		data: form_data,
    		type: "POST",
    		dataType:"json",
    		beforeSend:function(){
    		//提交之前执行，可用于禁用按钮，避免重复点击
    			return true;
    		},
    		success:function(data){
    			//重新加载表格
    			querybytj();
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
	
	
//表单验证
$("#usersInputForm").validate({
	submitHandler: function (form) {
	    form.submit();
	},
	rules:{
		login_name:{
			required:true
		},
		password:{
			required:true
		},
		passwordR:{
			required:true,
			equalTo: "#password"
		},
		name:{
			required:true
		},
		usertype:{
			required:true
		}
	},
	messages:{
		login_name: {
	    	required: "请输入登录名",
		},
		password: {
	    	required: "请输入密码",
		},
		passwordR: {
	    	required: "请输入确认密码",
	    	 equalTo: "两次密码不一致"
		},
		name:{
			required: "请输入名称",
		},
		usertype:{
			required: "请选择角色",
		},
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

//绑定-编辑按钮
$(".edit_btn").bind("click",function(){
	//页面处理
	$("#myModalLabel").text("编辑用户");
	$("#password_div").hide();
	$("#passwordR_div").hide();
	$("#saveButton").attr("isEdit", "1");
	
	//判断是否选择一项
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择企业");
		return false;
	}
	var id = $('#cs_table').find("tr.active").find("td:first").text();
	var $formInput = $("#usersInputForm");
	$formInput.find("#id").val(id);
	//获取数据
	$.ajax({
        url: "system/findUsersByCondition",
        data:{"id":id},
        type: "POST",
        dataType:"json",
        beforeSend:function(){
            return true;
        },
        success:function(data){
		    if(data != null && data.errorCode == "000000"){
        		var item = data.users;
        		$formInput.find("#id").val(item[0].id);
        		$formInput.find("#login_name").val(item[0].login_name);
        		$formInput.find("#name").val(item[0].name);
        		$formInput.find("#email").val(item[0].email);
        		$formInput.find("#qyid").val(item[0].qyid);
        		$formInput.find("#usertype").val(item[0].usertype);
        		
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
	//页面处理
	$("#myModalLabel").text("编辑用户");
	$("#password_div").hide();
	$("#passwordR_div").hide();
	$("#saveButton").remove();
	$("#usersInputForm").find('input,textarea,select').prop('readonly',true);
	
	//判断是否选择一项
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择企业");
		return false;
	}
	var id = $('#cs_table').find("tr.active").find("td:first").text();
	var $formInput = $("#usersInputForm");
	$formInput.find("#id").val(id);
	//获取数据
	$.ajax({
        url: "system/findUsersByCondition",
        data:{"id":id},
        type: "POST",
        dataType:"json",
        beforeSend:function(){
            return true;
        },
        success:function(data){
		    if(data != null && data.errorCode == "000000"){
        		var item = data.users;
        		$formInput.find("#id").val(item[0].id);
        		$formInput.find("#login_name").val(item[0].login_name);
        		$formInput.find("#name").val(item[0].name);
        		$formInput.find("#email").val(item[0].email);
        		$formInput.find("#qyid").val(item[0].qyid);
        		$formInput.find("#usertype").val(item[0].usertype);
        		
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

//绑定-重置密码按钮
$(".repassword_btn").bind("click",function(){
	//页面处理
	$("#myModalLabel").text("重置密码");
	$("#name_div").remove();
	$("#email_div").remove();
	$("#qyid_div").remove();
	$("#usertype_div").remove();
	$("#login_name").prop('readonly',true);
	$("#password_div").show();
	$("#passwordR_div").show();
	$("#saveButton").attr("isEdit", "2");
	
	
	//判断是否选择一项
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择用户");
		return false;
	}
	
	var id = $('#cs_table').find("tr.active").find("td:first").text();
	var $formInput = $("#usersInputForm");
	$formInput.find("#id").val(id);
	
	//获取数据
	$.ajax({
        url: "system/findUsersByCondition",
        data:{"id":id},
        type: "POST",
        dataType:"json",
        beforeSend:function(){
            return true;
        },
        success:function(data){
		    if(data != null && data.errorCode == "000000"){
        		var item = data.users;
        		$formInput.find("#id").val(item[0].id);
        		$formInput.find("#login_name").val(item[0].login_name);
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

