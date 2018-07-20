<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<style>
div {
	box-sizing: border-box;
}

#wrapper {
	width: 700px;
	margin: 0px auto;
	text-align: center;
	box-sizing: border-box;
}

#article {
	width: 700px;
	text-align: center;
	margin: 0px auto;
}

#title {
	width: 100%;
	height: 30px;
	line-height: 26px;
}

#info {
	height: 24px;
}

#info div {
	float: left;
}

#contents {
	width: 100%;
	height: 500px;
	padding: 3px;
}

#commenttable {
	width: 100%;
	margin-top: 10px;
}

#comment {
	margin: 10px auto;
	height: 80px;
}

#commentbtn {
	height: 100%;
}

#comment_textarea {
	float: left;
	height: 100%;
	box-sizing: border-box;
}
</style>
<script>
$(document).ready(function(){
	$("#delete").click(function(){
		location.href="toDeleteArticleProc.bo?seq="+${result.seq};
	})
	$("#back").click(function(){
		location.href="boardlist.bo";
	})
	$("#edit").click(function(){
		location.href="toEditArticle.bo?seq="+${result.seq};
	})
})
</script>
</head>
<body>
	<div id="wrapper">
		<div id="article">
			<div id="titleCon">
				<div id="title"><h3>${result.title}</h3></div>
				<div id="info">
					<div id="writer">작성자 ${result.writer}</div>
					<div id="writedate">작성일 ${result.writedate}</div>
					<div id="viewcount">조회수 ${result.viewcount}</div>
				</div>
			</div>
			<div>
				<div id="contents" align="left">${result.contents}</div>
				<div>
					<button type="button" id="back">목록</button>
					<button type="button" id="edit">수정</button>
					<button type="button" id="delete">삭제</button>
				</div>
			</div>
		</div>
		<div id="commentlist">
			<table border="1" id="commenttable">
				<tr>
					<td>댓글 내용</td>
					<td width="100">작성자</td>
					<td width="150">작성시간</td>
				</tr>
				<c:choose>
					<c:when test="${empty commentlist }">
						<tr>
							<td colspan=3>작성된 댓글이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${commentlist }" var="comment">
							<tr>
								<td align="left">${comment.contents }</td>
								<td>${comment.writer }</td>
								<td>${comment.writedate }</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
		<form action="comment.bo" method="post">
			<div id="comment">
				<input type="hidden" name="article_no" value="${result.seq }" />
				<textarea name="contents" id="comment_textarea" cols="90" rows="5"></textarea>
				<button id="commentbtn">등록</button>
			</div>
		</form>
	</div>
</body>
</html>