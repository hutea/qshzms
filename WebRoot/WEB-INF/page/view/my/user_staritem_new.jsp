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
    <link href="${pageContext.request.contextPath}/resource/typeahead/mystyle.css"  rel="stylesheet"></link>
    <link href="${pageContext.request.contextPath}/resource/datetimepicker/bootstrap-datetimepicker.min.css"  rel="stylesheet" ></link>
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
					<form class="form-horizontal" action="/my/star/item/save" method="post" enctype="multipart/form-data">
					  <input type="hidden" id="sid" name="sid" /> 
					  <div class="form-group">
					    <label class="col-md-2 control-label">选择明星</label>
					    <div class="col-md-10">
					      <input type="text" name="sname" id="sname" class="typeahead"  autocomplete="off" placeholder="">
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-md-2 control-label">资源名称</label>
					    <div class="col-md-4">
				      		<input type="text" name="name" class="form-control"  placeholder="资源名称">
					    </div>
					    <label class="col-md-2 control-label">资源类型</label>
					    <div class="col-md-4">
					    	<select name="scid" id="sup_category" class="form-control">
					    		<option value="-1">请选择</option>
					    	</select>
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-md-2 control-label">时间</label>
					    <div class="col-md-4">
					    	<div class='input-group date' >
					      		<input type="text" name="sdate" id='sdate'  class="form-control"  placeholder="演出时间">
			                    <span class="input-group-addon">
			                        <span class="fa fa-calendar fa-sm"></span>
			                    </span>
			                </div>
					    </div>
					    <label class="col-md-2 control-label">展示图</label>
					    <div class="col-md-4">
					    	<div class="input-group">
					      		<input type="file" name="image" class="form-control"  placeholder="展示图">
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
					    	<textarea name="playUrl"  placeholder="播放代码" style="width: 100%;"  rows="2"></textarea>
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-md-2 control-label">资源介绍</label>
					    <div class="col-md-10">
						    <textarea name="summary" placeholder="资源介绍" style="width: 100%;"  rows="5">
						    </textarea>
					    </div>
					  </div>
			        <p class="col-md-12 text-center" style="margin-top: 10px;" id="share_save_tip">
	                </p>
			        <p class="col-md-12 text-center" style="margin-top: 10px;" >
			          <input type="submit" class="btn btn-default" value="保存" />
	                </p>
					</form>
		        </div>
			        
            </div>
        </div>
    </div>
    <div class="myfoot">
      <%@ include file="/WEB-INF/page/view/common/foot.jsp" %>
    </div>

<script src="${pageContext.request.contextPath}/resource/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>

<script  src="${pageContext.request.contextPath}/resource/typeahead/handlebars.js"></script>
<script  src="${pageContext.request.contextPath}/resource/typeahead/typeahead.bundle.js"></script>
<script  src="${pageContext.request.contextPath}/resource/datetimepicker/moment-2.9.0.js"></script>
<script  src="${pageContext.request.contextPath}/resource/datetimepicker/bootstrap-datetimepicker.min.js"></script>

<script type="text/javascript">
$(function() {
	 var bestPictures = new Bloodhound({
		    datumTokenizer: Bloodhound.tokenizers.obj.whitespace('label'),
		    queryTokenizer: Bloodhound.tokenizers.whitespace,
		    prefetch: '/my/star/query/s',
		    remote: {
		      url: '/my/star/query/ajax/%QUERY.json',
		      wildcard: '%QUERY'
		    }
		  });

		  $('#sname').typeahead(null, {
		    name: 'best-pictures',
		    display: 'label',
		    source: bestPictures,
		    templates: {
		        empty: [
		          '<div class="empty-message">',
		            '您的输入未能匹配任何明星',
		          '</div>'
		        ].join('\n'),
		        suggestion: Handlebars.compile('<div><img src="{{icon}}" style="height:40px;"/><strong>{{label}}</strong> – {{resnum}}</div>')
		    },
		    classNames: {
		        input: 'Typeahead-input',
		        hint: 'Typeahead-hint',
		        selectable: 'Typeahead-selectable'
		    }
		  });
		  
		  $('.typeahead').bind('typeahead:select', function(ev, suggestion) {
			  $("#sid").val(suggestion.id);
			  //console.log('Selection: ' + suggestion.id);
			  console.log('sf: ' + $("#sid").val());
			  loadCategory(suggestion.id); 
		   });
		  
		  //加载日历控件
		  $('#sdate').datetimepicker(
				  {format: 'YYYY年MM月DD日',}
		  );
});

function loadCategory(sid) {
	$.get("/my/star/support/category", {
		sid : sid
	}, function(data) {
	    $("#sup_category").empty();
	    $("#sup_category").append("<option value='0'>请选择</option>");
		json = eval(data);  
	    for(var i=0; i<json.length; i++){
	    	$("#sup_category").append("<option value='"+json[i].id+"'>"+json[i].name+"</option>");
	    }  
	});
}
</script>
</html>