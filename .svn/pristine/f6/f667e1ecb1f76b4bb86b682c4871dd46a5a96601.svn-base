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
    <link rel="stylesheet" type="text/css" href="hanson/css/table.css"/>
	<link rel="stylesheet" href="hanson/css/common.css" />

  </head>
  
  <body>
   <!--快速查询-->
<div class="quick_search">
	<form id="quick_search_from">
		<span>昵称:<input id="nickname" name="nickname" type="text" class = "cuname"/></span>
	</form>
</div>
<!--对表单进行操作-->
<div class="from_handle">
	<em>&nbsp;</em>
	<a href="javascript:querybytj()"><b></b>查询</a>
	<a href="javascript:reset()"><b></b>重置</a>
</div>
<!--表单-->
<table id="cs_table" class="datatable"></table>
<script src="hanson/js/table.ajax.js" type="text/javascript" charset="utf-8"></script>
<script>
	//页面初始化
	var headers = [{title:"ID",code:"id"},
					 {title:"昵称",code:"nickname"},
					 {title:"性别",code:"sex"},
					 {title:"手机",code:"mobile"},
					 {title:"邮箱",code:"email"}];
		
	
	var cs = new table({
		"cache":true,    //必须
 		"url":"user/userList",
        "tableId":"cs_table",    //必须
        "headers":headers,   //必须
        "currentPageNum":1,
        "displayNum": 20,    //必须   默认 10
        "groupDataNum":9    //可选    默认 10
	});	
	
	//查询
	function querybytj(){
		cs.paramData = $("#quick_search_from").serializeArray();
		cs.init(0, cs.displayNum);
	}
	
	//重置
	function reset(){
		$("#nickname").val("");
	}
</script>
  </body>
</html>
