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
    
    <title>订单管理</title>
	<link rel="stylesheet" type="text/css" href="css/table.css"/>
	<link rel="stylesheet" href="css/common.css" />

  </head>
  
  <body>
  <!--快速查询-->
  <form action="order/orderList" method="post">
<div class="quick_search">
	<span>商户编号:<input type="text" class="productid"/></span>
	<span>订单号:<input type="text" class="orderid"/></span>
	<span>客户昵称:<input type="text" class="cuname"/></span>
	<span>订单日期:<input type="text" class="kdate"/>~<input type="text" class="jdate"/></span><br />
	<span>订单状态:
		<select class="orderstatus">
			<option value="-1">请选择</option>
			<option value="20">未生产</option>
			<option value="25">生产完毕</option>
			<option value="30">已发货	</option>
			<option value="40">已签收	</option>
			<option value="110">已关闭</option>
			<option value="200">无效订单</option>
		</select>
	</span>
	<span>提现状态:
		<select class="txStatus">
			<option value="-1">请选择</option>
			<option value="10">未提现</option>
			<option value="20">提现中</option>
			<option value="40">提现成功</option>
		</select>
	</span>
</div>

<!--对表单进行操作-->
<div class="from_handle">
	<a href="#" style="display: none;"><b></b></a>
	<a href="index.jsp"><b></b>查看</a>
	<a href="#"><b></b>订单下载</a>
	<a href="javascript:sc()"><b></b>订单生产完毕</a>
	<a href="javascript:fahuo()"><b></b>订单已发货</a>
	<a href="#"><b></b>订单退款</a>
	<a href="javascript:js()"><b></b>发起结算</a>
	<a href="#"><b></b>导出excel</a>
	<a href="javascript:querybytj()"><b></b>查询</a>
	<a href="#"><b></b>重置</a>
</div>
</form>
<!--模态-录入-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
  	<div class="modal-dialog" role="document">
    	<div class="modal-content">
      		<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">录入</h4>
		    </div>
		    <form action="" method="post"  class="form-horizontal">
		    <div class="modal-body">
				<div class="control-group">
					<label class="control-label" for="inputCName">公司名称</label>
					<div class="controls">
					    <input type="text" id="inputCName" placeholder="公司名称">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputCName1">公司名称</label>
					<div class="controls">
					    <input type="text" id="inputCName1" placeholder="公司名称1">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputCName2">公司名称</label>
					<div class="controls">
					    <input type="text" id="inputCName2" placeholder="公司名称2">
					</div>
				</div>
		    </div>				
		  	<div class="modal-footer">
		    	<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		    	<button type="submit" class="btn btn-primary">确定</button>
		  	</div>
		    </form>
		</div>
	</div>
</div>

