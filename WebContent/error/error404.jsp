<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>

<!-- IE(Internet Explorer)가 자체적으로 에러페이지가 512 바이트 보다 작으면 IE자체의 에러페이지를 보여주기 때문이다.. -->
<% //response.setStatus(200); %>
<%
response.setStatus(HttpServletResponse.SC_OK);
%>

<html>
<head>
<meta charset="UTF-8">
<title>에러 404</title>
</head>
<body>

에러 404입니다!!!
	
에러 내용은 아래와 같습니다.

<P>
Following error found:
<I><%= exception %></I>. Stack Trace:
<PRE>
<%@ page import="java.io.*" %>
<% exception.printStackTrace(new PrintWriter(out)); %>


</body>
</html>

<!--
에러페이지 용량 채우기용 주석입니다.에러페이지 용량 채우기용 주석입니다.
에러페이지 용량 채우기용 주석입니다.에러페이지 용량 채우기용 주석입니다.
에러페이지 용량 채우기용 주석입니다.에러페이지 용량 채우기용 주석입니다.
-->