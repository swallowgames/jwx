<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title id="headTitle">${title!}</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
<div class="mainBox">
    <div class="article">
      <h2>${activity.title!}<span>${activity.ctime!}</span></h2>
      ${activity.intro!}
    </div>
  </div>
  <br><br><br>
  <#if request.getParameter("from")?if_exists != 'my'>
  	<div class="btnDiv fixed"><span><button class="btnGreen" onClick="location.href='${base}/activity/signUp.html?id=${activity.id!}'">立即报名</button></span></div>
  </#if>	
</body>
</html>