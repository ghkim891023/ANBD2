<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../include/headerKgh.jsp"%>
<style>
	input#id  /*id 칸에는 영어 먼저 입력, 크롬, 파이어폭스, ms, ie 환경에서 작동시킴*/
	{
	-webkit-ime-mode:inactive;
	-moz-ime-mode:inactive;
	-ms-ime-mode:inactive;
	ime-mode:inactive;
	}
</style>
<body>
<section class="contact-section">
	<div class="container">
		<h2>회원가입</h2> <!-- action="joinBean_ok.jsp" -->
		<form class="contact-form" id="joinfrm" name="joinfrm"  method="post" action="/anbd2/joinBean_ok">
			<div class="row join">
				<div class="col-lg-12">
					<p align="left">아이디</p>
					<input type="text" placeholder="아이디를 입력하세요"  name="id" id="id" autofocus maxlength="12"/>
					<a target="_blank" id="idcheck" class="site-btn">중복검사</a>
				</div>
				
				<div class="col-lg-12">
					<p align="left">비밀번호</p>
					<input type="password" placeholder="비밀번호를 입력하세요" name="pw" id="pw" autocomplete="off" maxlength="20"/>
				</div>
				
				<div class="col-lg-12">
					<p align="left">비밀번호 확인</p>
					<input type="password" placeholder="비밀번호를 다시 입력하세요" name="pw2" id="pw2" autocomplete="off" maxlength="20"/>
				</div>
				
				<div class="col-lg-12">
					<!-- <div align="left">이름</div> -->
					<p align="left">이름</p>
					<input type="text" placeholder="이름을 입력하세요" name="name" id="name" maxlength="20"/>
				</div>
				
				<div class="col-lg-12" align="left">
					<p style="float:left">이메일</p>
						<pre id="message">
<b>이메일 수집 및 이용에 관한 동의</b>
1. 이용자가 제공한 이메일은 다음의 목적을 위해 활용하며, 하기 목적 이외의 용도로는 사용되지 않습니다.
   1-1. 회원가입 시 본인 인증
   1-2. 나눔/교환 글 작성 시에 연락 수단으로 회원에게 이메일 공개
2. 이메일을 입력하고 회원가입을 진행하면 이메일 공개 동의로 간주합니다.
3. 회원가입을 하면 인증메일이 발송됩니다. 이메일 url로 인증 후 회원가입이 완료됩니다.
4. 사용 가능 도메인(com, net, co.kr, go.kr, ac.kr, 한국, info, biz, me, so, co)
※ 단, 본 사이트의 이메일을 수집해 이를 악용하는 행위는 처벌을 받을 수 있습니다.</pre>				
						<input type="email" placeholder="이메일을 입력하세요" name="email" id="email" maxlength="320">
					</div>
				</div><!--row 클래스 마지막-->
			
			<button class="site-btn sb-big" id="join" > 가입 </button> 
			<button class="site-btn sb-big" id="cancel">취소 </button>
		</form>
	</div><!--=====container 클래스 마지막-->
</section><!--=====Contact Section end -->
<%@include file="../include/footer.jsp"%>
<script type="text/javascript">
	var isMemberCheck = 0;
	document.title="ANBD | 아나바다-회원가입";
	window.onload = function()
	{
		//$("#inputDomain").hide();
		/*
		$("#domain").change(function()
				{
					if($("#domain").val() == "direct")
					{$("#inputDomain").show();}
					else
					{$("#inputDomain").hide();}
				});//직접입력 클릭>입력란 생성 
		*/
		$("#cancel").click(function(e)
			{
				e.preventDefault();
				var cancel = confirm("회원가입을 취소하고 목록으로 돌아가시겠습니까?");
				if(cancel==true)
				{
					window.location.href='../main/main.jsp'; 
				}
				else{}
			});//취소 여부 확인 후 메인으로 돌아감
			
		$("#join").click(function(){
				//사용할 수 있는 도메인 목록
				var domainArray = [".com", ".net", ".co.kr", ".go.kr", ".ac.kr", ".한국", ".info", ".biz", ".me", ".so", ".co"];
				var emailValue = $("#email").val();	//입력한 이메일 값
				var searchDomain = 0;				//도메인 사용 가능 여부 판별, 0이 아니면 사용할 수 있는 도메인
				var form = document.joinfrm;
				if(jQuery.trim(form.id.value)==""){ //jQuery.trim() 공백 입력시에도 경고창
					alert("아이디를 입력해 주세요."); 
					document.joinfrm.id.focus(); 
					return false;
				}
			   if(isMemberCheck==0){
				   alert("ID 중복검사를 해주세요.");
				   return false;
			   }
				if(jQuery.trim(form.pw.value)==""){ 
					alert("비밀번호를 입력해 주세요."); 
					document.joinfrm.pw.focus(); 
					return false;
				}
				if(jQuery.trim(form.pw2.value)==""){ 
					alert("비밀번호를 확인을 입력해 주세요."); 
					document.joinfrm.pw2.focus(); 
					return false;
				}
				if(form.pw2.value != form.pw.value){ 
					alert("비밀번호가 일치하지 않습니다."); 
					document.joinfrm.pw2.focus(); 
					return false;
				}
				if(jQuery.trim(form.name.value)==""){ 
					alert("이름을 입력해주세요"); 
					document.joinfrm.name.focus(); 
					return false;
				}
				if(jQuery.trim(form.email.value)==""){
					alert("이메일을 입력해주세요");
					return false;
				}
				//도메인 검사 [시작]
				for(var i in domainArray)			//domainArray 길이만큼 반복
				{
					//입력한 이메일에 사용할 수 있는 도메인이 있는지 검출
					if(emailValue.indexOf(domainArray[i]) != -1)
					{
						//사용할 수 있는 도메인이면 searchDomain를 0보다 큰 수로 세팅
						searchDomain++;
						//반복하지 않도록 이 구문을 빠져나감
						return true;
					}
					else
					{
						alert("사용할 수 없는 도메인입니다\n사용 가능 도메인 :\n.com, .net, .co.kr, .go.kr, .ac.kr, .한국, .info, .biz, .me, .so, .co");
						return false;
					}
				}//도메인 검사 [종료]
				//alert("인증 메일이 발송되었습니다.\n가입한 이메일 주소로 발송된 이메일을 확인해주세요.\n이메일의 URL을 접속하시면 회원가입이 완료됩니다.\n※ 해당 URL은 발송시점부터 24시간만 유효합니다.");
				return true;
		});//end of #join
				
		//중복확인 버튼 클릭시 이동하는 코드
		$("#idcheck").click(function()
			  {
				if( jQuery.trim(document.joinfrm.id.value)==""){ //공백 제거
					alert("아이디를 입력해 주세요."); 
					document.joinfrm.id.focus(); 
					return; //아래 실행 안하고 빠져나와
				}
				var url = "idCheck.jsp?id="+document.joinfrm.id.value;
				var name = "idcheck";
				var x = (window.outerWidth/2)-200; 	  //outerWidth-요소의 border포함 크기
				var y = (window.screen.height/2)-150; //화면의 세로 크기
				window.open(url, name, 'height=300, width=400, left='+ x + ', top='+ y);
		});//팝업 띄우기, 중앙 정렬 포함
			

	};
</script>
		