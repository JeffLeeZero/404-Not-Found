var url = "http://localhost:8080/404-Not-Found/";
layui.use('layer', function(){
	var layer = layui.layer;
	layer.msg("请输入用户名", {icon: 0,time: 1500,offset:'-4050px'}); 
}); 

var vue = new Vue({
	el: '#myIndex',
	data: {
		loginning: false,
		menuClose:true,
		menuAnimation:"",
		carouselStyle: "",
		logoStyle:"logo-index",
		userName:"",
		password:"",
		autoplay:true,
		withPhone:false,
		newAccount:false,
		time:0,
		vercode:"",
		getVercodeStyle:"layui-btn",
		userPhone:"",
	},
	computed:{
		cutDown:{
			get:function(){
				if(this.time==0)
				{
					return "";
				}else if(this.time>0){
					return "("+this.time+")";
				}
			}
		}
	},
	methods: {
		startButtonClick: function() {
			this.menuAnimation = "";
			this.loginning = true;
			this.carouselStyle = "vague";
			this.logoStyle = "logo-login";
			this.autoplay = false;
			
		},
		openMenu:function(){
			this.menuClose = false;
			this.carouselStyle = "vague";
			this.menuAnimation = "left-enter"; 
		},
		closeMenu:function(){
			this.menuClose = true;
			this.carouselStyle = "";
			this.menuAnimation = "right-leave"
		},
		loginWithPhone:function(){
			this.withPhone = true;
		},
		loginWithAccount:function(){
			this.withPhone = false;
		},
		loginWithVercode:function(){
			this.newAccout = true;
			if(this.userPhone=="")
			{
				layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.msg("请输入手机号", {icon: 0,time: 1500}); 
				}); 
				
				return;
			}
			else if(this.vercode=="")
			{
				layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.msg("请输入验证码", {icon: 0,time: 1500}); 
				}); 
				return;
			}
			var that = this;
		},
		getVercode:function(node){
			console.log("click");
			node.disabled = true;
			this.time = 60;
			this.getVercodeStyle = "layui-btn-disabled";
			var that = this;
			var i = setInterval(()=>{
				that.time--;
				console.log(that.time);
				if(that.time==0)
				{
					clearInterval(i);
					that.getVercodeStyle = "layui-btn";
					node.disabled = false;
				}
			},1000)
		},
		login:function(){
			if(this.userName=="")
			{
				layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.msg("请输入用户名", {icon: 0,time: 1500}); 
				}); 
				
				return;
			}
			else if(this.password=="")
			{
				layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.msg("请输入密码", {icon: 0,time: 1500}); 
				}); 
				return;
			}
			var that = this;
			$.ajax({
				url:url+"LoginServlet",
				data:JSON.stringify({
					"reqId": "",
	                "reqParam": {
	                  "nickname": this.userName,
	                  "password": this.password
	                }
				}),
				type:"POST",
				dataType:"json",
				success:function(res){
					if(res.isSuccess)
					{
						window.localStorage.reqId = res.reqId;
						this.userName = res.resData.nickname;
						window.localStorage.userName = res.resData.nickname;
						window.location.href = "recommend.html";
						
					}
					else 
					{
						layui.use('layer', function(){
						  var layer = layui.layer;
						  layer.msg(res.message, {icon: 2,time: 1500, anim: 6});
						}); 
						console.log(res);
						 
					}
				},
				error:function(res){
					console.log(res);
					layui.use('layer', function(){
						var layer = layui.layer;
						layer.msg("网络异常，请重试", {icon: 2,time: 1500, anim: 6});
					}); 
				}
			})
		},
		register:function(){
			if(this.userName=="")
			{
				layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.msg("请输入用户名", {icon: 0,time: 1500}); 
				}); 
				console.log(userName);
				return;
			}
			else if(this.password=="")
			{
				layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.msg("请输入密码", {icon: 0,time: 1500}); 
				}); 
				return;
			}
			$.ajax({
				url:url+"SignUpServlet",
				data:JSON.stringify({
					"reqId": "",
	                "reqParam": {
	                  "nickname": this.userName,
	                  "password": this.password
	                }
				}),
				type:"POST",
				dataType:"json",
				success:function(res){
					if(res.isSuccess)
					{
						layui.use('layer', function(){
						  var layer = layui.layer;
						  layer.msg("注册成功", {icon: 1,time: 1500});
						}); 
					}
					else 
					{
						layui.use('layer', function(){
						  var layer = layui.layer;
						  layer.msg(res.message, {icon: 2,time: 1500, anim: 6});
						}); 
						console.log(res);
					}
				},
				error:function(res){
					layui.use('layer', function(){
						var layer = layui.layer;
						layer.msg("网络异常，请重试", {icon: 2,time: 1500, anim: 6});
					}); 
				}
			})
		},
		
	}
});
layui.use('carousel', function() {
	var carousel = layui.carousel;
	//建造实例
	carousel.render({
		elem: '#index-carousel',
		width: '102vw', //设置容器宽度
		height: '102vh',
		arrow: 'none', //不显示箭头
		anim: 'fade', //切换动画方式
		indicator: 'none',
		interval: 5000,
		autoplay:true
	});
});

