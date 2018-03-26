/**
 * 企业管理页面功能
 */
//初始化录入页面元素
$("#companytype").select2({width: "200px",placeholder: "请选择"});
$("#msgtype").select2({width: "200px",});
$("#parentid").select2({width: "200px",placeholder: "请选择"});
//隐藏授权指派
$("#assignmentDiv").hide();
/*//隐藏商户级别
$("#merchantlevelDiv").hide();*/
//隐藏指派生产中心
$("#setCenterDiv").hide();
//隐藏其他信息
$("#otherInfo").hide();
//消息与默认
$("#msgtype").select2("val", 1);//TODO

//点击表格添加active

//设置表头
var headers = [
 {title:"商户ID",code:"id"},
 {title:"公司名称",code:"companyname"},
 {title:"登录名",code:"code"},
 {title:"省份",code:"provincename"},
 {title:"市/区",code:"cityname"},
 {title:"区/县",code:"areaname"},
 {title:"公司地址",code:"address"},
 {title:"公司邮箱",code:"email"},
 {title:"手机号",code:"mobile"},
 {title:"联系电话",code:"phone"}
 ];

//初始化表格	 
var cs = new table({
    "cache":true,    //必须
    "url":"company/jsonlist", 
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
//绑定-编辑按钮
$(".edit_btn").bind("click",function(){
	//判断是否选择一项
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择企业");
		return false;
	}
	//添加商户类型
	$("#companytype").select2("destroy");
	$("#companytype").append("<option value='20'>推广中心</option>").
					  append("<option value='30'>推广员</option>");
	
	$("#myModalLabel").text("编辑");
	var id = $('#cs_table').find("tr.active").attr("data-id");
	var $formInput = $("#companyInputForm");
	$formInput.append('<input type="hidden" id="id" name="id" value="'+id+'"/>');
	
	//获取数据
	$.ajax({
        url: "company/findUniqueById",
        data:{"id":id},
        type: "POST",
        dataType:"json",
        beforeSend:function(){
            // $("#tip").html("<span style='color:blue'>正在处理...</span>");
            return true;
        },
        success:function(data){
        	if(data != null && data.errorCode == "000000"){
        		var item = data.company;
        		$formInput.find("#companyname").val(item.companyname);
        		$formInput.find("#oldcode").val(item.code);
        		$formInput.find("#code").val(item.code);
        		$formInput.find("#email").val(item.email);
        		$formInput.find("#latitude").val(item.latitude);
        		$formInput.find("#longitude").val(item.longitude);
        		//赋值后禁用
        		//alert(item.companytype);
        		$("#companytype").select2("destroy");
        		$("#companytype").val(item.companytype);
        		
        		$formInput.find("#website").val(item.website);
        		$formInput.find("#myMobile").val(item.mobile);
        		$formInput.find("#phone").val(item.phone);
        		$formInput.find("#storepath").val(item.storepath);
        		
        		//清空下拉框
    			$("#parentid option:not(:first)").remove();//保留第一项，其他清空
        		
        		//初始化编辑页面所属上级
        		if(item.companytype == 40){
        			
        			
        			//经销商
        			$.ajax({
        				url: "company/findParent",
        				data: {"id":item.companytype},
        				type: "POST",
        				dataType:"json",
        				success:function(data){
        					//获取返回结果
        					var companyList = data.companyList;
        					//设置所属上级
        					for(var i = 0; i < companyList.length; i++){
        						$("#parentid").append("<option value='"+companyList[i].id+"'>"+companyList[i].companyname+"</option>");  
        					}
        					$("#parentid").select2("val",item.distributid);//赋值
        				}
        			});
        			
        		}else if(item.companytype == 10 || item.companytype == 50){
        			if(item.distributid == HUIMEI_ID){
        				$("#parentid").append("<option value='"+HUIMEI_ID+"'>总号</option>");
        				$("#parentid").select2("destroy");
            			$("#parentid").val(HUIMEI_ID);//TODO
    					
            			//授权指派
            			$(":radio[name='isassign'][value='" + item.isassign + "']").prop("checked", "checked");
                		$("#assignmentDiv").show();
        			}
    				//门店或者生产中心
        			$.ajax({
        				url: "company/findParent",
        				data: {"id":item.companytype},
        				type: "POST",
        				dataType:"json",
        				success:function(data){
        					//获取返回结果
        					var companyList = data.companyList;
        					//设置所属上级
        					for(var i = 0; i < companyList.length; i++){
        						$("#parentid").append("<option value='"+companyList[i].id+"'>"+companyList[i].companyname+"</option>");  
        					}
        					$("#parentid").select2("val",item.distributid);//赋值
        				}
        			});
        			
        			$("#assignmentDiv").hide();
        			
        			
        		}else if(item.companytype == 20){
        			//推广中心
        			if(item.distributid == 0 && item.merchantid == 0 && item.prometerid == 0){
        				$("#parentid").append("<option value='"+HUIMEI_ID+"'>总号</option>");
            			$("#parentid").select2("val", HUIMEI_ID);//TODO
            			//显示指派生产中心
            			$("#setCenterDiv").show();
        			}else{
        				$.ajax({
            				url: "company/findParent",
            				data: {"id":item.companytype},
            				type: "POST",
            				dataType:"json",
            				success:function(data){
            					//获取返回结果
            					var companyList = data.companyList;
            					//设置所属上级
            					for(var i = 0; i < companyList.length; i++){
            						$("#parentid").append("<option value='"+companyList[i].id+"'>"+companyList[i].companyname+"</option>");  
            					}
            					$("#parentid").select2("val", item.merchantid);//赋值
            				}
            			});
        			}
        		}else if(item.companytype == 30){
        			
        			$.ajax({
        				url: "company/findParent",
        				data: {"id":item.companytype},
        				type: "POST",
        				dataType:"json",
        				success:function(data){
        					//获取返回结果
        					var companyList = data.companyList;
        					//设置所属上级
        					for(var i = 0; i < companyList.length; i++){
        						$("#parentid").append("<option value='"+companyList[i].id+"'>"+companyList[i].companyname+"</option>");  
        					}
        					//给所属上级赋值后禁用
        					$("#parentid").select2("val", item.merchantid);
        				}
        			});
        		}
        		initProvicialSelect(item.province, item.city, item.area);
        		$formInput.find("#address").val(item.address);
        		$formInput.find("#logo").val(item.logo);
        		$("#msgtype").select2("val", item.msgtype);//TODO
        		$formInput.find("#sdesc").val(item.sdesc);
        		

        		
        		//门店和生产中心时显示
        		if(item.companytype == 10 || item.companytype == 50){
        			//商户级别
            		$("#merchantlevel").select2("val", item.merchantlevel);//TODO
            		/*$("#merchantlevelDiv").show();*/
        		}else{
        			/*$("#merchantlevelDiv").hide();*/
        		}
        		
        		//商户类型推广中心且所属上级为汇美时显示
        		if(item.companytype == 20 && $("#parentid").select2("val") == HUIMEI_ID){
        			$("#designMerchant").select2("val", item.merchantid);//TODO
        			$("#setCenterDiv").show();
        		}else{
        			$("#setCenterDiv").hide();
        		}
        		
        		
        		
        		$("#tuiguangurl").text(item.tuiguangurl);
        		
        		//登陆名不可编辑
        		$("#code").attr("disabled", true);
        		
        		//显示其他信息并赋值
        		$("#otherInfo").show();
        		$("#cityPostage").text(item.cityPostage);
        		$("#provincePostage").text(item.provincePostage);
        		$("#nationPostage").text(item.nationPostage);
        		$("#qrcodepath").attr("src","http://hmzf-img.homy.cc/images/"+item.qrcodepath)
        		//$("#qrcodepath").attr("src","http://xgpic.homy.cc/"+item.qrcodepath)
        		//$("#qrcodepath").attr("src","http://d-img.homy.cc/"+item.qrcodepath);
        		//$("#qrcodepath").attr("src","http://weixin.yb-news.com/"+item.qrcodepath);
        	}else{
        		toastr.warning(data.message);
//        		console.log(data.message);
        	}
        },
        error:function(e){
//        	console.log(e);
        	toastr.error('网络错误');
        },
        complete:function(){
            // $('#tips').hide();
        }
    });
	
	//打开模态框
	$('#myModal').modal('show');
});

//绑定-查看费用按钮
$(".make_btn").bind("click",function(){
	//判断是否选择一项
//	console.log($('#cs_table').find("tr.active"));
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择企业");
		return false;
	}
	var id = $('#cs_table').find("tr.active").attr("data-id");
	var $formInput = $("#companyMakeForm");
	$formInput.find(".productid").val(id);
	$maketable = $formInput.find("#companyMaketable");
	//清理表格
	$maketable.html("");
	//获取数据
	$.ajax({
        url: "company/make",
        data:{"id":id},
        type: "POST",
        dataType:"json",
        beforeSend:function(){
            return true;
        },
        success:function(data){
        	if(data != null && data.errorCode == "000000"){

        		if( data.specvalues ==null || data.specvalues.length <=0){
        			$maketable.html('<div><h1 style="color: red;">该用户没有生产权限</h1></div>');
        		}else{
//            		var specnames = data.specnames;
            		var specnames = [{id:1, name:"尺寸", code:"size"},{id:2, name:"材料", code:"specinfo"}];
            		
        			//创建表头
        			var  $thead = $("<thead>"),
                    $tr = $("<tr>"),
                    $th;
        			for(var i=0;i<specnames.length;i++){
                        $th = $("<th data-type='"+specnames[i].id+"'>").html(specnames[i].name);
                        $th.appendTo($tr);
                    }
        			
        			$th = $('<th class="notspacname" data-type="oldprice">生产费用</th>');
        			$th.appendTo($tr);
        			
                    $tr.appendTo($thead);
                    $thead.appendTo($maketable);
                    
                    var specvalues = data.specvalues;
                    //创建内容
	                var $tbody = $("<tbody>"),
	                $tr,
	                $td;
	                // 循环创建行
	                for(var i=0; i< specvalues.length;i++){
	                    $tr = $("<tr >").appendTo($tbody);
	                    $input = $('<input name="specid" type="hidden" value="'+specvalues[i].id+'" />');
	                    $input.appendTo($tr);
	                    // 循环创建列  取得对象中的键
	                    for(var key in specnames){
	                    	var code = specnames[key].code;
	                        $td = $("<td>").html(specvalues[i][code]).appendTo($tr);
	                    }
	                    $("<td>").html('<input type="text" size="10" value="'+specvalues[i].productioncost+'" name="oldprices"/>').appendTo($tr);
	                }
	                $maketable.append($tbody);
        		}
        	}else{
//        		console.log(data.message);
        	}
        },
        error:function(){
        	toastr.error('网络错误');
        },
        complete:function(){
            // $('#tips').hide();
        }
    });
	
	//打开模态框
	$('#companyMakeModal').modal('show');
});

