
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
                            <input type="text" id="name" name="name" placeholder="请输入商品名称">
                        </div>
                    </div>
                    <div class="control-group span6">
                        <label class="control-label" for="is_publish">是否可用</label>
                        <div class="controls">
                            <select name="status" id="status">
                                <option value="">请选择</option>
                                <option value="1">上架</option>
                                <option value="2">下架</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="control-group span6">
                        <label class="control-label" for="brand">品牌</label>
                        <div class="controls">
                            <input type="text" id="brand" name="brand" placeholder="请输入品牌">
                        </div>
                    </div>
                    <div class="control-group span6">
                        <label class="control-label" for="caizhi">材质</label>
                        <div class="controls">
                            <input type="text" id="material" name="productinfo.material" placeholder="请输入材质">
                        </div>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="control-group span6">
                        <label class="control-label" for="shaper">标准价</label>
                        <div class="controls">
                            <input type="text" id="standardprice" name="standardprice" placeholder="请输入标准价" onkeyup="clearNoNum(this)">
                        </div>
                    </div>
                    <div class="control-group span6">
                        <label class="control-label" for="shaper">页数</label>
                        <div class="controls">
                            <input type="text" id="pageNum" name="productinfo.pageNum" placeholder="请输入页数">
                        </div>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="control-group span6">
                        <label class="control-label" for="shaper">相册类型</label>
                        <div class="controls">
                            <select name="productinfo.type" id="type">
                                <option value="">请选择</option>
                                <option value="1">封面可DIY</option>
                                <option value="2">封面封底均可DIY</option>
                                <option value="3">封面封底均不可DIY</option>
                            </select>
                        </div>
                    </div>
                    <div class="control-group span6">
                        <label class="control-label" for="shaper">照片数（张）</label>
                        <div class="controls">
                            <input type="text" id="picNum" name="productinfo.picNum" placeholder="请输入照片数" onkeyup="clearNoNum(this)">
                        </div>
                    </div>
                </div>
                <div class="row-fluid">

                    <div class="control-group span6">
                        <label class="control-label" for="standard_price">相册系列</label>
                        <div class="controls">
                            <select id="series" name="productinfo.series">
                                <option value="">请选择</option>
				                <option value="亲子系列">亲子系列</option>
				                <option value="爱情系列">爱情系列</option>
				                <option value="旅游系列">旅游系列</option>
				                <option value="闺蜜系列">闺蜜系列</option>
				                <option value="族系列">族系列</option>
				                <option value="家庭系列">家庭系列</option>
				                <option value="新品推荐">新品推荐</option>
				                <option value="校园系列">校园系列</option>
				                <option value="婚纱系列">婚纱系列</option>
				                <option value="动漫系列">动漫系列</option>
				                <option value="企业系列">企业系列</option>
				                <option value="星座系列">星座系列</option>
				                <option value="聚会系列">聚会系列</option>
				                <option value="相册系列">相册系列</option>
				                <option value="Lomo卡系列">Lomo卡系列</option>
				                <option value="台历系列">台历系列</option>
				                <option value="插页相册">插页相册</option>
                            </select>
                        </div>
                    </div>
                    
                </div>
                <div class="row-fluid">

                    <div class="control-group span6">
                        <label class="control-label" for="standard_price">类型描述</label>
                        <div class="controls">
                            <input type="text" id="seriesDescribe" name="productinfo.seriesDescribe" placeholder="类型描述">
                        </div>
                    </div>
                </div>
                
                <!-- 商品主頁圖 -->
                <div class="row-fluid">
                    <div class="control-group span12">
                        <label class="control-label">商品主页图</label>
                        <div class=" controls">
                            <label class="pic-sel pic-wrapper" >
                                <img src="hanson/img/image_add.jpg" id="index_img" class="img-polaroid">
                                <input type="file" class="hide"  id="index_file_input" name="mainimg"
                                       accept="image/jpg,image/jpeg,image/png,image/bmp">
                            </label>
                        </div>
                        <label class="control-label">商品缩略图</label>
                        <div class=" controls">
                            <label class="pic-sel pic-wrapper">
                                <img src="hanson/img/image_add.jpg" id="index_img" class="img-polaroid">
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

                        </div>
                    </div>
                </div>
                
                <!-- 商品模板图 -->
                <div class="row-fluid">
                    <div class="control-group span13">
                        <label class="control-label">商品模板图</label>
                        <div class="controls scroll_wrapper clearfix" id="pro_scroll_wrapper1">
							
                        </div>
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
                                <td>编辑</td>
                            </tr>

                            </thead>
                            <tbody id="pro_content">

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

            </div>
            <!--<div class="span2">-->
                <!--<label class="btn btn-success btn-large" id="detail_input_wrapper">-->
                    <!--插入新的图片-->
                    <!--&lt;!&ndash;<input type="file" class="hide" accept="image/jpg,image/jpeg,image/png,image/bmp"&ndash;&gt;-->
                           <!--&lt;!&ndash;id="pro_detail_input">&ndash;&gt;-->
                <!--</label>-->
            <!--</div>-->


        </div>

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

<script src="hanson/js/hanson.xcfb.js"></script>

<div>
<div style="margin: 0 auto;  width: 160px">
<button type="button" class="btn btn-large btn-success text-center" onclick="saveProduct()" >保存</button>
</div>
</div>
</body>
</html>

