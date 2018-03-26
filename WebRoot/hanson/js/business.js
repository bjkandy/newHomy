var now   = new Date();
var fullyear = now.getFullYear();

$(".showmonth1 input[name='startDate']").val(fullyear + "-01");
$(".showmonth1 input[name='endDate']").val(fullyear + "-12");
$(function(){
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
	
	$("#screen2").click(function(){
		tadeilUser();
	});
	tadeilUser();
	$("#export").click(function(){
		var dateType = $("#judgeMonths").val();
		var startDate = dateType=="month" ? $(".showmonth1 input[name='startDate']").val() : $(".showdate1 input[name='startDate']").val();
		var endDate = dateType=="month" ? $(".showmonth1 input[name='endDate']").val() : $(".showdate1 input[name='endDate']").val();
		window.location.href = 'statistic/businessDataExcel?startDate='+startDate+'&endDate='+endDate+'&judgeDayOrMonths='+dateType;
	});
});

function tadeilUser(){
	var dateType = $("#judgeMonths").val();
	var startDate = dateType=="month" ? $(".showmonth1 input[name='startDate']").val() : $(".showdate1 input[name='startDate']").val();
	var endDate = dateType=="month" ? $(".showmonth1 input[name='endDate']").val() : $(".showdate1 input[name='endDate']").val();
	$.ajax({
		type : "post",
		url : "statistic/trananlysis?judgeDayOrMonths="+dateType+"&startDate="+startDate+"&endDate="+endDate,
		success : function(data) {
			$("div .fansUl").empty();
			$("#fanstable tbody").empty();
			var json = JSON.parse(data); 
			console.log(json);
			if(json.totalResultMap.length == 0){
				alert("暂无相关数据");
			}else{
				                                     // 订单总金额                                                                                                                                                                                                             付款订单总金额（元）                                                                                                                                                                                             订单总数                                                                                                                                                                                                                                                        付款订单总数                                                                                                                                                                                                                             下单人数                                                                                                                                                                                                                                      付款人数                                                                                                                                                                                                                                                                                      付款转换率
				$("div .fansUl").append("<li><dl><dt>订单总金额（元）</dt><dd>"+(json.resultListMap.allprice/100).toFixed(2)+"</dd></dl></li><li><dl><dt>付款订单总金额（元）</dt><dd>"+(json.resultListMap.payOdrTotalprice/100).toFixed(2)+"</dd></dl></li><li><dl><dt>订单总数</dt><dd>"+json.resultListMap.odrid+"</dd></dl></li><li><dl><dt>付款订单总数</dt><dd>"+json.resultListMap.payOdrNumber+"</dd></dl></li><li><dl><dt>下单人数 </dt><dd>"+json.resultListMap.allorderperson+"</dd></dl></li><li><dl><dt>付款人数</dt><dd>"+json.resultListMap.payorderperson+"</dd></dl></li><li><dl><dt>付款转换率</dt><dd>"+(json.resultListMap.payorderperson/json.resultListMap.allorderperson*100).toFixed(2)+"%</dd></dl></li>");
				for(var i=0;i<json.totalResultMap.length;i++){
					$("#fanstable tbody").append("<tr><td>"+json.totalResultMap[i].createtime+"</td><td>"+json.totalResultMap[i].odrid+"</td><td>"+json.totalResultMap[i].payOdrNumber+"</td><td>"+(json.totalResultMap[i].allprice/100).toFixed(2)+"</td><td>"+(json.totalResultMap[i].payOdrTotalprice/100).toFixed(2)+"</td><td>"+json.totalResultMap[i].allorderperson+"</td><td>"+json.totalResultMap[i].payorderperson+"</td><td>"+(json.totalResultMap[i].payorderperson/json.totalResultMap[i].allorderperson*100).toFixed(2)+"%</td></tr>");
				}
			}
		},
		error : function(data) {
			
		}
	});
}

