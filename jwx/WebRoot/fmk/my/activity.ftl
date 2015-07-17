<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>我的活动</title>
<#include "/comm/header.ftl" />
<script type="text/javascript" src="${base}/resources/js/jquery.timeago.js?r=${stversion}"></script>
</head>
<body>
  <div class="mainBox2">
  	<#if activityList?exists>
    <ul class="list myact">
		<#list activityList as applyActivity>
	      <li><a href="${base}/activity/detail.html?id=${applyActivity.activity.id!}&from=my">
	        <em><img src="${imgUrl!}${applyActivity.activity.picUrl!}" alt="图片"></em>
	        <h3>${applyActivity.activity.title!}</h3>
	        <p>${applyActivity.activity.dsc!}</p>
	        <span><time class="timeago" datetime="${applyActivity.activity.ctime!}"></time></span>
	      </a></li>
		</#list>
    </ul>
    </#if>
  </div>
</body>
<script>
$(function(){
    $(".timeago").timeago(); 
});
</script>
</html>