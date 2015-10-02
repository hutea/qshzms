<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE html>
<!--[if lte IE 8]>              <html class="ie8 no-js" lang="en">     <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <html class="not-ie no-js" lang="en">  <!--<![endif]-->
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	
	<title>最新资源</title>
	
	<meta name="description" content="明星资源">
	<meta name="author" content="">
	
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resource/star/index/images/favicon.ico">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/star/index/css/style.css" media="screen" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/star/index/fancybox/jquery.fancybox.css" media="screen" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/star/index/css/video-js.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/star/index/css/audioplayerv1.css" media="screen" />
	
	<!-- HTML5 Shiv + detect touch events -->
	<script src="${pageContext.request.contextPath}/resource/star/index/js/modernizr.custom.js"></script>
	
	<!-- HTML5 Video Player -->
	<script src="${pageContext.request.contextPath}/resource/star/index/js/video.js"></script>
	<script>_V_.options.flash.swf = '${pageContext.request.contextPath}/resource/star/index/js/video-js.swf';</script>
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
		
		<!-- **************** - END Header - **************** -->
		
		
		<div class="content-wrapper sbr clearfix">
			
			<div class="page-header clearfix">
				
				<h1 class="alignleft">最新资源</h1>
				
				<div class="search-container">
					<form id="search" action="/">
						<input placeholder="Search" type="text" />
						<button type="submit"></button>
					</form><!--/ #search-->
				</div><!--/ .search-container-->
				
			</div><!--/ .page-header-->
			
			<section id="content">
			<c:forEach items="${pageView.records}" var="entry">
				<article class="post-item clearfix">
					<a class="single-image picture-icon" href="${entry.showImage}">
						<img class="entry-image" src="${entry.showImage}" alt="" />
					</a>
					<div class="image-title clearfix">
						<a >
							<h2>
								<span class="post-type picture"></span>
								${entry.name}<br/>
								<span style="font-size: 13px;">演出时间:<fmt:formatDate value="${entry.showDate}" pattern="yyyy-MM-dd"/></span>
							</h2>
						</a>
					</div><!--/ .image-title-->
					
					<div class="one-sixth">
						<div class="meta-entry">
							<a href="#" class="date"><span title="发布时间"><fmt:formatDate value="${entry.createDate}" pattern="yyyy-MM-dd"/></span></a>
							<a href="#" class="author"><span>${entry.star.name}</span></a>
							<span class="category">${entry.category.name }</span>
						</div><!--/ .meta-entry-->
					</div><!--/ .one-sixth-->
					
					<div class="entry-body">
						<p>${entry.summary }</p>
						<c:if test="${fn:length(entry.baiduDownUrl)>5 }">
						<a href="javascript:void(0)" onclick="javascript:downloadClick('${entry.id}')"  >云下载(${entry.baiduDownNum}次)</a>
						</c:if>
						<c:if test="${fn:length(entry.playUrl)>5 }">
						<a href="javascript:void(0)" onclick="javascript:playClick('${entry.id}')">播放(${entry.playNum}次)</a>
						</c:if>
						<div id="download-content-${entry.id}" style="display: none;margin: 10px 0px;color: #132587;"></div>
						<div id="play-content-${entry.id}" style="display: none;"></div>
					</div><!--/ .entry-body-->
					
				</article><!--/ .post-item-->
			</c:forEach>
				
				<div class="page-title clearfix">
					<div class="pagination">
						<c:if test="${pageView.currentPage>1}">
							<a href="/star/item?page=${pageView.currentPage-1}" class="prev-project">Prev</a> <!--/ .prev-project-->
						</c:if>
						<c:if test="${pageView.currentPage<=1}">
							<span href="#" class="prev-project">Prev</span> <!--/ .prev-project-->
						</c:if>
						<c:if test="${pageView.currentPage>=pageView.totalPage}">
							<span href="#" class="next-project">Next</span> <!--/ .next-project-->
						</c:if>
						<c:if test="${pageView.currentPage<pageView.totalPage}">
							<a href="/star/item?page=${pageView.currentPage+1}" class="next-project">Next</a> <!--/ .next-project-->
						</c:if>
					</div><!--/ .pagination-->
				</div><!--/ .page-title-->
				
			</section><!--/ #content-->
			
			
			<!-- ********** - Sidebar - ************ -->
			
			<aside id="sidebar">
				
				<div class="widget">
					<h1 class="widget-title">数据统计</h1>

					<ul class="feature-menu">
						<c:forEach items="${stars}" var="star">
							<li><a href="/star/view/${star.code }">${star.name }</a>&nbsp;<span>(${star.resnum})</span></li>
						</c:forEach>
					</ul><!--/ .feature-menu-->
					
				</div><!--/ .widget-->
				
			</aside><!--/ #sidebar-->
			
			<!-- ********** - END Sidebar - ************ -->
			
			
		</div><!--/ .content-wrapper-->
		

		<!-- ************* - Main Footer - *************** -->
		<%@ include file="/WEB-INF/page/view/core/star_foot.jsp" %>  

		<!-- ************ - END Footer - ************ -->	

		
	</section><!--/ .container-->
	
	<!-- *************** - END Container - *************** -->

	
	<!-- ************ - Footer Bottom - ************ -->
	<%@ include file="/WEB-INF/page/view/core/star_footbottom.jsp" %>  
	
	<!-- ************ - END Footer - ************ -->
	

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
<script type="text/javascript">
	function downloadClick(siid){
		$.post("/star/item/download/click", {
			siid : siid
		}, function(data) {
				$("#download-content-"+siid).css("display","block");
				$("#download-content-"+siid).html(data);
		});
	}
	
	function playClick(siid){
		$.post("/star/item/paly/click", {
			siid : siid
		}, function(data) {
			$("#play-content-"+siid).css("display","block");
			$("#play-content-"+siid).html(data);
		});
	}
</script>
</body>
</html>
