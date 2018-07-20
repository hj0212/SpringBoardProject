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

fieldset {
	width: 500px;
	height: 200px;
	text-align: center;
	margin: 100px auto;
	padding: 50px auto;
}

legend {
	color: red;
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
	$(document).ready(function() {
		$("#delete").click(function() {
			location.href = "toDeleteArticleProc.bo?seq=" + ${result.seq};
		})
		$("#back").click(function() {
			location.href = "boardlist.bo";
		})
		$("#edit").click(function() {
			location.href = "toEditArticle.bo?seq=" + ${result.seq};
		})
		
		$("#commenttable").on("click","#commentremobtn", function() {
			var commentseq = $("#commentseq").val();
			location.href = "toRemoveComment.bo?seq=${result.seq}&comseq=" + commentseq;	
		})
	})
</script>
</head>
<body>
	<c:choose>
		<c:when test="${loginId eq null}">
			<div class="wrapper">
				<fieldset id="erorr">
					<legend>
						<h1>로그인이 필요한 페이지입니다</h1>
					</legend>
					<hr>
					<h5>3초 뒤 로그인 페이지로 전환됩니다</h5>
				</fieldset>
			</div>
			<script>
				setInterval(function() {
					location.href = "toLogin.do";
				}, 3000);
			</script>
		</c:when>
		<c:otherwise>
			<div id="wrapper">
				<div id="article">
					<div id="titleCon">
						<div id="title">
							<h3>${result.title}</h3>
						</div>
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
							<c:if test="${loginId == result.writer}">
								<button type="button" id="edit">수정</button>
								<button type="button" id="delete">삭제</button>
							</c:if>
						</div>
					</div>
				</div>
				<div id="commentlist">
					<table id="commenttable">
						<tr class="head">
							<th>댓글 내용</td>
							<th width="80">작성자</td>
							<th width="130">작성시간</td>
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
										<td align="left">
											<input type="hidden" id="commentseq" value="${comment.seq }"/>
											${comment.contents }
											<c:if test="${comment.writer eq loginId }">
											<button id="commentremobtn">삭제</button>
											</c:if>
										</td>
										<td>${comment.writer }</td>
										<td>${comment.writedate }</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
				</div>
				<form action="toAddComment.bo" method="post">
					<div id="comment">
						<input type="hidden" name="article_no" value="${result.seq }" />
						<textarea name="contents" id="comment_textarea" cols="90" rows="5"></textarea>
						<button id="commentbtn">등록</button>
					</div>
				</form>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>