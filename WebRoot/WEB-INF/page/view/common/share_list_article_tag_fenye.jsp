<%@ page language="java" pageEncoding="UTF-8"%>
<c:if test="${pageView.totalrecord>0}">
	<nav  class="text-center">
	<ul class="pagination  pagination-md">
		<c:if test="${(pageView.currentPage-1)<1}">
			<li class="disabled">
				<a href='#'>Prev</a>
			</li>
		</c:if>
		<c:if test="${(pageView.currentPage-1)>=1}">
			<li>
				<a href='${pageContext.request.contextPath}/share/article/tag/${tagID}/page/${pageView.currentPage-1}'>Prev</a>
			</li>
		</c:if>
		<li>
			<a href='${pageContext.request.contextPath}/share/article/tag/${tagID}/page/1' class="re" ${pageView.currentPage==1?'style="background-color: #efefef;"':''} >1</a>
		</li>
		<c:if test="${pageView.pageIndex.startindex>2}">
			<li>
				<a href='#'>...</a>
			</li>
		</c:if>
		<c:forEach begin="${pageView.pageIndex.startindex}"
			end="${pageView.pageIndex.endindex}" var="per">
			<li>
				<a href='${pageContext.request.contextPath}/share/article/tag/${tagID}/page/${per}' ${pageView.currentPage==per?'style="background-color: #efefef;"':''}>${per}</a>
			</li>
		</c:forEach>

		<c:if test="${pageView.pageIndex.endindex<pageView.totalPage-1}">
			<li>
				<a href='#'>...</a>
			</li>
		</c:if>

		<c:if test="${pageView.totalPage>=2}">
			<li>
				<a href='${pageContext.request.contextPath}/share/article/tag/${tagID}/page/${pageView.totalPage}' ${pageView.currentPage==pageView.totalPage?'style="background-color: #efefef;"':''} >${pageView.totalPage}</a>
			</li>
		</c:if>

		<c:if test="${(pageView.currentPage+1)>pageView.totalPage}">
			<li class="disabled">
				<a href='#'>Next</a>
			</li>
		</c:if>
		<c:if test="${(pageView.currentPage+1)<=pageView.totalPage}">
			<li>
				<a href='${pageContext.request.contextPath}/share/article/tag/${tagID}/page/${pageView.currentPage+1}'>Next</a>
			</li>
		</c:if>
	</ul>
	</nav>
</c:if>



