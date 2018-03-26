
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<!-- container-fluid -->
<head>
    <title>汇美微传即时管理系统</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script src="hanson/js/jquery.min.js"></script>
    <script src="hanson/js/bootstrap.min.js"></script>
    <script src="hanson/js/jquery.form.js"></script>
    <link rel="stylesheet" href="hanson/css/common.css">
    <style>
        .space {
            height: 50px;
        }

        #pro_table td {
            text-align: center;
        }

        #pro_table thead tr td {
            text-align: center;
            font-weight: 700 !important;
        }

        #pro_table tr input[type=text],
        #pro_table tr select {
            display: inline-block;
            padding: 0;
            min-width: 50px;
            max-width: 100px;
            width: 100%;
            text-align: center;
            height: 30px !important;
        }

        .input_error {
            border-color: #fe0000 !important;
        }


        .pic-wrapper{
            width: 200px;
            max-height: 200px;
            overflow: hidden;
        }
        .pic-sel {

            margin-right: 10px;
            position: relative;
        }

        #pro_content .file-sel{

        }
        #pro_content .operate{

        }

        .pic-sel:hover .icon-close {
            display: block;
        }

        .pic-sel img {
            width: 100%;
        }

        .pic-sel .icon-close {
            position: absolute;
            width: 30px;
            height: 30px;
            background-image: url("hanson/img/icons/close.png");
            background-repeat: no-repeat;
            background-size: cover;
            background-position: top left;
            right: 0;
            top: 0;
            border-radius: 10000px;
            display: none;
        }
    </style>

</head>
<body>


