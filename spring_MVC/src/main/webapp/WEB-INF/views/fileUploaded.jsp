<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Uploaded File Infomation</h3>
	<ul>
		<li>title: ${title }</li>
		<li>original file name : ${originalFileName }</li>
		<li>uploaded file name : ${uploadedFileName }</li>
		<li>upload path : ${uploadPath }</li>
	</ul>
	
	<button onclick="location.href='fileUploadForm';">목록</button>
</body>
</html>