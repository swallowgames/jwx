<!DOCTYPE html>
<html lang="zh-cn">
<head>
<#include "/comm/header.ftl" />
<title>物业报修</title>
</head>
<body class="gray">
<div class="mainBox2">

  <div class="sign orderDiv" id="inputList">
    <p class="tit-p">填写报修内容</p>
    <form name="fm" id="fm" enctype="multipart/form-data" action="${base}/uploadRepair.html" method="post">
    <ul id="inputList">
      <li><em>报修标题</em><span><input name="title" id="title" type="text" class="input_1" placeholder="请输入报修名称" value=""></span></li>
      <li><em class="ver-top">报修描述</em><span><textarea name="desc" id="desc" placeholder="请输入具体的报修详细描述" class="textarea_1" style="height:120px;"></textarea></span></li>
      <li><em>区域</em><span><input type="text" name="area" id="area" class="input_1" placeholder="请输入报修所在区域" value=""></span></li>
      <li><em>地址</em>
        <div class="addlist">
        	<input name="addr" id="addr" type="hidden">
        <#if member?? && member.orderStreet?? && member.orderStreet?trim != ''>
        	<span class="border on"><input type="checkbox" class="checkbox_1"><i class="likecheckbox"></i>${member.orderStreet?if_exists}</span>
            <span class="other"><input type="checkbox" class="checkbox_1"><i class="likecheckbox"></i><input id="other" type="text" class="input_1 addedit" placeholder="其他"></span>
        <#else>
        	<span class="other on"><input id="other" type="text" class="input_1 addedit" placeholder="请输入地址"></span>
        </#if>
        </div>
    </li>
      <li>
        <em class="ver-top">报修照片</em>
        <div class="img-upload clearFloat">
          <div class="photo_pic hide" id="prev_pic">
            <span class="photodiv hide"><img src="" id="img_1"/><a href="javascript:;" class="closebtn Hi-close"></a></span>
            <span class="photodiv hide"><img src="" id="img_2"/><a href="javascript:;" class="closebtn Hi-close"></a></span>
            <span class="photodiv hide"><img src="" id="img_3"/><a href="javascript:;" class="closebtn Hi-close"></a></span>
            <span class="photodiv hide"><img src="" id="img_4"/><a href="javascript:;" class="closebtn Hi-close"></a></span>
            <span class="photodiv hide"><img src="" id="img_5"/><a href="javascript:;" class="closebtn Hi-close"></a></span>
            <span class="photodiv hide"><img src="" id="img_6"/><a href="javascript:;" class="closebtn Hi-close"></a></span>
            <span class="photodiv hide"><img src="" id="img_7"/><a href="javascript:;" class="closebtn Hi-close"></a></span>
            <span class="photodiv hide"><img src="" id="img_8"/><a href="javascript:;" class="closebtn Hi-close"></a></span>
            <span class="photodiv hide"><img src="" id="img_9"/><a href="javascript:;" class="closebtn Hi-close"></a></span>
          </div>
          <span id="upload_pic">
          <div class="photodiv upload" id="pic_1">
          	<input  type="file" name="pic" multiple accept="image/*"/>
          </div>
          <div class="photodiv upload" id="pic_2">
          	<input  type="file" name="pic" multiple accept="image/*"/>
          </div>
          <div class="photodiv upload" id="pic_3">
          	<input  type="file" name="pic" multiple accept="image/*"/>
          </div>
          <div class="photodiv upload" id="pic_4">
          	<input  type="file" name="pic" multiple accept="image/*"/>
          </div>
          <div class="photodiv upload" id="pic_5">
          	<input  type="file" name="pic" multiple accept="image/*"/>
          </div>
          <div class="photodiv upload" id="pic_6">
          	<input  type="file" name="pic" multiple accept="image/*"/>
          </div>
          <div class="photodiv upload" id="pic_7">
          	<input  type="file" name="pic" multiple accept="image/*"/>
          </div>
          <div class="photodiv upload" id="pic_8">
          	<input  type="file" name="pic" multiple accept="image/*"/>
          </div>
          <div class="photodiv upload" id="pic_9">
          	<input  type="file" name="pic" multiple accept="image/*"/>
          </div>
          </span>
        </div>
      </li>
    </ul>
	</form>
  </div>
  <div class="btnDiv mt17">
    <button id="signBtn" class="btnGreen">提交保修</button>
  </div>
    
