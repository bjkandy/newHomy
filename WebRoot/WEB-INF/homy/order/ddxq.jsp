<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<script type="text/javascript">
//返回
function back(){
	var url = "/order/order";
	$("#content").empty();
	$("#content").load(url);
}

function refresh(obj){
	var url = "/order/orderDetail?orderid="+$(obj).attr("orderid");
	$("#content").empty();
	$("#content").load(url);
}
</script>

<head>

    <title>订单详情</title>

    <style>
        .space {
            height: 50px;
        }

        .fz-default {
            font-size: 20px;
            line-height: 1.8;
        }

        .text-light {
            color: #666;
        }

        .text-assist {
            color: #ad2a27;
        }

        table, tr, td, th {
            font-size: 20px;
        }

    </style>

</head>
<body>


<div id="main">


    <!--  基本信息 -->
    <div class="space"></div>
    <div class="row-fluid">
        <div class="span2 text-center">
            <h3 class="text-center">订单信息</h3>
        </div>
        <div class=" span10">
            <div class="row-fluid fz-default">
                <span class="span4">
                    <span class="">订单编号:</span>
                    <span class="">${order.orderid}</span>
                </span>
                <span class="span4">
                    <span class="">用户昵称:</span>
                    <span class="">${order.cuname}</span>
                </span>
            </div>
            <div class="row-fluid fz-default">
                <span class="span4">
                    <span class="">订单状态:</span>
                    <span class="">
                    	<c:if test="${order.paystatus eq 0}">未支付</c:if>
                    	<c:if test="${order.paystatus eq 10}">支付成功</c:if>
                    </span>
                </span>
                <span class="span4">
                    <span class="">生产状态:</span>
                    <span class="">
                    	<c:if test="${order.orderstatus eq 20}">未生产</c:if>
                    	<c:if test="${order.orderstatus eq 25}">生产完毕</c:if>
                    	<c:if test="${order.orderstatus eq 30}">已发货</c:if>
                    </span>
                </span>
                <span class="span4">
                    <span class="">支付方式:</span>
                    <span class="">
                    	<c:if test="${order.paytype eq 0}">支付宝支付</c:if>
                    	<c:if test="${order.paytype eq 1}">微信支付</c:if>
                    </span>
                </span>
            </div>
            <div class="row-fluid fz-default">
                <span class="span4">
                    <span class="">下单时间:</span>
                    <span class="">
                    	${order.createdate}
                    </span>
                </span>
                <span class="span4">
                    <span class="">支付时间:</span>
                    <span class="">
                    	${order.createdate}
                    </span>
                </span>
            </div>
            <div class="row-fluid fz-default">
                <span>
                    <span class="">备注:</span>
                    <span class="">
                    	${order.remark}
                    </span>
                </span>

            </div>
        </div>
    </div>
    <!-- 订单明细 -->
    <div class="space"></div>
    <div class="row-fluid fz-default">
        <div class="span2 text-center">
            <h3 class="text-center">订单明细</h3>
        </div>
        <div class=" span10">
            <div class="row-fluid">
                <table class="table text-center" border="1">
                    <thead>
                    <tr>
                        <td class="text-center">商品图</td>
                        <td class="text-center">商品</td>
                        <td class="text-center">商品价格(元)</td>
                        <td class="text-center">商品数量</td>
                        <td class="text-center">小计(元)</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${detail}" var="item" >
                    	<tr>
	                        <td>
	                        	<c:if test="${not empty item.productname}"><img src="${imageViewDir}${item.productname}" alt=""></c:if>
	                        	<c:if test="${empty item.productname}"><img src="hanson/img/print_defualt.png" alt=""></c:if>
	                        </td>
	                        <td>
	                            <p>${item.name}</p>
	                            <p class="text-light">
	                            	<c:if test="${item.producttype eq 10}">
	                            		材质:${item.size};
	                            	</c:if>
	                            		尺寸:${item.size};
	                            	<c:if test="${item.plastic eq 1}">
	                            		冲印类型:过塑
	                            	</c:if>
	                            	<c:if test="${item.plastic ne 1}">
	                            		冲印类型:不过塑
	                            	</c:if>
	                            	<c:if test="${item.producttype eq 20}">
	                            		颜色分类:${item.colour};
	                            	</c:if>
	                            	<c:if test="${item.producttype eq 20}">
	                            		<c:if test="${empty item.productname}">
	                            			套餐类型:相框;
	                            		</c:if>
	                            		<c:if test="${not empty item.productname}">
	                            			套餐类型:相框+照片;
	                            		</c:if>
	                            	</c:if>
	                            </p>
	                        </td>
	                        <td>
	                            <span class="text-assist">&yen; ${item.price}</span>
	                        </td>
	                        <td>${item.number}</td>
	                        <td>
	                            <span class="text-assist">&yen; 
	                            	<fmt:formatNumber type="number" value="${item.totalprice}" pattern="0.00" maxFractionDigits="2"/>  
	                            </span>
	                        </td>
	                    </tr>
                    
                    </c:forEach>
                    
                    
                    </tbody>
                </table>

            </div>
            <div class="row-fluid clearfix">
                <span class="pull-right">订单共${number}件商品，总计：￥${totalprice}（含邮费￥${postage}）</span>
            </div>
        </div>
    </div>
    <!-- 配送信息 -->
    <div class="space"></div>
    <div class="row-fluid fz-default">
        <div class="span2 text-center">
            <h3 class="text-center">订单信息</h3>
        </div>
        <div class="row-fluid fz-default span10">

            <div class="row-fluid">
            <span>
                <span class="">配送方式:</span>
                <span class="">
                	<c:if test="${resuldata.expresstype eq 1}">快递配送</c:if>
                	<c:if test="${resuldata.expresstype eq 2}">上门自取</c:if> 
                </span>
            </span>
            </div>
            <div class="row-fluid">
            <span class="span4">
                <span class="">联系人:</span>
                <span class="">${resuldata.shipname}</span>
            </span>
                <span class="span4">
                <span class="">联系电话:</span>
                <span class="">${resuldata.phone}</span>
            </span>
            </div>
            <div class="row-fluid">
            <span>
                <span class="">
                <c:if test="${resuldata.expresstype eq 1}">收货地址:</c:if>
                </span>
                <span class="">
                <c:if test="${resuldata.expresstype eq 1}">${resuldata.province}${resuldata.city}${resuldata.area}${resuldata.shipaddress} </c:if>                
                </span>
            </span>
            </div>
            <div class="row-fluid">
            <span class="span4">
                <span class="">
                 <c:if test="${resuldata.expresstype eq 1}">物流公司:</c:if>
                 </span>
                <span class="">
                 <c:if test="${resuldata.expresstype eq 1}">${resuldata.ExpressCompanyName}</c:if>
                 </span>
            </span>
                <span class="span4">
                <span class="">
                 <c:if test="${resuldata.expresstype eq 1}">快递编号:</c:if>
                 </span>
                <span class="">
                 <c:if test="${resuldata.expresstype eq 1}">${resuldata.expressno}</c:if>
                 </span>
            </span>
            </div>

        </div>
    </div>
    <!-- 底部两个按钮 -->
    <div class="space"></div>
    <div class="text-center">
        <button class="btn btn-large badge-success" id="refresh" orderid="${order.orderid}" onclick="refresh(this);">刷新</button>
        <button class="btn btn-large badge-success" id="back" onclick="back();">返回</button>
    </div>

</div>

</body>
</html>
