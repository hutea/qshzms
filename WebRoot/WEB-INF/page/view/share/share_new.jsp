<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/css/hz.sus.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/Font-Awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/jqueryFileUpload/css/jquery.fileupload.css" rel="stylesheet">  
    <link href="${pageContext.request.contextPath}/resource/jqueryFileUpload/css/jquery.fileupload-ui.css" rel="stylesheet">  
    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head> 
<body>
<div class="container">
	<%@ include file="/WEB-INF/page/view/common/head.jsp" %>
    <div class="hz-main"> 
        <div class="hz-core col-md-8">
            <div class="hz-per hz-per-file">
                <div class="row hz-row-title">
                    <div class="col-md-2"><i class="icon-cloud-download icon-4x" style="color: #369;" ></i></div>
                    <div class="col-md-8 h3">Hadoop视频全集下载</div>
                    <div class="col-md-2">
                    	<p>
	                    	<i class="icon-star pull-right" style="color: red;" ></i>
	                    	<i class="icon-star pull-right" style="color: red;" ></i>
	                    	<i class="icon-star pull-right" style="color: red;" ></i>
	                    	<i class="icon-star pull-right" style="color: red;" ></i>
	                    	<i class="icon-star pull-right" style="color: red;" ></i>
                    	</p>
                    </div>
                </div> 
                <div class="row hz-row-tag">
                    <div class="col-md-9"><i class="icon-tags"></i>
                        <a class="btn btn-danger btn-xs">Hadoop</a>
                        <a class="btn btn-primary btn-xs">java</a>
                        <a class="btn btn-warning btn-xs">HDFS</a>
                        <a class="btn btn-info btn-xs">设计</a>
                    </div>
                    <div class="col-md-3">13:35:30 05/04</div>
                </div>
                <div class="row hz-row-content">
                    <p class="col-md-12">
                        <span class="text-info"><strong>下载地址：</strong></span> <a href="#">http://www.getblimp.com/</a>
                    </p>
                    <div class="col-md-12">
                        <p>
                            Hadoop是一个分布式系统基础架构，由Apache基金会开发。用户可以在不了解分布式底层细节的情况下，开发分布式程序。本专题为传智播客hadoop视频，hadoop作为一个开源的云计算框架，越来越火，希望大家喜欢。
                        </p>
                    </div>
                </div>
                <div class="row hz-row-bottom">
                    <div class="col-md-3 ">
                        <a class="btn btn-primary">
                            <i class="icon-thumbs-up icon-large"></i>
                            <span>赞(18)</span>
                        </a>
                    </div>
                    <div class="col-md-3"><span class="btn btn-primary">评论(20)</span></div>
                    <div class="col-md-3 col-md-push-3"><span class="btn btn-primary">more</span></div>
                </div>
            </div>

            <div class="hz-per hz-per-article">
                <div class="row hz-row-title">
                    <div class="col-md-3"><i class="icon-list-alt icon-4x"></i></div>
                    <div class="col-md-9 h3 hz-per-title">文章分享</div>
                </div>
                <div class="row hz-row-tag">
                    <div class="col-md-9"><i class="icon-tags"></i>
                        <a class="btn btn-danger btn-xs">Hadoop</a>
                        <a class="btn btn-primary btn-xs">java</a>
                        <a class="btn btn-warning btn-xs">HDFS</a>
                        <a class="btn btn-info btn-xs">设计</a>
                    </div>
                    <div class="col-md-3">13:35:30 05/04</div>
                </div>
                <div class="row hz-row-content">
                    <div class="col-md-12">
                        <p>“五一”小长假的最后一天，成都双流国际机场人来人往。突然，随着动感的音乐，一群准空姐、准空少出现在了机场T2出发大厅，并随着音乐跳起了小苹果舞蹈。
                            　　面对突然出现的这一幕，过往的乘客纷纷驻足观望、拍照。美丽优雅的空姐、帅气潇洒的空少，极具亲和力的空姐礼仪，时尚的舞蹈，短短几分钟的表演赢得了众人的尖叫和掌声。随后，准空姐、空少拿出了早已准备好的“文明乘机、安全出行”、“中国民航第四城成都欢迎您”、“文明礼让、畅行天下”、“飞机最安全”……等宣传航空文明、航空安全的标语，呼吁人们文明乘机、安全出行。</p>
                    </div>
                </div>
                <div class="row hz-row-bottom">
                    <div class="col-md-3 ">
                        <a class="btn btn-primary">
                            <i class="icon-thumbs-up icon-large"></i>
                            <span>赞(18)</span>
                        </a>
                    </div>
                    <div class="col-md-3"><span class="btn btn-primary">评论(20)</span></div>
                    <div class="col-md-3 col-md-push-3"><span class="btn btn-primary">more</span></div>
                </div>
            </div>
            <div class="hz-per hz-per-music">
                <div class="row hz-row-title">
                    <div class="col-md-3"><i class="icon-list-alt icon-4x"></i></div>
                    <div class="col-md-9 h3 hz-per-title">音乐分享</div>
                </div>
                <div class="row hz-row-tag">
                    <div class="col-md-9"><i class="icon-tags"></i>
                        <a class="btn btn-danger btn-xs">Hadoop</a>
                        <a class="btn btn-primary btn-xs">java</a>
                        <a class="btn btn-warning btn-xs">HDFS</a>
                        <a class="btn btn-info btn-xs">设计</a>
                    </div>
                    <div class="col-md-3">13:35:30 05/04</div>
                </div>
                <div class="row hz-row-content">
                    <div class="col-md-12">
                        <p>“五一”小长假的最后一天，成都双流国际机场人来人往。突然，随着动感的音乐，一群准空姐、准空少出现在了机场T2出发大厅，并随着音乐跳起了小苹果舞蹈。
                            　　面对突然出现的这一幕，过往的乘客纷纷驻足观望、拍照。美丽优雅的空姐、帅气潇洒的空少，极具亲和力的空姐礼仪，时尚的舞蹈，短短几分钟的表演赢得了众人的尖叫和掌声。随后，准空姐、空少拿出了早已准备好的“文明乘机、安全出行”、“中国民航第四城成都欢迎您”、“文明礼让、畅行天下”、“飞机最安全”……等宣传航空文明、航空安全的标语，呼吁人们文明乘机、安全出行。</p>
                    </div>
                </div>
                <div class="row hz-row-bottom">
                    <div class="col-md-3 ">
                        <a class="btn btn-primary">
                            <i class="icon-thumbs-up icon-large"></i>
                            <span>赞(18)</span>
                        </a>
                    </div>
                    <div class="col-md-3"><span class="btn btn-primary">评论(20)</span></div>
                    <div class="col-md-3 col-md-push-3"><span class="btn btn-primary">more</span></div>
                </div>
            </div>
            <div class="hz-per hz-per-movie">
                <div class="row hz-row-title">
                    <div class="col-md-3"><i class="icon-list-alt icon-4x"></i></div>
                    <div class="col-md-9 h3 hz-per-title">电影分享</div>
                </div>
                <div class="row hz-row-tag">
                    <div class="col-md-9"><i class="icon-tags"></i>
                        <a class="btn btn-danger btn-xs">Hadoop</a>
                        <a class="btn btn-primary btn-xs">java</a>
                        <a class="btn btn-warning btn-xs">HDFS</a>
                        <a class="btn btn-info btn-xs">设计</a>
                    </div>
                    <div class="col-md-3">13:35:30 05/04</div>
                </div>
                <div class="row hz-row-content">
                    <div class="col-md-12">
                        <p>“五一”小长假的最后一天，成都双流国际机场人来人往。突然，随着动感的音乐，一群准空姐、准空少出现在了机场T2出发大厅，并随着音乐跳起了小苹果舞蹈。
                            　　面对突然出现的这一幕，过往的乘客纷纷驻足观望、拍照。美丽优雅的空姐、帅气潇洒的空少，极具亲和力的空姐礼仪，时尚的舞蹈，短短几分钟的表演赢得了众人的尖叫和掌声。随后，准空姐、空少拿出了早已准备好的“文明乘机、安全出行”、“中国民航第四城成都欢迎您”、“文明礼让、畅行天下”、“飞机最安全”……等宣传航空文明、航空安全的标语，呼吁人们文明乘机、安全出行。</p>
                    </div>
                </div>
                <div class="row hz-row-bottom">
                    <div class="col-md-3 ">
                        <a class="btn btn-primary">
                            <i class="icon-thumbs-up icon-large"></i>
                            <span>赞(18)</span>
                        </a>
                    </div>
                    <div class="col-md-3"><span class="btn btn-primary">评论(20)</span></div>
                    <div class="col-md-3 col-md-push-3"><span class="btn btn-primary">more</span></div>
                </div>
            </div>
            <div class="hz-per hz-per-image">
                <div class="row hz-row-title sr-only">
                    <div class="col-md-3"><i class="icon-list-alt icon-4x"></i></div>
                    <div class="col-md-9 h3 hz-per-title">图片分享</div>
                </div>
                <div class="row hz-row-tag">
                    <div class="col-md-9"><i class="icon-tags"></i>
                        <a class="btn btn-danger btn-xs">Hadoop</a>
                        <a class="btn btn-primary btn-xs">java</a>
                        <a class="btn btn-warning btn-xs">HDFS</a>
                        <a class="btn btn-info btn-xs">设计</a>
                    </div>
                    <div class="col-md-3">13:35:30 05/04</div>
                </div>
                <div class="row hz-row-content">
                    <div class="col-md-12">
                        <p><img src="10-1.jpg"  class="img-thumbnail"/></p>
                    </div>
                </div>
                <div class="row hz-row-bottom">
                    <div class="col-md-3 ">
                        <a  class="btn btn-primary" onclick="lk('${entry.id}')">
                            <i class="icon-thumbs-up icon-large"></i>
                            <span id="lk-${entry.id}">赞(18)</span>
                        </a>
                    </div>
                    <div class="col-md-3">
                   	 <a class="btn btn-primary" onclick="cmd('${entry.id}')" id="cmd-${entry.id }">评论(20)</a>
                    </div>
                    <div class="col-md-3 col-md-push-3"><span class="btn btn-primary">more</span></div>
                </div>
            </div>

            <nav class="text-center">
                <ul class="pagination pagination-md" >
                    <li><a href="#" aria-label="Previous">&laquo;</a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">6</a></li>
                    <li><a href="#">7</a></li>
                    <li><a href="#">8</a></li>
                    <li><a href="#">9</a></li>
                    <li><a href="#">10</a></li>
                    <li><a href="#" aria-label="Previous">&raquo;</a></li>
                </ul>
            </nav>
        </div>

        <div class="hz-right col-md-4">
            <div class="hz-per">
                <img src="01.jpg"  class="img-responsive"/>
            </div>
        </div>
    </div>
    
   <%@ include file="/WEB-INF/page/view/common/foot.jsp" %>
</div>

<!-- 模态框 -->
<%@ include file="/WEB-INF/page/view/common/modal.jsp" %>

<script src="${pageContext.request.contextPath}/resource/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/ckeditor/ckeditor.js"></script>
<!-- upload -->
<script src="${pageContext.request.contextPath}/resource/jqueryFileUpload/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/jqueryFileUpload/js/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/jqueryFileUpload/js/jquery.fileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/jqueryFileUpload/js/cors/jquery.xdr-transport.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/resource/js/hz.sus.common.js" type="text/javascript"></script>


</body>
</html>