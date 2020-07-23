<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<% response.setStatus(HttpServletResponse.SC_OK); //해당 코드가 생략되면 웹 브라우저는 자체적으로 제공하는 화면을 표시 %>

<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${requestScope['javax.servlet.error.status_code']}"> </c:out> 에러</title>
</head>
<body>
	<h4><c:out value="${requestScope['javax.servlet.error.status_code']}"> </c:out> 에러</h4>
	
	<c:if test="${requestScope['javax.servlet.error.status_code'] == 400}"> 
		<p>잘못 된 요청입니다.</p> 
	</c:if> 
	
	<c:if test="${requestScope['javax.servlet.error.status_code'] == 404}"> 
		<p>요청하신 페이지를 찾을 수 없습니다.</p> 
	</c:if> 
	
	<c:if test="${requestScope['javax.servlet.error.status_code'] == 405}"> 
		<p>요청된 메소드가 허용되지 않습니다.</p> 
	</c:if> 
	
	<c:if test="${requestScope['javax.servlet.error.status_code'] == 500}"> 
		<p>서버에 오류가 발생하여 요청을 수행할 수 없습니다.</p> 
	</c:if> 
	
	<c:if test="${requestScope['javax.servlet.error.status_code'] == 503}"> 
		<p>서비스를 사용할 수 없습니다.</p> 
	</c:if> 
	
	<a href="/anbd2/main/main.jsp">메인으로 돌아가기</a>
</body>
</html>

<!--
에러페이지 용량 채우기용 주석입니다.에러페이지 용량 채우기용 주석입니다.
에러페이지 용량 채우기용 주석입니다.에러페이지 용량 채우기용 주석입니다.
에러페이지 용량 채우기용 주석입니다.에러페이지 용량 채우기용 주석입니다.
-->