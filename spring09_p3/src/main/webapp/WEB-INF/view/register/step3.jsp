<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="member.register"/></title>
</head>
<body>
	<%-- <p><strong>${registerRequest.name}</strong>님의 회원 가입을 축하합니다.</p> --%>
	<p>
	<spring:message code="register.done" arguments="${registerRequest.name}"/>
								   <!--  arguments = "문자열1", "문자열2", "문자열3"-->
	<p><a href="<c:url value='/main'/>">[<spring:message code="go.main"/>]</a></p>
	
</body>
</html>

		<!-- registerRequest 객체를 언제 만들었지? 했더니 
		스프링 MVC가 커맨드 객체의 클래스의 이름을  RegisterRequest(DTO)
		 첫글자를 소문자로 변경해서 커맨드 객체를 뷰로 전달함.. 소름 
		 
		 이름 바꾸기도 가능 -->
		 
		 
		
