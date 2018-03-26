var now   = new Date();
var fullyear = now.getFullYear();

$(".showmonth1 input[name='startTime']").val(fullyear + "-01");
$(".showmonth1 input[name='endTime']").val(fullyear + "-12");


$(function(){
	
	//显示日   月	
	$("#judgeMonths").change(function(){
		if($(this).val()=="0"){
			$(".showmonth1").hide();
			$(".showdate1").show();
			
			$(".showmonth1 input[name=startTime]").val("");
			$(".showmonth1 input[name=endTime]").val("");
	
		}else{
			$(".showmonth1").show();
			$(".showdate1").hide();
			
			$(".showdate1 input[name=startTime]").val("");
			$(".showdate1 input[name=endTime]").val("");
		}
	});
	

	$("#screen2").click(function(){
		tadeilUser();
	});
	
	$("#export").click(function(){
		var dateType = $("#judgeMonths").val();
		var startTime = dateType == 1 ? $(".showmonth1 input[name='startTime']").val() : $(".showdate1 input[name='startTime']").val();
		var endTime = dateType == 1 ? $(".showmonth1 input[name='endTime']").val() : $(".showdate1 input[name='endTime']").val();
		window.location.href = "dataCenter/merchantDataAnalyzeExcel?dateType="+dateType+"&startTime="+startTime+"&endTime="+endTime;
	});
	
	tadeilUser();
});


function tadeilUser(dateType,startTime,endTime){
	var dateType = $("#judgeMonths").val();
	var startTime = dateType == 1 ? $(".showmonth1 input[name='startTime']").val() : $(".showdate1 input[name='startTime']").val();
	var endTime = dateType == 1 ? $(".showmonth1 input[name='endTime']").val() : $(".showdate1 input[name='endTime']").val();
	$.ajax({
		type : "post",
		url : "dataCenter/merchantDataAnalyze?dateType="+dateType+"&startTime="+startTime+"&endTime="+endTime,
		success : function(data) {
			console.log(data);
			$("div .fansUl").empty();
			$("#fanstable tbody").empty();
			
			$("div .fansUl").append("<li><dl><dt>商户总数</dt><dd>"+data.data.currentMerchantNumber+"</dd></dl></li><li><dl><dt>新增商户（个）</dt><dd>"+data.data.totalNewMerchantNumber+"<span style='font-size:14px;position: relative;display: inline-block;width: 500px;top: -26px;left: 126px;'>(门店："+data.data.totalMerchantNumber+"；经销商："+data.data.totalDistributNumber+"；区域制作中心："+data.data.totalProductNumber+"；推广中心："+data.data.totalPrometerNumber+"；推广员："+data.data.totalQyNumber+")</span></dd></dl></li>");
		
			$("#fanstable thead").show();
			for(var i=0;i<data.data.rows.length;i++){
				$("#fanstable tbody").append("<tr><td>20"+data.data.rows[i].datetime+"</td><td>"+data.data.rows[i].currentMerchantNumber+"</td><td>"+data.data.rows[i].newMerchantNumber+"</td><td>"+data.data.rows[i].activeMerchandNumber+"</td><td>"+data.data.rows[i].merchantNumber+"</td><td>"+data.data.rows[i].distributNumber+"</td><td>"+data.data.rows[i].productNumber+"</td><td>"+data.data.rows[i].prometerNumber+"</td><td>"+data.data.rows[i].qyNumber+"</td></tr>");
			}
			$(".tcdPageCode").createPage({
		        pageCount:Math.ceil(data.data.total/10),
		        current:1,
		        backFn:function(p){  	
		        	console.log(p);
		        	var dateType = $("#judgeMonths").val();
		        	var startTime = dateType == 1 ? $(".showmonth1 input[name='startTime']").val() : $(".showdate1 input[name='startTime']").val();
		        	var endTime = dateType == 1 ? $(".showmonth1 input[name='endTime']").val() : $(".showdate1 input[name='endTime']").val();
		            $.ajax({
						type:"post",
						url:"dataCenter/merchantDataAnalyze?dateType="+dateType+"&startTime="+startTime+"&endTime="+endTime+"&pageNo="+p,
						async:true,
						success:function(data1){
							console.log(data1);
							if(data1.code=="4001"){
								
							}else{
								$("#fanstable tbody").empty();
								for(var i=0;i<data1.data.rows.length;i++){
									$("#fanstable tbody").append("<tr><td>20"+data1.data.rows[i].datetime+"</td><td>"+data1.data.rows[i].currentMerchantNumber+"</td><td>"+data1.data.rows[i].newMerchantNumber+"</td><td>"+data1.data.rows[i].activeMerchandNumber+"</td><td>"+data1.data.rows[i].merchantNumber+"</td><td>"+data1.data.rows[i].distributNumber+"</td><td>"+data1.data.rows[i].productNumber+"</td><td>"+data1.data.rows[i].prometerNumber+"</td><td>"+data1.data.rows[i].qyNumber+"</td></tr>");
								}
							}
							
							
						}
					});
		        }
		    });
		},
		error : function(data) {
		
		}
	});
}
