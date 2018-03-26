var now   = new Date();
var fullyear = now.getFullYear();
$(function(){
	//显示日   月	
	$("#judgeDay").change(function(){
		if($(this).val()=="day"){
			$(".showmonth").hide();
			$(".showdate").show();
			
			$(".showmonth input[name=startDate]").val("");
			$(".showmonth input[name=endDate]").val("");
		}else{
			$(".showmonth").show();
			$(".showdate").hide();
			$(".showdate input[name=startDate]").val("");
			$(".showdate input[name=endDate]").val("");
		}
	});
	
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
	

	$("#screen1").click(function(){
		tadeilUser();
	});
	
	$("#screen2").click(function(){
		tadeilUser();
	});
	
	$("#export").click(function(){
		var params = $("#detailFrom").serialize();
		location.href = "userstatistics/export?excel&"+params;
	});
	
	
	tadeilUser();
});


function tadeilUser(){
	$.ajax({
		type : "post",
		url : "statistic/trananlysis",
		data: $("#detailFrom").serialize(),
		success : function(data) {
			$("div .fansUl").html("");
			$("#fanstable tbody").html("");
			var json = eval('(' + data + ')'); 
			
			$("div .fansUl").append("<li><dl><dt>订单总金额</dt><dd>"+json.totalResultMap.allprice+"</dd></dl></li><li><dl><dt>付款订单总金额</dt><dd>"+json.totalResultMap.payOdrTotalprice+"</dd></dl></li><li><dl><dt>订单总数</dt><dd>"+json.totalResultMap.odrid+"</dd></dl></li><li><dl><dt>付款订单总数</dt><dd>"+json.totalResultMap.payOdrNumber+"</dd></dl></li><li><dl><dt>下单人数</dt><dd>"+json.totalResultMap.allorderperson+"</dd></dl></li><li><dl><dt>付款人数</dt><dd>"+json.totalResultMap.payorderperson+"</dd></dl></li>");
			
			for(var i=0;i<json.resultListMap.length;i++){
				$("#fanstable tbody").append("<tr><td>"+json.resultListMap[i].date+"</td><td>"+json.resultListMap[i].newfans+"</td><td>"+json.resultListMap[i].numberoffansonfoot+"</td><td>"+json.resultListMap[i].netfannumber+"</td><td>"+json.resultListMap[i].cumulativefans+"</td></tr>");
			}
		},
		error : function(data) {
		
		}
	});
}