<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
<c:forEach items="${tags}" var="tag">
	 <a href="/blog/tag/${tag.id}">${tag.name }</a>
</c:forEach>
<a href="/blog/tags/all">...</a> 