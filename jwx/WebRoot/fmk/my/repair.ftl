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
    <ul id="inputList">
      <li><em>报修标题</em><span><input name="" type="text" class="input_1" placeholder="请输入报修名称" value=""></span></li>
      <li><em class="ver-top">报修描述</em><span><textarea placeholder="请输入具体的报修详细描述" class="textarea_1" style="height:120px;"></textarea></span></li>
      <li><em>区域</em><span><input name="" type="text" class="input_1" placeholder="请输入报修所在区域" value=""></span></li>
      <li><em>地址</em>
        <div class="addlist">
        <#if member.orderStreet?? && member.orderStreet?trim != ''>
        	<span class="border on"><input name="" type="checkbox" class="checkbox_1"><i class="likecheckbox"></i>${member.orderStreet?if_exists}</span>
            <span class="other"><input name="" type="checkbox" class="checkbox_1"><i class="likecheckbox"></i><input name="" type="text" class="input_1 addedit" placeholder="其他"></span>
        <#else>
        	<span class="other on"><input name="" type="text" class="input_1 addedit" placeholder="请输入地址"></span>
        </#if>
        </div>
    </li>
      <li>
        <em class="ver-top">报修照片</em>
        <div class="img-upload clearFloat">
          <div class="photo_pic hide" id="prev_pic">
            <span class="photodiv hide"><img src="" /><a href="javascript:;" class="closebtn Hi-close"></a></span>
            <span class="photodiv hide"><img src="" /><a href="javascript:;" class="closebtn Hi-close"></a></span>
            <span class="photodiv hide"><img src="" /><a href="javascript:;" class="closebtn Hi-close"></a></span>
            <span class="photodiv hide"><img src="" /><a href="javascript:;" class="closebtn Hi-close"></a></span>
            <span class="photodiv hide"><img src="" /><a href="javascript:;" class="closebtn Hi-close"></a></span>
            <span class="photodiv hide"><img src="" /><a href="javascript:;" class="closebtn Hi-close"></a></span>
            <span class="photodiv hide"><img src="" /><a href="javascript:;" class="closebtn Hi-close"></a></span>
            <span class="photodiv hide"><img src="" /><a href="javascript:;" class="closebtn Hi-close"></a></span>
            <span class="photodiv hide"><img src="" /><a href="javascript:;" class="closebtn Hi-close"></a></span>
          </div>
          <div class="photodiv upload"><input type="file" name="pic" id="pic"/></div>
          <p class="upload-state">照片利于报修定位</p>
        </div>
      </li>
    </ul>

  </div>
  <div class="btnDiv mt17">
    <button id="signBtn" class="btnGreen btnGray" disabled="disabled" onclick="">提交保修</button>
  </div>
    
</div>
</body>

<script>
  $(function(){
      setClickBtn('#inputList','#signBtn');
      $('.addlist').delegate('span', 'click', function(event) {
          $(this).addClass('on').siblings().removeClass('on');
          if($(this).hasClass('other')){
            $(this).find('.input_1').focus();
          }
      });

    var supportFileReader;
    try{ if( (typeof FileReader) =='function') supportFileReader = true;}catch(e){ supportFileReader=false; }
    // var isie = navigator.userAgent.toLowerCase().indexOf('msie')>=0;
    //console.log('supportFileReader=',supportFileReader);
    $('#pic').change(function(){
        $(".upload-state").hide();
        if( supportFileReader ){ //console.log('in FileReader part'); //ie10 chrome , firefox
            if(this.files.length>=9){  return;}
            var invalid = 0;
            $.each(this.files, function(i, file, files){
                if(! /image\/\w+/.test(file.type) ){ //file.type 文件的mime值
                    $.popMask("请选择图像文件"); ++invalid; return false;
                }else if(file.size>5242880){
                    $.popMask("所选择图像文件不能大于5M"); ++invalid; return false;
                }else{
                    var reader = new FileReader();
                    reader.readAsDataURL(file);//用户选择的文件readAsDataURL
                    reader.onload = function(){
                        $('#prev_pic span.hide:first').find('img').attr('src', this.result).end().removeClass('hide');
                        $('#prev_pic').slideDown(200, up_count);
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
                $.popMask({html:'请选择图像文件'}); return;
            }
            var upImg = $('#prev_pic span.hide:first img')[0];
            upImg.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);";
            upImg.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = fpath;
            $(upImg).parent().removeClass('hide');

            $('#prev_pic').slideDown(200, up_count);
        }
    });
    
    // 计算预上传 和 剩余可上传
    function up_count(){ 
        var uploaded_num = $('#prev_pic span:not(.hide)').length;
        console.log(uploaded_num);
        if(uploaded_num>=9){
            $(".photodiv.upload").hide();
        }else{
            $(".photodiv.upload").show();
        }
    }
    
    // 点击删除已上传图片
    $('#prev_pic .Hi-close').click(function(){
        $(this).parent().remove(); 
        up_count();
        $('#prev_pic').append( $('#prev_pic')[0].imgSpan.clone(true));
    });
    //保存小图预览模板
    $('#prev_pic')[0].imgSpan = $('#prev_pic').children().first().clone(true);
});
</script>
</html>