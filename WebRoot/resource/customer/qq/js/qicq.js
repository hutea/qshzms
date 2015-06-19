$("#qicq_add").click(function(){
	 $("#modal_qicq_add").modal({
	        backdrop: false,//点击空白处模态框消失
	 });
})

$("#save").click(function(){
	var qicq = $("#modal_qicq_add form input[name=qicq]").val();
	var money = $("#modal_qicq_add form input[name=money]").val();
	var top = $("#modal_qicq_add form select[name=top]  option:selected").val();
	var detail = $("#modal_qicq_add form textarea[name=detail]").val();
	var page = $("#page").val();
	var queryQicq = $("#queryQicq").val();
	$.post("/manage/qicq/save", {
		qicq : qicq,
		money : money,
		top : top,
		detail : detail,
		queryQicq:queryQicq
	}, function(data) {
		var obj = eval('(' + data + ')'); 
		if(obj.sign=='SUCCESS'){
			window.location.href=obj.url; 
		}else{
			alert("数据更新异常！");
		}
	});
})

function update(id){
	 var qicq = $("#qicq_"+id).attr("data-value");
	 var money = $("#money_"+id).attr("data-value");
	 var top = $("#top_"+id).attr("data-value");
	 var online = $("#online_"+id).attr("data-value");
	 var detail = $("#detail_"+id).attr("data-value");
	 //初始化赋值
	 $("#modal_qicq_edit form input[name=qicq]").val(qicq);
	 $("#modal_qicq_edit form input[name=money]").val(money);
	 $("#modal_qicq_edit form select[name=top]").val(top);
	 $("#modal_qicq_edit form select[name=online]").val(online);
	 $("#modal_qicq_edit form textarea[name=detail]").val(detail);
	 $("#modal_qicq_edit form input[name=id]").val(id);
	 $("#modal_qicq_edit").modal({
	        backdrop: false,//点击空白处模态框消失
	 });
	 
}

$("#update").click(function(){
	 var id = $("#modal_qicq_edit form input[name=id]").val();
	 var qicq = $("#modal_qicq_edit form input[name=qicq]").val();
	 var money = $("#modal_qicq_edit form input[name=money]").val();
	 var top = $("#modal_qicq_edit form select[name=top]  option:selected").val();
	 var online = $("#modal_qicq_edit form select[name=online]  option:selected").val();
	 var detail = $("#modal_qicq_edit form textarea[name=detail]").val();
	 
	 var page = $("#page").val();
	 var queryQicq = $("#queryQicq").val();
	 $.post("/manage/qicq/update", {
		id:id,
		qicq : qicq,
		money : money,
		top : top,
		online:online,
		detail : detail,
		page:page,
		queryQicq:queryQicq
	}, function(data) {
		var obj = eval('(' + data + ')'); 
		if(obj.sign=='SUCCESS'){
			window.location.href=obj.url; 
		}else{
			alert("数据更新异常！");
		}
	});
})