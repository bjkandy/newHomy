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
		<script src="hanson/js/hanson.releaseActivity.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
<!-- 		<div class="banner">
    		<figure id="full_feature" class="swipslider">
				<ul class="sw-slides">
					<li class="sw-slide">
						<img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514539115088&di=c8924777e069db8057421c371c4d32e5&imgtype=0&src=http%3A%2F%2Fwww.zhlzw.com%2FUploadFiles%2FArticle_UploadFiles%2F201204%2F20120422013451522.JPG">
					</li>
				</ul>
			</figure>
    	</div> -->
    	<form action="actionCenter/saveBanner"  id="addbanner" enctype="multipart/form-data" method="post" >
     		<div class="setBanner">
				 <!--<div class="clearAll">
					<div class="fl frDiv">
						<div>
							<img id="myimg" width="250" src="http://bpic.588ku.com/element_pic/01/47/03/35574339ab3c813.jpg"/><br>
							<a href="javascript:;" class="a-upload">
							    <input type="file" name="bannerurl" id="file" onchange="changeToop(this);">点击这里上传文件
							</a>
						</div>
					</div>
					<div class="fl flDiv">
						<div>*广告摘要<span class="fz12">不超过20个汉字</span>
							<textarea name="introduce" rows="3" cols="20" placeholder="请输入摘要内容"></textarea>
						</div>
						<div style="position:relative">*链接<span class="fz12">设置到链接地址</span>
							<input type="hidden" id="" name="inputid" value="">
							<input type="text" id="inputxt" name="inputxt" value=""  class="cc">
							<ul class="bannerlist"></ul>
						</div>
					</div>
					<span class="deleteX">X</span>
				</div>-->
    		</div>  
    		<div class="addBanner">添加推荐banner内容</div>
    		<div style="text-align:center;"><button class="tijiao">确认</button></div>
    	</form>
	</body>
	<script>
		$.ajax({
			type : "get",
			url : "/actionCenter/bannerlist",
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