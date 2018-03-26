<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>商品管理</title>
<link rel="stylesheet" type="text/css" href="hanson/css/table.css" />
<link rel="stylesheet" href="hanson/css/common.css" />
<link rel="stylesheet" href="hanson/css/toastr.css" />
<style type="text/css">
	#companyMaketable{
		width: 100%;
		text-align: center;
	}
	#companyMaketable th{text-align: center;}
	#companyMaketable input{width:100px;text-align: center;}
</style>
<script src="hanson/js/toastr.js"></script>
<script src="hanson/js/toastr.setting.js"></script>
<script src="hanson/js/table.ajax.js" type="text/javascript" charset="utf-8"></script>
<script src="hanson/js/hanson.productList.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<form id="productSearchForm">
		<div class="quick_search">
			<span>商品编号:<input type="text" id="id" name="id" /> </span> 
			<span>商品名称:<input type="text" id="name" name="name" /> </span> 
			<span>是否可用:
				<select id="status" name="status">
					<option value="">请选择</option>
					<option value="1">上架</option>
					<option value="2">下架</option>
				</select> 
			</span> 
			<span>商品类型:
				<select id="producttype" name="producttype">
					<option value="">请选择</option>
					<option value="10">照片</option>
					<option value="20">照片框</option>
					<option value="30">照片墙</option>
					<option value="40">相册</option>
					<option value="50">约拍</option>
				</select>
			</span><br /> 
		</div>
	</form>

	<!--对表单进行操作-->
	<div class="from_handle">
		<a href="#" style="display: none;"><b></b></a>
		<a href="javascript:toNavigation()"><b></b>发布商品</a>
		<a href="javascript:deleteProduct()"><b></b>删除商品</a>
		<a href="javascript:editProduct()"><b></b>编辑商品</a> 
		<a href="javascript:view()"><b></b>查看</a>
		<a href="javascript:void(0)" class="companyMakeModal" ><b></b>设置生产费用</a>
		<a href="javascript:void(0)" class="synchronizeproductfee" ><b></b>同步总店生产费用</a>
		<a href="javascript:productExcel()"><b></b>导出产品excel</a>
		<a href="javascript:querybytj()"><b></b>查询</a>
		<a href="javascript:void()"><b></b>重置</a>
	</div>
	<!-- 设置生产费用 -->
	<div class="modal hide fade" id="companyMakeModal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	  	<div class="modal-dialog" role="document">
	    	<div class="modal-content">
	      		<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="productName">产品名</h4>
			    </div>
			    <form id="" action="" method="post" class="form-horizontal">
			    <div class="modal-body">
					<input type="hidden" name="" class="" value="" /> 		
					<table id="companyMaketable" class="datatable">
						<thead></thead>
						<tbody></tbody>
					</table>
			    </div>				
			  	<div class="modal-footer">
			    	<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
			    	<button type="button" value="Validate" class="btn btn-primary" onclick="cost();">保存</button>
			  	</div>
			    </form>
			</div>
		</div>
	</div>
	<table id="cs_table" class="datatable"></table>
</body>
</html>
