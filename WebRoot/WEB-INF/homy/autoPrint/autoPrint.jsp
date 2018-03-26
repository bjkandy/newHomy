<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>设备管理</title>
</head>

<link rel="stylesheet" href="hanson/css/common.css" />
<link rel="stylesheet" type="text/css" href="hanson/css/table.css"/>
<link rel="stylesheet" href="hanson/css/datepicker.css" />
<link rel="stylesheet" href="hanson/css/select2.css" />
<link rel="stylesheet" href="hanson/css/toastr.css" />
<link rel="stylesheet" href="hanson/css/bootstrap.min.css" />
<link rel="stylesheet" href="hanson/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="hanson/css/fullcalendar.css" />	
<link rel="stylesheet" href="hanson/css/uniform.css" />
<link rel="stylesheet" href="hanson/css/select2.css" />	
<link rel="stylesheet" href="hanson/css/unicorn.main.css" />
<link rel="stylesheet" href="hanson/css/unicorn.grey.css" class="skin-color" />

<style>
.select2-drop{  
     z-index:99999          
} 
.button1{
    background-color: #169BD5;
    text-align: center;
    width:80px;
    height:30px;
    line-height: 30px;
    border:none;
    color: #FFFFFF;
    font-size: 16px;
    border-radius: 5px;
}
#autoPrintForm{
    margin-left: 20px;
}
#autoPrintForm>div>div{
    display: inline-block;
    margin-right: 20px;
    margin-bottom: 20px;
}
input[type="text"]{
    width: 100px;
}
.productInfo{
    background-color: #FFFFFF;
    padding:20px;
    border-radius: 5px;
}
.productInfo input[type='text']{
    width: 50px;
    text-align: center;
    height: 27px;
    border-radius: 5px;
    border: 1px solid #DDDDDD;
    margin: 0px 10px;
}
.productInfo .size{
	width:20px;
	border:none;
	background-color:#fff;
}
</style>

<script type="text/javascript">
	
	function query(){
		$("#table tbody").html("");
		$.ajax({
		type : "post",
			url : "/equipment/pagingQuery",
			data: $("form").serialize(),
			success : function(data) {
				var obj = eval('(' + data + ')');
		
				for(var i = 0; i< obj.rows.length;i++){
					$("#table tbody").append("<tr><td><input type='checkbox'></td> <td>"+(i+1)+"</td><td>"+obj.rows[i].id+"</td><td>"+obj.rows[i].qyid+"</td><td>"+obj.rows[i].equipmentname+"</td><td>"+obj.rows[i].provincename+"</td><td>"+obj.rows[i].cityname+"</td><td>"+obj.rows[i].districtname+"</td><td>"+obj.rows[i].address+"</td><td><a href='equipment/add?id="+obj.rows[i].id+"' class='equipAdd'>编辑</a></td></tr>");
				}
				
				$(".prepage").attr("href",obj.prepage);
				$(".nextpage").attr("href",obj.nextpage);
				$("input[name=page]").val(obj.pageno);
				
				for(var i=0;i<obj.pagesize;i++){
					
					$(".nextpage").before('<a href="" class="A" >'+(i+1)+'</a>');
					if(i==9){
						break;
					}
				}
			},
			error : function(data) {
			
			}
		});
	}
</script>
<body>
  <!--快速查询-->
<div class="quick_search">
	<form id="quick_search_from" action="">
		<div>
			<span>商户id：<input name="qyid" type="text" value="" placeholder="请输入公司地址"/></span>
			<span>设备id：<input name="id" type="text" value="" placeholder="请输入邮箱"/></span>
			<span>设备名：<input name="equipmentname" type="text" value="" placeholder="请输入手机号"/></span>
		</div>
		
		<div>
			<span>注册日期:<input name="createtimeStart" type="text" data-date="2017-01-01" data-date-format="yyyy-mm-dd" value="" class="datepicker "/>~<input name="createtimeEnd" type="text" data-date="2017-01-01" data-date-format="yyyy-mm-dd" value="" class="datepicker"/></span>
		</div>
		
		<div>
			<span>地区:
			<select class="form-control" name="province" id="selProvinceSech">
			<option value="--请选择省份--">--请选择省份--</option></select>
			<select class="form-control" name="city"  id="selCitySech">
			<option value="--请选择市/区--">--请选择市/区--</option></select>
			<select class="form-control" name="district" id="selDistrictSech">
			<option value="--请选择区/县--">--请选择区/县--</option></select>
			</span>
			<input type="hidden" name="provincename">
			<input type="hidden" name="cityname">
			<input type="hidden" name="districtname">
		</div>
	</form>
