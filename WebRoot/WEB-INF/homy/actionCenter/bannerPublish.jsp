<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新建banner</title>
		<link rel="stylesheet" href="hanson/css/base.css" />
		<link rel="stylesheet" href="hanson/css/swipeslider.css" />
		<link rel="stylesheet" href="hanson/css/toastr.css" />
		<style>
			.banner{width:625px;margin:0px auto;padding-top:20px;}
			.bannerText{
				position: absolute;
			    bottom: 0px;
			    left: 0px;
			    width: 100%;
			    height: 50px;
			    background-color: #ddd;
			    z-index: 999;
			    opacity: 0.5;
			}
			
			.a-upload {
			    padding: 4px 10px;
			    height: 20px;
			    line-height: 20px;
			    position: relative;
			    cursor: pointer;
			    color: #888;
			    background: #fafafa;
			    border: 1px solid #ddd;
			    border-radius: 4px;
			    overflow: hidden;
			    display: inline-block;
			    *display: inline;
			    *zoom: 1
			}
			.a-upload  input {
			    position: absolute;
			    font-size: 100px;
			    right: 0;
			    top: 0;
			    opacity: 0;
			    filter: alpha(opacity=0);
			    cursor: pointer
			}
			.a-upload:hover {
			    color: #444;
			    background: #eee;
			    border-color: #ccc;
			    text-decoration: none
			}
			.clearAll{width:800px;margin:0 auto;min-height:250px;border:1px solid #ddd;position:relative;margin-bottom:20px;}
			.flDiv{width:350px;padding:10px;}
			.frDiv{width:50%;text-align:center;}
			.flDiv select,.flDiv textarea{border:1px solid #ddd;background-color:#fff;text-align:left;}
			.fz12{font-size:12px;color:#9D8E95;padding-left:10px;}
			#isgroom{margin-right: 5px;margin-top: -1px;}
			.tijiao{margin-top:10px;border:1px solid #ddd;padding: 5px 20px;border-radius: 5px;background-color: #00A4FF;color: #fff;}
			.frDiv img{margin:20px;}
			.deleteX{position:absolute;right:5px;top:5px;width:25px;height:25px;line-height:25px;background-color:#000;color:#fff;text-align:center;border-radius:50%;cursor:pointer;}
			.addBanner{width:800px;margin:0 auto;border:1px solid #ddd;height:30px;line-height:30px;padding-left:50px;box-sizing: border-box;cursor:pointer;user-select: none;}
			.disnone{display:none;}
			.bannerlist{position: absolute;bottom: -100px;left: -24px;background-color: #fff;width: 62%;height: 100px;overflow-y: scroll;border: 1px solid #ddd;display: none;}
			.bannerlist li {
			    text-align: center;
			    height: 30px;
			    line-height: 30px;
			}
			.bannerlist li:hover {
			    background-color: #21C6F2;
			    color: #fff;
			}
		</style>
		<!-- 消息提醒 -->
		<script src="hanson/js/jquery.validate.js"></script>
		<script src="hanson/js/select2.min.js"></script>
		<!-- 消息提醒 -->
		<script src="hanson/js/toastr.js"></script>
		<script src="hanson/js/toastr.setting.js"></script>
		
		<!-- 省市区控件 -->
		<script src="hanson/js/provincial.data.js"></script>
		<script src="hanson/js/provincial.select.js"></script>
		<!-- 表格控件 -->
		<script src="hanson/js/table.ajax.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="hanson/js/jquery.form.js"></script>
		<script src="hanson/js/hanson.bannerPublish.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
	
    	<form action="actionCenter/savePublishBanners"  id="addbanner" enctype="multipart/form-data" method="post" >
     		<div class="setBanner">
     		
    		</div>  
    		<div class="addBanner">添加推荐banner内容</div>
    		<div style="text-align:center;"><button class="tijiao">确认</button></div>
    	</form>
	</body>
	<script>
		$.ajax({
			type : "get",
			url : "/actionCenter/getAllbanners",
			success :function(data){
				console.log(data);
				$(".addBanner").attr("data",data.rows.length);
				$(".setBanner").empty();
				$.each(data.rows,function(i,v){
					add(i+1,v);
				});
			}
		});
	</script>
</html>