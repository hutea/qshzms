<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <meta name="description" content="">
        <title>明星分类管理</title>
        <link href="${pageContext.request.contextPath}/resource/chain/css/style.default.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resource/css/manage.common.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/resource/js/myform.js" type="text/javascript"></script>
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->
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
             	 	  <div class="row" style="margin-bottom: 10px;">
				           <div class="col-md-12">
				                <form class="form-inline" id="myform" action="${pageContext.request.contextPath}/manage/star/category/save" method="post" >
				                     <input type="text" name="name" class="form-control" placeholder="分类名称">
				                     <input type="text" name="lv" class="form-control" value="1"  placeholder="LEVEL">
				                     <input type="text" name="background" class="form-control"   placeholder="背景色">
				                     <input type="text" name="icon" class="form-control"  placeholder="图标">
                        			 <input type="submit" class="btn btn-primary" name="" value="提交">
				                </form>
				           </div>
				      </div>
             	 	  <div class="row" style="margin-bottom: 10px;">
             	 	  	<div class="col-md-2"><i class="fa fa-vimeo-square fa-2x"></i>fa fa-vimeo-square</div>
             	 	  	<div class="col-md-2"><i class="fa fa-desktop fa-2x"></i>fa fa-desktop</div>
             	 	  	<div class="col-md-2"><i class="fa fa-music fa-2x"></i>fa fa-music</div>
             	 	  	<div class="col-md-2"><i class="fa fa-play-circle fa-2x"></i>fa fa-play-circle</div>
             	 	  	<div class="col-md-2"><i class="fa fa-align-justify fa-2x"></i>fa fa-align-justify</div>
             	 	  	<div class="col-md-2"><i class="fa fa-square fa-2x"></i>fa fa-square</div>
             	 	  </div>
				      <div class="row">
				      	<form id="pageList" action="${pageContext.request.contextPath}/manage/star/list" method="post">
				      		<input type="hidden" name="page" value="${page}">
				      	</form>
				      	<table class="table table-bordered table-striped">
                           <thead>
                                <tr>
                                    <th>#</th>
                                    <th>ID</th>
                                    <th>操作</th>
                                </tr>
                           </thead>
                            <tbody>
                                  <c:forEach items="${pageView.records}" var="entry" varStatus="s">  
	                           	  	<tr id="tr_${entry.id}"">
	                           		 <td>${s.index+1}</td> 
	                           		 <td>${entry.id}</td> 
	                           		 <td>
	                           		    <form class="form-inline" id="myform" action="${pageContext.request.contextPath}/manage/star/category/update" method="post" >
						                     <input type="hidden" name="sid" value="${entry.id}" >
						                     <input type="text" name="name" value="${entry.name}"  class="form-control" placeholder="分类名称">
						                     <input type="text" name="lv" value="${entry.lv}" class="form-control"   placeholder="LEVEL">
						                     <input type="text" name="background" value="${entry.background}" class="form-control"   placeholder="LEVEL">
						                     <input type="text" name="icon" value="${entry.icon}" class="form-control"   placeholder="LEVEL">
		                        			 <input type="submit" class="btn btn-primary" name="" value="修改">
						                </form>
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
