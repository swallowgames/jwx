<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>注册</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
<div class="mainBox2">
 <div class="sign">
  <h2>请先注册账号</h2>
  <ul id="inputList">
    
    <li><em>姓名</em><span><input name="" type="text" class="input_1" placeholder="请输入您的姓名" name="cusname" id="cusname"></span></li>
    <li><em>手机号码</em><span><button class="btnGreen short fr getYan" onclick="validate();">获取验证码</button><input name="mobile" id="mobile" type="text" class="input_1 short" placeholder="请输入您的手机号码" value=""></span></li>
    <li><em>验证码</em><span><input name="" type="text" class="input_1 short" placeholder="请输入短信验证码" name="random" id="random"></span></li>
  </ul>
 </div>
  <div class="btnDiv mt17"><button class="btnGreen btnGray" id="signBtn" onClick="register();" disabled="disabled">立即注册</button></div>
    
</div>
</body>
<script>
setClickBtn('#inputList','#signBtn');

function showButton() {
	$(".getYan").addClass('btnGray');
	$(".getYan").attr("disabled","disabled");
	show();
}

    var timeout = 60;
     function show() {
         var showbox = $('.getYan');
         showbox.html("剩余("+timeout+")s");
         if (timeout == 0) {
             showbox.removeClass('btnGray');
             showbox.removeAttr("disabled");
             showbox.html("获取验证码");
             timeout == 60;
         }
         else {
            setTimeout("show()", 1000);
        }
        timeout--;
    }
    
function register() {
	var mobile = $.trim($("#mobile").val());
    if(!(/^1/.test(mobile)) || !(/^[0-9]{11}$/.test(mobile)) ){ 
    	alert("请l填写11位手机号码");
		$("#mobile").focus();
        return false; 
    }
    var random = $.trim($("#random").val());
    if(!(/^[0-9]{6}$/.test(random))){ 
    	alert("请l正确填写验证码");
		$("#random").focus();
        return false; 
    }
    
    var cusname = $("#cusname").val();
	if(cusname == "") {
		alert("请输入名称");
		return false;
	}
    
	$.ajax({
        type: "POST",
        url:"${base}/my/register.html",
        data: {mobile:mobile,random:random,cusname:cusname},
        success: function(html) {
        	//alert(html.status);
        	if(html.status == true) {
        		alert("注册成功");
        		window.location.href="${base}/my/index.html";
        	} else {
        		alert("注册失败");
        	}
        }
	});
}

function validate() {
	var mobile = $.trim($("#mobile").val());
    if(!(/^1/.test(mobile)) || !(/^[0-9]{11}$/.test(mobile)) ){ 
    	alert("请l填写11位手机号码");
		$("#mobile").focus();
        return false; 
    }
    showButton();
	$.ajax({
        type: "POST",
        url:"${base}/my/validateRegister.html?mobile=" + mobile,
        success: function(html) {
        	//alert(html.id);
        	alert("发送随机码成功");
        	$("#id").val(html.id);
        }
	});
}
</script>
</html>
