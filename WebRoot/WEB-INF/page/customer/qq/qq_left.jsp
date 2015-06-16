<%@ page language="java" pageEncoding="UTF-8"%>
<div class="leftpanel">
	<div class="media profile-left">
		<a class="pull-left profile-thumb" 	href='' >
			<img class="img-circle" src="${pageContext.request.contextPath}/resource/chain/images/photos/profile.png" alt=""> 
		</a>
		<div class="media-body">
			<h4 class="media-heading">${loginAccount.nickname}</h4>
		</div>
	</div>
	<!-- media -->

	<h3 class="leftpanel-title">
		Navigation
	</h3>
	<ul class="nav nav-pills nav-stacked">
		<li <c:if test="${m==null}">class="active"</c:if> >
			<a href="${pageContext.request.contextPath}/manage/qicq/list"><i class="fa fa-home"></i> <span>QICQ管理</span>
			</a>
		</li>
	</ul>
</div>
<!-- leftpanel -->