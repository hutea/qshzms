<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
 <!DOCTYPE html>
<!--[if lte IE 8]>              <html class="ie8 no-js" lang="en">     <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <html class="not-ie no-js" lang="en">  <!--<![endif]-->
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	
	<title>明星资源站 留言</title>
	
	<meta name="description" content="">
	<meta name="author" content="">
	
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resource/star/index/images/favicon.ico">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/star/index/css/style.css" media="screen" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/star/index/sliders/elastslider/elastic.css" media="screen" />
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

		<!-- *************** - Projects Carousel - *************** -->	
		<div class="page-title clearfix">
			<h2>留言</h2>
		</div><!--/ .page-title-->
		
		<div class="container">
			<div class="one-fourth">
				<p>
					明星资源站2群：104967927
				</p>
				<p>明星群联盟 </p>
			</div><!--/ .one-fourth-->

			<div class="three-fourth last">
				
			</div><!--/ .three-fourth-->
			
			<div>
				<div id="SOHUCS" sid="star-contact"></div>
				<script charset="utf-8" type="text/javascript" src="http://changyan.sohu.com/upload/changyan.js" ></script>
				<script type="text/javascript">
				    window.changyan.api.config({
				        appid: 'cyrYRsxhH',
				        conf: 'prod_fe34c82a093404d36eaa4592faaad6e3'
				    });
				</script>   
			</div><!--/ .one-fourth-->
			
		</div><!--/ .container-->
		<div class="clear"></div>
		
		<!-- *************** - end Projects Carousel - *************** -->	


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

</body>
</html>

