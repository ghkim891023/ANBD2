<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ page import="Captcha.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Real estate HTML Template">
<meta name="keywords" content="real estate, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=10">
	
	<link href="../img/anbd favicon.ico" rel="shortcut icon"/>

	<!-- Google font 글씨체 -->
	<link href="https://fonts.googleapis.com/css?family=Lato:400,400i,700,700i,900%7cRoboto:400,400i,500,500i,700,700i&display=swap" rel="stylesheet">

 	<!-- CSS 총 4개 -->
	<link rel="stylesheet" href="../css/bootstrap.css"/>

	<!-- Main CSS -->
	<link rel="stylesheet" href="../css/style.css"/>
	
	<script src="../js/jquery-3.2.1.min.js"></script>
	<style>
		input
		{width: 300px;height: 60px;}
	</style>
<title>ANBD | 아나바다-자동수집 방지</title>
</head>
<body>
<%
CaptchaKey capKey = new CaptchaKey();
String keyOrigin = "";
String jsonKey = capKey.mainMethod(keyOrigin);
String lastKey = jsonKey.substring(8, 24);
%>
	<form action="" method="POST" name="" id="">
		<img id="captcha" width="300px" height="60px" src="" /><br/>
		<input type="text" name="prevent" id="prevent" placeholder="숫자 또는 영문을 입력하세요"><br/>
		<input type="submit" class="site-btn" value="이메일 보기">
	</form>
	<script>
		$(document).ready(function()
			{
			var lastKey = '<%= lastKey %>';
				$.ajax
				({
					type:"get",
					url:"emailViewGarbage2.jsp",
					data: "lastKey="+lastKey,
					processData: false,
					contentType: false,				
					dataType:"html",
					success: function (data) 
					{
						var data = data+"";
						$("#captcha").attr("src", data);
						alert("이미지 캡차 로딩 완료");
		            },
		            error: function(request, status, error)
		            {
		            	alert(request.error);
		            }
					
				});//ajax FLOW	
			})
	</script>
</body>
</html>