//绑定-消息语按钮
$(".messages_btn").bind("click",function(){
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择企业");
		return false;
	}
	var id = $('#cs_table').find("tr.active").attr("data-id");
	$.ajax({
    	url: "company/updateMsgType",
    	type:"POST",
    	dataType:"json",
    	data:{"id": id},
    	success:function(result){
    		if(result!=null && result.errorCode == "000000"){
    			toastr.success(result.msg);
    		}else{
    			toastr.warning(result.msg);
    		}
    	}
    });
});

//绑定-消息语按钮
$(".clearPrarentid_btn").bind("click",function(){
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择企业");
		return false;
	}
	var id = $('#cs_table').find("tr.active").attr("data-id");
	$.ajax({
    	url: "company/clearParentid",
    	type:"POST",
    	dataType:"json",
    	data:{"id": id},
    	success:function(result){
    		if(result!=null && result.errorCode == "000000"){
    			toastr.success("清除成功");
    		}else{
    			toastr.warning(result.msg);
    		}
    	}
    });
});

//绑定-二维码按钮
$(".ercode_btn").bind("click",function(){
	//判断是否选择一项
//	console.log($('#cs_table').find("tr.active"));
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择企业");
		return false;
	}
	var id = $('#cs_table').find("tr.active").attr("data-id");
	var $formBody = $("#companyQrcodeModal .modal-body");
//	console.log($formBody);
	//获取数据
	$.ajax({
        url: "company/createQrcode",
        data:{"id":id},
        type: "POST",
        dataType:"json",
        beforeSend:function(){
            return true;
        },
        success:function(data){
        	$formBody.find("h3").html(data.msg);
        	$("#images").attr("src","http://hmzf-img.homy.cc/images/"+ data.url);
    		//$("#images").attr("src","http://xgpic.homy.cc/"+ data.url);
    		//$("#images").attr("src","http://weixin.yb-news.com/"+ data.url);
        },
        error:function(){
        	toastr.error('网络错误');
        },
        complete:function(){
            // $('#tips').hide();
        }
    });
	
	//打开模态框
	$('#companyQrcodeModal').modal('show');
});


