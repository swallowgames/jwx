<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>我的订单</title>
<#include "/comm/header.ftl" />
<#assign status=request.getParameter("status")?if_exists>
</head>
<body>
  <div class="mainBox2">
    <div class="topList topNav">
      <ul id="navList">
        <li class="on"><a href="javascript:;">全部<i></i></a></li>
        <li ><a href="javascript:;">未完成<i></i></a></li>
        <li ><a href="javascript:;">已完成<i></i></a></li>
      </ul>
    </div>
    <#if orderList?exists && (orderList?size>0)>
    <div class="conList" id="conList">
    <div>
    <ul class="list3 list4">
    	<#list orderList as order>
		      <li><a href="${base}/my/orderDetail.html?id=${order.orderNo!}">
		        <em><img src="${imgUrl!}${order.picUrl!}" alt="图片"></em>
		        <h3>${order.name!} </h3>
		        <p><span>总价：${order.orderPrice!}元</span>数量：1</p>
		        <#if order.status!?string == '1'>
			        <span class="pj">正在处理</span>
			        <span class="actBtn"><button id="${order.orderNo!}">取消</button></span>
			    <#elseif order.status!?string == '2'>
			    	<span class="pj">正在发货</span>
			        <span class="actBtn"><button id="${order.orderNo!}">取消</button></span>
			    <#elseif order.status!?string == '3'>
			    	<span class="pj yellow">已完成</span>
			    <#elseif order.status!?string == '4'>
			    	<span class="pj">已取消</span>
		        </#if>
		        <!--
		    	<span class="pj">待评价</span>
		    	<span class="actBtn"><button onClick="location.href='pingjia.html'">评价</button></span>
		    	-->
		      </a></li>
      	</#list>
    </ul>
    </div>
    <div class="hide">
		<ul class="list3 list4">
    	  <#list orderList as order>
    		<#if order.status!?string != '3'>
		      <li><a href="${base}/my/orderDetail.html?id=${order.orderNo!}">
		        <em><img src="${imgUrl!}${order.picUrl!}" alt="图片"></em>
		        <h3>${order.name!} </h3>
		        <p><span>总价：${order.orderPrice!}元</span>数量：1</p>
		        <#if order.status!?string == '1'>
			        <span class="pj">正在处理</span>
			        <span class="actBtn"><button id="${order.orderNo!}">取消</button></span>
			    <#elseif order.status!?string == '2'>
			    	<span class="pj">正在发货</span>
			        <span class="actBtn"><button id="${order.orderNo!}">取消</button></span>
			    <#elseif order.status!?string == '3'>
			    	<span class="pj yellow">已完成</span>
			    <#elseif order.status!?string == '4'>
			    	<span class="pj">已取消</span>
		        </#if>
		      </a></li>
	      	</#if>
	      </#list>
	    </ul>
	</div>
    <div class="hide">
		<ul class="list3 list4">
    	 <#list orderList as order>
    		<#if order.status!?string = '3'>
		      <li><a href="${base}/my/orderDetail.html?id=${order.orderNo!}">
		        <em><img src="${imgUrl!}${order.picUrl!}" alt="图片"></em>
		        <h3>${order.name!} </h3>
		        <p><span>总价：${order.orderPrice!}元</span>数量：1</p>
		        <!--
		        <span class="pj">待评价</span>
		        <span class="actBtn"><button onClick="location.href='pingjia.html'">评价</button></span>
		        -->
		        <#if order.status!?string == '1'>
			        <span class="pj">正在处理</span>
			        <span class="actBtn"><button id="${order.orderNo!}">取消</button></span>
			    <#elseif order.status!?string == '2'>
			    	<span class="pj">正在发货</span>
			        <span class="actBtn"><button id="${order.orderNo!}">取消</button></span>
			    <#elseif order.status!?string == '3'>
			    	<span class="pj yellow">已完成</span>
			    <#elseif order.status!?string == '4'>
			    	<span class="pj">已取消</span>
		        </#if>
		      </a></li>
	      	</#if>
	      </#list>
	    </ul>
	</div>
    </div>
    </#if>
  </div>
</body>
<script>
 
 setNav2('#navList','#conList');
	
 var cancel = function(id){
 	
 }
 
$(function(){
	$(".actBtn button").click(function(event){ 
		event.stopPropagation(); 
		var id = $(this).attr("id");
		var btn = $(this);
		if(confirm("确定取消该订单吗？")){
			$.post("${base}/my/cancelOrder.html",{orderNo:id,status:'4'},function(data){
				if(data) {
					//修改订单状态
					btn.parent().prev().html('已取消');
					btn.parent().hide();
				}else{
					alert("订单取消失败");
				}
			});
		}
		return false;
	}); 
}); 
 
</script>
</html>