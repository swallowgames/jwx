
	
//==========功能函数=======
function $id(id){return document.getElementById(id);}

function each(eles, fn){
        for(var i=0, len= eles.length; i<len; i++){
            fn(eles[i],i,eles);
        }
    }

function swDisplay(o){
        var dv=(o.currentStyle||window.getComputedStyle(o,null)).display;
        switch (dv){
        case "":
            o.style.display="none";
            break;
        case "block":
            o.style.display="none";
            break;
        case "inline":
            o.style.display="none";
            break;
        case "none":
            o.style.display="block";
            break;
        default:
            break;
        }
    }

//==========下拉框=======
function showSlect(option){
	
  var txt=option.span,
      div=option.div;
  if(txt==undefined||div==undefined){return}
  
  $id(txt).onclick = function(){
		  swDisplay( $id(div) );
	  }
	  
  var Atags = $id(div).getElementsByTagName('a');
	  each(Atags,function(ele){
		  ele.onclick = function(){ $id(txt).innerHTML = this.innerHTML+'<s></s>'; swDisplay( $id(div) );};
	  });
}

//模拟单选按钮效果
function radioFun(o,list){
	
	var ls='span'; 
    if(list!=undefined){ls=list}
	   
    var radios = $id(o).getElementsByTagName(ls);
    /*each(radios,function(ele,i,eles){
        (function(m){ele.onclick = function(){ this.className='on'; eles[1-m].className=''; this.firstChild.checked = true;};})(i);
    });*/
	each(radios,function(ele,i,eles){
        (function(m){ele.onclick = function(){ 
		
		for(var k=0; k<radios.length; k++){
			radios[k].className='';
			radios[k].firstChild.checked = false;
			}
		
		this.className='on'; 
		
		//eles[1-m].className=''; 
		this.firstChild.checked = true;};})(i);
    });
}

//模拟复选按钮效果
function checkBoxFun(o,list){
	
	var ls='span'; 
    if(list!=undefined){ls=list}
	    
    var radios = $id(o).getElementsByTagName('span');
    each(radios,function(ele,i,eles){
        (function(m){ele.onclick = function(){ 
		     if(this.className=='on'){
				 this.className=''
				 this.firstChild.checked = false;
				 }else{
					 this.className='on';
					 this.firstChild.checked = true;
					 }
			 };})(i);
    });
}


function onfouse(o){
	var div = $id(o);
	var inp = div.getElementsByTagName('input')[0];
	inp.onfocus=function(){
		div.className='inpOut on'
		}
	inp.onblur=function(){
		div.className='inpOut'
		}
	}
		
/**
 * 功能实现：
 * 弹出层
 **/
function winMask(options) {
	var id = options.id || 'winMask',
		tit = options.tit || '提示',
		cls = options.cls || '',
		html = options.html || '内容',
		btn = options.btn || '',
		noClose = options.noClose || false,
		closeFun = options.closeFun || '';
		closeFunPar = options.closeFunPar||'';
	
	var dm = dce('div', 'divMask', 'divMask');
	var wm = dce('div', id, 'winMask');
	wm.className += ' ' + cls;
	var cl = '<a href="javascript:;" class="close" id="' + id + 'Close"></a>';
	
	if (noClose) cl = '';
	wm.innerHTML = '<div class="inner">' +
				'<div class="tit">' + tit + cl + '</div>' +
				'<div class="det"><p>' + html + '</p><div class="tc">' + btn + '</div></div>' +
				'</div>';
	
	document.body.appendChild(dm);
	document.body.appendChild(wm);
	
	if (!noClose) {
		document.getElementById(id+'Close').onclick = function() {
			closeMask(id);
			
			if (closeFun) {
				if (typeof(closeFun) == 'function') {
					if(closeFunPar){
						closeFun(closeFunPar);
						}else{
							closeFun();
							}
				} else {
					eval(closeFun);
				}
			}
		}
	}
	
	wm.style.marginTop = -wm.offsetHeight/2+'px';
}
function closeMask(id) {
	var obj = document.getElementById(id);
	var mask = document.getElementById('divMask');
	document.body.removeChild(mask);
	document.body.removeChild(obj);
}

