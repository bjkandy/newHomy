//初始化角色列表
initData("");
//初始化权限列表
//initAuthorities("");

function initData(t){
	$.ajax({
	   type: "POST",
	   url: "system/rolesList",
	   data: t,
	   success: function(msg){
		    var obj = eval(msg);
		    var data = [];
			    for(var i=0;i<obj.length;i++){
						data[i] = {
						"id":obj[i]["id"],
						"name":obj[i]["name"]
						};
			    }
			    var cs = new table({
			        "tableId":"cs_table",    //必须
			        "headers":["id", "名称"],   //必须
			        "data":data,        //必须
			        "displayNum": 20,    //必须   默认 10
			        "groupDataNum":9    //可选    默认 10
				});	
		   }
	});
}


function initAuthorities(t){
	$.ajax({
	   type: "POST",
	   url: "system/authoritiesList",
	   data: t,
	   success: function(msg){
		    var obj = eval(msg);
		    var data = [];
			    for(var i=0;i<obj.length;i++){
						data[i] = {
						"id":obj[i]["id"],
						"name":obj[i]["name"]
						};
			    }
			    var cs = new table({
			        "tableId":"cs_table",    //必须
			        "headers":["id", "名称"],   //必须
			        "data":data,        //必须
			        "displayNum": 20,    //必须   默认 10
			        "groupDataNum":9    //可选    默认 10
				});	
		   }
	});
}

//选中效果
$('#cs_table').on("click","td",function(){
	console.log(this);
	$(this).parent().addClass("active").siblings().removeClass("active");
});


