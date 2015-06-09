<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
	<header class="main-header"  style="background-image: url(/resource/image/share-header-bg.jpg)">
		<div class="container">
		    <div class="row">
		        <div class="col-sm-12  text-center">
		            <!-- start logo -->
		            <a class="branding sr-only"  href="http://www.qishimeishi.com" title="其实没事  ">
		           	 <img src="/resource/image/blogo.png" alt="其实没事   "></a>
		            <!-- end logo -->
		            <h2 class="text-hide">其实没事  分享</h2>
		            <img src="/resource/image/share-header-bg.jpg" alt="其实没事  分享" class="hide">
		        </div>
		    </div>
		</div>
	</header>
    <nav class="navbar navbar-default hz-navbar ">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="#" class="navbar-brand"> <img src="/resource/image/brand.png" width="25" height="25" ></a>
                <p class="navbar-text"><strong>其实、没事</strong></p>
            </div>
            <ul class="nav navbar-nav"> 
                <li ${m==null||m==0?'class="active" ':''}><a href="/share">首页</a></li>
                <li ${m==1?'class="active" ':''}><a href="/share/file"><i class="icon-download-alt"></i>资源</a></li>
                <li ${m==2?'class="active" ':''}><a href="/share/article">文章</a></li>
                <li ${m==3?'class="active" ':''}><a href="/share/music">音乐</a></li>
                <li ${m==4?'class="active" ':''}><a href="/share/video">视频</a></li>
                <li ${m==5?'class="active" ':''}><a href="/share/image">美图</a></li>
            </ul>


            <form  class="navbar-form navbar-left hidden-xs hidden-sm" role="search" >
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>

			<p class="navbar-text" id="userinfo">
				<c:if test="${loginUser!=null}" >
	           		<a href="${pageContext.request.contextPath}/my/share" title="${loginUser.id}">我的分享</a>
				</c:if>
				<c:if test="${loginUser==null}"  >
	           		 <a id="signup" href="javascript:signup()">一键注册</a>
				</c:if>
            </p>
			<c:if test="${loginUser==null}"  >
           	<button type="button" class="navbar-btn btn btn-default" role="button" id="signin">登录</button> 
			</c:if>
            
            <button type="button" class="navbar-btn navbar-right btn btn-primary" role="button" id="share_new">分享Go</button>
        </div>
    </nav>