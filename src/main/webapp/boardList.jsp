<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Board</title>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<style>
div {
	box-sizing: border-box;
}

#container {
	width: 700px;
	margin: 0 auto;
}

table {
	width: 100%;
	margin: 20px auto;
	text-align: center;
}

.center {
	margin: 0 auto;
	text-align: center;
}

#searcharea {
	text-align: center;
}

#write {
	float: right;
}

#page {
	float: left;
}

#page a{
	text-decoration: none;
}
</style>
<script>
	$(document).ready(function() {
		$("write").click(function() {
			location.href = "";
		})
	})
</script>
</head>
<body>

	<div id="container">
		<div>
			<h1 class="center">게시판</h1>
		</div>
		<table border="1">
			<tr>
				<td>글번호</td>
				<td width="300px">글제목</td>
				<td width="100px">작성자</td>
				<td width="110px">작성일</td>
				<td>조회수</td>
			</tr>
			<c:choose>
				<c:when test="${empty list}">
					<tr>
						<td colspan=5>게시글이 없습니다.
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
		<div id="searcharea">
		<div id="page">${pageNavi}</div>
			<form action="search.bo">
				<input type="text" name="keyword" />
				<button id="searchbtn">검색</button>
			</form>
			<button id="write">글쓰기</button>
		</div>
	</div>

</body>
</html>