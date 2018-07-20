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
		<form action="joinProc.me" method="post" id="joinForm">
			<div>
				<input type="text" name="id" id="idtext" placeholder="아이디를 입력하세용"> <br>
				<input type="text" readonly id="idCheck"><br>
				<input type="password" name="pw" id="pwtext" placeholder="비밀번호를 입력하세용"> <br>
				<input type="text" name="email" id="emailtext" placeholder="이메일을 입력하세용"><br>

			</div>
			<div id="btns">				
				<button id="signB" type="button">회원 가입</button>
				<button  type="button" id="backB">뒤로 가기</button>
			</div>
		</form>
	</div>
	
	<script>
	$("#backB").click(function(){
		$(location).attr("href","login.jsp");	
	})
	
	$("#signB").click(function(){
		if($("#idtext").val()!="" && $("#pwtext").val()!="" && $("#emailtext").val()!=""){
			$("#joinForm").submit();
		}else{
			alert("빈칸을 채워주세요.");			
		}	
	})
	
	$("#idtext").blur(function(){
	
		$.ajax({
			url:"idCheck.me",
			type:"get",
			data:{id:$("#idtext").val()}, 
			success:function(data){		
				if(data == 0){
				/* 	$("#idCheck").text("사용가능한 아이디 입니다.");	 */
				alert("사용가능");
				}else{
				/* $("#idCheck").text("사용중인 아이디 입니다."); */
				alert("사용불가");
				}
				},
			error:function(){console.log("AJAX 실패 에러 발생");}  
		})
	})
	</script>
</body>
</html>