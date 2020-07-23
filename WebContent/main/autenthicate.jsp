<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ANBD | 이메일 본인인증</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<%
	//브라우저 확인 [시작]
	String userAgent = request.getHeader("User-Agent");
	String browser = "";
	if(userAgent.contains("iPhone")||userAgent.contains("iPad")||userAgent.contains("iPot")||userAgent.contains("Android"))
		{browser = "Mobile";}
	else if(userAgent.contains("MSIE")||userAgent.contains("Trident"))
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
	else
		{browser = "UNKNOWN";}
	//브라우저 확인 [종료]
	String id = request.getParameter("id");
%>
<table align="center">
	<tr>
		<td align="center">
			<h4 id="authComplete"></h4>
			<h4 id="doLogin"><a href="../common/login.jsp">로그인하기</a></h4>
			<h1 id="ifMobile"></h1>
		</td>
	</tr>
</table>
<script>
$(document).ready(function()
	{
		var browser = "<%= browser %>";
		var id = "<%= id %>";
		$("#doLogin").hide();
		if(browser=="Mobile")
			{
				alert("모바일 환경에서 인증할 수 없습니다.\n웹 브라우저에서 다시 시도해 주세요");
				$("#ifMobile").html("모바일 환경에서 인증할 수 없습니다.<br/>웹 브라우저에서 다시 시도해 주세요");
				return false;
			}
		if(browser!="Mobile")
			{
				$("#doLogin").show();
				var HTML = "";
					HTML += "<font color='red'>"+id+"</font>님의 본인인증이 완료되었습니다<br/>";
					HTML += "메인으로 가려면 링크를 클릭해주세요<br/>";
				$("#authComplete").html(HTML);
				return;
			}
		else
			{
				alert("접근할 수 없는 브라우저입니다.\n인터넷익스플로러, 크롬, 사파리, 파이어폭스, 오페라로 접속해주세요");
			}
	})
</script>
</body>
</html>