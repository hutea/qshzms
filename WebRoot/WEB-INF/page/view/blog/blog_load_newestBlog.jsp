<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
<c:forEach items="${blogs}" var="blog">
	  <div class="recent-single-post">
		  <a href="/blog/view/${blog.id}" class="post-title">${blog.title}</a>
		  <div class="date" title='<fmt:formatDate value="${blog.createDate}" pattern="HH:mm:ss"/>'><fmt:formatDate value="${blog.createDate}" pattern="MM月dd日，yyyy年"/></div>
	  </div>
</c:forEach>
