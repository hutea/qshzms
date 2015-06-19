$("#share_new").click(function(){
	 $("#myModal").modal({
	        backdrop: true,//点击空白处模态框消失
	 });
})
$("#signin").click(function(){
	$("#myModal-signin").modal({
		backdrop: false,//点击空白处模态框不消失
	});
})

/**模态框放大**/
function enlarge(){
	$("#myModal-dialog").removeClass("modal-md");
	$("#myModal-dialog").addClass("modal-lg");
}

$("#myTab a:first").tab("show");//页面加载显示最后一个标签页


$("#save").click(function(){
	/** 1=下载；2=文章；3=音乐；4=电影；5=图片 **/
	var elemid =  $(".tab-content .active").attr('id');
	var type = 1;
	var title =  $(".tab-content .active form input[name=title]").val();
	var content = "";
	var view = !$(".tab-content .active form input[name=view]").is(':checked');
	var url =  $(".tab-content .active form input[name=url]").val();
	var tag =  $(".tab-content .active form input[name=tag]").val();
	if(elemid=="modal_ty_a"){//文件分享
		content =  CKEDITOR.instances.ck_tya.getData() ;
		type=1;
	}else if(elemid=="modal_ty_b"){//文章分享
		content =  CKEDITOR.instances.ck_tyb.getData() ;
		type=2;
	}else if(elemid=="modal_ty_c"){//音乐分享
		content =  CKEDITOR.instances.ck_tyc.getData() ;
		type=3;
	}else if(elemid=="modal_ty_d"){//电影分享
		content =  CKEDITOR.instances.ck_tyd.getData() ;
		type=4;
	}else if(elemid=="modal_ty_e"){//图片分享
		type=5;
		url =  $("#modal_show").attr("src");
	}
	save(type, title, content, view, url, tag);
});


//upload image
$(function() {
	$("#modal_image").fileupload({
	    	url: '/share/upload',
	    	sequentialUploads: true
	    }).bind('fileuploadprogress', function (e, data) {
	    	var progress = parseInt(data.loaded / data.total * 100, 10);
	    	$("#modal_progress").css('width',progress + '%');
	    	$("#modal_progress").html(progress + '%');
	    }).bind('fileuploaddone', function (e, data) {
	    	$("#modal_show").attr("src",data.result);
	    	$("#modal_upload").css({display:"none"});
	    	$("#modal_cancle").css({display:""});
	    });
	
	//upload image delete reUpload
	$("#modal_cancle").click(function(){
		var url =  $("#modal_show").attr("src");
		$.post("/share/upload/delete", {
			url : url
		}, function(data) {
			$("#modal_show").attr("src","/resource/image/preview.png");
			$("#modal_upload").css({display:""});
	    	$("#modal_cancle").css({display:"none"});
	    	$("#modal_progress").css('width','0%');
	    	$("#modal_progress").html('');
		});
	});
	
});


function save(type,title,content,view,url,tag){
	$("#save").attr("disabled",true); 
	$.post("/share/save", {
		category : type,
		title : title,
		content : content,
		view : view,
		url : url,
		tag_str : tag
	}, function(data) {
		if(data=='true'){
			$("#myTabContent").addClass("sr-only");//隐藏
			$("#thank").fadeIn("slow",function(){//
				$("#close_tip").fadeIn(500,function(){
					$("#close_tip").fadeOut(1000,function(){
						$("#thank").fadeOut(500);
						$('#myModal').modal('hide');//hide modal
						$("#myTabContent").removeClass("sr-only");//隐藏
					});
				});
			});
			if(type==1){
				$('#modal_ty_a .form-horizontal')[0].reset();
				CKEDITOR.instances.ck_tya.setData('');//clear CKEDITOR
			}else if(type==2){
				$('#modal_ty_b .form-horizontal')[0].reset();
				CKEDITOR.instances.ck_tyb.setData('');//clear CKEDITOR
			}else if(type==3){
				$('#modal_ty_c .form-horizontal')[0].reset();
				CKEDITOR.instances.ck_tyc.setData('');//clear CKEDITOR
			}else if(type==4){
				$('#modal_ty_d .form-horizontal')[0].reset();
				CKEDITOR.instances.ck_tyd.setData('');//clear CKEDITOR
			}else if(type==5){
				$('#modal_ty_e .form-horizontal')[0].reset();
				$("#modal_show").attr("src","/resource/image/preview.png");
				$("#modal_upload").css({display:""});
		    	$("#modal_cancle").css({display:"none"});
		    	$("#modal_progress").css('width','0%');
		    	$("#modal_progress").html('');
			}
			$("#save").removeAttr("disabled");
		}else{
			alert(data);
			$("#save").removeAttr("disabled");
		}
	});
}

