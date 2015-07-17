<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>${title!}</title>
<#include "/comm/header.ftl" />
</head>
<body>
<div class="mainBox2">
  <div class="successBox">
    <em class="icon_tips <#if result?if_exists?string = 'true'>icon_tips2</#if>"></em>
    <p>${message?if_exists}</p>
  </div>
  <div class="btnDiv mt17">
   <ul class="twoBtn">
    <li><span><button class="btnGreen" onclick="location.href='${base}/activity/list.html';">其它活动</button></span></li>
    <li><span><button class="btnGreen" onclick="location.href='${base}/my/activity.html';">我的活动</button></span></li>
   </ul>
  </div>
    
</div>
</body>
<script>

</script>
</html>