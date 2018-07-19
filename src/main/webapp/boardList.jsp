<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Board</title>
<style>
	div {
		box-sizing: border-box;
		border: 1px solid black;
	}
	#container {
		width: 900px;
		margin: 0 auto;

	}
	
	.center {
		margin: 0 auto;
		text-align: center;
	}
</style>
</head>
<body>

<%-- <c:choose>
	<c:when test="${empty list}">
		여기
	</c:when>
	<c:otherwise>
		저기
	</c:otherwise>
</c:choose> --%>

<div id="container">
	<div><h1 class="center">게시판</h1></div>
	<table border="1" class="center">
		<tr>
			<td>글번호</td>
			<td width="300px">글제목</td>
			<td width="100px">작성자</td>
			<td width="100px">작성일</td>
			<td>조회수</td>
		</tr>
		<c:choose>
			<c:when test="${empty list}">
				<tr>
				<td colspan=5> 게시글이 없습니다.
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list }" var="item">
					<tr>
						<td>${item.seq }</td>
						<td>${item.title }</td>
						<td>${item.writer }</td>
						<td>${item.writedate }</td>
						<td>${item.viewcount }</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>

</div>

</body>
</html>