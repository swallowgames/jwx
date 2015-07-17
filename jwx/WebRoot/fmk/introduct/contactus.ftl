<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=480, user-scalable=no, target-densitydpi=high-dpi"/>
<meta content="telephone=no" name="format-detection" />
<title id="headTitle">${title!}</title>
<link rel="stylesheet" href="${base}/resources/css/style.css" type="text/css" />
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/public.js"></script>
</head>
<body class="gray">
  <div class="mainBox2">
  	<#if contacts?exists && (contacts?size>0)>
    <div class="topList topNav <#if (contacts?size<=2)>two<#elseif (contacts?size>=4)>four</#if>">
      <ul id="navList">
      	<#list contacts as contact>
        	<li <#if contact_index == 0>class="on"</#if>><a href="javascript:;">${contact.type!}<i></i></a></li>
        </#list>
      </ul>
    </div>
    <div id="contactList" class="conList">
    	<#list contacts as contact>
        <div <#if contact_index != 0>class="hide"</#if>>
        	<#if contact.contactTels?exists && (contact.contactTels?size>0)>
            <div class="contact">
            	<#list contact.contactTels as tel>
                <div class="contactLi">
                    <a class="call" href="tel:${tel.tel}"><i class="icon_phone"></i></a>
                    <span class="conText">${tel.name}</span>
                    <span><small class="conTime">${tel.serviceTime}</small>${tel.tel}</span>
                </div>
                </#list>
            </div>
            </#if>
            <div class="contact" style="margin-top:15px;">
            	<#if contact.companyName?? && contact.companyName?trim != ''>
                <div class="contactLi">
                    <span class="conText">公司名称</span>
                    <span class="conLong">${contact.companyName}</span>
                </div>
                </#if>
                <#if contact.companyAddr?? && contact.companyAddr?trim != ''>
                <div class="contactLi">
                    <span class="conText">公司地址</span>
                    <span class="conLong">${contact.companyAddr}</span>
                </div>
                </#if>
                <#if contact.companyWebsite?? && contact.companyWebsite?trim != ''>
                <div class="contactLi" style=" padding: 18px 15px;">
                    <span class="conText">官方网址</span>
                    <a class="conUrl" href="${contact.companyWebsite}">${contact.companyWebsite}</a>
                </div>
                </#if>
            </div>
            <#if contact.picUrl?? && contact.picUrl?trim != ''>
            	<div class="mainBox" style="padding:0px;">
	        	<h1 style="padding:0px;"><img width="480" src="${imgUrl!}/${contact.picUrl}"></img></h1>
	        	</div>
        	</#if>
        </div>
        </#list>
    </div>
    </#if>
    
</div>
</body>
<script>
setNav2('#navList','#contactList');
</script>
</html>