//import {userInfo, movieList} from "global.js"
var url = "http://localhost:8080/404-Not-Found/";

var setRank = function(id, rank, showText, canWrite) {
	layui.use('rate', function() {
		var rate = layui.rate;

		//渲染
		var ins1 = rate.render({
			elem: id,
			value: rank / 2,
			half: true,
			readonly: canWrite,
			text: showText,
			theme: '#FFFFFF',
			setText: function(value) {
				this.span.text(value * 2);
			} //绑定元素
		});
	});
}

var vue = new Vue({
	el: '#detail',
	data: {
		movieId: [],
		index: 0,
		show: false,
		movies: [],
		prepare: 0,
		animation: [],
		commentOpen: false,
		commentStyle: "",
		comments: [],
		myRank: "10",
		myComment: "",
		commentCount: 0
	},
	methods: {
		toPersonPage:function(){
			window.open("personal.html")
		},
		likeIt:function(index){
			var that = this;
			$.ajax({
				type:"POST",
				dataType:"json",
				url:url+"FavouriteServlet",
				data:JSON.stringify({
					"reqId":window.localStorage.reqId,
					"reqParam":{
						"movieId":this.movies[index].movieId,
						"isFavourite":true
					}
				}),
				success:function(res){
					if(res.isSuccess){
						that.movies[index].isFavourite = true;
					}
				},
				error:function(err){
					console.log("传到后台的参数是"+this.data);
					console.log("报错信息"+err);
				}
			})
		},
		unlikeIt:function(index){
			var that = this;
			$.ajax({
				type:"POST",
				dataType:"json",
				url:url+"FavouriteServlet",
				data:JSON.stringify({
					"reqId":window.localStorage.reqId,
					"reqParam":{
						"movieId":this.movies[index].movieId,
						"isFavourite":false
					}
				}),
				success:function(res){
					if(res.isSuccess){
						that.movies[index].isFavourite = false;
					}
				},
				error:function(err){
					console.log("传到后台的参数是"+this.data);
					console.log("报错信息"+err);
				}
			})
		},
		nextMovie: function() {
			this.prepare++;
			this.animation[this.prepare] = "enterFromDown";
			this.animation[this.index] = "leaveFromDown";
			setTimeout(() => {
				this.index++;
				setRank('#level' + this.index, parseInt(this.movies[this.index].movieScore), false, true);
			}, 700);

		},
		lastMovie: function() {
			this.prepare--;
			this.animation[this.prepare] = "enterFromUp";
			this.animation[this.index] = "leaveFromUp";
			setTimeout(() => {
				this.index--;
				setRank('#level' + this.index, parseInt(this.movies[this.index].movieScore), false, true)
			}, 700);
		},
		openComment: function() {

			this.getComments(0);
			this.commentStyle = "comment-enter";
			this.commentOpen = true;
			var that = this;
			layui.use('rate', function() {
					var rate = layui.rate;

					//渲染

					var ins1 = rate.render({
						elem: '#myrank',
						value: 5,
						half: true,
						readonly: false,
						text: false,
						theme: '#FFFFFF',
						choose: function(value) {
							this.myRank = (value * 2).toString();
							console.log(typeof(this.myRank));
						}
					});
				});
			setTimeout(() => {
				
				layui.use('laypage', function() {
						var laypage = layui.laypage;
						//执行一个laypage实例
						laypage.render({
							elem: 'page', //注意，这里的 test1 是 ID，不用加 # 号
							count: that.commentCount,
							theme: "#a1a2a3",
							jump: function(obj, first) {
								if(!first) {
									that.getComments(obj.curr-1);
								}
							} //数据总数，从服务端得到
						});
					});
			}, 1000);

		},
		sendComment: function() {
			$.ajax({
				type: "POST",
				url: url + "SetCommentServlet",
				dataType: "json",
				data: JSON.stringify({
					"reqId": window.localStorage.reqId,
					"reqParam": {
						"movieId": this.movieId[this.index],
						"movieScore": this.myRank,
						"movieComment": this.myComment
					}
				}),
				success: function(res) {
					console.log("发表评论成功");
					console.log(res);
				},
				error: function(err) {
					console.log("发表评论失败");
					console.log(this.data);
					console.log(err);
				}
			});
		},
		getRanks: function(data) {
			console.log("开始渲染时的评论内容" + this.comments);
			console.log("开始渲染时的评论数目" + this.comments.length);
			var that = this;
			layui.use('rate', function() {
				var rate = layui.rate;

				//渲染
				for(var i = 0; i < that.comments.length; i++) {
					var ins = rate.render({
						elem: '#rank' + i,
						value: that.comments[i].movieScore / 2,
						half: true,
						readonly: true,
						text: false,
						theme: '#FFFFFF',
					});
				}
			});
		},
		closeComment: function() {
			this.commentStyle = "comment-leave";
			setTimeout(() => {
				this.commentOpen = false;
			}, 300)
		},
		getComments: function(curPage) {
			var that = this;
			$.ajax({
				type: "POST",
				url: url + "GetCommentServlet",
				dataType: "json",
				data: JSON.stringify({
					"reqId": window.localStorage.reqId,
					"reqParam": {
						"movieId": this.movieId[this.index]
					},
					"reqPageInfo": {
						"curPage": curPage.toString(),
						"pageSize": "10"
					}
				}),
				success: function(res) {
					console.log("获取评论");
					console.log(this.data);
					console.log(res);
					that.commentCount = parseInt(res.message);
					that.comments = res.resData;

					
					//that.getRanks(res.resData);
				},
				error: function(err) {
					console.log(this.data);
					console.log(err);
				}
			});
		}
	},
	mounted: function() {
		this.movieId = JSON.parse(window.localStorage.movieInfo).movieId;
		this.index = window.localStorage.index;
		this.prepare = this.index;
	}
})

var focusing = function() {
	var text = document.getElementById("myComment");
	text.className = "text-focusing";
	var button = document.getElementById("submit-button");
	button.classList.add("buttonShow");
	button.style.display = "block";
}

var bluring = function() {
	var text = document.getElementById("myComment");
	text.className = "text-leaving";
}

var getMovie = function(id) {
	$.ajax({
		type: "POST",
		url: url + "DetailServlet",
		dataType: "json",
		data: JSON.stringify({
			"reqId": window.localStorage.reqId,
			"reqParam": {
				"movieId": vue.$data.movieId[id]
			}
		}),
		success: function(res) {
			vue.$data.movies[id] = res.resData;
		},
		error: function(err) {
			console.log(err);
		}
	});
}

console.log(window.localStorage);
$.ajax({
	type: "POST",
	url: url + "DetailServlet",
	dataType: "json",
	data: JSON.stringify({
		"reqId": window.localStorage.reqId,
		"reqParam": {
			"movieId": vue.$data.movieId[vue.$data.index]
		}
	}),
	success: function(res) {
		vue.$data.movies[vue.$data.index] = res.resData;
		setRank('#level' + vue.$data.index, parseInt(res.resData.movieScore), false, true);
		vue.$data.show = true;
	},
	error: function(err) {
		console.log(err);
	}
});

for(var i = 0; i < 6; i++) {
	if(i == vue.$data.index) {
		continue;
	}
	getMovie(i);
}