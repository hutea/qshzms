<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
<div>第${pageView.currentPage}页</div>
 <div class="my-gallery" itemscope itemtype="http://schema.org/ImageGallery">
 		<c:forEach items="${pageView.records }" var="entry">
	        <figure itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
	            <a href="${entry.linkurl}" itemprop="contentUrl"
	               data-size="${entry.width}x${entry.height}">
	                <img src="${entry.t_url}" itemprop="thumbnail"
	                     alt="${entry.tagDes }"/>
	            </a>
	            <figcaption itemprop="caption description">${entry.tagDes }</figcaption>
	        </figure>
 		</c:forEach>
</div>

<c:if test="${pageView.currentPage<pageView.totalPage}">
	<div id="loadImageNext" ><a style="font-size: 16px;color:white;" href="javascript:loadImage(${pageView.currentPage+1})">下一页</a></div>
</c:if>
