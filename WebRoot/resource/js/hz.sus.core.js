function editMyStar(url) {
	var url = $("#s-email").val();
	alert(url);
	$.get("/my/star/set", {
		url:url,
		v : 1
	}, function(data) {
		alert(data);
	});
}

function downloadClick(siid){
	$.post("/star/item/download/click", {
		siid : siid
	}, function(data) {
			$("#download-content-"+siid).css("display","block");
			$("#download-content-"+siid).html(data);
	});
}

function playClick(siid){
	$.post("/star/item/paly/click", {
		siid : siid
	}, function(data) {
		$("#play-content-"+siid).css("display","block");
		$("#play-content-"+siid).html(data);
	});
}