<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>认证失败</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
<div class="mainBox2">
  <div class="successBox errorBox">
    <em class="icon_tips"></em>
    <p>很抱歉，认证提交出错了!</p>
  </div>
  <ul class="myCertiMsg">
    <li><span>所在小区</span><span class="tr">北京市 海淀区 万达大湖公馆<br/>紫龙府 A单元 2202室</span></li>
    <li><span>认证时间</span><span class="tr">2015-03-20&emsp;20:56:25</span></li>
    <li><span>业主</span><span class="tr"></span></li>
    <li><span>联系电话</span><span class="tr">13928880098</span></li>
  </ul>
  <div class="btnDiv mt17">
    <button class="btnGreen" onclick="jump();">查看我的资料</button>
  </div>
    
</div>
</body>
<script>
function jump() {
	window.location.href="${base}/my/index.html";
}
</script>
</html>
