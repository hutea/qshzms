<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
    <div class="myhead">
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#"><img src="/resource/image/brand.png" width="25" height="25"></a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class=""><a href="/share">官方首页</a></li>
                        <li ><a href="/my/center">帐号管理</a></li>
                        <li ><a href="/my/share">我的分享</a></li>
                        <li ><a href="/my/comment">评论记录</a></li>
                        <li ><a href="/my/report">举报记录</a></li>
                        <li ><a href="/my/share/new">开始分享</a></li>
                        
                        <form class="navbar-form navbar-left" role="search" >
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Search">
                            </div>
                            <button type="submit" class="btn btn-default">搜索</button>
                        </form>
                    </ul>
               		<p class="navbar-text">您好，欢迎进入个人中心。</p>
                	<button type="button" class="navbar-btn navbar-right btn btn-default" role="button" onclick="signout()">登出</button>
                </div>
            </div>
        </nav>
    </div>