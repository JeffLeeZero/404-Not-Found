//import {userInfo, movieList} from "global.js"
var url = "http://localhost:8080/404-Not-Found/";
var vue = new Vue({
	el:'#recommend',
	data:{
		index:0,
		movies:[{movieId:"",movieScore:"6.3",movieName:"无",moviePoster:"",movieSlogan:"hhhhhhhhhhhhhhhh",movieBackground:"img/background1.jpg"},
		{movieId:"",movieScore:"6.3",movieName:"无",moviePoster:"",movieSlogan:"hhhhhhhhhhhhhhhh",movieBackground:"img/background2.jpg"}],
		touched:false,
		constellation:"",
		chosen:false,
		movieInfo:{
			movieId:["00"],
			index:0
		}
	},
	methods:{
		toPersonPage:function(){
			window.open("personal.html")
		},
		movieChoose:function(index){
			if(index==6)
			{
				this.touched = false;
				return;
			}
			this.index = index;
			this.touched = true;
		},
		enterDetail:function(index){
			this.movieInfo.index = index;
			window.localStorage.movieInfo = JSON.stringify(this.movieInfo);
			window.localStorage.index = this.movieInfo.index;
			window.open("detail.html");
		}
	}
});
function back(){
	vue.$data.chosen = false;
    var self=document.getElementsByClassName("starPage")[0];
    self.style.display="none";
    var behind= document.getElementsByClassName("firstPage")[0];
    behind.style.display="block";
}
function change(constell){
	vue.$data.constellation = constell;
	$.ajax({
		url:url+"RecommendServlet",
		data:JSON.stringify({
			"reqId":window.localStorage.reqId,
			"reqParam":{
				"constellation":constell
			}
		}),
		type:"POST",
		dataType:"json",
		success:function(res){
			vue.$data.movies = res.resData;
		    vue.$data.chosen = true;
		    var behind= document.getElementsByClassName("firstPage")[0];
		    behind.style.display="none";
			for(var i=0;i<6;i++)
			{
				vue.$data.movieInfo.movieId[i] = res.resData[i].movieId;
			}
		},
		error:function(err){
			//vue.$data.movies[0].movieSlogan = "hhhhhhhhhhhhhhhhhhhhgghhhhhhh";
		}
		
	});
	
	
    
}
