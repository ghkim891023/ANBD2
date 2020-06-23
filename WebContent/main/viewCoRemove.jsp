<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/fix.jsp"%>

<% 
	int coNo = Integer.parseInt(request.getParameter("coNo"));
	int no = Integer.parseInt(request.getParameter("no"));
	
	dao.delDelComment(coNo);
	response.sendRedirect("view.jsp?no="+no); 
%>

