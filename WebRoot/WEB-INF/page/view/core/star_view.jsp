<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>${star.name} 资源站</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/star/view/css/normalize.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/star/view/css/font-awesome.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/star/view/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/star/view/css/templatemo-style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/photoswipe/photoswipe.css"> 
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/photoswipe/default-skin/default-skin.css"> 
        
        <script src="${pageContext.request.contextPath}/resource/star/view/js/vendor/modernizr-2.6.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/photoswipe/photoswipe.min.js"></script> 
		<script src="${pageContext.request.contextPath}/resource/photoswipe/photoswipe-ui-default.min.js"></script> 
		    <style type="text/css">
        .my-gallery {
            width: 100%;
            float: left;
        }

        .my-gallery img {
            width: 100%;
            height: auto;
        }

        .my-gallery figure {
            display: block;
            float: left;
            margin: 0 5px 5px 0;
            width: 150px;
        }

        .my-gallery figcaption {
            display: none;
        }

        .img-area{
            margin-left: -30px;
            padding: 16px 30px;
            width: 100%;
            -webkit-box-sizing: content-box;
            -moz-box-sizing: content-box;
            box-sizing: content-box;
            background-color: #f8f8f8;
            height: 600px;
            width: 500px;
        }
    </style>
    </head>
    <body onload="loadImage(1)">
        <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
        
        <div class="site-bg"></div>
        <div class="site-bg-overlay"></div>

        <!-- TOP HEADER -->
        <div class="top-header">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <p class="phone-info"><a href="/">明星资源站</a>(QQ群2群：104967927)</a></p>
                    </div>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div class="social-icons">
                            <ul>
                                <li>推荐：</li>
                                <c:forEach items="${stars}" var="entry">
                               		<li><a href="/star/view/${entry.code }" target="_blank" >${entry.name}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- .top-header -->


        <div class="visible-xs visible-sm responsive-menu">
            <a href="#" class="toggle-menu">
                <i class="fa fa-bars"></i> Show Menu
            </a>
            <div class="show-menu">
                <ul class="main-menu">
                    <li>
                        <a class="show-1 active homebutton" href="#"><i class="fa fa-home"></i>首页</a>
                    </li>
                    <c:forEach items="${star.categorys}" var="category">
                    	<li>
                        	<a class="show-${category.id} mycategory" cid="${category.id }"  style="background: ${category.background}"  href="#" >
                        	<i class="${category.icon }"></i>${category.name}</a>
                    	</li>
		            </c:forEach>
                    
                    <li>
                        <a class="show-3 projectbutton" href="#"><i class="fa fa-camera"></i>相册</a>
                    </li>
                    <li>
                        <a class="show-5 contactbutton" href="#"><i class="fa fa-envelope"></i>留言</a>
                    </li>
                </ul>
            </div>
        </div>
		<div class="copyrights"> 
			 <p>Copyright &copy; qishimeishi.com 版权所有 蜀ICP21202414号 
                        <a href="http://www.qishiemeishi.com/" target="_blank" title="明星资源站">明星资源站</a> 
                        <a href="#" title="明星资源 ">${star.name}</a></p>
        </div>
        <div class="container" id="page-content">
            <div class="row">
				<div class="col-md-12 hidden-sm">
                    <nav id="nav" class="main-navigation hidden-xs hidden-sm">
                        <ul class="main-menu">
                            <li >
                                <a class="show-1 active homebutton" href="#"><i class="fa fa-home"></i>首页</a>
                            </li>
                            <li >
                                <a class="show-3 projectbutton" href="#"><i class="fa fa-camera"></i>相册</a>
                            </li>
                            <li>
                                <a class="show-5 contactbutton" href="#"><i class="fa fa-envelope"></i>留言</a>
                            </li>
                            <c:forEach items="${star.categorys}" var="category">
		                    	<li>
		                        	<a class="show-${category.id} mycategory" cid="${category.id }" style="background: ${category.background}"  href="#">
		                        	<i class="${category.icon }"></i>${category.name}</a>
		                    	</li>
		                    </c:forEach>
                        </ul>
                    </nav>
                </div>
                
                
                <div class="col-md-12 col-sm-12 content-holder">
                    <!-- CONTENT -->
                    <div id="menu-container" style="min-height: 500px;">
                        <div id="menu-1" class="homepage home-section text-center">
                            <div class="welcome-text">
                                <h2><strong>${star.name}</strong></h2>
                                <img src="${star.imageUrl }" class="img-responsive">
                                <p style="text-align: left;text-indent: 2em">${star.summary }</p>
                            </div>
                        </div>
						
						<c:forEach items="${dataMap}" var="map">
                        <div id="menu-${map.key}" class="content m-${map.key}">
                            <div class="row">
                            	<c:forEach items="${map.value}" var="si">
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <div class="box-content profile">
                                        <h3 class="widget-title"><fmt:formatDate value="${si.showDate}" pattern="yyyy年MM月dd日"/></h3>
                                        <div class="profile-thumb">
                                            <img src="${si.showImage}" alt="">
                                        </div>
                                        <div class="profile-content">
                                            <h3 class="profile-name">${si.name }</h3>
                                            <span class="profile-role" title="分享时间">
                                            <fmt:formatDate value="${si.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </span>
                                            <p>${si.summary}</p>
                                        </div>
                                        <div style="margin-top: 10px;">
											<c:if test="${fn:length(si.baiduDownUrl)>5 }">
											<a href="javascript:void(0)" onclick="javascript:downloadClick('${si.id}')"  >云下载(${si.baiduDownNum}次)</a>
											</c:if>
											<c:if test="${fn:length(si.playUrl)>5 }">
											<a href="javascript:void(0)" onclick="javascript:playClick('${si.id}')">播放(${si.playNum}次)</a>
											</c:if>
											<div id="download-content-${si.id}" style="display: none;margin: 10px 0px;color: #132587;"></div>
											<div id="play-content-${si.id}" style="display: none;"></div>
                                        </div>
                                    </div>
                                </div>
                            	</c:forEach>
                            </div>
                        </div>
						</c:forEach>
						
                        <div id="menu-3" class="content gallery-section">
                            <input type="hidden" id="starId" value="${star.id}">
                            
                            <%@ include file="/WEB-INF/page/view/core/star_imagePlugin.jsp" %>  
                        </div>

                        <div id="menu-4" class="content contact-section">
                        	<div><i class="fa fa-comment fa-4x"></i></div>
                        	<div class="col-md-8">
								<div id="SOHUCS" sid="${star.id}"></div>
								<script charset="utf-8" type="text/javascript" src="http://changyan.sohu.com/upload/changyan.js" ></script>
								<script type="text/javascript">
								    window.changyan.api.config({
								        appid: 'cyrYRsxhH',
								        conf: 'prod_fe34c82a093404d36eaa4592faaad6e3'
								    });
								</script>   
							</div>
                        	<div class="col-md-4">
							</div>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>

        <!-- SITE-FOOTER -->
        <div class="site-footer">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 text-center">
                        <p>Copyright &copy; qishimeishi.com 版权所有 蜀ICP21202414号 
                        <a href="/star/index" title="明星资源站">明星资源站</a> 
                        <a href="/star/view/${star.code}" title="">${star.name}</a></p>
                    </div>
                </div>
            </div>
        </div> <!-- .site-footer -->

        <script src="${pageContext.request.contextPath}/resource/star/view/js/vendor/jquery-1.10.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/resource/star/view/js/plugins.js"></script>
        <script src="${pageContext.request.contextPath}/resource/star/view/js/main.js"></script>
        <script src="${pageContext.request.contextPath}/resource/photoswipe/myfun.js"></script>
		<!-- templatemo 439 rectangle -->
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
			
			function loadImage(page){
				var starId = document.getElementById("starId").value;
				$.post("/star/load/image", {
					page : page,
					starId:starId
				}, function(data) {
					$("#loadImageNext").remove();
					$("#menu-3").append(data);
					initPhotoSwipeFromDOM('.my-gallery');
				});
			}
		</script>
    </body>
</html>
