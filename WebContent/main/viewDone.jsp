<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/fix.jsp"%>

<% 
	int pNo = Integer.parseInt(request.getParameter("no"));
	
	dao.upStatusDone(pNo);
	response.sendRedirect("view.jsp?no="+pNo);
%>
		