<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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
	<script src="hanson/js/table.js" type="text/javascript"></script>
	<script src="hanson/js/hanson.roles.js" type="text/javascript"></script>
	<script src="hanson/js/jquery.validate.js"></script>
	<script src="hanson/js/toastr.js"></script>
	<script src="hanson/js/toastr.setting.js"></script>
	<script src="hanson/js/bootstrap-datepicker.js"></script>
	<script src="hanson/js/select2.min.js"></script>
  </head>
  
  <body>
    <!--快速查询-->
	<div class="quick_search">
		<form action="" id="rolesSearchForm">
			<span>名称:<input id="name_s" name="name" type="text" class = "cuname"/></span>
		</form>
	</div>
	<!--对表单进行操作-->
	<div class="from_handle">
		<a href="#myModal" role="button"  data-toggle="modal" ><b></b>录入</a>
		<a href="javascript:void(0)" class="edit_btn"><b></b>编辑</a>
		<a href="javascript:void(0)" class="make_btn"><b></b>查看</a>
		<a href="javascript:void(0)" class="delete_btn"><b></b>删除</a>
		<a href="javascript:void(0)" class="setAuthorities_btn"><b></b>权限设置</a>
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
		    <form id="rolesInputForm" method="post" class="form-horizontal">
		    <div class="modal-body">
		    	<input type="hidden" id="id" name="id" />
				<div class="control-group" id="name_div">
					<label class="control-label" for="name">名称</label>
					<div class="controls">
					    <input type="text" id="name" name="name" placeholder="名称">
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

<!-- 权限弹出框 -->
<form id="rolesAuthoritiesForm">
<div class="modal hide fade" id="myQxModal" role="dialog" tabindex="-1" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<button class="allChoose">全选</button>
			<button class="noChoose">重置</button>
			<button class="saveChoose">保存</button>
			<input type="hidden" id="id" name="id" />
			
			<ul id="functionalAuthority">
			<c:forEach items="${authoritiesList}" var="authorities">
				<li>
					<div><input type="checkbox" value="${authorities.id}" /><span>${authorities.display_name}</span></div>
					<ul>
						<c:forEach items="${authorities.authoritiesList}" var="item">
							<li><input type="checkbox" value="${item.id}"/><span>${item.display_name }</span></li>
						</c:forEach>
					</ul>
				</li>
			
			</c:forEach>
		</ul>
		</div>
	</div>
</div>
</form>


  </body>
</html>
