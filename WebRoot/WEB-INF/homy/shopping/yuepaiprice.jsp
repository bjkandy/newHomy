<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>edit_yuepai</title>
    <meta charset="UTF-8">
    <style type="text/css">
			body{font-family: "微软雅黑";color: #333333;}
			*{box-sizing: border-box;}
			#essentialInformation,#commodityPrice,#productDetails{overflow: hidden;}
			.part1,.part2{float: left;background-color: #F7F7F7;margin-bottom: 10px;}
			.part2{width: 100%;padding-left: 10px;}
			.part2>div{margin: 10px 0px;min-height: 30px;line-height: 30px;}
			.part2>div textarea{width: 400px;height: 100px;vertical-align: middle;padding: 5px;}
			.part2 b{color: red;}
			.part2 i{color: red;}
			.part2 span{display: inline-block;width: 100px;text-align: left;}
			.part2 input,.part2 select{height: 30px;border: 1px solid #D9D9D9;border-radius: 5px;padding-left: 5px;width: 200px !important;}
			.nextBtn{
				display: block;background-color: #108EE9;border-radius: 5px;color: #FFFFFF;width: 90px;text-align: center;height: 35px;
				line-height: 35px;margin: 20px auto;outline: none;border: none;
			}
			.searchBtn{background-color: #108EE9;border-radius: 5px;color: #FFFFFF;text-align: center;height: 30px;line-height: 30px;
			outline: none;border: none;padding: 0px 10px;}
			.setPrice{float: left;margin: 0 10px;position: relative;}
			.setPrice>div{
				display: none;width: 216px;height: 74px;line-height: 74px;text-align: center;position: absolute;bottom: -74px;left: 30px;
				background-image:url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAANgAAABKCAYAAADZjpqlAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAOTSURBVHhe7dm/SxthGMDxy0Wr1daCtKAOXUSDOAi1g1Oho0PHUhwcKrjUtSA4dCmFbuIf4NQWHBwtlAyKgiDNVhVUiiBF6OBPjI0m8dLnOXOSBNFY7ylN/X7g4c67izj45X2TOAAAVKRI/hiWwt+Xyx+BGyvMwNz8BL/Tk9HIggFuHA0iDBqVm0gkni8uLo6OjY09lJ9rZKpkonpPJuzVEvjnhRWYcjOZzL26urrB3t7eb0tLSx8nJiYeyXUNrVqG0HDjhLmCRTzPi9TW1mZaW1vrmpubn3V3d8eXl5enxFO5r6HdkglWNULDfy/MFczRwPTouq7T2NhYJaHdbWlpeRKLxSZXVla+Tk9Pv+jq6qqXR3RFIzSgTBpqzdzc3KvNzc1k7hwHBwe59fX1pIT2c3Z2dqivr+++vOa2DNtH3Ej+tq/M0UBqLwoscHh4mNvY2Pi1urq6v7Cw8GZwcLBZXquh6faxMDSGqaQ5V+mNsl5UIni2emZm5mV7e/t72RbqNvBCx8fHzvb29lEymfRSqdSn+fn50aGhoQ25dSKjH/ErPt5HJSn8aspXGJGeR4eHh+u3trai4+Pj6fy1cuhzVfF4vL+zs/NdOYEFMpmMhpaWLWREovucSCTeDgwMfJdbxIVKkevv76/p6OhwRkZG9uVnXST8/9/CgHRrVi3vkb5Eo9HHnufdOb18NQ0NDemmpibd7l1JNpt19vb2sjs7O/rH6SeOQMVwXVffGv2QHZx+NaWLk78LKwzMfx8l742S8tDpFQBlW1tbc2KxmC5MRzK6UJx9TK+hBQPgzxW1FAQWIDDgeooaKg0MQIgIDDBEYIAhAgMMERhgiMAAQwQGGCIwwBCBAYYIDDBEYIAhAgMMERhgiMAAQwQGGCIwwBCBAYYIDDBEYIAhAgMMERhgiMAAQwQGGCIwwBCBAYYIDDBEYIAhAgMMERhgiMAAQwQGGCIwwBCBAYYIDDBEYIAhAgMMERhgiMAAQwQGGCIwwBCBAYYIDDBEYIAhAgMMERhgiMAAQwQGGCIwwBCBAYYIDDBUGlgufwQQgiCwICwCA66nqKXCFUwveJFIZCuVSp1eAVCWdDqtBw3HkzlbqCL5o9LYqqampnra2tom5fyBfxVAOfZ2d3df9/T0fJDzrIyGVhSY8iOTiebP9X7pMwCK+bs/GQ3rJH/ur2LnxUNYwNVpUIXjuywiIgMudxYUgL/GcX4DWFsWjnDZ2sgAAAAASUVORK5CYII=");
			}
			.setPrice input{width: 80px !important;}
			.confirmChange{margin: 0 10px;padding:0 10px;background-color: #108EE9;border-radius: 5px;border: none;outline: none;color: #FFFFFF;
			height: 30px;line-height: 30px;}
			.setBtn{background-color: #F3F3F3;border-radius: 5px;border: 1px solid #D9D9D9;outline: none;padding: 0px 10px;height: 30px;
			line-height: 30px;color: #666666;}
			.price_t{text-align: center;width: 90%;margin: 0 auto;border-collapse: collapse; padding: 0;border:1px solid #E3E3E3;}
			.price_t th{background-color: #F3F3F3;height: 40px;line-height: 40px;}
			.price_t td{background-color: #FFFFFF;height: 50px;line-height: 50px;}
			.price_t input{width: 50px;border: none;text-align: center;}
			.price_t button{background-color: #108EE9;border-radius: 5px;color: #FFFFFF;text-align: center;height: 30px;line-height: 30px;
			outline: none;border: none;padding: 0px 10px;}
		</style>
  </head>
 <body>
		<form action="shopping/toyuepaiPriceEdit"  class="container-fluid form-horizontal" id="basicinfo" enctype="multipart/form-data" method="post" >
		<input id="yupaiid" type="hidden" name="yupaiid" value="${yupaiid}">
		<div id="commodityPrice">
			<div class="part2">
				<div><b>*</b><span>地区:</span>
					<input type="text" placeholder="请输入市/区" name="area"/>
					<input type="text" placeholder="请输入商户编号" name="qyid"/>
					<button class="searchBtn">搜索</button>
				</div>
				<div>
					<div class="setPrice">
						<button class="setBtn">一键设置现价</button>
						<div><input type="number" min="0" step="0.01" name="" placeholder=""/><button class="confirmChange" data="price">确定</button></div>
					</div>
					<div class="setPrice">
						<button class="setBtn">一键设置加拍价</button>
						<div><input type="number" min="0" step="0.01" name="" placeholder=""/><button class="confirmChange" data="oldPrice">确定</button></div>
					</div>
					<div class="setPrice">
						<button class="setBtn">一键设置原价</button>
						<div><input type="number" min="0" step="0.01" name="" placeholder=""/><button class="confirmChange" data="repairPrint">确定</button></div>
					</div>
					<div class="setPrice" style="display:none;">
						<button class="setBtn">一键设置加印价格</button>
						<div><input type="number" min="0" step="0.01" name="" placeholder=""/><button class="confirmChange" data="printPrice">确定</button></div>
					</div>
				</div>
				<div>
					<table class="price_t" border="" cellspacing="0" cellpadding="0">
						<thead>
							<tr><th>ID</th><th>市/区</th><th>商户编号</th><th>公司名称</th><th>现价(单位:分)</th><th>加拍价(单位:分)</th><th>原价(单位:分)</th><th>编辑</th></tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			
			<!-- <button class="nextBtn" >完成</button> -->
		</div>
		</form>
	</body>
	
	<script src="hanson/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="hanson/js/jquery.form.js"></script>	
	<script src="hanson/js/hanson.xcfb.js"></script>
	<script type="text/javascript" src="/hanson/js/photo.js"></script>
	<script type="text/javascript">
		$(function(){
			ajaxMendianList($("#yupaiid").val(),'','');
			/* 按条件搜索门店 */
			$(".searchBtn").click(function(e){
				e.preventDefault();
				var area = $("input[name='area']").val(),
					qyid = $("input[name='qyid']").val();
				ajaxMendianList($("#yupaiid").val(),area,qyid);
			});
			$(".price_t").on("click","button",function(e){
				e.preventDefault();
				var dataId = $(this).parent().parent().attr("data-id"),
					price = $(this).parent().parent().find(".price").val(),
					addPrice = $(this).parent().parent().find(".addPrice").val(),
					originalprice = $(this).parent().parent().find(".originalprice").val();
					$.ajax({
						type:"post",
						url:"shopping/modifyyuepaiPrice?id="+dataId+"&price="+price+"&addPrice="+addPrice+"&originalprice="+originalprice,
						async:true,
						success:function(data){
							if(data == "1"){
								alert("成功修改该门店的价格！");
							}
						}
					});
			});
			//一键设置功能
			$(".setBtn").click(function(e){
				e.preventDefault();
				var that =  $(this);
				that.siblings('div').toggle(500);
				that.parent().siblings().find("div").hide(500);
			});
			$(".confirmChange").click(function(e){
				e.preventDefault();
				var that = $(this);
				var data = that.attr("data");
				var price = that.siblings("input").val();
				if(price==""){
					alert("请输入价格！");
				}else{
					//一件修改现价price  原价oldPrice  加修加印repairPrint  加印printPrice
					if(data=="price"){
						$(".price_t .price").val(price);
					}else if(data=="oldPrice"){
						$(".price_t .addPrice").val(price);
					}else if(data==".originalprice"){
						$(".price_t .originalprice").val(price);
					}
					that.parent().hide(500);
				}
			});
			$(".nextBtn").click(function(e){
				var p = $(this).parent();
				var parent = p.attr("id");
				
				//ajax提交form表单
 				$("#basicinfo").on("submit",function() {
					  $("#basicinfo").ajaxSubmit(function(){
							var url = "/shopping/toYuepai";
							$("#content").empty();
							$("#content").load(url);
					  });
					  return false; 
				}); 
			});
		});		
		//获取门店信息
		function ajaxMendianList(projectid,area,qyid){
			$.ajax({
				type:"post",
				url:"shopping/selectyuepaiPrice?projectid="+projectid+"&area="+area+"&qyid="+qyid,
				async:true,
				success:function(data){
					var json = JSON.parse(data);
					console.log(json);
					if(json.length == 0){
						alert("请确认查询条件！");
					}else{
						$(".price_t tbody").empty();
						$.each(json,function(i,v){
							$(".price_t tbody").append(
								'<tr data-id="'+v.id+'"><td>'+v.id+'</td><td>'+v.area+'</td><td>'+v.qyid+'</td><td>'+v.companyname+'</td>'+
									'<td><input type="number" min="0" step="0.01" value="'+v.presentmoney+'" class="price"/></td>'+
									'<td><input type="number" min="0" step="0.01" value="'+v.addproductmoney+'" class="addPrice"/></td>'+
									'<td><input type="number" min="0" step="0.01" value="'+v.originalprice+'" class="originalprice"/></td>'+
									'<td><button>确认</button></td>'+
								'</tr>'
							);
						});
					}
				}
			});
		}	
	</script>
 
</html>
