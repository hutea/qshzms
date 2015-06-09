CKEDITOR.dialog.add('himageDialog',
		function(editor) {
			return {
				title : '插入图片 HImage',
				minWidth : 400,
				minHeight : 200,
				contents : [ {
					id : 'tab-image-net',
					label : '网络图片',
					elements : [ {
						 	type: 'vbox',
						 	heights: [ '20%', '80%'],
						    children: [
						        {
						        	type: 'text',
						            label: '地址',
						            id:'himage-netimage-input-src',
						            labelLayout: 'horizontal',
						            widths: [20, 200],
						            onBlur:function (){
										var imgsrc =  this.getValue();
										var re =new RegExp("^.*[^a][^b][^c]\.(?:png|jpg|bmp|gif|jpeg)$"); 
										if(re.test(imgsrc)){
											$("#himage-netimage-image").attr("src",imgsrc);
										}else if(imgsrc.length>1){
											//alert("非图片地址");
										}
						        	}
						        },
			                    {
					            	type: 'text',
					            	label: '描述',
					            	id:'himage-netimage-input-detail',
					            	labelLayout: 'horizontal',
					            	widths: [20, 200]
					             },
			                     {
			                    	type: 'html',
							        html: '<span><img id="himage-netimage-image" style="width:400px;height:262px" src="/resource/image/preview_net.png"></span>',
			                     }
						    ]
					} ]
				}, {
					id : 'tab-image-local',
					label : '本地上传',
					elements : [ 
						{
							type: 'html',
			            	html: '<input id="himage" type="file" name="himage" onchange="imageUpload()">'
			            		 +'<span id="himage-tip" style="color:red;display:none;">正在上传...</span>',
						},
						{
							type: 'html',
			            	html: '<img id="himage-upload-image" style="width:400px;height:300px" src="/resource/image/preview_local.png">',
						},
						{
							type: 'html',
							html:'',
						},
					]
				} ],
				onOk : function() {
					var dialog = this;
					//本地图片
					/*
					var localImg = editor.document.createElement('img');
					var localImgSrc =$("#himage-upload-image").attr("src");
					if(localImgSrc.indexOf("preview_local.png")==-1){
						localImg.setAttribute('src',localImgSrc);
						localImg.setAttribute('title','其实没事www.qishimeishi.com');
						localImg.setAttribute('class','img-thumbnail');
						editor.insertElement(localImg);
					}*/
					var localImgSrc =$("#himage-upload-image").attr("src");
					if(localImgSrc.indexOf("preview_local.png")==-1){
						editor.insertHtml( '<img src="'+localImgSrc+'" title="其实没事www.qishimeishi.com" class="img-thumbnail" />');
					} 
					
					//网络图片
					/*
					var netImg = editor.document.createElement('img');
					var netImgSrc = $("#himage-netimage-image").attr("src"); 
					if(netImgSrc.indexOf("preview_net.png")==-1){
						netImg.setAttribute('src',netImgSrc);
						var title = $("#himage-netimage-input-title").attr("value");
						netImg.setAttribute('title',title);
						netImg.setAttribute('class','img-thumbnail');
						editor.insertElement(netImg);
					}*/
					var netImgSrc= $("#himage-netimage-image").attr("src"); 
					if(netImgSrc.indexOf("preview_net.png")==-1){
						var title = $("#himage-netimage-input-title").attr("value");
						editor.insertHtml( '<img src="'+netImgSrc+'" title="'+title +'" class="img-thumbnail" />');
					}
					
				}
			};
		});


/**
	 $.ajaxFileUpload({
url: '/sus/share/upload/image', //用于文件上传的服务器端请求地址
type:'post',
data:{name:"jack",age:"20"},
secureuri: false, //是否需要安全协议，一般设置为false
fileElementId: 'himage', //文件上传域的ID
dataType: 'json', //返回值类型 一般设置为json
success: function (data, status)  //服务器成功响应处理函数
{
    $("#img1").attr("src", data.url);
    if (typeof (data.error) != 'undefined') {
        if (data.error != '') {
            alert(data.error);
        } else {
            alert(data.msg);
        }
    }
},
error: function (data, status, e)//服务器响应失败处理函数
{
    alert(e);
}
});
*/