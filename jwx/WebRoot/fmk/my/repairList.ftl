<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>我的报修</title>
<#include "/comm/header.ftl" />
</head>
<body>
  <div class="mainBox2">
    <div class="topList topNav four">
      <ul id="navList">
        <li class="on"><a href="javascript:;">全部<i></i></a></li>
        <li><a href="javascript:;">受理中<i></i></a></li>
        <li><a href="javascript:;">处理中<i></i></a></li>
        <li><a href="javascript:;">已完成<i></i></a></li>
      </ul>
    </div>
    <div class="conList" id="conList">
    <div>
      <ul class="list myact">
      	<#list repairList as repair>
	       <li><a href="${base}/my/repairDetail.html?id=${repair.sheetId!}">
	          <em><img src="${repair.picUrl!}" alt="图片"></em>
	          <h3>${repair.title!}</h3>
	          <p>${repair.desc!}</p>
	          <!--
	          <span>待评价</span><div class="actBtn"><button>评价</button></div>
	          -->
	        </a></li>
        </#list>
      </ul>
    </div>
    <div class="hide">
		<ul class="list myact">
			<#list repairList as repair>
				<#if repair.cstatus!?string = "1">
			       <li><a href="${base}/my/repairDetail.html?id=${repair.sheetId!}">
			          <em><img src="${imgUrl!}${repair.picUrl!}" alt="图片"></em>
			          <h3>${repair.title!}</h3>
			          <p>${repair.desc!}</p>
			          <!--
			          <span>待评价</span><div class="actBtn"><button>评价</button></div>
			          -->
			        </a></li>
		        </#if>
	        </#list>
	      </ul>
	</div>
    <div class="hide">
		<ul class="list myact">
	       <#list repairList as repair>
				<#if repair.cstatus!?string = "2">
			       <li><a href="${base}/my/repairDetail.html?id=${repair.sheetId!}">
			          <em><img src="${repair.picUrl!}" alt="图片"></em>
			          <h3>${repair.title!}</h3>
			          <p>${repair.desc!}</p>
			          <!--
			          <span>待评价</span><div class="actBtn"><button>评价</button></div>
			          -->
			        </a></li>
		        </#if>
	        </#list>
	      </ul>
	</div>
    <div class="hide">
		<ul class="list myact">
	       <#list repairList as repair>
				<#if repair.cstatus!?string = "3">
			       <li><a href="${base}/my/repairDetail.html?id=${repair.sheetId!}">
			          <em><img src="${repair.picUrl!}" alt="图片"></em>
			          <h3>${repair.title!}</h3>
			          <p>${repair.desc!}</p>
			          <!--
			          <span>待评价</span><div class="actBtn"><button>评价</button></div>
			          -->
			        </a></li>
		        </#if>
	        </#list>
	      </ul>
	</div>
    </div>
    
  </div>
</body>
<script>
 
 setNav2('#navList','#conList');

</script>
</html>