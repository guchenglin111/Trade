$(function(){
	index.initBanner();
	index.initNav();
	//请求的是最新发布数据
	index.initHot();

	// index.banner();
	//请求的是搜索次数
	index.initIike();
	//最多收藏
	index.initNew();
	//最多交易
	index.morePlay();
	//资金周转
	index.moreDownload();
})
var index = {
	initBanner : function(){
		$('.banner-box-ls li').each(function(index , item){
			$(this).off('click').on('click' , function(){
				var itemId = $(this).attr('item');
				$.cookie('itemId',itemId);
				window.location = 'shop';
			})
		});
	},
	initNav : function(){
		$.ajax({
			type: "GET",
			url: "typelist",
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data){
				var obj = data.goodType;
				$('.index-hot-nav').empty();   
				var html = '';
				html += "<dt class='index-hot-dl'><h1 class='index-h1'>热门推荐</h1></dt>"
				$.each(obj , function(index , item){
					html += "<dd><a href='javascript:void(0);' type='"+item.id+"'>"+item.name+"</a></dd>";
				})
				$(".index-hot-nav").append(html);

				//默认第一个为选中状态
				$(".index-hot-nav dd").eq(0).addClass('active');
				//导航点击事件
				$('.index-hot-nav dd').each(function(i , item){
					$(this).off('click').on('click' , function(){
						var type = $(this).find('a').attr('type');
						index.initHot(type);
						$('.index-hot-nav dd').each(function(index , item){
							$(this).removeClass('active');
						})
						$(this).addClass('active');
					})
				})
			}
		});
		
	},
	initHot : function(type){
		var type = type || 1;
		$.ajax({
			type: "GET",
			url: "list",
			data: JSON.stringify({'type':type}),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data){
				$(".index-hot-con").empty();
				var obj = data.pageInfo.list;
				console.log(obj);
				var html = '';
				$.each(obj , function(index , item){
					html += "<li itemId='"+item.id+"' userId='"+item.userId+"' typeId='"+item.typeId+"'><a href='javascript:void(0);'>";
					html += "<img src='"+item.imageUrl+"' ></a>";
					html += "<p class='index-hot-con-mc'>";
					html += "<span>"+item.name+"</span>";
					html += "<span> - </span>";
					html += "<span>"+ item.typeName+"</span>";
					html += "</p></li>";
				})
				$(".index-hot-con").append(html);
				index.hotHover();
				$('.index-hot-con li').each(function(index , item){
					$(this).off('click').on('click' , function(){
						var itemId = $(this).attr('itemId');
						$.cookie('itemId',itemId);
						window.location = 'shop';
					})
				});
			}
		});

		
	},
	initIike : function(){
		$.ajax({
			type: "GET",
			url: "list?sort=search_count",
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data){
				$('.index-like-con').empty();
				var obj = data.pageInfo.list;
				var html = "";
				$.each(obj , function(index , item){
					html += "<li itemId='"+item.id+"' userId='"+item.userId+"' typeId='"+item.typeId+"'><a href='javascript:void(0);'>";
					html += "<img src='"+item.imageUrl+"'>";
					html += "</a></li>";
				});
				$('.index-like-con').append(html);
				index.hotHover();
				$('.index-like-con li').each(function(index , item){
					$(this).off('click').on('click' , function(){
						var itemId = $(this).attr('itemId');
						$.cookie('itemId',itemId);
						window.location = 'shop';
					})
				})
			}
		});

		
	},
	//最多收藏
	initNew : function(){
		$.ajax({
			type: "GET",
			url: "list?collect_count",
			//data: data,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data){
				$('.index-new-con').empty();
				var obj = data.pageInfo.list;
				var html = "";
				$.each(obj , function(index , item){
					html += "<li itemId='"+item.id+"' userId='"+item.userId+"' typeId='"+item.typeId+"'><a href='javascript:void(0);'>";
					html += "<img src='"+item.imageUrl+"'>";
					html += "</a></li>";
				});
				$('.index-new-con').append(html);
				index.hotHover();
				$('.index-new-con li').each(function(index , item){
					$(this).off('click').on('click' , function(){
						var itemId = $(this).attr('itemId');
						$.cookie('itemId',itemId);
						window.location = 'shop';
					})
				})
			}
		});

	},
	hotHover : function(){
		$('.index-hot-con li').each(function(index , item){
			$(this).off('mouseover').on('mouseover' , function(){
				$(this).find(".index-hot-con-mc").show();
			});
		});
		$('.index-hot-con li').each(function(index , item){
			$(this).off('mouseout').on('mouseout' , function(){
				$(this).find(".index-hot-con-mc").hide();
			});
		});
	},
	//最多交易
	morePlay : function(){
		$.ajax({
			type: "GET",
			url: "list?sort=out_count",
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data){
				var obj = data.pageInfo.list;
				var html = "";
				$('.index-more-play-con').empty();
				$.each(obj , function(index , item){
					html += "<li itemId='"+item.id+"' userId='"+item.userId+"' typeId='"+item.typeId+"'>";
					html += "<a href='javascript:void(0);'>";
					html += "<span class='index-more-play-song' >"+item.name+"</span>";
					html += "-";
					html += "<span class='index-more-play-singer'>"+item.typeName+"</span>";
					html += "</a></li>";
					if(index == 9){
						return false;
					}
				});
				$('.index-more-play-con').append(html);

				$(".index-more-play-con li").each(function(){
					$(this).off('click').on('click' , function(){
						var itemId = $(this).attr('itemId');
						$.cookie('itemId',itemId);
						window.location = 'shop';
					})
				})
			}
		});
		
	},
	//资金周转
	moreDownload : function(){
		var data = {'type':6};
		$.ajax({
			type: "post",
			url: "money",
			data : JSON.stringify(data),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data){
				var obj = data.pageInfo.list;
				var html = "";
				$('#index-more-download').empty();
				$.each(obj , function(index , item){
					html += "<li itemId='"+item.id+"' userId='"+item.userId+"' typeId='"+item.typeId+"'>";
					html += "<a href='javascript:void(0);'>";
					html += "<span class='index-more-download-song' >"+item.name+"</span>";
					html += "-";
					html += "<span class='index-more-download-singer'>"+item.typeName+"</span>";
					html += "</a></li>";
					if(index == 9){
						return false;
					}
				});
				$('#index-more-download').append(html);

				$("#index-more-download li").each(function(){
					$(this).off('click').on('click' , function(){
						var itemId = $(this).attr('itemId');
						$.cookie('itemId',itemId);
						window.location = 'shop';
					})
				})
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest.readyState);
				console.log(textStatus);
				console.log(errorThrown);
			}
		});
		
	}/*,
	//类型导航栏点击
	clickType : function(type){
		var type = type || 1;
		$.ajax({
			type: "GET",
			url: "search",
			data: JSON.stringify({'type':type}),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data){
				$(".index-hot-con").empty();
				var obj = data.pageInfo.list;
				console.log(obj);
				var html = '';
				$.each(obj , function(index , item){
					html += "<li itemId='"+item.id+"' userId='"+item.userId+"' typeId='"+item.typeId+"'><a href='javascript:void(0);'>";
					html += "<img src='"+item.imageUrl+"' ></a>";
					html += "<p class='index-hot-con-mc'>";
					html += "<span>"+item.name+"</span>";
					html += "<span> - </span>";
					html += "<span>"+ item.typeName+"</span>";
					html += "</p></li>";
				})
				$(".index-hot-con").append(html);

				$('.index-hot-con li').each(function(index , item){
					$(this).off('click').on('click' , function(){
						var itemId = $(this).attr('itemId');
						$.cookie('itemId',itemId);
						window.location = 'shop';
					})
				});
			}
		});
	}*/
}