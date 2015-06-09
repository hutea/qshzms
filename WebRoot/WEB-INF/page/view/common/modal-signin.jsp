<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
<div class="modal fade" id="myModal-signin"  role="dialog" aria-label="myModalLabel" aria-hidden="true">
   <div class="modal-dialog modal-sm">
       <div class="modal-content">
           <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                    </button>
           </div>
           <div class="modal-body">
           		<form>
                    <div class="form-group">
                        <label class="control-label">邮箱</label>
                        <input type="text" class="form-control" id="signin_email">
                    </div>
                    <div class="form-group pus ">
                        <label class="control-label">密码 <a href="/user/pwd/find">找回密码</a></label>
                        <input type="password" class="form-control" id="signin_pwd">
                    </div>
               </form>
               <p id="signin_tip"></p>
           </div><!-- modal-body -->
           <div class="modal-footer">
           	   <p class="col-md-12 text-center" >
           	   	<button type="button" class="btn btn-default" onclick="signin()">登录</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
               </p>
           </div>
       </div>
   </div>
</div><!--modal fade  -->