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
    <script type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>
    <title>交易分析</title>
    <style>
        .fansFluctuationTendency{
            padding: 20px;
        }
        .fansFluctuationTendency div{
            float: right;
        }
        .fansFluctuationTendency button{
            background-color: #FFFFFF;
            padding: 0 10px;
            border-radius: 3px;
            border: 1px solid #666666;
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
        #fanstable{
            width: 100%;
            text-align: center;
            margin-top: 20px;
            background-color: #FFFFFF;
        }
        #fanstable th{
            background-color: #C8C8C8;
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
    </style>
</head>
<body>
    <div class="fansFluctuationTendency">
        <span>交易增减趋势：</span>
        <div>
        	<form action="" id = "fantrendFrom">
        		<select name="judgeDayOrMonths" id="judgeDay">
                <option value="month">筛选时间（月）</option>
                <option value="day">筛选时间（日）</option>
	            </select>
	            
	            <div class="showdate" style=" display: none;">
		            <input type="text" value="" name="startDate" class="Wdate" onclick="WdatePicker()" placeholder="请选择日">
		            ~
		            <input type="text" value="" name="endDate" class="Wdate" onclick="WdatePicker()" placeholder="请选择日">
	            </div>
	            
	            <div class="showmonth">
		            <input type="text" value="" name="startDate" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false })" placeholder="请选择月份">
		            ~
		            <input type="text" value="" name="endDate" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false })" placeholder="请选择月份">
	            </div>
	            <input type="button" id="screen1" value="筛选">
        	</form>
        </div>
    </div>
    <!--图表-->
    <div class='wrapper'>
        <canvas height='300' id='canvas' width='900'></canvas>
    </div>
    <!---->
    <div class="fansFluctuationTendency">
        <span>详细数据：</span>
        <div>
            <form action="" id = "detailFrom">
        		<select name="judgeDayOrMonths" id="judgeMonths">
                <option value="month">筛选时间（月）</option>
                <option value="day">筛选时间（日）</option>
	            </select>
	            
	            <div class="showdate1" style=" display: none;">
		            <input type="text" value="" name="startDate" class="Wdate" onclick="WdatePicker()" placeholder="请选择日">
		            ~
		            <input type="text" value="" name="endDate" class="Wdate" onclick="WdatePicker()" placeholder="请选择日">
	            </div>
	            
	            <div class="showmonth1">
		            <input type="text" value="" name="startDate" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false })" placeholder="请选择月份">
		            ~
		            <input type="text" value="" name="endDate" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false })" placeholder="请选择月份">
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
            <thead>
                <tr><th>日期</th><th>订单总数</th><th>付款订单总数</th><th>订单总金额（元）</th><th>付款订单总金额（元）</th><th>下单人数</th><th>付款人数</th><th>付款转化率（%）</th></tr>
            </thead>
            <tbody>
                
            </tbody>
        </table>
        <!--分页-->
        <div class="page">
            <div>
                <a href="javascript:void(0)" class="prev disabled prepage"><</a>
                <a href="javascript:void(0)" class="next nextpage">></a>
                <select id="rows" style="width: 100px;height: 30px;">
                    <option value="2">20条/页</option>
                    <option value="40">40条/页</option>
                    <option value="60">60条/页</option>
                    <option value="80">80条/页</option>
                    <option value="100">100条/页</option>
                </select>
                跳至<input type="number" style="width: 40px; text-align: center;height: 27px;border-radius: 5px;border: 1px solid #DDDDDD;margin: 0px 10px">页
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="/hanson/js/jquery-1.8.0.min.js"></script>
<script src='/hanson/js/Chart.min.js'></script>
<script src="/hanson/js/prefixfree.min.js" type="text/javascript" charset="utf-8"></script>
<script src="/hanson/js/transaction.js"></script>
</html>
