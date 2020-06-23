<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/fix.jsp"%>

<% 
	request.setCharacterEncoding("utf-8");
	
	int coNo = Integer.parseInt(request.getParameter("coNo"));
	int no = Integer.parseInt(request.getParameter("no"));
	String content = request.getParameter("content");
	
	dao.upModifyComment(coNo, content);
	response.sendRedirect("view.jsp?no="+no); 
%>

