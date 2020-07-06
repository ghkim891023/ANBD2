<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="anbd.DbInfo" %>
<%@ page import="anbd.AnbdVO" %>
<%@ page import="anbd.AnbdDAO" %>
<jsp:useBean id="vo" class="anbd.AnbdVO" scope="page"/>
<jsp:useBean id="db" class="anbd.DbInfo" scope="page"/>
<jsp:useBean id="dao" class="anbd.AnbdDAO" scope="page"/>
<%
//==========확인용, 원래는 내가 쓴 글로 넘어감==========
request.setCharacterEncoding("utf-8");
String id = (String)session.getAttribute("loginId");
dao.selLoginUserNo(vo, id);
String uploadPath = request.getRealPath("/upload");
vo.setUploadPath(uploadPath);
dao.inWrite(vo, request, vo.getLoginUserNo());

String[] sido = request.getParameterValues("sido");
for(int i=0; i<sido.length; i++)
{
	out.print("시도 = "+sido[i]);
}
%>${vo.getNo()}<br/>
