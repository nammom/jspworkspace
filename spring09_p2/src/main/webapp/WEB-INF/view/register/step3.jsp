<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 뷰step3</title>
</head>
<body>
	<p><strong>${registerRequest.name}</strong>님의 회원가입을 축하합니다.</p>
				<!--  커맨드객체의 클래스 이름(첫글자 소문자로 변경)을 사용해서 커맨드 객체를 뷰에전달-->
				<!-- @ModelAttribute("formData")로 커맨드 객체 이름 변경했다면 ${formData.name} -->
	<p><a href = "<c:url value= '/main'/>">[홈 화면 이동]</a>
</body>
</html>