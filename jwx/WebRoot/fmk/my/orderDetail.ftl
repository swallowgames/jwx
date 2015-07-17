<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>订单详情</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
<div class="mainBox2">
 	
<#if details??>
	<#list details as detail>
  <div class="orderTop">
    <div class="product">
      <p>${detail.name!}</p>
      <span>${detail.price!}</span>
    </div>
    <div class="coutDiv">
      <span class="fl">数量：</span>
      <span class="fr">${detail.counts!}</span>
    </div>
    <div class="priDiv"><span class="fl">总价：</span><span class="fr">${detail.counts! * detail.price!}</span></div>
    
  </div>
  </#list>
</#if>
  
  <div class="orderDiv">
  <h2>收货人信息</h2>
  <div class="xxDiv"><a href="javascript:void(0);">
    <h3>${order.contacts!} ${order.tel!}</h3>
    <p>${order.addr!}</p>
  </a></div>
  </div>
  
  <#if order.remark?? && order.remark?trim != ''>
	  <div class="orderDiv">
	  <h2>备注信息</h2>
	  <div class="xxDiv"><a href="javascript:void(0);">
	    <p>&nbsp;${order.remark!}</p>
	  </a></div>
	  </div>
  </#if>

  <div class="xxDiv orderDt">
    <div class="clearFloat">
      <span class="fl">订单号</span>
      <span class="fr">${order.orderNo!}</span>
    </div>
    <div class="clearFloat">
      <span class="fl">生成时间</span>
      <span class="fr">${order.ctime!}</span>
    </div>
  </div>
  </div>
    
</div>
</body>
<script>
 $(document).ready(function(){
	  setCout(".coutBar");
	  }) 
</script>
</html>