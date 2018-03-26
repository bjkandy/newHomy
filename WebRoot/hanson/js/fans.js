var now   = new Date();
var fullyear = now.getFullYear();
//$(".showmonth input[name='startDate']").val(fullyear + "-01");
//$(".showmonth input[name='endDate']").val(fullyear + "-12");

$(".showmonth1 input[name='startDate']").val(fullyear + "-01");
$(".showmonth1 input[name='endDate']").val(fullyear + "-12");
//
//var defaults = {    
//		scaleOverride : false,  //是否用硬编码重写y轴网格线    默认为false
//		scaleSteps : null,    //y轴刻度的个数  默认为null  只有在scaleOverride为true时才能更改
//		scaleStepWidth : null,   //y轴每个刻度的宽度 默认为null  只有在scaleOverride为true时才能更改
//	    scaleStartValue : null,     // Y 轴的起始值 默认为null  只有在scaleOverride为true时才能更改
//	    scaleLineColor : "#666",    // Y/X轴的颜色
//	    scaleLineWidth : 1,        // X,Y轴的宽度
//	    scaleShowLabels : true,    // 刻度是否显示标签, 即Y轴上是否显示文字   
//	    scaleLabel : "<%=value%>", // Y轴上的刻度,即文字  
//	    scaleFontFamily : "'MicroSoft YaHei'",  // 字体  
//	    scaleFontSize : 20,        // 文字大小 
//	    scaleFontStyle : "normal",  // 文字样式  
//	    scaleFontColor : "#666",    // 文字颜色  
//	    scaleShowGridLines : false,   // 是否显示网格  
//	    scaleGridLineColor : "#666",   // 网格颜色
//	    scaleGridLineWidth : 1,      // 网格宽度  
//	    bezierCurve : false,         // 是否使用贝塞尔曲线? 即:线条是否弯曲     
//	    pointDot : true,             // 是否显示点数  
//	    pointDotRadius : 4,          // 圆点的大小  
//	    pointDotStrokeWidth : 1,     // 圆点的笔触宽度, 即:圆点外层边框大小 
//	    datasetStroke : true,        // 数据集行程
//	    datasetStrokeWidth : 1,      // 线条的宽度, 即:数据集
//	    datasetFill : true,         // 是否填充数据集 
//	    animation : true,            // 是否执行动画  
//	    animationSteps : 60,          // 动画的时间   
//	    animationEasing : "easeOutQuart",    // 动画的特效   
//	    onAnimationComplete : null    // 动画完成时的执行函数   
//	};

$(function(){
//	//显示日   月	
//	$("#judgeDay").change(function(){
//		if($(this).val()=="day"){
//			$(".showmonth").hide();
//			$(".showdate").show();
//			
//			$(".showmonth input[name=startDate]").val("");
//			$(".showmonth input[name=endDate]").val("");
//		}else{
//			$(".showmonth").show();
//			$(".showdate").hide();
//			
//			$(".showdate input[name=startDate]").val("");
//			$(".showdate input[name=endDate]").val("");
//		}
//	});
//	
	//显示日   月	
	$("#judgeMonths").change(function(){
		if($(this).val()=="day"){
			$(".showmonth1").hide();
			$(".showdate1").show();
			
			$(".showmonth1 input[name=startDate]").val("");
			$(".showmonth1 input[name=endDate]").val("");
	
		}else{
			$(".showmonth1").show();
			$(".showdate1").hide();
			
			$(".showdate1 input[name=startDate]").val("");
			$(".showdate1 input[name=endDate]").val("");
		}
	});
	

//	$("#screen1").click(function(){
//		tadeilUserData();
//	});
	
	$("#screen2").click(function(){
		tadeilUser();
	});
	
	$("#export").click(function(){
		var params = $("#detailFrom").serialize();
		location.href = "userstatistics/export?excel&"+params;
	});
	
//	tadeilUserData();
	tadeilUser();
});

function tadeilUserData(){
	$.ajax({
		type : "post",
		url : "userstatistics/tadeilUserData",
		data: $("#fantrendFrom").serialize(),
		success : function(data) {
			var json = JSON.parse(data); 
			var date=[];
			var datatailde=[];
			$('#canvas').remove(); // this is my <canvas> element
			$('.wrapper').append('<canvas height="300" id="canvas" width="900"></canvas>');
			
			for(var i=0;i<json.resultListMap.length;i++){
				datatailde.push(json.resultListMap[i].newfans);
				if($("#judgeDay").val()=='day'){
					date.push(formatDate(json.resultListMap[i].date));
				}else{
					date.push(json.resultListMap[i].date);
				}
			}
			console.log(date);
			var myData = {
			  labels : date,
			  datasets : [
			    {
			      fillColor : "rgba(255,255,255,0)",//背景填充色
			      strokeColor : "rgba(0,0,0,1)",//路径颜色
			      pointColor : "rgba(0,0,0,1)",//数据点颜色
			      pointStrokeColor : "rgba(0,0,0,1)",//数据点边框颜色
			      data : datatailde//对象数据	
			    }
			  ]
			};
			
			var cxs = document.getElementById("canvas").getContext("2d");
			var chart = new Chart(cxs).Line(myData,defaults);
		},
		error : function(data) {
		
		}
	});
}

function tadeilUser(){
	console.log($("#detailFrom").serialize());
	$.ajax({
		type : "post",
		url : "userstatistics/tadeilUserData",
		data: $("#detailFrom").serialize(),
		success : function(data) {
			$("div .fansUl").empty();
			$("#fanstable tbody").empty();
			var json = JSON.parse(data);
			
			$("div .fansUl").append("<li><dl><dt>粉丝总数</dt><dd>"+json.totalResultMap.number+"</dd></dl></li><li><dl><dt>新增粉丝（个）</dt><dd>"+json.totalResultMap.newfans+"</dd></dl></li><li><dl><dt>跑路粉丝（个）</dt><dd>"+json.totalResultMap.numberoffansonfoot+"</dd></dl></li><li><dl><dt>净增粉丝（个）</dt><dd>"+json.totalResultMap.netfannumber+"</dd></dl></li>");
			
			if(json.resultListMap.length<=0){
				alert("暂无相关数据!");
			}else{
				$("#fanstable thead").show();
				for(var i=0;i<json.resultListMap.length;i++){
					if($("#judgeMonths").val()=='day'){
						$("#fanstable tbody").append("<tr><td>"+formatDate(json.resultListMap[i].date)+"</td><td>"+json.resultListMap[i].newfans+"</td><td>"+json.resultListMap[i].numberoffansonfoot+"</td><td>"+json.resultListMap[i].netfannumber+"</td><td>"+json.resultListMap[i].cumulativefans+"</td></tr>");
					}else{
						$("#fanstable tbody").append("<tr><td>"+json.resultListMap[i].date+"</td><td>"+json.resultListMap[i].newfans+"</td><td>"+json.resultListMap[i].numberoffansonfoot+"</td><td>"+json.resultListMap[i].netfannumber+"</td><td>"+json.resultListMap[i].cumulativefans+"</td></tr>");
					}
				}
			}
		},
		error : function(data) {
		
		}
	});
}
function formatDate(now){
	var now = new Date(now);
	var year=now.getFullYear();
  	var month=(now.getMonth()+1)>=10?(now.getMonth()+1):"0"+(now.getMonth()+1);
 	var date=now.getDate()>=10?now.getDate():"0"+now.getDate();      
  	return year+"-"+month+"-"+date;  
}
//var cxs = document.getElementById("canvas").getContext("2d");
//var chart = new Chart(cxs).Line(myData,defaults);
