<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.io.*" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/fix.jsp"%>

<% 
	int pNo = Integer.parseInt(request.getParameter("no"));
	//vo.setNo(pNo);

	//String savePath = application.getRealPath("/upload");
	//out.print(savePath);
	
	int size = 1024*1024*10; //10MB
	
	//MultipartRequest multi = new MultipartRequest(request, savePath, size, "utf-8", new DefaultFileRenamePolicy());
	
	//upModifyBoard_v1
	//String pMenu = multi.getParameter("menu");
	//vo.setMenu(pMenu);
	
	//String pTitle = multi.getParameter("title");
	//vo.setTitle(pTitle);
	//out.print("<br/>"+pTitle);
	
	//String pContent = multi.getParameter("content");
	//vo.setContent(pContent);
	//out.print("<br/>"+pContent);
	
	boolean modifyResult = dao.upModifyBoard(vo, request);
	if(modifyResult == true){
		out.print("수정이 완료되었습니다.");
	}else{
		out.print("수정에 실패하였습니다.");
	}
	response.sendRedirect("view.jsp?no="+pNo);
	
%>

<br/><a href="view.jsp?no=<%=pNo%>">글보기 이동</a>
<br/><%--=vo.getTitle() --%>
<br/><%--=vo.getContent() --%>