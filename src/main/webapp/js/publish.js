$(function(){
	//选择类型的样式变化
	publish.typeSelected();
	// publish.getText();
	//数字验证
	publish.numberValide();
	//发布
	publish.publishShop();
	//取消发布
	publish.cancelPublish();
	//资金周转
	publish.asset();
})
var publish = {
	typeSelected : function(){
		$('.shop-type-ls li').each(function(index , item){
			$(this).off('click').on('click' , function(){
				$('.shop-type-ls li').each(function(index , item){
					$(this).removeClass('active');
				});
				$(this).addClass('active');
			})
		});
		$('.shop-judeg-ls li').each(function(index , item){
			$(this).off('click').on('click' , function(){
				$('.shop-judeg-ls li').each(function(index , item){
					$(this).removeClass('active');
				});
				$(this).addClass('active');
			})
		})
	},
	content : {},
	//获取文本信息
	getText : function(){
		var msg = '';
		var shopName = $('#shopName').val();
		if(!shopName.trim()){
			$('.publish-warn .warn1').css('visibility' , 'visible');
			return false;
		}

		var shopMoney = $('#shopMoney').val();
		var msg = publish.numberValide(shopMoney); 
		if(msg != '合法'){
			$('.publish-warn .warn2').html(msg).css('visibility' , 'visible');
			return false;
		}

		var shopOldMoney = $('#shopOldMoney').val();
		msg = publish.numberValide(shopOldMoney); 
		if(msg != '合法'){
			$('.publish-warn .warn3').html(msg).css('visibility' , 'visible');
			return false;
		}

		var shopDeposit = $('#shopDeposit').val();
		msg = publish.numberValide(shopDeposit); 
		if(msg != '合法'){
			$('.publish-warn .warn4').html(msg).css('visibility' , 'visible');
			return false;
		}

		var shopType = ''
		$(".shop-type-ls li").each(function(){
			if($(this).hasClass('active')){
				shopType = Number($(this).attr('type-id'));
			}
		});
		var shopJudeg = '';
		$(".shop-judeg-ls li").each(function(){
			if($(this).hasClass('active')){
				shopJudeg = $(this).find('a').html();
			}
		});

		var shopDetail = $('#shopDetail').val();
		if(!shopDetail.trim()){
			$('.publish-warn .warn7').css('visibility' , 'visible');
			return false;
		}

		/*var shopTel = $('#shopTel').val();
		msg = publish.numberValide(shopTel , 'tel'); 
		if(msg != '合法'){
			$('.publish-warn .warn8').html(msg).css('visibility' , 'visible');
			return false;
		}*/

		var shopAddress = $('#shopAddress').val();
		if(!shopAddress.trim()){
			$('.publish-warn .warn9').css('visibility' , 'visible');
			return false;
		}

		/*var logoPic = document.getElementById("shopLogo").files[0];
		if(!logoPic){
			$('.publish-warn .warn10').css('visibility' , 'visible');
			return false;
		}
		var logo = logoPic.name;
		var picFiles = document.getElementById("shopPics").files;
		if(picFiles.length == 0){
			$('.publish-warn .warn11').css('visibility' , 'visible');
			return false;
		}
		var pics = []
		$.each(picFiles , function(index ,item){
			pics.push(item.name);
		});*/
		publish.content.name = shopName;
		publish.content.tradeCost = shopMoney;
		publish.content.originalCost = shopOldMoney;
		publish.content.currentPrice = shopDeposit;
		publish.content.type = shopType;
		publish.content.lever = shopJudeg;
		publish.content.detail = shopDetail;
		// publish.content.shopTel = shopTel;
		publish.content.region = shopAddress;
	},
	numberValide : function(str , type){
		var reg = /\d/;
		var reg2 = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
		var msg = '合法'; //2代表非数字 3代表格式不对
		if(!reg.test(str)){
			msg = '输入的数字不合法';
			return msg;
		}
		if(type){
			if(!reg2.test(str)){
				msg = '格式不正确';
				return msg;
			}
		}
		return msg;
	},
	publishShop : function(){
		$("#publishOk").off('click').on('click' , function(){
			$('.publish-warn p').each(function(index ,item){
				$(this).css('visibility' , 'hidden');
			})
			publish.getText();
			var content = publish.content;
			console.log(content);/*publishSuccess*/
			//传送数据
			$.ajax({
				type: "post",
				url: "user/addGoods",
				data : JSON.stringify(publish.content),
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				success: function(obj){
					// alert('发布成功');
					$('#publishSuccess').html('发布成功，等待管理员审核，汪~').show();
					setTimeout(function(){
						$('#publishSuccess').hide();
						window.location = 'list';
					} , 2000);
					//回到上一页
					// window.location = '';
				},
				error : function(){
					console.log('error');
				}
			});
		})
	},
	cancelPublish : function(){
		$('#publishCancel').off('click').on('click' , function(){
			window.location(-1);
		})
	},
	//资金周转
	asset :function(){
		$('#assetPublish').off('click').on('click' , function(){
			$('.add-money-wrap').show();
		})
		$('#addMoneyOk').off('click').on('click' , function(){
			var name = Number($('#assetMoney').val());
			var tradeCost = Number($('#assetTradeMoney').val());
			var region = $('#assetAddress').val();
			$.ajax({
				type: "post",
				url: "user/addGoodsMoney",
				data : JSON.stringify({ 'name':name , "tradeCost":tradeCost , "region":region}),
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				success: function(obj){
					$('.add-money-wrap').hide();
					$('#publishSuccess').html('发布成功，等待管理员审核，汪~').show();
					setTimeout(function(){
						$('#publishSuccess').hide();
						window.location = 'list';
					} , 2000);
					//回到上一页
					
				},
				error : function(){
					console.log('error');
				}
			});
		});
		$('#addMoneyCancel , .close add-money-close').off('click').on('click' , function(){
			$('.add-money-wrap').hide();
		})
	}
}


var ajaxUploadLogo = new AjaxUpload($("#shopLogo"),{
	action: 'goods/upload', //要提交的地址
	name: 'file',//参数名次
	// data: update.music,//和文件一起提交的其它参数
	autoSubmit: true,//选中文件后是否就提交
	responseType: false,//返回的相应格式，如果是t ext格式的，会在响应前后加上一个<pre></pre>标签
	hoverClass: 'hover',
	disabledClass: 'disabled',
	onChange: function(file, extension){//在选中了文件的时候触发
	},
	onSubmit: function(file, extension){//在提交的时候触发

	},
	onComplete: function(file, response){//上传结束的时候触发
		alert('成功')
		return ;
	}
});
var ajaxUploadPics = new AjaxUpload($("#shopPics"),{
	action: 'goods/upload/group', //要提交的地址
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
		alert('成功')
		return ;
	}
});
