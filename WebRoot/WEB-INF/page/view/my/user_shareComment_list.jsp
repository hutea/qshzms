<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="其实没事 分享 文章 视频 音乐 资源 下载 图片 大图 美图" />
 	<meta name="keywords" content="其实没事 分享 文章 视频 音乐 资源 下载 图片 大图 美图" />
	<title>我的评论 个人中心 其实没事（专注分享的网站）</title>
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/Font-Awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet">
    
    <style type="text/css">
        .myfoot{
            margin-top: 50px;
            border-top: 1px solid #e5e5e5;
            color: #337ab7;
            padding: 10px 30px;
            text-align: center;
        }
    </style>
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
                    <li><a href="#">评论记录</a></li>
                </ol>
                <div class="list">
                      <c:if test="${fn:length(pageView.records)<=0}"><div class="h3 text-primary text-center">您还没有评论过任何内容</div></c:if>
                        	<c:forEach items="${pageView.records}" var="entry" varStatus="s">
                            <div style="border-bottom: 1px solid #d3d3d3;padding: 3px 5px;">
                                <div class="col-md-2 text-info">
                                <a href="/share/view/${entry.share.id}" target="_blank" title="点击查看分享内容">${s.index+1}#</a>
                                <c:if test="${!entry.visible||entry.reported>shareCommentReportedMax}"><span class="text-danger">因举报被屏蔽</span></c:if>
                                </div>
                                <div class="col-md-10">${entry.content}</div>
                                <div class="row">
                                	<div class="col-md-4 pull-right">  
                                		<fmt:formatDate value="${entry.cmtTime}" pattern="yyyy年MM月dd日 HH时mm分ss秒"/>
                                	</div>
                                </div>
                            </div>
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
										<a href='${pageContext.request.contextPath}/my/comment?page${pageView.currentPage+1}'>Prev</a>
									</li>
								</c:if>
								<li>
									<a href='${pageContext.request.contextPath}/my/comment?page=1' class="re" ${pageView.currentPage==1?'style="background-color: #efefef;"':''} >1</a>
								</li>
								<c:if test="${pageView.pageIndex.startindex>2}">
									<li>
										<a href='#'>...</a>
									</li>
								</c:if>
								<c:forEach begin="${pageView.pageIndex.startindex}"
									end="${pageView.pageIndex.endindex}" var="per">
									<li>
										<a href='${pageContext.request.contextPath}/my/comment?page=${per}' ${pageView.currentPage==per?'style="background-color: #efefef;"':''}>${per}</a>
									</li>
								</c:forEach>
						
								<c:if test="${pageView.pageIndex.endindex<pageView.totalPage-1}">
									<li>
										<a href='#'>...</a>
									</li>
								</c:if>
						
								<c:if test="${pageView.totalPage>=2}">
									<li>
										<a href='${pageContext.request.contextPath}/my/comment?page=${pageView.totalPage}' 	${pageView.currentPage==pageView.totalPage?'style="background-color: #efefef;"':''} >${pageView.totalPage}</a>
									</li>
								</c:if>
						
								<c:if test="${(pageView.currentPage+1)>pageView.totalPage}">
									<li class="disabled">
										<a href='#'>Next</a>
									</li>
								</c:if>
								<c:if test="${(pageView.currentPage+1)<=pageView.totalPage}">
									<li>
										<a href='${pageContext.request.contextPath}/my/comment?page=${pageView.currentPage+1}'>Next</a>
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