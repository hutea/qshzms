<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <title>推荐文章  其实没事 博客</title>

    <meta name="HandheldFriendly" content="True" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/Font-Awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet">
	<link  href="${pageContext.request.contextPath}/resource/syntaxhighlighter_3.0.83/styles/shCoreDefault.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="/resource/css/hz.blog.css" />
    
    <script src="${pageContext.request.contextPath}/resource/syntaxhighlighter_3.0.83/scripts/shCore.js"></script>
    <%@ include file="/WEB-INF/page/view/blog/shBrush.jsp" %>
   	<script type="text/javascript">SyntaxHighlighter.all();</script>


    <script>
        /*====================================================
         THEME SETTINGS & GLOBAL VARIABLES
         ====================================================*/
        //  1. Sidebar Position
        var sidebar_left = false; // Set true or flase for positioning sidebar on left
        //  2. Recent Post count
        var recent_post_count = 3;
    </script>

</head>
<body class="home-template">

<!-- start header -->

<%@ include file="/WEB-INF/page/view/blog/blog-common-header.jsp" %>
<!-- end header -->

<!-- start navigation -->
<%@ include file="/WEB-INF/page/view/blog/blog-common-main-nav.jsp" %>
<!-- end navigation -->


<!-- start site's main content area -->
<section class="content-wrap">
    <div class="container">
        <div class="row">

            <main class="col-md-8 main-content">
				<c:forEach items="${pageView.records}" var="entry" varStatus="s">
                <article id=85 class="post tag-about-ghost tag-release featured">
                	<c:if test="${entry.recommend}">
                    <div class="featured" title="推荐文章">
                        <i class="fa fa-star"></i>
                    </div>
                	</c:if>

                    <div class="post-head">
                        <h1 class="post-title"><a href="/blog/view/${entry.id}">${entry.title }</a></h1>
                        <div class="post-meta">
                            <span class="author">分类：<a href="/blog/${entry.blogType.code}">${entry.blogType.name}</a></span> &bull;
                            <time class="post-date" title='<fmt:formatDate value="${entry.createDate}" pattern="yyyy年MM月dd日 HH时mm分ss秒"/>' >
                            	<fmt:formatDate value="${entry.createDate}" pattern="yyyy年MM月dd日"/>
                            </time>
                        </div>
                    </div>
                    <div class="post-content">
                        <p>${entry.summary}</p>
                    </div>
                    <div class="post-permalink">
                        <a href="/blog/view/${entry.id}" class="btn btn-default">阅读全文</a>
                        <div class="pull-right">
                        	<span class="text-muted"><storng>${entry.pv}</storng>pv</span>
                        </div>
                    </div>

                    <footer class="post-footer clearfix">
                        <div class="pull-left tag-list">
                            <i class="fa fa-folder-open-o"></i>
                            <c:forEach items="${entry.tags}" var="tag" varStatus="s" >
                            	<a href="/blog/tag/${tag.id}">${tag.name}</a> ${s.index+1!=fn:length(entry.tags)?",":""}
                            </c:forEach>
                        </div>
                        <div class="pull-right share">
                        </div>
                    </footer>
                </article>
				</c:forEach>

                <nav class="pagination" role="navigation">
                	<c:if test="${pageView.currentPage>1}">
                    	<a class="older-posts" href="/blog/page/${pageView.currentPage-1}"><i class="fa fa-angle-left"></i></a>
                	</c:if>
                    <span class="page-number">第 ${pageView.currentPage } 页 &frasl; 共 ${pageView.totalPage} 页</span>
                    <c:if test="${pageView.currentPage<pageView.totalPage}">
                    	<a class="older-posts" href="/blog/page/${pageView.currentPage+1}"><i class="fa fa-angle-right"></i></a>
                	</c:if>
                </nav>


            </main>
			<%@ include file="/WEB-INF/page/view/blog/blog-common-aside.jsp" %>
            
        </div>
    </div>
</section>

<%@ include file="/WEB-INF/page/view/blog/blog-common-footer.jsp" %>

<script src="${pageContext.request.contextPath}/resource/chain/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/hz.sus.blog.js"></script> 

</body>
</html>