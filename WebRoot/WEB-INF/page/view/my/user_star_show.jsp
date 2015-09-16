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
	<title>明星资源  个人中心 其实没事（专注分享的网站）</title>
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
                    <li><a href="#">明星资源 发布</a></li>
                </ol>

		        <div >
					<form class="form-horizontal">
					  <input type="hidden" id="sid" /> 
					  <div class="form-group">
					    <label class="col-md-2 control-label">选择明星</label>
					    <div class="col-md-10">
					      <input type="text" name="title" class="form-control"  placeholder="">
					      
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-md-2 control-label">资源名称</label>
					    <div class="col-md-4">
				      		<input type="text" name="name" value="${sr.name}" class="form-control"  placeholder="资源名称">
					    </div>
					    <label class="col-md-2 control-label">资源类型</label>
					    <div class="col-md-4">
					    	<select name="category" class="form-control">
					    		<option value="-1">请选择</option>
					    		<option value="1">MV</option>
					    		<option value="2">音乐现场</option>
					    		<option value="3">演唱会</option>
					    		<option value="4">影视</option>
					    		<option value="5">综艺</option>
					    		<option value="5">综艺</option>
					    		<option value="0">其他</option>
					    	</select>
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-md-2 control-label">时间</label>
					    <div class="col-md-4">
					      	<input type="text" name="showDate" class="form-control"  placeholder="演出时间">
					    </div>
					    <label class="col-md-2 control-label">展示图</label>
					    <div class="col-md-4">
					    	<div class="input-group">
					      		<input type="file" name="showImage" class="form-control"  placeholder="展示图">
				            </div>
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-md-2 control-label">百度云下载</label>
					    <div class="col-md-10">
					      <input type="text" name="baiduDownUrl" class="form-control" placeholder="百度云下载地址/提取密码">
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-md-2 control-label">播放代码</label>
					    <div class="col-md-10">
					      <input type="text" name="playUrl" class="form-control" placeholder="优酷/土豆等提供的播放代码">
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-md-2 control-label">资源介绍</label>
					    <div class="col-md-10">
						    <textarea name="summary" placeholder="资源介绍" style="width: 100%;"  rows="5">
						    </textarea>
					    </div>
					  </div>
					</form>
		        </div>
			        
		        <p class="col-md-12 text-center" style="margin-top: 10px;" id="share_save_tip">
                </p>
		        <p class="col-md-12 text-center" style="margin-top: 10px;" >
           	   	  <button type="button" class="btn btn-default" id="save" ${loginUser!=null?'':'disabled="disabled"'}>保存</button>
                </p>
            </div>
        </div>
    </div>
    <div class="myfoot">
      <%@ include file="/WEB-INF/page/view/common/foot.jsp" %>
    </div>

<script src="${pageContext.request.contextPath}/resource/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>

<!-- upload -->
</body>
</html>