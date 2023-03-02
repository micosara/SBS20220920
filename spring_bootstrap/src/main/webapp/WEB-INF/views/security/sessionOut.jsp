<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	response.setStatus(302); %>

<script>
	alert("${message}");
	if(window.opener) {
		window.opener.parent.location.reload();
	}else{
		window.location.href="<%=request.getContextPath()%>";
	}
	window.close();
</script>