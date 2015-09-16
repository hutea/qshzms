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
			<a href=""><i class="fa fa-home"></i> <span>首页</span>
			</a>
		</li>

		<li <c:if test="${param.m==1||m==1}">class="active parent"</c:if> class="parent">
			<a href=""><i class="fa fa-bars"></i><span>BLOG管理</span></a>
			<ul class="children">
				<li><a href='${pageContext.request.contextPath}/manage/blog/new'>写博客</a></li>
				<li><a href='${pageContext.request.contextPath}/manage/blog/list'>博客管理</a></li>
				<li><a href='${pageContext.request.contextPath}/manage/blog/type/list'>博客分类管理</a></li>
			</ul>
		</li>
		<li <c:if test="${param.m==2||m==2}">class="active parent"</c:if> class="parent">
			<a href=""><i class="fa fa-bars"></i><span>Share管理</span></a>
			<ul class="children">
				<li><a href='${pageContext.request.contextPath}/manage/share/list'>Share管理</a></li>
				<li><a href='${pageContext.request.contextPath}/manage/share/tag/list'>Share 标签管理</a></li>
			</ul>
		</li>
		<li <c:if test="${param.m==3||m==3}">class="active parent"</c:if> class="parent">
			<a href=""><i class="fa fa-bars"></i><span>帐号管理</span></a>
			<ul class="children">
				<li><a href='${pageContext.request.contextPath}/manage/account/list'>系统帐号管理</a></li>
				<li><a href='${pageContext.request.contextPath}/manage/user/list'>用户帐号管理</a></li>
				<li><a href='${pageContext.request.contextPath}/manage/account/group/list'>角色定义</a></li>
			</ul>
		</li>
		<li <c:if test="${param.m==4||m==4}">class="active parent"</c:if> class="parent">
			<a href=""><i class="fa fa-bars"></i><span>明星资源</span></a>
			<ul class="children">
				<li><a href='${pageContext.request.contextPath}/manage/star/new'>明星添加</a></li>
				<li><a href='${pageContext.request.contextPath}/manage/star/list'>明星管理</a></li>
				<li><a href='${pageContext.request.contextPath}/manage/star/category/list'>明星分类管理</a></li>
			</ul>
		</li>
		<li <c:if test="${param.m==9||m==9}">class="active parent"</c:if> class="parent">
			<a href=""><i class="fa fa-bars"></i><span>贴图库管理</span></a>
			<ul class="children">
				<li><a href='${pageContext.request.contextPath}/manage/ttk/list'>相册管理</a></li>
				<li><a href='${pageContext.request.contextPath}/manage/ttk/image/list'>上传记录</a></li>
			</ul>
		</li>
		
	</ul>
</div>
<!-- leftpanel -->