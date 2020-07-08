<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/fix.jsp"%>

<% 
	int coNo = Integer.parseInt(request.getParameter("coNo"));
	int no = Integer.parseInt(request.getParameter("no"));
	
	webutil.Init(request);
	int pageno = webutil._I("page","1");
	String menu   = webutil._S("menu","");
	String option = webutil._S("option","title");
	String Key = webutil._S("key","");
	String mEncodeKey = webutil._E("key","");
	
	String mParam  = "";
			 mParam += "page=" + pageno;
			 mParam += "&";
			 mParam += "no=" + no;
			 mParam += "&";
			 mParam += "menu=" + menu;
			 mParam += "&";
			 mParam += "option=" + option;
			 mParam += "&";
			 mParam += "key=" + mEncodeKey;
	
	dao.delDelComment(coNo);
	//response.sendRedirect("view.jsp?no="+no);
	response.sendRedirect("view.jsp?"+mParam);
%>

