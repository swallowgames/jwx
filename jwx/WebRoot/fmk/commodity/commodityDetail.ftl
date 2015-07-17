<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title id="headTitle">商品</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
<div class="mainBox2">
  <div class="main_visual">
	<!--<div class="flicking_con"><em>
		<a href="#"></a>
		<a href="#"></a>
		<a href="#"></a>
		<a href="#"></a></em>
	</div>-->
	<div class="main_image">
        <!--<ul>
			<li><span class="img_1"><a href="#"><img src="${base}/resources/images/ad_1.jpg"></a></span></li>
			<li><span class="img_2"><a href="#"><img src="${base}/resources/images/ad_2.jpg"></a></span></li>
            <li><span class="img_1"><a href="#"><img src="${base}/resources/images/ad_1.jpg"></a></span></li>
			<li><span class="img_2"><a href="#"><img src="${base}/resources/images/ad_2.jpg"></a></span></li>
		</ul>
        <a href="javascript:;" id="btn_prev"></a>
		<a href="javascript:;" id="btn_next"></a>-->
		<ul><li><span class="img_2"><img src="${imgUrl!}${commodity.picUrl!}"></span></li></ul>
	</div>
  </div>
  <div class="titDiv">
    <h1>${commodity.name!}</h1>
    <div class="priceDiv">
      <span class="price"><i>¥</i>${commodity.price!}</span><span class="priceRight"><span>运费: 包邮</span></span>
    </div>
  </div>
  <div class="addrDiv mt17">
    <i class="icon_dz"></i><p>${commodity.merchant.name!}</p>
    <a href="${base}/merchant/detail.html?id=${commodity.merchant.id!}" class="gotoShop">进入店铺</a>
  </div>
  <div class="article2 mt17">
    ${commodity.detailDesc!}
  </div>
  <#if commodity.isbuy?? && commodity.isbuy==1>
  <div class="btnDiv"><button class="btnGreen"  id="buy">立即购买</button></div>
  </#if>
</div>
</body>
<script type="text/javascript" src="${base}/resources/js/jquery.touchSlider.js"></script>
<script>
$(document).ready(function(){
  $("#buy").click(function(){
  	location.href="${base}/commodity/toBuy.html?id=${commodity.id}";
  });
 }); 
</script>
</html>
