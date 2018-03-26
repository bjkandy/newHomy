// 主页图片选择
$("#index_file_input").on("change", function() {
	let objUrl = getObjectURL(this.files[0]);
	if(objUrl) {
		$(this).parent().find("img").attr("src", objUrl);
	}
});

// 商品轮图的选择
(function() {
	$("#addProductForm input").attr("disabled", "disabled");
    $("#addProductForm textarea").attr("disabled", "disabled");
    $("#addProductForm select").attr("disabled", "disabled"); 

	let $tempSel = $(``),
	    picSet   = new Set(),
	    
	    /*$("#pro_scroll_wrapper input[type='hidden']").each(function(index, item){
	    	addEvent($(item));
		});*/
	    
	    $wrapper = $("#pro_scroll_wrapper");
	addNewTempSelScr();
	
	// 添加事件
	function addEventScr($target) {
		$target.find("input").on("change", function(ev) {
			let file = this.files[0];
			console.dir(this);
			if(!file) return;
			if(picSet.has(file.name)) {
				alert(`你已经选择了一张名为"${file.name}"请不要选择同一的图片....... `);
				return;
			}
			picSet.add(file.name);
			let $father = $(this).parent(),
			    objUrl  = getObjectURL(this.files[0]);
			$father.find("img").attr("src", objUrl);
			if($father.find(".icon-close").length === 0) {
				let $close = $(`<span class="icon-close" ></span>`);
				$father.append($close);
				$close.on("click", function() {
					picSet.delete(file.name);
					$target.remove();
					return false;
				});
				addNewTempSelScr();
				
				
			}
			//设置隐藏变量为空
			$(this).parent().find("input[type='hidden']").val("");
		});
	}
	
	// 添加新的sel, 并返回添加的新元素
	function addNewTempSelScr() {
		let $clone = $tempSel.clone();
		$wrapper.append($clone);
		$("#pro_scroll_wrapper label").each(function(index, item){
	    	addEventScr($(item));
		});
	}
	
})();

// 商品详情图的选择
(function() {
	
	let $tempSel = $(``),
	    picSet   = new Set(),
	    $wrapper = $("#detail_scroll_wrapper");
	addNewTempSel();
	
	// 添加事件
	function addEvent($target) {
		$target.find("input").on("change", function(ev) {
			let file = this.files[0];
			console.dir(this);
			if(!file) return;
			if(picSet.has(file.name)) {
				alert(`你已经选择了一张名为"${file.name}"请不要选择同一的图片....... `);
				return;
			}
			picSet.add(file.name);
			let $father = $(this).parent(),
			    objUrl  = getObjectURL(this.files[0]);
			$father.find("img").attr("src", objUrl);
			if($father.find(".icon-close").length === 0) {
				let $close = $(`<span class="icon-close" ></span>`);
				$father.append($close);
				$close.on("click", function() {
					picSet.delete(file.name);
					$target.remove();
					return false;
				});
				addNewTempSel();
			}
			//设置隐藏变量为空
			$(this).parent().find("input[type='hidden']").val("");
		});
	}
	
	// 添加新的sel, 并返回添加的新元素
	function addNewTempSel() {
		let $clone = $tempSel.clone();
		$wrapper.append($clone);
		$("#detail_scroll_wrapper label").each(function(index, item){
			addEvent($(item));
		});
		//addEvent($clone);
	}
	
})();