</div>
</body>

<script>
  $(function(){
      $('.addlist').delegate('span', 'click', function(event) {
          $(this).addClass('on').siblings().removeClass('on');
          if($(this).hasClass('other')){
            $(this).find('.input_1').focus();
          }
      });

    var supportFileReader;
    try{ if( (typeof FileReader) =='function') supportFileReader = true;}catch(e){ supportFileReader=false; }
    $('input[name="pic"]:visible').change(function(){
        $(".upload-state").hide();
        var fileUpload = $(this);
        if( supportFileReader ){ //console.log('in FileReader part'); //ie10 chrome , firefox
            if(this.files.length>=9){  return;}
            var invalid = 0;
            $.each(this.files, function(i, file, files){
                if(! /image\/\w+/.test(file.type) ){ //file.type 文件的mime值
                   alert("请选择图像文件"); ++invalid; return false;
                }else if(file.size>5242880){
                   alert("所选择图像文件不能大于5M"); ++invalid; return false;
                }else{
                    var reader = new FileReader();
                    reader.readAsDataURL(file);//用户选择的文件readAsDataURL
                    reader.onload = function(){
                        $('#prev_pic span.hide:first').find('img').attr('src', this.result).end().removeClass('hide');
                        $('#prev_pic').slideDown(200);
                        fileUpload.parent().hide();
                    }
                }
            });

            if(invalid) return;

        }else{ //ie 6 7 8
            this.select(); 
            var img = new Image(); 
            var fpath = document.selection.createRange().text; //获取文件域的路径
            var reImg = /\w+\.(bmp|jpg|jpeg|png|gif)$/i;
            if(! reImg.test(fpath) ) {//非图像文件 则提示并退出
                alert('请选择图像文件'); return;
            }
            var upImg = $('#prev_pic span.hide:first img')[0];
            upImg.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);";
            upImg.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = fpath;
            $(upImg).parent().removeClass('hide');

            $('#prev_pic').slideDown(200);
            fileUpload.parent().hide();
        }
    });
    
    // 点击删除已上传图片
    $('#prev_pic .Hi-close').click(function(){
    	var id = $(this).prev().attr("id");
    	var pic_id = id.replace("img_","pic_");
        $(this).parent().remove(); 
        $('#prev_pic').append( $('#prev_pic')[0].imgSpan.clone(true));
        var fileDiv = $($('#upload_pic div[id='+pic_id+']')[0]);
        var file = fileDiv.find("input");
        file.val("");
        fileDiv.show();
    });
    //保存小图预览模板
    $('#prev_pic')[0].imgSpan = $('#prev_pic').children().first().clone(true);
    
    $("#signBtn").click(function(){
    	$('.addlist span').each(function(){
    		if($(this).hasClass('border on')){
    		  //会员地址
	          $("#addr").val('<#if member??>${member.orderStreet?if_exists}</#if>');
	          return;
	        }
	        if($(this).hasClass('other on')){
	          //用户输入的地址
	          $("#addr").val($(this).find('.input_1').val());
	          return;
	        }
    	});
    	
    	if($.trim($('#title').val()) == ''){
    		alert("请填写报修标题");
    		return;
    	}
    	
    	if($.trim($('#addr').val()) == ''){
    		alert("请填写地址信息");
    		return;
    	}
    	
		$("#fm").submit();
		$("#signBtn").text("提交中...");
		$("#signBtn").attr("disabled","disabled");
		
		$("input[type='file']").each(function(){
			$(this).val("");
		});
		
    });
    
});
</script>
</html>