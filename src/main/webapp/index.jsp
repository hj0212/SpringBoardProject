<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	#wrapper {
		width: 150px;
		margin: 100px auto;
		text-align: center;
	}
</style>
</head>
<body>
<div id="wrapper">
	
<c:choose>
	<c:when test="${empty sessionScope.id }">
	<a href="toLogin.do">로그인</a>
	</c:when>
	<c:otherwise>
	<a href="boardlist.bo">게시판</a>
	</c:otherwise>
</c:choose>

</div>
</body>
</html>