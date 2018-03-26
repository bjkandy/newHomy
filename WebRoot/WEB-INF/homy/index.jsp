<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
	<!-- container-fluid -->
	<head>
		<base href="<%=basePath%>">
		<title>汇美微传即时管理系统</title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="hanson/css/bootstrap.min.css" />
		<link rel="stylesheet" href="hanson/css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="hanson/css/fullcalendar.css" />	
		<link rel="stylesheet" href="hanson/css/uniform.css" />
		<link rel="stylesheet" href="hanson/css/select2.css" />	
		<link rel="stylesheet" href="hanson/css/unicorn.main.css" />
		<link rel="stylesheet" href="hanson/css/unicorn.grey.css" class="skin-color" />
        <script src="hanson/js/excanvas.min.js"></script>
        <script src="hanson/js/jquery.min.js"></script>
        <script src="hanson/js/jquery.ui.custom.js"></script>
        <script src="hanson/js/bootstrap.min.js"></script>
        <script src="hanson/js/jquery.flot.min.js"></script>
        <script src="hanson/js/jquery.flot.resize.min.js"></script>
        <script src="hanson/js/jquery.peity.min.js"></script>
        <script src="hanson/js/fullcalendar.min.js"></script>
        <script src="hanson/js/unicorn.js"></script>
        <script src="hanson/js/unicorn.dashboard.js"></script>
        <!-- 百度地图api -->
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=3zGWCiDQyY1CQ1sCHvM2Vn7lpB4a0CzB"></script>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>	
		<div id="header">
			<h1 style="font-size: 18px;color: #fff;background:none;line-height: 30px;">汇美微传即时管理系统</h1>		
		</div>
		<!-- 搜索 -->
		<!-- <div id="search">
			<input type="text" placeholder="Search here..." /><button type="submit" class="tip-right" title="Search"><i class="icon-search icon-white"></i></button>
		</div> -->
		<div id="user-nav" class="navbar navbar-inverse">
            <ul class="nav btn-group">
                <!-- <li class="btn btn-inverse"><a title="" href="login.jsp"><i class="icon icon-user"></i> <span class="text">个人中心</span></a></li>
                <li class="btn btn-inverse"><a title="" href="login.jsp"><i class="icon icon-cog"></i> <span class="text">设置</span></a></li> -->
                <li class="btn btn-inverse"><a title="" href="main/signOut"><i class="icon icon-share-alt"></i> <span class="text">退出登录</span></a></li>
            </ul>
        </div>
            
		<div id="sidebar">
			<ul>
				<li class="active"><a href="main/index"><i class="icon icon-home"></i> <span>首页</span></a></li>
				<!-- 根据当前用户权限展示导航菜单 -->
				<c:forEach items="${authoritiesListR}" var="authorities">
						<li class="submenu">
							<a href="login.jsp"><i class="icon icon-th-list"></i> <span>${authorities.display_name}</span> <span class="label">${authorities.authoritiesList.size()}</span></a>
							<c:if test="${authorities.authoritiesList.size() gt 0}">
								<ul>
									<c:forEach items="${authorities.authoritiesList}" var="authority">
										<li><a href="${authority.request}">${authority.display_name}</a></li>
									</c:forEach>
								</ul>
							</c:if>
						</li>
				</c:forEach>
			</ul>
		</div>
		<div id="content">
		</div>
	</body>
	<script type="text/javascript">
		$(".submenu ul a").click(function(e){
			e.preventDefault();
			var url = $(this).attr("href");
			console.log(url);
			$("#content").empty();
			$("#content").load(url);
		})
	</script>
	
</html>

