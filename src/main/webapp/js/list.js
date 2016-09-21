$(function(){
	//从头上search搜索进来
	list.getHeadSearch();
	//设置中间的那个大search的样式和提示的样式 点击input 提示消失
	list.searchFrameClick();
	// list.init();
	//搜索
	list.search();
	//点击导航栏
	list.clickNav();
})
var list = {
	//首先判断是从首页搜索进来的  还是点击导航栏进来的
	getHeadSearch : function(){
		var key = $.cookie('headSearch');
		if(key){
			list.searchList(key);
			$('.list-search').val(key);
			$('.list-tishi').hide();
			$.cookie('headSearch' , '');
		}else{
			list.init();
		}
	},

	init : function(curr){
		$('.list-none').hide();
		var curr = curr || 1;
		var html = '';
		$('.list-music-ls').empty();
		$.ajax({
			type: "post",
			url: "goods/goodsList?page="+curr,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data){
				$('.list-music-ls').empty();
				var objs = data.pageInfo.list;
				if(objs.length<=0){
					$('.list-none').show();
				}
				var html = list.describeHtml(objs);
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
		                    list.init(obj.curr);
		                }
		            }
				});
				$('.list-music-ls').append(html);
				list.clickOne();
			},
			error : function(){

			}
		});
	},
	search : function(){
		$('.list-search-label').off('click').on('click' , function(){
			var key = $(".list-search").val().trim();
			if(key){
				list.searchList(key);
			}
			
		});
		$('.list-search').keydown(function(e){
			if(e.keyCode == 13){
				var key = $('.list-search').val();
				if(!key){
					return false;
				}
				list.searchList(key);
			}
		});
	},

	searchList : function(key , curr){
		$('.list-none').hide();
		var curr = curr || 1;
		$.ajax({
			type: "post",
			url: "goods/searchList?search="+key+"&page="+curr,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data){
				$('.list-music-ls').empty();
				var objs = data.pageInfo.list;
				if(objs.length<=0){
					$('.list-none').show();
				}
				var html = list.describeHtml(objs);
				$('.list-music-ls').append(html);
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
		                	var key = $(".list-search").val().trim();
		                    list.searchList(key , obj.curr);
		                }
		            }
				});
				list.clickOne();
			},
			error : function(){

			}
		});
	},

	describeHtml : function(objs){
		var html = '';
		$.each(objs , function(index , item){
			html += "<li class='music-cont-ls-li' data-id='"+item.id+"'>";
			html += "<ul class='music-cont-info-ls'>";
			html += "<li class='music-cont-info-ls-i1'>";
			html += "<i class='icomoon info-label label-music'>&#xe99f</i>";
			html += "</li>";
			html += "<li class='music-cont-info-ls-i2'><img src='"+item.imageUrl+"'></li>";
			html += "<li class='music-cont-info-ls-i3 cutWord'>"+item.name+"</li>";
			html += "<li class='music-cont-info-ls-i4'>"+item.typeName+"</li>";
			html += "<li class='music-cont-info-ls-i5'>￥"+item.tradeCost+"</li>";
			html += "<li class='music-cont-info-ls-i7 region'>"+item.region+"</li>";
			html += "<li class='music-cont-info-ls-i6'>";
			html += "<a href='javascript:void(0);'>";
			html += "<i class='icomoon info-label label-heart' data-favorite='0'>&#xe9da</i>";
			html += "</a></li>";
			html += "</ul></li>"
		});
		return html;
	},


						
	searchFrameClick : function(){
		$(".list-search").off('focus').on('focus' , function(){
			$(".list-tishi").hide();
		}).off('blur').on('blur' , function(){
			if(!$(this).val().trim()){
				$(".list-tishi").show();
			}
		})
	},
	listPage : function(){
		laypage({
		    cont: $('#listPage'), //容器。值支持id名、原生dom对象，jquery对象,
		    pages: 100, //总页数
		    skip: true, //是否开启跳页
		    skin: '#AF0000',
		    groups: 3 ,//连续显示分页数
		    jump: function(obj, first){ //触发分页后的回调
                /*if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                    demo(obj.curr);
                }*/
        	}
		});
	},
	clickOne : function(){
		$('.list-music-ls li').each(function(index , item){
			$(this).off('click').on('click' , function(){
				var itemId = $(this).attr('data-id');
				$.cookie('itemId' , itemId);
				window.location = 'shop';
			})
		
		});
	},
	searchFrameClick : function(){
		$(".list-search").off('focus').on('focus' , function(){
			$(".list-tishi").hide();
		}).off('blur').on('blur' , function(){
			if(!$(this).val().trim()){
				$(".list-tishi").show();
			}
		})
	},
	searchLabelClick : function(){
		var key = $(".list-search").val();
		//搜索
	},
	clickNav : function(){
		$('#listNav li').each(function(){
			$(this).off('click').on('click' , function(){
				$('.list-none').hide();
				$('#listNav li').each(function(){
					$(this).removeClass('active');
				})
				$(this).addClass('active');
				var type = $(this).find('a').attr('type');
				funClic();
				function funClic(curr){
					var curr = curr || 1 ;
					$.ajax({
						type: "post",
						url: "goods/selectList?page="+curr,
						data : JSON.stringify({'type':type}),
						dataType: "json",
						contentType: "application/json; charset=utf-8",
						success: function(data){
							$('.list-music-ls').empty();
							var objs = data.pageInfo.list;
							if(objs.length<=0){
								('.list-none').show();
							}
							var html = list.describeHtml(objs);
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
					                    funClic(obj.curr);
					                }
					            }
							});
							$('.list-music-ls').append(html);
							list.clickOne();
						},
						error : function(){

						}
					});
				}
				
			})
		})
	}
}