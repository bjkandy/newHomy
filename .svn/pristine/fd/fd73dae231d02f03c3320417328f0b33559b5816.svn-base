<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.hanson.util.Constant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="hanson/css/common.css" />
<link rel="stylesheet" type="text/css" href="hanson/css/table.css" />
<link rel="stylesheet" href="hanson/css/datepicker.css" />
<link rel="stylesheet" href="hanson/css/select2.css" />
<link rel="stylesheet" href="hanson/css/toastr.css" />
<link rel="stylesheet" href="hanson/css/bootstrap.min.css" />
<link rel="stylesheet" href="hanson/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="hanson/css/fullcalendar.css" />
<link rel="stylesheet" href="hanson/css/uniform.css" />
<link rel="stylesheet" href="hanson/css/select2.css" />
<link rel="stylesheet" href="hanson/css/unicorn.main.css" />
<link rel="stylesheet" href="hanson/css/unicorn.grey.css"
	class="skin-color" />
<link rel="stylesheet"
	href="//at.alicdn.com/t/font_469373_f1ivqovej8j8aor.css">
<style>
.select2-drop {
	z-index: 99999
}

.MDlist {
	position: absolute;
	bottom: -102px;
	left: 175px;
	background-color: #fff;
	width: 52%;
	height: 100px;
	overflow-y: scroll;
	border: 1px solid #ddd;
	display: none;
}

.MDlist li {
	text-align: center;
	height: 30px;
	line-height: 30px;
}

.MDlist li:hover {
	background-color: #21C6F2;
	color: #fff;
}

.modeloading {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, .4);
	display: none;
}

.modeloading img {
	position: absolute;
	top: 50%;
	left: 50%;
	margin-left: -64px;
	margin-top: -64px;
}

/*2018.03.03*/
.panel-body {
	padding: 10px 0;
	font-size: 16px;
	display: flex;
}

.btn-body select {
	margin: 0
}

.panel-body span {
	margin: 0 10px 0 5px;
}

.searchform {
	display: flex;
	white-space: nowrap;
	flex-wrap: wrap;
	align-items: center;
	line-height: 40px;
}

.searchform input,.searchform select {
	margin: 0 10px 0
}
</style>

<style>
.pop-view {
	position: fixed;
	background-color: rgba(0, 0, 0, .3);
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
	z-index: 1000;
}

.pop-view .content {
	background-color: #fff;
	border-radius: 6px;
	margin: 0 auto;
	margin-top: 100px;
	width: 60%;
	min-height: 500px;
	padding: 20px;
}

[class ^="icon-"],[class *=" icon-"] {
	display: inline-block;
	width: auto;
	height: auto;
	margin-top: auto;
	line-height: inherit;
	vertical-align: text-top;
	background-image: none;
}

.iconfont {
	font-size: inherit;
}

.icon-star {
	color: #666;
}

.icon-star-full {
	color: #fbe223
}

.pic-list .item {
	width: 100px;
	height: 100px;
	background-color: red;
	margin-right: 10px;
}
a{
color: #FF9900;
}
a:hover {background-color:#FF704D;
          cursor:pointer;
        }   /* 鼠标移动到链接上 */
</style>
<script type="text/javascript"
	src="http://www.my97.net/My97DatePicker/WdatePicker.js"></script>

<div style="    padding: 10px;">
	<h3 style="margin:0">查询筛选</h3>
	<div class="panel-body">
		<input type="radio" name="optionsRadiosinline" id="optionsRadios3"
			value="" checked><span>全部</span> <input type="radio"
			name="optionsRadiosinline" id="optionsRadios3" value="好评"><span>好评(4~5星)</span>
		<input type="radio" name="optionsRadiosinline" id="optionsRadios3"
			value="中评"><span>中评(3星)</span> <input type="radio"
			name="optionsRadiosinline" id="optionsRadios3" value="差评"><span>差评(1~2星)</span>
		<input type="checkbox" id="headCheckBox" /><span>全选</span>
	</div>
	<div class="btn-body">
		<select id="txStatus" name="actionOfEvulation">
			<option value="" checked>批量操作</option>
			<option value="0">显示评价</option>
			<option value="1">隐藏评价</option>
			<option value="2">删除评价</option>
		</select> <input type="button" value="确定" onclick="getContent()"
			class="btn btn-default" />
		<button type="button" id="reset" class="btn btn-default">重置</button>
		<button type="button" id="search_btn" class="btn btn-default">查询</button>
		<a id='showClose' class='btn' data-toggle="collapse"
			data-parent="#accordion" href="#collapseOne" onclick='changeText()'>展开筛选</a>
	</div>
</div>


<div class="panel panel-default">
	<div id="collapseOne" class="panel-collapse collapse in">

		<!--快速查询-->
		<div class="quick_search">
			<form id="quick_search_from" action="">
				<div class='searchform'>
					<span>服务商户:</span><input name="merchants" type="text" value=""
						placeholder="请输入服务商户" /> <span>商品分类:</span><select id="txStatus"
						name="goods">
						<option value="">请选择</option>
						<option value="10">照片</option>
						<option value="20">照片框</option>
						<option value="30">照片墙</option>
						<option value="40">相册</option>
						<option value="50">约拍</option>
						<option value="60">自动打印</option>
						<option value="70">插页相册</option>
						<option value="80">台历</option>
					</select> <span>用户昵称:</span><input name="nickname" type="text" value=""
						placeholder="请输入用户昵称" /> <span>评价:</span><input name="evaluation"
						type="text" value="" placeholder="请输入评价内容" /> <span>商品名:</span><input
						name="tradeName" type="text" value="" placeholder="请输入商品名" />
					<div>
						<span>评价时间:</span><input id="date01" name="evaluationTimeFrom"
							type="text" class="Wdate"
							onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'date02\')||\'2050-10-01\'}'})"
							value="" />至<input id="date02" name="evaluationTimeTo"
							type="text" value="" class="Wdate"
							onFocus="WdatePicker({minDate:'#F{$dp.$D(\'date01\')}', maxDate:'2050-10-01'})" />
					</div>
				</div>
				<input type="hidden" name="start" />
			</form>
			<for>
		</div>
	</div>
