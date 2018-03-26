<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//out.println("-------------" + path);
//out.println("==================" + basePath);

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>汇美微传即时管理系统</title>
        <link rel="stylesheet" href="<%=basePath%>hanson/css/base.css" />
        <style type="text/css">
        	body{
        		background: url(<%=basePath%>hanson/img/back.png);
        	}
        	form{
        		width: 480px;
        		height: 320px;
        		background-color: #ffffff;
        		position: absolute;
        		top: 50%;
        		left: 50%;
        		margin: -160px 0 0 -240px;
        		opacity: 0.9;
        		text-align: center;
        		border-radius:10px;
        	}
        	
        	input[type="text"],input[name="password"]{
        		width: 350px;
        		height: 40px;
        		margin-bottom: 15px;
        		padding-left: 40px;
        		border: 1px solid #ddd;
        	}
        	input[name="login_name"]{
        		margin-top: 100px;
        		background: url(<%=basePath%>hanson/img/usern.png) no-repeat 0px 0px;
        	}
        	input[name="password"]{
        		background: url(<%=basePath%>hanson/img/pwd.png) no-repeat 0px 0px;
        		
        	}
        	input[type="submit"]{
        		width: 350px;
        		height: 40px;
        		background-color: #4997D8;
        		text-align: center;
        	}
        </style>
        
        <script src="<%=basePath%>hanson/js/jquery.min.js"></script>
        <script src="<%=basePath%>hanson/js/jquery.form.js"></script>
    </head>
    <body>
    	<form id="loginForm" method="post">
    		<input type="text" name="login_name" placeholder="用户名"/>
    		<input type="password" name="password" placeholder="密码"/>
    		<input type="submit" value="登录"/>
    	</form>
 	</body>
 <script type="text/javascript">
$(function(){
	$("#loginForm").on("submit", function(){
		var login_name = $("#login_name").val();
		var password = $("#password").val();
		
		$(this).ajaxSubmit({
			type: "post", 
            url: "main/loginCheck", 
            dataType:"json",
            data: { 'login_name': login_name, 'password':password},
            success: function(data) { 
            	if(data != null && data.errorCode == "000000"){
            		//验证通过跳转到后台首（主）页
            		window.location.href="main/index";
            	}else{
            		alert("账号或密码错误请重新录入");
            	}
                
            }
		});
		
		return false;// 阻止表单自动提交事件
	});
}); 

</script>
 


	
 	
</html>