<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.hanson.util.Constant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="hanson/css/common.css" />
<link rel="stylesheet" type="text/css" href="hanson/css/table.css" />
<link rel="stylesheet" href="hanson/css/datepicker.css" />
<link rel="stylesheet" href="hanson/css/select2.css" />
<link rel="stylesheet" href="hanson/css/toastr.css" />
<link rel="stylesheet" href="hanson/css/bootstrap.min.css" />
<link rel="stylesheet" href="hanson/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="hanson/css/fullcalendar.css" />
<link rel="stylesheet" href="hanson/css/uniform.css" />
<link rel="stylesheet" href="hanson/css/select2.css" />
<link rel="stylesheet" href="hanson/css/unicorn.main.css" />
<link rel="stylesheet" href="hanson/css/unicorn.grey.css"
	class="skin-color" />
<link rel="stylesheet"
	href="//at.alicdn.com/t/font_469373_f1ivqovej8j8aor.css">
<style>
.select2-drop {
	z-index: 99999
}

.MDlist {
	position: absolute;
	bottom: -102px;
	left: 175px;
	background-color: #fff;
	width: 52%;
	height: 100px;
	overflow-y: scroll;
	border: 1px solid #ddd;
	display: none;
}

.MDlist li {
	text-align: center;
	height: 30px;
	line-height: 30px;
}

.MDlist li:hover {
	background-color: #21C6F2;
	color: #fff;
}

.modeloading {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, .4);
	display: none;
}

.modeloading img {
	position: absolute;
	top: 50%;
	left: 50%;
	margin-left: -64px;
	margin-top: -64px;
}

/*2018.03.03*/
.panel-body {
	padding: 10px 0;
	font-size: 16px;
	display: flex;
}

.btn-body select {
	margin: 0
}

.panel-body span {
	margin: 0 10px 0 5px;
}

.searchform {
	display: flex;
	white-space: nowrap;
	flex-wrap: wrap;
	align-items: center;
	line-height: 40px;
}

.searchform input,.searchform select {
	margin: 0 10px 0
}
</style>

<style>
.clearfix::before,.clearfix::after{
    content: '';
    display: block;
    clear: both;
    font-size: 0;
    height:0;
}
ul{
    list-style: none;
}
.cursor_pointer{
    cursor: pointer;
}
body{
    font-size: 18px;
    padding:0;
    margin:0;
}
/*标题*/
#head_title{
    width:100%;
    height: 60px;
    background: #ccc;
    position: relative;
    line-height: 60px;
    text-indent: 80px;
}
#head_title img{
    position: absolute;
    left: 30px;
    top: 15px;
    width:26px;
    height: 30px;
}
#app{
    width:90%;
    margin:50px auto;
    position: relative;
}
/*table*/
.manage_table{
    width:100%;
    border-collapse: collapse;
}
.manage_table td{
    text-align: center;
    height: 150px;
}
.manage_table .left_name{
    width:20%;
}
.manage_table .center_content{
    width:60%;
}
.manage_table .right_operate{
    width:20%;
}
/*弹框*/
.edit_dialog{
    position: absolute;
    left:0;
    top: 50px;
    width:100%;
    height: 600px;
    display: none;
}
.edit_dialog .content{
    width: 600px;
    height: 600px;
    margin:0 auto;
    background: #fff;
    border:1px solid #666;
}
/*标题*/
.edit_dialog .title{
    height: 40px;
    border-bottom: 1px solid #666;
    position: relative;
}
.edit_dialog .title span{
    line-height: 40px;
    margin-left: 12px;
}
.edit_dialog .title .clear_dialog{
    width:16px;
    height: 16px;
    position: absolute;
    right: 12px;
    top: 12px;
}
/*当前关键字*/
.current_value li{
    width: 200px;
    height: 60px;
    border:1px solid #666;
    float: left;
    margin:0 0 14px 40px;
    position: relative;
    text-align: center;
    line-height: 60px;
    cursor: pointer;
    border-radius: 4px;
}
.current_value .selected_value{
    border-color: orangered;
    color: orangered;
}
.current_value li .clear_value{
    width:26px;
    height:26px;
    position: absolute;
    right:0;
    top:0;
}
/*编辑关键字*/
.edit_value .title{
    padding-left: 60px;
    height: 30px;
    line-height: 30px;
    position: relative;
    border:none;
}
.edit_value .title img{
    width:26px;
    height: 26px;
    position: absolute;
    left: 20px;
    top: 2px;
}
.edit_value .edit_area{
    height: 50px;
    margin-top: 15px;
    border-bottom: 1px solid #ccc;
    padding-bottom: 30px;
}
.edit_area .input_wrap{
    float: left;
}
.edit_area .edit_btn{
    float: left;
    width: 60px;
    height:30px;
    border:1px solid orange;
    color: orange;
    text-align: center;
    line-height: 30px;
    margin:10px 0 0 20px;
    border-radius: 4px;
    cursor: pointer;
}
.edit_area .input_wrap{
    margin-left: 60px;
    width:300px;
    height: 50px;
    border:1px solid #666;
    position: relative;
}
.edit_area .input_wrap input{
    width:100%;
    height: 50px;
    border:0;
    padding:0;
    font-size: 16px;
}
.edit_area .input_wrap .clear_input{
    width: 16px;
    height: 16px;
    position: absolute;
    right: 17px;
    top: 17px;
    cursor: pointer;
}
/*提示*/
.edit_dialog .tips{
    color: #6c6c6c;
    height: 30px;
    line-height: 30px;
    margin-bottom: 20px;
}
/*操作按钮*/
.dialog_btns .cancel_btn{
    width:90px;
    height: 30px;
    border:1px solid orange;
    color: orange;
    text-align: center;
    line-height: 30px;
    border-radius: 4px;
    float: right;
    margin-right: 30px;
    cursor: pointer;
}
.dialog_btns .assure_btn{
    width: 92px;
    height:32px;
    background: orange;
    color: #fff;
    text-align: center;
    line-height: 30px;
    border-radius: 4px;
    float: right;
    margin-right: 15px;
    cursor: pointer;
}
</style>
<script src="hanson/js/jquery.min.js"></script>
<script src="hanson/js/hanson.commentConfiguration.js" type="text/javascript"
	charset="utf-8"></script> 
	
