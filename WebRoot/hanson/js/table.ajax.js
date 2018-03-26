/**
 * 抽象化表格
 */
function abstractTable(){
    // ---------内容属性
	this.paramData = {};
	this.cache = false;	//是否使用AJAX,默认为false
	this.url = "";	//AJAX接口路径
    this.id = null;         // 每个表格都有唯一的一个id
    this.tableobj = null;  //表格对象
    this.rowNum = 0;       //行数
    this.colNum = 0;      //列数
    this.header = [];     //表头数据
    this.content = [];   //body数据
    // ----------提供外部使用获得表格内部数据
    this.currentClickRowID = 0;   //当前点击的行数据
    // --- 通过表头来获得这张表的列数
    this.getColNum = function(){
        this.colNum = this.header.length;
        return   this.colNum;
    };
    // -----------  表格自我构建行为
    this.clearTable = function(){};
    this.showHeader = function(){};
    this.showContent = function(begin,end){};
    this.showFoot = function(){};

    // --------- 分页功能属性
    this.allDataNum = 0;  // 总数据条数
    this.displayNum = 10; // 每页显示条数
    this.maxPageNum = 0;  // 最大页码值
    this.currentPageNum =1;// 当前页码值
    //tfoot分页组
    this.groupDataNum = 10;  //每组显示10页
    this.groupNum = 1;       //当前组

    // -------- 分页功能行为
    this.paginationFromBeginToEnd = function(begin,end){};
    this.first =  function(){};//首页
    this.last = function(){};//最后一页
    this.prev = function(){};//上一页
    this.next = function(){};//下一页
    this.gotoPage = function(){}; //跳到某页

    // ----------- 表格初始化
    this.init = function(begin,end){};

}

/*
 表格对象模板
 */
function tableTemplet(table_id){
    abstractTable.call(this);
    this.id = table_id;
}
/**
 * 表格对象
 * @param options
 */
