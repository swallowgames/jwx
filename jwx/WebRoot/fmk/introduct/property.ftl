<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title  id="headTitle">${title!}</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
  <div class="mainBox">
  	<#if type! = 'wdIntro'>
  		<h1><img src="${base}/resources/images/logo.png" width="325" height="75"></h1>
  	</#if>
    <div class="con_1">
    	${issueContent.content!}
    </div>
    
    <#if issueContent.columns??>
    <#list issueContent.columns as column>
	    <div class="conBox">
	      <h3>${column.name!}</h3>
	      <div class="con_2">
	        ${column.content!}
	      </div>
	    </div>
    </#list>
    </#if>
  </div>
</body>
</html>
