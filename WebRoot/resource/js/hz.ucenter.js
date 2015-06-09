$(function() {
	$(".js-affixed-element-top").affix( {
		offset : {
			top : 10,
			bottom : 10
		}
	})
})

function inputEmail() {
	$("#email").css("display", "");
	$("#sendMail").css("display", "");
}

var countdown=60;

function sendEmail() {
	var email = $("#email").val();
	$.post("/my/email/send", {
		email : email
	}, function(data) {
		if (data == "EMAILERROR") {
			$("#tip").html("<i class='fa fa-exclamation-triangle text-danger'></i>"+"邮箱格式错误");
		}else if(data=="SUCCESS"){
			countdown=60;//重新设置为60
			$("#tip").html("<i class='fa fa-check-square-o text-info'></i>"+"邮件已发送，请登录邮箱完成绑定，您也可将收到的验证码复制到下面输入框中完成邮箱绑定。");
			countDown() ;
			$("#bindEmail").css("display", "");
		}else if(data=="BINDED"){
			$("#tip").html("<i class='fa fa-exclamation-triangle text-danger'></i>"+"已绑定");
		}else if(data=="EMAILUSED"){
			$("#tip").html("<i class='fa fa-exclamation-triangle text-danger'></i>"+"邮箱已经被使用");
		}else{
			$("#tip").html("<i class='fa fa-exclamation-triangle text-danger'></i>"+"请"+data+"秒后后重试");
		}
	});
}

function countDown() {
	if (countdown == 0) {
		$("#sendMail").removeAttr("disabled");
		$("#sendMail").attr("value","重新发送" ); 
	} else {
		$("#sendMail").attr("disabled", true);
		$("#sendMail").attr("value","重新发送(" + countdown + ")"); 
		countdown--;
	}
	setTimeout(function() {
		countDown()
	}, 1000)
}

function bindEmail() {
	var code = $("#code").val();
	$.post("/my/email/bind/ajax", {
		code : code
	}, function(data) {
		if (data == "CODEERROR") {
			$("#tip").html("<i class='fa fa-exclamation-triangle text-danger'></i>"+"验证码错误");
		}else if(data=="BINDED"){
			$("#tip").html("<i class='fa fa-exclamation-triangle text-danger'></i>"+"已绑定");
		}else if(data=="CODEOVERTIME"){
			$("#tip").html("<i class='fa fa-exclamation-triangle text-danger'></i>"+"验证码过期");
		}else{
			$("#tip").html("<i class='fa fa-check-square-o text-info'></i>"+ "恭喜，绑定成功。登录帐号为“邮箱地址”，初始密码为“邮箱前6位”！");
		}
	});
}

function profileEdit() {
	var nickname = $("#nickname").val();
	$.post("/my/profile/edit", {
		nickname : nickname
	}, function(data) {
		if (data == "SUCCESS") {
			$("#profiletip").html("<i class='fa fa-check-square-o text-info'></i>"+"昵称修改成功");
		}
	});
}
function pwdEdit() {
	var oripwd = $("#oripwd").val();
	var newpwd = $("#newpwd").val();
	var newpwd_rep = $("#newpwd_rep").val();
	if(oripwd==""){
		$("#pwdtip").html("<i class='fa fa-exclamation-triangle text-danger'></i>"+"原密码未输入");
		return ;
	}
	if(newpwd==""){
		$("#pwdtip").html("<i class='fa fa-exclamation-triangle text-danger'></i>"+"新密码未输入");
		return ;
	}
	if(newpwd!=newpwd_rep){
		$("#pwdtip").html("<i class='fa fa-exclamation-triangle text-danger'></i>"+"两次输入的新密码不一致");
		return ;
	}
	$.post("/my/pwd/edit", {
		oripwd : oripwd,
		newpwd : newpwd
	}, function(data) {
		if (data == "SUCCESS") {
			$("#pwdtip").html("<i class='fa fa-check-square-o text-infor'></i>"+"修改成功");
		}else if (data == "UNBIND") {
			$("#pwdtip").html("<i class='fa fa-exclamation-triangle text-danger'></i>"+"未绑定邮箱不能修改");
		}else if (data == "ORIPWDERROR") {
			$("#pwdtip").html("<i class='fa fa-exclamation-triangle text-danger'></i>"+"原密码错误");
		}
	});
}
function signout(){
	$.get("/my/signout", {
	}, function(data) {
		if (data == "SUCCESS") {
			alert("退出成功");
			self.location='http://www.qishimeishi.com'; 
		}else{
			alert("当前你使用的临时帐号(未绑定邮箱)，暂不支持完全退出！");
		}
	});
}

/**SHARE add*/
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
	$.post("/share/save", {
		category : type,
		title : title,
		content : content,
		view : view,
		url : url,
		tag_str : tag
	}, function(data) {
		if(data=='true'){
			$("#share_save_tip").html("<i class='fa fa-check-square-o text-infor'></i>"+"保存成功！");
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
		}else{
			$("#share_save_tip").html("<i class='fa fa-exclamation-triangle text-danger'></i>"+data);
		}
	});
}
