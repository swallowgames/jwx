<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>我的资料</title>
<#include "/comm/header.ftl" />
</head>
<body>
<div class="mainBox2">
  <ul class="myMsglist">
    <a onclick="doEdit();"><li class="clearFloat"></i><span>昵称</span><em><#if member.nickName! != ''>${member.nickName!}<#else>${member.wxName!}</#if></em><i class="icon_pr"></i></li></a>
    <a onclick="doEdit();"><li class="clearFloat"></i><span>姓名</span><em>${member.name?if_exists}</em><i class="icon_pr"></i class="clearFloat"></li></a>
    <a onclick="doEdit();"><li class="clearFloat"></i><span>性别</span><em><#if member.sex!?string = '1'>男<#elseif member.sex!?string = '2'>女<#else>未知</#if></em><i class="icon_pr"></i></li></a>
    <a onclick="doEdit();"><li class="clearFloat"></i><span>身份证</span><em>${member.idno!}</em><i class="icon_pr"></i></li></a>
    <a onclick="doEdit();"><li class="clearFloat"></i><span>电话</span><em>${member.tel!}</em><i class="icon_pr"></i></li></a>
    <a onclick="doEdit();"><li class="clearFloat"> </i><span>订单地址</span><em>${member.orderStreet!}</em></li></a>
  </ul>
  <div class="btnDiv mt17"><span><button class="btnGreen" onclick="doEdit();">修改资料</button></span></div>
</div>
</body>
<script>
function doEdit() {
	window.location.href="${base}/my/data.html?type=edit";
}
</script>
</html>