//对应业务的返佣显示隐藏
$("#editSHModel").on("change","input[type='checkbox']",function(){
	if($(this).is(":checked")){
		$(this).val(1);
		$('.'+$(this).attr("id")).parent().show();
	}else{
		$(this).val(0);
		$('.'+$(this).attr("id")).parent().hide();
	}
});
//选择总店显示隐藏
$("#editSHModel").on("change","input[type='radio']",function(){
	if($(this).val() == 1){
		$("#headoffice").val($(this).attr("data-id"));
		$("#headoffice").parent().parent().hide();
		$(".MDlist").hide();
	}else{
		$("#headoffice").val('');
		$("#headoffice_name").val('');
		$("#headoffice").parent().parent().show();
	}
});
//选择门店列表
$("#headoffice_name").keyup(function(){
	$.ajax({
		url: "company/findheadStore",
		data: {"name":$("#headoffice_name").val()},
		type: "POST",
		dataType:"json",
		success:function(data){
			console.log(data);
			var companyList = data.companyList;
			$(".MDlist").empty();
			$.each(data.companyList,function(i,v){
				$(".MDlist").append('<li MDid="'+v.id+'">'+v.companyname+'</li>');
			});
		}
	});
	$(".MDlist").show();
});
//点击设置总店
$(".MDlist").on("click","li",function(){
	console.log(this);
	$("#headoffice").val($(this).attr("MDid"));
	$("#headoffice_name").val($(this).html());
	$(this).parent().hide();
});
//vip预充值
$(".vipCharge").bind("click",function(){
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择企业");
		return false;
	}
	var id = $('#cs_table').find("tr.active").attr("data-id");
	var $formInput = $("#store_charge");
	$formInput.find("#id1").val(id);
	var $formBody = $("#vipstoreCharge .modal-body");
	
	$('#vipstoreCharge').modal('show');
});
//编辑商户权限
$(".editMerchantAuthority").bind("click",function(){
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择企业");
		return false;
	}
	var id = $('#cs_table').find("tr.active").attr("data-id");
	var $formInput = $("#companyauthority");
	$formInput.find("#id").val(id);
	var $formBody = $("#companyQrcodeModal .modal-body");
	
	$.ajax({
        url: "company/setstoreproduct",
        data:{"id":id},
        type: "POST",
        dataType:"json",
        beforeSend:function(){
            return true;
        },
        success:function(data){
          console.log(data);
          if(data != null && data.errorCode == "000000"){
    	    var item=data.company;
    	  
    	    $("#id").val(item.id);
    	    //商户权限
    	    $("#photoenable").val(item.photoenable).attr("checked" , item.photoenable == 1 ? true : false);
    	    $("#photoframeenable").val(item.photoframeenable).attr("checked" , item.photoframeenable == 1 ? true : false);
    	    $("#photoalbumenable").val(item.photoalbumenable).attr("checked" , item.photoalbumenable == 1 ? true : false);
    	    $("#outphotoenable").val(item.outphotoenable).attr("checked" , item.outphotoenable == 1 ? true : false);
    	    $("#memberenable").val(item.memberenable).attr("checked" , item.memberenable == 1 ? true : false);
    	    
    	    //根据商户权限判断返佣比例的显示隐藏
    	    item.photoenable == 1 ?  $("#photorate").parent().show() : $("#photorate").parent().hide();
    	    item.photoframeenable == 1 ?  $("#photoframerate").parent().show() : $("#photoframerate").parent().hide();
    	    item.photoalbumenable == 1 ?  $("#photoalbumrate").parent().show() : $("#photoalbumrate").parent().hide();
    	    item.outphotoenable == 1 ?  $("#outphotorate").parent().show() : $("#outphotorate").parent().hide();
    	    item.memberenable == 1 ?  $("#memberrate").parent().show() : $("#memberrate").parent().hide();
    	    
    	    //返佣比例
    	    $("#photorate").val(item.photorate*100);
    	    $("#photoframerate").val(item.photoframerate*100);
    	    $("#photoalbumrate").val(item.photoalbumrate*100);
    	    $("#outphotorate").val(item.outphotorate*100);
    	    $("#memberrate").val(item.memberrate*100);
    	    
    	    //生产权限
    	    $("#photoproduct").val(item.photoproduct).attr("checked" , item.photoproduct == 1 ? true : false);
    	    $("#photoframeproduct").val(item.photoframeproduct).attr("checked" , item.photoframeproduct == 1 ? true : false);
    	    $("#photoalbumproduct").val(item.photoalbumproduct).attr("checked" , item.photoalbumproduct == 1 ? true : false);
    	    
    	    $("#isHeadStore1").attr("data-id" , item.id);
    	    
    	    //设为总店
    	    if(item.headoffice != item.id){
    	    	$("#isHeadStore0").attr("checked" , true);
    	    	$("#headoffice").parent().parent().show();
    	    	$("#headoffice_name").val(item.companyname);
    	    }else{
    	    	$("#isHeadStore1").attr("checked" , true);
    	    	$("#headoffice").parent().parent().hide();
    	    }
    	   
    	   /* application/json;charset=UTF-8*/
    	    //预存标准
    	    $("#standardsum").val(item.standardsum/100);
          }
        },
        error:function(){
        	toastr.error('网络错误');
        },
        complete:function(){
        	
        }
    });
	//打开模态框
	$('#editSHModel').modal('show');
});
//生成推广url
$(".createurl").bind("click",function(){
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择企业");
		return false;
	}
	var id = $('#cs_table').find("tr.active").attr("data-id");
	$.ajax({
    	url: "company/creatcodeUrl",
    	type:"POST",
    	dataType:"json",
    	data:{"id": id},
    	success:function(result){
    		if(result!=null && result.errorCode == "000000"){
    			toastr.success("推广url生成成功");
    		}else{
    			toastr.warning(result.msg);
    		}
    	}
    });
});
//开通相册产品
$(".addphotoalbumfunction").bind("click",function(){
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择企业");
		return false;
	}
	$(".modeloading").show();
	var id = $('#cs_table').find("tr.active").attr("data-id");
	$.ajax({
    	url: "company/addphotoalbumfunction",
    	type:"POST",
    	dataType:"json",
    	data:{"id": id},
    	beforeSend:function(){
			//提交之前执行，可用于禁用按钮，避免重复点击
				return true;
			},
    	success:function(result){
    		$(".modeloading").hide();
    		if(result!=null && result.errorCode == "000000"){
    			toastr.success("相册开通成功");
    		}else if(result.errorCode=="111111"){
    			toastr.success("此相册功能尚未开通.");
    		}else if(result.errorCode=="22222"){
    			toastr.success("此功能已经开通,产品复制完成.");
    		}else{
    			toastr.warning(result.msg);
    		}
    	}
    });
});
//开通相框
$(".addphotoframefunction").bind("click",function(){
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择企业");
		return false;
	}
	$(".modeloading").show();
	var id = $('#cs_table').find("tr.active").attr("data-id");
	$.ajax({
    	url: "company/addphotoframefunction",
    	type:"POST",
    	dataType:"json",
    	data:{"id": id},
    	beforeSend:function(){
			//提交之前执行，可用于禁用按钮，避免重复点击
				return true;
			},
    	success:function(result){
    		$(".modeloading").hide();
    		if(result!=null && result.errorCode == "000000"){
    			toastr.success("相框开通成功");
    		}else if(result.errorCode=="111111"){
    			toastr.success("此相框功能尚未开通.");
    		}else if(result.errorCode=="22222"){
    			toastr.success("此功能已经开通,产品复制完成.");
    		}else{
    			toastr.warning(result.msg);
    		}
    	}
    });
});
$(".addphotofunction").bind("click",function(){
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择企业");
		return false;
	}
	var id = $('#cs_table').find("tr.active").attr("data-id");
	$.ajax({
    	url: "company/addphotofunction",
    	type:"POST",
    	dataType:"json",
    	data:{"id": id},
    	beforeSend:function(){
			//提交之前执行，可用于禁用按钮，避免重复点击
				return true;
			},
    	success:function(result){
    		
    		if(result!=null && result.errorCode == "000000"){
    			toastr.success("冲洗开通成功");
    		}else if(result.errorCode=="111111"){
    			toastr.success("此冲洗功能尚未开通.");
    		}else if(result.errorCode=="22222"){
    			toastr.success("此功能已经开通,产品复制完成.");
    		}else{
    			toastr.warning(result.msg);
    		}
    	}
    });
});
//开通约拍
$(".addyuepaifunction").bind("click",function(){
	if($('#cs_table').find("tr.active").length <=0){
		toastr.info("请选择企业");
		return false;
	}
	$(".modeloading").show();
	var id = $('#cs_table').find("tr.active").attr("data-id");
	$.ajax({
    	url: "company/addYuepaifunction",
    	type:"POST",
    	dataType:"json",
    	data:{"id": id},
    	beforeSend:function(){
			//提交之前执行，可用于禁用按钮，避免重复点击
				return true;
			},
    	success:function(result){
    		$(".modeloading").hide();
    		if(result!=null && result.errorCode == "000000"){
    			toastr.success("约拍开通成功");
    		}else if(result.errorCode=="111111"){
    			toastr.success("此约拍功能尚未开通.");
    		}else{
    			toastr.warning(result.msg);
    		}
    	}
    });
});
//绑定-搜索按钮
$(".search_btn").bind("click",function(){
	cs.paramData = $("#quick_search_from").serializeArray();
	cs.init(0, cs.displayNum);
});

