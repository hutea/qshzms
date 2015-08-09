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
	<meta name="description" content="其实没事 分享 文章 视频 音乐 资源 下载 图片 大图 美图" />
 	<meta name="keywords" content="其实没事 分享 文章 视频 音乐 资源 下载 图片 大图 美图" />
	<title>个人中心 其实没事（专注分享的网站）</title>
</head>
<body>
    <%@ include file="/WEB-INF/page/view/my/user-center-head.jsp" %>

    <div class="mybody container">
        <div class="row">
    		<%@ include file="/WEB-INF/page/view/my/user-center-menu.jsp" %>
            
            <div class="col-md-9">
                <ol class="breadcrumb">
                    <li><a href="#">个人中心</a></li>
                    <li><a href="#">邮箱绑定</a></li>
                </ol>
                <div class="content">
               	   <c:if test="${msg=='CODEERROR'}"><i class='fa fa-exclamation-triangle text-danger'></i> 验证码错误</c:if>
               	   <c:if test="${msg=='CODEOVERTIME'}"><i class='fa fa-exclamation-triangle text-danger'></i> 验证码过期</c:if>
               	   <c:if test="${msg=='BINDED'}"><i class='fa fa-exclamation-triangle text-danger'></i> 邮箱已绑定</c:if>
               	   <c:if test="${msg=='SUCCESS'}">
               	   		<i class='fa fa-check-square-o text-info'></i> 恭喜，绑定成功。登录帐号为“邮箱地址”，初始密码为“邮箱前6位”！
               	   </c:if>
                </div>
        </div>
    </div>
    </div>
    <div class="myfoot">
      <%@ include file="/WEB-INF/page/view/common/foot.jsp" %>
    </div>

<script src="${pageContext.request.contextPath}/resource/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/hz.ucenter.js"></script>
</body>
</html>