<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/fix.jsp"%>

<jsp:useBean id="before" class="anbd.NextDAO" scope="page" />


<% /*
	int pNo = Integer.parseInt(request.getParameter("no"));
	
	dao.upStatusDone(pNo);
	
	response.sendRedirect("view.jsp?no="+pNo);*/
	
	
	
	int no = Integer.parseInt(request.getParameter("no"));
	
	int beforeNo = before.beforeNo(no);
	
	
	if(beforeNo==0){
	%>
	<script>
		alert("이전 글이 존재하지 않습니다.");
		location.href="view.jsp?no=<%= no %>"
	</script>
	<%
	}else{
		response.sendRedirect("view.jsp?no="+beforeNo);
	}
	
	

%>
		