function dce(elm, id, cn) {
	var obj = document.createElement(elm);
	obj.id = id;
	obj.className = cn;
	return obj;
}

function linkActive(o){
	if(!$(o)){return}
	var as = $(o).find('a');
	as.each(function(i){
		as.eq(i).touchstart(function(){
			as.eq(i).addClass('on');
			})
		as.eq(i).touchend(function(){
			as.eq(i).removeClass('on');
			})
		as.eq(i).touchmove(function(){
			as.eq(i).attr('class','on');
			})
		})
	}
	
function backLink(a,c){

        a.touchstart(function(){
			a.addClass(c);
			})
		a.touchend(function(){
			a.removeClass(c);
			})
		a.touchmove(function(){
			a.addClass(c);
			})
		/*a.bind("touchstart",function(){alert(1);
            a.addClass(c);
         });*/
	}	
$(document).ready(function(){
	
	var btn_gj = $('.btn_gj');
	btn_gj.each(function(){
		backLink($(this),'btn_gj_on');
		})
		
	var btn_gray = $('.btn_1');
	btn_gray.each(function(){
		backLink($(this),'btn_1_on');
		})
		
	var btn_red = $('.btn_red');
	btn_red.each(function(){
		backLink($(this),'btn_red_on');
		})
		
	var alink = $('.aLink');
	alink.each(function(){
		backLink($(this),'aLink_on');
		})
	
	
}); 

function setSelect(obj){
	if(!$(obj)){return}
	var sel = $(obj).find('select').eq(0);
	var sp = $(obj).find('span').eq(0);
	
	sel.change(function(){
      sp.html($(this).children('option:selected').val());
	  sp.addClass('chan');
	})
	
	}
	
function tabSwitch(o){
	var tab = $(o.tab).find('li');
	var con = $(o.con).find('div');
	tab.each(function(i){
		tab.eq(i).click(function(){
			
			for(var k=0;k<tab.size();k++){
				tab.eq(k).removeClass('on');
				con.eq(k).hide();
				}
			tab.eq(i).addClass('on');
			con.eq(i).fadeIn();
			})
		
		})
	
	var wh = $(window).height();	
	var offset = $(o.con).offset();
	var divh = wh - offset.top - 85;
	con.each(function(){
		$(this).css({'max-height':divh+'px'})
		})
	
	}
	
function setNav(o){
	var obj = $(o);
	var w1 = $(window).width();
	var liw = w1/4-80/4;
	var ulw = liw*obj.find('li').size();
	obj.width(ulw);
	var con = $('.conList').eq(0).find('div');
	obj.find('li').each(function(i){
		obj.find('li').eq(i).width(liw);
		obj.find('li').eq(i).bind('click',function(){
			$(this).addClass('on').siblings().removeClass('on');
			con.eq(i).removeClass('hide').siblings().addClass('hide');
			})
		})
	//alert(obj.css('margin-left'))
	obj.bind("swipeleft",function(){
      var ml =  parseInt(obj.css('margin-left'));
	  if(liw*8-ml<=ulw){
		  obj.animate({'margin-left':(ml-liw*4)+'px'},500);
		  }else if(liw*4-ml<=ulw&&liw*8-ml>ulw){
			  obj.animate({'margin-left':(liw*4-ulw)+'px'},500);
			  }
    });
	obj.bind("swiperight",function(){
      var ml =  parseInt(obj.css('margin-left'));
	  if(ml+liw*4>=0){
		  obj.animate({'margin-left':0 +'px'},500);
		  }else if(ml+liw*4<0){
			  obj.animate({'margin-left':(ml+liw*4)+'px'},500);
			  }
    });
	
}

