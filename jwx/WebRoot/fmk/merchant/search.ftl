<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title  id="headTitle">商品搜索</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
<div class="mainBox2">
  <div class="searchBar"><input id="search" name="search" type="text" placeholder="请输入商家名"><i></i></div>
  <!--
  <div class="hotList mt17">
    <ul>
      <li><a href="javascript:void(0);">热门搜索</a></li>
      <li><a href="${base}/merchant/doSearch.html?name=披萨">披萨</a></li>
      <li><a href="${base}/merchant/doSearch.html?name=自助餐">自助餐</a></li>
      <li><a href="${base}/merchant/doSearch.html?name=蛋糕">蛋糕</a></li>
      <li><a href="${base}/merchant/doSearch.html?name=铁观音">铁观音</a></li>
      <li><a href="${base}/merchant/doSearch.html?name=普洱">普洱</a></li>
      <li><a href="${base}/merchant/doSearch.html?name=棋牌">棋牌</a></li>
      <li><a href="${base}/merchant/doSearch.html?name=KTV">KTV</a></li>
      <li class="last"><a href="${base}/merchant/doSearch.html?name=奇异果">奇异果</a></li>
      <li class="last"><a href="${base}/merchant/doSearch.html?name=车厘子">车厘子</a></li>
      <li class="last"><a href="${base}/merchant/doSearch.html?name=开锁">开锁</a></li>
      <li class="last"><a href="${base}/merchant/doSearch.html?name=修电器">修电器</a></li>
    </ul>
  </div>
  <div class="hisList mt17">
    <ul>
      <li><a href="${base}/merchant/doSearch.html?name=澄湖大闸蟹"><span>澄湖大闸蟹</span><i class="icon_clock"></i><i class="icon_pr"></i></a></li>
      <li><a href="${base}/merchant/doSearch.html?name=新鲜百香果"><span>新鲜百香果</span><i class="icon_clock"></i><i class="icon_pr"></i></a></li>
      <li><a href="${base}/merchant/doSearch.html?name=小龙虾"><span>小龙虾</span><i class="icon_clock"></i><i class="icon_pr"></i></a></li>
    </ul>
  </div>
  <br>
  -->
</div>
</body>
<script language="javascript">  
$("#search").keydown(function(event) {
    if (event.keyCode == 13) {//keyCode=13是回车键
        var name = $(this).val();
        if(name != null && name.trim() != ''){
        	location.href="${base}/merchant/doSearch.html?name="+encodeURI(encodeURI(name));
        }
    }
});
</script> 
</html>