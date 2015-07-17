<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>业主认证</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
<div class="mainBox2">
  <div class="orderDiv marginT20 selectpark">
  	<#list yeTais as yeTai>
    <div class="xxDiv chooseAdd">
      <a href="javascript:history.go(-1);"><i class="choosen"></i>
        <p>${yeTai.yetaiName!}</p>
      </a>
    </div>
    </#list>
  </div>
    
</div>
</body>
<script>
  $('.selectpark .chooseAdd').on('tap',function(){
      $(this).addClass('active').siblings().removeClass('active');
  });
</script>
</html>