//绑定-重置按钮
$(".resert_btn").bind("click",function(){
	$("#quick_search_from")[0].reset();
	cs.paramData = {};
	cs.init(0, cs.displayNum);
});


//不能输入中文
jQuery.validator.addMethod("english", function(value, element) {
    return !/[\u4E00-\u9FA5]/i.test(value);
}, "不能输入中文");

//验证固定电话
jQuery.validator.addMethod("isTel", function(value, element) {
    var length = value.length;
    var phone = /(^(\d{3,4}-)?\d{6,8}$)|(^(\d{3,4}-)?\d{6,8}(-\d{1,5})?$)|(\d{11})/;
    return this.optional(element) || (phone.test(value));
   }, "请填写正确的固定电话");//可以自定义默认提示信息



//表单验证
$("#companyInputForm").validate({
	submitHandler: function (form) {
	    form.submit();
	},
	rules:{
		companyname:{
			required:true
		},
		email:{
			email: true
		},
		code:{
			required:true,
			remote:{   //验证用户名是否存在
               type:"POST",
               url:"company/loginVerify",             //servlet
               dataType: "json", 
               data:{
            	  id:$("#oldcode").val(),
            	  name:function(){return $("#code").val();}
               } 
	        },
	        english:true
		},
		mobile:{
			digits: true,
			maxlength: 11,
			minlength:11
		}, 
		companytype:{
			required:true
		},
		parentid:{
			required:true
		},
		photorate:{
			range:[0,100]
		},
		photoframerate:{
			range:[0,100]
		},
		photoalbumrate:{
			range:[0,100]
		}
	},
	messages:{
		companyname: {
	    	required: "请输入企业名称",
		},
		code: {
	    	required: "请输入登录名",
	    	remote: "登录名已存在",
	    	english: "不能输入中文"
		},
		email: {
	    	email: "请输入正确的email地址"
		},
		mobile:{
			digits: "只能输入数字",
			maxlength: "手机号不能超过11位",
			minlength: "手机号不能少于11位"
		},
		companytype:{
			required: "请选择商户类型"
		},
		parentid:{
			required: "请选择所属上级"
		},
		photorate:{
			range: "输入值必须介于 0 和 100 之间"
		},
		photoframerate:{
			range: "输入值必须介于 0 和 100 之间"
		},
		photoalbumrate:{
			range: "输入值必须介于 0 和 100 之间"
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
function check_charge_form(){
	var chargemoney=$("#chargemoney").val()*100;
	var form_url = $('#store_charge').attr("action");
    var form_data = $('#store_charge').serialize();
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
			alert(data.errorCode);
//			console.log(data);
        	if(data != null && data.errorCode == "000000"){
		    	//重新加载表格
		    	toastr.success('保存成功');
		    	$('#vipstoreCharge').modal('hide');
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
    return false;
}
// AJAX提交表单
function check_form(){
	//验证商户类型
	var companytype = $("#companytype").select2("val");
	//验证所属上级
	/*var parentid = $("#parentid").select2("val");
	if(companytype == ''){
		alert("商户类型不能为空！");
		return false;
	}else if(parentid == ''){
		alert("所属上级不能为空！");
		return false;
	}*/
	//验证手机号和联系电话二选一
	var mobile = $("#mobile").val();
	var phone = $("#phone").val();
	if(mobile == '' && phone == ''){
		alert("手机号和联系电话至少填一项！");
		return false;
	}
	
	
	//判断是否全部通过验证
	var valid = $('#companyInputForm').valid(); 
	if(!valid){
		return false;
	}
	
 	var form_url = $('#companyInputForm').attr("action");
    var form_data = $('#companyInputForm').serialize();
    
    console.log(form_data);
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
};


function check_store_form(){
	var valid = $('#companyauthority').valid(); 
	if(!valid){
		return false;
	}
	var form_url = $('#companyauthority').attr("action");
    var form_data = $('#companyauthority').serialize();
    $.ajax({
		url: form_url,
		data: form_data,
		type: "post",
		dataType:"json",
		beforeSend:function(){
			//提交之前执行，可用于禁用按钮，避免重复点击
				return true;
			},
		success:function(){
			cs.init(0, cs.displayNum);
	    	toastr.success('保存成功');
	    	$('#editSHModel').modal('hide');
			 return true;
		},
		error:function(){
			toastr.error("网络错误....");
		    return false;
		},
		complete:function(){
		}
	});
    return false;
};
//AJAX提交表单
function check_Make_form(){
	//判断是否全部通过验证
	var valid = $('#companyMakeForm').valid(); 
	if(!valid){
		return false;
	}
	
 	var form_url = $('#companyMakeForm').attr("action");
    var form_data = $('#companyMakeForm').serialize();
    alert(form_url);
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
//			console.log(data);
        	if(data != null && data.errorCode == "000000"){
		    	//重新加载表格
		    	cs.init(0, cs.displayNum);
		    	toastr.success('保存成功');
		    	$('#companyMakeModal').modal('hide');
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

$('#myModal').on('hide.bs.modal', function () {
//	console.log(2);
	$("#companyInputForm")[0].reset();
	$("#companytype").attr("disabled", false);
	$("#companytype").val("");//赋值
	$("#companytype").select2({width: "200px",placeholder: "请选择"});
	$("#parentid").val("");//赋值
	$("#parentid").select2({width: "200px",placeholder: "请选择"});
	$("#msgtype").select2("val", 1);//TODO
	clearProvicialSelect();
	$("#tuiguangurl").text("");
	//隐藏其他信息
	$("#otherInfo").hide();
	//微传默认选中
	$("#photoenable").attr("checked", true);
	//去掉商户类型中的推广中心和推广员
	$("#companytype").select2("destroy");
	$("#companytype option[value='20']").remove(); 
	$("#companytype option[value='30']").remove(); 
	$("#myModalLabel").text("录入");
	//登录名变为可编辑
	$("#code").attr("disabled", false);
	$("#companyInputForm").find("#id").val("");
});

//关闭编辑页面时重置表单
$('#companyQrcodeModal').on('hidden.bs.modal', function () {
	var $formBody = $("#companyQrcodeModal .modal-body");
	$formBody.find("h3").html("");
	$formBody.find("image").attr("src","");
});

function searchSelect_company(flay, cid){
	if(flay){
		var parentid = $("#parentid").val();
		
		$("#parentid").select2({
	        ajax: {
	            url: "company/findCompany",
	            dataType: 'json',
	            type: "POST",
	            delay: 250,
	            data: function (term, page) {
	                return {
	                    q: term, // search term
	                    page: page
	                };
	            },
	            results: function (data, page) {
	                page = page || 1;
	                return {
	                    results: data.rows,
//	                     more: (params.page * 30) < data.total_count
	                };
	            },
	            cache: true
	        },
	        initSelection: function (element, callback) {
	        	var cid = $(element).val();
	            if (cid !== "") {
	                $.ajax("company/findUniqueById?id=" + cid, {
	                    dataType: "json"
	                }).done(function(data) { 
	                	if(data.company != null){
	                		callback({id: data.company.id, text: data.company.companyname});
	                	}
	                });
	            }
//	        	var data = {id: 12, text:"测试"};
//	        	callback(data);
	        },
	        width: "200px",
	        formatSearching: function(){
	        	return "正在搜索...";
	        },
	        multiple:false,
	        language: "zh-CN", //设置 提示语言
	        placeholder: "请选择",
			dropdownCssClass: "bigdrop", // apply css that makes the dropdown taller
			escapeMarkup: function (m) { return m; } // we do not want to escape markup since we are displaying html in results
	    });
	}
}
//绑定-切换企业类型时companytype
$("#companytype").bind("change",function(){
	var val = $(this).val();
	//清空所属上级
	$("#parentid option:not(:first)").remove();//保留第一项，其他清空
	
	$.ajax({
		url: "company/findParent",
		data: {"id":val},
		type: "POST",
		dataType:"json",
		async: false,
		success:function(data){
			//获取返回结果
			var companyList = data.companyList;
			//设置所属上级
			for(var i = 0; i < companyList.length; i++){
				$("#parentid").append("<option value='"+companyList[i].id+"'>"+companyList[i].companyname+"</option>");  
			}
		}
	});
});

//绑定-切换所属上级
$("#parentid").bind("change",function(){
	//展示授权指派和生产费用
	var parentid = $(this).val();
	var companytype = $("#companytype").val();
	if(parentid == HUIMEI_ID && (companytype == 10 || parentid == 50)){
		//如果商户类型是门店或者生产中心，且上级为汇美则显示授权指派
		$("#assignmentDiv").show();
	}else{
		$("#assignmentDiv").hide();
	}
});




//绑定-导出按钮
$(".exportExcel_btn").bind("click",function(){
	var params = $("#quick_search_from").serialize();
	location.href = "company/exportExcel?excel&"+params;
});

//初始日期空间 -必须最后加载，否则第二次加载时会报错
$('.datepicker').datepicker();


function addr(){
	var address = $("#selProvince").find("option:selected").text() + $("#selCity").find("option:selected").text() + $("#selDistrict").find("option:selected").text() + $("#address").val();
	map(address);
}
//百度地图
function map(addr){
	// 百度地图API功能
	var map = new BMap.Map("myMap");
	$("#myMap").show();
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,14);
	// 创建地址解析器实例
	var myGeo = new BMap.Geocoder();
	// 将地址解析结果显示在地图上,并调整地图视野
	myGeo.getPoint(addr, function(point){
		//console.log(point);
		$("#longitude").val(point.lng);
		$("#latitude").val(point.lat);
		if (point) {
			map.centerAndZoom(point, 18);
			map.addOverlay(new BMap.Marker(point));
			map.addEventListener("click",function(e){
				map.clearOverlays();
				var mk = new BMap.Marker(e.point);
				map.addOverlay(mk);
				map.panTo(e.point);
				myGeo.getLocation(e.point, function(rs){
					var addComp = rs.addressComponents;
					var thisAddr = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
					//确定详细地址（省市县不考虑）
					$("#address").val(addComp.street + addComp.streetNumber);
					//显示地址信息
					var label = new BMap.Label(thisAddr,{offset:new BMap.Size(20,-10)});
					mk.setLabel(label);
				});   
				//鼠标标注确定经纬度
				$("#longitude").val(e.point.lng);
				$("#latitude").val(e.point.lat);
			});
		}else{
			alert("您选择地址没有解析到结果!");
		}
	});
	map.enableScrollWheelZoom(true); 
	map.addControl(new BMap.NavigationControl());    
	map.addControl(new BMap.ScaleControl());    
	map.addControl(new BMap.OverviewMapControl());    
	map.addControl(new BMap.MapTypeControl());    
}