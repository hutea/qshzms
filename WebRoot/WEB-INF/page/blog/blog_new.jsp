<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <meta name="description" content="">
        <title>博客 发表</title>
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
                             <li><a href="">Pages</a></li>
                             <li>Blank Page</li>
                         </ul>
                         <h4>Blank Page</h4>
                     </div>
                 </div><!-- media -->
             </div><!-- pageheader -->
                    
             <div class="contentpanel">    
             	 <div class="content" >
             	 	 <form action="${pageContext.request.contextPath}/manage/blog/save" id="myform" name="myform" method="post"  > 
	    					<div class="form-horizontal">
		    					 <div class="form-group">
								    <div class="col-md-5">
								 	  <label class="control-label" style="padding-top:4px" >博客标题</label>
								      <input type="text" class="form-control" name="title"  >
								    </div>
								    <div class="col-md-3">
								  		<label class="control-label" style="padding-top:4px" >博客分类</label>
								    	<select name="tid" class="form-control">
									    	<c:forEach items="${types}" var="type">
									      	<option value="${type.id }">${type.name }</option>
									    	</c:forEach>
									    </select>
								    </div>
								  </div>
		    					 <div class="form-group">
								    <div class="col-md-5">
								    	<label class="control-label" style="padding-top:4px" >博客标签</label>
								      	<input type="text" class="form-control" name="tag_str"   placeholder="多个标签用#号分隔" >
								    </div>
								    <div class="col-md-2">
								    	<label class="control-label" style="padding-top:4px" >是否推荐</label>
							            <select name="recommend" class="form-control">
									      	<option value="true">推荐【YES】</option>
									      	<option value="false">推荐【NO】</option>
									    </select>
							        </div>
								  </div>
								  
								  <div class="form-group">
								  	<div class="col-md-11">
									<textarea id="comteditor" class="ckeditor" name="content" style="height: 100px;border: 1px solid #d5d5d5;"></textarea>
									</div>
				                 </div>
				                 <div class="form-group">
				                 	<div class="text-center col-md-12">
				                 		<input type="reset" value="重置" class="btn btn-primary"/>
				                 		<input type="submit" value="提交" class="btn btn-primary"/>
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
    
    <script type="text/javascript">
    var comtCkeditor = CKEDITOR.replace('content', {
    	toolbar:[   
    	         ['Source','-','Save','NewPage','Preview','-','Templates'],   
    	         ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],   
    	         ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],   
    	         ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],   
    	         ['BidiLtr', 'BidiRtl'],   
    	         '/',   
    	         ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],   
    	         ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],   
    	         ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],   
    	         ['Link','Unlink','Anchor'],   
    	         ['himage','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],   
    	         '/', 
    	         ['Styles','Format','Font','FontSize'],   
    	         ['TextColor','BGColor'],   
    	         ['Syntaxhighlight','Maximize', 'ShowBlocks','-','About'] 
    	     ], 
    	uiColor : '#428bca',
    }); 
    </script>
</body>
</html>