function signup_share() {
	var app = navigator.appName;
	$.get("/user/signup", {
		app : app
	}, function(data) {
		$("#modal_signup").fadeOut("slow",function(){
			var showID = data.substr(data.length - 8, 8);
			var tip='<span id="modal_signup_tip" style="display:none;">注册成功  ID='+showID+'<span>';
			$(tip).appendTo("#modal_signup_info");
			$("#modal_signup_tip").fadeIn("fast");
			$("#modal_signup_tip").fadeOut(2000,function(){
				$("#myTabContent").removeClass("sr-only");
				$("#save").removeAttr("disabled");
			});
		});
	});
}

function signup() {
	var app = navigator.appName;
	$.get("/user/signup", {
		app : app
	}, function(data) {
		$("#signup").fadeOut("slow",function(){
			var tip='<span id="tip" style="display:none;">注册成功<span>';
			$(tip).appendTo("#userinfo");
			$("#tip").fadeIn("fast");
			$("#tip").fadeOut(2000,function(){
				// $("#userinfo").empty();
				var showID = data.substr(data.length - 8, 8);
				var content='<a id="myshare" href="/my/share" title="'+showID+'">我的分享</a>';
				$(content).appendTo("#userinfo");
				$("#myshare").fadeIn(2000);
			}); 
		});
	});
}

function signin() {
	var email = $("#signin_email").val();
	var password = $("#signin_pwd").val();
	$.get("/user/signin", {
		email : email,
		password : password
	}, function(data) {
		if(data!="SUCCESS"){
			$("#signin_tip").html("<i class='fa fa-exclamation-triangle text-danger'></i>"+"邮箱或密码错误");
			return ;
		}
		$("#signin_tip").html("<i class='fa fa-check-square-o text-infor'></i>"+"登录成功");
		$('#signin').css('display',"none");//hide登录按钮
		$("#signup").fadeOut("slow",function(){
			$('#myModal-signin').modal('hide');//hide modal
			var tip='<span id="tip" style="display:none;">登录成功<span>';
			$(tip).appendTo("#userinfo");
			$("#tip").fadeIn("fast");
			$("#tip").fadeOut(2000,function(){ 
				// $("#userinfo").empty();
				var showID = data.substr(data.length - 8, 8);
				var content='<a id="myshare" href="/my/share" title="'+showID+'">我的分享</a>';
				$(content).appendTo("#userinfo");
				$("#myshare").fadeIn(2000);
			}); 
		});
	});
}

function lk(sid){
	$.post("/share/like/save", {
		sid : sid
	}, function(data) {
		if('NOUSER'==data){
			$("#lkc-"+sid).popover({
	            content:'未登录' 
	        });
	        $("#lkc-"+sid).popover('show');
		}else if('RELIKE'==data){ 
			$("#lkc-"+sid).popover({
	            content:'已赞' 
	        });
	        $("#lkc-"+sid).popover('show');
		}else if('NOSHARE'==data){
			$("#lkc-"+sid).popover({
	            content:'None' 
	        });
	        $("#lkc-"+sid).popover('show');
		}else{
			$("#lk-"+sid).html("赞("+data+")");
		}
	});
}

