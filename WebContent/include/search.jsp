<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<body>
	<!--  <div class="container">-->
		<div class="hero-warp">
			<form class="main-search-form" action="../main/main.jsp" method="get" id="searchFrm" name="searchFrm">
				<!-- 검색 옵션 탭 -->
				<div class="search-type">
				    <!-- 각 옵션 탭별로 div -->
				    
					<!-- radio는 style.css에서 hidden되있음 -->
					<div class="st-item">
						<input type="radio" name="option" id="title" value="title" checked>
						<label for="title">제목</label>
					</div>
					<div class="st-item">
						<input type="radio" name="option" id="id" value="id">
						<label for="id">아이디</label>
					</div>
					<div class="st-item">
						<input type="radio" name="option" id="emailOption" value="emailOption">
						<label for="emailOption">이메일</label>
					</div>
				</div>
				
				<!-- 검색창, Ajax기술 활용 방안 -> 키워드 입력시 자동완성 -->
				<div class="search-input">
					<input type="text" placeholder="검색 키워드를 입력해주세요." name="key" id="key">
					<button class="site-btn" id="search">검색</button>
					<c:choose>
						<c:when test="${sessionScope.id eq null}">
							<input type="button" id="write" class="Wrt" value="글쓰기" onclick="javascript:doAlert();">
						</c:when>
						<c:when test="${sessionScope.id ne null}">
							<input type="button" id="write" class="Wrt" value="글쓰기" onclick="javascript:location.href='write.jsp'">
						</c:when>
					</c:choose>
					
				</div>
			</form>
		</div>
	<!-- </div>-->
