<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <meta name="description" content="">
        <title>Chain Responsive Bootstrap3 Admin</title>
        <link href="${pageContext.request.contextPath}/resource/chain/css/style.default.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resource/css/manage.common.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/resource/js/myform.js" type="text/javascript"></script>
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->
        <script type="text/javascript">
    	function del(sid){
			if (confirm('您确定要删除吗')) {
			  $.get("${pageContext.request.contextPath}/manage/share/delete", 
			  {sid:sid},
			  function(data) {
		      	if(data=='true'){
		      		$("#tr_"+sid).css("display","none");
		       	}
			   });
			}
		}
        </script>
    </head>

<body>
	<%@ include file="/WEB-INF/page/common/head.jsp" %>
    <section>
    <div class="mainwrapper">
		<%@ include file="/WEB-INF/page/common/left.jsp" %>
                
        <div class="mainpanel">
             <div class="pageheader">
                 <div class="media">
                     <div class="pageicon pull-left">
                         <i class="fa fa-home"></i>
                     </div>
                     <div class="media-body">
                         <ul class="breadcrumb">
                             <li><a href=""><i class="glyphicon glyphicon-home"></i></a></li>
                             <li><a href="">分享管理</a></li>
                         </ul>
                         <h4>分享管理</h4>
                     </div>
                 </div><!-- media -->
             </div><!-- pageheader -->
                    
             <div class="contentpanel">    
             	 <div class="content" >
				      <div class="row">
				      	<form id="pageList" action="${pageContext.request.contextPath}/manage/share/list" method="post">
				      		<input type="hidden" name="page" value="${page}">
					      	 <div class="text-center" style="margin-bottom: 6px;"> 
	                         	<span class="text-primary hidden" >查询选项 </span>
	                         	<input type="text" style="width: 220px;display: inline-block;" name="queryContent" value="${queryContent}" class="form-control"  placeholder="对标题、内容进行模糊查询"  >
	                       		<input type="button" style="margin: 0 50px;"  class="btn btn-primary"   value="查 询" onclick="confirmQuery()"  >
	                         </div>
				      	</form>
				      	<table class="table table-bordered table-striped">
                           <thead>
                                <tr>
                                    <th>#</th>
                                    <th>分享时间</th>
                                    <th>标题</th>
                                    <th>站点认证</th>
                                    <th>类别</th>
                                    <th>赞</th>
                                    <th>评论数</th>
                                    <th>举报数</th>
                                    <th>星级</th>
                                    <th>可见度</th>
                                    <th>分享者</th>
                                    <th>操作</th>
                                </tr>
                           </thead>
                            <tbody>
                                  <c:forEach items="${pageView.records}" var="entry" varStatus="s">  
	                           	  	<tr id="tr_${entry.id}"">
	                           		 <td>${s.index+1}</td> 
	                           		 <td><fmt:formatDate value="${entry.createDate}" pattern="yyyy-MM-dd hh:mm:ss "/></td> 
	                           		 <td>${entry.title}</td> 
	                           		 <td>${entry.siteauth?'站点认证':'普通分享'}</td> 
	                           		 <td>
										<c:if test="${entry.category==1}">下载</c:if>
										<c:if test="${entry.category==2}">文章</c:if>
										<c:if test="${entry.category==3}">音乐</c:if>
										<c:if test="${entry.category==4}">视频</c:if>
										<c:if test="${entry.category==5}">图片</c:if>
									</td> 
	                           		 <td>${entry.lk}</td> 
	                           		 <td>${entry.comt}</td> 
	                           		 <td>${entry.reported}</td> 
	                           		 <td>${entry.star}</td> 
	                           		 <td>${entry.view?"直接可见":"评论可见"}</td> 
	                           		 <td>${entry.user.nickname}</td> 
	                           		 <td>
	                           		 	<a href="javascript:del('${entry.id}')">删除</a>
	                           		 	<a href="${pageContext.request.contextPath}/manage/share/edit/${entry.id}">修改</a>
	                           		 	<a href="${pageContext.request.contextPath}/manage/share/show/${entry.id}">详细</a>
	                           		 </td> 
	                           	  	</tr>
	                           	  </c:forEach>
                            </tbody>
                        </table>
                        <div> 
                        	<%@ include file="/WEB-INF/page/common/fenye.jsp" %>
                        </div>
				    </div>

             	 </div> <!-- content -->          
                 
                     
             
             </div> <!-- contentpanel -->
             
             <%@ include file="/WEB-INF/page/common/bottom.jsp" %>
        </div> <!-- mainpanel -->
    </div> <!-- mainwrapper -->
    </section>


    <script src="${pageContext.request.contextPath}/resource/chain/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/chain/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/chain/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/chain/js/modernizr.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/chain/js/pace.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/chain/js/retina.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/chain/js/jquery.cookies.js"></script>
    <script src="${pageContext.request.contextPath}/resource/chain/js/custom.js"></script>
</body>
</html>
