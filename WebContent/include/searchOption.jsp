<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="webutil2" class="anbd.WebUtil" scope="page"/>
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
			<form class="main-search-form" action="../main/main.jsp" method="get" id="searchFrm" name="searchFrm">
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
				<div>
					<select class="showResult" name="showResult" style="display:none">
					</select>
				</div>
			</form>
		</div>
	<!-- </div>-->
<!-- 자동완성 기능을 위한 웹UI개발 플러그인 --> 
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" ></script>
<script type="text/javascript">
	var key2 = '<%=mKey2%>';
	var option = '<%=option%>';
	$(document).ready(function() {
		var autoUrl = '../include/autocomplete.jsp';
		//자동완성을 위한 AJAX
		$(function()
			{
				$("#key").autocomplete({
					//[source] - input 필드에 입력하면 동작함
					source : function(request, response)
					{
						$.ajax({
							url : autoUrl,
							type : 'POST',
							datatype : 'HTML',
							//request.term은 $("#key").val();랑 같음
							data : {key : request.term},
							success : function(result)
								{
								var keyResult = result.split(",");
								var html = "";
									$(keyResult).each(function(j){
										html += "<option value='"+keyResult[j]+"'>"+keyResult[j]+"</option>";
									});//key에 입력한 결과 개수만큼 each로 가져오기
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
			})
		
		
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