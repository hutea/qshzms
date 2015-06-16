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
        <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet">
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
		<%@ include file="/WEB-INF/page/customer/qq/qq_left.jsp" %>
                
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
				                <form class="form-inline" id="myform" action="${pageContext.request.contextPath}/manage/ttk/save" method="post" >
				                     <input type="text" name="albumname" class="form-control" placeholder="QQ号">
                        			 <input type="submit" class="btn btn-primary" name="" value="查询">
				              		 <a class="btn btn-info" href="javascript:void(0)" id="qicq_add">增加</a>
				                </form>
				           </div>
				      </div>
				      
				      <div class="row">
				      	<form id="pageList" action="${pageContext.request.contextPath}/manage/ttk//list" method="post">
				      		<input type="hidden" name="page" value="${page}">
				      	</form>
				      	<table class="table table-bordered table-striped">
                           <thead>
                                <tr>
                                    <th>#</th>
                                    <th>QICQ号</th>
                                    <th>价值</th>
                                    <th>置顶级别</th>
                                    <th>状态</th>
                                    <th>发布时间</th>
                                    <th>最后修改时间</th>
                                    <th>操作</th>
                                </tr>
                           </thead>
                            <tbody>
                                  <c:forEach items="${pageView.records}" var="entry" varStatus="s">  
	                           	  	<tr id="tr_${entry.id}"">
	                           		 <td>${s.index+1}</td> 
	                           		 <td>${entry.qicq}</td> 
	                           		 <td>${entry.money}</td> 
	                           		 <td> 
	                           		 	<c:if test="${entry.top==1}">一级置顶</c:if>
	                           		 	<c:if test="${entry.top==2}">二级置顶</c:if>
	                           		 	<c:if test="${entry.top==3}">三级置顶</c:if>
	                           		 </td> 
	                           		 <td> 
	                           		 	<c:if test="${entry.online}">上架</c:if>
	                           		 	<c:if test="${entry.online}">下架</c:if>
	                           		 </td> 
	                           		 <td><fmt:formatDate value="${entry.sendTime}" pattern="yyyy-MM-dd hh:mm:ss "/></td> 
	                           		 <td><fmt:formatDate value="${entry.lastEditTime}" pattern="yyyy-MM-dd hh:mm:ss "/></td> 
	                           		 
	                           		 <td>
	                           		 	<a href="javascript:update('${entry.id}')">修改</a>
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
             
        </div> <!-- mainpanel -->
    </div> <!-- mainwrapper -->
    </section>

	
	<div class="modal fade" id="modal_qicq_add"  role="dialog" aria-label="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-md">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">QICQ信息添加</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label class="control-label col-md-3 text-right">QICQ号</label>
                            <div class=" col-md-5">
                            <input type="text" class="form-control" name="qicq">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 text-right">价值</label>
                            <div class=" col-md-5">
                            <input type="text" class="form-control"  name="money">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 text-right">置顶级别</label>
                            <div class="col-md-5">
                            	<select name="top" class="form-control" name="top">
                            		<option value="1">一级置顶</option>
                            		<option value="2">二级置顶</option>
                            		<option value="3">三级置顶</option>
                            	</select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 text-right">详细描述</label>
                            <div class=" col-md-9">
                            <textarea rows="5" cols="30" class="form-control" maxlength="200" name="detail"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                	<div class="text-center">
                  	    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    	<button type="button" class="btn btn-default" id="save" >保存</button>
                	</div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 修改 -->
	<div class="modal fade" id="modal_qicq_edit"  role="dialog" aria-label="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-md">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">QICQ信息修改</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label class="control-label col-md-3 text-right">QICQ号</label>
                            <div class=" col-md-5">
                            <input type="text" class="form-control" name="qicq">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 text-right">价值</label>
                            <div class=" col-md-5">
                            <input type="text" class="form-control"  name="money">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 text-right">置顶级别</label>
                            <div class="col-md-5">
                            	<select name="top" class="form-control" name="top">
                            		<option value="1">一级置顶</option>
                            		<option value="2">二级置顶</option>
                            		<option value="3">三级置顶</option>
                            	</select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 text-right">上下架</label>
                            <div class="col-md-5">
                            	<select name="top" class="form-control" name="top">
                            		<option value="1">上架</option>
                            		<option value="2">下架</option>
                            	</select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 text-right">详细描述</label>
                            <div class=" col-md-9">
                            <textarea rows="5" cols="30" class="form-control" maxlength="200" name="detail"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                	<div class="text-center">
                  	    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    	<button type="button" class="btn btn-default" id="save" >保存</button>
                	</div>
                </div>
            </div>
        </div>
    </div>
	
    <script src="${pageContext.request.contextPath}/resource/chain/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/chain/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/customer/qq/js/qicq.js"></script>
</body>
</html>
