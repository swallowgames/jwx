<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title id="headTitle">商品订单</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
<div class="mainBox2">
<form name="fm" id="fm">
  <div class="orderTop">
    <div class="product">
      <p>${commodity.name!}</p>
      <span id="price">${commodity.price!}</span>
    </div>
    <input type="hidden" name="commodityId" value="${commodity.id!}">
    <div class="coutDiv">
      <span class="fl">数量：</span>
      <span class="coutBar fr"><a href="javascript:;" class="jie def">-</a><input readOnly="true" data-role="none" name="count" id="cout" value="1" type="text"><a href="javascript:;" class="add">+</a><input name="minNum" type="hidden" value="1"><input name="maxNum" type="hidden" value="10000"></span>
    </div>
    <div class="priDiv"><span class="fl">总价：</span><span class="fr" id="totalPrice"></span></div>
    
  </div>
  
  <div class="orderDiv">
  <h2>收货人信息</h2>
  <div class="xxDiv"><a href="${base}/my/data.html"><i></i>
    <h3><span id="name">${member.name!}</span> <span id="tel">${member.tel!}</span></h3>
    <p><span id="addr">${member.orderStreet!}</span></p>
  </a>
  </div>
  </div>
  <div class="sign myData2 myData3 mt17">
    <ul id="inputList">
    <li class="textA"><em>备注：</em><span class="add">
    <textarea name="remark" id="remark" class="textarea_1" cols="" rows=""></textarea>
    </span></li>
   </ul>
  </div>
  </form>
  
  <div class="btnDiv mt17"><button class="btnGreen" id="buy">立即购买</button></div>
    
</div>
</body>
<script>
 $(document).ready(function(){
	  setCout(".coutBar");
	  var price = parseFloat($("#price").text());
	  var cout = parseInt($("#cout").val());
	  $("#totalPrice").text(parseFloat(price * cout).toFixed(2));
	  $("#buy").click(function(){
	  	var cout = parseInt($("#cout").val());
	  	if(cout > 0){
	  		var name = $("#name").text();
	  		var tel = $("#tel").text();
	  		var addr = $("#addr").text();
	  		if($.trim(name) == ''){
	  			alert("姓名不能为空！");
	  			return;
	  		}
	  		if($.trim(tel) == ''){
	  			alert("联系电话不能为空！");
	  			return;
	  		}
	  		if($.trim(addr) == ''){
	  			alert("订单地址不能为空！");
	  			return;
	  		}
			$("#fm").attr("action",'${base}/commodity/buy.html');
			$("#fm").attr("method","POST");
			$("#fm").submit();
			$(this).attr('disabled',"true");添加disabled属性 
	  	}else{
	  		alert("购买数量不能小于0");
	  	}
	  });
 }); 
</script>
</html>
