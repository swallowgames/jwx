<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title id="headTitle">商家详细</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
  <div class="mainBox2">
    <div class="sellTop">
      <img src="${imgUrl!}${merchant.detailPicUrl!}">
      <h1><span>${merchant.name!}</span></h1>
      <span class="star">
	        <#if merchant.star == '5'>
	        <i class="on"></i><i class="on"></i><i class="on"></i><i class="on"></i><i class="on"></i>
	        <#elseif merchant.star == '4'>
	        <i class="on"></i><i class="on"></i><i class="on"></i><i class="on"></i><i></i>
	        <#elseif merchant.star == '3'>
	        <i class="on"></i><i class="on"></i><i class="on"></i><i></i><i></i>
	        <#elseif merchant.star == '2'>
	        <i class="on"></i><i class="on"></i><i></i><i></i><i></i>
	        <#elseif merchant.star == '1'>
	        <i class="on"></i><i ></i><i></i><i></i><i></i>
	        <#else>
	        <i class="on"></i><i class="on"></i><i class="on"></i><i class="on"></i><i class="on"></i>
	        </#if>
		</span>
    </div>
    <div class="address"><a href="${base}/merchant/merchantMap.html?id=${merchant.id!}">
      <i class="iconP2"></i>
      <p>${merchant.addr!}</p></a>
      <a class="listPhone" href="tel:${merchant.tel!}"><i class="icon_phone"></i></a>
    </div>
    
    <div class="list3 mt17" id="list_1">
     <ul>
     <#list commodityList as commodity >
      <li <#if  (commodity_index > 0)>class="hide"</#if>><a href="${base}/commodity/detail.html?id=${commodity.id!}" >
        <em><img src="${imgUrl!}${commodity.picUrl!}" alt="图片"></em>
        <h3>${commodity.name!} </h3>
        <span class="price"><i>¥</i>${commodity.price!}</span>
      </a></li>
      </#list>
    </ul>
    <div class="moreList ml"><a >查看全部商品<i class="icon_pd"></i></a></div>
    </div>
    <div class="shopintro mt17" id="list_3">
      <h3>商家介绍</h3>
      <ul class="shopintroDtl">
        <li>${merchant.intro!}</li>
      </ul>
      <div class="moreList ml"><a href="javascript:;">查看商家全部介绍<i class="icon_pd"></i></a></div>
    </div>
</div>
</body>
<script>
 showMoreList('#list_1',2);
 showMoreList('#list_3',1);
</script>
</html>
