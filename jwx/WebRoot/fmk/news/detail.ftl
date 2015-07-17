<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title  id="headTitle">${keyWord.title?if_exists}</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
  <div class="mainBox">
  	 ${keyWord.content?if_exists}
  </div>
</body>
</html>
