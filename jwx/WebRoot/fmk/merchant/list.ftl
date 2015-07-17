<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title  id="headTitle">商家列表</title>
<#include "/comm/header.ftl" />
</head>
  <div class="mainBox2">
    <div class="topList">
      <ul id="navList">
      	<#if merchantTypeList?exists>
      		<#list merchantTypeList as type>
      			<li <#if typeId = type.id>class="on"</#if>><a href="${base}/merchant/list.html?typeId=${type.id?if_exists}">${type.name?if_exists}<i></i></a></li>
      		</#list>
      	</#if>
      </ul>
      <em class="search"><a href="${base}/merchant/search.html"></a></em>
    </div>
    <div class="conList">
	    <div>
	    <ul class="list list2">
	    <#list merchantList as merchant>
	      <li><a href="${base}/merchant/detail.html?id=${merchant.id}">
	        <em><img src="${imgUrl!}${merchant.picUrl!}" alt="图片"></em>
	        <h3>${merchant.name!}</h3>
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
	        </span><br>
	        <span>${merchant.tel!}</span>
	        <a class="listPhone" href="tel:${merchant.tel!}"><i class="icon_phone"></i></a>
	      </a></li>
	     </#list>
	    </ul>
	    </div>
    </div>
  </div>
<script>
 setNav('#navList');
</script>
</html>
