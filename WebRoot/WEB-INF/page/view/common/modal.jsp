<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
<div class="modal fade" id="myModal"  role="dialog" aria-label="myModalLabel" aria-hidden="true">
   <div class="modal-dialog modal-md" id="myModal-dialog">
       <div class="modal-content">
           <div class="modal-header">
                   
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <span class="modal-title h3">开始分享吧</span>
                    <a href="/my/share/new">分享+</a>
                    <a href="javascript:enlarge()"  title="点击放大窗口">
	                    <i title="放大"  class="fa fa-arrows-alt "></i>
                    </a>
                    <p>
                    	<span id="thank" class="text-primary" style="display: none;">分享成功，感谢您的无私分享。</span>
                    	<span id="close_tip" class="text-info" style="display: none;"> <strong>1</strong>秒后窗口关闭</span>
                   	</p>
           </div>
           <div class="modal-body">
           		<ul id="myTab" class="nav nav-tabs">
			        <li class="active"><a href="#modal_ty_a" data-toggle="tab">资源分享</a></li>
			        <li><a href="#modal_ty_b" data-toggle="tab">文章分享</a></li>
			        <li><a href="#modal_ty_c" data-toggle="tab">音乐分享</a></li>
			        <li><a href="#modal_ty_d" data-toggle="tab">视频分享</a></li>
			        <li><a href="#modal_ty_e" data-toggle="tab">图片分享</a></li>
			    </ul>
			    <div id="myTabContent" class="tab-content ${loginUser!=null?'':'sr-only'}">
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
								<img id="modal_show" style="height:180px;margin-top:10px;margin-bottom:8px;"  src="/resource/image/preview.png" data-holder-rendered="true">
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
		        </div><!-- myTabContent -->
		        <!-- signup  -->
		        <c:if test="${loginUser==null}">
				    <p id="modal_signup_info">
				    	<p id="modal_signup">您好，分享内容前需要先注册！好吧，<a href="javascript:signup_share()">一键注册</p></a>
				    </p>
		        </c:if>
           </div><!-- modal-body -->
           <div class="modal-footer">
           	   <p class="col-md-4"></p>
           	   <p class="col-md-4" >
           	   	<button type="button" class="btn btn-default" id="save" ${loginUser!=null?'':'disabled="disabled"'}>保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
               </p>
           	   <p class="col-md-4">为分享而生</p>
           </div>
       </div>
   </div>
</div><!--modal fade  -->