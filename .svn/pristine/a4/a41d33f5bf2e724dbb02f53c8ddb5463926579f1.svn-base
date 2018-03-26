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
			.ulNav{overflow: hidden;width: 100%;margin-bottom: 10px;}
			.ulNav li{list-style-type:none;width: 33.33%;height: 50px;line-height: 50px;float: left;text-align: center;background-color: #F2F2F2;border: 1px solid #C9C9C9;}
			.ulNav li.action{background-color:#FFFFFF;border-bottom-color: #FFFFFF;}
			input,select,textarea{background-color: #FFFFFF;}
			#essentialInformation,#commodityPrice,#productDetails{overflow: hidden;}
			#commodityPrice,#productDetails{display: none;}
			.part1,.part2{float: left;background-color: #F7F7F7;margin-bottom: 10px;}
			.part1{text-align: center;width:20%;margin-right: 1%;padding-top: 20px;}
			.part2{width: 79%;padding-left: 10px;}
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
			.addImage{width: 250px;position: relative;display: inline-block;margin: 10px;}
			.addImage>img{width: 100%;}
			.addImage>span{position:absolute;width:30px;height:30px;background-image: url(../../../hanson/img/icons/close.png);
			background-repeat: no-repeat;background-size: cover;background-position: top left;right: 0;top: 0;border-radius:50%;display: none;}
			.searchBtn{background-color: #108EE9;border-radius: 5px;color: #FFFFFF;text-align: center;height: 30px;line-height: 30px;
			outline: none;border: none;padding: 0px 10px;}
			.setPrice{float: left;margin: 0 10px;position: relative;}
			.setPrice>div{
				display: none;width: 216px;height: 74px;line-height: 74px;text-align: center;position: absolute;bottom: -74px;left: 30px;
				background-image:url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAANgAAABKCAYAAADZjpqlAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAOTSURBVHhe7dm/SxthGMDxy0Wr1daCtKAOXUSDOAi1g1Oho0PHUhwcKrjUtSA4dCmFbuIf4NQWHBwtlAyKgiDNVhVUiiBF6OBPjI0m8dLnOXOSBNFY7ylN/X7g4c67izj45X2TOAAAVKRI/hiWwt+Xyx+BGyvMwNz8BL/Tk9HIggFuHA0iDBqVm0gkni8uLo6OjY09lJ9rZKpkonpPJuzVEvjnhRWYcjOZzL26urrB3t7eb0tLSx8nJiYeyXUNrVqG0HDjhLmCRTzPi9TW1mZaW1vrmpubn3V3d8eXl5enxFO5r6HdkglWNULDfy/MFczRwPTouq7T2NhYJaHdbWlpeRKLxSZXVla+Tk9Pv+jq6qqXR3RFIzSgTBpqzdzc3KvNzc1k7hwHBwe59fX1pIT2c3Z2dqivr+++vOa2DNtH3Ej+tq/M0UBqLwoscHh4mNvY2Pi1urq6v7Cw8GZwcLBZXquh6faxMDSGqaQ5V+mNsl5UIni2emZm5mV7e/t72RbqNvBCx8fHzvb29lEymfRSqdSn+fn50aGhoQ25dSKjH/ErPt5HJSn8aspXGJGeR4eHh+u3trai4+Pj6fy1cuhzVfF4vL+zs/NdOYEFMpmMhpaWLWREovucSCTeDgwMfJdbxIVKkevv76/p6OhwRkZG9uVnXST8/9/CgHRrVi3vkb5Eo9HHnufdOb18NQ0NDemmpibd7l1JNpt19vb2sjs7O/rH6SeOQMVwXVffGv2QHZx+NaWLk78LKwzMfx8l742S8tDpFQBlW1tbc2KxmC5MRzK6UJx9TK+hBQPgzxW1FAQWIDDgeooaKg0MQIgIDDBEYIAhAgMMERhgiMAAQwQGGCIwwBCBAYYIDDBEYIAhAgMMERhgiMAAQwQGGCIwwBCBAYYIDDBEYIAhAgMMERhgiMAAQwQGGCIwwBCBAYYIDDBEYIAhAgMMERhgiMAAQwQGGCIwwBCBAYYIDDBEYIAhAgMMERhgiMAAQwQGGCIwwBCBAYYIDDBEYIAhAgMMERhgiMAAQwQGGCIwwBCBAYYIDDBUGlgufwQQgiCwICwCA66nqKXCFUwveJFIZCuVSp1eAVCWdDqtBw3HkzlbqCL5o9LYqqampnra2tom5fyBfxVAOfZ2d3df9/T0fJDzrIyGVhSY8iOTiebP9X7pMwCK+bs/GQ3rJH/ur2LnxUNYwNVpUIXjuywiIgMudxYUgL/GcX4DWFsWjnDZ2sgAAAAASUVORK5CYII=");
			}
			.setPrice input{width: 80px;}
			.confirmChange{margin: 0 10px;padding:0 10px;background-color: #108EE9;border-radius: 5px;border: none;outline: none;color: #FFFFFF;
			height: 30px;line-height: 30px;}
			.setBtn{background-color: #F3F3F3;border-radius: 5px;border: 1px solid #D9D9D9;outline: none;padding: 0px 10px;height: 30px;
			line-height: 30px;color: #666666;}
			.price_t{text-align: center;width: 90%;margin: 0 auto;border-collapse: collapse; padding: 0;border:1px solid #E3E3E3;}
			.price_t th{background-color: #F3F3F3;height: 40px;line-height: 40px;}
			.price_t td{background-color: #FFFFFF;height: 50px;line-height: 50px;}
			.price_t input{width: 50px;border: none;text-align: center;}
		</style>
  </head>
  
 <body>
		<!--导航栏-->
		<ul class="ulNav">
			<li class="action" data-id="#essentialInformation">编辑基本信息</li>
			<li data-id="#commodityPrice" style="display:none;">编辑商品价格</li>
			<li data-id="#productDetails">编辑商品详情</li>
		</ul>
		<form action="album/editbasicdata"  class="container-fluid form-horizontal" id="basicinfo" enctype="multipart/form-data" method="post" >
		 <input type="hidden" name="id" value="${serviceProject.id}">
		<div id="essentialInformation">
			<div class="part1">基本信息</div>
			<div class="part2">
				<div><b>*</b><span>商品名称:</span><input type="text" id="projectname" name="projectname" placeholder="请输入商品名称" onblur="selectprojectname()" value="${serviceProject.projectname}"/></div>
				<div><b>*</b><span>商品英文名:</span><input type="text" id="englishname" name="englishname" placeholder="请输入商品英文名" value="${serviceProject.englishname}"/></div>
				<div><b>*</b><span>拍摄类型:</span>
					<select name="shootingtype" id="shootingtype">
						<option value="30" >请选择</option>
						<option value="10" <c:if test="${serviceProject.shootingtype eq 10}">selected</c:if>>到门店拍</option>
						<option value="20" <c:if test="${serviceProject.shootingtype eq 20}">selected</c:if>>上门外拍</option>
					</select>
				</div>
				<div><b>*</b><span>现价:</span>
					<input type="text" name="totalamount" id="totalamount" placeholder="请输入现价" value="${serviceProject.totalamount/100}"/>
				</div>
				<div><b>*</b><span>原价:</span>
					<input type="text" name="deposit" id="deposit" placeholder="请输入原价" value="${serviceProject.deposit/100}"/>
				</div>
				<div><b>*</b><span>服务说明:</span>
					<div class="addImage">
					    <img    <c:if test="${!empty serviceProject.serviceinfo}">src="${yuyueimageViewDir}${serviceProject.serviceinfo}"</c:if> >
						<input type="file" name="serviceimg" style="display: none;" accept="image/jpg,image/jpeg,image/png,image/bmp">
						<span></span>
					</div>
					<i>建议尺寸：640*640像素；</i>
				</div>
				<div><b>*</b><span>用途:</span>
					<textarea name="mainfunction" id="mainfunction" rows="10" cols="10">${serviceProject.mainfunction}</textarea>
				</div>
				<div><b>*</b><span>增值服务:</span>
					<textarea name="addservice" id="addservice" rows="10" cols="10">${serviceProject.addservice}</textarea>
				</div>
				<div><b>*</b><span>商品主页图:</span>
					<div class="addImage">
						<img <c:if test="${!empty serviceProject.firstbanner}">src="${yuyueimageViewDir}${serviceProject.firstbanner}"</c:if> />
						<input type="file" id="index_file_input" name="firstImg" style="display: none;" accept="image/jpg,image/jpeg,image/png,image/bmp">
						<span></span>
					</div>
					<i>建议尺寸：640*640像素；</i>
				</div>
				<div><b>*</b><span>商品缩略图:</span>
					<div class="addImage">
					
						<img <c:if test="${!empty serviceProject.orderpic}">src="${yuyueimageViewDir}${serviceProject.orderpic}"</c:if> />
						<input type="file" id="index_file_input" name="thumbImg" style="display: none;" accept="image/jpg,image/jpeg,image/png,image/bmp">
						<span></span>
					</div>
					<i>建议尺寸：640*640像素；</i>
				</div>
			</div>
	     	<button class="nextBtn" style="display:none;">下一步</button>
		</div>
		
		<div id="productDetails">
			<div class="part1">商品详情</div>
			<div class="part2">
				<div><b>*</b><span>商品轮播图:</span>
					<c:forEach items="${detailimgList}" var="img" varStatus="status">
						<div class="addImage">
							<img src="${yuyueimageViewDir}${img}"/>
							<span></span>
						</div>
					</c:forEach>
					<div class="addImage">
						<img src="../../../hanson/img/image_add.jpg"/>
						<input type="file" data-name="detail" style="display: none;" accept="image/jpg,image/jpeg,image/png,image/bmp">
						<span></span>
					</div>
					<i>建议尺寸：640*640像素；</i>
				</div>
				<div><b>*</b><span>案列展示图:</span>
					<c:forEach items="${examplepicList}" var="img" varStatus="status">
						<div class="addImage">
							  <img src="${yuyueimageViewDir}${img}"/>
							<span></span>
						</div>
					</c:forEach>
					<div class="addImage">
						<img src="../../../hanson/img/image_add.jpg"/>
						<input type="file" data-name="example" style="display: none;" accept="image/jpg,image/jpeg,image/png,image/bmp">
						<span></span>
					</div>
					<i>建议尺寸：640*640像素；</i>
				</div>
				<div><b>*</b><span>专业特设内容:</span>
					<c:forEach items="${specialcontentList}" var="img" varStatus="status">
						<div class="addImage">
						  	<img src="${yuyueimageViewDir}${img}"/>
							<input type="file" data-name="special" style="display: none;" accept="image/jpg,image/jpeg,image/png,image/bmp">
							<span></span>
						</div>
					</c:forEach>
					<div class="addImage">
						<img src="../../../hanson/img/image_add.jpg"/>
						<input type="file" data-name="special" style="display: none;" accept="image/jpg,image/jpeg,image/png,image/bmp">
						<span></span>
					</div>
					<i>建议尺寸：640*640像素；</i>
				</div>
			</div>
			<button class="nextBtn">完成</button>
		</div>
		</form>
	</body>
	
	<script src="hanson/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="hanson/js/jquery.form.js"></script>	
	<script src="hanson/js/hanson.xcfb.js"></script>
	<script type="text/javascript" src="/hanson/js/photo.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#essentialInformation .part1").css('height',$("#essentialInformation .part2").height());
			//切换li显示不同内容
			$(".ulNav").on("click","li",function(){
				var $this = $(this);
				$this.addClass('action').siblings().removeClass('action');
				$($this.attr("data-id")).show(200).siblings("div").hide(200,function(){
					$($this.attr("data-id")+" .part1").css('height',$($this.attr("data-id")+" .part2").height());
				});
			})
			//添加图片
			$(".addImage").on("click","img",function(){
				$(this).next("input").click();
			});
			$(".addImage input").change(function(){
				var parent = $(this).closest("div[id]").attr("id");
				if(parent=="essentialInformation"){
					$(this).prev("img").attr("src",getObjectURL(this.files[0]));
					$(this).prev("img").load(function(){
						$("#essentialInformation .part1").css('height',$("#essentialInformation .part2").height());
						$("#productDetails .part1").css('height',$("#productDetails .part2").height());
					})
				}else{
					var $clone = $(this).parent().clone();
					$clone.find("img").attr("src",getObjectURL(this.files[0]));
					$(this).parent().before($clone);
					var index = $(this).parent().parent().find(".addImage").length;
					var $name = $(this).attr("data-name");
					$(this).parent().parent().find(".addImage").each(function(i,v){
						if($(v).find("img").attr("src").indexOf("image_add")>0){
							return;
						}else{
							$(v).find("input").attr("name",$name+i);
						}
					});
				}
			});
			$(".part2").on("mouseover",".addImage",function(){
				var src = $(this).find("img").attr("src");
				if(src.indexOf("image_add")>0){
					return;
				}else{
					$(this).find("span").show();
				}
			});
			$(".part2").on("mouseout",".addImage",function(e){
		        $(this).find("span").hide();
			});
			$(".part2").on("click",".addImage span",function(){
				var parent = $(this).closest("div[id]").attr("id");
				if(parent=="essentialInformation"){
					$(this).siblings("img").attr("src","../../../hanson/img/image_add.jpg");
				}else{
					$(this).parent().remove();
				}
			});
			//一键设置功能
			$(".setBtn").click(function(){
				var that =  $(this);
				that.siblings('div').toggle(500);
				that.parent().siblings().find("div").hide(500);
			})
			$(".confirmChange").click(function(){
				var that = $(this);
				var data = that.attr("data");
				var price = that.siblings("input").val()
				if(price==""){
					alert("请输入价格！");
				}else{
					//一件修改现价price  原价oldPrice  加修加印repairPrint  加印printPrice
					if(data=="price"){
						$(".price_t .price").val(price);
					}else if(data=="oldPrice"){
						$(".price_t .oldPrice").val(price);
					}else if(data=="repairPrint"){
						//
					}else if(data=="printPrice"){
						//
					}
					that.parent().hide(500);
				}
			});
			
			//下一步  切换操作
			$(".nextBtn").click(function(e){
				var p = $(this).parent();
				var parent = p.attr("id");
				console.log(111);
				//ajax提交form表单
 				$("#basicinfo").on("submit",function() {
					  $("#basicinfo").ajaxSubmit(function(){
							var url = "/shopping/toYuepai";
							$("#content").empty();
							$("#content").load(url);
					  });
					  return false; 
				}); 
			})
		});
		//建立一個可存取到該file的url
		function getObjectURL(file) {
			var url = null;
			if(window.createObjectURL !== undefined) { // basic
				url = window.createObjectURL(file);
			} else if(window.URL !== undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(file);
			} else if(window.webkitURL !== undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(file);
			}
			return url;
		}
		
		
		
		
				
	</script>
 
</html>
