<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title  id="headTitle">${title!}</title>
<#include "/comm/header.ftl" />
</head>
<body>
  <div class="mainBox">
    <div class="article">
      <h2>${notice.wxTitle?if_exists}<span>${notice.ctime?if_exists}</span></h2>
      ${notice.content?if_exists}
    </div>
  </div>
</body>
</html>
