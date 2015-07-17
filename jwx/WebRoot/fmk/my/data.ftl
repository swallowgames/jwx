<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>修改资料</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
<div class="mainBox2 mt27">
  <div class="sign myData2">
  	<form name="editor_form" id="editor_form">
    <ul id="inputList">
    <input name="openId" id="openId" type="hidden" value="${member.openId?if_exists}">
    <input name="sex" id="sex" type="hidden" value="0">
    <li><em>昵称</em><span><input name="nickName" id="nickName" type="text" class="input_1" placeholder="请输入您的昵称" value="<#if member.nickName! != ''>${member.nickName!}<#else>${member.wxName!}</#if>"></span></li>
    <li><em>姓名</em><span><input name="name" id="name" type="text" class="input_1" placeholder="请输入您的姓名" value="${member.name?if_exists}"></span></li>
    <li><em>性别</em><span><span class="xb" id="xb">
    						<span <#if member.sex!?string = '1'>class="on"</#if> value="1">男</span>
    						<span <#if member.sex!?string = '2'>class="on"</#if> value="2">女</span>
    						<span value="0">未知</span></li>
    <li><em>身份证</em><span><input name="idno" id="idno" type="text" class="input_1" placeholder="请输入您的身份证" value="${member.idno!}"></span></li>
    <li><em>电话</em><span><input name="tel" id="tel" type="text" class="input_1" placeholder="请输入您的电话" value="${member.tel!}"></span></li>
   </ul>
  </div>
  <div class="sign myData2 myData3 mt17">
    <ul id="inputList">
    <li class="textA"><em>订单地址</em><span class="add">
    <textarea name="orderStreet" class="textarea_1" cols="" rows="">${member.orderStreet!}</textarea>
    </span></li>
   </ul>
   </form>
  </div>
 <div class="btnDiv mt17"><span><button class="btnGreen" id="submit_btn">提交保存</button></span></div> 
    
</div>
</body>
<script>
setTab('#xb');
var reqUri = "${base}/my/";
$('#submit_btn').click(function() {
	var idno = $("#idno").val();
	if(idno != null && idno.trim() != ""){
		if(!isCardNo(idno)){
			alert("请输入15位或者18位身份证号码");
			return;
		}
	}
	//设置会员的性别
	$("#xb span").each(function(){
		var spanClass = $(this).attr("class");
		if(spanClass == "on"){
			$("#sex").val($(this).attr("value"));
			return;
		}
	});
	$("#editor_form").attr("action",(reqUri+'edit.html'));
	$("#editor_form").attr("method","POST");
	$("#editor_form").submit();	
});
function isCardNo(card){  
   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
   return reg.test(card);
}  
</script>
</html>