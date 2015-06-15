<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
<input type="hidden" value="${page}" id="page" />
 <c:forEach items="${list}" var="entry" varStatus="s">  
		  <div class="item page-${page}">
              <div class="thumbnail">
                <img src="${entry.url}" title='<fmt:formatDate value="${entry.createDate}" pattern="hh:mm:ss dd/MM"/>' 
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
