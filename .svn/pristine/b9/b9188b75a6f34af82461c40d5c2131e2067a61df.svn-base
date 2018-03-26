<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>发布活动</title>
		<link rel="stylesheet" href="hanson/css/base.css" />
		<link rel="stylesheet" href="hanson/css/toastr.css" />
		<style>
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
			    right: -50px;
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
			.h4{
				padding-left: 20px;
			    padding-top: 20px;
			    font-weight: normal;
			}
			.clearAll{overflow:hidden;}
			.flDiv{width:350px;padding:10px;}
			.frDiv{width:50%;}
			.flDiv select,.flDiv textarea{border:1px solid #ddd;background-color:#fff;text-align:left;}
			.fz12{font-size:12px;color:#9D8E95;padding-left:10px;}
			#isgroom{margin-right: 5px;margin-top: -1px;}
			.tijiao{position:relative;left:360px;margin-top:10px;border:1px solid #ddd;padding: 5px 20px;border-radius: 5px;background-color: #00A4FF;color: #fff;}
		</style>
		<!-- 消息提醒 -->
		<script src="hanson/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="hanson/js/jquery.form.js"></script>
		<script src="hanson/js/toastr.js"></script>
		<script src="hanson/js/toastr.setting.js"></script>
		<script src="hanson/js/table.ajax.js" type="text/javascript" charset="utf-8"></script>
	 	<script src="hanson/js/hanson.newBanner.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<h4 class="h4">新建商户通知</h4>
		<form  action="actionCenter/saveactivity"  id="addstoreactivity" enctype="multipart/form-data" method="post">
		<div class="clearAll">
			<div class="fl flDiv">
				<div>*通知类型<br/>
					<select name="announcetype" id="announcetype">
						<option value="0">请选择</option>
						<option value="1">最近活动</option>
				    	<option value="2">功能上线</option>
					</select>
				</div>
				<div>*标题<span class="fz12">不超过20个汉字</span>
					<textarea rows="3" cols="20" id="title" name="title" placeholder="请输入通知标题"></textarea>
				</div>
				<div>发布作者<span class="fz12">不超过8个汉字</span>
					<input type="text" id="createUser" name="createUser" placeholder="选填，如果不填默认为汇美影像" value=""/>
				</div>
				<div>设置推荐<span class="fz12">置顶推荐消息</span><br/>
					<label for="isgroom"><input type="checkbox" id="recommend" name="recommend" value="1"/>设为推荐</label>
				</div>
			</div>
			<div class="fl frDiv">
				<div>
					<span class="fz12">*上传通告内容，内容通告格式为图片，暂不支持其他格式文档，图片大小控制在200KB以内。</span><br>
					<a href="javascript:;" class="a-upload">
					    <input type="file" name="picture" required="required"  id="file" onchange="changeToop();">点击这里上传文件
					</a><br>
					<img id="myimg" src="http://bpic.588ku.com/element_pic/01/47/03/35574339ab3c813.jpg"/>
				</div>
			</div>
		</div>
		 <button class="tijiao" >确认</button>
		</form>
	</body>
</html>