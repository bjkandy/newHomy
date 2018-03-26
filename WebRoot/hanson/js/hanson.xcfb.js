// 主页图片选择
$("input[name='mainimg'], input[name='thumbnail']").on("change", function() {
	let objUrl = getObjectURL(this.files[0]);
	if(objUrl) {
		$(this).parent().find("img").attr("src", objUrl);
	}
});

// 商品轮图的选择
(function() {
	let $tempSel_1 = $(`<label class="pull-left pic-sel pic-wrapper" >
					    <img src="hanson/img/image_add.jpg" class="add-img">
					    <input type="file" name="templateImage" class="index_file_input hide" accept="image/jpg,image/jpeg,image/png,image/bmp"/>
					</label>`);
	
	let $tempSel = $(`<label class="pull-left pic-sel pic-wrapper" >
                         <img src="hanson/img/image_add.jpg" class="add-img">
                         <input type="file" name="scrollimg" class="index_file_input hide" accept="image/jpg,image/jpeg,image/png,image/bmp"/>
                     </label>`),
	    picSet   = new Set(),
	    $wrapper = $("#pro_scroll_wrapper"),
		$wrapper1 = $("#pro_scroll_wrapper1");
	addNewTempSel();
	
	// 添加事件
	function addEvent($target) {
		$target.find("input").on("change", function(ev) {
			let file = this.files[0];
			console.dir(this);
			if(!file) return;
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
				addNewTempSel1(this);
			}
		});
	}
	
	// 添加新的sel, 并返回添加的新元素
	function addNewTempSel() {
		let $clone = $tempSel.clone();
		let $clone1 = $tempSel_1.clone();
		$wrapper.append($clone);
		$("#pro_scroll_wrapper1").append($clone1);
		addEvent($clone);
		addEvent($clone1);
	}
	
	// 添加新的sel, 并返回添加的新元素
	function addNewTempSel1(obj) {
		let $clone = $tempSel.clone();
		let $clone1 = $tempSel_1.clone();
		//判断当前操作的是对象是模板图还是录播图
		var name = $(obj).attr("name");
		if(name == 'scrollimg'){
			$(obj).parent().parent().append($clone);
			addEvent($clone);
		}else{
			$(obj).parent().parent().append($clone1);
			addEvent($clone1);
		}
		
	}
	
})();

