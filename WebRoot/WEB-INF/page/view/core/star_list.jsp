<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE html>
<!--[if lte IE 8]>     <html class="ie8 no-js" lang="en">     <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <html class="not-ie no-js" lang="en">  <!--<![endif]-->
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>明星资源站</title>
	<meta name="description" content="明星资源站 其实没事">
	<meta name="author" content="">
	
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resource/star/index/images/favicon.ico">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/star/index/css/style.css" media="screen" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/star/index/fancybox/jquery.fancybox.css" media="screen" />
	
	<!-- HTML5 Shiv + detect touch events -->
	<script src="${pageContext.request.contextPath}/resource/star/index/js/modernizr.custom.js"></script>
</head>
<body class="liquid light">

<div id="wrapper">

	
	<!-- ***************** - Header Top - ***************** -->	
	<%@ include file="/WEB-INF/page/view/core/star_headtop.jsp" %>   
	
	<!-- ***************** - END Header Top - ***************** -->


	<!-- ***************** - Container - ***************** -->
	
	<section class="container">
		<!-- ***************** - Header - ***************** -->
		<%@ include file="/WEB-INF/page/view/core/star_head.jsp" %> 
		
		<!-- **************** - end Header - **************** -->
		
		
		<div class="content-wrapper clearfix">
			
			<header class="page-header clearfix">
				
				<h1>Star ${title }</h1>
				
				<ul id="portfolio-filter">
					<li>Show:</li>
					<li><a data-categories="*">All</a></li>
					<li><a data-categories="type_1">华语</a></li>
					<li><a data-categories="type_2">欧美</a></li>
					<li><a data-categories="type_3">日韩</a></li>
				</ul><!-- end #portfolio-items-filter -->
				
			</header><!--/ .page-header-->
			
			<section id="portfolio-items" class="gl-col-2">
				<c:forEach items="${pageView.records}" var="entry">
					<article class="one-half" data-categories="type_${entry.type}">
	
						<a href="${entry.imageUrl}" class="single-image picture-icon" rel="group_portfolio">
							<img src="${entry.imageUrl }" alt="" style="width: 460px;height: 280px;">
						</a>
	
						<a href="/star/view/${entry.code}" class="project-meta">
							<h3 class="title-item">${entry.name }</h3>
							<span class="categories">${entry.note }</span>
							<div>
								<span title="总访问量">${entry.pv}PV</span>
								<span title="资源总数">${entry.resnum}RS </span>
								<span title="最后更新时间"><fmt:formatDate value="${entry.modifyDate }" pattern="yyyy-MM-dd"/></span>
							</div>
						</a>
				
					</article><!--/ .one-half -->
				</c:forEach>

			</section><!--/ #portfolio-items-->

			<div class="page-title clearfix">
					<div class="pagination">
						<c:if test="${pageView.currentPage>1}">
							<a href="/star/list/${way}?page=${pageView.currentPage-1}" class="prev-project">Prev</a> <!--/ .prev-project-->
						</c:if>
						<c:if test="${pageView.currentPage<=1}">
							<span href="#" class="prev-project">Prev</span> <!--/ .prev-project-->
						</c:if>
						<c:if test="${pageView.currentPage>=pageView.totalPage}">
							<span href="#" class="next-project">Next</span> <!--/ .next-project-->
						</c:if>
						<c:if test="${pageView.currentPage<pageView.totalPage}">
							<a  href="/star/list/${way}?page=${pageView.currentPage+1}" class="next-project">Next</a> <!--/ .next-project-->
						</c:if>
					</div><!--/ .pagination-->

				</div><!--/ .page-title-->
						
		</div><!--/ .content-wrapper-->
		

		<!-- ************* - Main Footer - *************** -->
		<%@ include file="/WEB-INF/page/view/core/star_foot.jsp" %>  
		
		<!-- ************ - end Footer - ************ -->	


	</section><!--/ .container-->
	
	<!-- *************** - end Container - *************** -->

	
	<!-- ************ - Footer Bottom - ************ -->
	<%@ include file="/WEB-INF/page/view/core/star_footbottom.jsp" %>  
	
	<!-- ************ - end Footer - ************ -->


</div><!--/ #wrapper-->

<script type="text/javascript" src="${pageContext.request.contextPath}/resource/star/index/js/jquery-1.7.1.min.js"></script>
<!--[if lt IE 9]>
	<script src="js/selectivizr-and-extra-selectors.min.js"></script>
	<script src="../../ie7-js.googlecode.com/svn/version/2.1(beta4)/IE8.js"></script>
<![endif]-->
<script src="${pageContext.request.contextPath}/resource/star/index/js/respond.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/star/index/js/jquery.easing-1.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/star/index/fancybox/jquery.fancybox.pack.js"></script>
<script src="${pageContext.request.contextPath}/resource/star/index/js/jquery.isotope.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/star/index/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/resource/star/index/themeChanger/js/colorpicker.js"></script>
<script src="${pageContext.request.contextPath}/resource/star/index/themeChanger/js/themeChanger.js"></script>

</body>
</html>

