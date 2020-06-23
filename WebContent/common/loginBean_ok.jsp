<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/fix.jsp"%>
<%	request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="joinBean" class="anbd.AnbdVO" scope="page" /> 
<jsp:useBean id="joinBean2" class="anbd.AnbdDAO" scope="page" /> <!-- 데이터베이스 mysql에서도 보이게 하는거. 값을 넘기고 받고 하는 거 -->

<jsp:setProperty name="joinBean" property="*"/>

<html>
<!--  <p> 아이디	 : <%=joinBean.getId() %>    </p>
<p> 비번 		 : <%=joinBean.getPw() %> 	 </p> -->

<!--  <% %> 열고 닫는 표시는 자바 언어만 쓸 수 있음. html쓰려면 닫고 다시 열기 -->
<% boolean result = joinBean2.selLogin(joinBean.getId(), joinBean.getPw()); 
	if(result==true){
		out.print(joinBean.getId() +  "님 로그인되었습니다.");
		
		//id 세션 생성
		String id = joinBean.getId();
		session.setAttribute("loginId", id);
		
%>
		<script>
			alert("로그인 되었습니다.");
			location.href="../main/main.jsp"
			//<a href="../main/main.jsp"> 메인으로 갈래? </a>
		</script>
<% 
	}else{
		out.print("아이디, 비밀번호를 확인해주세요.");
%> 		
		<script>
			alert("아이디, 비밀번호를 확인해주세요.");
			location.href="login.jsp"
		</script>
		<!-- <a href="login.jsp" > 로그인 페이지로 이동 </a> --> 	
<% 
	}
%>
</html>

