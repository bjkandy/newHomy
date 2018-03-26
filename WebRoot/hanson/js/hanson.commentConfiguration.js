$(function() {
	$.ajax({
		type : "post",
		dataType : "json",
		async : false,
		url : "/comment/commentConfiguration",
		success : function(data) {
			var star1 = "";
			var star2 = "";
			var star3 = "";
			var star4 = "";
			var star5 = "";
			for ( var i = 0; i < data.length; i++) {
				if (data[i].starlevel == 1) {
					star1 += data[i].tagcontent + ";";
				} else if (data[i].starlevel == 2) {
					star2 += data[i].tagcontent + ";";
				} else if (data[i].starlevel == 3) {
					star3 += data[i].tagcontent + ";";
				} else if (data[i].starlevel == 4) {
					star4 += data[i].tagcontent + ";";
				} else if (data[i].starlevel == 5) {
					star5 += data[i].tagcontent + ";";
				}
			}
			$("#star1").html(star1);
			$("#star2").html(star2);
			$("#star3").html(star3);
			$("#star4").html(star4);
			$("#star5").html(star5);
		}
	});
});


function getCommentTagByStarLevel(starLevel){
	
	
	$("#starLevel").val(starLevel);
	
	
	$.ajax({
		type : "post",
		dataType : "json",
		async : false,
		data:{"star":starLevel},
		url : "/comment/commentConfiguration",
		success : function(data) {
			var html='';
			for(var i=0;i<data.length;i++){
               html+="<li><input type='hidden' value='"+data[i].id+"'/><span>"+data[i].tagcontent+"</span><img src='hanson/img/u12321.png' alt='' class='clear_value'></li>";
			}
	       $("#kewWord").html(html);
	       console.log($("#kewWord")[0].children)
		}
	});
}



function consure(){
//    var id=$("#kewWord").find("input").val();
//    var tagcontent=$("#kewWord").find("span").html();
	var modifyStarLevel=$("#starLevel").val();
	var signList = [];
    for(var i= 0;i < $("#kewWord")[0].children.length;i++){
//    	signList.push({
//    		id:$("#kewWord")[0].children[i].children[0].value,
//    		commenttag:$("#kewWord")[0].children[i].children[1].innerText
//    	});
    	signList.push($("#kewWord")[0].children[i].children[0].value+"-"+$("#kewWord")[0].children[i].children[1].innerText);
    }
    $.ajax({
    	type:"post",
    	dataType:"json",
    	async:false,
    	data:{"modifyData":JSON.stringify(signList),"starLevel":modifyStarLevel},
    	url:"/comment/modifyCommenttag",
    	success:function(data){
    		alert(data);
    	}
    });
    console.log(signList);
}

function deleteCommentTag(id)
{
	
	
	if(id==0){
		return false;
	}
    $.ajax({
    	type:"post",
    	dataType:"json",
    	async:false,
    	data:{"id":id},
    	url:"/comment/deleteCommenttag",
    	success:function(data){
    		alert(data);
    	}
    });
}