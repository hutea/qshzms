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
    <title>最新分享 其实没事（专注分享的网站）</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/css/hz.sus.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/Font-Awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/jqueryFileUpload/css/jquery.fileupload.css" rel="stylesheet">  
    <link href="${pageContext.request.contextPath}/resource/jqueryFileUpload/css/jquery.fileupload-ui.css" rel="stylesheet">  
    <link href="${pageContext.request.contextPath}/resource/bootswitch/bootstrap-switch.min.css" rel="stylesheet">  
    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <%@ include file="/WEB-INF/page/view/common/baidutongji.jsp" %>  
</head> 
<body>
<div class="wrapall">
	<%@ include file="/WEB-INF/page/view/common/head.jsp" %>
    <div class="hz-main"> 
    	<div class="col-md-1 hidden-xs hidden-sm"></div>
        <div class="hz-core col-xs-12 col-sm-12 col-md-7">
    	<c:forEach items="${pageView.records}" var="entry" varStatus="s">  
    		<div class="hz-per hz-per-file">
                <div class="row hz-row-title">
                    <div class="col-md-2">
                    	<c:if test="${entry.category==1}"><a href="${entry.url}" target="_blank"><i class="fa fa-cloud-download fa-lg" style="color: #369;" ></i></a></c:if>
                    	<c:if test="${entry.category==2}"><i class="fa fa-list-alt fa-lg" style="color: #138;" ></i></c:if>
                    	<c:if test="${entry.category==3}"><i class="fa fa-music fa-lg" style="color: #2a5;" ></i></c:if>
                    	<c:if test="${entry.category==4}"><i class="fa fa-video-camera fa-lg" style="color: #369;" ></i></c:if>
                    	<c:if test="${entry.category==5}"><i class="fa fa-camera-retro fa-lg" style="color: #369;" ></i></c:if>
                    </div>
                    <div class="col-xs-10 col-sm-10 col-md-8 h3 text-center"><a href="/share/view/${entry.id}">${entry.title}</a></div>
                    <div class="col-xs-2 col-sm-2 col-md-2">
                    	<p>
                    	<c:forEach begin="1" end="${entry.star}" step="1"  >  
	                    	<i class="fa fa-star pull-right" style="color: red;" ></i>
                    	</c:forEach>  
                    	</p>
                    </div>
                </div> 
                <div class="row hz-row-tag">
                    <div class="col-xs-8 col-sm-9 col-md-9"><c:if test="${fn:length(entry.tags)>0}"><i class="fa fa-tag" style="color:#368;"></i></c:if> 
                    	<c:forEach items="${entry.tags}" var="tag" varStatus="s">  
	                		<c:if test="${s.index%5==0}"> <a href="${pageContext.request.contextPath}/share/tag/${tag.id}" class="btn btn-primary btn-xs">${tag.name}</a></c:if>
	                		<c:if test="${s.index%5==1}"> <a href="${pageContext.request.contextPath}/share/tag/${tag.id}" class="btn btn-warning btn-xs">${tag.name}</a></c:if>
	                		<c:if test="${s.index%5==2}"> <a href="${pageContext.request.contextPath}/share/tag/${tag.id}" class="btn btn-info btn-xs">${tag.name}</a></c:if>
	                		<c:if test="${s.index%5==3}"> <a href="${pageContext.request.contextPath}/share/tag/${tag.id}" class="btn btn-danger btn-xs">${tag.name}</a></c:if>
	                		<c:if test="${s.index%5==4}"> <a href="${pageContext.request.contextPath}/share/tag/${tag.id}" class="btn btn-default btn-xs" >${tag.name}</a></c:if>
                		</c:forEach>
                    </div>
                    <div class="col-xs-4 col-sm-3 col-md-3 time-col" title='<fmt:formatDate value="${entry.createDate}" pattern="HH时mm分ss秒"/>'><fmt:formatDate value="${entry.createDate}" pattern="yyyy年MM月dd日"/></div>
                </div>
                <div class="row hz-row-content">
                	<c:if test="${entry.category==3 && entry.url!=null}">
	                    <p class="col-md-12">
	                        <audio src="${entry.url}" preload="auto" />
	                    </p>
                	</c:if>
                	<c:if test="${entry.category==4 && entry.url!=null}">
	                    <p class="col-md-12">
	                        ${entry.video}
	                    </p>
                	</c:if>
                    <div class="col-md-12">
                        <div id="share-content-${entry.id}" class="col-content">
                        	<c:if test="${entry.view}">
	                        	${entry.sumary}
	                        	<c:if test="${entry.loadMore}">
                        				<div class="text-right content-link"><a href="/share/view/${entry.id}">查看全部</a></div>
                        		</c:if>
                        	</c:if>
                        	<c:if test="${!entry.view}">
                        		<c:if test="${entry.comment}"><!-- 用户评论过 -->
                        			${entry.sumary}  
                        			<c:if test="${entry.loadMore}">
                        				<div  class="text-right content-link"><a href="/share/view/${entry.id}">查看全部</a></div>
                        			</c:if>
                        		</c:if>
                        		<c:if test="${!entry.comment}"><span title="">分享者对【内容】设置了评论可见，点击评论后隐藏的内容便会出现。</span></c:if>
                        	</c:if>
                        </div>
                        <c:if test="${entry.category==5}">
                        	<p class="text-left"><img src="${entry.url}"  class="img-thumbnail"/></p>
                        </c:if>
                    </div>
                </div>
                 <div class="row hz-row-bottom">
                    <div class="col-xs-4 col-sm-4 col-md-3">
                        <a  class="btn btn-primary btn-sm hz-pop" onclick="javascript:lk('${entry.id}')" id="lkc-${entry.id}" role="button" data-toggle="popover" data-trigger="focus" >
                            <i class="fa fa-thumbs-up fa-large"></i> 
                            <span id="lk-${entry.id}" >赞 <c:if test="${entry.lk>0}">(${entry.lk})</c:if> </span>
                        </a>
                    </div> 
                    <div class="col-xs-4 col-sm-4 col-md-3">
                   	 <a class="btn btn-primary btn-sm" onclick="comt('${entry.id}')" id="comt-${entry.id }">评论 <c:if test="${entry.comt>0}">(${entry.comt})</c:if> </a>
                    </div> 
                    
                    <div class="col-xs-4 col-sm-4 col-md-3 col-md-push-3 text-right">
	                    <div class="btn-group" role="group">
					        <button class="btn btn-primary btn-sm hz-pop" type="button" id="report-${entry.id}" data-toggle="popover" data-trigger="focus" >More</button>
					        <button class="btn btn-primary btn-sm dropdown-toggle" type="button" data-toggle="dropdown"><span class="caret"></span></button>
					        <ul class="dropdown-menu" role="menu" style="min-width: 100px;">
					            <li role="presentation" ><a role="menuitem" tabindex="-1" href="/share/view/${entry.id}">查看</a></li>
					            <li role="presentation" class="divider" ></li>
					            <li role="presentation" ><a role="menuitem" tabindex="-1" href="javascript:report('${entry.id}',1)">举报</a></li>
					        </ul>
					    </div>
                    </div>
                </div>
            </div>
    	</c:forEach>
            
            <%@ include file="/WEB-INF/page/view/common/share_list_fenye.jsp" %>
            
        </div> <!-- hz-core -->

		<%@ include file="/WEB-INF/page/view/common/hz-right.jsp" %><!-- hz-right -->
		<div class="col-md-1 hidden-xs hidden-sm"></div>
    </div> <!-- hz-main -->
    
   <%@ include file="/WEB-INF/page/view/common/foot.jsp" %>
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
    
<script src="${pageContext.request.contextPath}/resource/js/hz.sus.common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/hz.sus.ajaxupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/hz.common.js"></script> 

</body>
</html>