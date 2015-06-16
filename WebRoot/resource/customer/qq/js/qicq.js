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
	$.post("/qshzms/manage/qicq/save", {
		qicq : qicq,
		money : money,
		top : top,
		detail : detail
	}, function(data) {
		
	});
})

function update(id){
	
	 $("#modal_qicq_edit").modal({
	        backdrop: false,//点击空白处模态框消失
	 });

	 var qicq = $("#modal_qicq_add form input[name=qicq]").val();
	 var money = $("#modal_qicq_add form input[name=money]").val();
	 var top = $("#modal_qicq_add form select[name=top]  option:selected").val();
	 var detail = $("#modal_qicq_add form textarea[name=detail]").val();
	 $.post("/manage/qicq/save", {
		qicq : qicq,
		money : money,
		top : top,
		detail : detail
	}, function(data) {
		
	});
}