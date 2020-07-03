<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ page import="Captcha.*" %>
	<% 
	
	String lastKey = request.getParameter("lastKey");
	CaptchaImage capImage = new CaptchaImage();
	%>
	<%= capImage.mainMethod(lastKey) %>
	<%
		String fileName = capImage.fileName;
		//String filePath = capImage.filePath;
		String filePath = "/anbd2/captchaImage/";
		//String totalImageName = filePath+fileName+".jpg";
		String totalImageName = "../captchaImage/" + fileName+".jpg";
	%>
	<%= totalImageName %>