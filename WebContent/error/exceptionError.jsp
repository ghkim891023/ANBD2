<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%= exception.getMessage() %> 에러</title>
</head>
<body>
	<h4><%= exception %></h4>
	
	<p>사이트 이용에 불편을 드려서 죄송합니다.</p>
	<p>다음과 같은 에러가 발생하였습니다.</p>
	
	Stack Trace:
	<PRE>
	<%@ page import="java.io.*" %>
	<% exception.printStackTrace(new PrintWriter(out)); %>
	</PRE>
</body>
</html>
