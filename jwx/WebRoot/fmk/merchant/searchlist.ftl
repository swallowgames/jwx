<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title  id="headTitle">商品搜索</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
	
  <div class="mainBox2">
  	<#if merchantList?exists && (merchantList?size>0) >
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
    <#else>
	    <div class="searchBar"><input id="search" name="search" type="text" placeholder="请输入商家名，品类或商品名..."><i></i></div>
		  <div class="searchNo">
		    <em class="icon_tips"></em>
		    <p>暂无此类商家，请看看别的吧！</p>
		</div>
		<script language="javascript">  
			$("#search").keydown(function(event) {
			    if (event.keyCode == 13) {//keyCode=13是回车键
			        var name = $(this).val();
			        if(name != null && name.trim() != ''){
			        	location.href="${base}/merchant/doSearch.html?name="+name;
			        }
			    }
			});
		</script> 
	</#if>
  </div>
</body>
</html>
