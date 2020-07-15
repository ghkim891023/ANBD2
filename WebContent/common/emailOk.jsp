<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../include/headerKgh.jsp"%>
<%@include file="../include/fix.jsp"%>

<% 
String id = request.getParameter("id");
dao.upUserEmailOk(id);
%>

<div style="margin-left: 50px">
	<br>
	<%=id%>님 이메일 인증이 완료되었습니다. 
	<br><br>
	로그인으로 이동하시려면 다음을 눌러주세요.
	<br><br>
	<a href="login.jsp">로그인하러 가기</a>
</div>

<script>
	//바로 로그인 페이지로 이동
	//location.href="login.jsp";
</script>


