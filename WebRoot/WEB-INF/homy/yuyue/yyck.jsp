<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="hanson/css/table.css"/>
	<link rel="stylesheet" href="hanson/css/common.css" />

  </head>
  
  <body>
   <!--快速查询-->
<div class="quick_search">
	<span>用户编号:<input type="text" class = "cuid"/></span>
	<span>预约编号:<input type="text" class = "orderid"/></span>
	<span>项目名称:<input type="text" class = "odrname"/></span>
	<span>客户姓名:<input type="text" class = "cuname"/></span>
</div>
<!--对表单进行操作-->
<div class="from_handle">
	<a href="#" style="display: none;"><b></b>111</a>
	<a href="javascript:querybytj()"><b></b>查询</a>
	<a href="#"><b></b>重置</a>
</div>
<!--表单-->
<table id="cs_table" class="datatable"></table>
<script src="hanson/js/table.js" type="text/javascript" charset="utf-8"></script>
<script>
	query("");
	function querybytj(){
	     var cuid  = $(".cuid").val();
	     var orderid  = $(".orderid").val();
	     var odrname  = $(".odrname").val();
		 var cuname  = $(".cuname").val();
		 data = "cuid="+cuid+"&orderid="+orderid+"&odrname="+odrname+"&cuname="+cuname;
		 query(data);
	}
	function query(t){
	   $.ajax({
	   type: "POST",
	   url: "yuyue/yuyueList.do",
	   data: t,
	   success: function(msg){
		    var obj = eval(msg);
		    var data = [];
			    for(var i=0;i<obj.length;i++){
			    	var zfType = "";//支付方式
			    	if(obj[i]["paytype"]==0){
			    		zfType = "支付宝";
			    	}else if(obj[i]["paytype"]==1){
			    		zfType = "微支付";
			    	}else if(obj[i]["paytype"]==3){
			    		zfType = "线下支付";
			    	}
			    	var zfStatus = "";
			    	if(obj[i]["paystatus"]=="0"){
			    		zfStatus ="未支付";
			    	}else{
			    		zfStatus="已支付";
			    	}
						data[i] = {"id":obj[i]["id"],"cuid":obj[i]["cuid"],"companyname":obj[i]["companyname"],"orderid":obj[i]["orderid"],"odrname":obj[i]["odrname"],"cuname":obj[i]["cuname"],"paytype":zfType,"paystatus":zfStatus,"createdate":obj[i]["createdate"],"createdateyuyuebegin":obj[i]["createdateyuyuebegin"],"address":obj[i]["address"],"amount":obj[i]["amount"],"quantity":obj[i]["quantity"],"expresscode":obj[i]["expresscode"]};
			    }
			    var cs = new table({
			        "tableId":"cs_table",    //必须
			        "headers":["ID","用户编号","公司名称","预约编号","项目名称","客户姓名","支付方式","定金支付状态","创建时间","预约服务开始时间","地址","总价","实际金额","收货人联系电话"],   //必须
			        "data":data,        //必须
			        "displayNum": 6,    //必须   默认 10
			        "groupDataNum":9    //可选    默认 10
				});	
		   }
	});
	}
	
</script>
  </body>
</html>
