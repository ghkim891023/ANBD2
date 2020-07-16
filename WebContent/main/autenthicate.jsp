<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ANBD | 이메일 본인인증</title>
</head>
<body>
<%
	//브라우저 확인 [시작]
	String userAgent = request.getHeader("User-Agent");
	String browser = "";
	if(userAgent.contains("MSIE")||userAgent.contains("Trident"))
		{browser = "IE";}
	else if(userAgent.contains("Opera")||userAgent.contains("OPR"))
		{browser = "Opera";}
	else if(userAgent.contains("Firefix"))
		{browser = "Firefix";}
	else if(userAgent.contains("Safari"))
		{
			if(userAgent.contains("Chrome"))
			{browser = "Chrome";}
			else{browser = "Safari";}
		}
	else if(userAgent.contains("iPhone")||userAgent.contains("iPad")||userAgent.contains("iPot")||userAgent.contains("Android"))
		{
			browser = "Mobile";
		}
	//브라우저 확인 [종료]
	String id = request.getParameter("id");
%>
<h1>본인인증하러 오셨습니까!</h1>
<h1>접속 브라우저 = <%= browser %></h1>
<h4 id="authComplete"></h4>
<a href="../common/login.jsp">로그인하기</a>
<script>
	var browser = "<%= browser %>";
	var id = "<%= id %>";
	if(browser=="Mobile")
		{
			alert("모바일 환경에서 인증할 수 없습니다.\n웹 브라우저에서 다시 시도해 주세요");
		}
	if(browser!="Mobile")
		{
			var HTML = "";
				HTML += "<font color='red'>"+id+"</font>님의 본인인증이 완료되었습니다<br/>";
				HTML += "메인으로 가려면 링크를 클릭해주세요<br/>";
			$("#authComplete").html(HTML);
		}
</script>
</body>
</html>