</div>

<!--对表单进行操作-->

<!--表单-->
<div class="widget-box" style="overflow: auto;">
	<table id="cs_table"
		class="datatable table table-bordered table-hover table-condensed"></table>
</div>




<!-- 弹出窗 -->
<div class="pop-view" id="popView" style="display: none;">
	<div class="content" style="font-size: 18px; line-height: 2;  ">
		<div style="position: relative;">
			<!-- 关闭按钮 -->
			<div style="position: absolute; top: -20px; right: 0 ">
				<i class="iconfont icon-close" id="closeDiv" style="font-size: 25px"></i>
			</div>
			<!-- 头部-->
			<div class="clearfix" style="line-height: 50px;">
				<div class="pull-left">
					<img src="" alt=""
						style="display: inline-block; width: 40px; margin-right: 10px; height: 40px; background-color: cyan; border-radius: 1000px">
				</div>
				<div id="detailOfnickName" class="pull-left">昵称预约</div>
				<div class="pull-right">
					<div id="detailOfstarts" style="font-size: 22px!important;">
						<i class="iconfont icon-star-full"></i> <i
							class="iconfont icon-star"></i> <i class="iconfont icon-star"></i>
						<i class="iconfont icon-star"></i> <i class="iconfont icon-star"></i>
					</div>
				</div>
			</div>
			<!-- 评价内容 -->
			<div id="detailOfcontent" style="font-size: 20px;">
				朋友介绍的，不愧是传承三代专业摄影世家，质量扎实，包装也很有型，汇美出品，必出精品！</div>
			<!-- 标签-->
			<div style="line-height: 40px;">
				<i class="iconfont icon-tag"
					style="font-size: 22px; position: relative; margin-top: -10px;"></i>
				<span id="detailOfcommenttab" style="color: #666;">
					服务热情；性价比高；环境优雅；效果赞；价格实惠；体验好</span>
			</div>
			<!-- 图片 -->
			<div>
				<div id="picture" class="clearfix pic-list"></div>
			</div>
			<!--参数-->
			<div id="canshu" style="color: #666;">
				尺寸：7寸；颜色：胡桃木色；是否配照片：配输出照片</div>
			<!-- 追评 -->
			<div>
				<div style="color: #f00">购买30天后的评价</div>
				<!-- 追评内容 -->
				<div id="additionalComments" style="font-size: 18px;">
					挂在墙上很漂亮，卖家售后很给力，点个赞。</div>
				<!-- 图片 -->
				<div>
					<div id="additionalPicture" class="clearfix pic-list">
						
					</div>
				</div>
			</div>
			<!-- 底部点赞和时间 -->
			<div class="clearfix" style="font-size: 18px; line-height: 45px;">
				<div class="pull-left">
					<i class="iconfont icon-zan"
						style="position: relative; top: -10px; font-size: 25px;"></i> <span
						id="detailOfnod" style="display: inline-block;">32</span>
				</div>
				<div id="time" class="pull-right">2018-01-30 13:34</div>
			</div>
			<!-- 商家回复 -->
			<div>
				<div class="clearfix"
					style="border-top: 1px solid #999; padding: 10px 0;">
					<i class="iconfont icon-phone pull-left"
						style="font-size: 25px; margin-right: 10px; position: relative; margin-top: -8px;"></i>
					<div class="pull-left" style="margin-right: 10px; color: #fbe223;">
						商家回复:</div>
					<div id="detailOfrcontent" class="pull-left"></div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="hanson/js/jquery.min.js"></script>
<!-- 页面功能JS -->
<script src="hanson/js/hanson.comment.js" type="text/javascript"
	charset="utf-8"></script>
<!--添加复选框的js.-->
<script>

	   $(function(){
	    $("#popView").hide();
	     $("#headCheckBox").click(function(){		 
		   if(this.checked){
		   $("input[type='checkbox']").prop("checked",true);
		   }else{
		   $("input[type='checkbox']").removeAttr("checked",false);		   
		   }
		 });  
	   
	    $("input[type='checkbox']").click(function(){
		  var len=$("input[type='checkbox']").length;
		  var len1=$("input[type='checkbox']:checked").length;
          if(len==len1){
		    $("#headCheckBox").prop("checked",true);
		  }else{
		  $("#headCheckBox").removeAttr("checked",false);
		  };
	
		});
	   	
	   	
	   	$("#closeDiv").click(function(){
	   	 $("#popView").hide();
	   	});
	   	
	   });
	   
	   function changeText(){
		   if($('#showClose').text() === '展开筛选'){
		   		$('#showClose').text('收起筛选');
		   }else{
		   		$('#showClose').text('展开筛选');
		   }
	   	
	   }
</script>


