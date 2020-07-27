<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="webutil2" class="anbd.WebUtil" scope="page"/>
<style>
	.search-input
	{margin-bottom:0px;}
</style>
<%
	webutil2.Init(request);
	
	String menu   = webutil2._S("menu","");
	String option = webutil2._S("option","title");
	String mKey2  = webutil2._S("key","");
	
	/*선생님께서 알려주시기 전 코드
	String menu = request.getParameter("menu");
	if(menu==null){
		menu="";
	}
	String mKey2 = request.getParameter("key"); 
	if(mKey2==null){ //그냥 검색안하면 null
		mKey2="";
	}
	String option = request.getParameter("option"); 
	if(option==null){
		option="title"; 
	}*/ 
%>
<body>
	<!--  <div class="container">-->
		<div class="hero-warp">
			<form class="main-search-form" action="/anbd2/main/main.jsp" method="get" id="searchFrm" name="searchFrm">
				<!-- 검색 옵션 탭 -->
				<div class="search-type">
				    <!-- 각 옵션 탭별로 div -->
				    
				    <!-- 메뉴 히든 -->
				    <input type="hidden" name="menu" id="menu" value="<%=menu %>">
				    
					<!-- radio는 style.css에서 hidden되있음 -->
					<div class="st-item">
						<input type="radio" name="option" id="title" value="title">
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
					<button class="site-btn" id="search">검색</button><br/>
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
							<input type="button" id="write" class="Wrt" value="글쓰기" onclick="javascript:location.href='/anbd2/write.do'">
							<%
						}
					%>
					<%-- 
					<c:choose>
						<c:when test="${sessionScope.loginId eq null}">
							<input type="button" id="write" class="Wrt" value="글쓰기" onclick="javascript:doAlert();">
						</c:when>
						<c:when test="${sessionScope.loginId ne null}">
							<input type="button" id="write" class="Wrt" value="글쓰기" onclick="javascript:location.href='write.jsp'">
						</c:when>
					</c:choose>
					 --%>
				</div>
			</form>
					<select class="showResult" name="showResult" style="display:none;" size=3>
					</select>
		</div>
	<!-- </div>-->
<!-- 자동완성 기능을 위한 웹UI개발 플러그인 --> 
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" ></script>
<script type="text/javascript">
	var key2 = '<%=mKey2%>';
	var option = '<%=option%>';
	var showResult = $(".showResult");
	$(document).ready(function() {
		var autoUrl = '/anbd2/include/autocomplete.jsp';
		//자동완성을 위한 AJAX
		$(function()
			{
			//autocomplete
			//플러그인을 선언해줘야만 사용 가능
			//http://code.jquery.com/ui/1.10.3/jquery-ui.js
				$("#key").autocomplete({
					//[source] - input 필드에 입력하면 동작함
					source : function(request, response)
					{
						$.ajax({
							//autoUrl 주소에 자동완성 단어 있음
							url : autoUrl,
							type : 'POST',
							datatype : 'HTML',
							//request.term은 $("#key").val();랑 같음
							data : {key : request.term},
							//통신 성공
							//html 형식으로 가져오니까 function의 파라미터는 아무 단어를 써도 무방
							//json 형식으로 가져올 때는 중요
							success : function(result)
								{
								//autoUrl 내용 참고
								//실행문을 , 단위로 잘라서 option 태그에 value로 사용
								var keyResult = result.split(",");
								var html = "";
									//key에 입력한 결과 개수만큼 each로 가져오기
									$(keyResult).each(function(j){
										html += "<option value='"+keyResult[j]+"' tabindex="+j+">"+keyResult[j]+"</option>";
									});
									//키워드 입력 칸에서 자판 입력 이벤트
									$("#key").keydown(function(e)
										{
											//↓ 입력 이벤트
											if(event.keyCode == 40)
											{
												//2020.07.20[의문] ↓를 누를 때마다 1씩 keyCount가 1씩 증가하는 방법은 없을까?
												//2020.07.21[해결] ↓를 누르면 셀렉트 박스로 포커스를 옮김, 간단!!
												$(".showResult").focus();
											}
										});
									//자동입력 셀렉트박스의 selected된 항목이 변할 때마다
									//키워드 칸에 입력
									$(".showResult").change(function()
										{
											var selectValue = $('.showResult option:selected').val();
											$('#key').val(selectValue);
										});
									
									//url의 내용을 가져온다.
									$(".showResult").html(html);
									$(".showResult").show();
								},
							error : function(request, status, error)
								{
									alert("자동완성/검색 기능을 수행할 수 없습니다");
								},
						})//end of ajax FLOW
					}//end of source FUNTION
				})//end of key autocomplete
				$(".showResult").keydown(function(e)
					{
						//엔터 입력 이벤트
						if(event.keyCode == 13)
						{
							//역방향 캐싱 오류
							//$("#search").click();
							//[문제]제목+엔터로 검색했을 때 결과가 없음
							//[해결]autocomplete.jsp의 내용에 공백 제거
							$("#searchFrm").submit();
						}
					})
			});//자동완성 [종료]
		
		if( !key2==""){ 			//검색어가 있으면
			$('#key').val(key2); //검색칸에 검색어 넣기
		}
		
		if(option==""){
			option ='title';
		}
		//console.log("옵션: ["+option+"]");
		
		$('input:radio[name=option]:input[value='+ option +']').attr("checked", true);
		
		
	});
</script>