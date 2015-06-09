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
    		<%@ include file="/WEB-INF/page/view/my/user-center-menu.jsp" %>
            <div class="col-md-9">
                <ol class="breadcrumb">
                    <li><a href="/my/center">个人中心</a></li>
                    <li><a href="#">帐号管理</a></li>
                </ol>
                <div class="content">
               	   <ul id="myTab" class="nav nav-tabs ">
				        <li class="active"><a href="#msg" data-toggle="tab">帐号信息</a></li>
				        <li><a href="#edit" data-toggle="tab">资料修改</a></li>
				        <li><a href="#pwd" data-toggle="tab">密码修改</a></li>
				    </ul>
				    <div id="myTabContent" class="tab-content" >
				        <div id="msg" class="tab-pane fade in active">
				        	<div class="row"><h4>基本信息</h4></div>
				            <table class="table table-bordered table-condensed">
				            	<tr>
				            		<td>昵称</td>
				            		<td>分享次数</td>
				            		<td>评论次数</td>
				            		<td>举报次数</td>
				            		<td>点赞数</td>
				            		<td>状态</td>
				            	</tr>
				            	<tr>
				            		<td>${loginUser.nickname}</td>
				            		<td>${myShares}</td>
				            		<td>${myComments}</td>
				            		<td>${myReports}</td>
				            		<td>${myLikes }</td>
				            		<td>${loginUser.emailBind?"邮箱会员":"普通用户" }</td>
				            	</tr>
				            </table>
				            <div class="row">
				            	<c:if test="${fn:length(myTags)>0}"><h4>我的标签</h4></c:if>
				            	<c:forEach items="${myTags}" var="tag">
				            		<div class="col-sm-3 col-md-2" >
				            			<label><a href="/share/tag/${tag.id}" >${tag.name}</a></label>
				            		</div>
				            	</c:forEach>
				            </div>
				            <c:if test="${!loginUser.emailBind}">
				            	<div></div>
				            	<p>尊敬的用户您好，由于您尚未绑定邮箱，系统建议你<a href="javascript:inputEmail()">点此</a>立即绑定</p>
							    <div class="form-inline">
								    <input type="text" class="form-control" name="email" placeholder="输入要绑定的邮箱地址" id="email" style="display: none;"/>
								    <input type="button" class="btn btn-default" id="sendMail" style="display: none;" onclick="sendEmail()" value="确定"/>
							    </div>
							  	<p id="tip" ></p>
							    <div id="bindEmail" class="form-inline" style="display: none;">
								    <input type="text" class="form-control" name="code" id="code" placeholder="输入验证码" maxlength="6" />
								    <input type="button" class="btn btn-default" id="bindMail"  onclick="bindEmail()" value="确定"/>
							    </div>			            
				            </c:if>
				        </div>
				        <div id="edit" class="tab-pane fade">
							<p id="profiletip" ></p>
				            <div class="form-inline">
								   <input type="text" class="form-control" name="nickname" placeholder="修改昵称" id="nickname" value="${loginUser.nickname}"/>
								   <input type="button" class="btn btn-default"  onclick="profileEdit()" value="确定"/>
							</div>
				        </div>
				        <div id="pwd" class="tab-pane fade">
				            <form class="form-horizontal">
				            	  <p id="pwdtip" ></p>
				            	   <div class="form-group">
									   <label class="col-md-2 control-label">原密码</label>
									   <div class="col-md-4">
									   	<input type="text" class="form-control"  id="oripwd"/>
									   </div>			            	   
				            	   </div>
				            	   <div class="form-group">
									   <label class="col-md-2 control-label">新密码</label>			            	   
									   <div class="col-md-4">
									   <input type="text" class="form-control" id="newpwd" />
				            	  	   </div>
				            	   </div>
				            	   <div class="form-group">
									   <label class="col-md-2 control-label">确认新密码</label>			            	   
									   <div class="col-md-4">
									   <input type="text" class="form-control"  id="newpwd_rep" />
									   </div>
				            	   </div>
				            	   <div class="form-group text-center">
									   <div class="col-md-6">
								  		<input type="button" class="btn btn-default"  onclick="pwdEdit()" value="确定"/>
				            	   		</div>
				            	   </div>
							</form>
				        </div>
				    </div>
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