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
				   <c:if test="${msg=='CODEERROR'}"><i class='fa fa-exclamation-triangle text-danger'></i> 验证码错误</c:if>
               	   <c:if test="${msg=='CODEOVERTIME'}"><i class='fa fa-exclamation-triangle text-danger'></i> 验证码过期</c:if>
               	   <c:if test="${msg=='UIDERROR'}"><i class='fa fa-exclamation-triangle text-danger'></i> 用户不存在</c:if>
               	   <c:if test="${msg=='SUCCESS'}">
               	   		<i class='fa fa-check-square-o text-info'></i> 恭喜，密码找回成功，新的密码为：<strong>${pwd}</strong>
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
</body>
</html>