<!--表单-->
<table id="cs_table" class="datatable"></table>
<script src="js/table.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	query("");//查询
	
	
	//修改已发货
	function fahuo(){
	var ss = confirm("是否要修改为已发货");
		if(ss==true){
		
	var id  = $("#cs_table .active").children().first().html();
	var struts  = $("#cs_table .active").children().eq(7).html();
	var data = "id="+id+"&struts="+struts;
	 $.ajax({
	   type: "POST",
	   url: "order/orderfahuo.do",
	   data: data,
	   success: function(msg){
	   alert(msg);
}
	});
	querybytj();
	/* query("");//查询 */
		}else{
		return;
		}
	
	}
	//修改生产完毕
		function sc(){
		var ss = confirm("是否要修改为生产完毕");
			if(ss==true){
				var id  = $("#cs_table .active").children().first().html();
	var struts  = $("#cs_table .active").children().eq(7).html();
	var data = "id="+id+"&struts="+struts;
	 $.ajax({
	   type: "POST",
	   url: "order/ordersc.do",
	   data: data,
	   success: function(msg){
	   alert(msg);
}
	});
	querybytj();
	/* query("");//查询 */
			}else{
			return;
			}

	}
	//发起结算
		function js(){
		var ss = confirm("是否要发起结算");
			if(ss==true){
	var id  = $("#cs_table .active").children().first().html();
	var data = "id="+id;
	 $.ajax({
	   type: "POST",
	   url: "order/orderjs.do",
	   data: data,
	   success: function(msg){
	   alert(msg);
}
	});
	querybytj();
	/* query("");//查询 */
	}else{
	return;
	}
	}
	//模糊查询的方法
	function querybytj(){
	     var cuid  = $(".productid").val();
	     var orderid  = $(".orderid").val();
		 var cuname  = $(".cuname").val();
		 var kdate  = $(".kdate").val();
	     var jdate  = $(".jdate").val();
	     var orderstatus  = $(".orderstatus").val();
		 var txStatus  = $(".txStatus").val();
		 data = "cuid="+cuid+"&orderid="+orderid+"&cuname="+cuname+"&kdate="+kdate+"&jdate="+jdate+"&orderstatus="+orderstatus+"&txStatus="+txStatus;
		 query(data);
	}
	function query(t){
	   $.ajax({
	   type: "POST",
	   url: "order/orderList.do",
	   data: t,
	   success: function(msg){
	   var obj = eval(msg);
			var data = [];
		    for(var i=0;i<obj.length;i++){
		    	var jsje = obj[i]["totalprice"]-obj[i]["fee"];
		    	var bl = (obj[i]["fee"]/obj[i]["totalprice"])*100+"%";
		    	var dStatus = "";
		    	if( obj[i]["orderstatus"]==20){
		    		dStatus ="未生产";
		    	}else if( obj[i]["orderstatus"]==25){
		    		dStatus ="生产完毕";
		    	}else if( obj[i]["orderstatus"]==30){
		    		dStatus ="已发货";
		    	}else if( obj[i]["orderstatus"]==40){
		    		dStatus ="已签收";
		    	}else if( obj[i]["orderstatus"]==110){
		    		dStatus ="已关闭";
		    	}else if( obj[i]["orderstatus"]==200){
		    		dStatus ="无效订单";
		    	}
		    	var zfType = "";
		    	if(obj[i]["paytype"]==0){
		    		zfType="支付宝";
		    	}else{
		    		zfType="微支付";
		    	}
		    	var txStatus = "";
		    	if(obj[i]["txStatus"]==10){
		    		txStatus = "未提现";
		    	}else if(obj[i]["txStatus"]==20){
		    		txStatus = "提现中";
		    	}else{
		    		txStatus = "成功提现";
		    	}
		        data[i] = {"id":obj[i]["id"],"cuid":obj[i]["cuid"],"orderid":obj[i]["orderid"],"cuname":obj[i]["cuname"],"totalprice":obj[i]["totalprice"],"productotallprice":obj[i]["productotallprice"],number:obj[i]["number"],orderstatus:dStatus,"txStatus":txStatus,"createdate":obj[i]["createdate"],paytype:zfType,"jsje":jsje,fee:obj[i]["fee"],bl:bl};
		    }
		    var cs = new table({
		        "tableId":"cs_table",    //必须
		        "headers":[" ","商户编号","订单号","客户昵称","订单总金额","商品总金额","数量","订单状态","提现状态","订单日期","支付方式","结算金额","返佣金额","返佣比例"],   //必须
		        "data":data,        //必须
		        "displayNum": 6,    //必须   默认 10
		        "groupDataNum":9  //可选    默认 10
			});	
		}
	});
	}
	

	
	//点击表格添加active
	$('#cs_table').on("click","td",function(){
		console.log(this);
		$(this).parent().addClass("active").siblings().removeClass("active");
	})
	$("#bianji").click(function(e){
		console.log(this);
		if($("#cs_table tr").hasClass("active")){
			console.log($("#cs_table tr.active"));
			$('#myModal').modal('show');
		}else{
			alert("1111");
		}
	})
</script>
  </body>
</html>
