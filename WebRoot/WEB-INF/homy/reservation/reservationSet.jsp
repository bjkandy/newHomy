<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.hanson.util.Constant"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<link rel="stylesheet" href="hanson/css/common.css" />
	<link rel="stylesheet" type="text/css" href="hanson/css/table.css"/>
	<link rel="stylesheet" href="hanson/css/datepicker.css" />
	<link rel="stylesheet" href="hanson/css/select2.css" />
	<link rel="stylesheet" href="hanson/css/toastr.css" />
	<link rel="stylesheet" href="hanson/css/bootstrap.min.css" />
	<link rel="stylesheet" href="hanson/css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" href="hanson/css/fullcalendar.css" />	
	<link rel="stylesheet" href="hanson/css/uniform.css" />
	<link rel="stylesheet" href="hanson/css/select2.css" />	
	<link rel="stylesheet" href="hanson/css/unicorn.main.css" />
	<link rel="stylesheet" href="hanson/css/unicorn.grey.css" class="skin-color" />
	
    <style type="text/css">
		from{
			display:block;
			width:60%;
			margin: 10px auto;
		}
		li{
			list-style:none;
		}
		li>div{
			height:40px;
			line-height:40px;
			font-size:16px;
		}
		.YCSet{
			width:100%;
			height:40px;
			line-height:40px;
			padding-left:20px;
			background-color:#F2F2F2;
		}
   	</style>
   	<script src="hanson/js/toastr.js"></script>
   	<script src="hanson/js/toastr.setting.js"></script>
   	<script src="hanson/js/table.ajax.js" type="text/javascript" charset="utf-8"></script>
   	<script src="hanson/js/jquery.validate.js"></script>
</head>
  
<body>
	<div class="YCSet">优惠预存设置</div>
	<ul class="vipList">
<%-- 			<c:forEach items="${vipList}" var="vip" varStatus="status">
				<li>
					<div>vip ${status.index+1}</div>
					<span>
						一次性预存&nbsp;&nbsp;&nbsp;<input type="number" name="chargemoney" id="chargemoney${status.index+1}" value="${vip.chargemoney}"/>&nbsp;&nbsp;
						生产费用享受&nbsp;&nbsp;&nbsp;<input type="number" min="0" max="10" step="0.1" name="discount" id="discount${status.index+1}" value="${vip.discount*10}"/>折
						<input type="hidden" name="autoid" id="a${status.index+1}" value="${vip.id}"  />
						<input type="hidden" name="vip" id="v${status.index+1}" value="${vip.grade}" />
					</span>	
				</li>
			</c:forEach> --%>
	</ul>
	<div style="text-align:center;width:50%;">
		<button type="button"  class="btn btn-primary" onclick="check_save_store()">保存设置</button>
	</div>
</body>
  <script>
  	$.ajax({
    	url: "reservation/VipList",
    	type:"POST",
    	dataType:"json",
    	success:function(data){
    		console.log(data);
    		$(".vipList").empty();
    		$.each(data.rows,function(i,v){
    			$(".vipList").append(
    				'<li>'+
						'<div>vip'+(i+1)+'</div>'+
						'<span>'+
							'一次性预存&nbsp;&nbsp;&nbsp;<input type="number" name="chargemoney" id="chargemoney'+(i+1)+'" value="'+v.chargemoney/100+'"/>&nbsp;&nbsp;'+
							'生产费用享受&nbsp;&nbsp;&nbsp;<input type="number" min="0" max="10" step="0.1" name="discount" id="discount'+(i+1)+'" value="'+(v.discount*10).toFixed(1)+'"/>折'+
							'<input type="hidden" name="autoid" id="a'+(i+1)+'" value="'+v.id+'"/>'+
							'<input type="hidden" name="vip" id="v'+(i+1)+'" value="'+v.grade+'" />'+
						'</span>'+
					'</li>'
    			);
    		});
    	}
    });
    function check_save_store(){
    	var x = '';
		$.each($(".vipList li"),function(i,v){
			x += ','+$(v).find("input[name='autoid']").val()+"_"+$(v).find("input[name='chargemoney']").val()+"_"+$(v).find("input[name='discount']").val();
 		});
 		console.log(x.slice(1));
 		$.ajax({
	        url: "reservation/Savestorevip",
	        data:{"vips":x.slice(1)},
	        type: "POST",
	        dataType:"json",
	        beforeSend:function(){
	            return true;
	        },
	        success:function(result){
				if(result!=null && result.errorCode == "000000"){
    				toastr.success("优惠预存设置成功!");
    		    }else{
    				toastr.warning(result.msg);
    		    }	
	        },
	        error:function(){
	        	toastr.error('网络错误');
	        },
	        complete:function(){
	        	
	        }
	    });
	};
  </script>
</html>
