<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>

<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js ie6 oldie" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8 oldie" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="de"> <!--<![endif]-->
<head>
<meta charset="utf-8">

<!-- scaling not possible (for smartphones, ipad, etc.) -->
<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" />

<title>RAWA - A responsive &amp; minimal html template - Home - shared on mycodes.net</title>

<link href="${pageContext.request.contextPath}/resource/customer/qq/css/fonts.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resource/customer/qq/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resource/customer/qq/css/isotope.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resource/customer/qq/css/mqueries.css" rel="stylesheet" type="text/css" media="screen" />

<script src="files/js/jquery.modernizr.min.js"></script>

</head>
<body>
<div id="page">

<section id="top">
    <div class="top_inner wrapper clearfix">
            
            <div class="widget left_float">
            	<h6>站点名称</h6>			
                <div class="textwidget">关于本站的一些简要说明</div>
			</div>                
            
            
            <div class="socialmedia right_float" title="友情链接" >
            	<a class="facebook" href="index.html" target="_blank">友链一</a>
                <a class="twitter" href="index.html" target="_blank">友链二</a>
                <a class="dribbble" href="index.html" target="_blank">Dribbble</a>
                <a class="vimeo" href="index.html" target="_blank">Vimeo</a>
                <a class="flickr" href="index.html" target="_blank">Flickr</a>
                <a class="behance" href="index.html" target="_blank">Behance</a>
                <a class="forrst" href="index.html" target="_blank">Forrst</a>
			</div>
        
    </div>
</section> <!-- END top -->


<header id="header" class="wrapper">
	
    <div class="header_top clearfix">
    	<div id="logo" class="left_float">
            <a class="logotype" href="index.html"><img src="${pageContext.request.contextPath}/resource/customer/qq/images/logo.png" alt="Logotype"></a>
        </div>
        
        <nav id="nav" class="right_float">
            <ul>
                <li><a href="${pageContext.request.contextPath}/resource/customer/qq/help.html" class="loadcontent" rel="about">使用帮助</a></li>
                <li><a href="${pageContext.request.contextPath}/resource/customer/qq/shortcodes.html" class="loadcontent" rel="shortcodes">付款方式</a></li>
                <li><a href="${pageContext.request.contextPath}/resource/customer/qq/contact.html" class="loadcontent" rel="contact">联系我们</a></li>
            </ul>
        </nav>
        
    </div>
    
    <div class="header_tagline">
    
    <h3>公告</h3>
    <h5>公告：高价回收各种号码，以及其他项目的合作。提示：本站所有号码均带二代密保</h5>
    
    </div>
    	
     
    
    <div id="loader">
        <img src="${pageContext.request.contextPath}/resource/customer/qq/images/loader.gif" alt="loader" title="Ajax loader" />
    </div>
    
    <div id="close">
    	<a href="">close</a>
    </div>
 	
</header>


     
<!-- ALL CONTENT WILL BE LOADED IN THIS AREA  -->    
<section id="pagecontent">
    <div id="pageloader">        
            <!-- THE LOADED CONTENT WILL SHOW HERE -->
    </div> <!-- END #pageloader -->
</section>    
<!-- ALL CONTENT WILL BE LOADED IN THIS AREA  -->  


<section id="main">
    <div class="main_inner wrapper clearfix">        
        <div id="masonry" class="entries clearfix">                
                <div class="masonry_item entry post">
                    <div class="img_holder">
                            QQ:1234567890
                        </div>
                        
                    <div class="entry-headline">
                        <div class="entry-title"><h5>价格：<em style="color: red;">￥50.00</em></h5></div>

                    </div>
                        
                    <div class="entry-info">
                        <p>
                            Praesent dapibus, neque id cursus faucibus, tortor neque egestas augue, eu vulputate magna eros eu erat. Aliquam erat volutpat magna eros eu erat.
                        </p>
                    </div>
                </div>
                
                
                <div class="masonry_item entry work motion">
                    <div class="img_holder">
						QQ:1234567890
                    </div>
                    
                    <div class="entry-headline">
                        <div class="entry-title"><h5>价格：<em style="color: red;">￥50.00</em></h5></div>
                        
                    </div>
                    
                    <div class="entry-info">
                        
                        <p>
                            Praesent dapibus, neque id cursus faucibus, tortor neque egestas augue, eu vulputate magna eros eu erat. Aliquam erat volutpat magna eros eu erat.
                        </p>
                        
                    </div>
                </div>
                                
                
                <div class="masonry_item entry work web">
                    <div class="img_holder">
						QQ:1231231230			
                    </div>
                    
                    <div class="entry-headline">
                        <div class="entry-title"><h5>价格：<em style="color: red;">￥50.00</em></h5></div>
                        
                    </div>
                    
                    <div class="entry-info">
                        <p>
                            Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.
                        </p>
                    </div>
                </div>
                
                
                
                
            </div><!-- END #masonry -->
	</div>       
</section> <!-- END #content -->
    
<footer id="footer">
	<div class="footer_inner wrapper clearfix">
        <div class="left_float">Copyright © 2012 by Rawa. All rights reserved</div>     
        <div class="right_float">Created by <a href="">Spab Rice</a> for <a href="">Themeforest</a></div>  
	</div>           
</footer> <!-- END #footer -->

<section id="bottom">
	<div id="slideup">
    	<a href="" class="">bottom</a>
    </div>
    
	<div class="bottom_inner">
        <div class="wrapper clearfix">
        	<div class="column one_third">
            	<h4>Widget 一</h4>
                <p>
                	内容待定
                </p>
            </div>
            <div class="column one_third">
            	<h4> Widget 二</h4>
                <p>
               	内容待定
                </p>
            </div>
            <div class="column one_third last">
            	<h4>Widget 三</h4>
                <p>
                	内容待定
                </p>
            </div>
        </div>
    </div>
</section>

</div> <!-- END #page -->


<!-- jquery -->
<script src="${pageContext.request.contextPath}/resource/customer/qq/js/jquery-1.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/customer/qq/js/jquery.isotope.min.js"></script>
<script src='${pageContext.request.contextPath}/resource/customer/qq/js/jquery.easing.1.3.js'></script>
<script src='${pageContext.request.contextPath}/resource/customer/qq/js/jquery.easing.compatibility.js'></script>
<script src="${pageContext.request.contextPath}/resource/customer/qq/js/loader.js"></script>
<script src="${pageContext.request.contextPath}/resource/customer/qq/js/script.js"></script>

</body>
</html>

