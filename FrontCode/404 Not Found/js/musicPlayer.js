var musics = [{
	music: "bgm/Mystery of Love.mp3",
	name: "Mystery of Love"
}, {
	music: "bgm/卡农.mp3",
	name: "卡农"
}, {
	music: "bgm/Sundaland of mind.mp3",
	name: "Sundaland of mind"
}]

var index = 1;
var bgm = document.getElementsByTagName('audio')[0];
var source = document.getElementsByTagName('source')[0];
var musicControl = false;
var stop = false;
var box = document.getElementsByClassName('box')[0]
var bar = document.getElementsByClassName('bar')[0]
var all = document.getElementsByClassName('musicPlayer')[0]
var cha;
source.src = musics[index].music;
document.getElementsByClassName("musicName")[0].innerHTML = musics[index].name;
var musicPlay = function() {
	document.removeEventListener('click', musicPlay);
	if(!bgm.paused)
	{
		return;
	}
	bgm.play();
	nextMusic();
	console.log(bgm.played);
}

console.log(bgm.paused);
if(bgm.paused&&bgm.autoplay) {
	document.addEventListener('click', musicPlay);
	console.log(bgm.paused);
}
if(!bgm.autoplay) {
	pause();
}
var musicController = function() {
	if(musicControl == true) {
		musicControl = false;
		all.style.display = "none";
		console.log(all);
	} else {
		musicControl = true;
		all.style.display = "block";
		cha = bar.offsetWidth - box.offsetWidth;
		box.style.left = cha * bgm.volume + 'px';
		console.log(all);
		console.log(cha);
	}
}

function pause() {
	var pause = document.getElementsByClassName("pauseMusic")[0];
	pause.style.display = "none";
	var play = document.getElementsByClassName("play")[0];
	play.style.display = "block";
	bgm.pause();
	var circle = document.getElementsByClassName("disc")[0];
	circle.style.WebkitAnimationPlayState = "paused";
	circle.style.animationPlayState = "paused";
	stop = true;
}

function play() {
	var pause = document.getElementsByClassName("pauseMusic")[0];
	pause.style.display = "block";
	var play = document.getElementsByClassName("play")[0];
	play.style.display = "none";
	bgm.play();
	console.log("play");
	console.log(bgm.played);
	var circle = document.getElementsByClassName("disc")[0];
	circle.style.WebkitAnimationPlayState = "running";
	circle.style.animationPlayState = "running";
	stop = false;
}

var nextMusic = function() {
	if(index < musics.length - 1) {
		index++;
	} else {
		index = 0;
	}
	document.getElementsByClassName("musicName")[0].innerHTML = musics[index].name;
	bgm.src = musics[index].music;
	if(bgm.paused){
		play();
	}
}

var beforeMusic = function() {
	console.log(index);
	if(index > 0) {
		index--;
	} else {
		index = musics.length - 1;
	}
	document.getElementsByClassName("musicName")[0].innerHTML = musics[index].name;
	bgm.src = musics[index].music;
	if(bgm.paused){
		play();
	}
}

box.onmousedown = function(ev) {
	let boxL = box.offsetLeft
	let e = ev || window.event //兼容性
	let mouseX = e.clientX //鼠标按下的位置
	window.onmousemove = function(ev) {
		let e = ev || window.event
		let moveL = e.clientX - mouseX //鼠标移动的距离
		let newL = boxL + moveL //left值

		// 判断最大值和最小值

		if(newL < 0) {

			newL = 0

		}

		if(newL >= cha) {

			newL = cha

		}

		// 改变left值

		box.style.left = newL + 'px'

		// 计算比例

		var bili = newL / cha;

		bgm.volume = bili;

		return false //取消默认事件

	}

	window.onmouseup = function() {

		window.onmousemove = false //解绑移动事件

		return false

	}

	return false

};

// 点击音量条

bar.onclick = function(ev) {

	console.log(cha);

	let left = ev.clientX - all.offsetLeft - box.offsetWidth / 2 - 50

	if(left < 0) {

		left = 0

	}

	if(left >= cha) {

		left = cha

	}

	box.style.left = left + 'px'

	var bili = left / cha;

	bgm.volume = bili;

	return false

}
