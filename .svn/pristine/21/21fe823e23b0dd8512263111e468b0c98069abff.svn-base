//选择图片后展示
function Id(id){
	return document.getElementById(id);
}
function changeToop(){ 
    var file = Id("file");
    console.log(file);
    if(file.value==''){
        //设置默认图片
    Id("myimg").src='http://bpic.588ku.com/element_pic/01/47/03/35574339ab3c813.jpg';
    }else{
        preImg("file","myimg");
    }
}

String.prototype.getBytesLength = function() { 
    return this.replace(/[^\x00-\xff]/gi, "--").length;
}

$(".tijiao").click(function(e){
	if($("#announcetype").val()==0){
		toastr.warning("请选择通知类型");
		return false; 
	}else if($("#title").val().getBytesLength()>40){
		toastr.warning("标题需控制在20个汉字以内");
		return false; 
	}else if($("#title").val()==''){
		toastr.warning("请输入标题");
		return false; 
	}else if($("#createUser").val().getBytesLength()>16){
		toastr.warning("发布作者需控制在8个汉字以内");
		return false; 
	}else if($("#createUser").val()==''){
		$("#createUser").val("汇美影像");
	}else if($("#file")[0].files[0].size>1024*1024){
		toastr.warning("图片需控制在200K以内");
		return false; 
	}else{
		$("#addstoreactivity").on("submit",function() {
			  $("#addstoreactivity").ajaxSubmit(function(){
					var url = "/actionCenter/activilist";
					$("#content").empty();
					$("#content").load(url);
			  });
			  return false; 
		}); 
	}
});
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