<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>业主认证</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
<form name="form" id="form" method="post" action="${base}/my/apply.html">
<div class="mainBox2">
  <div class="orderDiv" id="inputList">
    <h2>输入具体资料</h2>
    <div class="xxDiv chooseAdd">
      <p class="fillInput"><em>姓名</em><input class="input_1 shorts" type="text" placeholder="请输入您的姓名" name="username" id="username"></p>
    </div>
    <div class="xxDiv chooseAdd">
      <p class="fillInput"><em>手机号码</em><input class="input_1 shorts" type="text" placeholder="请输入您的手机号码" name="mobile" id="mobile"></p>
    </div>
    <div class="xxDiv chooseAdd">
      	<a>
        <p><em>开发周期</em>万达大湖公馆</p>
      </a>
    </div>
    <div class="xxDiv chooseAdd">
      <a><p id="pOrg"><em>组团</em></p></a>
    </div>
    <div class="xxDiv chooseAdd">
      <a><p id="pBuilding"><em>楼栋</em></p></a>
    </div>
    <div class="xxDiv chooseAdd">
      <a><p id="pUnit"><em>单元</em></p></a>
    </div>
    <div class="xxDiv chooseAdd">
      <a><p id="pRoom"><em>房号</em></p></a>
    </div>
    
  </div>
  <div class="btnDiv mt17">
    <input type="button" id="signBtn" class="btnGreen" onclick="apply();" value="提交认证" />
  </div>
    
</div>
</form>
</body>
<script>

function apply() {
	var username = $.trim($("#username").val());
	if(username == "") {
		alert("请l填写姓名");
		$("#username").focus();
		return false;
	}
	var mobile = $.trim($("#mobile").val());
    if(!(/^1/.test(mobile)) || !(/^[0-9]{11}$/.test(mobile)) ){ 
    	alert("请l填写11位手机号码");
		$("#mobile").focus();
        return false; 
    }
	var yetaiId = $("#yetaiid").val();
	if(yetaiId == "") {
		alert("请选择业态");
		return false;
	}

	var orgId = $("#orgid").val();
	alert(orgId);
	if(orgId == "") {
		alert("请选择组团");
		return false;
	}
	var buildingId = $("#building").val();
	if(buildingId == "") {
		alert("请选择楼栋");
		return false;
	}
	var unit = $("#unit").val();
	if(unit == "") {
		alert("请选择单元");
		return false;
	}
	var room = $("#room").val();
	if(room == "") {
		alert("请选择房号");
		return false;
	}
	$("#form").submit();
}

function changeYeTai(obj) {
	if(obj.value == "") {
		$("#pOrg").html("<em>组团</em>");
    	$("#pBuilding").html("<em>楼栋</em>");
    	$("#pUnit").html("<em>单元</em>");
    	$("#pRoom").html("<em>房号</em>");
    	return;
	}
	$.ajax({
        type: "POST",
        url:"${base}/my/chooseOrg.html?yetaiId=" + obj.value,
        success: function(html) {
        	$("#pOrg").html(html);
        	$("#pBuilding").html("<em>楼栋</em>");
        	$("#pUnit").html("<em>单元</em>");
        	$("#pRoom").html("<em>房号</em>");
        }
	});
}

function changeOrg(obj) {
	if(obj.value == "") {
    	$("#pBuilding").html("<em>楼栋</em>");
    	$("#pUnit").html("<em>单元</em>");
    	$("#pRoom").html("<em>房号</em>");
    	return;
	}
	$.ajax({
        type: "POST",
        url:"${base}/my/chooseBuilding.html?orgId=" + obj.value,
        success: function(html) {
        	$("#pBuilding").html(html);
        	$("#pUnit").html("<em>单元</em>");
    	$("#pRoom").html("<em>房号</em>");
        }
	});
}

function changeBuilding(obj) {
	if(obj.value == "") {
    	$("#pUnit").html("<em>单元</em>");
    	$("#pRoom").html("<em>房号</em>");
    	return;
	}
	$.ajax({
        type: "POST",
        url:"${base}/my/chooseUnit.html?buildingId=" + obj.value,
        success: function(html) {
        	$("#pUnit").html(html);
        	$("#pRoom").html("<em>房号</em>");
        }
	});
}

function changeUnit(obj) {
	if(obj.value == "") {
    	$("#pRoom").html("<em>房号</em>");
    	return;
	}
	$.ajax({
        type: "POST",
        url:"${base}/my/chooseRoom.html?unitId=" + obj.value,
        success: function(html) {
        	$("#pRoom").html(html);
        }
	});
}

</script>
</html>
