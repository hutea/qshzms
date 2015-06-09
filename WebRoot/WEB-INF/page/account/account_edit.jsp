<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <meta name="description" content="">
        
        <title>系统帐号添加</title>
        <link href="${pageContext.request.contextPath}/resource/chain/css/style.default.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resource/chain/css/morris.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resource/chain/css/select2.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/resource/css/manage.common.css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/myform.js"></script>
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="${pageContext.request.contextPath}/resource/chain/js/html5shiv.js"></script>
        <script src="${pageContext.request.contextPath}/resource/chain/js/respond.min.js"></script>
        <![endif]-->
		<script type="text/javascript">
		function checkUsername(){
			var username = $("#username").val();
				$.post("${pageContext.request.contextPath}/manage/account/checkUsername.action", {
					username : username
				}, function(data) {
					if(data==0 && username != "" && username != null){//表示 帐户存在
						$("#username_error").html("用户名已经存在");
						$("#repeat").val("");
					}else{
						$("#username_error").html("");
						$("#repeat").val("success");
					}
				});
			}
		</script>
    <body>
        <header>    
        <%@ include file="/WEB-INF/page/common/head.jsp" %>
        </header>
        
        <section>
            <div class="mainwrapper">
        	<%@ include file="/WEB-INF/page/common/left.jsp" %>
                
                <div class="mainpanel">
                    <div class="pageheader">
                        <div class="media">
                            <div class="pageicon pull-left">
                                <i class="fa  fa-list-alt"></i>
                            </div>
                            <div class="media-body">
                                <ul class="breadcrumb">
                                    <li><a href=""><i class="glyphicon glyphicon-home"></i></a></li>
                                    <li>account add</li>
                                </ul>
                                <h4>帐户添加</h4>
                            </div>
                        </div><!-- media -->
                    </div><!-- pageheader -->
                    
                    <div class="contentpanel">
                    	<div class="content-xs" >
                         <div>系统帐号添加</div>
                         <div style="border-bottom: 1px solid #d5d5d5;margin-bottom: 10px;">&nbsp</div>
                         <form action="/manage/account/update" method="post"  > 
                         	<input type="hidden" name="id" value="${account.id}">
	    					<div class="form-horizontal">
		    					 <div class="form-group">
								    <label  class="col-sm-2 control-label">用户名</label>
								    <div class="col-sm-4">
								      <input type="text" class="form-control" name="username" value="${account.username}"  onkeyup="checkUsername()" placeholder="用户名" id="username">
								      <span class="errorStyle" id="username_error"></span>
 									  <s:hidden name="repeat" id="repeat" value=""/>
								    </div>
								    <label  class="col-sm-2 control-label">密码</label>
								    <div class="col-sm-4">
								      <input type="text" class="form-control" name="password"  value="${account.password}"  placeholder="密码">
								      <span></span>
								    </div>
								  </div>
		    					 <div class="form-group">
								    <label  class="col-sm-2 control-label">用户类型</label>
								    <div class="col-sm-4">
								      <input type="text" class="form-control"  name="lv" value="${account.lv}"  placeholder="用户类型(填写数字)">
								      <span></span>
								    </div>
								    <label  class="col-sm-2 control-label">昵称</label>
								    <div class="col-sm-4">
								      <input type="text" class="form-control" name="nickname" value="${account.nickname}"  placeholder="昵称">
								      <span></span>
								    </div>
								  </div>
								 <h4>角色选择</h4>
		    					 <div class="form-group" style="border: 1px solid #d3d3dd;padding: 20px;">
								    <div class="col-sm-12">
								      <c:forEach items="${groups}" var="group" varStatus="s">
								      	<div class="col-sm-6">
								      		<input type="checkbox" name="gids" value="${group.id}" ${fn:contains(ugs, group.id)?"checked='checked'":""}/>
								      		<a class="pls" href="#" data-toggle="tooltip"  data-placement="bottom" 
								      		data-title="${group.name}"  data-trigger="focus"
								      		data-content="
								      		<c:forEach items="${group.privileges}"  var="p" >
													【${p.name}】    
											</c:forEach> 
											" >${group.name}</a>
								      	</div>
								      </c:forEach>
								    </div>
								  </div>
	    					 	
	    					 	<div  class="col-md-12 text-center">
	    					 		<span><input type="reset" value="重置" class="btn btn-primary"/></span>
	    					 		<span><input type="submit" value="提交" class="btn btn-primary"/></span>
	    					 	</div>
	    					</div>
    					 </form>
						</div>  <!-- content  -->

                    </div><!-- contentpanel -->
                    <div class="bottomwrapper" >
						<%@ include file="/WEB-INF/page/common/bottom.jsp" %>
                    </div>
                </div><!-- mainpanel -->
            </div><!-- mainwrapper -->
        </section>

	    <script src="${pageContext.request.contextPath}/resource/chain/js/jquery-1.11.1.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resource/chain/js/jquery-migrate-1.2.1.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resource/chain/js/bootstrap.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resource/chain/js/modernizr.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resource/chain/js/pace.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resource/chain/js/retina.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resource/chain/js/jquery.cookies.js"></script>
	    <script src="${pageContext.request.contextPath}/resource/chain/js/custom.js"></script>
		
		<!-- 验证框架 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery.validate.min.js"></script>
  	    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery.maskedinput-1.0.js"></script>
  	    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/validate/account.js"></script>
		
		<script type="text/javascript">
			$('[data-toggle="tooltip"]').popover();
		</script>
    </body>
</html>
