<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/fix.jsp"%>

<jsp:useBean id="bofore" class="anbd.NextDAO" scope="page" />


<% /*
	int pNo = Integer.parseInt(request.getParameter("no"));
	
	dao.upStatusDone(pNo);
	
	response.sendRedirect("view.jsp?no="+pNo);*/
	
	
	
	int no = Integer.parseInt(request.getParameter("no"));
	
	int beforeNo = bofore.boforeNo(no);
	
	out.println(beforeNo);
	
	response.sendRedirect("view.jsp?no="+beforeNo);
%>
		