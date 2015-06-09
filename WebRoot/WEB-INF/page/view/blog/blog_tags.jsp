<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <title>博客标签云  其实没事博客</title>

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
				<article class="post page">
				    <header class="post-head">
				        <h1 class="post-title">标签云</h1>
				    </header>
				    <section class="post-content widget">
				    	<div class="tag-cloud">
				        	<c:forEach items="${tags}" var="tag">
								<a href="/blog/tag/${tag.id}">${tag.name }</a>
				        	</c:forEach>
				        </div>
				    </section>
				
				</article>

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