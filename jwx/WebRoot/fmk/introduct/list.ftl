<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title id="headTitle">${title!}</title>
<#include "/comm/header.ftl" />
<script type="text/javascript" src="${base}/resources/js/jquery.timeago.js?r=${stversion}"></script>
</head>
<body>
  <div class="mainBox2">
  	<#if noticeList?exists>
    <ul class="list">
      	<#list noticeList as notice>
      	  <li>
	      	  <a href="${base}/noticedetail.html?id=${notice.id?if_exists}">
		        <em><img src="${imgUrl!}${notice.wxPic?if_exists}" alt="图片"></em>
		        <h3>${notice.wxTitle?if_exists}</h3>
		        <p>${notice.wxIntro?if_exists}</p>
		        <span name="time"><time class="timeago" datetime="${notice.ctime?if_exists}"></time></span>
		      </a>
	      </li>
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