function setCout(o){
	
	var cl=$(o);
	cl.each(function(){
	var cd=$(this).find('.jie'),
	    ad=$(this).find('.add'),
		inp=$(this).find('input').eq(0),
		minNum=$(this).find("input[name='minNum']").attr('value')||1,
		maxNum=$(this).find("input[name='maxNum']").attr('value');
		var price = parseFloat($("#price").text());
		cd.click(function(){
			
			var cou=parseInt(inp.attr('value'));
			  if(cou<=parseInt(minNum)){
				  cd.addClass('def');
				  return
				  }else{
					  inp.attr('value',cou-1);
					  $("#totalPrice").text(parseFloat(price * (cou-1)).toFixed(2));
					  ad.removeClass('def');
					  }
			});
		ad.click(function(){
			var cou=parseInt(inp.attr('value'));
			if(maxNum==null){
				inp.attr('value',cou+1);
				$("#totalPrice").text(parseFloat(price * (cou+1)).toFixed(2));
				}else{
					if(cou>=parseInt(maxNum)){
					   ad.addClass('def');
					   return
					   }else{
						  inp.attr('value',cou+1); 
						  $("#totalPrice").text(parseFloat(price * (cou+1)).toFixed(2));
						  cd.removeClass('def');
						   }
					}
		
			});
	});
	}
	
function showMoreList(o,n){
	var lis = $(o).find('ul li');
	var am = $(o).find('.ml a').eq(0);
	var num = n||4;
	
	lis.each(function(i){
		if(i>=num){lis.eq(i).addClass('hide')}
		})
	am.bind('click',function(){
		lis.each(function(i){
			lis.eq(i).removeClass('hide');
			if(i==lis.size()-1){
				lis.eq(i).css({'border-bottom':'none'})
				}
			})
		$(o).find('.ml').eq(0).addClass('hide');
		})
	
	}
	
function setClickBtn(obj,btn){
	var ul = $(obj);
	var btn = $(btn);
	var inps = ul.find('input.input_1');
	
	inps.each(function(i){
		inps.eq(i).blur(function(){
			var kk = 0;
            for(var j=0;j<inps.size();j++){
				if(inps.eq(j).val()!=''){kk++}
				}
			if(kk==inps.size()){
				btn.removeClass('btnGray');
				btn.removeAttr("disabled");
				}else{
					btn.addClass('btnGray');
					btn.attr("disabled","disabled");
					}
      });
		})
	}

function setClickBtn2(obj,btn){
	var ul = $(obj);
	var btn = $(btn);
	var inps = ul.find('textarea');
	
	inps.each(function(i){
		inps.eq(i).blur(function(){
			var kk = 0;
            for(var j=0;j<inps.size();j++){
				if(inps.eq(j).val()!=''){kk++}
				}
			if(kk==inps.size()){
				btn.removeClass('btnGray');
				btn.removeAttr("disabled");
				}else{
					btn.addClass('btnGray');
					btn.attr("disabled","disabled");
					}
           });
		})
	}

function setNav2(tab,con){
	var tab = $(tab).find('li');
	var con = $(con).children('div');
	tab.each(function(i){
		tab.eq(i).bind('click',function(){
			tab.eq(i).addClass('on').siblings().removeClass('on');
			con.eq(i).removeClass('hide').siblings().addClass('hide');
			})
		})
	}
function setTab(tab){
	var tab = $(tab).find('span');
	tab.each(function(i){
		tab.eq(i).bind('click',function(){
			tab.eq(i).addClass('on').siblings().removeClass('on');
			})
		})
	}
	
function setStar(obj){
	var star=$(obj).find('i');
	star.each(function(i){
		star.eq(i).bind('click',function(){
			for(var k=0;k<star.size();k++){
				if(k<=i){
					star.eq(k).addClass('on');
					}else{
						star.eq(k).removeClass('on');
						}
				}
			})
		})
	}