<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title id="headTitle">${title!}</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
<div class="mainBox2">
    <ul class="list">
    <#list activityList as activity>
      <li><a href="${base}/activity/detail.html?id=${activity.id?c}">
        <em><img src="${imgUrl!}${activity.picUrl}" alt="图片"></em>
        <h3>${activity.title!}</h3>
        <p>${activity.dsc!}</p>
        <span></span>
      </a></li>
      </#list>
    </ul>
  </div>
</body>
</html>
