
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<!-- container-fluid -->
<head>
    <title>汇美微传即时管理系统</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="hanson/css/table.css" />
    <link rel="stylesheet" href="hanson/css/common.css" />

<style>
        .toast-title{
            font-size: 50px;
            text-align: center;
            margin-top: 100px;
        }
        .category-list .item {
            box-sizing: border-box;
            float: left;
            padding: 30px;
            width: 25%;
        }

        .category-list .item a {
            display: block;
            border: 1px solid #e1e1e8;
            line-height: 100px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 30px;
            font-weight: 500;
            color: #333;
            text-align: center;
        }

        .category-list .item a:hover {
            border-color: #a2a2a2;
            background-color: #a2a2a2;
            color: #fff;
        }
    </style>
</head>
<body>

    


    <div class="choose_publish_category container">
        <div class="h2 toast-title ">请选择要发布的商品种类</div>

        <div class="category-list text-center">
            <div class="" >
                <div class="item">
                    <a href="">照片冲印</a>
                </div>
                <div class="item ">
                    <a id="photoFrame" style="cursor: pointer;">相框摆台</a>
                </div>
                <div class="item ">
                    <a id="ablumFrame" style="cursor: pointer;">相册/照片书</a>
                </div>
                <div class="item ">
                    <a id="yuepai"  style="cursor: pointer;">约拍</a>
                </div>
                <div class="item ">
                    <a href="">工牌</a>
                </div>
                <div class="item ">
                    <a href="">日历</a>
                </div>
                <div class="item ">
                    <a href="">照片墙</a>
                </div>
                <div class="item ">
                    <a href="">名片</a>
                </div>
            </div>
        </div>
    </div>


</div>
</body>
<script type="text/javascript">
$("#photoFrame").click(function(e){
	$("#content").empty();
	$("#content").load("shopping/toAddProduct");
});
$("#ablumFrame").click(function(e){
	$("#content").empty();
	$("#content").load("shopping/toAblumFrame");
});
$("#yuepai").click(function(e){
	$("#content").empty();
	$("#content").load("shopping/yuepaiFrame");
});
</script>

</html>

