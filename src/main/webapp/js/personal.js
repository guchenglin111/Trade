$(function(){
	//初始化个人信息
	mine.initPersonal();
	//支付弹窗关闭，未支付成功
	mine.frameClose();
	//编辑个人信息
	mine.editClick();
	//修改密码
	mine.editPass();
	//关闭修改资料弹窗
	mine.editClose();
	//充值
	mine.addMoney();
	//点击完成订单按钮
	mine.compTrade();
	//切换订单导航
	mine.chanegNav();
	mine.addFllow();
	//切换导航栏
	mine.navSwitch();
})
var mine = {
	//是否是当前登录者的个人中心
	boolMine : false, //默认不是自己
	currentUserId : $.cookie('currentUserId'), //当前正在页面显示信息人的id
	initPersonal : function(){
		//在最上边的用户名上添加信息
		$('#name').html($.cookie('user'));
		//看的这个人的id
		var currentUserId = $.cookie('currentUserId');
		$.cookie('currentUserId' , '');
		if(!currentUserId){
			currentUserId = '-1';
			// mine.boolMine = true;
		}
		//初始化好友信息
		$.ajax({
			type: "POST",
			url: "user/friend",
			data: JSON.stringify({'id':currentUserId}),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data){
				var obj = data.userInfos;
				var html = "";
				$('#fllowers').empty();
				$.each(obj , function(index , item){
					html += "<li class='fllower-li' data-id='"+item.id+"'>";
					html += "<a href='javascript:void(0);'>";
					html += "<i class='icomoon personal-nav-label'>&#xe951</i><span>"+item.name+"</span>";
					html += "</a></li>";
				});
				$('#fllowers').append(html);
				$('#fllowersNum').html(obj.length);
				$('.fllower-li').each(function(index , item){
					$(this).off('click').on('click' , function(){
						$.cookie('currentUserId' , $(this).attr('data-id'));
						window.location = 'personal';
					})
				})
			},
			error:function(){

			}
		});
		//初始化粉丝信息
		$.ajax({
			type: "POST",
			url: "user/fans",
			data: JSON.stringify({'id':currentUserId}),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data){
				var obj = data.userInfos;
				var html = "";
				$('#fllowings').empty();
				$.each(obj , function(index , item){
					html += "<li class='fllowing-li' data-id='"+item.id+"'>";
					html += "<a href='javascript:void(0);'>";
					html += "<i class='icomoon personal-nav-label'>&#xe951</i><span>"+item.name+"</span>";
					html += "</a></li>";
				});
				$('#fllowings').append(html);
				$('#fllowingsNum').html(obj.length);
				$('.fllowing-li').each(function(index , item){
					$(this).off('click').on('click' , function(){
						$.cookie('currentUserId' , $(this).attr('data-id'));
						window.location = 'personal';
					});
				})
			},
			error:function(){

			}
		});

		//初始化个人信息
		$.ajax({
			type: "POST",
			url: "user/show",
			data: JSON.stringify({'id':currentUserId}),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data){
				var item = data.user;
				//名字
				$('#userName').html(item.name);
				//当前余额
				$('#money').html(item.asset);
				//头像
				$('#personalLogo').attr('src' , item.portrait);
				/*//好友数量
				$('#fllowersNum').html(item.);
				//粉丝数量
				$('#fllowingsNum').html(item.);*/
				//如果是查看别人的个人中心页,ifSelf是true的话是自己，否则是别人
				mine.boolMine = data.ifSelf;
				if(!mine.boolMine){
					//隐藏编辑个人资料按钮
					$('.pernal-edit').hide();
					//隐藏余额和充值按钮
					$('.pernal-info-money').hide();
					if($.cookie('user')){
						//显示加关注或者取消关注按钮
						$.ajax({
							type: "POST",
							url: "user/ifForcus",
							data: JSON.stringify({'id':currentUserId}),
							dataType: "json",
							contentType: "application/json; charset=utf-8",
							success: function(data){
								if(data.result){
									var html = '取消关注';
									var state = '1';
								}else{
									var html = '加关注';
									var state = '0';
								}
								$('.pernal-add-follow').show().find('a').attr('state',state).html(html);
							},
							error : function(){

							}
						});
					}else{
						$('.pernal-add-follow').show().find('a').attr('state','0').html("加关注");
					}
					
					
				}
			},
			error:function(){

			}
		});

		//初始化订单 默认为全部订单
		mine.initDefaultList();
	},
	navSwitch : function(){
		var currentUserId = Number(mine.currentUserId);
		if(currentUserId == ''){
			currentUserId = -1;
		};
		function removeClass(){
			$('.pernal-nav-ls li').each(function(index , item){
				$(this).removeClass('active')
			})
		}
		//发布列表
		$('#default').off('click').on('click' , function(){
			removeClass();
			$(this).addClass('active');
			mine.initPublishList(currentUserId);
			$('.music-cont-info-ls-i10').each(function(){
				$(this).css('visibility' , 'hidden');
			})
		})
		//借入订单
		$('#borrow').off('click').on('click' , function(){
			removeClass();
			$(this).addClass('active');
			mine.initBrrowwList(currentUserId);
			if(mine.boolMine){
				$('.music-cont-info-ls-i10').each(function(index , item){
					var state = $(this).attr('trade-status');
					if(state == 0){
						$(this).prev().prev().css('visibility' , 'visible');
					}
				});

				//完成订单操作
				$('.complete-item').each(function(index, item){
					$(this).off('click').on('click' , function(e){
						e.stopPropagation();
						$(this).parent().css('visibility' , 'hidden');
						$(this).parent().next().next().html('已完成');
						var itemId = $(this).closest('.music-cont-ls-li').attr('item-id');
						var ownerId = $(this).closest('.music-cont-ls-li').attr('onwer-id');
						var tradeId = $(this).closest('.music-cont-ls-li').attr('trade-id');
						$.ajax({
							type : 'POST',
							url : 'user/return',
							data : JSON.stringify({'goods': Number(itemId) , 'owner': Number(ownerId) , 'id' : Number(tradeId)}),
							dataType : 'json',
							contentType: "application/json; charset=utf-8",
							success : function(){

							},
							error : function(){

							}
						});
					});
				});
			}
		});
		//借出订单
		$('#lend').off('click').on('click' , function(){
			removeClass();
			$(this).addClass('active');
			mine.initLendList(currentUserId);
		});
		//收藏订单
		$('#store').off('click').on('click' , function(){
			removeClass();
			$(this).addClass('active');
			mine.initCollList(currentUserId);
			if(mine.boolMine){
				$('.music-cont-info-ls-i6').each(function(){
					$(this).css('visibility' , 'visible');
				});
				//取消收藏
				$('.label-heart').each(function(){
					$(this).off('click').on('click' , function(){
						$(this).css('color','#999');
						var id = $(this).closest('.music-cont-ls-li').attr('item-id')
						$.ajax({
							type : 'POST',
							url : 'user/cancleCollect',
							data : JSON.stringify({'goods': Number(id)}),
							dataType : 'json',
							contentType: "application/json; charset=utf-8",
							success : function(data){

							},
							error : function(){

							}
						});
					})
				});
			}
			$('.music-cont-info-ls-i10').each(function(){
				$(this).css('visibility' , 'hidden');

			});

			
		})
	},
	initDefaultList : function(data , curr){
		var data = Number(mine.currentUserId);
		if(data == ''){
			data = -1;
		};
		var curr = curr || 1;
		$.ajax({
			type : 'POST',
			url : 'user/trade/list?page='+curr,
			data : JSON.stringify({ 'id':data}),
			dataType : 'json',
			async : false,
			contentType: "application/json; charset=utf-8",
			success : function(data){
				var objs = data.list.list;
				var html = '';
				$('.music-cont-ls').empty();
				$.each(objs , function(index , item){
					var date = new Date(item.outTime);
					var dateStr = date.getFullYear()+'-'+date.getMonth()+'-'+date.getDate();
					html += "<li class='music-cont-ls-li' item-id ='"+item.id+"' onwer-id='"+item.userId+"'>";
					html += "<ul class='music-cont-info-ls'>";
					html += "<li class='music-cont-info-ls-i1'>";
					html += "<i class='icomoon info-label label-music'>&#xe99f</i>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i2'>";
					html += "<img src='"+item.imageUrl+"'>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i3 cutWord personal-i3'>"+item.name+"</li>";
					html += "<li class='music-cont-info-ls-i4 personal-i4'>"+item.typeName+"</li>";
					html += "<li class='music-cont-info-ls-i5 personal-price'>￥"+item.tradeCost+"</li>";
					html += "<li class='music-cont-info-ls-i9'>"+dateStr+"</li>"
					html += "<li class='music-cont-info-ls-i7'>";
					html += "<a href='javascript:void(0);' class='complete-item'>完成订单</a>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i6'>";
					html += "<a href='javascript:void(0);'>";
					html += "<i class='icomoon info-label label-heart' data-favorite='1'>&#xe9da</i>";
					html += "</a>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i10' trade-status='"+item.tradeStatus+"'>已完成</li>"
					html += "</ul>";
					html += "</li>";
				});
				$('.music-cont-ls').append(html);
				//分页
				laypage({
				    cont: $('#page'), //容器。值支持id名、原生dom对象，jquery对象,
				    pages: data.list.pages, //总页数
				    skip: true, //是否开启跳页
				    skin: '#AF0000',
				    curr: curr || 1, //当前页
				    groups: 3 ,//连续显示分页数
				    jump: function(obj, first){ //触发分页后的回调
		                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
		                	var currentUserId = Number(mine.currentUserId);
							if(currentUserId == ''){
								currentUserId = -1;
							};
		                    mine.initDefaultList(currentUserId , obj.curr);
		                }
		            }
				});
				//判断状态
				$('.music-cont-info-ls-i10').each(function(index , item){
					var state = $(this).attr('trade-status');
					if(state == 0){
						$(this).html('未完成')
					}else{
						$(this).html('已完成')
					}
				});
				//整条可点 进入商品详情页
				$('.music-cont-ls-li').each(function(){
					$(this).off('click').on('click' , function(){
						$.cookie('itemId' , $(this).attr('item-id'));
						window.location = 'shop';
					});
				})
			},
			error : function(){

			}
		})
	},
		
	//弹窗关闭
	frameClose : function(){
		$("#frameClose").off('click').on('click' , function(){
			$(".frame-wrap").hide();
		}) 
	},
	//编辑个人信息
	editClick : function(){
		$(".personal-edit-text p input , .personal-edit-pass-content-text p input").each(function(){
			$(this).focus(function(){
				$(this).addClass('inputClick');
			}).blur(function(){
				$(this).removeClass('inputClick');
			});
		});
		$('.pernal-edit').off('click').on('click' , function(){
			$('.personal-edit-wrap').show();
		})
	},
	editPass : function(){
		//点击修密码按钮
		$("#editPass").off('click').on('click' , function(){
			//如果修改了密码 ， 设置属性为1
			$(".personal-edit-pass-content").slideDown().attr('type' , '1');
			$(".edit-pass-cancle").show();
			// $('.personal-edit-box').css('height' , $(this).height()+30+'px');
		});
		//点击取消修改密码按钮
		$("#editPassCancle").off('click').on('click' , function(){
			//如果不修改密码 ， 设置属性为0
			$(".personal-edit-pass-content").slideUp().attr('type' , '0');
			$(".edit-pass-cancle").hide();
		})
		//点击保存按钮
		$("#editBtnOk").off('click').on('click' , function(){
			$("#passValideMsg").html("").hide();
			// 首先判断有没有修改密码
			if($(".personal-edit-pass-content").attr('type') == 1){//修改了密码
				var pass = $("#oldPass").val();
				$.ajax({
					type : 'POST',
					data : JSON.stringify({'password':pass}),
					url : 'user/judgePass',
					dataType : 'json',
					contentType: "application/json; charset=utf-8",
					success : function(data){
						//如果不正确, 返回false
						if(!data.result){
							$("#passValideMsg").html("原密码输入有误").show();
							return false;
						}
						var newPass = $("#newPass").val();
						var reNewPass = $("#reNewPass").val();
						//如果两次密码不一致
						if(newPass!=reNewPass){
							$("#passValideMsg").html("两次输入密码不一致").show();
							return false;
						}

						update();

					},
					error : function(){

					}
				});
			}else{//没有修改密码
				update();
			}
			function update(){
				//所有信息验证完毕 
				var name = $('.personal-edit-text p').eq(0).find('input').val();
				var sex = $('.personal-edit-text p').eq(1).find('input').val();
				var tel = $('.personal-edit-text p').eq(2).find('input').val();
				var user = {'name':name , 'sex':sex , 'phone':tel};
				$.ajax({
					type : 'POST',
					data : JSON.stringify(user),
					url : 'user/update',
					dataType : 'json',
					contentType: "application/json; charset=utf-8",
					success : function(){
						$('#success').show();
						window.location = "personal";//刷新本页面
					},
					error : function(){

					}
				});
			}
		});
	},
	//关闭修改资料弹窗
	editClose : function(){
		$('#personalEditClose').off('click').on('click' , function(){
			$('.personal-edit-wrap').hide();
		});
		$('#editBtnCancle').off('click').on('click' , function(){
			$('.personal-edit-wrap').hide();
		});
	},
	//充值
	addMoney : function(){
		$('#addMoneyNumber').focus(function(){
			$(this).addClass('inputClick');
		}).blur(function(){
			$(this).removeClass('inputClick');
		});
		//取消按钮
		$('#addMoneyCancel').off('click').on('click' , function(){
			$('.add-money-wrap').hide();
		})
		//右上角x
		$('.add-money-close').off('click').on('click' , function(){
			$('.add-money-wrap').hide();
		})
		$("#addMoney").off('click').on('click' , function(){
			$('.add-money-wrap').show();
			$('.add-money-text').find('p').html($('#money').html())
		});
		$('#addMoneyOk').off('click').on('click' , function(){
			var num = $('#addMoneyNumber').val();
			if(num.trim() == ''){
				$('.add-money-wrap').hide();
				$('.add-money-success-wrap').show();
				setTimeout(function(){
					$('.add-money-success-wrap').hide();
				} , 2000);
			}else{
				$.ajax({
					type: "POST",
					url: "user/addMoney",
					data: JSON.stringify({'asset' : Number(num)}),
					dataType: "json",
					contentType: "application/json; charset=utf-8",
					success: function(data){
						$('.add-money-wrap').hide();
						$('.add-money-success-wrap').show();
						setTimeout(function(){
							$('.add-money-success-wrap').hide();
							window.location = 'personal';
						} , 2000);
						
					}, 
					error : function(){

					}
				});
			}
		})
	},
	autoClose :function(className){
		var dom = $("."+className+"");
		setTimeout("hide()", 2000);
		function hide(){
			dom.hide();
		}
	},
	compTrade : function(){
		$('#compTrade').off('click').on('click' , function(){

		})
	},
	chanegNav :function(){
		$('.pernal-nav-ls li').each(function(index , item){
			$(this).removeClass('active');
			$(this).off('click').on('click' , function(){
				$(this).addClass("active");
				var html = $(this).html();
				if(html == "全部订单"){
					$('.music-cont-info-ls-i7').css('visibility' , 'hidden');
				}else if(html == "交易进行"){
					$('.music-cont-info-ls-i7').css('visibility' , 'visible');
				}else if(html == "交易进行"){
					$('.music-cont-info-ls-i7').css('visibility' , 'hidden');
				}else if(html == "收藏订单"){
					$('.music-cont-info-ls-i7').css('visibility' , 'hidden');
					//请求收藏数据
				}
			})
		});
	},
	addFllow : function(){
		$('#addFollw').off('click').on('click' , function(){
			var state = $(this).attr('state');
			var data = {'friendId' : Number(mine.currentUserId)};
			//取消关注
			if(state == 1){
				$.ajax({
					type: "POST",
					url: "user/cancleFocus",
					data: JSON.stringify(data),
					dataType: "json",
					contentType: "application/json; charset=utf-8",
					success: function(data){
						$('.pernal-add-follow').show().find('a').attr('state','0').html('加关注');
					}, 
					error : function(){

					}
				});
			}else if(state == 0){  //加关注
				$.ajax({
					type: "POST",
					url: "user/focus",
					data: JSON.stringify(data),
					dataType: "json",
					contentType: "application/json; charset=utf-8",
					success: function(data){
						$('.pernal-add-follow').show().find('a').attr('state','1').html('取消关注');
					}, 
					error : function(){

					}
				});
			}
		})
	},
	//发布
	initPublishList : function(data , curr){
		var curr = curr || 1 ;
		$.ajax({
			type : 'POST',
			url : 'user/add/list?page='+curr,
			data : JSON.stringify({ 'id':data}),
			dataType : 'json',
			async : false,
			contentType: "application/json; charset=utf-8",
			success : function(data){
				var objs = data.list.list;
				var html = '';
				$('.music-cont-ls').empty();
				$.each(objs , function(index , item){
					var date = new Date(item.pubTime);
					var dateStr = date.getFullYear()+'-'+date.getMonth()+'-'+date.getDate();
					html += "<li class='music-cont-ls-li' item-id ='"+item.id+"' onwer-id='"+item.userId+"'>";
					html += "<ul class='music-cont-info-ls'>";
					html += "<li class='music-cont-info-ls-i1'>";
					html += "<i class='icomoon info-label label-music'>&#xe99f</i>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i2'>";
					html += "<img src='"+item.imageUrl+"'>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i3 cutWord personal-i3'>"+item.name+"</li>";
					html += "<li class='music-cont-info-ls-i4 personal-i4'>"+item.typeName+"</li>";
					html += "<li class='music-cont-info-ls-i5 personal-price'>￥"+item.tradeCost+"</li>";
					html += "<li class='music-cont-info-ls-i9'>"+dateStr+"</li>"
					html += "<li class='music-cont-info-ls-i7'>";
					html += "<a href='javascript:void(0);' class='complete-item'>完成订单</a>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i6'>";
					html += "<a href='javascript:void(0);'>";
					html += "<i class='icomoon info-label label-heart' data-favorite='1'>&#xe9da</i>";
					html += "</a>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i10' trade-status='"+item.tradeStatus+"'>已完成</li>"
					html += "</ul>";
					html += "</li>";
				});
				$('.music-cont-ls').append(html);
				
				//分页
				laypage({
				    cont: $('#page'), //容器。值支持id名、原生dom对象，jquery对象,
				    pages: data.list.pages, //总页数
				    skip: true, //是否开启跳页
				    skin: '#AF0000',
				    curr: curr || 1, //当前页
				    groups: 3 ,//连续显示分页数
				    jump: function(obj, first){ //触发分页后的回调
		                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
		                	var currentUserId = Number(mine.currentUserId);
							if(currentUserId == ''){
								currentUserId = -1;
							};
		                    mine.initPublishList(currentUserId , obj.curr);
		                }
		            }
				});
				//判断状态
				$('.music-cont-info-ls-i10').each(function(index , item){
					var state = $(this).attr('trade-status');
					if(state == 0){
						$(this).html('未完成')
					}else{
						$(this).html('已完成')
					}
				});
				//整条可点 进入商品详情页
				$('.music-cont-ls-li').each(function(){
					$(this).off('click').on('click' , function(){
						$.cookie('itemId' , $(this).attr('item-id'));
						window.location = 'shop';
					});
				})
			},
			error : function(){

			}
		})
	},
	//借入
	initBrrowwList : function(data , curr){
		var curr = curr || 1 ;
		$.ajax({
			type : 'POST',
			url : 'user/trade?page='+curr,
			data : JSON.stringify({ 'id':data}),
			dataType : 'json',
			async : false,
			contentType: "application/json; charset=utf-8",
			success : function(data){
				var objs = data.list.list;
				var html = '';
				$('.music-cont-ls').empty();
				$.each(objs , function(index , item){
					var date = new Date(item.outTime);
					var dateStr = date.getFullYear()+'-'+date.getMonth()+'-'+date.getDate();
					html += "<li class='music-cont-ls-li' item-id ='"+item.id+"' onwer-id='"+item.userId+"' trade-id='"+item.tradeId+"'>";
					html += "<ul class='music-cont-info-ls'>";
					html += "<li class='music-cont-info-ls-i1'>";
					html += "<i class='icomoon info-label label-music'>&#xe99f</i>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i2'>";
					html += "<img src='"+item.imageUrl+"'>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i3 cutWord personal-i3'>"+item.name+"</li>";
					html += "<li class='music-cont-info-ls-i4 personal-i4'>"+item.typeName+"</li>";
					html += "<li class='music-cont-info-ls-i5 personal-price'>￥"+item.tradeCost+"</li>";
					html += "<li class='music-cont-info-ls-i9'>"+dateStr+"</li>"
					html += "<li class='music-cont-info-ls-i7'>";
					html += "<a href='javascript:void(0);' class='complete-item'>完成订单</a>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i6'>";
					html += "<a href='javascript:void(0);'>";
					html += "<i class='icomoon info-label label-heart' data-favorite='1'>&#xe9da</i>";
					html += "</a>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i10' trade-status='"+item.tradeStatus+"'>已完成</li>"
					html += "</ul>";
					html += "</li>";
				});
				$('.music-cont-ls').append(html);
				
				//分页
				laypage({
				    cont: $('#page'), //容器。值支持id名、原生dom对象，jquery对象,
				    pages: data.list.pages, //总页数
				    skip: true, //是否开启跳页
				    skin: '#AF0000',
				    curr: curr || 1, //当前页
				    groups: 3 ,//连续显示分页数
				    jump: function(obj, first){ //触发分页后的回调
		                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
		                	var currentUserId = Number(mine.currentUserId);
							if(currentUserId == ''){
								currentUserId = -1;
							};
		                    mine.initBrrowwList(currentUserId , obj.curr);
		                }
		            }
				});
				//判断状态
				$('.music-cont-info-ls-i10').each(function(index , item){
					var state = $(this).attr('trade-status');
					if(state == 0){
						$(this).html('未完成')
					}else{
						$(this).html('已完成')
					}
				});
				//整条可点 进入商品详情页
				$('.music-cont-ls-li').each(function(){
					$(this).off('click').on('click' , function(){
						$.cookie('itemId' , $(this).attr('item-id'));
						window.location = 'shop';
					});
				})
			},
			error : function(){

			}
		})
	},
	//借出
	initLendList : function(data , curr){
		var curr = curr || 1 ;
		$.ajax({
			type : 'POST',
			url : 'user/borrow/list?page='+curr,
			data : JSON.stringify({ 'id':data}),
			dataType : 'json',
			async : false,
			contentType: "application/json; charset=utf-8",
			success : function(data){
				var objs = data.list.list;
				var html = '';
				$('.music-cont-ls').empty();
				$.each(objs , function(index , item){
					var date = new Date(item.outTime);
					var dateStr = date.getFullYear()+'-'+date.getMonth()+'-'+date.getDate();
					html += "<li class='music-cont-ls-li' item-id ='"+item.id+"' onwer-id='"+item.userId+"'>";
					html += "<ul class='music-cont-info-ls'>";
					html += "<li class='music-cont-info-ls-i1'>";
					html += "<i class='icomoon info-label label-music'>&#xe99f</i>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i2'>";
					html += "<img src='"+item.imageUrl+"'>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i3 cutWord personal-i3'>"+item.name+"</li>";
					html += "<li class='music-cont-info-ls-i4 personal-i4'>"+item.typeName+"</li>";
					html += "<li class='music-cont-info-ls-i5 personal-price'>￥"+item.tradeCost+"</li>";
					html += "<li class='music-cont-info-ls-i9'>"+dateStr+"</li>"
					html += "<li class='music-cont-info-ls-i7'>";
					html += "<a href='javascript:void(0);' class='complete-item'>完成订单</a>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i6'>";
					html += "<a href='javascript:void(0);'>";
					html += "<i class='icomoon info-label label-heart' data-favorite='1'>&#xe9da</i>";
					html += "</a>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i10' trade-status='"+item.tradeStatus+"'>已完成</li>"
					html += "</ul>";
					html += "</li>";
				});
				$('.music-cont-ls').append(html);
				
				//分页
				laypage({
				    cont: $('#page'), //容器。值支持id名、原生dom对象，jquery对象,
				    pages: data.list.pages, //总页数
				    skip: true, //是否开启跳页
				    skin: '#AF0000',
				    curr: curr || 1, //当前页
				    groups: 3 ,//连续显示分页数
				    jump: function(obj, first){ //触发分页后的回调
		                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
		                	var currentUserId = Number(mine.currentUserId);
							if(currentUserId == ''){
								currentUserId = -1;
							};
		                    mine.initLendList(currentUserId , obj.curr);
		                }
		            }
				});
				//判断状态
				$('.music-cont-info-ls-i10').each(function(index , item){
					var state = $(this).attr('trade-status');
					if(state == 0){
						$(this).html('未完成')
					}else{
						$(this).html('已完成')
					}
				});
				//整条可点 进入商品详情页
				$('.music-cont-ls-li').each(function(){
					$(this).off('click').on('click' , function(){
						$.cookie('itemId' , $(this).attr('item-id'));
						window.location = 'shop';
					});
				})
			},
			error : function(){

			}
		})
	},
	//收藏
	initCollList : function(data , curr){
		var curr = curr || 1 ;
		$.ajax({
			type : 'POST',
			url : 'user/collect/list?page='+curr,
			data : JSON.stringify({ 'id':data}),
			dataType : 'json',
			async : false,
			contentType: "application/json; charset=utf-8",
			success : function(data){
				var objs = data.list.list;
				var html = '';
				$('.music-cont-ls').empty();
				$.each(objs , function(index , item){
					var date = new Date(item.collectTime);
					var dateStr = date.getFullYear()+'-'+date.getMonth()+'-'+date.getDate();
					html += "<li class='music-cont-ls-li' item-id ='"+item.id+"' onwer-id='"+item.userId+"'>";
					html += "<ul class='music-cont-info-ls'>";
					html += "<li class='music-cont-info-ls-i1'>";
					html += "<i class='icomoon info-label label-music'>&#xe99f</i>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i2'>";
					html += "<img src='"+item.imageUrl+"'>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i3 cutWord personal-i3'>"+item.name+"</li>";
					html += "<li class='music-cont-info-ls-i4 personal-i4'>"+item.typeName+"</li>";
					html += "<li class='music-cont-info-ls-i5 personal-price'>￥"+item.tradeCost+"</li>";
					html += "<li class='music-cont-info-ls-i9'>"+dateStr+"</li>"
					html += "<li class='music-cont-info-ls-i7'>";
					html += "<a href='javascript:void(0);' class='complete-item'>完成订单</a>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i6'>";
					html += "<a href='javascript:void(0);'>";
					html += "<i class='icomoon info-label label-heart' data-favorite='1'>&#xe9da</i>";
					html += "</a>";
					html += "</li>";
					html += "<li class='music-cont-info-ls-i10' trade-status='"+item.tradeStatus+"'>已完成</li>"
					html += "</ul>";
					html += "</li>";
				});
				$('.music-cont-ls').append(html);
				
				//分页
				laypage({
				    cont: $('#page'), //容器。值支持id名、原生dom对象，jquery对象,
				    pages: data.list.pages, //总页数
				    skip: true, //是否开启跳页
				    skin: '#AF0000',
				    curr: curr || 1, //当前页
				    groups: 3 ,//连续显示分页数
				    jump: function(obj, first){ //触发分页后的回调
		                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
		                	var currentUserId = Number(mine.currentUserId);
							if(currentUserId == ''){
								currentUserId = -1;
							};
		                    mine.initCollList(currentUserId , obj.curr);
		                }
		            }
				});
				//判断状态
				$('.music-cont-info-ls-i10').each(function(index , item){
					var state = $(this).attr('trade-status');
					if(state == 0){
						$(this).html('未完成')
					}else{
						$(this).html('已完成')
					}
				});
				//整条可点 进入商品详情页
				$('.music-cont-ls-li').each(function(){
					$(this).off('click').on('click' , function(){
						$.cookie('itemId' , $(this).attr('item-id'));
						window.location = 'shop';
					});
				})
			},
			error : function(){

			}
		})
	},

}

var ajaxUploadImg = new AjaxUpload($("#personalLogo"),{
	action: 'user/upload', //要提交的地址
	name: 'file',//参数名次
	// data: update.music,//和文件一起提交的其它参数
	autoSubmit: true,//选中文件后是否就提交
	responseType: false,//返回的相应格式，如果是text格式的，会在响应前后加上一个<pre></pre>标签
	hoverClass: 'hover',
	disabledClass: 'disabled',
	onChange: function(file, extension){//在选中了文件的时候触发
	},
	onSubmit: function(file, extension){//在提交的时候触发

	},
	onComplete: function(file, response){//上传结束的时候触发
		window.location = 'personal';
	}
});