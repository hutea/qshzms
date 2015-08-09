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
	<title>分享发布 个人中心 其实没事（专注分享的网站）</title>
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/Font-Awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/jqueryFileUpload/css/jquery.fileupload.css" rel="stylesheet">  
    <link href="${pageContext.request.contextPath}/resource/jqueryFileUpload/css/jquery.fileupload-ui.css" rel="stylesheet">  
    <link href="${pageContext.request.contextPath}/resource/css/hz.ucenter.css" rel="stylesheet">
</head>
<body>
    <%@ include file="/WEB-INF/page/view/my/user-center-head.jsp" %>

    <div class="mybody container">
        <div class="row">
            <div class="col-md-12">
                <ol class="breadcrumb">
                    <li><a href="/my/center">个人中心</a></li>
                    <li><a href="#">分享</a></li>
                </ol>
                <ul id="myTab" class="nav nav-tabs">
			        <li class="active"><a href="#modal_ty_a" data-toggle="tab">下载分享</a></li>
			        <li><a href="#modal_ty_b" data-toggle="tab">文章分享</a></li>
			        <li><a href="#modal_ty_c" data-toggle="tab">音乐分享</a></li>
			        <li><a href="#modal_ty_d" data-toggle="tab">视频分享</a></li>
			        <li><a href="#modal_ty_e" data-toggle="tab">图片分享</a></li>
			    </ul>
			    <div id="myTabContent" class="tab-content ${loginUser!=null?'':'sr-only'}" style="padding-bottom: 100px;margin-bottom: 20px;">
			        <div id="modal_ty_a" class="tab-pane fade in active">
						<form class="form-horizontal">
						 <div class="form-group">
						    <label class="col-md-2 control-label">资源名称</label>
						    <div class="col-md-10">
						      <input type="text" name="title" class="form-control"  placeholder="">
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-md-2 control-label">下载地址</label>
						    <div class="col-md-10">
						      <input type="text" name="url" class="form-control" placeholder="资源下载地址">
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-md-2 control-label">标签</label>
						    <div class="col-md-10">
						      <input type="text" name="tag" class="form-control"  placeholder="标签之间用#号分隔">
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-md-2 control-label" style="padding-top:4px" >提示内容</label>
						    <div class="col-md-10">
					            <input type="checkbox" name="view" style="height: 20px; width: 20px;"/>
					            <span class="text-justify" style="vertical-align:super;">评论可见</span>
					        </div>
						  </div>
						  <textarea class="ckeditor" cols="80" id="ck_tya" name="content_tya" rows="10">
						  </textarea>
						</form>
			        </div>
			        <div id="modal_ty_b" class="tab-pane fade">
						<form class="form-horizontal">
						  <div class="form-group">
						    <label class="col-md-2 control-label">文章标题</label>
						    <div class="col-md-10">
						      <input type="text" name="title" class="form-control"  placeholder="">
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-md-2 control-label">标签</label>
						    <div class="col-md-10">
						      <input type="text" name="tag" class="form-control"  placeholder="标签之间用#号分隔">
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-md-2 control-label">引用地址</label>
						    <div class="col-md-10">
						      <input type="text" name="url" class="form-control" placeholder="文章原文地址">
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-md-2 control-label" style="vertical-align:super;padding-top: 4px;">文章内容</label>
						    <div class="col-md-10">
					            <input type="checkbox" style="height: 20px; width: 20px;"/>
					            <span class="text-justify" style="vertical-align:super;">评论可见</span>
					        </div>
						  </div>
						   <textarea class="ckeditor" cols="80" id="ck_tyb" name="content_tyb" rows="10">
						  </textarea>
						</form>
			        </div>
			        <div id="modal_ty_c" class="tab-pane fade">
						<form class="form-horizontal">
						 <div class="form-group">
						    <label class="col-md-2 control-label">音乐名称</label>
						    <div class="col-md-10">
						      <input type="text" name="title" class="form-control"  placeholder="">
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-md-2 control-label">视听地址</label>
						    <div class="col-md-10">
						      <input type="text" name="url"  class="form-control" placeholder="">
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-md-2 control-label">标签</label>
						    <div class="col-md-10">
						      <input type="text" name="tag" class="form-control"  placeholder="标签之间用#号分隔">
						    </div>
						  </div>
						  <div class="form-group">
						    	<label class="control-label" style="padding:0 20px; vertical-align:super">分享理由或视听感受</label>
					            <input type="checkbox" style="height: 20px; width: 20px;"/>
					            <span class="text-justify" style="vertical-align:super">评论可见</span>
						  </div>
						   <textarea class="ckeditor" cols="80" id="ck_tyc" name="content_tyc" rows="10">
						  </textarea>
						</form>
			        </div>
			        <div id="modal_ty_d" class="tab-pane fade">
						<form class="form-horizontal">
						 <div class="form-group">
						    <label class="col-md-2 control-label">视频名称</label>
						    <div class="col-md-10">
						      <input type="text" name="title" class="form-control"  placeholder="">
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-md-2 control-label">观看地址</label>
						    <div class="col-md-10">
						      <input type="text" name="url" class="form-control" placeholder="">
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-md-2 control-label">标签</label>
						    <div class="col-md-10">
						      <input type="text" name="tag" class="form-control"  placeholder="标签之间用#号分隔">
						    </div>
						  </div>
						  <div class="form-group">
						    	<label class="control-label" style="padding:0 20px; vertical-align:super">分享理由或影评</label>
					            <input type="checkbox" style="height: 20px; width: 20px;"/>
					            <span class="text-justify" style="vertical-align:super ">评论可见</span>
						  </div>
						</form>
						 <textarea class="ckeditor" cols="80" id="ck_tyd" name="content_tyd" rows="10">
						  </textarea>
			        </div>
			        <div id="modal_ty_e" class="tab-pane fade">
						<form class="form-horizontal">
						  <div class="row fileupload-buttonbar" style="padding-left:15px;">
							<div class="col-sm-3"></div>
							<div class="thumbnail col-sm-6">
								<img id="modal_show" style="height:250px;margin-top:10px;margin-bottom:8px;"  src="/resource/image/preview.png" data-holder-rendered="true">
								<div class="progress progress-striped active" role="progressbar" aria-valuemin="10" aria-valuemax="100" aria-valuenow="0">
									<div id="modal_progress" class="progress-bar progress-bar-success" style="width:0%;"></div>
								</div>
								<div class="caption" align="center">
									<span id="modal_upload" class="btn btn-primary fileinput-button">
										<span>上传</span>
										<input type="file" id="modal_image" name="file" multiple>
									</span>
									<a id="modal_cancle" href="javascript:void(0)" class="btn btn-warning" role="button"  style="display:none">删除</a>
								</div>
							</div>
							<div class="col-sm-3"></div>
						  </div><!-- row fileupload-buttonbar  -->
						  
						  <div class="form-group">
						    <label class="col-md-2 control-label">标签</label>
						    <div class="col-md-10">
						      <input type="text" name="tag"  class="form-control"  placeholder="标签之间用#号分隔">
						    </div>
						  </div>
						</form>
			        </div>
			        <p class="col-md-12 text-center" style="margin-top: 10px;" id="share_save_tip">
	                </p>
			        <p class="col-md-12 text-center" style="margin-top: 10px;" >
	           	   	  <button type="button" class="btn btn-default" id="save" ${loginUser!=null?'':'disabled="disabled"'}>保存</button>
	                </p>
		        </div><!-- myTabContent -->
            </div>
        </div>
    </div>
    <div class="myfoot">
      <%@ include file="/WEB-INF/page/view/common/foot.jsp" %>
    </div>

<script src="${pageContext.request.contextPath}/resource/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>

<script src="${pageContext.request.contextPath}/resource/ckeditor/ckeditor.js"></script>
<!-- upload -->
<script src="${pageContext.request.contextPath}/resource/jqueryFileUpload/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/jqueryFileUpload/js/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/jqueryFileUpload/js/jquery.fileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/jqueryFileUpload/js/cors/jquery.xdr-transport.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/resource/js/hz.sus.ajaxupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/hz.ucenter.js"></script>
</body>
</html>