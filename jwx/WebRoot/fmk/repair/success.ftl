<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>物业报修结果</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
<div class="mainBox2">
  <div class="searchNo repair">
    <em class="icon_tips <#if result?if_exists?string = 'true'>icon_tips2</#if>">"></em>
    <div class="repairText">
      <#if result?if_exists?string = 'true'>
      	<h3>恭喜您，报修提交成功啦!</h3>
      <#else>
      	<h3>很抱歉，报修提交出错了!</h3>
      	<span>亲，联系我们菜单可以拨打物管电话哦</span>
      </#if>
    </div>
  </div>
  <div class="btnDiv mt17">
   <ul class="twoBtn">
    <li><span><button class="btnGreen" onclick="location.href='${base}/repair.html';">物业报修</button></span></li>
    <li><span><button class="btnGreen" onclick="location.href='${base}/my/repair.html';">我的报修</button></span></li>
   </ul>
  </div>
</div>
</body>
</html>