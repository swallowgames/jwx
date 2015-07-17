<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title  id="headTitle">${merchant.name!}</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
  <div class="mainBox2">
    <div class="mainBox" style="padding:0px;">
		<h1 style="padding:0px;"><img width="480" src="${imgUrl!}/${merchant.addrPic!}"></img></h1>
	</div>
    <div class="contact">
      <div>
      	<span>${merchant.tel!}</span>
      	<a class="call" style="margin-top:0px;margin-right:0px;" href="tel:${merchant.tel!}"><i class="icon_phone"></i></a>
      </div>
    </div>
</div>
</body>
</html>
