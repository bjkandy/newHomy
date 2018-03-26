<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="hanson/css/table.css"/>
		<link rel="stylesheet" href="hanson/css/common.css" />
		<link rel="stylesheet" href="hanson/css/datepicker.css" />
		<link rel="stylesheet" href="hanson/css/select2.css" />
		<link rel="stylesheet" href="hanson/css/toastr.css" />
		
		<!-- js加载 -->
        <script src="hanson/js/table.js" type="text/javascript" charset="utf-8"></script>
        <script src="hanson/js/jquery.validate.js"></script>
		<script src="hanson/js/hanson.item.js" type="text/javascript" charset="utf-8"></script>
		<script src="hanson/js/toastr.js"></script>
		<script src="hanson/js/toastr.setting.js"></script>
		<script src="hanson/js/bootstrap-datepicker.js"></script>
		<script src="hanson/js/select2.min.js"></script>
        
        <style type="text/css">
       		td,th{
       			width: 200px;
       			height: 50px;
       		} 	
       		table{
       			text-align: center;
       		}
       		.left_td td:first-child{
       			text-align: left;
       		}
       		tr.active td{background-color: #F0AD4E;}
        </style>	
	</head>
	<body>
	<!--对表单进行操作-->
	<div class="from_handle">
		<a href="javascript:void(0)" class="add_btn" role="button"  data-toggle="modal" ><b></b>录入</a>
		<a href="javascript:void(0)" class="edit_btn"><b></b>编辑</a>
		<a href="javascript:void(0)" class="make_btn"><b></b>查看</a>
		<a href="javascript:void(0)" class="delete_btn"><b></b>删除</a>
		<a href="javascript:void(0)">&nbsp;</a>
		<a href="javascript:void(0)">&nbsp;</a>
	</div>
		<table border="1" cellpadding="0" cellspacing="0" id="cs_table">
			<thead>
				<tr>
					<th>权限名称</th>
					<th>权限编码</th>
					<th>权限类型</th>
					<th>图标类型</th>
					<th>访问地址</th>
					<th>显示排序</th>
					<th>权限排序</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${authoritiesList}" var="authorities">
					<tr class="left_td" itemId = "${authorities.id}">
						<td>${authorities.display_name}</td>
						<td>${authorities.name}</td>
						<td>${authorities.authtype}</td>
						<td>${authorities.iconcls}</td>
						<td>${authorities.request}</td>
						<td>${authorities.sortno}</td>
						<td>${authorities.id}</td>
						<td>${authorities.remark}</td>
					</tr>
					<c:if test="${authorities.authoritiesList.size() gt 0}">
						<tr>
							<td colspan="9">
								<div>
									<table border="1" cellpadding="0" cellspacing="0" style="text-align: center;">
										<tbody>
											<c:forEach items="${authorities.authoritiesList}" var="item">
												<tr itemId = "${item.id}">
													<td>${item.display_name}</td>
													<td>${item.name}</td>
													<td>${item.authtype}</td>
													<td>${item.iconcls}</td>
													<td>${item.request}</td>
													<td>${item.sortno}</td>
													<td>${item.authno}</td>
													<td>${item.remark}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</c:if>
					
				</c:forEach>
			</tbody>
		</table>
		
<!--录入-->
<div class="modal hide fade" id="myModal" role="dialog" tabindex="-1" aria-labelledby="gridSystemModalLabel">
  	<div class="modal-dialog" role="document">
    	<div class="modal-content">
      		<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">新增权限</h4>
		    </div>
		    <form id="authoritiesInputForm" action="system/saveUsers" method="post" class="form-horizontal">
		    <div class="modal-body">
		    	<input type="hidden" id="id" name="id" />
				<div class="control-group">
					<label class="control-label" for="name">权限编码</label>
					<div class="controls">
					    <input type="text" id="name" name="name" placeholder="权限编码">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="display_name">权限名称</label>
					<div class="controls">
					    <input type="text" id="display_name" name="display_name" placeholder="权限名称">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="parentid">上级权限</label>
					<div class="controls">
					    <select id="parentid" name="parentid" placeholder="上级权限">
					    	<option value="0">请选择</option>
						    <c:forEach items="${authoritiesRootList}" var="authority">
						    	<option value="${authority.id}">${authority.display_name}</option>
						    </c:forEach>
					    </select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="authtype">权限类型</label>
					<div class="controls">
					    <select id="authtype" name="authtype" placeholder="权限类型">
					    	<option value="">请选择</option>
					    	<c:forEach items="${parameterList}" var="parameter">
					    		<option value="${parameter.pmcode}">${parameter.pmname}</option>
					    	</c:forEach>
					    </select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="request">访问地址</label>
					<div class="controls">
					    <input type="text" id="request" name="request" placeholder="访问地址">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="sortno">显示排序</label>
					<div class="controls">
					    <input type="text" id="sortno" name="sortno" placeholder="显示排序">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="authno">权限排序</label>
					<div class="controls">
					    <input type="text" id="authno" name="authno" placeholder="权限排序">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="iconcls">图标类型</label>
					<div class="controls">
					    <input type="text" id="iconcls" name="iconcls" placeholder="图标类型">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="remark">备注</label>
					<div class="controls">
					    <input type="text" id="remark" name="remark" placeholder="备注">
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

