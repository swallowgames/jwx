<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>订单详情</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
<div class="mainBox2">
  <div class="addrDiv shopname"><i class="icon_dz"></i><span class="xxDiv">${merchant.name!}<i></i></span></div>
  <div class="orderTop">
  <#if details??>
	<#list details as detail>
    <div class="product">
      <p>${detail.name!}</p>
      <span>${detail.price!}</span>
    </div>
    <div class="coutDiv">
      <span class="fl">数量：</span>
      <span class="fr">${detail.counts!}</span>
    </div>
    <div class="priDiv"><span class="fl">总价：</span><span class="fr">${detail.counts! * detail.price!}</span></div>
    </#list>
   </#if>
    <div class="orderMsg">
      <p>订单号: ${order.orderNo!}</p>
      <p>订单时间:${order.ctime!}</p>
    </div>
  </div>
  
  <div class="orderDiv">
  <h2>收货人信息</h2>
  <div class="xxDiv"><a href="javascript:void(0);">
    <h3>${order.contacts!} ${order.tel!}</h3>
    <p>${order.addr!}</p>
  </a></div>
  </div>
  
  <div class="orderDiv">
    <h2>备注</h2>
    <div class="xxDiv tip">&nbsp;${order.remark!}</div>
  </div>

</div>
</body>
<script>
</script>
</html>
