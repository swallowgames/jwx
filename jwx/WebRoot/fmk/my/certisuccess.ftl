<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>认证成功</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
<div class="mainBox2">
  <div class="successBox">
    <em class="icon_tips icon_tips2"></em>
    <p>恭喜您，活动报名成功啦!</p>
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
