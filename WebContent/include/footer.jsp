<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="footerDao" class="anbd.AnbdDAO" scope="page"/>
<body>
<%
//=======공지 [시작]
footerDao.selNotice();
pageContext.setAttribute("selNotice", footerDao.getBoardList());
//=======공지 [종료]
%>
	<!-- Footer Section -->
	<footer class="footer-section">
	<c:forEach items="${selNotice}" var="selNotice">
		<a href="/anbd2/view.do?no=${selNotice.no}&menu=notice">
			<img src="../img/logo.png" style="width:10%" alt="필독!공지입니다로 가는 로고입니다">
			필독! 공지입니다
		</a>
	</c:forEach>
		<div class="copyright">
			<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
			Copyright &copy;<script>document.write(new Date().getFullYear());</script>
			All rights reserved | This template is made with
			<i class="fa fa-heart-o" aria-hidden="true"></i> by
			<a href="https://colorlib.com" target="_blank">Colorlib</a>
			<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
		</div>
	</footer>
	<!-- Footer Section end -->

	<!--====== Javascripts & Jquery ====== 상단에 적으면 주방사진 안뜸 -->
	
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/jquery.slicknav.min.js"></script>
	<script src="../js/jquery.magnific-popup.min.js"></script>
	<script src="../js/main.js"></script>
	</body>
</html>

<%--= 1/0 --%>