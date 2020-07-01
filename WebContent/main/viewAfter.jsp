<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@include file="../include/header.jsp"%>
<%@include file="../include/fix.jsp"%>

<jsp:useBean id="before" class="anbd.NextDAO" scope="page" />
 

 	<% 

 		int no = Integer.parseInt(request.getParameter("no"));
 		int afterNo = before.afterNo(no);
		if(afterNo==0){
		%>
		<script>
			alert("다음 글이 존재하지 않습니다.");
			location.href="view.jsp?no=<%= no %>"
		</script>
		<%
		}else{
 		 	response.sendRedirect("view.jsp?no=" + afterNo);
 			 
 		 }
 		out.println(afterNo);
 		//실행하고 오류 없으면 페이지 바로 이동하는 거 
 	%>
 	

