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
			function del(id){
				if (confirm('您确定要删除此信息吗')) {
				  $.get("${pageContext.request.contextPath}/manage/ttk/delete", 
				  {aid:id},
				  function(data) {
			      	if(data==1){
			      		$("#tr_"+id).css("display","none");
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
                             <li><a href="">Pages</a></li>
                             <li>Blank Page</li>
                         </ul>
                         <h4>Blank Page</h4>
                     </div>
                 </div><!-- media -->
             </div><!-- pageheader -->
                    
             <div class="contentpanel">    
             	 <div class="content" >
				      <div class="row">
				      	<form id="pageList" action="${pageContext.request.contextPath}/manage/ttk/image/list" method="post">
				      		<input type="hidden" name="page" value="${page}">
				      	</form>
				      	<table class="table table-bordered table-striped">
                           <thead>
                                <tr>
                                    <th>#</th>
                                    <th>宽</th>
                                    <th>高</th>
                                    <th>大小</th>
                                    <th>图片</th>
                                    <th>隶属</th>
                                    <th>状态</th>
                                </tr>
                           </thead>
                            <tbody>
                                  <c:forEach items="${pageView.records}" var="entry" varStatus="s">  
	                           	  	<tr id="tr_${entry.id}" title="ID:${entry.id}">
	                           		 <td>${s.index+1}</td> 
	                           		 <td>${entry.width}</td> 
	                           		 <td>${entry.height}</td> 
	                           		 <td>${entry.size}</td> 
	                           		 <td>
	                           		 	<div  >
	                           		 	<a href="${entry.linkurl}" target="_blank" title="点击查看原图"><img style="height:100px;" src="${entry.t_url}" class="thumbnail" /></a>
	                           		 	</div>	
	                           		 </td> 
	                           		 <td>
	                           		 	<c:if test="${entry.share!=null}">
	                           		 		<a href="/share/view/${entry.share.id }">内容查看</a>
	                           		 	</c:if>
	                           		 	<c:if test="${entry.blog!=null}">
	                           		 		<a href="/blog/view/${entry.share.id }">博客查看</a>
	                           		 	</c:if>
	                           		 </td> 
	                           		 <td>
	                           		 	<c:if test="${entry.backup}" ><span title="${entry.backurl}" style="color: green">已备份</span></c:if>
	                           		 	<c:if test="${!entry.backup}" ><span  style="color: red">未备份</span></c:if>
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
