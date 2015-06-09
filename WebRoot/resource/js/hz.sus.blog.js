$(function(){
    loadAsideTage();
    loadFooterTage();
    loadNewstBlog();
});

/**加载右侧标签**/
function loadAsideTage() {
	var app = navigator.appName;
	$.get("/blog/load/atag", {
		num : 20
	}, function(data) {
			$(data).appendTo("#aside-tags");
	});
}

/**加载底部标签**/
function loadFooterTage() {
	var app = navigator.appName;
	$.get("/blog/load/ftag", {
		num : 15
	}, function(data) {
		$(data).appendTo("#footer-tags");
	});
}

/**加载最新的博客**/
function loadNewstBlog() {
	var app = navigator.appName;
	$.get("/blog/load/nblog", {
		num : 2
	}, function(data) {
		$(data).appendTo("#newestBlogs");
	});
}