function table(options){
    if(!options){return;}
    if(!$.isPlainObject(options)){return;}

    tableTemplet.call(this,options.tableId);
    //得到表格对象
    this.tableobj = $("#"+this.id);
    //清空表格内容
    this.clearTable = function(){
        this.tableobj.html(" ");
    };
    // 实现分页行为
    this.paginationFromBeginToEnd= function(x,y){
        this.maxPageNum = Math.ceil(this.allDataNum/this.displayNum);
        var arrPage = [];
        for(var i= x;i<y;i++){
            arrPage.push(this.content[i]);
        }
        return arrPage;
    };
    
    this.showHeader = function(){
        if(this.header != null){
           var  $thead = $("<thead>"),
                $tr = $("<tr>"),
                $th;
            for(var i=0;i<this.colNum;i++){
                $th = $("<th style='width:100px;'>").html(this.header[i].title);
                $th.appendTo($tr);
            }
            $tr.appendTo($thead);
            $thead.appendTo(this.tableobj);
        }else{
        	
        };
    };
    //初始化tbody
    this.showContent = function(begin,end){
    	
        if(this.content != null){
            var $tbody = $("<tbody>"),
                $tr,
                $td;
            
            var tempDaTa = this.paginationFromBeginToEnd(begin,end),
                len = tempDaTa.length;
            // 循环创建行
            for(var i=0;i<len;i++){
                $tr = $("<tr >").appendTo($tbody);
                if(i%2==1){
                    $tr.addClass("evenrow");
                }
                
                	
                console.log(tempDaTa[i].id);
                // 循环创建列  取得对象中的键
                for(var key in tempDaTa[i]){
                    $td = $("<td>").html(tempDaTa[i][key]).appendTo($tr);
                }
            }
            this.tableobj.append($tbody);
        }else{
        	
        }
    };
    //初始化tbody Ajax方式
    this.showContentAjax = function(){
    	
        if(this.content != null){
        	this.maxPageNum = Math.ceil(this.allDataNum/this.displayNum);
            var $tbody = $("<tbody>"),
                $tr,
                $td;
            var headDaTa = this.header;
            var tempDaTa = this.content,
                len = tempDaTa.length;
            // 循环创建行
            for(var i=0;i<len;i++){
                $tr = $("<tr>").appendTo($tbody);
                if(i%2==1){
                    $tr.addClass("evenrow");
                }
                $tr.attr("data-id", tempDaTa[i].id);
//                console.log(tempDaTa[i].id);
                // 循环创建列  取得对象中的键
                for(var key in headDaTa){
//                	console.log(headDaTa[key].title);
                	var code = headDaTa[key].code;
//                	console.log(tempDaTa[i][code]);
                	
                    $td = $("<td>").html(tempDaTa[i][code]).appendTo($tr);
                }
            }
            this.tableobj.append($tbody);
        }
    };
    //初始化tfoot
    this.showFoot = function(){
//    	console.log(this.colNum);
       var $tfoot = $("<tfoot>"),
           $tr = $("<tr>"),
           $td = $("<td>").attr("colspan",this.colNum).addClass("paging");
           $tr.append($td);
           $tfoot.append($tr);
           this.tableobj.append($tfoot);
           this.pagination($td);
    };
    //表格分页
    this.pagination = function(tdCell){
        var $td= typeof(tdCell) == "object" ? tdCell : $("#" + tdCell);
        //首页
        var oA = $("<a/>");
        oA.attr("href","#1");
        oA.html("首页");
        $td.append(oA);
        //上一页
        if(this.currentPageNum>=2){
            var oA = $("<a/>");
            oA.attr("href","#"+(this.currentPageNum - 1));
            oA.html("上一页");
            $td.append(oA);
        }
//        console.log(this.maxPageNum);
        //普通显示格式
        console.log(this.maxPageNum);
        if(this.maxPageNum <= this.groupDataNum){  // 10页以内 为一组
            for(var i = 1;i <= this.maxPageNum ;i++){
                var oA = $("<a/>");
                oA.attr("href","#"+i);
                if(this.currentPageNum == i){
                    oA.attr("class","current");
                }
                oA.html(i);
                $td.append(oA);
            }
        }else{//超过10页以后（也就是第一组后）
        	console.log(this.groupNum);
             if(this.groupNum<=1){//第一组显示
                 for(var j = 1;j <= this.groupDataNum ;j++){
                     var oA = $("<a/>");
                     oA.attr("href","#"+j);
                     if(this.currentPageNum == j){
                         oA.attr("class","current");
                     }
                     oA.html(j);
                     $td.append(oA);

                 }
             }else{//第二组后面的显示
                 var begin = (this.groupDataNum*(this.groupNum-1))+ 1,
                      end ,
                     maxGroupNum = Math.ceil(this.maxPageNum/this.groupDataNum);
                 if(this.maxPageNum%this.groupDataNum!=0&&this.groupNum==maxGroupNum){
                     end = this.groupDataNum*(this.groupNum-1)+this.maxPageNum%this.groupDataNum;
                 }else{
                     end = this.groupDataNum*(this.groupNum);
                 }

                 for(var j = begin;j <= end ;j++){
                     var oA = $("<a/>");
                     oA.attr("href","#"+j);
                     if(this.currentPageNum == j){
                         oA.attr("class","current");
                     }
                     oA.html(j);
                     $td.append(oA);
                 }
             }
        }
        //下一页
        if( (this.maxPageNum - this.currentPageNum) >= 1 ){
            var oA = $("<a/>");
            oA.attr("href","#" + (this.currentPageNum + 1));
            oA.html("下一页");
            $td.append(oA);
        }
        //尾页
        var oA = $("<a/>");
        oA.attr("href","#" + this.maxPageNum);
        oA.html("尾页");
        $td.append(oA);

        var page_a = $td.find('a');
        var tempThis = this;

        page_a.unbind("click").bind("click",function(){
            var nowNum =  parseInt($(this).attr('href').substring(1));
//            tempThis.currentPageNum = nowNum;
            console.log(nowNum);
//            console.log("tempThis.currentPageNum ------------ "+tempThis.currentPageNum);
//            console.log("nowNum ------------ "+nowNum);
            if(nowNum>tempThis.currentPageNum){//下一组
                if(tempThis.currentPageNum%tempThis.groupDataNum==0){
                    tempThis.groupNum += 1;

                    var maxGroupNum = Math.ceil(tempThis.maxPageNum/tempThis.groupDataNum);
                    if(tempThis.groupNum>=maxGroupNum){
                        tempThis.groupNum = maxGroupNum;
                    }
                }
            }
            if(nowNum<tempThis.currentPageNum){//上一组
                if((tempThis.currentPageNum-1)%tempThis.groupDataNum==0){
                    tempThis.groupNum -= 1;
                    if(tempThis.groupNum<=1){
                        tempThis.groupNum = 1;
                    }
                }
            }
            if(nowNum==tempThis.maxPageNum){//直接点击尾页
                var maxGroupNum = Math.ceil(tempThis.maxPageNum/tempThis.groupDataNum);
                tempThis.groupNum = maxGroupNum;
            }
            if(nowNum==1){
                var maxGroupNum = Math.ceil(tempThis.maxPageNum/tempThis.groupDataNum);
                tempThis.groupNum = 1;
            }
            tempThis.currentPageNum = nowNum;

            tempThis.init((tempThis.currentPageNum-1)*tempThis.displayNum,
                tempThis.currentPageNum*tempThis.displayNum);
            return false;
        });
    };
    //初始化
    this.init = function(begin,end){
    	this.header = options.headers;
        this.colNum = this.header.length;
        this.header = options.headers;
    	if(options.cache){
	        this.cache = options.cache ;
	    }
    	if(options.url){
	        this.url = options.url ;
	    }
	    if(options.currentPageNum){
	        this.currentPageNum = end/options.displayNum ;
	    }
	    if(options.displayNum){
            this.displayNum = options.displayNum;
        }
	    if(options.groupDataNum){
            this.groupDataNum = options.groupDataNum;
        }
	    
    	this.clearTable();
        this.showHeader();
        var content = {};
        if(this.cache){
//        	console.log(this.paramData);
//        	console.log(options.paramData);
        	var paramDatas = {};
    	    //分页参数
    	    paramDatas.page = this.currentPageNum;
    	    paramDatas.rows = this.displayNum;
    	    //获取、组合页面中的筛选条件
//    	    this.paramData = options.paramData;
    	    for(var key in this.paramData){
    	    	paramDatas[this.paramData[key].name] = this.paramData[key].value;
            }
    	    
    		$.ajax({
            	url: this.url,
            	type:"POST",
            	async:false,
            	dataType:"json",
            	data:paramDatas,
            	success:function(result){
                 	console.log(result);
            		content = result;
            	}
            });
    		this.content = content.rows;
    		this.allDataNum = content.total;
    		this.showContentAjax();
    		this.showFoot();
    	}else{
    		this.content = options.data;
    		this.allDataNum = options.allDataNum;
    		this.showContent(begin,end);
    		this.showFoot();
    	};
    	
    };
    
    this.init((options.currentPageNum-1) * options.displayNum, options.currentPageNum * options.displayNum);
}