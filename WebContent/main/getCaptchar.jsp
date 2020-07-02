<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" 
trimDirectiveWhitespaces="true" %>
<jsp:useBean id="ke" class="api.CaptchaKey" scope="page"/>
<jsp:useBean id="im" class="api.CaptchaImage" scope="page"/>
<jsp:useBean id="re" class="api.CaptchaResult" scope="page"/>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="net.sf.json.*" %>
<% 
String imagePath = request.getServletContext().getRealPath("img\\captchar") + "\\";
//out.print(imagePath);
im.imageMain(imagePath); //캡차 이미지 생성
String key = ke.key;
String imgFileName = im.imgFileName;
String pathFileName = imagePath+imgFileName;
//out.print(imgFileName);
//out.print("<br>");
//out.print(key);
//String strJson="{\"pathFileName\":\""+pathFileName+"\", \"key\":\""+key+"\"}";
String strJson="{pathFileName :\""+pathFileName+"\", key :\""+key+"\"}";
//strJson=strJson.substring(0,strJson.length()-1);
out.println(strJson);%>