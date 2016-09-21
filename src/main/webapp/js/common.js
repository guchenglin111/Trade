$(function(){
	//鼠标划过头部个人部分
	common.clickSetInfo();
	//点击收藏
	common.favorite();
	//点击添加到购物车
	common.addShop();
	common.goTop();
	// common.warnLog();
	//判断是否登陆
	common.judegLogin();
	common.controlSearch();
	//最上边的那个搜索
	common.headSearch();
	//退出登录
	common.quite();
	//点击头像，回到首页
	common.goIndex();
	common.writeName();
	//初始化消息
	common.reciveMsg();
	//点击出现消息框
	common.clickMsgLeaf();
})
var common = {
	writeName : function(){
		if($.cookie('user')){
			$('#name').html($.cookie('user'))
		}
	},
	//鼠标划过头部个人部分
	clickSetInfo : function(){
		$("#arrow").off('click').on('click' , function(e){
			if($(this).attr('type')=="up"){
				$(this).html("&#xea43");
				$(this).attr('type' , 'down');
			}else{
				$(this).html("&#xea41");
				$(this).attr('type' , 'up');
			}
			$(".header-info-ls-set").toggle();
			e.stopPropagation();
		});
		$(document).off('click').on('click' , function(){
			$("#headerArrow").html("&#xea41");
			$(".header-info-ls-set").hide();
			$('.header-info-msg').hide();
		})
		
	},
	//点击收藏
	favorite : function(){
		$(".label-heart").off('click').on('click' , function(){
			if($(this).attr('data-favorite') == 0){
				$(this).css('color' , 'red');
				$(this).attr('data-favorite' , '1')
			}else{
				$(this).css('color' , '#999');
				$(this).attr('data-favorite' , '0');
			}
			var songId = $(this).parent('.music-cont-ls-li').attr('songId');
			$.ajax({
				type: "GET",
				url: "",
				data: songId,
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				success: function(data){
					$('.index-hot-nav').empty();   
					var html = '';
					$.each(obj , function(index , item){
						list.describeHtml(arr);
					})
				}
			});
		})
	},
	//点击添加到购物车
	addShop : function(){
		$(".label-add").off('click').on('click' , function(){
			$(this).addClass('click');
			var $that = $(this);
			setTimeout(function(){
				$that.removeClass('click');
			} , 2000);
		})

	},
	
	goTop : function(){
		$('#goTop').off('click').on('click' , function(){
			var speed=200;//滑动的速度
	        $('body,html').animate({ scrollTop: 0 }, speed);
	        return false;
		})
	},
/*
	warnLog : function(){
		$("#goTop").show();
		common.goTop();
		setTimeout(function(){
			$("#goTop").hide();
		} , 5000);
	},*/
	judegLogin : function(){
		if(!$.cookie('user')){
			$('#loginHave').hide();
			$('#loginNone').show();
		}else{
			$('#loginHave').show();
			$('#loginNone').hide();
			$('#name').html($.cookie('user'));
		}
		$('#login').off('click').on('click' , function(){
			window.location = 'login';
		})
	},
	controlSearch : function(){
		$('.list-search').focus(function(){
			$('.list-tishi').hide();
		}).blur(function(){
			if(!$(this).val()){
				$('.list-tishi').show();
			}
		})
	},
	headSearch : function(){
		$('.search').keydown(function(event){
			if(event.keyCode == 13){
				var key = $('.search').val();
				if(!key){
					return false;
				}
				$.cookie('headSearch' , key);
				window.location = 'goodsList';
			}
		});

	},
	clickMsgLeaf : function(){
		$('#alarm').off('click').on('click' , function(e){
			$('.header-info-msg').show();
			e.stopPropagation();
		});
	},
	quite : function(){
		$('#quite').off('click').on('click' , function(){
			$.cookie('user' , '');
			$.ajax({
				type: "POST",
				url: "user/quit",
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				success: function(data){
					window.location = 'list';
				},
				error : function(){

				}
			});
		})
	},
	goIndex : function(){
		$('.header-logo').off('click').on('click' , function(){
			window.location = 'list';
		})
	},
	reciveMsg : function(){
		$.ajax({
			type: "POST",
			url: "user/message",
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data){
				var objs = data.messageList;
				var html = '';
				$('.header-info-msg').empty();
				$.each(objs , function(index , item){
					html += "<li>";
					html += "<a href='javascript:void(0);' class='msg cutWord'>"+item.content+"</a>";
					html += "<a href='javascript:void(0);' class='msg-read' ><i class='icomoon leaf' msg-id='"+item.id+"' state='0'>&#xe9a4</i></a>";
					html += "</li>";
				});
				$('.msg-num').html(objs.length);
				$('.header-info-msg').append(html);

				$('.msg-read').each(function(index , item){
					$(this).off('click').on('click' , function(){
						$(this).find('i').css('color' , '#3ACE00')
						var id = $(this).find('i').attr('msg-id');
						$.ajax({
							type: "POST",
							url: "user/read",
							data : JSON.stringify({'id':id}),
							dataType: "json",
							contentType: "application/json; charset=utf-8",
							success: function(data){
								
							},
							error : function(){

							}
						});
					})
				})

			},
			error : function(){

			}
		});

		
	}
	
}