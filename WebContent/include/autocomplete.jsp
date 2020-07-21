<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String key = request.getParameter("key");
	if(key.contains("개"))
	{
		out.print("개발자,개발이,개발은,개발을");
	}
	if(key.contains("나"))
	{
		out.print("나눔,무료나눔,무료 나눔,나눠,나눠드려요,나눔합니다");
	}
	if(key.contains("무"))
	{
		out.print("무료,무료나눔,무료 나눔");
	}
	if(key.contains("인"))
	{
		out.print("인형,인터넷");
	}
	if(key.contains("공"))
	{
		out.print("공지,공지입니다,공유,공짜");
	}
	if(key.contains("오"))
	{
		out.print("오피스텔");
	}
	if(key.contains("선"))
	{
		out.print("선착순");
	}
	if(key.contains("빌"))
	{
		out.print("빌려주실,빌라");
	}
	if(key.contains("사"))
	{
		out.print("사무실");
	}
	if(key.contains("제"))
	{
		out.print("제목입니다,제목");
	}
%>