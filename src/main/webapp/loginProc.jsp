<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:choose>
		<c:when test="${loginresult[0] eq null}">
			<script>
				alert("다시 로그인 해주세요.");
				location.href="login.jsp";
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert("방문을 환영합니당!");
				location.href="index.jsp";
			</script>
		</c:otherwise>
	</c:choose>

</body>
</html>