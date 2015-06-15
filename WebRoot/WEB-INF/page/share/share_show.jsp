<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <meta name="description" content="">
        <title>分享 查看</title>
        <link href="${pageContext.request.contextPath}/resource/chain/css/style.default.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resource/css/manage.common.css" rel="stylesheet">
        <link  href="${pageContext.request.contextPath}/resource/syntaxhighlighter_3.0.83/styles/shCoreDefault.css" type="text/css" rel="stylesheet" />
        <script src="${pageContext.request.contextPath}/resource/syntaxhighlighter_3.0.83/scripts/shCore.js"></script>
    	<%@ include file="/WEB-INF/page/view/blog/shBrush.jsp" %>
   		<script type="text/javascript">SyntaxHighlighter.all();</script>
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
                             <li><a href="">分享详细 查看</a></li>
                         </ul>
                         <h4>分享详细 查看</h4>
                     </div>
                 </div><!-- media -->
             </div><!-- pageheader -->
                    
             <div class="contentpanel">    
             	 <div class="content" >
             	 	 <form action="${pageContext.request.contextPath}/manage/share/update" id="myform" name="myform" method="post"  > 
             	 	 		<input type="hidden" name="id" value="${share.id}">
	    					<div class="form-horizontal">
		    					 <div class="form-group">
								    <div class="col-md-5">
								 	  <label class="control-label" >标题</label>
								      <input type="text" class="form-control" name="title" value="${share.title }">
								    </div>
								    <div class="col-md-2">
								  		<label class="control-label"> 所属分类</label>
								    	<select name="category" class="form-control">
									      	<option value="1"  ${share.category==1?'selected="selected"':'' } >下载</option>
									      	<option value="2"  ${share.category==2?'selected="selected"':'' } >文章</option>
									      	<option value="3"  ${share.category==3?'selected="selected"':'' } >音乐</option>
									      	<option value="4"  ${share.category==4?'selected="selected"':'' } >视频</option>
									      	<option value="5"  ${share.category==5?'selected="selected"':'' } >图片</option>
									    </select>
								    </div>
								    <div class="col-md-2">
								  		<label class="control-label"  >星级</label>
								  		<select name="star" class="form-control">
									    	<c:forEach begin="0" end="5" step="1" varStatus="s" > 
									      	<option value="${s.index }" ${share.star==s.index?'selected="selected"':'' } >${s.index}</option>
									    	</c:forEach>
									    </select>
								    </div>
								    <div class="col-md-2">
								  		<label class="control-label" >站点认证(防恶意举报)</label>
									    <select name="siteauth" class="form-control">
									      	<option value="true"  ${share.siteauth?'selected="selected"':''} >认证分享</option>
									      	<option value="false" ${!share.siteauth?'selected="selected"':''}>普通分享</option>
									    </select>
								    </div>
								  </div>
		    					 <div class="form-group">
								    <div class="col-md-5">
								    	<label class="control-label" >标签</label>
								      	<input type="text" class="form-control" name="tag_str" placeholder="多个标签用#号分隔" value="${tag_str}">
								    </div>
								    <div class="col-md-2">
								    	<label class="control-label" >可见度</label>
							            <select name="recommend" class="form-control">
									      	<option value="true"  ${blog.view?'selected="selected"':''} >直接可见</option>
									      	<option value="false" ${!blog.view?'selected="selected"':''} >回复可见</option>
									    </select>
							        </div>
							        <div class="col-md-2">
								  		<label class="control-label" >赞数(特殊情况下修改)</label>
								    	<input type="text" class="form-control" name="lk" value="${share.lk}" >
								    </div>
							        <div class="col-md-2">
								  		<label class="control-label"  >评论总数(特殊情况下修改)</label>
								    	<input type="text" class="form-control" name="comt" value="${share.comt}" >
								    </div>
								  </div>
		    					 <div class="form-group" style="margin-bottom: 5px;">
								    <div class="col-md-5">
								    	<label class="control-label"  >url地址:下载链接、音视频播放地址、图片地址、文章引用源地址</label>
								      	<input type="text" class="form-control" name="url" placeholder="多个标签用#号分隔" value="${share.url}">
								    	<label class="control-label text-primary" >内容摘要</label>
								    </div>
								    <div class="col-md-6">
								    	<label class="control-label" >播放代码：视频播放相关代码</label>
							            <textarea class="form-control" rows="" cols="" name="video">${share.video}</textarea>
							        </div>
								  </div>
								  
								 <div class="form-group">
								  	<div class="col-md-11">
									${share.sumary}
									</div>
				                 </div>
				                  <label class="control-label text-primary" >详细内容</label>
								  <div class="form-group">
								  	<div class="col-md-11">
									${share.content}
									</div>
				                 </div>
							</div>
					 </form>
             	 </div>           
                 
             
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
    <script src="${pageContext.request.contextPath}/resource/ckeditor/ckeditor.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/hz.sus.ajaxupload.js" type="text/javascript"></script>
    
</body>
</html>
