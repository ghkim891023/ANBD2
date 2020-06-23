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
					<%
						//세션 변수에 저장된 userId값이 비어있으면 로그인 안한것
						if(session.getAttribute("loginId")==null)
						{
							%>
								<input type="button" id="write" class="Wrt" value="글쓰기" onclick="javascript:doAlert();">
							<%
						}
						else
						{
							String loginId = (String)session.getAttribute("loginId");
							//out.print(session.getAttribute("loginId"));
							%>
							<input type="button" id="write" class="Wrt" value="글쓰기" onclick="javascript:location.href='write.jsp'">
							<%
						}
					%>
				</div>
			</form>
		</div>
	<!-- </div>-->
