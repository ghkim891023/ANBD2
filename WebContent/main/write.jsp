<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../include/headerKgh.jsp"%>
<%@include file="../include/fix.jsp"%>

<script>
	document.title="ANBD | 아나바다-글쓰기";
</script>
<style>
	#share, #reuse
	{
		width:130px
	}
	table
	{
		margin:auto;
	}
</style>
<%
	//int userNo = 2;
	dao.selJuso();
	pageContext.setAttribute("boardList", dao.getBoardList()); //주소 정보
	
	String loginId = (String)session.getAttribute("loginId");
	int loginUserNo = dao.selLoginUserNo(vo, loginId); //id세션으로 회원번호 얻기, 로그인 여부 -> 로그인 안하고 write.do접속시 처리
	pageContext.setAttribute("userNo", loginUserNo);
%>
<%--서블릿 적용 전, 정상 작동 확인
	<form class="contact-form" id="write" name="write" method="post" action="writeOk.jsp?userNo=${pageScope.userNo}" enctype="multipart/form-data" onsubmit="return false;"> 
	--%>
<!--서블릿 적용 후
	<form class="contact-form" id="write" name="write" method="post" action="writeSer" enctype="multipart/form-data" onsubmit="return false;">
	-->
<%--서블릿 적용 전, 정상 작동 확인--%>
 <form class="contact-form" id="write" name="writeForm" method="post" action="/anbd2/writeOk.do?userNo=${pageScope.userNo}" enctype="multipart/form-data" onsubmit="return false;"> 
	<div class="container" id="Wrt">
		<!--테이블 형식 본문-->
		<table>
			<tr>
				<td>카테고리</td>
				<td>
					<div class="search-type2">
					 	<div class="st-item2">
							<input type="radio" name="menu" id="share" value="아나" checked> 
							 	<label for="share">아/나</label>
							<input type="radio"  name="menu" id="reuse" value="바다"  >
								<label for="reuse">바/다</label>
						</div><!-- st-item2  클래스 마지막 -->
					</div><!--==== search-type2 클래스 마지막 -->
				</td>
			</tr>
			<tr>
				<td>지역</td>
				<td>
					<select name="sido" id="sido">
						<c:forEach items="${boardList}" var="boardList">
							<c:if test="${boardList.sido eq '기타'}">
								<option value=${boardList.sido} selected="selected">${boardList.sido}</option>
							</c:if>
							<c:if test="${boardList.sido ne '기타'}">
								<option value=${boardList.sido}>${boardList.sido}</option>
							</c:if>
						</c:forEach>
					</select>
					<span>
					<select name="sigun" id="sigun">
							<option value="251">기타</option>
					</select>
					</span>
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" placeholder="제목을 입력하세요" id="title" name="title" maxlength="30" 
						    autofocus style="width:580px; ime-mode:active;">
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea style="width:580px" id="content" name="content" placeholder="내용을 입력하세요"></textarea>
				</td>
			</tr>
			<tr >
				<td>파일</td>
				<td class="moreFile">
					<input type="file" id="fileName" name="fileName1" onchange="fileCheck(this)" 
							 accept="image/gif, image/jpeg, image/png" /> <!-- accept는 ie10 이후 지원, 이하 버전은 onchange -->
					<input type="button" class="site-btn" id="add" value="추가"/>
					<input type="button" class="site-btn" id="remove" value="삭제"/><!-- 파라미터 안받는건 name필요x -->
				</td>
			</tr>
			<tr>
				<td colspan="2" style="padding-top:30px;">		
					<button class="site-btn" id="save">등록</button>
					<input type="reset" class="site-btn" id="cancel" value="초기화"/>
				</td>
			</tr>
		</table>
		<input type="hidden" name="loginId" value="${sessionScope.loginId}"/>
	</div>
</form>
<div class="Lst">
	<button class="site-btn" id="list" onclick="location.href='/anbd2/main/main.jsp'">목록</button>
