<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="/hanson/css/table.css"/>
<link rel="stylesheet" href="/hanson/css/common.css" />
<style>
    .quick_search>button{
        float: right;
        background-color: #169BD5;
        text-align: center;
        width:80px;
        height:30px;
        line-height: 30px;
        border:none;
        color: #FFFFFF;
        font-size: 16px;
        border-radius: 5px;
        margin-right: 20px;
    }
    #cs_table tbody td,#cs_table th{
    	text-align:center;
    }
    *{ margin:0; padding:0; list-style:none;}
	a{ text-decoration:none;}
	a:hover{ text-decoration:none;}
	.tcdPageCode{padding: 15px 20px;text-align: left;color: #ccc;text-align:center;}
	.tcdPageCode a{display: inline-block;color: #428bca;display: inline-block;height: 25px;	line-height: 25px;	padding: 0 10px;border: 1px solid #ddd;	margin: 0 2px;border-radius: 4px;vertical-align: middle;}
	.tcdPageCode a:hover{text-decoration: none;border: 1px solid #428bca;}
	.tcdPageCode span.current{display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;color: #fff;background-color: #428bca;	border: 1px solid #428bca;border-radius: 4px;vertical-align: middle;}
	.tcdPageCode span.disabled{	display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;	color: #bfbfbf;background: #f2f2f2;border: 1px solid #bfbfbf;border-radius: 4px;vertical-align: middle;}
</style>
<!--快速查询-->
<div class="quick_search">
    <span>商户名称：<input type="text" class="shname"/></span>
    <span>商户类型:
		<select name="" class="shtype">
			<option value="">请选择</option>
			<option value="50">门店</option>
			<option value="40">经销商</option>
			<option value="10">区域制作中心</option>
			<option value="20">推广中心</option>
			<option value="30">推广员</option>
		</select>
	</span>
    <span>
		<select name="dateType" class="changeDate">
			<option value="1">筛选时间（月）</option>
			<option value="0">筛选时间（日）</option>
		</select>
		<span class="month">
			<input type="text" name="startTime" placeholder="请选择月份" class="Wdate" onFocus="WdatePicker({ dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false })" />~
        	<input type="text" name="endTime" placeholder="请选择月份" class="Wdate" onFocus="WdatePicker({ dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false })" />
		</span>
        <span class="date" style="display:none;">
			<input type="text" name="startTime" placeholder="请选择日期" class="Wdate" onFocus="WdatePicker()" />~
        	<input type="text" name="endTime" placeholder="请选择日期" class="Wdate" onFocus="WdatePicker()" />
		</span>
	</span>
	<span>
		<input type="checkbox" class="isactive" style="width:30px;"/>活跃商户
	</span>
    <button class="checkbtn">筛选</button>
    <button onclick="exportExcel();">导出报表</button>
</div>
<!--表单-->
<table id="cs_table" class="datatable">
	<thead style="display:none;">
		<tr>
			<th style="width:100px;">商户编号</th>
			<th style="width:100px;">商户名称</th>
			<th style="width:100px;">新增粉丝数</th>
			<th style="width:100px;">累计粉丝数</th>
			<th style="width:100px;">订单总数</th>
			<th style="width:100px;">付款订单总数</th>
			<th style="width:100px;">订单总金额</th>
			<th style="width:100px;">付款订单总金额</th>
			<th style="width:100px;">注册日期</th>
			<th style="width:100px;">商户类型</th>
			<th style="width:100px;">省</th>
			<th style="width:100px;">电话</th>
			<th style="width:100px;">手机</th>
		</tr>
	</thead>
	<tbody></tbody>
</table>
<!--分页-->
<div class="tcdPageCode"></div>
<script src="hanson/js/jquery.page.js"></script>
<script src="http://www.my97.net/My97DatePicker/WdatePicker.js"></script>
<script>

	var now   = new Date();
	var fullyear = now.getFullYear();
	
	$(".month input[name='startTime']").val(fullyear + "-01");
	$(".month input[name='endTime']").val(fullyear + "-12");
	
	$(".changeDate").change(function(){
		var thisVal = $(this).val();
		if(thisVal==1){
			$(".month").show();
			$(".date").hide();
		}else{
			$(".month").hide();
			$(".date").show();
		}
	});
	
	function tadeilUser(){
		var shname = $(".shname").val();
		var shtype = $(".shtype").val();
		var dateType = $(".changeDate").val();
		var startTime = dateType==1 ? $(".month input[name='startTime']").val() : $(".date input[name='startTime']").val();
		var endTime = dateType==1 ? $(".month input[name='endTime']").val() : $(".date input[name='endTime']").val();
		var isactive = $(".isactive").attr('checked')=="checked"?1:0;
		$.ajax({
			type:"post",//
			url:"/dataCenter/merchantData?companyname="+shname+"&companytype="+shtype+"&dateType="+dateType+"&startTime="+startTime+"&endTime="+endTime+"&active="+isactive,
			async:true, 
			success:function(data){
				console.log(data);
				$("#cs_table tbody").empty();
				for(var i=0;i<data.data.rows.length;i++){
					$("#cs_table thead").show();
					$("#cs_table tbody").append('<tr><td style="width:100px;">'+data.data.rows[i].id+'</td><td style="width:100px;">'+data.data.rows[i].companyname+'</td><td style="width:100px;">'+data.data.rows[i].newUserNumber+'</td><td style="width:100px;">'+data.data.rows[i].userNumber+'</td><td style="width:100px;">'+data.data.rows[i].odrNumber+'</td><td style="width:100px;">'+data.data.rows[i].payOdrNumber+'</td><td style="width:100px;">'+data.data.rows[i].odrTotalPrice+'</td><td style="width:100px;">'+data.data.rows[i].payOdrTotalprice+'</td><td style="width:100px;">'+data.data.rows[i].createdate+'</td><td style="width:100px;">'+data.data.rows[i].companytype+'</td><td style="width:100px;">'+data.data.rows[i].provincename+'</td><td style="width:100px;">'+data.data.rows[i].phone+'</td><td style="width:100px;">'+data.data.rows[i].mobile+'</td></tr>');
				}
				$(".tcdPageCode").createPage({
			        pageCount:Math.ceil(data.data.total/10),
			        current:1,
			        backFn:function(p){
				        var shname = $(".shname").val();
						var shtype = $(".shtype").val();
						var dateType = $(".changeDate").val();
						var startTime = dateType==1 ? $(".month input[name='startTime']").val() : $(".date input[name='startTime']").val();
						var endTime = dateType==1 ? $(".month input[name='endTime']").val() : $(".date input[name='endTime']").val();
						var isactive = $(".isactive").attr('checked')=="checked"?1:0;
			            $.ajax({
							type:"post",//
							url:"/dataCenter/merchantData?companyname="+shname+"&companytype="+shtype+"&dateType="+dateType+"&startTime="+startTime+"&endTime="+endTime+"&active="+isactive+"&pageNo="+p,
							async:true,
							success:function(data){
								console.log(data);
								if(data.msg=="商户数据为空！"){
									alert("暂无相关数据");
								}else{
									$("#cs_table tbody").empty();
									for(var i=0;i<data.data.rows.length;i++){
										$("#cs_table tbody").append('<tr><td style="width:100px;">'+data.data.rows[i].id+'</td><td style="width:100px;">'+data.data.rows[i].companyname+'</td><td style="width:100px;">'+data.data.rows[i].newUserNumber+'</td><td style="width:100px;">'+data.data.rows[i].userNumber+'</td><td style="width:100px;">'+data.data.rows[i].odrNumber+'</td><td style="width:100px;">'+data.data.rows[i].payOdrNumber+'</td><td style="width:100px;">'+data.data.rows[i].odrTotalPrice+'</td><td style="width:100px;">'+data.data.rows[i].payOdrTotalprice+'</td><td style="width:100px;">'+data.data.rows[i].createdate+'</td><td style="width:100px;">'+data.data.rows[i].companytype+'</td><td style="width:100px;">'+data.data.rows[i].provincename+'</td><td style="width:100px;">'+data.data.rows[i].phone+'</td><td style="width:100px;">'+data.data.rows[i].mobile+'</td></tr>');
									}
								}
							}
						});
			        }
			    });
			}
		});
	}

	tadeilUser();
	$(".checkbtn").click(function(){
		tadeilUser();
	});

	
	function exportExcel(){
		var shname = $(".shname").val();
		var shtype = $(".shtype").val();
		var dateType = $(".changeDate").val();
		var startTime = dateType==1 ? $(".month input[name='startTime']").val()+"-00" : $(".date input[name='startTime']").val();
		var endTime = dateType==1 ? $(".month input[name='endTime']").val()+"-00" : $(".date input[name='endTime']").val();
		var isactive = $(".isactive").attr('checked')=="checked"?1:0;

		window.location.href ="dataCenter/merchantDataExcel?companyname="+shname+"&companytype="+shtype+"&dateType="+dateType+"&startTime="+startTime+"&endTime="+endTime+"&active="+isactive;
	}
</script>