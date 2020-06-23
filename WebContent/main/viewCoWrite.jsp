<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/fix.jsp"%>

<% 
	request.setCharacterEncoding("utf-8");
	
	//int pNo = Integer.parseInt(request.getParameter("no"));
	//String pUserNo = request.getParameter("loginUserNo");
	
	int pNo = Integer.parseInt(request.getParameter("no"));
	int pUserNo = Integer.parseInt(request.getParameter("userNo"));
	
	String pContent = request.getParameter("comment");
	
	dao.inSaveComment(vo, pNo, pUserNo, pContent);
	
	out.println("pNo: "+pNo);
	out.println("pUserNo: "+pUserNo);
	out.println("pContent: "+pContent);
	
	response.sendRedirect("view.jsp?no="+pNo);
%>
		