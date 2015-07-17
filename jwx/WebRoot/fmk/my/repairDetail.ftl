<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>我的报修</title>
<#include "/comm/header.ftl" />
</head>
<body class="gray">
<div class="mainBox2">
 <div class="baoxiu">
 	<!--完成-->
 	<i class="zhang"></i>
  <ul>
   <li><span class="green fl">${repairInfo.STATUS?if_exists}</span>
   <!--<span class="time fr">报修时间：08.09  22:30</span>--></li>
   <li class="tw">
     <span class="gray">业主：</span><span>${member.name?if_exists}</span><br>
     <span class="gray">房号：</span><span>${repairInfo.commu_name?if_exists} ${repairInfo.building_name?if_exists} ${repairInfo.unit_name?if_exists} ${repairInfo.room_name?if_exists}</span>
   </li>
  </ul>
 </div>
 
 <div class="liucheng mt17">
 	<#if repairDetail?? && repairDetail.data??>
   <ul>
   	<#list repairDetail.data as detail>
	 <li <#if detail_index == 0>class="on"</#if>>
       <div class="left"><span class="time">${detail.sheetNo?if_exists}</span></div>
       <div class="right"><span class="tit">${detail.description?if_exists}</span></div>
     </li>
   	</#list>
   	<!--
     <li class="on">
       <div class="left"><span class="time">08.10 18:29</span><span>处理完成</span></div>
       <div class="right"><span class="tit">工程主管 张述畅</span><span>物业管理工程部</span></div>
     </li>
     <li>
       <div class="left"><span class="time">08.10 18:29</span><span>处理完成</span></div>
       <div class="right"><span class="tit">工程主管 张述畅</span><span>物业管理工程部</span></div>
     </li>
     <li>
       <div class="left"><span class="time">08.10 18:29</span><span>处理完成</span></div>
       <div class="right"><span class="tit">工程主管 张述畅</span><span>物业管理工程部</span></div>
     </li>
     <li>
       <div class="left"><span class="time">08.10 18:29</span><span>处理完成</span></div>
       <div class="right"><span class="tit">工程主管 张述畅</span><span>物业管理工程部</span></div>
     </li>
     -->
   </ul>
   </#if>
 </div>
    
</div>
</body>
</html>