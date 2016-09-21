$(function(){
	search.initData();
	//加关注 取消关注
	search.fllow();
})
var search = {
	initData : function(){
		//取默认数据
		$.ajax({
			type : 'POST',
			url : '',
			dataType : 'json',
			contentType: "application/json; charset=utf-8",
			success : function(data){
				var objs = data;
				var html = "";
				$('.user-list').empty();
				$.each(objs , function(index , item){
					html += "<li userid='"+item.+"'>";
					html += "<img src='"+item.+"'>";
					html += "<span class='search-user-name'>"+item.+"</span>";
					html += "<p class='search-friend-num'>";
					html += "<i>"+item.+"</i>Fllowings ";
					html += "<i>"+item.+"</i>Fllowers";
					html += "</p>";
					html += "<a href='javascript:void(0);' class='search-btn' type='"+item.+"'>加关注</a>";
					html += "</li>";
				});
				$('.user-list').append(html);
				//整条可点
				search.clickAll();
			},
			error : function(){

			}
		});
	},
	//跳转到个人中心页
	clickAll : function(){
		$('.user-list li').each(function(index , item){
			$(this).off('click').on('click' , function(){
				var id = $(this).attr('userid');
				$.ajax({
					type : 'POST',
					data : JSON.stringify({'userid':id}),
					url : '',
					dataType : 'json',
					contentType: "application/json; charset=utf-8",
					success : function(data){
						window.location = '';
					}, 
					error : function(){

					} 
				});
			})
		})
	},
	fllow : function(){
		$('.search-btn').each(function(index , item){
			$(this).off('click').on('click' , function(){
				if($(this).attr('type') == 0){
					$(this).attr('type' , '1').html('取消关注');
				}else{
					$(this).attr('type' , '0').html('加关注');
				}
				//加关注或者取消关注
				$.ajax({
					type : 'POST',
					url : '',
					dataType : 'json',
					contentType: "application/json; charset=utf-8",
					success : function(data){
						// $(this).find('a').html('取消关注')
					}, 
					error : function(){

					} 
				});
			})
		})
	}
}
