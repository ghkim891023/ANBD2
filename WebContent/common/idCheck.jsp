<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="EUC-KR" %>
<%@ page import="anbd.AnbdDAO" %>
<%	request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="idc" class="anbd.AnbdVO" scope="page"/>

<script language="javascript">
function doUse(){
	opener.isMemberCheck = 1; //부모창에 값 전달
	window.close();
}
</script>

<!DOCTYPE html>
<html lang="en">
<title>아이디 중복검사</title>
<%
	String id = request.getParameter("id");  //입력 값을 파라미터로 넘겨받아 id에 저장
	anbd.AnbdDAO mDAO = new anbd.AnbdDAO();

	boolean result = mDAO.selIdCheck(id);      //id값을 매개변수로 confirmId 메소드를 호출하여 그 결과값을 result에 저장

	if(result){ 
		%>  
		<div style="text-align:center;">
		<br><br><br>
		<h4>이미 사용 중인 아이디입니다.</h4>
		</div>
		<center><a href="JavaScript:window.close()">아이디 다시 입력</a></center>
		<%
		
	}else{ 
		%>
		<div style="text-align:center;">
		<br><br><br>
		<h4>입력하신 <%= id %>는 사용할 수 있는 <br>아이디입니다.</h4>
		</div>
		<center><a href="JavaScript:doUse()">사용하기</a></center>
		<%
	}
%> 

</html>
