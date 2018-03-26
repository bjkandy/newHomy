//删除该banner
$(".setBanner").on("click",".deleteX",function(){
	$(this).parent().remove();
});
//轮播
/*$('#full_feature').swipeslider({
	transitionDuration: 600,
	autoPlayTimeout: 10000,
	sliderHeight: '300px'
});*/


//添加banner
$(".addBanner").click(function(){
	if($(".clearAll").length>=4){
		return false;
	}else{
		var i = $(this).attr('data') - 1 + 2;
		$(this).attr('data',i);
		$(".setBanner").append(
			'<div class="clearAll">'+
				'<div class="fl frDiv">'+
					'<div>'+
						'<img id="myimg'+i+'" width="250" src=""/><br>'+
						'<a href="javascript:;" class="a-upload">'+
						    '<input type="file" name="bannerurl'+i+'" id="file'+i+'" onchange="changeToop(this);">点击这里上传文件'+
						'</a>'+
					'</div>'+
				'</div>'+
				'<div class="fl flDiv">'+
					'<div>*广告摘要<span class="fz12">不超过20个汉字</span>'+
						'<textarea name="introduce" rows="3" cols="20" placeholder="请输入摘要内容"></textarea>'+
					'</div>'+
					'<div style="position:relative">*链接<span class="fz12">设置到链接地址</span><br/>'+
							'<input type="hidden" class="bannerid" name="inputid" value="">'+
							'<input type="text" class="bannername" name="inputxt" value="">'+
							'<ul class="bannerlist"></ul>'+
					'</div>'+
				'</div>'+
				'<span class="deleteX">X</span>'+
			'</div>'
		);
	}
});

function add(i,v){
	$(".setBanner").append(
		'<div class="clearAll">'+
			'<div class="fl frDiv">'+
				'<div>'+
					'<img id="myimg'+i+'" width="250" src="'+v.bannerurl+'"/><br>'+
					'<a href="javascript:;" class="a-upload">'+
					    '<input type="file" name="bannerurl'+i+'" id="file'+i+'" onchange="changeToop(this);">点击这里上传文件'+
					'</a>'+
				'</div>'+
			'</div>'+
			'<div class="fl flDiv">'+
				'<div>*广告摘要<span class="fz12">不超过20个汉字</span>'+
					'<textarea name="introduce" rows="3" cols="20" placeholder="请输入摘要内容">'+v.profile+'</textarea>'+
				'</div>'+
				'<div style="position:relative">*链接<span class="fz12">设置到链接地址</span><br/>'+
					'<input type="hidden" class="bannerid" name="inputid" value="'+v.id+'">'+
					'<input type="text" class="bannername" name="inputxt" value="'+v.title+'">'+
					'<ul class="bannerlist"></ul>'+
				'</div>'+
			'</div>'+
			'<span class="deleteX">X</span>'+
		'</div>'
	);
}

$(".setBanner").on('keyup','.bannername',function(){
	var name = $(this).val();
	$.ajax({
		url: "actionCenter/findAnnounce",
		data: {"name":name},
		type: "GET",
		dataType:"json",
		success:function(data){
			console.log(data);
			var announeList = data.announeList;
			$(".bannerlist").empty();
			$.each(data.announeList,function(i,v){
				$(".bannerlist").append('<li bannerid="'+v.id+'">'+v.title+'</li>');
			});
		}
	});
	$(this).parent().find(".bannerlist").show();
});

$(".setBanner").on("click",".bannerlist li",function(){
	console.log(this);
	$(this).parent().parent().find(".bannerid").val($(this).attr("bannerid"));
	$(this).parent().parent().find(".bannername").val($(this).html());
	$(this).parent().hide();
});


$(".tijiao").click(function(e){
	$("#addbanner").on("submit",function() {
		$("#addbanner").ajaxSubmit(function(){
			var url = "/actionCenter/activilist";
			$("#content").empty();
			$("#content").load(url);
		});
		return false; 
	});
});
//选择图片后展示
function Id(id){
	return document.getElementById(id);
}
function changeToop(e){
    var file = $(e).attr("id");
    var myimg = $(e).parent().parent().find("img").attr("id");

    if(this.value==''){
        //设置默认图片
    	$(e).parent().parent().find("img").attr('src','http://bpic.588ku.com/element_pic/01/47/03/35574339ab3c813.jpg');
    }else{
        preImg(file,myimg);
    }
}
//获取input[file]图片的url Important
function getFileUrl(fileId) { 
    var url; 
    var file = Id(fileId);
    var agent = navigator.userAgent;
    if (agent.indexOf("MSIE")>=1) {
    	url = file.value; 
    } else if(agent.indexOf("Firefox")>0) { 
    	url = window.URL.createObjectURL(file.files.item(0)); 
    } else if(agent.indexOf("Chrome")>0) {
    	url = window.URL.createObjectURL(file.files.item(0)); 
    } 
    return url; 
} 
//读取图片后预览
function preImg(fileId,imgId) { 
	var imgPre =Id(imgId);
	imgPre.src = getFileUrl(fileId);
}