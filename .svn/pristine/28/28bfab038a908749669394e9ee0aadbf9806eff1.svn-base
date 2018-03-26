<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge,chrome=1">
    <title>编辑设备</title>
    <style>
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
        .productInfo input[type='number']{
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
</head>

<script type="text/javascript">
	$(function(){
		$("#uptButton").click(function(){
			$.ajax({
				type : "post",
				url : "/equipment/insert",
				data: $("form").serialize(),
				success : function(data) {
					
				},
				error : function(data) {
				
				}
			});
		});
		
		$.each(province, function (k, p) { 
		    var option = "<option value='" + p.ProName + "'>" + p.ProName + "</option>";
		    $("#selProvinceSech1").append(option);
		});
		
		$("#selProvinceSech1").change(function () {
		    var selValue = $(this).val(); 
		    $("#selCitySech1 option:gt(0)").remove();
		     
		    $.each(city, function (k, p) { 
		        if (p.ProID == selValue) {
		            var option = "<option value='" + p.CityName + "'>" + p.CityName + "</option>";
		            $("#selCitySech1").append(option);
		        }
		    });
		     
		});
		 
		$("#selCitySech1").change(function () {
		    var selValue = $(this).val();
		    $("#selDistrictSech1 option:gt(0)").remove(); 
		
		    $.each(District, function (k, p) {
		        if (p.CityID == selValue) {
		            var option = "<option value='" + p.DisName + "'>" + p.DisName + "</option>";
		            $("#selDistrictSech1").append(option);
		        }
		    }); 
		});
		
	});
	
	
</script>
<body>
    <!--返回按钮-->
    <div style="padding-left: 20px;padding-top: 20px;margin-bottom: 20px;">
        <button class="button1">返回</button>
    </div>

    <form action="" id="autoPrintForm">
        <div style="margin-bottom: 20px;">商户基本信息</div>
        <!--商户基本信息-->
        <div style="background-color: #FFFFFF;padding: 20px;border-radius: 5px;">
            <div>商户名称：<input type="text" placeholder="请输入商户名称" name="companyname" value="${companyname}"></div>
            <div>商户id：<input type="text" placeholder="请输入商户id" name = "qyid" value="${qyid}"></div>
            <div>
                <span>地区:
					<select class="form-control" name="provincename" id="selProvinceSech1">
					<option value="">--请选择省份--</option></select>
					<select class="form-control" name="cityname"  id="selCitySech1">
					<option value="">--请选择市/区--</option></select>
					<select class="form-control" name="districtname" id="selDistrictSech1">
					<option value="">--请选择区/县--</option></select>
				</span>
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
	
   </form>
    <!--确认修改-->
    <div style="padding-right: 20px;padding-top: 20px;margin-bottom: 20px;text-align: right;">
    	<c:if test="${not empty id}">
        	<button id="uptButton" class="button1">确认修改</button>
       	</c:if>
       	
       	<c:if test="${empty id}">
        	<button id="uptButton" class="button1">确认修改</button>
       	</c:if>
    </div>
</body>
</html>