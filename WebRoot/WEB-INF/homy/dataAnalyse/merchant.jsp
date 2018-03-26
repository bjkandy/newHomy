<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge,chrome=1">
    <script type="text/javascript" src="http://www.my97.net/My97DatePicker/WdatePicker.js"></script>
    <title>商户分析</title>
    <style>
        .fansFluctuationTendency{
            padding: 20px;
        }
        .fansFluctuationTendency div{
            float: right;
            margin-right: 80px;
            position:relative;
        }
        .fansFluctuationTendency button{
            background-color: #FFFFFF;
            padding: 0 10px;
            border-radius: 3px;
            border: 1px solid #666666;
        }
        .fansFluctuationTendency select{
        	margin-right:10px;
        }
        :before, :after {
            content: '';
            display: block;
            position: absolute;
            box-sizing: border-box;
        }
        ul, ol, li,dl,dt,dd{list-style: none;}
        .wrapper {
            padding: 50px 0;
            border-bottom: 1px solid rgba(0, 0, 0, 0.1);
            background: #fff;
        }

        #canvas {
            display: block;
            margin: 0 auto;
        }
        .fansUl{
            overflow: hidden;
            padding: 20px;
            background-color: #FFFFFF;
        }
        .fansUl li{
            float: left;
            text-align: center;
            width: 200px;
        }
        .fansUl dd{
        	text-align: center;
        	font-size: 28px;
        	margin-top: 10px;
        	margin-left: 0px;
        }
        #fanstable{
            width: 100%;
            text-align: center;
            margin-top: 20px;
            background-color: #FFFFFF;
        }
        #fanstable th{
            background-color: #C8C8C8;
            height:30px;
            line-height:30px;
        }
        #fanstable td{
        	border:1px solid #D6D6D6;
        	height:30px;
            line-height:30px;
        }
        .page{
            overflow: hidden;
            text-align: right;
            margin-top: 20px;
        }
        .page>div{
            display: inline-block;
        }
        .page>div>a{
            display: inline-block;
            width:30px;
            height: 30px;
            line-height: 28px;
            text-align: center;
            border-radius: 5px;
            text-decoration: none;
            border: 1px solid #666666;
            color: #666666;
            background-color: #FFFFFF;
        }
        .page>div>a.active{
            background-color: #108EE9;
            color: #FFFFFF;
        }
        .page>div>a.disabled{
            color: #666666;
            background-color: #DDDDDD;
        }
        #screen2,#screen1,#export{
        	position:absolute;
        	right:1%;
        	top:2%;
        	border: 1px solid #66CCCC;
        	border-radius: 5px;
        	padding: 3px 10px;
        	background-color: #ffffff;
        }
        #export{
        	right:-10%;
        	top:2%;
        }
        *{ margin:0; padding:0; list-style:none;}
		a{ text-decoration:none;}
		a:hover{ text-decoration:none;}
		.tcdPageCode{padding: 15px 20px;text-align: left;color: #ccc;text-align:center;}
		.tcdPageCode a{display: inline-block;color: #428bca;display: inline-block;height: 25px;	line-height: 25px;	padding: 0 10px;border: 1px solid #ddd;	margin: 0 2px;border-radius: 4px;vertical-align: middle;}
		.tcdPageCode a:hover{text-decoration: none;border: 1px solid #428bca;}
		.tcdPageCode span.current{display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;color: #fff;background-color: #428bca;	border: 1px solid #428bca;border-radius: 4px;vertical-align: middle;}
		.tcdPageCode span.disabled{	display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;	color: #bfbfbf;background: #f2f2f2;border: 1px solid #bfbfbf;border-radius: 4px;vertical-align: middle;}
    </style>
</head>
<body>
    <!---->
    <div class="fansFluctuationTendency">
        <span>详细数据：</span>
        <div>
            <form action="" id = "detailFrom">
        		<select name="dateType" id="judgeMonths">
                <option value="1">筛选时间（月）</option>
                <option value="0">筛选时间（日）</option>
	            </select>
	            
	            <div class="showdate1" style=" display: none;">
		            <input type="text" value="" name="startTime" class="Wdate" onclick="WdatePicker()" placeholder="请选择日">
		            ~
		            <input type="text" value="" name="endTime" class="Wdate" onclick="WdatePicker()" placeholder="请选择日">
	            </div>
	            
	            <div class="showmonth1">
		            <input type="text" value="" name="startTime" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false })" placeholder="请选择月份">
		            ~
		            <input type="text" value="" name="endTime" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false })" placeholder="请选择月份">
	            </div>
	            <input type="button" id="screen2" value="筛选">
	            <input type="button" id="export" value="导出报表">
        	</form>
        </div>
    </div>
    <!---->
    <div>
        <ul class="fansUl">
            
        </ul>
        <!--表单-->
        <table id="fanstable" class="">
            <thead style="display:none;">
                <tr><th>日期</th><th>累计商户数</th><th>新增商户数</th><th>活跃商户数</th><th>门店</th><th>经销商</th><th>区域制作中心</th><th>推广中心</th><th>推广员</th></tr>
            </thead>
            <tbody>
                
            </tbody>
        </table>
    </div>
    <!--分页-->
	<div class="tcdPageCode"></div>
</body>
<script type="text/javascript" src="/hanson/js/jquery-1.8.0.min.js"></script>
<script src="http://www.my97.net/My97DatePicker/WdatePicker.js"></script>
<script src="hanson/js/jquery.page.js"></script>
<script src="/hanson/js/merchant.js"></script>
</html>