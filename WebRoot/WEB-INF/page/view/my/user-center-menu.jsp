<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
<div class="leftmenu col-md-3">
     <div class="list-group affixed-element-top js-affixed-element-top">
         <a href="/my/center" class=" list-group-item  ${mym==1?'active':''}">帐号管理</a>
         <a href="/my/share" class="list-group-item  ${mym==2?'active':''}">我的分享</a>
         <a href="/my/comment" class="list-group-item  ${mym==3?'active':''}">评论记录</a>
         <a href="/my/report" class="list-group-item  ${mym==4?'active':''}">举报记录</a>
         <a href="#" class="list-group-item  ${mym==5?'active':''}">明星资源</a>
     </div>
</div>