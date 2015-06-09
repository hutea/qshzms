<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/Font-Awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/css/hz.ucenter.css" rel="stylesheet">
    <title>后台管理页面</title>
</head>
<body>
    <%@ include file="/WEB-INF/page/view/my/user-center-head.jsp" %>

    <div class="mybody container">
        <div class="row">
    		<%@ include file="/WEB-INF/page/view/my/user-center-menu.jsp" %>
            

            <div class="col-md-9">
                <ol class="breadcrumb">
                    <li><a href="/my/center">个人中心</a></li>
                    <li><a href="#">我的分享</a></li>
                </ol>
                <div class="mytable table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr class="bg-primary">
                                <th>#</th>
                                <th>标题</th>
                                <th>分享时间</th>
                                <th>分享类型</th>
                                <th>被赞次数</th>
                                <th>评论次数</th>
                                <th>可见度</th>
                                <th>状态</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:if test="${fn:length(pageView.records)<=0}"><tr><td colspan="8" class="h3 text-primary text-center">您还没有开始分享<i class="fa fa-share-alt-square"></i></td></tr></c:if>
                        	<c:forEach items="${pageView.records}" var="entry" varStatus="s">
                            <tr>
                                <td>${s.index+1}</td>
                                <td>
                                	<span style="width:120px;display:block;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">
                                	<a href="/share/view/${entry.id }" title="点击查看：${entry.title}" target="_blank">${entry.title} ${entry.category==5?"图片":""}</a>
                                	</span>
                                </td>
                                <td title='<fmt:formatDate value="${entry.createDate}" pattern="HH:mm:ss"/>'><fmt:formatDate value="${entry.createDate}" pattern="yyyy年MM月dd日"/></td>
                                <td>
                                	<c:if test="${entry.category==1}">下载分享</c:if>
									<c:if test="${entry.category==2}">文章分享</c:if>
									<c:if test="${entry.category==3}">音乐分享</c:if>
									<c:if test="${entry.category==4}">视频分享</c:if>
									<c:if test="${entry.category==5}">图片分享</c:if>
                                </td>
                                <td>${entry.lk }</td>
                                <td>${entry.comt }</td>
                                <td>${entry.view?"直接可见":"评论可见"}</td>
                                <td>
                                	<c:if test="${entry.reported>shareReportedMax}">
                                		<span class="text-danger">因举报被屏蔽</span>
                                	</c:if>
                                	<c:if test="${entry.reported<=shareReportedMax}">
                                		<c:if test="${entry.visible}"><span class="text-success">正常显示</span></c:if>
                                		<c:if test="${!entry.visible}"><span class="text-danger">官方屏蔽</span></c:if>
                                	</c:if>
                                </td>
                            </tr>
                        	</c:forEach>
                        </tbody>
                    </table>
                    <nav class="pull-right">
                       <c:if test="${pageView.totalrecord>0}">
							<ul class="pagination  pagination-md">
								<c:if test="${(pageView.currentPage-1)<1}">
									<li class="disabled">
										<a href='#'>Prev</a>
									</li>
								</c:if>
								<c:if test="${(pageView.currentPage-1)>=1}">
									<li>
										<a href='${pageContext.request.contextPath}/my/share?page${pageView.currentPage+1}'>Prev</a>
									</li>
								</c:if>
								<li>
									<a href='${pageContext.request.contextPath}/my/share?page=1' class="re" ${pageView.currentPage==1?'style="background-color: #efefef;"':''} >1</a>
								</li>
								<c:if test="${pageView.pageIndex.startindex>2}">
									<li>
										<a href='#'>...</a>
									</li>
								</c:if>
								<c:forEach begin="${pageView.pageIndex.startindex}"
									end="${pageView.pageIndex.endindex}" var="per">
									<li>
										<a href='${pageContext.request.contextPath}/my/share?page=${per}' ${pageView.currentPage==per?'style="background-color: #efefef;"':''}>${per}</a>
									</li>
								</c:forEach>
						
								<c:if test="${pageView.pageIndex.endindex<pageView.totalPage-1}">
									<li>
										<a href='#'>...</a>
									</li>
								</c:if>
						
								<c:if test="${pageView.totalPage>=2}">
									<li>
										<a href='${pageContext.request.contextPath}/my/share?page=${pageView.totalPage}' 	${pageView.currentPage==pageView.totalPage?'style="background-color: #efefef;"':''} >${pageView.totalPage}</a>
									</li>
								</c:if>
						
								<c:if test="${(pageView.currentPage+1)>pageView.totalPage}">
									<li class="disabled">
										<a href='#'>Next</a>
									</li>
								</c:if>
								<c:if test="${(pageView.currentPage+1)<=pageView.totalPage}">
									<li>
										<a href='${pageContext.request.contextPath}/my/share?page=${pageView.currentPage+1}'>Next</a>
									</li>
								</c:if>
							</ul>
						</c:if>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <div class="myfoot">
      <%@ include file="/WEB-INF/page/view/common/foot.jsp" %>
    </div>

<script src="${pageContext.request.contextPath}/resource/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/hz.ucenter.js"></script>
</body>
</html>