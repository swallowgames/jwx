<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>${title!}</title>
<#include "/comm/header.ftl" />
</head>
<body>
<div class="mainBox2">
  <div class="myTop">
    <div class="myImg"><img src="${base}/resources/images/my_ad.jpg"></div>
    <div class="myIcon"><em><img src="${member.picUrl!}"></em><br><span><#if member.nickName! != ''>${member.nickName!}<#else>${member.wxName!}</#if></span></div>
    <div class="myText">
      <#if member.type! = 'attention' >
      	<p></p><span class="rightBtn"><a href="${base}/my/toRegister.html">注册</a></span>
      </#if>
      <#if member.type! = 'register' >
      	<p>您还没有认证，现在马上去认证!</p><span class="rightBtn"><a href="${base}/my/toApply.html">请认证</a></span>
      </#if>
      <#if member.type! = 'resident'>
      	<p><#if resident?exists>${customer.yetaiid!}${customer.orgid!}${customer.building!}${customer.unit!}${customer.room!}</#if></p><span class="rightBtn"><span class="icon_yz">业主</span></span>
      </#if>
    </div>
  </div>
  <ul class="myList">
    <li><a href="${base}/my/data.html"><i class="ico_1"></i><span>我的资料</span><i class=""></i><i class="icon_pr"></i></a></li>
    <li><a href="${base}/my/activity.html"><i class="ico_2"></i><span>我的活动</span><i class=""></i><i class="icon_pr"></i></a></li>
    <li><a href="${base}/my/repair.html"><i class="ico_3"></i><span>我的报修</span><i class="icon_point"></i><i class="icon_pr"></i></a></li>
    <li><a href="${base}/my/order.html"><i class="ico_4"></i><span>我的订单</span><i class=""></i><i class="icon_pr"></i></a></li>
  </ul>
    
</div>
</body>
</html>