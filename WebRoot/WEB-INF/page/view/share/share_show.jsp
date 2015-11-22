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
    <title>${share.title}  其实没事（专注分享的网站）</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/css/hz.sus.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/Font-Awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/jqueryFileUpload/css/jquery.fileupload.css" rel="stylesheet">  
    <link href="${pageContext.request.contextPath}/resource/jqueryFileUpload/css/jquery.fileupload-ui.css" rel="stylesheet">  
    <link href="${pageContext.request.contextPath}/resource/bootswitch/bootstrap-switch.min.css" rel="stylesheet">  
    <link href="${pageContext.request.contextPath}/resource/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
       
    
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
    		<div class="hz-per hz-per-file">
                <div class="row hz-row-title">
                    <div class="col-md-2">
                    	<c:if test="${share.category==1}"><i class="icon-cloud-download icon-3x" style="color: #369;" ></i></c:if>
                    	<c:if test="${share.category==2}"><i class="icon-list-alt icon-3x" style="color: #138;" ></i></c:if>
                    	<c:if test="${share.category==3}"><i class="icon-music icon-2x" style="color: #2a5;" ></i></c:if>
                    	<c:if test="${share.category==4}"><i class="icon-film icon-2x" style="color: #369;" ></i></c:if>
                    	<c:if test="${share.category==5}"><i class="icon-picture icon-1x" style="color: #369;" ></i></c:if>
                    </div>
                    <div class="col-xs-10 col-sm-10 col-md-8 h3 text-center">${share.title}</div>
                    <div class="col-xs-2 col-sm-2 col-md-2">
                    	<p>
                    	<c:forEach begin="1" end="${share.star}" step="1"  >  
	                    	<i class="icon-star pull-right" style="color: red;" ></i>
                    	</c:forEach>
                    	</p>
                    </div> 
                </div> 
                <div class="row hz-row-tag">
                    <div class="col-xs-8 col-sm-9 col-md-9"><c:if test="${fn:length(share.tags)>0}"><i class="icon-tags"></i></c:if> 
                    	<c:forEach items="${share.tags}" var="tag" varStatus="s">  
	                		<c:if test="${s.index%5==0}"> <a href="${pageContext.request.contextPath}/share/tag/${tag.id}" class="btn btn-primary btn-xs">${tag.name}</a></c:if>
	                		<c:if test="${s.index%5==1}"> <a href="${pageContext.request.contextPath}/share/tag/${tag.id}" class="btn btn-warning btn-xs">${tag.name}</a></c:if>
	                		<c:if test="${s.index%5==2}"> <a href="${pageContext.request.contextPath}/share/tag/${tag.id}" class="btn btn-info btn-xs">${tag.name}</a></c:if>
	                		<c:if test="${s.index%5==3}"> <a href="${pageContext.request.contextPath}/share/tag/${tag.id}" class="btn btn-danger btn-xs">${tag.name}</a></c:if>
	                		<c:if test="${s.index%5==4}"> <a href="${pageContext.request.contextPath}/share/tag/${tag.id}" class="btn btn-default btn-xs" >${tag.name}</a></c:if>
                		</c:forEach>
                    </div>
                    <div class="col-xs-4 col-sm-3 col-md-3 time-col " title='<fmt:formatDate value="${share.createDate}" pattern="HH时mm分ss秒"/>'><fmt:formatDate value="${share.createDate}" pattern="yyyy年MM月dd日"/></div>
                </div>
                <div class="row hz-row-content">
                	<c:if test="${share.category==1}">
                    <p class="col-md-12">
                        <span class="text-info"><strong>下载地址：</strong></span> <a href="${share.url}" target="_blank">${share.url}</a>
                    </p>
                	</c:if>
                	<c:if test="${share.category==4}">
                    <p class="col-md-12">
                        ${share.url}
                    </p>
                	</c:if>
                    <div class="col-md-12">
                        <p id="share-content-${share.id}">
                        	<c:if test="${share.view}">${share.content}</c:if>
                        	<c:if test="${!share.view}">
                        		<c:if test="${share.comment}">${share.content}</c:if>
                        		<c:if test="${!share.comment}">分享者对内容设置【评论可见】</c:if>
                        	</c:if>
                        </p>
                        <c:if test="${share.category==5}">
                        	<p class="text-left"><img src="${share.url}"  class="img-thumbnail"/></p>
                        </c:if>
                    </div>
                </div>
                 <div class="row hz-row-bottom">
                    <div class="col-xs-4 col-sm-4 col-md-3">
                        <a  class="btn btn-primary hz-pop" onclick="javascript:lk('${share.id}')" id="lkc-${share.id}" role="button" data-toggle="popover" data-trigger="focus" >
                            <i class="icon-thumbs-up icon-large"></i> 
                            <span id="lk-${share.id}" >赞 <c:if test="${share.lk>0}">(${share.lk})</c:if> </span>
                        </a>
                    </div> 
                    <div class="col-xs-4 col-sm-4 col-md-3">
                   	 <a class="btn btn-primary" onclick="comt('${share.id}')" id="comt-${share.id }">评论 <c:if test="${share.comt>0}">(${share.comt})</c:if> </a>
                    </div> 
                    
                    <div class="col-xs-4 col-sm-4 col-md-3 col-md-push-3 text-right">
	                    <div class="btn-group" role="group">
					        <button class="btn btn-primary hz-pop" type="button" id="report-${share.id}" data-toggle="popover" data-trigger="focus"  >More</button>
					        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"><span class="caret"></span></button>
					        <ul class="dropdown-menu" role="menu" style="min-width: 100px;">
					            <li role="presentation" ><a  role="menuitem" tabindex="-1" href="javascript:report('${share.id}',1)">举报</a></li>
					        </ul>
					    </div>
                    </div>
                </div>
            </div>
            
            <div class="hz-comts" >
            	<c:forEach items="${pageView.records}" var="entry" varStatus="s">  
            		<div class="row hz-comt-per ${entry.reported>reportedMax?'sr-only':''}"> 
		            	<div class="col-xs-3 col-sm-3 col-md-2">
		            		<div class="row">
		            			<div class="col-xs-8 col-md-8 col-md-6">
		            				<img class="img-thumbnail" src="/resource/image/headphoto-lv.png" />
		            			</div>
		            		</div>
		            		<div class="row">
		            			<div class="col-md-12">
		            				<a class="hz-pop" href="javascript:report('${entry.id}',2)" id="report-comt-${entry.id}" data-toggle="popover" data-trigger="focus" >举报</a>
		            			</div>
		            		</div> 
		            	</div>
		            	<div class="col-xs-9 col-sm-9 col-md-10">
			            	<div class="row hz-row-comt">
			            			<div class="col-md-12 text-right">
			            				<span class="text-info">${entry.user.nickname}</span>
			            				<span class="text-muted" title='<fmt:formatDate value="${entry.cmtTime}" pattern="HH:mm:ss"/>'><fmt:formatDate value="${entry.cmtTime}" pattern="yyyy年MM月dd日"/></span>
			            				<span class="text-primary">${(pageView.currentPage-1)*pageView.maxresult+s.index+1}F</span>
			            			</div>
			            	</div>
			            	<div class="row hz-row-comt">
			            			<div class="col-md-10"><p>${entry.content}</p></div>
			            			<div class="col-md-2"></div> 
			            	</div>
			            </div>
            		</div>
            	</c:forEach>
				<c:if test="${pageView.totalPage>pageView.currentPage}">
					<div class="text-right" id="comt-load-more">
						<a href="javascript:loadMoreComts('${share.id}','${pageView.currentPage+1 }')">加载更多</a>
					</div>
				</c:if>
            </div>
            
        </div> <!-- hz-core -->

  		<%@ include file="/WEB-INF/page/view/common/hz-right.jsp" %>
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
    
<script src="${pageContext.request.contextPath}/resource/js/hz.sus.common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/hz.sus.ajaxupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/hz.common.js"></script> 

</body>
</html>