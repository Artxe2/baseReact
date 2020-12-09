<%@ page language='java' contentType='text/html; charset=UTF-8'
	pageEncoding='UTF-8'%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>Insert title here</title>
</head>
<script type='text/javascript'>
	document.addEventListener('DOMContentLoaded', (e) => {
		document.querySelector('#eve_img').addEventListener('change', (e) => {
			document.querySelector('#pre_view').innerHTML = '';
			let files = e.target.files;
			let image = document.createElement('img');
			let reader = new FileReader();
			reader.onload = ((img) => {
				return (e) => {
					img.src = e.target.result
					img.width = 450;
					img.height = 300;
				}
			})(image)
			if (files) {
				for (let i = 0; i < files.length; i++) {
					if (files[i].type && files[i].type.indexOf('image') > -1) {						
						reader.readAsDataURL(files[i]);
						break;
					}
				}
			}
			document.querySelector('#pre_view').appendChild(image);
		});
	});
</script>
<body>
	<div id='pre_view'></div>
	<form id='upload_form' method='post' enctype='multipart/form-data'
		action='<%=request.getContextPath() %>/base/file_upload'>
		<input type='file' id='eve_img' name='i_file'><br>
		<input type='text' name='i_text'> 
		<input type='submit' value='Send'>
	</form>
	<div style='margin-bottom: 20px'></div>
	<form id='download_form' method='get' action='<%=request.getContextPath() %>/base/file_download'>
		<input type='text' value='base_files/고기.gif' name='file_path'>
		<input type='submit' value='Download'>
	</form>
	</body>
</html>