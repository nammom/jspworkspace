<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 뷰step2</title>
</head>
<!-- spring form 태그를 사용하려면 반드시 커맨드 객체가 존재해야한다  step1->step2로 요청이 넘어올때 커맨드 객체가 모델에 담겨야 하는 것이다. -->

<body>
	
	<h2>회원정보입력</h2>

	<form:form action= "step3" commandName = "registerRequest">
	<p>	
		<label>이메일 : <br>
		<form:input path="email"/>
		</label>
	</p>	
	<p>	
		<label>이름 : <br>
		<form:input path="name"/>
		</label>
	</p>
	<p>	
		<label>비밀번호 : <br>
		<form:password path="password"/>
		</label>
	</p>
	<p>	
		<label>비밀번호 확인 : <br>
		<form:password path="confirmPassword"/>
		</label>
	</p>	
	<input type="submit" value ="가입완료">
	</form:form>
</body>
</html>