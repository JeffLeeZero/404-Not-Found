var url = "http://localhost:8080/404-Not-Found/";

var vue = new Vue({
	el:'#person',
	data:{
		count:6,
		listLength:0,
		index:1,
		prepare:1,
		movieList:[],
		commentList:[],
		nickname:"",
		animation:[]
	},
	mounted:function(){
		this.nickname = window.localStorage.userName;
	},
	methods:{
		next:function(){
			this.prepare++;
			this.animation[this.prepare] = "enterFromRight";
			this.animation[this.index] = "leaveFromRight";
			setTimeout(() => {
				this.index++;
			}, 700);
		},
		last:function(){
			this.prepare--;
			this.animation[this.prepare] = "enterFromLeft";
			this.animation[this.index] = "leaveFromLeft";
			setTimeout(() => {
				this.index--;
			}, 700);
		}
	}
})

$.ajax({
	type:"POST",
	url:url + "PersonalPageServlet",
	dataType:"json",
	data:JSON.stringify({
		"reqId":window.localStorage.reqId
	}),
	success:function(res){
		vue.$data.movieList = res.resData.favMovie;
		vue.$data.commentList = res.resData.commentedMovie;
		vue.$data.listLength = res.resData.favMovie.length;
	},
	error:function(err){
		console.log("传到后台的参数是"+this.data);
		console.log("报错信息");
		console.log(err);
	}
});
