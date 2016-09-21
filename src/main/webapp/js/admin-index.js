$(function(){
	admin.initAdminData();
	admin.initAdminUserData();
	admin.page();
	//整条可点 进入商品详情页
	admin.adminShopDetail();
})
var admin = {
	initAdminData : function(){
		var data = adminData.info2;
		var html = "";
		$.each(data , function(index , item){
			html += "<dd class='admin-ls-dd admin-shop-detail' >";
			html += "<ul class='admin-ls-dd-ls'>";
			html += "<li class='admin-number'>"+item.number+"</li>";
			html += "<li class='admin-img'><img src='"+item.img+"'></li>";
			html += "<li class='admin-song'>"+item.song+"</li>";
			html += "<li class='admin-singer'>"+item.singer+"</li>";
			html += "<li class='admin-download'>"+item.download+"</li>";
			html += "<li class='admin-play'>"+item.play+"</li>";
			html += "<li class='admin-collect'>"+item.collect+"</li>";
			html += "<li class='admin-buy'>"+item.buy+"</li>";
			html += "<li class='admin-sou'><a href='javascript:void(0);'>"+item.sou+"</a></li>";
			html += "<li class='admin-options'>";
			// html += "<a href='javascript:void(0);' class='admin-options-update'>修改</a>";
			html += "<a href='javascript:void(0);' class='admin-options-delete'>删除</a>";
			html += "</li></ul></dd>"		
		})
		$('#admin').append(html);
		$('.admin-sou a').each(function(index , item){
			if($(this).html() == '已审核'){
				$(this).css({'color' : '#999'},{'cursor' : 'pointer'});
			}else{
				$(this).off('click').on('click' , function(e){
					$(this).html('已审核').css({'color' : '#999'},{'cursor' : 'pointer'});
					e.stopPropagation();
				})
			}
		})
	},
	initAdminUserData : function(){
		var data = adminData.info;
		var html = "";
		$.each(data , function(index , item){
			html += "<dd class='admin-ls-dd'>";
			html += "<ul class='admin-ls-dd-ls'>";
			html += "<li class='admin-number'>"+item.number+"</li>";
			html += "<li class='admin-img'><img src='"+item.img+"'></li>";
			html += "<li class='admin-song'>"+item.song+"</li>";
			html += "<li class='admin-singer'>"+item.singer+"</li>";
			html += "<li class='admin-download'>"+item.download+"</li>";
			html += "<li class='admin-play'>"+item.play+"</li>";
			html += "<li class='admin-collect'>"+item.collect+"</li>";
			html += "<li class='admin-buy'>"+item.buy+"</li>";
			html += "<li class='admin-sou'>"+item.sou+"</li>";
			html += "<li class='admin-options'>";
			// html += "<a href='javascript:void(0);' class='admin-options-update'>修改</a>";
			html += "<a href='javascript:void(0);' class='admin-options-delete'>删除</a>";
			html += "</li></ul></dd>"		
		})
		$('#adminUser').append(html);
		/*$('.admin-sou a').each(function(index , item){
			if($(this).html() == '已审核'){
				$(this).css({'color' : '#999'},{'cursor' : 'pointer'});
			}else{
				$(this).off('click').on('click' , function(e){
					$(this).html('已审核').css({'color' : '#999'},{'cursor' : 'pointer'});
					e.stopPropagation();
				})
			}
		})*/
	},
	page : function(){
		/*$.getJSON('test/demo1.json', {
	        page: 1 || 1 //向服务端传的参数，此处只是演示
	    }, function(res){
	        //显示分页
	        var res = res;
	        res = adminData.info;
	        laypage({
			    cont: $('#page'), //容器。值支持id名、原生dom对象，jquery对象,
			    pages: 100, //总页数
			    skip: true, //是否开启跳页
			    skin: '#AF0000',
			    groups: 3 ,//连续显示分页数
			    jump: function(obj, first){ //触发分页后的回调
                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                    demo(obj.curr);
                }
            }
			});
	    });*/
			laypage({
			    cont: $('#page'), //容器。值支持id名、原生dom对象，jquery对象,
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
	adminShopDetail : function(){
		$(".admin-shop-detail").each(function(index , item){
			$(this).off('click').on('click' , function(e){
				alert('进入商品详情页')
				e.stopPropagation();
			})
		})
	}
}