//新增保存和编辑保存
function check_form(){
	//判断是否全部通过验证
	var valid = $('#rolesInputForm').valid(); 
	if(!valid){
		return false;
	}
	
    var form_data = $('#rolesInputForm').serialize();
    var isEdit = $("#saveButton").attr("isEdit");
    
    //新增保存
    if(isEdit == '0'){
    	// 异步提交数据
        $.ajax({
    		url: "system/saveRoles",
    		data: form_data,
    		type: "POST",
    		dataType:"json",
    		success:function(data){
            	if(data != null && data.errorCode == "000000"){
    		    	//重新加载表格
            		initData("");
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
    		}
    	});
    }
    
    //编辑保存
    if(isEdit == '1'){
    	// 异步提交数据
        $.ajax({
    		url: "system/updateRoles",
    		data: form_data,
    		type: "POST",
    		dataType:"json",
    		beforeSend:function(){
    		//提交之前执行，可用于禁用按钮，避免重复点击
    			return true;
    		},
    		success:function(data){
    			//重新加载表格
    			initData("");
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


//绑定-编辑按钮
$(".edit_btn").bind("click",function(){
	//页面处理
	$("#myModalLabel").text("编辑角色");
	$("#saveButton").attr("isEdit", "1");
	
	//判断是否选择一项
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择企业");
		return false;
	}
	var id = $('#cs_table').find("tr.active").find("td:first").text();
	var $formInput = $("#rolesInputForm");
	$formInput.find("#id").val(id);
	//获取数据
	$.ajax({
        url: "system/findRolesById",
        data:{"id":id},
        type: "POST",
        dataType:"json",
        beforeSend:function(){
            return true;
        },
        success:function(data){
		    if(data != null && data.errorCode == "000000"){
        		var item = data.roles;
        		$formInput.find("#id").val(item.id);
        		$formInput.find("#name").val(item.name);
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
	$("#myModalLabel").text("查看角色");
	$("#saveButton").remove();
	$("#rolesInputForm").find('input,textarea,select').prop('readonly',true);
	
	//判断是否选择一项
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择企业");
		return false;
	}
	var id = $('#cs_table').find("tr.active").find("td:first").text();
	var $formInput = $("#rolesInputForm");
	$formInput.find("#id").val(id);
	//获取数据
	$.ajax({
        url: "system/findRolesById",
        data:{"id":id},
        type: "POST",
        dataType:"json",
        success:function(data){
		    if(data != null && data.errorCode == "000000"){
        		var item = data.roles;
        		$formInput.find("#name").val(item.name);
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
	var id = $('#cs_table').find("tr.active").find("td:first").text();
	//判断是否选择一项
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择角色");
		return false;
	}
	
	 if(confirm("是否确认删除")){
		 $.ajax({
		        url: "system/delteRolesById",
		        data:{"id":id},
		        type: "POST",
		        dataType:"json",
		        success:function(data){
				    if(data != null && data.errorCode == "000000"){
				    	//重新加载表格
		    			initData("");
				    	toastr.success('保存成功');
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



$(".setAuthorities_btn").bind("click",function(){
	//获取id
	var id = $('#cs_table').find("tr.active").find("td:first").text();
	//设置id
	$("#rolesAuthoritiesForm").find("#id").val(id);
	//清空权限列表
	$("#functionalAuthority input").attr("checked",false);
	
	//判断是否选择一项
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择角色");
		return false;
	}
	
	//初始化权限展示页
	$.ajax({
        url: "system/getAuthorities",
        data:{"id":id},
        type: "POST",
        dataType:"json",
        success:function(data){
        	var authorities = data.authoritiesList;
        	for(var i = 0; i < authorities.length; i++){
        		$("#functionalAuthority input[type='checkbox']").each(function(){
        			var v = $(this).val();
        			if(v == authorities[i].id){
        				$(this).attr("checked","true"); 
        			}
            	});
        	}
        	
        },
        error:function(e){
        	toastr.error('网络错误');
        }
    }); 
	
	$('#myQxModal').modal('show');
});



//重置
function reset(){
	$("#name_s").val("");
}

//查询
function querybytj(){
	var form_data = $('#rolesSearchForm').serialize();
	initData(form_data);
}

//全选  重置  保存操作
$(".allChoose").click(function(){
	$("#functionalAuthority input").attr("checked",true);
	return false;
});
$(".noChoose").click(function(){
	$("#functionalAuthority input").attr("checked",false);
	return false;
});
$(".saveChoose").click(function(){
	//获取选中的值
	var str = "";
	$("#functionalAuthority input[type='checkbox']:checked").each(function(){
		str += "," + $(this).val();
	});
	
	//获取角色ID
	var id = $("#rolesAuthoritiesForm").find("#id").val();
	//ajax提交保存选中项
	$.ajax({
        url: "system/saveRolesAuthorities",
        data:{"id":id, "authorityIds":str},
        type: "POST",
        dataType:"json",
        success:function(data){
        	toastr.success('保存成功');
	    	$('#myQxModal').modal('hide');
	    	return true;
        },
        error:function(e){
        	toastr.error('网络错误');
        }
    }); 
	
	return false;
});


//二级全选操作		
$("#functionalAuthority>li").on("click","div>input",function(){
	var $this=$(this);
	console.log($this.attr("checked")=="checked");
	if($this.attr("checked")=="checked"){
		$this.parent().siblings("ul").find("input").attr("checked",true);
	}else{
		$this.parent().siblings("ul").find("input").attr("checked",false);
	}
})
//折叠三级菜单
$("#functionalAuthority>li").on("click","div>span",function(){
	var $this=$(this);
	$this.parent().siblings("ul").toggle();
})
//三级菜单选中操作
$("#functionalAuthority>li>ul input").change(function(){
	var $this=$(this);
	if($this.attr("checked")==undefined){
		//$this.closest("ul").prev("div").find("input").attr("checked",false);
	}else{
		var $input=$this.closest("ul").find("input");
		var num=0;
		$.each($input, function(i,v) {
			if($(v).attr("checked")=="checked"){
				num++;
			}
		});
		if(num==$input.length){
			console.log("进来了");
			$this.closest("ul").prev("div").find("input").attr("checked",true);
		}
	}
})

//表单验证
$("#rolesInputForm").validate({
	submitHandler: function (form) {
	    form.submit();
	},
	rules:{
		name:{
			required:true
		}
	},
	messages:{
		name: {
	    	required: "请输入名称",
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



