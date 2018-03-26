<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <!-- css文件加载 -->
    <link rel="stylesheet" type="text/css" href="hanson/css/table.css"/>
	<link rel="stylesheet" href="hanson/css/common.css" />
	<link rel="stylesheet" href="hanson/css/datepicker.css" />
	<link rel="stylesheet" href="hanson/css/select2.css" />
	<link rel="stylesheet" href="hanson/css/toastr.css" />
	
	<!-- js加载 -->
	<script src="hanson/js/table.ajax.js" type="text/javascript" charset="utf-8"></script>
	<script src="hanson/js/jquery.validate.js"></script>
	<script src="hanson/js/hanson.users.js" type="text/javascript" charset="utf-8"></script>
	<script src="hanson/js/toastr.js"></script>
	<script src="hanson/js/toastr.setting.js"></script>
	<script src="hanson/js/bootstrap-datepicker.js"></script>
	<script src="hanson/js/select2.min.js"></script>
  </head>
  
  <body>
   <!--快速查询-->
<div class="quick_search">
	<form action="" id="usersSearchForm">
		<span>登录名:<input id="login_name_s" name="login_name" type="text" class = "cuname"/></span>
		<span>名称:<input id="name_s" name="name" type="text" class = "cuname"/></span>
		<span>邮箱:<input id="email_s" name="email" type="text" class = "cuname"/></span>
	</form>
	
</div>
<!--对表单进行操作-->
<div class="from_handle">
	<a href="#myModal" role="button"  data-toggle="modal" ><b></b>录入</a>
	<a href="javascript:void(0)" class="edit_btn"><b></b>编辑</a>
	<a href="javascript:void(0)" cla
	ss="make_btn"><b></b>查看</a>
	<a href="javascript:void(0)" class="repassword_btn"><b></b>重置密码</a>
	<a href="javascript:querybytj()"><b></b>查询</a>
	<a href="javascript:reset()"><b></b>重置</a>
</div>
<!--表单-->
<div class="widget-box" style="overflow: auto;">
<table id="cs_table" class="datatable"></table>
</div>



<!--模态-录入-->
<div class="modal hide fade" id="myModal" role="dialog" tabindex="-1" aria-labelledby="gridSystemModalLabel">
  	<div class="modal-dialog" role="document">
    	<div class="modal-content">
      		<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">新增用户</h4>
		    </div>
		    <form id="usersInputForm" action="system/saveUsers" method="post" class="form-horizontal">
		    <div class="modal-body">
		    	<input type="hidden" id="id" name="id" />
				<div class="control-group" id="login_name_div">
					<label class="control-label" for="login_name">登录名</label>
					<div class="controls">
					    <input type="text" id="login_name" name="login_name" placeholder="登录名">
					</div>
				</div>
				<div class="control-group" id="password_div">
					<label class="control-label" for="password">输入密码</label>
					<div class="controls">
					    <input type="password" id="password" name="password" placeholder="输入密码">
					</div>
				</div>
				<div class="control-group" id="passwordR_div">
					<label class="control-label" for="passwordR">重复密码</label>
					<div class="controls">
					    <input type="password" id="passwordR" name="passwordR" placeholder="重复密码">
					</div>
				</div>
				
				<div class="control-group" id="name_div">
					<label class="control-label" for="name">名称</label>
					<div class="controls">
					    <input type="text" id="name" name="name"  placeholder="名称">
					</div>
				</div>
				<div class="control-group" id="email_div">
					<label class="control-label" for="email">邮箱</label>
					<div class="controls">
					    <input type="text" id="email" name="email" placeholder="邮箱">
					</div>
				</div>
				<div class="control-group" id="qyid_div">
					<label class="control-label" for="qyid">企业ID</label>
					<div class="controls">
					    <input type="text" id="qyid" name="qyid" placeholder="企业ID">
					</div>
				</div>
				<div class="control-group" id="usertype_div">
					<label class="control-label" for="usertype">角色</label>
					<div class="controls">
						<select id="usertype" name="usertype" placeholder="角色">
					    	<option value="">请选择</option>
					    	<option value="1">管理员</option>
					    	<option value="2">一般用户</option>
					    	<option value="6">店家录入</option>
					    	<option value="7">超级管理员</option>
					    	<option value="8">代理商</option>
					    	<option value="9">商户</option>
					    	<option value="10">约拍</option>
					    	<option value="12">it部测试</option>
					    </select>
					</div>
				</div>
		    </div>				
		  	<div class="modal-footer">
		    	<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		    	<button type="button" value="Validate" class="btn btn-primary" onclick="check_form()" isEdit = "0" id="saveButton">保存</button>
		  	</div>
		    </form>
		</div>
	</div>
</div>

  </body>
</html>
