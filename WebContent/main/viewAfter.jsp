<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@include file="../include/header.jsp"%>
<%@include file="../include/fix.jsp"%>

<jsp:useBean id="bofore" class="anbd.NextDAO" scope="page" />
 

 	<% 
 		 int no = Integer.parseInt(request.getParameter("no"));
 		 int afterNo = bofore.afterNo(no);   
 		 out.println(afterNo);
 		 
 		 response.sendRedirect("view.jsp?no=" + afterNo);
 		 //실행하고 오류 없으면 페이지 바로 이동하는 거 
 				 
 				 
 	%>