</div>

<!--对表单进行操作-->
<div class="from_handle">
	<a href="#myModal" role="button"  data-toggle="modal" ><b></b>录入</a>
	<a href="javascript:void(0)" class="ercode_btn"><b></b>二维码</a>
	<a href="javascript:void(0)" class="delete_btn" ><b></b>删除</a>
	<a href="javascript:void(0)" class="exportExcel_btn"><b></b>导出excel</a>
	<a href="javascript:void(0)" class="search_btn"><b></b>搜索</a>
</div>

<!--模态-录入-->
<div class="modal hide fade" id="myModal" role="dialog" tabindex="-1" aria-labelledby="gridSystemModalLabel" data-backdrop="static" data-keyboard="false">
  	<div class="modal-dialog" role="document">
    	<div class="modal-content">
      		<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">录入</h4>
		    </div>
		    <form id="companyInputForm" action="company/save" method="post" class="form-horizontal">
		    
		    <div class="modal-body">
		    	<input type="hidden" id="id" name="id" />
		    	
		    	<div style="margin-bottom: 20px;">商户基本信息</div>
		        <!--商户基本信息-->
		        <div style="background-color: #FFFFFF;padding: 20px;border-radius: 5px;">
		            <div>商户名称：<input type="text" placeholder="请输入商户名称" name="companyname" value="${companyname}"></div>
		            <div>商户id：<input type="text" placeholder="请输入商户id" name = "qyid" value="${qyid}"></div>
		            <div>
		                <span>地区:
						<select class="form-control" name="province" id="selProvince">
						<option value="--请选择省份--">--请选择省份--</option></select>
						<select class="form-control" name="cityn"  id="selCity">
						<option value="--请选择市/区--">--请选择市/区--</option></select>
						<select class="form-control" name="district" id="selDistrict">
						<option value="--请选择区/县--">--请选择区/县--</option></select>
						</span>
						<input type="hidden" name="provincename">
						<input type="hidden" name="cityname">
						<input type="hidden" name="districtname">
		            </div>
		            <div>详细地址：<input type="text" name="address" value="${address}" placeholder="输入商户详细地址" style="width:200px;"></div>
		        </div>
		        <br>
       			<div style="margin: 20px 0;">设备信息</div><br>
		        <!--设备信息-->
		        <div style="background-color: #FFFFFF;padding: 20px;border-radius: 5px;">
		            <div>设备名称：<input type="text" name="equipmentname" placeholder="请输入设备名称"></div>
		            <c:if test="${not empty id}">
		           	 <div>设备id：<input type="text" value="${id}"></div>
		            </c:if>
		            <br>
		            <c:if test="${not empty qrcode}">
		          	  <div >设备二维码：<img src="${qrcode}" alt="" style="vertical-align: top"></div>
		            </c:if>
		        </div>
        		<br>
	   			<div style="margin: 20px;">产品信息</div><br>
			    <!--产品信息-->
			    <div class="productInfo">
			        <div>
			            <span>光面</span><br><br>
			            <ul>
			                <li><input class="size" type="text" name="size5" value="5寸" readonly="readonly">：<input type="text" name="size5">元 &nbsp;<input type="checkbox" name="size5">上架 &nbsp;<span style="color: red">*</span>库存：<input type="text" name="size5"></li>
			                <li><input class="size" type="text" name="size6" value="6寸" readonly="readonly">：<input type="text" name="size6">元 &nbsp;<input type="checkbox" name="size6">上架 &nbsp;<span style="color: red">*</span>库存：<input type="text" name="size6"></li>
			                <li><input class="size" type="text" name="size7" value="7寸" readonly="readonly">：<input type="text" name="size7">元 &nbsp;<input type="checkbox" name="size7">上架 &nbsp;<span style="color: red">*</span>库存：<input type="text" name="size7"></li>
			                <li><input class="size" type="text" name="size8" value="8寸" readonly="readonly">：<input type="text" name="size8">元 &nbsp;<input type="checkbox" name="size8">上架 &nbsp;<span style="color: red">*</span>库存：<input type="text" name="size8"></li>
			            </ul>
			        </div>
			        <div>
			            <span>绒面</span><br><br>
			            <ul>
			                <li><input class="size" type="text" name="rsize5" value="5寸" readonly="readonly">：<input type="text" name="rsize5">元 &nbsp;<input type="checkbox" name="rsize5">上架 &nbsp;<span style="color: red">*</span>库存：<input type="text" name="rsize5"></li>
			                <li><input class="size" type="text" name="rsize6" value="6寸" readonly="readonly">：<input type="text" name="rsize6">元 &nbsp;<input type="checkbox" name="rsize6">上架 &nbsp;<span style="color: red">*</span>库存：<input type="text" name="rsize6"></li>
			                <li><input class="size" type="text" name="rsize7" value="7寸" readonly="readonly">：<input type="text" name="rsize7">元 &nbsp;<input type="checkbox" name="rsize7">上架 &nbsp;<span style="color: red">*</span>库存：<input type="text" name="rsize7"></li>
			                <li><input class="size" type="text" name="rsize8" value="8寸" readonly="readonly">：<input type="text" name="rsize8">元 &nbsp;<input type="checkbox" name="rsize8">上架 &nbsp;<span style="color: red">*</span>库存：<input type="text" name="rsize8"></li>
			            </ul>
			        </div>
			    	<hr/>
			    	<input type="checkbox" name="openstoc" value="1">库存提醒
			    </div>
			</div>	
		  	<div class="modal-footer">
		    	<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		    	<button type="button" value="Validate" class="btn btn-primary" onclick="check_form()">保存</button>
		  	</div>
		    </form>
		</div>
	</div>