/*评论加载模态框*/
function comt(sid){
	$("#shareid_atcomt").val(sid);
	$("#myModal-comt").modal({
		backdrop: true,//点击空白处模态框消失
	});
}
function comt_save(){
	$("#comt-save").addClass("disabled");//禁用保存按钮
	var sid = $("#shareid_atcomt").val();
	var content = CKEDITOR.instances.comteditor.getData();
	$.post("/share/comment/save", {
		sid : sid,
		content:content
	}, function(data) {
		if('NOUSER'==data){
			$("#comt-tip").html("您尚未登录");
			$("#comt-tip").fadeIn(2000,function(){
				$("#comt-tip").fadeOut(3000,function(){
					//do something
				});
			}); 
		}else if('TCMORE'==data){ 
			$("#comt-tip").html("您今天评论的太多啦");
			$("#comt-tip").fadeIn(2000,function(){
				$("#comt-tip").fadeOut(3000,function(){
					//do something
				});
			}); 
		}else if('NOSHARE'==data){
			$("#comt-tip").html("SHARE ID 错误");
			$("#comt-tip").fadeIn(2000,function(){
				$("#comt-tip").fadeOut(3000,function(){
					//do something
				});
			}); 
		}else{
			var obj = JSON.parse(data);
			$("#comt-"+sid).html("评论("+obj.comts+")");
			if(obj.load){
				$("#share-content-"+sid).html(obj.content); //重新加载评论内容
			}
			$("#comt-tip").html("评论保存成功，谢谢您的支持");
			$("#comt-tip").fadeIn(500,function(){
				$("#comt-tip").fadeOut(1000,function(){
					$('#myModal-comt').modal('hide');//hide modal
					$("#comt-save").removeClass("disabled");//移出保存按钮的禁用样式
					CKEDITOR.instances.comteditor.setData("");//清空ckeditor的评论内容
				});
			}); 
			
		}
	});
}

function report(sid,type){
	$.post("/share/report", {
		sid : sid,
		type:type
	}, function(data) { 
		var shid = "#report-"+sid;
		if(type==2){
			var shid = "#report-comt-"+sid;
		}
		if('NOUSER'==data){
			$(shid).popover({
	            content:'未登录' 
	        });
	        $(shid).popover('show');
		}else if('REREPORT'==data){ 
			$(shid).popover({
	            content:'已举报过' 
	        });
	        $(shid).popover('show');
		}else if('NONE'==data){
			$(shid).popover({
	            content:'None' 
	        });
	        $(shid).popover('show');
		}else if('SITEAUTH'==data){
			$(shid).popover({
	            content:'不要恶意举报' 
	        });
	        $(shid).popover('show');
		}else{
			$(shid).popover({
	            content:'已举报'
	        });
	        $(shid).popover('show');
		}
	});
}

/**加载评论*/
function loadMoreComts(sid,page){
	$.post("/share/comment/list", {
		sid : sid,
		page:page
	}, function(data) {
		$("#comt-load-more").remove();
		$(data).appendTo(".hz-comts"); 
	});
}

$('.hz-pop').on('shown.bs.popover', function () {
	$(this).next('div').fadeOut(2000,function(){
		$(this).next('div').remove();
	});
})

var comtCkeditor = CKEDITOR.replace('content', {
	toolbar:[
		['Bold','Italic','Underline','Strike'],
		['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
		['Font','FontSize'],
		['TextColor','BGColor']
	], 
	uiColor : '#AADC6E',
	allowedContent:false,
	
}); 

/**初始化音乐播放器**/
audiojs.events.ready(function() {
    var as = audiojs.createAll();
});
/*switch*/ 
/***
<input id="switch-state"  name="switch_tya" type="checkbox" checked>
$('input[name="switch_tya"]').bootstrapSwitch({
    size:"mini",
    state:false,
    labelText:"图文编辑器",
    onSwitchChange:function(event,state){
        if(state){
        	//
        }else{
        
        }
    }
})
*/
