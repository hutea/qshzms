<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
 <!DOCTYPE html>
<!--[if lte IE 8]>              <html class="ie8 no-js" lang="en">     <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <html class="not-ie no-js" lang="en">  <!--<![endif]-->
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	
	<title>明星资源站 首页</title>
	<meta name="description" content="明星资源站 其实没事">
	<meta name="author" content="">
	
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resource/star/index/images/favicon.ico">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/star/index/css/style.css" media="screen" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/star/index/sliders/elastslider/elastic.css" media="screen" />
	<!-- HTML5 Shiv + detect touch events -->
	<script src="${pageContext.request.contextPath}/resource/star/index/js/modernizr.custom.js"></script>
	<%@ include file="/WEB-INF/page/view/common/baidutongji.jsp" %>  
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


		<!-- ***************** - Slider - ***************** -->	
		
		<div id="ei-slider" class="ei-slider">
			
			<ul class="ei-slider-large">
				<c:forEach items="${indexStarList}" var="star">
					<li>
						<img src="${star.imageUrl}" />
						<div class="ei-title">
							<h2><a href="/star/view/${star.code }">${star.name}</a></h2>
							<h3>${star.note}</h3>
						</div>
					</li>
				</c:forEach>
			</ul><!-- ei-slider-large -->
			
			<ul class="ei-slider-thumbs">
				<li class="ei-slider-element">Current</li>
				<c:forEach items="${indexStarList}" var="star">
					<li><a href="#">Slide 1</a><img src="${star.imgUrl }"  /></li>
				</c:forEach>
			</ul><!-- ei-slider-thumbs -->
			
		</div><!-- ei-slider -->
		
		<!-- *************** - end Slider - *************** -->	

		
		<!-- *************** - Projects Carousel - *************** -->	
		
		<div class="page-title clearfix">
			<h2>最近更新</h2>
		</div><!--/ .page-title-->
		
		<div class="container">
			<div class="one-fourth">
				<p>
					明星资源站，最新精华资源更新
				</p>
				<p>感谢您访问明星资源站</p>
				<a href="#" class="button-style-1 small">View All</a>
			</div><!--/ .one-fourth-->

			<div class="three-fourth last">
				<ul class="projects-carousel clearfix">
					<c:forEach items="${lastStarList}" var="star">
						<li>
							<a href="/star/view/${star.code }">
								<img src="${star.imgUrl}" style="width:150px;height: 150px;" alt="" />
								<h3 class="title">${star.name }</h3>
								<span class="categories"><fmt:formatDate value="${star.modifyDate }" pattern="yyyy-MM-dd"/> </span>
							</a>
						</li>
					</c:forEach>
				</ul><!--/ .projects-carousel -->			
			</div><!--/ .three-fourth-->
			
		</div><!--/ .container-->
		<div class="clear"></div>
		
		<!-- *************** - end Projects Carousel - *************** -->	

		
		<!-- *************** - Tabs Container - *************** -->	
		
		<div class="content-tabs">
			
			<ul class="tabs-nav clearfix">

				<li class="active"><a href="#tab1">华语</a></li>
				<li><a href="#tab2">欧美</a></li>
				<li><a href="#tab3">日韩</a></li>

			</ul><!--/ .tabs-nav -->

			<div class="tabs-container">

				<div class="tab-content" id="tab1">
					<c:forEach items="${hyStarList }" var="star" varStatus="s">
						<div class="one-fourth">
							<h3><span class="dropcapspot">${s.index+1}</span>${star.name} <br /> ${star.pv}pv</h3>
							<p>${star.note}</p>
							<a href="/star/view/${star.code }" class="button-style-1 small">View</a>
						</div><!--/ .one-fourth-->
					</c:forEach>
				</div><!--/ #tab1-->

				<div class="tab-content" id="tab2">
					<c:forEach items="${omStarList }" var="star" varStatus="s">
						<div class="one-fourth">
							<h3><span class="dropcapspot">${s.index+1}</span>${star.name} <br /> ${star.pv}pv</h3>
							<p>${star.note}</p>
							<a href="/star/view/${star.code }" class="button-style-1 small">View</a>
						</div><!--/ .one-fourth-->
					</c:forEach>
				</div><!--/ #tab2-->

				<div class="tab-content" id="tab3">
					<c:forEach items="${rhStarList }" var="star" varStatus="s">
						<div class="one-fourth">
							<h3><span class="dropcapspot">${s.index+1}</span>${star.name} <br /> ${star.pv}pv</h3>
							<p>${star.note}</p>
							<a href="/star/view/${star.code }" class="button-style-1 small">View</a>
						</div><!--/ .one-fourth-->
					</c:forEach>
				</div><!--/ #tab3-->

			</div><!--/ .tabs-container -->		
		</div><!--/ .content-tabs-->

		<!-- ************** - end Tabs Container - ************** -->

		
		<!-- ************* - BEGIN Testimonials - *************** -->
		
		<div class="testimonials">
			
			<h1>最新资源</h1>
			
			<div class="quote-nav">
				<span class="quote-prev">Previous</span>
				<span class="quote-next">Next</span>
			</div><!--/ .quote-nav-->
			
			<ul class="quoteBox">
				<c:forEach items="${starItemList}" var="si">
					<li>
						<blockquote class="quote-text">
							${si.summary }
							<div class="quote-author"><fmt:formatDate value="${si.createDate }" pattern="yyyy-MM-dd"/></div>
						</blockquote>					
					</li>
				</c:forEach>
			</ul><!--/ .quoteBox-->
			
		</div><!--/ #testimonials -->
		
		<!-- ************* - end Testimonials - *************** -->


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
<script src="${pageContext.request.contextPath}/resource/star/index/sliders/elastslider/jquery.eislideshow.js"></script>
<script src="${pageContext.request.contextPath}/resource/star/index/js/jquery.jcarousel.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/star/index/js/jquery.cycle.all.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/star/index/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/resource/star/index/themeChanger/js/colorpicker.js"></script>
<script src="${pageContext.request.contextPath}/resource/star/index/themeChanger/js/themeChanger.js"></script>
<script src="${pageContext.request.contextPath}/resource/star/index/themeChanger/js/themeChanger.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/hz.sus.core.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/hz.common.js"></script> 
</body>
</html>

