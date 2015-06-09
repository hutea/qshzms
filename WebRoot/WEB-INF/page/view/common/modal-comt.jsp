<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
<div class="modal fade" id="myModal-comt"  role="dialog" aria-label="myModalLabel" aria-hidden="true">
   <div class="modal-dialog modal-md">
       <div class="modal-content">
           <div class="modal-body">
           		<textarea id="comteditor" name="content" style="height: 100px;border: 1px solid #d5d5d5;"></textarea>
           		<input type="hidden" id="shareid_atcomt">
           </div><!-- modal-body -->
           <div class="modal-footer">
           	   <div id="comt-tip" class="text-primary text-center" style="display: none;"></div>
           	   <p class="col-md-12 text-center" >
           	   	<button type="button" class="btn btn-default" id="comt-save" onclick="comt_save()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
               </p>
           </div>
       </div>
   </div>
</div><!--modal fade  -->