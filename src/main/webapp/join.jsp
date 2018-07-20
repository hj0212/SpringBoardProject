<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

<style>
@import url(https://fonts.googleapis.com/css?family=Roboto:300);

.login-page {
  width: 360px;
  padding: 8% 0 0;
  margin: auto;
}
.form {
  position: relative;
  z-index: 1;
  background: #FFFFFF;
  max-width: 360px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
.form button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #4CAF50;
  width: 100%;
  border: 0;
  padding: 15px;
  color: #FFFFFF;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}
.form button:hover,.form button:active,.form button:focus {
  background: #43A047;
}
.form .message {
  margin: 15px 0 0;
  color: #b3b3b3;
  font-size: 12px;
}
.form .message a {
  color: #4CAF50;
  text-decoration: none;
}
.form .register-form {
  display: none;
}
.container {
  position: relative;
  z-index: 1;
  max-width: 300px;
  margin: 0 auto;
}
.container:before, .container:after {
  content: "";
  display: block;
  clear: both;
}
.container .info {
  margin: 50px auto;
  text-align: center;
}
.container .info h1 {
  margin: 0 0 15px;
  padding: 0;
  font-size: 36px;
  font-weight: 300;
  color: #1a1a1a;
}
.container .info span {
  color: #4d4d4d;
  font-size: 12px;
}
.container .info span a {
  color: #000000;
  text-decoration: none;
}
.container .info span .fa {
  color: #EF3B3A;
}
body {
  background: #76b852; /* fallback for old browsers */
  background: -webkit-linear-gradient(right, #76b852, #8DC26F);
  background: -moz-linear-gradient(right, #76b852, #8DC26F);
  background: -o-linear-gradient(right, #76b852, #8DC26F);
  background: linear-gradient(to left, #76b852, #8DC26F);
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;      
}
</style>


<title>Insert title here</title>

</head>

<body>
<div class="login-page">
  <div class="form">
    <form class="login-form" action="joinProc.me" method="post" id="joinForm">
      <input type="text" placeholder="아이디를 입력하세용" name="id" id="idtext"/>
      <input type ="text" readonly id="idCheck" placeholder="**아이디 확인 중..."/>
      <input type="password" placeholder="비밀번호를 입력하세용" name="pw" id="pwtext"/>
       <input type="text" placeholder="이메일을 입력하세용" name="email" id="emailtext"/>
      <button id="signB">가 입</button>
      <p class="message">가입하기 싫으세요? <a href="login.jsp">뒤로 가기</a></p>
    </form>
  </div>
</div>
<script>
$('.message a').click(function(){
	   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
	});
	
$("#signB").click(
		function() {
			if ($("#idtext").val() != "" && $("#pwtext").val() != ""
					&& $("#emailtext").val() != "") {
				$("#joinForm").submit();
			} else {
				alert("빈칸을 채워주세요.");
			}
		})

$("#idtext").blur(function() {

	$.ajax({
		url : "idCheck.me",
		type : "get",
		data : {
			id : $("#idtext").val()
		},
		success : function(data) {
			if (data == 0) {
				$("#idCheck").css("color","blue");
				$("#idCheck").val("사용가능한 아이디 입니다.");
			} else {
				$("#idCheck").css("color","red");
				$("#idCheck").val("사용중인 아이디 입니다.");
			}
		},
		error : function() {
			console.log("AJAX 실패 에러 발생");
		}
	})
})
</script>
</body>
</html>