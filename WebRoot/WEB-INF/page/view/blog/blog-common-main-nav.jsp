<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
<nav class="main-navigation">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="navbar-header">
                        <span class="nav-toggle-button collapsed" data-toggle="collapse" data-target="#main-menu">
                        <span class="sr-only">Toggle navigation</span>
                        <i class="fa fa-bars"></i>
                        </span>
                </div>
                <div class="collapse navbar-collapse" id="main-menu">
                    <ul class="menu">
                        <li ${m==1?'class="nav-current" ':''} role="presentation"><a href="/blog">首页</a></li>
                        <li ${m==2?'class="nav-current" ':''} role="presentation"><a href="/blog/better">推荐文章</a></li>
                       	<li ${m==3?'class="nav-current" ':''}  role="presentation"><a href="/blog/${type.code}">分类 【${type.name}】</a></li>
                       	<li ${m==4?'class="nav-current" ':''}  role="presentation"><a href="/blog/tag/${tag.id}">标签【${tag.name}】</a></li>
                        <li ${m==5?'class="nav-current" ':''} role="presentation"><a href="/blog/tags/all">标签云</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>