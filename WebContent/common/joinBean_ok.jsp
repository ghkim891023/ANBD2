<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/fix.jsp"%>

<jsp:useBean id="joinBean" class="anbd.AnbdVO" scope="page" /> 
<jsp:useBean id="joinBean2" class="anbd.AnbdDAO" scope="page" /> <!-- 데이터베이스 mysql에서도 보이게 하는거. 값을 넘기고 받고 하는 거 -->
<!--jsp:useBean id="mail" class="api.JavaMail" scope="page" /-->

<jsp:setProperty name="joinBean" property="*"/>
<html>
<head>
</head>
<body>
	<p> 아이디	 : <%=joinBean.getId() %>    </p>
	<p> 이름 	 : <%=joinBean.getName() %>  </p>
	<p> 이메일     : <%=joinBean.getEmail() %> </p>
	
	<% 
		int i = joinBean2.inJoin(joinBean);
		
		String email = joinBean.getEmail();
		String id = joinBean.getId();
		mail.postMain(email, id);
		
		if(i!=1){ //1은 회원가입 성공 리턴값
			%>
				<script type="text/javascript">
					alert("회원가입에 실패하였습니다.");
					history.go(-1);
				</script>
			<%
		}else{ //회원가입 성공시
			//이메일 전송
			api.JavaMail.sendEmail(joinBean.getEmail(), joinBean.getId());
			%>
				<script type="text/javascript">
					//alert("회원가입에 성공하였습니다.");
					alert("이메일 인증 후 로그인해주세요.");
					location.href="login.jsp";
				</script>
			<%
		}
	%>
</body>
</html>

