<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="vo" class="anbd.AnbdVO" scope="page"/>
<jsp:useBean id="dao" class="anbd.AnbdDAO" scope="page"/>
<!-- CSS 총 4개 -->
	<link rel="stylesheet" href="../css/bootstrap.css"/>
	<link rel="stylesheet" href="../css/font-awesome.min.css"/>
	<link rel="stylesheet" href="../css/slicknav.min.css"/>

	<!-- Main CSS -->
	<link rel="stylesheet" href="../css/style.css"/>

<%
	String sido = request.getParameter("sido");
%>
<c:if test="${param.sido ne '기타'}">
 <%
	dao.selSigun(sido);
 	pageContext.setAttribute("selSigun", dao.getBoardList());
 %>
 
	<select name="sigun" id="sigun">
		<c:forEach items="${selSigun}" var="selSigun">
			<option value="${selSigun.jusoNo}:${selSigun.sigun}">${selSigun.sigun}</option>
		</c:forEach>
	</select>
</c:if>