<div id="head_title">评价关键字配置 <img src="hanson/img/u13887.png" alt=""></div>
<div id="app">
    <!--配置表-->
    <table class="manage_table" border="2">
        <tr>
            <td class="left_name">分类名称</td>
            <td class="center_content">评价关键字</td>
            <td class="right_operate">操作</td>
        </tr>
        <tr>
          <input type="hidden" value="1"/>
            <td class="left_name">一星评价</td>
            <td class="center_content" id="star1">价格高；服务到位；产品质量好；性价比高；服务态度好；物流快；效果好；门店漂亮</td>
            <td class="right_operate cursor_pointer edit_begin">编辑</td>
        </tr>
        <tr>
          <input type="hidden" value="2"/>
            <td class="left_name">二星评价</td>
            <td class="center_content" id="star2">价格高；服务到位；产品质量好；性价比高；服务态度好；物流快；效果好；门店漂亮</td>
            <td class="right_operate cursor_pointer edit_begin">编辑</td>
        </tr>
        <tr>
          <input type="hidden" value="3"/>
            <td class="left_name">三星评价</td>
            <td class="center_content" id="star3">价格高；服务到位；产品质量好；性价比高；服务态度好；物流快；效果好；门店漂亮</td>
            <td class="right_operate cursor_pointer edit_begin">编辑</td>
        </tr>
        <tr>
          <input type="hidden" value="4"/>
            <td class="left_name">四星评价</td>
            <td class="center_content" id="star4">价格高；服务到位；产品质量好；性价比高；服务态度好；物流快；效果好；门店漂亮</td>
            <td class="right_operate cursor_pointer edit_begin">编辑</td>
        </tr>
        <tr>
           <input type="hidden" value="5"/>
            <td class="left_name">五星评价</td>
            <td class="center_content" id="star5">价格高；服务到位；产品质量好；性价比高；服务态度好；物流快；效果好；门店漂亮</td>
            <td class="right_operate cursor_pointer edit_begin">编辑</td>
        </tr>
    </table>
    <!--弹框-->
    <div class="edit_dialog">
        <div class="content">
            <!--标题-->
            <div class="title">
                <span>关键字编辑</span>
                <img src="hanson/img/u11767.png" alt="" class="clear_dialog cursor_pointer">
            </div>
            <!--当前关键字-->
            <ul class="current_value clearfix" id="kewWord">
             </ul>
             <!--用来确-->
             <input type="hidden" id="starLevel" value="0"/>
            <!--编辑关键字-->
            <div class="edit_value">
                <div class="title">编辑关键字 <img src="hanson/img/u15343.png" alt=""></div>
                <div class="edit_area">
                    <div class="input_wrap">
                        <input type="text" placeholder="请输入评价关键字" id="edit_value_input">
                        <img src="hanson/img/u11767.png" alt="" class="clear_input" id="clear_value_input">
                    </div>
                    <div class="edit_btn" id="add_value_btn">添加</div>
                    <div class="edit_btn" id='change_value_btn'>修改</div>
                </div>
            </div>
            <!--提示-->
            <div class="tips">*  温馨提示：评价关键字最多仅能显示8个，最少不低于2个。</div>
            <!--操作按钮-->
            <div class="dialog_btns">
                <div class="assure_btn">确定</div>
                <div class="cancel_btn">取消</div>
            </div>
        </div>
    </div>
