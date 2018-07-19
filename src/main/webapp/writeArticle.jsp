<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<style>
#wrapper {
	margin: 0px auto;
	text-align: center;
	box-sizing: border-box;
}

#article {
	width: 500px;
	height: 600px;
	text-align: center;
	margin: 0px auto;
}

#title {
	width: 100%;
	height: 30px;
}

textarea {
	width: 100%;
	height: 500px;
}
</style>
<script>
$(document).ready(function(){
	$("#back").click(function(){
		location.href="boardlist.bo";
	})
})
</script>
</head>
<body>
<form action="toWriteArticleProc.bo" method="post">
	<div id="wrapper">
		<div id="article">
			<div>
				<input type="text" id="title" placeholder="제목을입력하세요" name="title">
			</div>
			<div>
				<textarea name="contents"></textarea>
				<button>작성</button>
				<button type="button" id="back">취소</button>
			</div>
		</div>
	</div>
</form>
</body>
</html>