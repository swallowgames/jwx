<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title id="headTitle" >${title!}</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
<div class="mainBox2">
 <div class="sign">
  <h2>请先填写资料</h2>
  <form id = "form" name="form" method="post" action="${base}/activity/apply.html">
  <input type="hidden" name="activity.id" id="id" value="${id!}" />
  <ul id="inputList">
    <li><em>姓名</em><span><input name="contacts" type="text" class="input_1" placeholder="请输入您的姓名" value="${member.name!}"></span></li>
    <li><em>手机号码</em><span><input id="tel" name="tel" type="text" class="input_1" placeholder="请输入您的手机号码" value="${member.tel!}"></span></li>
  </ul>
 </div>
 </form>
  <div class="btnDiv mt17"><button class="btnGreen btnGray" id="signBtn" onClick="submit()" >提 交</button></div>
</div>
</body>
<script>
	function submit() {
		var tel = $.trim($("#tel").val());
	    if(!(/^1/.test(tel)) || !(/^[0-9]{11}$/.test(tel)) ){ 
	    	alert("请l填写11位手机号码");
			$("#tel").focus();
	        return false; 
	    }
		$("#form").submit();
	}
	setClickBtn('#inputList','#signBtn');
</script>
</html>
