<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
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
	<a href="javascript:loadMoreComts('${sid}','${pageView.currentPage+1 }')">加载更多</a>
</div>
</c:if>