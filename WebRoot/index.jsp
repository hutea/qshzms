<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
var picPath;
var image;
// preview picture
function preview()
{
 document.getElementById('preview').style.display = 'none';
 
		//验证上传文件格式是否正确 
		var pos = picPath.lastIndexOf("."); 
		var lastname = picPath.substring(pos, picPath.length) 
		if (lastname.toLowerCase() != ".jpg") { 
			alert("您上传的文件类型为" + lastname + "，图片必须为 JPG 类型"); 
			return false; 
		} 
		
		//验证上传文件宽高比例 
		if (image.height / image.width > 1.5 || image.height / image.width < 1.25) { 
			alert("您上传的图片比例超出范围，宽高比应介于1.25-1.5"); 
			return; 
		}
		 
		//验证上传文件是否超出了大小 
		if (image.fileSize / 1024 > 150) { 
			alert("您上传的文件大小超出了150K限制！"); 
			return false; 
}
	 	// 下面代码用来获得图片尺寸，这样才能在IE下正常显示图片
 		document.getElementById('box').innerHTML = "<img width='"+image.width+"' height='"+image.height+"' id='aPic' src='"+picPath+"'>";
 
}
// show view button
	function buttonShow()
	{
		 /*
		 这里用来解决图片加载延时造成的预览失败.
		 简单说明一下，当image对象的src属性发生改变时JavaScript会重新给image装载图片内容，
		 这通常是需要一些时间的，如果在加载完成之前想将图片显示出来就会造成错误，所以我们
		 通过图片的宽度和高度来判断图片是否已经被成功加载，加载完毕才会显示预览按钮.
		 这里我仍然有一个困惑，在IE7下预览效果偶尔会失效.
		 */
		 if ( image.width == 0 || image.height == 0 ) {
		 	setTimeout(buttonShow, 1000);
		 } else {
		 	document.getElementById('preview').style.display = 'block';
		 }
	}
	/*加载图片*/
	function loadImage(ele) {
		 picPath = getPath(ele);
		 image = new Image();
		 image.src = picPath;
		 /*一秒钟后调用显示预览按钮函数*/
		 setTimeout(buttonShow, 1000);
	}
	 
	//得到图片的完整路径
	function getPath(obj)
	{
		 if(obj)
		 {
			 //ie
			 if (window.navigator.userAgent.indexOf("MSIE")>=1)
		 	{
		 		obj.select();
		 		// IE下取得图片的本地路径
				return document.selection.createRange().text;
			 }
			 //firefox
			 else if(window.navigator.userAgent.indexOf("Firefox")>=1)
			 {
			 	if(obj.files)
			 	{
				 // Firefox下取得的是图片的数据
				return obj.files.item(0).getAsDataURL();
			 	}
			 	return obj.value;
			 }
		 return obj.value;
		 }
	}
</script>
</head>
<body>

 
</body>
</html>