<div id="">


    <!--  基本信息 -->

    <form class="container-fluid form-horizontal" id="addProductForm" enctype="multipart/form-data" method="post"> 
        <input type="hidden" name="ID" value="${product.ID}">
        <input type="hidden" name="productinfo.id" value="${productinfo.id}">
        
        <div class="space"></div>
        <div class="row-fluid">
            <div class="span2 text-center">
                <h3 class="text-center">基本信息</h3>
            </div>
            <div class=" span10">
                <div class="row-fluid">
                    <div class="control-group span6">
                        <label class="control-label" for="pro_name">商品名称</label>
                        <div class="controls">
                            <input type="text" id="name" name="name" placeholder="请输入商品名称" value="${product.name}">
                        </div>
                    </div>
                    <div class="control-group span6">
                        <label class="control-label" for="is_publish">是否可用</label>
                        <div class="controls">
                            <select name="status" id="status">
                                <option value="">请选择</option>
                                <option value="1" <c:if test="${product.status eq 1}">selected</c:if>>上架</option>
                                <option value="2" <c:if test="${product.status eq 2}">selected</c:if>>下架</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="control-group span6">
                        <label class="control-label" for="brand">品牌</label>
                        <div class="controls">
                            <input type="text" id="brand" name="brand" placeholder="请输入品牌" value="${product.brand}">
                        </div>
                    </div>
                    <div class="control-group span6">
                        <label class="control-label" for="caizhi">材质</label>
                        <div class="controls">
                            <input type="text" id="material" name="productinfo.material" placeholder="请输入材质" value="${productinfo.material}">
                        </div>
                    </div>
                </div>
                <div class="row-fluid">

                    <div class="control-group span6">
                        <label class="control-label" for="standard_price">标准价</label>
                        <div class="controls">
                            <input type="text" id="standardprice" name="standardprice" placeholder="请输入标准价" value="${product.standardprice}">
                        </div>
                    </div>
                    <div class="control-group span6">
                        <label class="control-label" for="style">风格</label>
                        <div class="controls">
                            <input type="text" id="style" name="productinfo.style" placeholder="请输入产品风格" value="${productinfo.style}">
                        </div>
                    </div>
                </div>
                <div class="row-fluid">

                    <div class="control-group span6">
                        <label class="control-label" for="shaper">形状</label>
                        <div class="controls">
                            <select id="shape" name="productinfo.shape">
                                <option value="">请选择</option>
                                <option value="1" <c:if test="${productinfo.shape eq 1}">selected</c:if>>正方形</option>
                                <option value="2" <c:if test="${productinfo.shape eq 2}">selected</c:if>>长方形</option>
                            </select>
                        </div>
                    </div>
                </div>
                <!-- 商品主頁圖 -->
                <div class="row-fluid">
                    <div class="control-group span12">
                        <label class="control-label">商品主页图</label>
                        <div class=" controls">
                            <label for="index_file_input" class="pic-sel pic-wrapper">
                                <img 
                                	<c:if test="${!empty product.mainimg}">src="${imageViewDir}${product.mainimg}"</c:if> 
                                	<c:if test="${empty product.mainimg}">src="hanson/img/image_add.jpg"</c:if> 
                                	id="index_img" class="img-polaroid" >
                                	
                                <input type="file" class="hide" id="index_file_input" name="mainimg"
                                       accept="image/jpg,image/jpeg,image/png,image/bmp">
                            </label>
                        </div>
                    </div>
                </div>
                <!-- 商品輪播圖 -->
                <div class="row-fluid">
                    <div class="control-group span12">
                        <label class="control-label">商品轮播图</label>
                        <div class="controls scroll_wrapper clearfix" id="pro_scroll_wrapper">
							<c:forEach items="${scrollimgList}" var="img" varStatus="status">
		                		<label class="pull-left pic-sel pic-wrapper">
									<img class="add-img" src="${imageViewDir}${img}">
									<input class="index_file_input hide" name="scrollimg" accept="image/jpg,image/jpeg,image/png,image/bmp" type="file">
									<input type="hidden" name="scrollimg" value="${img}">
									<span class="icon-close"></span>
								</label>
							</c:forEach>
                        </div>
                        <input type="hidden" name="scrollimgs">
                    </div>
                </div>


            </div>
        </div>
        <div class="space"></div>

        <div class="row-fluid">
            <div class="span2 text-center">
                <h3 class="text-center">规格/库存</h3>
            </div>

            <div class="span10 ">
                <div class="row-fluid">
                    <div class="control-group span12">
                        <label class="control-label">商品规格</label>
                        <div class="controls row-fluid">
                            <label class="span3" for="color">
                                颜色
                                <input type="text" id="color" placeholder="请选择颜色">
                            </label>
                            <label class="span3" for="size">
                                尺寸
                                <select id="size">
                                    <option value="">未选择</option>
                                    <option value="6寸">4*6</option>
                                    <option value="7寸">5*7</option>
                                    <option value="8寸">6*8</option>
                                </select>
                            </label>
                            <button id="addItem" type="button" class="btn btn-success ">添加</button>
                        </div>
                        <table id="pro_table" class="table table-bordered">
                            <thead>
                            <tr>
                                <td>尺寸</td>
                                <td>颜色</td>
                                <td>商品价格</td>
                                <td>套餐价格</td>
                                <td>库存</td>
                                <td>缩略图</td>
                                <td>原图</td>
                                <td>原图高</td>
                                <td>原图宽</td>
                                <td>操作</td>
                            </tr>

                            </thead>
                            <tbody id="pro_content">
								<c:forEach items="${specvalueList}" var="specvalue">
									<tr>
										<input type="hidden" id="id" name="id" value="${specvalue.id}">
		                                <td>
		                                    <label>
		                                        <select id="size" name="size">
		                                            <option value="6寸" <c:if test="${specvalue.size eq '6寸'}">selected</c:if> >4*6</option>
		                                            <option value="7寸" <c:if test="${specvalue.size eq '7寸'}">selected</c:if> >5*7</option>
		                                            <option value="8寸" <c:if test="${specvalue.size eq '8寸'}">selected</c:if> >6*8</option>
		                                        </select>
		                                    </label>
		                                </td>
		                                <td>
		                                    <label>
		                                        <input value="${specvalue.color}" id="color" name="color" type="text">
		                                    </label>
		                                </td>
		                                <td>
		                                    <div class="input-prepend">
		                                        <span class="add-on">¥</span>
		                                        <input class="span2" id="" placeholder=".00" style="width: 100px;" step="0.01" name="price" type="number" value="${specvalue.price}">
		                                    </div>
		                                </td>
		                                <td>
		                                    <div class="input-prepend">
		                                        <span class="add-on">¥</span>
		                                        <input class="span2" placeholder=".00" style="width: 100px;" step="0.01" id="packageprice" name="packageprice" type="number" value="${specvalue.packageprice}">
		                                    </div>
		                                </td>
		                                <td>
		                                    <div class="input-prepend">
		                                        <input class="span2" id="inventory" name="inventory" placeholder="0" style="width: 100px;" step="1" type="number" value="${specvalue.inventory}">
		                                    </div>
		                                </td>
		                                <td class="thumbnail-block">
		                                    <label class="file-sel hide">
		                                        <span>点击选择缩原图</span>
		                                        <input class="hide originalPic" name="originalImg" type="file" data-url="${specvalue.original}">
		                                        <input type="hidden" name="original" value="${specvalue.original}">
		                                    </label>
		                                    <div class="operate" style="position: relative">
		                                    	<a href="javascripnt:;" class="btn btn-small preview" data-url="${imageViewDir}${specvalue.original}">预览</a>
		                                    	<a href="javascripnt:;" class="btn btn-small replace">替换</a>
		                                    	
											</div>
		                                </td>
		                                <td class="original-block">
		                                    <label class="file-sel hide">
		                                        <span>点击选择缩略图</span>
		                                        <input class="hide thumbnailPic" name="thumbnailImg" type="file" data-url="${specvalue.thumbnail}">
		                                        <input type="hidden" name="thumbnail" value="${specvalue.thumbnail}">
		                                    </label>
		                                    <div class="operate" style="position: relative">
		                                    	<a href="javascripnt:;" class="btn btn-small preview" data-url="${imageViewDir}${specvalue.thumbnail}">预览</a>
		                                    	<a href="javascripnt:;" class="btn btn-small replace">替换</a>
		                                    	
											</div>
		                                </td>
		                                <td>
		                                    <label><input id="height" name="height" value="${specvalue.height}" style="width: 100px;" type="number" ></label>
		                                </td>
		                                <td>
		                                    <label><input id="width" name="width" value="${specvalue.width}" style="width: 100px;" type="number" ></label>
		                                </td>
		                                <td><input type="button" value="删除" onclick="deleteRecord(this)"></td>
		                            </tr>
								</c:forEach>
                            </tbody>
                        </table>
                    </div>

                </div>

            </div>
        </div>
        <div class="space"></div>

        <div class="row-fluid">
            <div class="span2 text-center">
                <h3 class="text-center">参数/详情</h3>
            </div>
            <div class="span8" style="height: auto; width: 375px; overflow-y: scroll; padding-bottom: 200px;" id="detail_scroll_wrapper">
            	<c:if test="${!empty productinfo.img1}">
            		<label class="pic-sel">
                         <img src="${imageViewDir}${productinfo.img1}" class="add-img">
                         <input name="detialImg" class="index_file_input hide" accept="image/jpg,image/jpeg,image/png,image/bmp" type="file">
                         <input type="hidden" name="detailImg" value="${productinfo.img1}">
                     <span class="icon-close"></span></label>
            	</c:if>
            	<c:if test="${!empty productinfo.img2}">
            		<label class="pic-sel">
                         <img src="${imageViewDir}${productinfo.img2}" class="add-img">
                         <input name="detialImg" class="index_file_input hide" accept="image/jpg,image/jpeg,image/png,image/bmp" type="file">
                         <input type="hidden" name="detailImg" value="${productinfo.img2}">
                     <span class="icon-close"></span></label>
            	</c:if>
            	<c:if test="${!empty productinfo.img3}">
            		<label class="pic-sel">
                         <img src="${imageViewDir}${productinfo.img3}" class="add-img">
                         <input name="detialImg" class="index_file_input hide" accept="image/jpg,image/jpeg,image/png,image/bmp" type="file">
                         <input type="hidden" name="detailImg" value="${productinfo.img3}">
                     <span class="icon-close"></span></label>
            	</c:if>
            	<c:if test="${!empty productinfo.img4}">
            		<label class="pic-sel">
                         <img src="${imageViewDir}${productinfo.img4}" class="add-img">
                         <input name="detialImg" class="index_file_input hide" accept="image/jpg,image/jpeg,image/png,image/bmp" type="file">
                         <input type="hidden" name="detailImg" value="${productinfo.img4}">
                     <span class="icon-close"></span></label>
            	</c:if>
            	<c:if test="${!empty productinfo.img5}">
            		<label class="pic-sel">
                         <img src="${imageViewDir}${productinfo.img5}" class="add-img">
                         <input name="detialImg" class="index_file_input hide" accept="image/jpg,image/jpeg,image/png,image/bmp" type="file">
                         <input type="hidden" name="detailImg" value="${productinfo.img5}">
                     <span class="icon-close"></span></label>
            	</c:if>
            </div>
            <!--<div class="span2">-->
                <!--<label class="btn btn-success btn-large" id="detail_input_wrapper">-->
                    <!--插入新的图片-->
                    <!--&lt;!&ndash;<input type="file" class="hide" accept="image/jpg,image/jpeg,image/png,image/bmp"&ndash;&gt;-->
                           <!--&lt;!&ndash;id="pro_detail_input">&ndash;&gt;-->
                <!--</label>-->
            <!--</div>-->


        </div>

	<input type="hidden" id="lunboImage" name="lunboImage" value="">
	<input type="hidden" id="detailImg" name="detailImg" value="">
	
	
    </form>
</div>

<!-- Modal -->
<div id="picModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <h3 id="myModalLabel">请看图片</h3>
    </div>
    <div class="modal-body">
        <p>
            <img src="hanson/img/back.png" alt="" id="picShow">
        </p>
    </div>
</div>


<script src="hanson/js/hanson.xkfb_view.js"></script>

<div>
</div>
</body>
</html>

