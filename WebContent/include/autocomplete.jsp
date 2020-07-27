<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String key = request.getParameter("key");
	if(key.contains("개"))
	{
		out.print("개발자,개발이,개발은,개발을,개구리,개인적,개인,개발되다,개방");
	}
	else if(key.contains("나"))
	{
		out.print("나눔,무료나눔,무료 나눔,나눠,나눠드려요,나눔합니다,나오다,나가다,나이,나타나다,나누다,나이");
	}
	else if(key.contains("무"))
	{
		out.print("무료,무료나눔,무료 나눔,무겁다,무게,무릎,무늬,무용,무조건,무역");
	}
	else if(key.contains("인"))
	{
		out.print("인형,인터넷,인사,인천,인간,인상,인기");
	}
	else if(key.contains("공"))
	{
		out.print("공지,공지입니다,공유,공짜,공부,공원,공휴일,공항,공책,공간,공기");
	}
	else if(key.contains("오"))
	{
		out.print("오피스텔,오른쪽,오늘,오전,오랜만,오렌지,오후");
	}
	else if(key.contains("선"))
	{
		out.print("선착순,선물,선생님,선택,선배,선배님,선수,선풍기,선물하다");
	}
	else if(key.contains("빌"))
	{
		out.print("빌려주실,빌라,빌리다,빌딩,빌보드,빌리지");
	}
	else if(key.contains("사"))
	{
		out.print("사무실,사람,사이,사장,사과,사랑,사전,사용,사용감");
	}
	else if(key.contains("제"))
	{
		out.print("제목입니다,제목,제주도,제품,제한,제일,제공");
	}
%>