</div>
<script>
    var page={
        data:{

        },
        onload:function(){
            this.bind_event();
        },
        bind_event:function () {
            var self=this;
//            点击编辑
            $('.edit_begin').click(function(){
               var star=$(this).parent().find("input").val();
               getCommentTagByStarLevel(star);
                if($('.edit_dialog').css('display') == 'none')
                {
                    $('.edit_dialog').show();
                }
            });
//            点击弹框关闭图标
            $('.clear_dialog').click(function(){
                self.close_dialog();
            });
//            点击取消按钮
            $('.cancel_btn').click(function(){
                self.close_dialog();
            });
//            点击确定按钮
            $('.assure_btn').click(function(){
                 consure();
                self.close_dialog();
            });
//            选中关键字
            $('.current_value').delegate('li','click',function(){
                var ev=event || window.event;
                var target=ev.srcElement || ev.target;
                if(target.tagName.toLowerCase() == 'img')
                {
                    return
                }
//                改变关键字样式
                $('.current_value li').removeClass('selected_value');
                $(this).addClass('selected_value');
//                改变关键字输入框值
                var value=$(this).find('span');
                $('#edit_value_input').val(value[0].innerHTML)
            });
//            删除关键字
             $('.current_value').delegate('.clear_value','click',function(){
                if($('.current_value li').length <= 2)
                {
                    alert('关键字评价选项小于2项！');
                    return;
                }
                var id=$(this).parent('li').find("input").val();
                deleteCommentTag(id);
                $(this).parents('li').remove();
            }); 
//            添加关键字
            $('#add_value_btn').click(function(){
//                判断是否超过8个
                if($('.current_value li').length >= 8)
                {
                    alert('关键字评价选项不能大于8项！');
                    return;
                }
//                获取输入的值并判断是否为空
                var value=$('#edit_value_input').val();
                if(!value)
                {
                    alert('请输入关键字');
                    return;
                }
//                添加到关键字列表
                var htmlStr='<li><input type="hidden" value="0"/><span>'+value+'</span><img src="hanson/img/u12321.png" alt="" class="clear_value"></li>';
                var $li=$(htmlStr);
                $li.appendTo($('.current_value'));
            });
//            清空关键字输入框
            $('#clear_value_input').click(function(){
                $('#edit_value_input').val('');
            });
//            修改关键字
            $('#change_value_btn').click(function(){
//                当前是否有选中的关键字项
                var $value=$('.current_value li.selected_value');
                if($value.length < 1)
                {
                    alert('请选中需要修改的关键字');
                    return;
                }
//                关键字输入框是否为空
                if(!$('#edit_value_input').val())
                {
                    alert('请输入新的关键字');
                    return;
                }
//                修改关键字
                $value.find('span').html($('#edit_value_input').val());
            })
        },
//        关闭弹框
        close_dialog:function(){
            $('.edit_dialog').hide();
        }
    }
    $(function(){
        page.onload();
    })

</script>