// 商品详情图的选择
(function() {
	
	let $tempSel = $(`<label class="pic-sel" >
                         <img src="hanson/img/image_add.jpg" class="add-img">
                         <input type="file"  name="detialImg" class="index_file_input hide" accept="image/jpg,image/jpeg,image/png,image/bmp"/>
                     </label>`),
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
		});
	}
	
	// 添加新的sel, 并返回添加的新元素
	function addNewTempSel() {
		let $clone = $tempSel.clone();
		$wrapper.append($clone);
		addEvent($clone);
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
var trSet = new Set();
(function() {
	
	
	$("#addItem").on("click", function() {
		let $size       = $("#size"),
		    $proContent = $("#pro_content"),
		    size        = $size.val();
		
		//验证数据
		if(size === null || size === "") {
			$size.addClass("input_error");
			return;
		}else {
			$size.removeClass("input_error")
		}
		
		if(hasSameParam(size)) {
			alert("亲, 不要添加相同属性的数据");
			return;
		}
		trSet.add({size: size});
		let html = getNewTr(size);
		
		$proContent.append(html);
	});
	
	// 判断是否有重复数据
	function hasSameParam(size) {
		let r = false;
		
		trSet.forEach(function(item) {
			if(item.size === size) {
				r = true;
				return false;
			}
		});
		return r;
	}
	
	
})();
//删除规格
function deleteRecord(obj){
	let $size       = $(obj).parent().parent().find("select:eq(0)"),
	size = $size.val();
	
	trSet.forEach(function(item) {
		if(item.size === size) {
			item.size = "";
		}
	});
	$(obj).parent().parent().remove();
}

let $modal =$("#picModal");
function getNewTr(size) {
	let html  = `<tr>
                                <td>
                                    <label>
                                        <select id="size" name="size">
                                        	<option value="6*6英寸" ${size === "6*6英寸" ? 'selected' : ""}>6*6英寸</option>
                                            <option value="6*8英寸" ${size === "6*8英寸" ? 'selected' : ""}>6*8英寸</option>
                                            <option value="7*5英寸" ${size === "7*5英寸" ? 'selected' : ""}>7*5英寸</option>
                                            <option value="8*6英寸" ${size === "8*6英寸" ? 'selected' : ""}>8*6英寸</option>
                                            <option value="8*8英寸" ${size === "8*8英寸" ? 'selected' : ""}>8*8英寸</option>
                                            <option value="8*10英寸" ${size === "8*10英寸" ? 'selected' : ""}>8*10英寸</option>
                                            <option value="10*10英寸" ${size === "10*10英寸" ? 'selected' : ""}>10*10英寸</option>
                                            <option value="12*10英寸" ${size === "12*10英寸" ? 'selected' : ""}>12*10英寸</option>
                                            <option value="2.5*3.5英寸" ${size === "2.5*3.5英寸" ? 'selected' : ""}>2.5*3.5英寸</option>
					    <option value="银卡套餐" ${size === "银卡套餐" ? 'selected' : ""}>银卡套餐</option>
					    <option value="金卡套餐" ${size === "金卡套餐" ? 'selected' : ""}>金卡套餐</option>
					    <option value="钻石套餐" ${size === "钻石套餐" ? 'selected' : ""}>钻石套餐</option>
                                        </select>
                                    </label>
                                </td>
                                
                                <td>
                                    <div class="input-prepend">
                                        <span class="add-on">¥</span>
                                        <input class="span2 price" id="" placeholder=".00"
                                               style="width: 100px;" step="0.01" id="price" name="price" onkeyup="clearNoNum(this)">
                                    </div>
                                </td>
                               
                                <td>
                                    <div class="input-prepend">
                                        <input class="span2" id="inventory" name="inventory" type="number" placeholder="0"
                                               style="width: 100px;" step="1">
                                    </div>
                                </td>
                                <td><input type="button" value="删除" onclick="deleteRecord(this)"></td>
                            </tr>`;
	let $html = $(html);
	
	bindEvent($html.find(".thumbnail-block"));
	bindEvent($html.find(".original-block"));
	
	function bindEvent( $wrapper) {
		let $fileSel = $wrapper.find(".file-sel"),
				$operate = 	$wrapper.find(".operate"),
				$input = $wrapper.find("input"),
				$replace = $wrapper.find(".replace"),
				$del = $wrapper.find(".del"),
				$preview = $wrapper.find(".preview"),
				$picShow = $("#picShow"),
				file = null, url;
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
			console.log(url);
			$picShow[0].src = url;
			$modal.modal();
		});
		// 点击更换图片
		$replace.on("click", function() {
			$(this).parent().parent().find("input[type='hidden']").val("");
			$(this).parent().parent().find("input[type='file']").trigger("click");
		});
		// 点击删除
		$del.on("click", function() {
			$fileSel.fadeIn();
			$operate.fadeOut();
		})
	}
	
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


//设置图片的name属性
function setInputName(){
	//设置规格的缩略图
	$("input[type='file']").each(function(index, item){
		$(this).attr("name", $(this).attr("name") + index);
	});
}

//设置规格的name属性
function setSpecvalueName(){
	//设置规格的缩略图
	$("#pro_content tr").each(function(index, tr){
		$(tr).find("select[name=size]").attr("name", "specvalueList["+index+"].size");
		$(tr).find("input[name=price]").attr("name", "specvalueList["+index+"].price");
		$(tr).find("input[name=packageprice]").attr("name", "specvalueList["+index+"].packageprice");
		$(tr).find("input[name=inventory]").attr("name", "specvalueList["+index+"].inventory");
		
		
	});
}

//将规格属性中的原件和套餐价*100以分计算
function setSpecvaluePrice(){
	$("#pro_content tr").each(function(index, tr){
		var price = $(tr).find("input[name$='price']").val();
		$(tr).find("input[name$='.price']").val(price * 100);
	});
}

//保存产品
function saveProduct(){
	//设置属性名
	setInputName();
	setSpecvalueName();
	
	
	//将商品价格,规格属性中的价格和套餐价*100以分来计算
	var standardprice = $("#standardprice").val();
	$("#standardprice").val(standardprice*100);
	//将规格属性中的原件和套餐价*100以分计算
	setSpecvaluePrice();
	
	$("#addProductForm").attr("action", "album/saveProduct");
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



//只能输入整数或者两位小数
function clearNoNum(obj){  
	  obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符   
	  obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的   
	  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
	  obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数   
	  if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额  
	   obj.value= parseFloat(obj.value);  
	  }  
}


