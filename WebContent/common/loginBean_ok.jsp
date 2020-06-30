<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/fix.jsp"%>
<jsp:setProperty name="vo" property="*"/>
<%	
request.setCharacterEncoding("utf-8"); 
boolean result1 = dao.selLogin(vo.getId(), vo.getPw());
pageContext.setAttribute("result", result1);
%>
<html>
<h1>This format is jstl</h1>
<h4>Use pageContext object</h4>
	결과 = [${result}]<br/>
	아이디 = ${vo.getId()}<br/>
	비밀번호 = ${vo.getPw()}<br/>
	<c:choose>
		<c:when test="${result eq true}">
			This id & password is true!<br/>
			<% session.setAttribute("id", vo.getId()); %>
			${sessionScope.id }님이 로그인했음
			<script language="javascript">
				location.href="../main/main.jsp";
			</script>
		</c:when>
		<c:when test="${result eq false}">
			This id & password is NOT true!<br/>
			PLEASE TRY AGIN<br/>
			<script language="javascript">
				alert("승인할 수 없는 아이디/비밀번호입니다");
				location.href="login.jsp";
			</script>
		</c:when>
	</c:choose>
</html>

