$(function(){
	admin.initAdminUserData();
	// admin.page();
	//整条可点 进入商品详情页
	// admin.adminShopDetail();
	admin.clickSetInfo();
	admin.quite();
})
var admin = {
	initAdminUserData : function(curr){
		var curr = curr || 1;
		$.ajax({
			type: "post",
			url: "admin/user?page="+curr,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data){
				var objs = data.pageInfo.list;
				$('#admin').empty();
				var html ="";
				html += "<dt class='admin-ls-dt'>";
				html += "<ul class='admin-ls-dt-ls'>";
				html += "<li class='admin-title-number'>序号</li>";
				html += "<li class='admin-title-img'>logo</li>";
				html += "<li class='admin-title-song'>用户名称</li>";
				html += "<li class='admin-title-singer'>联系电话</li>";
				html += "<li class='admin-title-download'>账户余额</li>";
				html += "<li class='admin-title-play cutWord'>邮箱</li>";
				html += "<li class='admin-title-collect'>商品发布量</li>";
				html += "<li class='admin-title-buy'>好友量</li>";
				html += "<li class='admin-title-sou'>粉丝量</li>";
				html += "<li class='admin-title-options'>操作</li>";
				html += "</ul>";
				html += "</dt>";
				$.each(objs , function(index , item){
					html += "<dd class='admin-ls-dd' user-id='"+item.id+"'>";
					html += "<ul class='admin-ls-dd-ls'>";
					html += "<li class='admin-number'>"+index+"</li>";
					html += "<li class='admin-img'><img src='"+item.portrait+"'></li>";
					html += "<li class='admin-song'>"+item.name+"</li>";
					html += "<li class='admin-singer'>"+item.phone+"</li>";
					html += "<li class='admin-download'>"+item.asset+"</li>";
					html += "<li class='admin-play'>"+item.email+"</li>";
					html += "<li class='admin-collect'>"+item.goodsCount+"</li>";
					html += "<li class='admin-buy'>"+item.friendCount+"</li>";
					html += "<li class='admin-sou'>"+item.fansCount+"</li>";
					html += "<li class='admin-options'>";
					// html += "<a href='javascript:void(0);' class='admin-options-update'>修改</a>";
					html += "<a href='javascript:void(0);' class='admin-options-delete'>删除</a>";
					html += "</li></ul></dd>"		
				})
				$('#admin').append(html);

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
		                    admin.initAdminUserData(obj.curr);
		                }
		            }
				});

				//删除用户
				$('.admin-options-delete').each(function(index , item){
					$(this).off('click').on('click' , function(e){
						var id = $(this).closest('.admin-ls-dd').attr('user-id');
						e.stopPropagation();
						$.ajax({
							type: "post",
							url: "admin/user/delete",
							data : JSON.stringify({'id':id}),
							dataType: "json",
							contentType: "application/json; charset=utf-8",
							success: function(data){
								window.location = 'adminUser';
							},
							error : function(){

							}
						});
					})
				});


			},
			error : function(){

			}
		});

	},
	adminShopDetail : function(){
		$(".admin-shop-detail").each(function(index , item){
			$(this).off('click').on('click' , function(e){
				alert('进入商品详情页')
				e.stopPropagation();
			})
		})
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
		})

	},
}