</div>


<!--模态-创建二维码-->
<div class="modal hide fade" id="companyQrcodeModal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
  	<div class="modal-dialog" role="document">
    	<div class="modal-content">
      		<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="companyQrcodeModalLabel">生成二维码</h4>
		    </div>
		    <div class="modal-body">
				<h3>${message}</h3>
			    <div style="text-align: center;"><img id="images" width="350" height="270"/></div>
		    </div>				
		  	<div class="modal-footer">
		    	<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		  	</div>
		</div>
	</div>
</div>

<!--表单-->
<div class="widget-box" style="overflow: auto;">
<table id="cs_table" class="datatable" style="width:2000px"></table>
</div>

<script src="hanson/js/bootstrap-datepicker.js"></script>
<script src="hanson/js/jquery.validate.js"></script>
<script src="hanson/js/select2.min.js"></script>
<!-- 消息提醒 -->
<script src="hanson/js/toastr.js"></script>
<script src="hanson/js/toastr.setting.js"></script>
<!-- 省市区控件 -->
<script src="hanson/js/provincial.data.js"></script>
<script src="hanson/js/provincial.select.js"></script>
<!-- 表格控件 -->
<script src="hanson/js/table.ajax.js" type="text/javascript" charset="utf-8"></script>
<script>
initProvicialSelect();

$.each(province, function (k, p) { 
    var option = "<option value='" + p.ProID + "'>" + p.ProName + "</option>";
    $("#selProvinceSech").append(option);
});

$("#selProvinceSech").change(function () {
    var selValue = $(this).val(); 
    $("#selCitySech option:gt(0)").remove();
    
   $("#selDistrictSech option:gt(0)").remove(); 
    
    $("input[name='provincename']").val($('#selProvinceSech option:selected').text());
    
    $.each(city, function (k, p) { 
        if (p.ProID == selValue) {
            var option = "<option value='" + p.CityID + "'>" + p.CityName + "</option>";
            $("#selCitySech").append(option);
        }
    });
     
});
 
$("#selCitySech").change(function () {
    var selValue = $(this).val();
    $("#selDistrictSech option:gt(0)").remove(); 
    
    $("input[name='cityname']").val($('#selCitySech option:selected').text());
    
    $.each(District, function (k, p) {
        if (p.CityID == selValue) {
            var option = "<option value='" + p.Id + "'>" + p.DisName + "</option>";
            $("#selDistrictSech").append(option);
        }
    }); 
});

$("#selDistrictSech").change(function(){
	 $("input[name='districtname']").val($('#selDistrictSech option:selected').text());
});
</script>

<!-- 页面功能JS -->
<script src="hanson/js/hanson.autoprint.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>