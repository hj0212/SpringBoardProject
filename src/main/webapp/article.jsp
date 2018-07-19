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

#contents {
	width: 100%;
	height: 500px;
}
</style>
</head>
<body>
	<div id="wrapper">
		<div id="article">
			<div id="titleCon">
				<div id="writer">${result.getWriter}</div>
				<div id="title">${result.getTitle}</div>
				<div id="writedate">${result.getWritedate}</div>
				<div id="viewcount">${result.getViewcount}</div>
			</div>
			<div>
				<div id="contents">${result.getContents}</div>
				<button type="button" id="back">목록</button>
				<button type="button" id="edit">수정</button>
				<button type="button" id="delete">삭제</button>
			</div>
		</div>
	</div>
</form>
</body>
</html>