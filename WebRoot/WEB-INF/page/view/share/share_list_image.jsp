<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="其实没事 分享 文章 视频 音乐 资源 下载 图片 大图 美图" />
 	<meta name="keywords" content="其实没事 分享 文章 视频 音乐 资源 下载 图片 大图 美图" />
    <title>图片分享 其实没事（专注分享的网站）</title>
    
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/css/hz.sus.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/Font-Awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/jqueryFileUpload/css/jquery.fileupload.css" rel="stylesheet">  
    <link href="${pageContext.request.contextPath}/resource/jqueryFileUpload/css/jquery.fileupload-ui.css" rel="stylesheet">  
    <link href="${pageContext.request.contextPath}/resource/intense/css/styles.css" type="text/css" rel="stylesheet">
    <style type="text/css">
   		.item { width: 300px;}
    </style>
        
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head> 
<body>
<div class="wrapall">
	<%@ include file="/WEB-INF/page/view/common/head.jsp" %>
    <div class="hz-main" style="padding: 5px 8%;"> 
        <div id="hz-mason">
		  <c:forEach items="${pageView.records}" var="entry" varStatus="s">  
		  <div class="item">
              <div class="thumbnail">
                <img src="${entry.url}" title='<fmt:formatDate value="${entry.createDate}" pattern="yyyy年MM月dd日 HH时mm分ss秒"/>'
                  class="big-image"	data-image="${entry.url}" data-title="大图欣赏" data-caption="www.qishimeishi.com"  />
              	<div class="caption">
              		<div class="row">
              			 <div class="col-xs-4 col-sm-4 col-md-3">
		                 <a  class="btn btn-xs btn-default hz-pop" onclick="javascript:lk('${entry.id}')" id="lkc-${entry.id}" role="button" data-toggle="popover" data-trigger="focus" >
		                       <i class="fa fa-thumbs-up fa-large"></i> 
		                       <span id="lk-${entry.id}" >赞 <c:if test="${entry.lk>0}">(${entry.lk})</c:if> </span>
		                 </a>
              			 </div>
                		
                		 <div class="col-xs-4 col-sm-4 col-md-4 text-right">
	               		 <a class="btn btn-xs btn-default" onclick="comt('${entry.id}')" id="comt-${entry.id }">评论 <c:if test="${entry.comt>0}">(${entry.comt})</c:if> </a>
              			 </div>
                
		                 <div class="col-xs-4 col-sm-4 col-md-5 text-right btn-group" role="group">
					        <button class="btn btn-xs btn-default hz-pop" type="button" id="report-${entry.id}" data-toggle="popover" data-trigger="focus" >More</button>
					        <button class="btn btn-xs btn-default dropdown-toggle" type="button" data-toggle="dropdown"><span class="caret"></span></button>
					        <ul class="dropdown-menu" role="menu" style="min-width: 100px;">
					            <li role="presentation" ><a role="menuitem" tabindex="-1" href="/share/view/${entry.id}">查看</a></li>
					            <li role="presentation" class="divider" ></li>
					            <li role="presentation" ><a role="menuitem" tabindex="-1" href="javascript:report('${entry.id}',1)">举报</a></li>
					        </ul>
			   			 </div>
              		</div>
	                <c:if test="${fn:length(entry.tags)>0}">
	                	<div class="row" style="margin-top: 5px;">
	                	<div class="col-md-12 text-left"><i class="fa fa-tag" style="color:#A8A8A8;"></i>
		                 	<c:forEach items="${entry.tags}" var="tag" varStatus="s">  
		                 		<a href="${pageContext.request.contextPath}/share/image/tag/${tag.id}" class="text-primary">${tag.name}</a>${s.index+1!=fn:length(entry.tags)?",":""}
		             		</c:forEach>
	            	   </div>
	                   </div>
	             	</c:if> 
                </div>
            </div>
		  </div>
    	</c:forEach>
		</div>
    	<div id="next">
		    <a href="/share/image/scroll/2"></a>
		</div>
    </div> <!-- hz-main -->
    
   <div style="display: none" id="footer">
   <%@ include file="/WEB-INF/page/view/common/foot.jsp" %>
   </div>
</div> <!-- container -->

<!-- 模态框 -->
<%@ include file="/WEB-INF/page/view/common/modal.jsp" %>
<%@ include file="/WEB-INF/page/view/common/modal-signin.jsp" %>
<%@ include file="/WEB-INF/page/view/common/modal-comt.jsp" %>

<script src="${pageContext.request.contextPath}/resource/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootswitch/bootstrap-switch.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/ckeditor/ckeditor.js"></script>
<!-- upload -->
<script src="${pageContext.request.contextPath}/resource/jqueryFileUpload/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/jqueryFileUpload/js/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/jqueryFileUpload/js/jquery.fileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/jqueryFileUpload/js/cors/jquery.xdr-transport.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/resource/audiojs/audio.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/resource/masinf/masonry.pkgd.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/masinf/imagesloaded.pkgd.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/masinf/jquery.infinitescroll.js" type="text/javascript"></script>
    
<script src="${pageContext.request.contextPath}/resource/intense/js/intense.js" type="text/javascript"></script>
 
<script src="${pageContext.request.contextPath}/resource/js/hz.sus.common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/hz.sus.ajaxupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/hz.common.js"></script> 
<script type="text/javascript">
var $container = $('#hz-mason').masonry();
$container.imagesLoaded( function() {
	$container.masonry({
	    itemSelector: '.item',
	    gutterWidth: 15,
	    columnWidth: 310,
	    isFitWidth: false 
	});
});

$('#hz-mason').infinitescroll({
  navSelector  : "#next", // 页面分页元素(成功后会被隐藏)           
  nextSelector : "#next a", // 需要点击的下一页链接  
  itemSelector : ".item",   // ajax回来之后，每一项的selecter       
  debug        : true,                        
  loadingImg   : "/resource/image/ajax-loader.gif",          
  loadingText  : "Loading new posts...",      
  animate      : true,      
  extraScrollPx: 50,      //向下滚动的像素，必须开启动态效果
  donetext     : "I think we've hit the end, Jim" ,
  bufferPx     : 40,     //提示语展现的时长，数字越大，展现时间越短
  dataType	   : 'html',
  errorCallback: function(){
	  $("#footer").css('display','block'); 
  }, 
  localMode    : true
  },function(arrayOfNewElems,opt){//成功后执行的操作 
	 // $('#hz-mason').masonry('appended', arrayOfNewElems);
	   var $newElems = $(arrayOfNewElems);
       $('#hz-mason').masonry('appended', $newElems);
       $container.imagesLoaded( function() {
    		$container.masonry({
    		    itemSelector: '.item',
    		    gutterWidth: 15,
    		    columnWidth: 310,
    		    isFitWidth: true 
    		});
    	});
       /**再次调用 图片放大功能 **/
       var page= opt.state.currPage; //得到当前是第几页
	   var elements = document.querySelectorAll( '.page-'+page+' .big-image' );
	   Intense( elements );
	  //console.log(arrayOfNewElems);
	 // var data = $(arrayOfNewElems);
	  //console.log(data);
	 // $(data).appendTo(".hz-foot"); 
});
//图片放大
var elements = document.querySelectorAll( '.big-image' );
Intense( elements );
</script>
</body>
</html>