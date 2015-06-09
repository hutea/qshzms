<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/Font-Awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/css/hz.ucenter.css" rel="stylesheet">
    <title>后台管理页面</title>
</head>
<body>
    <%@ include file="/WEB-INF/page/view/my/user-center-head.jsp" %>

    <div class="mybody container">
        <div class="row">
            <div class="col-md-9">
                <ol class="breadcrumb">
                    <li><a href="">密码找回</a></li>
                </ol>
                <div class="content">
					    <div class="form-inline">
						    <input type="text" class="form-control" name="email" placeholder="输入邮箱地址" id="email" />
						    <input type="button" class="btn btn-default" id="sendMail" onclick="pwdSendEmail()" value="确定"/>
					    </div>
					  	<p id="tip" ></p>
                </div>
        </div>
    </div>
    </div>
    <div class="myfoot">
      <%@ include file="/WEB-INF/page/view/common/foot.jsp" %>
    </div>

<script src="${pageContext.request.contextPath}/resource/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>
<script type="text/javascript">
var countdown=60;

function pwdSendEmail() {
	var email = $("#email").val();
	$.post("/user/pwd/email/send", {
		email : email
	}, function(data) {
		if (data == "EMAILERROR") {
			$("#tip").html("<i class='fa fa-exclamation-triangle text-danger'></i>"+"邮箱错误");
		}else if(data=="SUCCESS"){
			countdown=60;//重新设置为60
			$("#tip").html("<i class='fa fa-check-square-o text-info'></i>"+"邮件已发送，请登录邮箱完成密码找回");
			countDown() ;
			$("#bindEmail").css("display", "");
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
	
</script>
</body>
</html>