
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
                            <input type="text" id="standardprice" name="standardprice" placeholder="请输入标准价" value="${product.standardprice}" onkeyup="clearNoNum(this)">
                        </div>
                    </div>
                    <div class="control-group span6">
                        <label class="control-label" for="shaper">页数</label>
                        <div class="controls">
                            <input type="text" id="pageNum" name="productinfo.pageNum" placeholder="请输入页数" value="${productinfo.pageNum}">
                        </div>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="control-group span6">
                        <label class="control-label" for="shaper">相册类型</label>
                        <div class="controls">
                            <select name="productinfo.type" id="type">
                                <option value="">请选择</option>
                                <option value="1" <c:if test="${productinfo.type eq 1}"> selected</c:if> >封面可DIY</option>
                                <option value="2" <c:if test="${productinfo.type eq 2}"> selected</c:if> >封面封底均可DIY</option>
                                <option value="3" <c:if test="${productinfo.type eq 3}"> selected</c:if> >封面封底均不可DIY</option>
                            </select>
                        </div>
                    </div>
                    <div class="control-group span6">
                        <label class="control-label" for="shaper">照片数（张）</label>
                        <div class="controls">
                            <input type="text" id="picNum" name="productinfo.picNum" placeholder="请输入照片数" onkeyup="clearNoNum(this)" value="${productinfo.picNum}">
                        </div>
                    </div>
                </div>
                <div class="row-fluid">

                    <div class="control-group span6">
                        <label class="control-label" for="standard_price">相册系列</label>
                        <div class="controls">
                            <select id="series" name="productinfo.series">
                                <option value="">请选择</option>
				                <option value="亲子系列" <c:if test="${productinfo.series eq '亲子系列'}">selected</c:if> >亲子系列</option>
				                <option value="爱情系列" <c:if test="${productinfo.series eq '爱情系列'}">selected</c:if> >爱情系列</option>
				                <option value="旅游系列" <c:if test="${productinfo.series eq '旅游系列'}">selected</c:if> >旅游系列</option>
				                <option value="闺蜜系列" <c:if test="${productinfo.series eq '闺蜜系列'}">selected</c:if> >闺蜜系列</option>
				                <option value="族系列" <c:if test="${productinfo.series eq '族系列'}">selected</c:if> >族系列</option>
				                <option value="家庭系列" <c:if test="${productinfo.series eq '家庭系列'}">selected</c:if> >家庭系列</option>
				                <option value="新品推荐" <c:if test="${productinfo.series eq '新品推荐'}">selected</c:if> >新品推荐</option>
				                <option value="校园系列" <c:if test="${productinfo.series eq '校园系列'}">selected</c:if> >校园系列</option>
				                <option value="婚纱系列" <c:if test="${productinfo.series eq '婚纱系列'}">selected</c:if> >婚纱系列</option>
				                <option value="动漫系列" <c:if test="${productinfo.series eq '动漫系列'}">selected</c:if> >动漫系列</option>
				                <option value="企业系列" <c:if test="${productinfo.series eq '企业系列'}">selected</c:if> >企业系列</option>
				                <option value="星座系列" <c:if test="${productinfo.series eq '星座系列'}">selected</c:if> >星座系列</option>
				                <option value="聚会系列" <c:if test="${productinfo.series eq '聚会系列'}">selected</c:if> >聚会系列</option>
				                <option value="相册系列" <c:if test="${productinfo.series eq '相册系列'}">selected</c:if> >相册系列</option>
				                <option value="Lomo卡系列" <c:if test="${productinfo.series eq 'Lomo卡系列'}">selected</c:if> >Lomo卡系列</option>
				                <option value="台历系列" <c:if test="${productinfo.series eq '台历系列'}">selected</c:if> >台历系列</option>
				                <option value="插页相册" <c:if test="${productinfo.series eq '插页相册'}">selected</c:if> >插页相册</option>
                            </select>
                        </div>
                    </div>
                    
                </div>
                <div class="row-fluid">

                    <div class="control-group span6">
                        <label class="control-label" for="standard_price">类型描述</label>
                        <div class="controls">
                            <input type="text" id="seriesDescribe" name="productinfo.seriesDescribe" placeholder="类型描述" value="${productinfo.seriesDescribe}">
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
                        <label class="control-label">商品缩略图</label>
                        <div class=" controls">
                            <label class="pic-sel pic-wrapper">
                                <img 
                                	<c:if test="${!empty specvalueList.get(0).thumbnail}">src="${imageViewDir}${specvalueList.get(0).thumbnail}"</c:if> 
                                	<c:if test="${empty specvalueList.get(0).thumbnail}">src="hanson/img/image_add.jpg"</c:if> 
                                	id="index_img" class="img-polaroid" >
                                <input type="file" class="hide" id="index_file_input" name="thumbnail"
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

				<!-- 商品模板图 -->
                <div class="row-fluid">
                    <div class="control-group span12">
                        <label class="control-label">商品模板图</label>
                        <div class="controls scroll_wrapper clearfix" id="template_scroll_wrapper">
							<c:forEach items="${templateImageList}" var="img" varStatus="status">
		                		<label class="pull-left pic-sel pic-wrapper">
									<img class="add-img" src="${imageViewDir}${img}">
									<input class="index_file_input hide" name="templateImage" accept="image/jpg,image/jpeg,image/png,image/bmp" type="file">
									<input type="hidden" name="templateImage" value="${img}">
									<span class="icon-close"></span>
								</label>
							</c:forEach>
                        </div>
                        <input type="hidden" name="templateImages">
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
                            <label class="span3" for="size">
                                尺寸
                                <select id="size">
                                    <option value="">未选择</option>
                                    <option value="6*6英寸">6*6英寸</option>
                                    <option value="6*8英寸">6*8英寸</option>
                                    <option value="7*5英寸">7*5英寸</option>
                                    <option value="8*6英寸">8*6英寸</option>
                                    <option value="8*8英寸">8*8英寸</option>
                                    <option value="8*10英寸">8*10英寸</option>
                                    <option value="10*10英寸">10*10英寸</option>
                                    <option value="12*10英寸">12*10英寸</option>
                                    <option value="2.5*3.5英寸">2.5*3.5英寸</option>
                                    <option value="银卡套餐">银卡套餐</option>
                                    <option value="金卡套餐">金卡套餐</option>
                                    <option value="钻石套餐">钻石套餐</option>
                                </select>
                            </label>
                            <button id="addItem" type="button" class="btn btn-success ">添加</button>
                        </div>
                        <table id="pro_table" class="table table-bordered">
                            <thead>
                            <tr>
                                <td>尺寸</td>
                                <td>商品价格</td>
                                <td>库存</td>
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
		                                        <option value="">未选择</option>
		                                        	<option value="6*6英寸" <c:if test="${specvalue.size eq '6*6英寸'}">selected</c:if> >6*6英寸</option>
		                                            <option value="6*8英寸" <c:if test="${specvalue.size eq '6*8英寸'}">selected</c:if> >6*8英寸</option>
		                                            <option value="7*5英寸" <c:if test="${specvalue.size eq '7*5英寸'}">selected</c:if> >7*5英寸</option>
		                                            <option value="8*6英寸" <c:if test="${specvalue.size eq '8*6英寸'}">selected</c:if> >8*6英寸</option>
		                                            <option value="8*8英寸" <c:if test="${specvalue.size eq '8*8英寸'}">selected</c:if> >8*8英寸</option>
		                                            <option value="8*10英寸" <c:if test="${specvalue.size eq '8*10英寸'}">selected</c:if> >8*10英寸</option>
		                                            <option value="10*10英寸" <c:if test="${specvalue.size eq '10*10英寸'}">selected</c:if> >10*10英寸</option>
		                                            <option value="12*10英寸" <c:if test="${specvalue.size eq '12*10英寸'}">selected</c:if> >12*10英寸</option>
		                                            <option value="2.5*3.5英寸" <c:if test="${specvalue.size eq '2.5*3.5英寸'}">selected</c:if> >2.5*3.5英寸</option>
		                                            <option value="银卡套餐" <c:if test="${specvalue.size eq '银卡套餐'}">selected</c:if> >银卡套餐</option>
		                                            <option value="金卡套餐" <c:if test="${specvalue.size eq '金卡套餐'}">selected</c:if> >金卡套餐</option>
		                                            <option value="钻石套餐" <c:if test="${specvalue.size eq '钻石套餐'}">selected</c:if> >钻石套餐</option>
		                                        </select>
		                                    </label>
		                                </td>
		                                <td>
		                                    <div class="input-prepend">
		                                        <span class="add-on">¥</span>
		                                        <input class="span2" id="" placeholder=".00" style="width: 100px;" step="0.01" name="price"  value="${specvalue.price}" onkeyup="clearNoNum(this)">
		                                    </div>
		                                </td>
		                                <td>
		                                    <div class="input-prepend">
		                                        <input class="span2" id="inventory" name="inventory" placeholder="0" style="width: 100px;" step="1" type="number" value="${specvalue.inventory}">
		                                    </div>
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
	<input type="hidden" id="templateImg" name="templateImg" value="">
	
	
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


<script src="hanson/js/hanson.xcfb_eidt.js"></script>

<div style="margin: 0 auto;  width: 160px">
<button type="button" class="btn btn-large btn-success text-center" onclick="saveEditProduct()" >保存</button>
</div>
</body>
</html>