</div>
<script type="text/javascript">
	var loginId = '<%=loginId%>'; //''안하면 js 정의되지 않았다고 에러남
	if(loginId==null || loginId=='null'){ 
		alert("글쓰기는 로그인 후 가능합니다.");
		location.href = "/anbd2/login.do"; // /anbd2/common/login.jsp
	}
	$(document).ready(function()
	{
		//초기화 [시작]
		$("#cancel").click(function()
			{
				//이 메소드가 없으면 지역 선택>바다 선택>초기화 클릭 시
				//지역 초기화 불가능
				$("#sido, #sigun").removeAttr('disabled');
				$("#sigun").html("<option value='251'>기타</option>");;
			})
		//초기화 [종료]
		//등록 클릭 [시작]
		$("#save").click(function()
		{	
			var writeForm = document.writeForm; //document.getElementById("write");
			var title = $("#title").val(); 
			var content = $("#content").val(); 
			if(title == "")
			{
				alert($("#title").prop("placeholder"));
				$("#title").focus();
				return;
			}//제목 입력 안 했을 때 경고
			if(content == "")
			{
				alert($("#content").prop("placeholder"));
				$("#content").focus();
				return;
			}//내용 입력 안 했을 때 경고
			
			var mForm = $('#write')[0];

			//전송할  Form의 데이터를 얻을 준비를 한다.
			var mPostData = new FormData(mForm);
			var path = "${pageContext.request.contextPath}";
			var urlSer = path+"/writeOk.do?userNo=${pageScope.userNo}";
			//var urlJsp = "writeOk.jsp?userNo=${pageScope.userNo}";
			writeForm.action = urlSer;
			writeForm.submit(); 
			/*$.ajax
			({
				type:"POST",
				enctype: "multipart/form-data",
				url: urlMvc2,
				data: mPostData,
				processData: false,
				contentType: false,				
				dataType:"html",
				success: function (data) 
				{	
					console.log(data);
					var array = data.split(",");
					var menu = array[1].toString();//메뉴
					var no = array[0]*1;//글번호
	            	alert("글 쓰기를 완료하였습니다.\n글 보기 화면으로 이동합니다.");
	            	//location.href="view.jsp?data="+encodeURIComponent(data);
					location.href = "view.jsp?menu="+menu+"&no=" +no;
	            },
	            error: function(request, status, error)
	            {
	            	alert("서블릿을 호출할 수 없음");
	            	console.log("code = "+ request.status + " message = " + request.responseText + " error = " + error);
	            	//alert("code = "+ request.status + " message = " + request.responseText + " error = " + error);
	            }
			});//ajax FLOW*/
		});//등록 클릭 [종료]
		$("input[name='menu']").change(function()
			{
				var categoryName = $("input[name='menu']:checked").val();
				if(categoryName == "바다")
				{
					$("#sido, #sigun").attr('disabled', 'disabled');
					$("#sido").attr('value', '기타');
					$("#sigun").attr('value', '251');
				}
				if(categoryName == "아나")
				{
					$("#sido, #sigun").removeAttr('disabled');
				}
			});//메뉴 바꿈
		
			$("#sido").change(function()
				{
				//선택한 시도 값 구하기
				var changeSigun = $("#sido").val();
				$.ajax
					({
						type:"GET",
						url:"/anbd2/main/writeSigun.jsp", 
						data: "sido=" + encodeURIComponent(changeSigun),
						dataType: "html",
						success: function (data) 
						{
							$("#sigun").html(data);
							/* ******* 
							 * [2020.06.29]
							 * json 형태의 코드, json 타입으로 변환하지 못했음
							alert(data);
			            	alert("AJAX success!!!!!!!!!!!");
			            	i = 0;
			            	$.each(data, function(name, value)
			            	{
			            		if( i <= 2)
			            		{
			            			alert(name);
			            			alert(value);
			            		}
			            		$("#sigun").append('<option value=' + value + '>' + value + '</option>');
			            		i++;
			            	});
			            	******* */
			            },
			            error: function(xhr, status, error)
			            {
			            	alert("지역 정보를 조회할 수 없습니다");
			            }
					});//ajax FLOW
				});//시/도 변화 시 시군구 변화
		//첨부파일 추가 버튼 방법 - 10개 까지 , name에 '파일명+숫자(count)' 붙임 
		var count = 2;
		$(document).on('click','#add',function(event){
			var html  = "<div class='added'><input type='file' id='fileName' name='fileName"+count+"'> ";
			html += "<input type='button' class='site-btn' id='add' value='추가'/> ";
			html += "<input type='button' class='site-btn del' id='remove' value='삭제'/></div>";
			if(count>10){
				alert("최대 10개까지 첨부가능합니다.");
			}else{
				$('.moreFile').append(html);
			}
			count++;
		});
		//첨부파일 첫번째 삭제 버튼 - 파일 값만 사라지게  - if(IE면){} else(다른 브라우저면){}
		$('#remove').eq(0).click(function(){
			var agent = navigator.userAgent.toLowerCase();
			if ( (navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1) ){
			    // ie 일때 input[type=file] init.
			    $("#fileName").replaceWith( $("#fileName").clone(true) );
			} else {
			    //other browser 일때 input[type=file] init.
			    $("#fileName").val("");
			}
		});
	})
		//2번째 삭제부터는 한줄 다 사라지게
		$(document).on('click', '.del', function() {
			$(this).parent().remove();
		});
		//이미지 파일만 가능하게 - https://sqlplus.tistory.com/entry/파일첨부에-이미지만-첨부할-수-있도록-처리-input-file
		function fileCheck(obj) {
		    pathpoint = obj.value.lastIndexOf('.'); //lastIndexOf()는 특정 문자열이 마지막에 나타나는 위치를 반환하는 메서드
		    filepoint = obj.value.substring(pathpoint+1,obj.length); //substring(start, end)은 문자열에서 특정 부분만 골라낼 때
		    filetype = filepoint.toLowerCase(); //toLowerCase() 소문자로 변환해서 반환
		    if(filetype=='jpg' || filetype=='gif' || filetype=='png' || filetype=='jpeg' || filetype=='bmp') {
		        // 정상적인 이미지 확장자 파일일 경우 ...
		    } else {
		        alert('이미지 파일만 선택할 수 있습니다.');
		        parentObj  = obj.parentNode //부모노드.replaceChild(newNode, oldNode) 기존 노드의 교체
		        node = parentObj.replaceChild(obj.cloneNode(true),obj); //cloneNode(true) 노트를 복제(true-자식도 함께 복제)
		        return false;
		    }
		    if(filetype=='bmp') {
		        upload = confirm('BMP 파일은 웹상에서 사용하기엔 적절한 이미지 포맷이 아닙니다.\n그래도 계속 하시겠습니까?');
		        if(!upload) return false;
		    }
		}
</script>
<%@include file="../include/footer.jsp"%>