//建立一個可存取到該file的url
function getObjectURL(file) {
	let url = null;
	if(window.createObjectURL !== undefined) { // basic
		url = window.createObjectURL(file);
	} else if(window.URL !== undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if(window.webkitURL !== undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}

// 点击添加一个条目
(function() {
	
	let trSet = new Set();
	
	$("#addItem").on("click", function() {
		let $color      = $("#color"),
		    $size       = $("#size"),
		    $proContent = $("#pro_content"),
		    color       = $.trim($color.val()),
		    size        = $size.val();
		
		//验证数据
		if(color === null || color === "") {
			$color.addClass("input_error");
			return;
		}
		else if(size === null || size === "") {
			$color.removeClass("input_error");
			$size.addClass("input_error");
			return;
		}
		else {
			$color.removeClass("input_error");
			$size.removeClass("input_error")
		}
		
		if(hasSameParam(color, size)) {
			alert("亲, 不要添加相同属性的数据");
			return;
		}
		trSet.add({color: color, size: size});
		let html = getNewTr(color, size);
		
		$proContent.append(html);
	});
	
	// 判断是否有重复数据
	function hasSameParam(color, size) {
		let r = false;
		
		trSet.forEach(function(item) {
			if(item.color === color && item.size === size) {
				r = true;
				return false;
			}
		});
		return r;
	}
	
})();


let $modal =$("#picModal");

//初始绑定规格属性事件(原图好缩略图)
bindEvent($("#pro_content .thumbnail-block"));
bindEvent($("#pro_content .original-block"));

function getNewTr(color, size) {
	let html  = `<tr>
                                <td>
                                    <label>
                                        <select id="size" name="size">
                                            <option value="6寸" ${size === "6寸" ? 'selected' : ""}>4*6</option>
                                            <option value="7寸" ${size === "7寸" ? 'selected' : ""}>5*7</option>
                                            <option value="8寸" ${size === "8寸" ? 'selected' : ""}>6*8</option>
                                        </select>
                                    </label>
                                </td>
                                <td>
                                    <label>
                                        <input type="text" value="${color}" id="color" name="color">
                                    </label>
                                </td>
                                <td>
                                    <div class="input-prepend">
                                        <span class="add-on">¥</span>
                                        <input class="span2" id="" type="number" placeholder=".00"
                                               style="width: 100px;" step="0.01" id="price" name="price">
                                    </div>
                                </td>
                                <td>
                                    <div class="input-prepend">
                                        <span class="add-on">¥</span>
                                        <input class="span2" type="number" placeholder=".00" style="width: 100px;" step="0.01" id="packageprice" name="packageprice">
                                    </div>
                                </td>
                                <td>
                                    <div class="input-prepend">
                                        <input class="span2" id="inventory" name="inventory" type="number" placeholder="0"
                                               style="width: 100px;" step="1">
                                    </div>
                                </td>
                                <td class="thumbnail-block">
                                    <label class="file-sel">
                                        <span>点击选择缩略图</span>
                                        <input type="file" class="hide originalPic" name="originalImg" data-url="">
                                        <input type="hidden" name="original" value="">
                                    </label>
                                    <div class="operate hide" style="position: relative">
                                    	<a href="javascripnt:;" class="btn btn-small preview">预览</a>
                                    	<a href="javascripnt:;" class="btn btn-small replace">替换</a>
                                    	
																		</div>
                                </td>
                                <td class="original-block">
                                    <label class="file-sel">
                                        <span>点击选择缩略图</span>
                                        <input type="file" class="hide thumbnailPic" name="thumbnailImg" data-url="">
                                        <input type="hidden" name="original" value="">
                                    </label>
                                    <div class="operate hide" style="position: relative">
                                    	<a href="javascripnt:;" class="btn btn-small preview">预览</a>
                                    	<a href="javascripnt:;" class="btn btn-small replace">替换</a>
                                    	
																		</div>
                                </td>
                                <td>
                                    <label><input id="height" name="height" type="number" value="0" style="width: 100px;" ></label>
                                </td>
                                <td>
                                    <label><input id="width" name="width" type="number" value="0" style="width: 100px;"></label>
                                </td>
                                <td><input type="button" value="删除" onclick="deleteRecord(this)"></td>
                            </tr>`;
	let $html = $(html);
	
	bindEvent($html.find(".thumbnail-block"));
	bindEvent($html.find(".original-block"));
	
	
	// 全选功能
	//$html.find("input[type=checkbox]").on("change", function() {
	//	if(this.checked) {
	//		let value = true;
	//		$("tbody input[type=checkbox]").each(function(i, item) {
	//			if(!item.checked) {
	//				value = false;
	//				return false;
	//			}
	//		});
	//		$("#selectAll").attr("checked", value);
	//	} else {
	//		$("#selectAll").attr("checked", this.checked);
	//	}
	//
	//});
	return $html;
}

function bindEvent( $wrapper) {
	let $fileSel = $wrapper.find(".file-sel"),
			$operate = 	$wrapper.find(".operate"),
			$input = $wrapper.find("input"),
			$replace = $wrapper.find(".replace"),
			$del = $wrapper.find(".del"),
			$preview = $wrapper.find(".preview"),
			$picShow = $("#picShow"),
			file = null, 
			url = $preview.data("url");
	console.log($picShow[0]);
	// 点击选择图片
	$fileSel.on("click", function() {
		$fileSel.fadeOut();
		$operate.fadeIn();
	});
	
	$input.on("change", function() {
		if(!this.files[0]) return;
		file = this.files[0];
		url = getObjectURL(file);
		console.log(url, file);
	});
	//  点击预览图片
	$preview.on("click", function() {
		$picShow[0].src = url;
		$modal.modal();
	});
	// 点击更换图片
	$replace.on("click", function() {
		//alert(1);
		$(this).parent().parent().find("input[type='hidden']").value("");
		$(this).parent().parent().find("input[type='file']").trigger("click");
	});
	// 点击删除
	$del.on("click", function() {
		$fileSel.fadeIn();
		$operate.fadeOut();
	})
}



//设置图片的name属性
function setInputName(){
	//设置规格的缩略图
	$("input[type='file']").each(function(index, item){
		$(this).attr("name", $(this).attr("name") + index);
	});
}

//设置轮播图name属性
function setScrollImage(){
	$("#pro_scroll_wrapper input[type='file']").each(function(index, item){
		$(this).attr("name", $(this).attr("name") + index);
	});
	$("#pro_scroll_wrapper input[type='hidden']").each(function(index, item){
		$(this).attr("name", "scrollimg" + index);
	});
}

//设置商品详情图的name属性
function detailImage(){
	$("#detail_scroll_wrapper input[type='file']").each(function(index, item){
		$(this).attr("name", $(this).attr("name") + index);
	});
	$("#detail_scroll_wrapper input[type='hidden']").each(function(index, item){
		$(this).attr("name", "detailImg" + index);
	});
}

//设置规格的name属性
function setSpecvalueName(){
	//设置规格的缩略图
	$("#pro_content tr").each(function(index, tr){
		$(tr).find("select[name=size]").attr("name", "specvalueList["+index+"].size");
		$(tr).find("input[name=color]").attr("name", "specvalueList["+index+"].color");
		$(tr).find("input[name=price]").attr("name", "specvalueList["+index+"].price");
		$(tr).find("input[name=packageprice]").attr("name", "specvalueList["+index+"].packageprice");
		$(tr).find("input[name=height]").attr("name", "specvalueList["+index+"].height");
		$(tr).find("input[name=width]").attr("name", "specvalueList["+index+"].width");
		$(tr).find("input[name=inventory]").attr("name", "specvalueList["+index+"].inventory");
		$(tr).find("input[name=id]").attr("name", "specvalueList["+index+"].id");
		$(tr).find("input[name=original]").attr("name", "specvalueList["+index+"].original");
		$(tr).find("input[name=thumbnail]").attr("name", "specvalueList["+index+"].thumbnail");
	});
}


//保存产品
function saveEditProduct(){
	//alert(1);
	//设置属性名
	setInputName();
	
	//设置规格属性name属性
	setSpecvalueName();
	
	//重新设置轮播图name属性
	setScrollImage();
	
	//设置商品详情图的name属性
	detailImage();
	
	//轮播图url连接串
	var strFileName = "";
	$("#pro_scroll_wrapper input[type='hidden']:not(':last')").each(function(index, item){
		var str = $(this).val();
		if(str == ''){
			str = "null";
		}
		strFileName = strFileName + str + ",";
	});
	//赋值
	$("#lunboImage").val(strFileName);
	
	//详情图url连接串
	var detailFileName = "";
	$("#detail_scroll_wrapper input[type='hidden']:not(':last')").each(function(index, item){
		var str = $(this).val();
		if(str == ''){
			str = "null";
		}
		detailFileName = detailFileName + str + ",";
	});
	//赋值
	$("#detailImg").val(detailFileName);
	
	/*//产品规格属性url连接串--原图
	var originalName = "";
	$("#pro_content td").find(".originalPic").each(function(index, item){
		var str = $(this).data("url");
		if(str == ''){
			str = "null";
		}
		originalName = originalName + str + ",";
	});
	
	alert(originalName);
	//产品规格属性url连接串--缩略图
	var thumbnailName = "";
	$("#pro_content td").find(".thumbnailPic").each(function(index, item){
		var str = $(this).data("url");
		if(str == ''){
			str = "null";
		}
		thumbnailName = thumbnailName + str + ",";
	});
	
	
	
	
    return false;*/
	
	
	$("#addProductForm").attr("action", "shopping/editProduct");
	$("#addProductForm").submit();
}

var options = {
  success:  showResponse
 };

function showResponse() {
	var url = "/shopping/toProduct";
	$("#content").empty();
	$("#content").load(url);
}
//ajax提交form表单
$('#addProductForm').submit(function() {
	  $(this).ajaxSubmit(options);
	  return false; 
});

//删除图片
$(".icon-close").on('click',function(e){ 
	e.stopPropagation();//阻止冒泡 
	$(this).parent().remove();
	return false;
});

//删除规格
function deleteRecord(obj){
	$(obj).parent().parent().remove();
}



