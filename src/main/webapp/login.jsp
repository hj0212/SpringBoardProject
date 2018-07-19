<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

<style>
button {
	background-color: none;
	background-color: white;
	margin-right:25px;
}

button:hover {
	background-color: skyblue;
}

input {
	margin-top: 10px;
	margin-bottom: 10px;
	text-align:center;
}

#wrap {
	width: 400px;
	height: 400px;
	margin: 100px auto;
}

</style>

</head>
<body>
	<div id="wrap">
		<form action="loginProc.me" method="post">
			<div>
				<input type="text" name="id" placeholder="아이디를 입력하세용"> <br>
				<input type="text" name="pw" placeholder="비밀번호를 입력하세용"> <br>

			</div>
			<div id="btns">
				<button id="loginB">로 그 인</button>
				<button type="button" id="joinB">회원 가입</button>
			</div>
		</form>
	</div>
	
	<script>
	$("#joinB").click(function(){
		$(location).attr("href","join.me");
	})
	</script>
</body>
</html>