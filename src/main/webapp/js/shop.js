$(function(){
	shop.init();
	//编写评论
	shop.write();
	//加入购物车
	shop.add();
	// common.warnLog();
	//租赁
	shop.rent();
	//点击卖家头像，进入卖家个人中心也、
	shop.goUserPersonal();
	//收藏
	shop.collectItem();
})
var shop = {
	msgAdd : '加入购物车成功，汪~',
	msgJudeg : '评论成功！',
	msgComiitFali : '评论失败!',
	itemId : '',
	init : function(){
		var itemId = $.cookie('itemId');
		shop.itemId = itemId;
		// $.cookie('itemId' , '');
		//请求宝贝信息
		$.ajax({
			type: "post",
			url: "view",
			data : JSON.stringify({'id':itemId}),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data){
				var item = data.goods;
				//商品大图
				$("#itemImg").attr('src' , item.imageUrl)
				//商品名称
				$('#itemName').html(item.name);
				//价格
				$('#itemMoney').html(item.tradeCost);
				//确认支付弹窗里的价格
				$("#configOkMoney").html(item.tradeCost);
				//原价
				$("#itemOldMoney").html(item.originalCost);
				//押金
				$("#itemDeposit").html(item.currentPrice);
				//确认支付弹窗里的押金
				$("#configDeporitMoney").html(item.currentPrice);
				//成色
				$("#itemCondition").html(item.lever);
				//联系电话
				$("#userTel").html(item.phone);
				//联系地址
				$("#userAddress").html(item.region);
				//卖家头像
				$("#userImg").attr('src' , item.userImage);
				//卖家昵称
				$("#userName").html(item.ownerName);
				//卖家id
				$('#userWrap').attr('data-userid' , item.userId).off('click').on('click' , function(){
					var id = $(this).attr('data-userid');
					$.cookie('currentUserId' , id);
					window.location= 'personal';
				});
				//交易次数
				$("#seeNumber").html(item.outCount);
				//宝贝详情
				$("#itemInfoText").html(item.detail);
			},
			error : function(){

			}
		});
		//请求组图
		$.ajax({
			type: "post",
			url: "goodsImage",
			data : JSON.stringify({'id':itemId}),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data){
				$("#itemImgs").empty();
				var obj = data.pictures;
				var html = "";
				$.each(obj , function(index , item){
					html += "<div class='shop-detail-img'>";
					html += "<img src='"+item.print+"'>";
					html += "</div>";
				});
				$("#itemImgs").append(html);
			},
			error : function(){

			}
		});
		//请求评论
		initJdgeInfo();
		function initJdgeInfo(curr){
			var curr = curr || 1;
			$.ajax({
				type: "post",
				url: "goodsEvaluate?page="+curr,
				data : JSON.stringify({'id':itemId}),
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				success: function(data){
					$('.shop-judge-ls').empty();
					var obj = data.pageInfo.list;
					var html = '';
					$.each(obj , function(index , item){
						html += "<li judge-user-id='"+item.owner+"'>";
						html += "<div class='judge-index'>"+index+1+"</div>";
						html += "<div class='judeg-text'>";
						html += "<p class='judge-text-start' data-lever='"+item.lever+"'>";
						html += "<i class='icomoon star1'>&#xe9d9</i><i class='icomoon star1'>&#xe9d9</i>";
						html += "<i class='icomoon star1'>&#xe9d9</i><i class='icomoon star1'>&#xe9d9</i><i class='icomoon star1'>&#xe9d9</i>";
						html += "</p>";
						html += "<p class='judeg-user-name' id='judgeUserName'>"+item.userName+"</p>";
						html += "<div style='clear: both'></div>";
						html += "<p class='judge-text-edit' id='judgeText'>"+item.content+"</p>";
						html += "</div>";
						html += "<div class='judeg-user-logo' id='judgeUserImg'>";
						html += "<img src='"+item.userImage+"'  class='shop-judge-user-img'>";
						html += "</div>";
						html += "</li>";
					});
					$('.shop-judge-ls').append(html);
					//分页
					laypage({
					    cont: $('#page'), //容器。值支持id名、原生dom对象，jquery对象,
					    pages: data.pageInfo.pages, //总页数
					    skip: true, //是否开启跳页
					    skin: '#AF0000',
					    curr: curr || 1, //当前页
					    groups: 3 ,//连续显示分页数
					    jump: function(obj, first){ //触发分页后的回调
			                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
			                    initJdgeInfo(obj.curr);
			                }
			            }
					});
					$('.judge-text-start').each(function(index , item){
						var lever = $(this).attr('data-lever');
						for(var i=0; i<lever; i++){
							$(this).find('i').eq(i).css('color' , 'red');
						}
					});
					$('.shop-judge-user-img').each(function(index , item){
						$(this).off('click').on('click' , function(){
							var id = $(this).parent().parent().attr('judge-user-id');
							$.cookie('currentUserId' , id);
							window.location = 'personal';
						})
					});

				},
				error : function(){

				}
			});
		};
		
		
		//请求是否收藏
		if($.cookie('user')){
			$.ajax({
				type: "post",
				url: "user/ifCollect",
				data : JSON.stringify({'id':itemId}),
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				success: function(data){
					if(data.result){
						$('#shopCollect').css('color' , 'red').attr('type' , '1');
					}
				},
				error : function(){

				}
			});
		}
		
		
	},
	write : function(){
		$('.judge-write-start a').each(function(index , item){
			$(this).off('click').on('click' , function(){
				if($(this).attr('type') == '0'){
					$(this).find('i').css('color' , 'red');
					$(this).attr('type' , '1');
				}else{
					$(this).find('i').css('color' , '#fff');
					$(this).attr('type' , '0');
				}
			})
		})
		$('#write').off('click').on('click' , function(){
			$('.judeg-write-wrap').show();
		});
		$(".judeg-cancle").off('click').on('click' , function(){
			$('.judeg-write-wrap').hide();
		});
		$(".judeg-ok").off('click').on('click' , function(){
			//红心数量
			var num = 0;
			$('.judge-write-start a').each(function(index , item){
				if($(this).attr('type') == '1'){
					num += 1;
					$(this).attr('type' , '0');
					$(this).find('i').css('color' , '#fff');
				}else{
					num -= 1;
					$(this).attr('type' , '1');
					$(this).find('i').css('color' , '#eee');
				}
				$(this).find('i').css('color' , '#eee');
			});
			//文本
			var text = $("#text").val();
			$("#text").val('');
			var data = {'goods':Number($.cookie('itemId')) , 'lever':num , 'content':text};
			//返回-1为未登陆，返回1为评论成功，0为评论失败
			$.ajax({
				type: "post",
				url: "user/evaluate",
				data : JSON.stringify(data),
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				success: function(data){
					//未登陆
					if(data.i == -1){
						//关闭
						$('.judeg-write-wrap').hide();
						//评论成功弹窗
						$('#success').html('请登录后在评论').show();
						setTimeout(function(){
							$('#success').hide();
						} , 2000);
						$('#goTop').show();
					}else if(data.i == 1){//评论成功
						//关闭
						$('.judeg-write-wrap').hide();
						//评论成功弹窗
						$('#success').html(shop.msgJudeg).show();
						setTimeout(function(){
							$('#success').hide();
						} , 2000);
						window.location = 'shop';
					}else if(data.i == 0){//评论失败
						//关闭
						$('.judeg-write-wrap').hide();
						//评论成功弹窗
						$('#success').html(shop.msgComiitFali).show();
						setTimeout(function(){
							$('#success').hide();
						} , 2000);
					}else if(data.i == -2){//评论失败
						//关闭
						$('.judeg-write-wrap').hide();
						//评论成功弹窗
						$('#success').html('您没有资格进行评论哦').show();
						setTimeout(function(){
							$('#success').hide();
						} , 2000);
					}
					
				},
				error : function(){

				}
			});
			
		});
	},


	add : function(){
		$('#add').off('click').on('click' , function(){
			$(this).css('color' , 'red');
			var $that = $(this);
			//弹窗 加入购物车成功
			$('#success').html(shop.msgAdd).show();
			setTimeout(function(){
				$that.css('color' , '#a36830');
				$('#success').hide();
			} , 2000);
			
		});
	},
	rent : function(){
		//点击一键租赁
		$('#buy').off('click').on('click' , function(){
			//如果未登录
			if(!$.cookie('user')){
				//提示登陆
				$('#goTop').fadeIn();
				return false;
			}else{
				//提示支付
				$(".judeg-pay-money").show();
			}
			
		})
		//点击确认付款
		$('.judeg-pay-ok').off('click').on('click' , function(){
			var id = shop.itemId; //商品id
			var currentPrice = $("#itemDeposit").html(); //押金
			var userId = $('#userWrap').attr('data-userid'); //卖家id
			var tradeCost = $("#configOkMoney").html();  //价格
			var data = { 'id':id , 'currentPrice':currentPrice , 'userId':userId ,'tradeCost':tradeCost};
			$.ajax({
				type: "post",
				url: "user/borrow",
				data : JSON.stringify(data),
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				success: function(data){
					$('.judeg-pay-money').hide();
					if(data.result){
						$('#success').html('支付成功').show();
						setTimeout(function(){
							$('#success').hide();
						} , 2000);
						window.location = 'personal';
					}else{
						$('#success').html('余额不足，支付失败，嘤嘤嘤~').show();
						setTimeout(function(){
							$('#success').hide();
						} , 2000);
					}
					
				},
				error : function(){
					
				}
			});
		})
	},
	goUserPersonal : function(){
		$('#userWrap').off('click').on('click' , function(){
			var id = $(this).attr('data-userid');
			$.cookie('currentUserId');
			window.location = 'personal';
		})
	},
	//收藏和取消收藏商品
	collectItem :function(){
		$('#shopCollect').off('click').on('click' , function(){
			if($(this).attr('type') ==1 ){
				$(this).css('color' , '#999').attr('type' , '0');
				$.ajax({
					type: "post",
					url: "user/cancleCollect",
					data : JSON.stringify({'goods':shop.itemId}),
					dataType: "json",
					contentType: "application/json; charset=utf-8",
					success: function(data){

					},
					error : function(){

					}
				});
			}else{
				if(!$.cookie('user')){
					window.location = 'login';
				}
				$(this).css('color' , 'red').attr('type' , '1');
				$.ajax({
					type: "post",
					url: "user/collect",
					data : JSON.stringify({'id':shop.itemId}),
					dataType: "json",
					contentType: "application/json; charset=utf-8",
					success: function(data){
					},
					error : function(){

					}
				});
			}
		})
	}
}