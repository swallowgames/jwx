<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>我的订单</title>
<#include "/comm/header.ftl" />
<#assign status=request.getParameter("status")?if_exists>
</head>
<body>
  <div class="mainBox2">
    <div class="topList topNav">
      <ul id="navList">
        <li class="on"><a href="javascript:;">全部<i></i></a></li>
        <li ><a href="javascript:;">未完成<i></i></a></li>
        <li ><a href="javascript:;">已完成<i></i></a></li>
      </ul>
    </div>
    <div class="conList" id="conList">
    <div>
    <#if orderList?exists>
    <ul class="list3 list4">
    	<#list orderList as order>
      <li><a href="myOrderDetail.html">
        <em><img src="#" alt="图片"></em>
        <h3>${order.orderPrice!} </h3>
        <p><span>总价：${order.orderPrice!}元</span>数量：1</p>
        <#if order.status!?string != '3'>
	        <span class="pj">未完成</span>
	        <span class="actBtn"><button>取消</button></span>
	    <#else>
	    	<!--
        	<span class="pj">待评价</span>
        	<span class="actBtn"><button onClick="location.href='pingjia.html'">评价</button></span>
        	-->
        </#if>
      </a></li>
      	</#list>
      <li><a href="myOrderDetail.html">
        <em><img src="${base}/resources/images/pic_l2.jpg" alt="图片"></em>
        <h3>超低温金枪鱼 新鲜蓝鳍金枪鱼刺身 新鲜300克 </h3>
        <p><span>总价：28.00元</span>数量：1</p>
        <span class="pj yellow">已评价4分</span>
      </a></li>
      <li><a href="myOrderDetail.html">
        <em><img src="${base}/resources/images/pic_l2.jpg" alt="图片"></em>
        <h3>超低温金枪鱼 新鲜蓝鳍金枪鱼刺身 新鲜300克 </h3>
        <p><span>总价：28.00元</span>数量：1</p>
        <span class="pj">未完成</span>
        <span class="actBtn"><button>取消</button></span>
      </a></li>
      <li><a href="myOrderDetail.html">
        <em><img src="${base}/resources/images/pic_l2.jpg" alt="图片"></em>
        <h3>超低温金枪鱼 新鲜蓝鳍金枪鱼刺身 新鲜300克 </h3>
        <p><span>总价：28.00元</span>数量：1</p>
        <span class="pj yellow">已评价4分</span>
      </a></li>
    </ul>
    </#if>
    </div>
    <div class="hide">
		<ul class="list3 list4">
    	  <#list orderList as order>
    		<#if order.status!?string != '3'>
		      <li><a href="myOrderDetail.html">
		        <em><img src="#" alt="图片"></em>
		        <h3>${order.orderPrice!} </h3>
		        <p><span>总价：${order.orderPrice!}元</span>数量：1</p>
		        <span class="pj">未完成</span>
	        	<span class="actBtn"><button>取消</button></span>
		      </a></li>
	      	</#if>
	      </#list>
	      <li><a href="myOrderDetail.html">
	        <em><img src="${base}/resources/images/pic_l2.jpg" alt="图片"></em>
	        <h3>超低温金枪鱼 新鲜蓝鳍金枪鱼刺身 新鲜300克 </h3>
	        <p><span>总价：28.00元</span>数量：1</p>
	        <span class="pj">未完成</span>
	        <span class="actBtn"><button>取消</button></span>
	      </a></li>
	      <li><a href="myOrderDetail.html">
	        <em><img src="${base}/resources/images/pic_l2.jpg" alt="图片"></em>
	        <h3>超低温金枪鱼 新鲜蓝鳍金枪鱼刺身 新鲜300克 </h3>
	        <p><span>总价：28.00元</span>数量：1</p>
	        <span class="pj">未完成</span>
	        <span class="actBtn"><button>取消</button></span>
	      </a></li>
	      <li><a href="myOrderDetail.html">
	        <em><img src="${base}/resources/images/pic_l2.jpg" alt="图片"></em>
	        <h3>超低温金枪鱼 新鲜蓝鳍金枪鱼刺身 新鲜300克 </h3>
	        <p><span>总价：28.00元</span>数量：1</p>
	        <span class="pj">未完成</span>
	        <span class="actBtn"><button>取消</button></span>
	      </a></li>
	    </ul>
	</div>
    <div class="hide">
		<ul class="list3 list4">
    	 <#list orderList as order>
    		<#if order.status!?string = '3'>
		      <li><a href="myOrderDetail.html">
		        <em><img src="#" alt="图片"></em>
		        <h3>${order.orderPrice!} </h3>
		        <p><span>总价：${order.orderPrice!}元</span>数量：1</p>
		        <!--
		        <span class="pj">待评价</span>
		        <span class="actBtn"><button onClick="location.href='pingjia.html'">评价</button></span>
		        -->
		      </a></li>
	      	</#if>
	      </#list>
	      <li><a href="myOrderDetail.html">
	        <em><img src="${base}/resources/images/pic_l2.jpg" alt="图片"></em>
	        <h3>超低温金枪鱼 新鲜蓝鳍金枪鱼刺身 新鲜300克 </h3>
	        <p><span>总价：28.00元</span>数量：1</p>
	        <span class="pj yellow">已评价4分</span>
	      </a></li>
	      <li><a href="myOrderDetail.html">
	        <em><img src="${base}/resources/images/pic_l2.jpg" alt="图片"></em>
	        <h3>超低温金枪鱼 新鲜蓝鳍金枪鱼刺身 新鲜300克 </h3>
	        <p><span>总价：28.00元</span>数量：1</p>
	        <span class="pj">未取消</span>
	      </a></li>
	      <li><a href="myOrderDetail.html">
	        <em><img src="${base}/resources/images/pic_l2.jpg" alt="图片"></em>
	        <h3>超低温金枪鱼 新鲜蓝鳍金枪鱼刺身 新鲜300克 </h3>
	        <p><span>总价：28.00元</span>数量：1</p>
	        <span class="pj yellow">已评价4分</span>
	      </a></li>
	    </ul>
	</div>
    
    </div>
    
  </div>
</body>
<script>
 
 setNav2('#navList','#conList');

</script>
</html>