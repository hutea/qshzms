<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <meta name="description" content="">
        <title>明星添加</title>
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
             	 	 <form action="${pageContext.request.contextPath}/manage/star/save" id="myform" name="myform" method="post"  > 
	    					<div class="form-horizontal">
		    					 <div class="form-group">
								    <div class="col-md-2">
								 	  <label class="control-label" style="padding-top:4px" >明星名称</label>
								      <input type="text" class="form-control" name="name" >
								    </div>
								    <div class="col-md-6">
								 	  <label class="control-label" style="padding-top:4px" >展示图片地址</label>
								      <input type="text" class="form-control" name="imageUrl" >
								    </div>
								  </div>
		    					 <div class="form-group">
								    <div class="col-md-2">
								 	  <label class="control-label" style="padding-top:4px" >明星代号</label>
								      <input type="text" class="form-control" name="code" >
								    </div>
								    <div class="col-md-6">
								  		<label class="control-label" style="padding-top:4px" >小图片地址</label>
								    	<input type="text" class="form-control" name="imgUrl">
								    </div>
								  </div>
		    					 <div class="form-group">
								    <div class="col-md-2">
								  		<label class="control-label" style="padding-top:4px" >排序级别</label>
								  		<select name="lv" class="form-control">
									    	<c:forEach begin="1" end="5" step="1" varStatus="s" > 
									      	<option value="${s.index }" >${s.index}</option>
									    	</c:forEach>
									    </select>
								    </div>
								    <div class="col-md-2">
								  		<label class="control-label" style="padding-top:4px" >首页排序标记</label>
								  		<select name="iod" class="form-control">
									    	<c:forEach begin="1" end="5" step="1" varStatus="s" > 
									      	<option value="${s.index }"  >${s.index}</option>
									    	</c:forEach>
									    </select>
								    </div>
							        <div class="col-md-2">
								  		<label class="control-label" style="padding-top:4px" >分类</label>
								    	<select name="type" class="form-control">
								    		<option value="1">华语</option>
								    		<option value="2">欧美</option>
								    		<option value="3">日韩</option>
								    	</select>
								    </div>
							        <div class="col-md-2">
								  		<label class="control-label" style="padding-top:4px" >浏览量</label>
								    	<input type="text" class="form-control" name="pv" value="0">
								    </div>
								  </div>
								 <div class="form-group">
								  	<div class="col-md-8">
									<input  type="text" class="form-control" name="note"  placeholder="简语" ></textarea>
									</div>
				                 </div>
								 <div class="form-group">
								  	<div class="col-md-8">
									<input  type="text" class="form-control" name="tagName" placeholder="标签名称：多个以#号分隔"  ></textarea>
									</div>
				                 </div>
								 <div class="form-group">
								  	<div class="col-md-8">
									<textarea  name="summary" style="height: 150px;border: 1px solid #d5d5d5;width:100%"></textarea>
									</div>
				                 </div>
								 <div class="form-group">
								 	  <div class="col-md-8">
								      <c:forEach items="${categorys}" var="entry" varStatus="s">  
										  	<div class="col-md-3">
										      	<input type="checkbox"  name="scids" value="${entry.id}">${entry.name } 
										    </div>
								      </c:forEach>
								      </div>
				                 </div>
				                 <div class="form-group">
				                 	<div class="text-center col-md-8">
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
  
</body>
</html>
