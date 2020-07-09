<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../include/headerKgh.jsp"%>
<%@include file="../include/fix.jsp"%>
<%@include file="../include/searchOption.jsp"%>
<%@ page import="java.net.URLEncoder" %> <!-- 브라우저 때문에.. -->
<jsp:useBean id="pg" class="anbd.PageDAO" scope="page"/>
<% 
request.setCharacterEncoding("utf-8");

int currentPage = 1;  //현재 페이지번호
int pageSize    = 19; //페이지당 게시물 목록 갯수
int count       = 0;  //전체 게시물 갯수
int startRow    = 0;  //페이지 시작행 번호
int seqNo       = 0;  //페이지 목록에 게시글 일련번호
int maxPageNo   = 0;  //최대 페이지 번호

currentPage = webutil._I("page","1");
String mKey = webutil._S("key","");
String mEncodeKey = webutil._E("key","");

/*선생님께서 알려주시기 전 코드 - 아래 코드를 위 코드로 단축!
String mTemp = request.getParameter("page");
if(mTemp != null){  //키워드 검색하면 무조건 1p
	currentPage = Integer.parseInt(mTemp);
}
String mKey = request.getParameter("key"); 
if(mKey==null){     //그냥 검색안하면 null
	mKey="";
}
String mEncodeKey = URLEncoder.encode(mKey, "UTF-8"); //url에 검색어 한글을 %로 바꿔줌(인코딩)*/
request.setAttribute("key", mEncodeKey);
%>
<script language="javascript">
	var key = '<%=mKey%>';
	if( key==""){ 	//검색어가 없으면
		document.title="ANBD | 아나바다-목록";
	}else{
		document.title="ANBD | '"+ key +"'검색 결과";
	}
</script>
<%
	startRow  = (currentPage - 1)*pageSize; //페이지 시작행 번호  = (현재 페이지번호 - 1) * 페이지당 출력 할 갯수
	seqNo     = startRow + 1;				    //페이지 목록에 게시글 일련번호
	
	ArrayList<AnbdVO> mainList = new ArrayList<AnbdVO>();
	String uri = request.getRequestURI();
	System.out.println("uri: "+uri);
	if(uri.equals("/anbd2/main/main.jsp")){
		//pg.selMainList2(mainList, startRow, pageSize, request); //pg.selMainList(mainList, startRow, pageSize, mKey);
	}
	session.setAttribute("pgList", mainList);
	
	count 	 = pg.count;						 //(selMainList2 메소드로부터 검색된)총 글 갯수
	maxPageNo = count/pageSize;  				 //최대 페이지 번호 계산
	if( (count % pageSize) != 0 ){			 //총 글 갯수를 목록표시갯수(20)로 나눠서 나머지가 남으면, 한 페이지 더 필요
		maxPageNo = maxPageNo + 1;				 //최대 페이지번호에 +1
	}
	
	ArrayList<AnbdVO> blist = new ArrayList<AnbdVO>();
	dao.selBoardList(blist);
	
	//서버에 attribute를 setting하겠다
	pageContext.setAttribute("blist", blist);
	
	dao.selJuso();
	pageContext.setAttribute("selJuso", dao.getBoardList());
%>
<c:if test="${param.menu eq 'reuse'}">
	<link rel="stylesheet" type="text/css" href="/anbd2/css/reuseStyle.css">
</c:if>
<div style="padding: 20px 40px 20px;">
<div align="right">
	<form id="jusoForm" name="jusoForm" method="post">
		<select id="sido" name="sido">
			<option value="시/도">시/도</option>
			<c:forEach items="${selJuso}" var="selJuso">
				<option value="${selJuso.sido}">${selJuso.sido}</option>
			</c:forEach>	
		</select>
		<select id="sigun" name="sigun">
			<option value="시/군/구">시/군/구</option>
		</select>
		<input type="hidden" id="jusoNo" name="jusoNo"/>
		<input type="hidden" id="startRow" name="startRow" value="<%=startRow%>"/>
		<input type="hidden" id="pageSize" name="pageSize" value="<%=pageSize%>"/>
		<input type="button" id="jusoSort" value="확인"/>
		<img id="noDone" style="width:22px;" src="/anbd2/img/checkGray.png"/> 거래완료 안보기</div>
	</form>
	
	<table id="board">
		<tr>
			<th width="160px">구분</th>
			<th width="500px">제목</th>
			<th width="200px">시도</th>
			<th width="200px">시군구</th>
			<th width="200px">작성일자</th>
		</tr>
		<!-- 공지 상단 고정 시작============================ -->
		<c:forEach items="${blist}" var="blist">
			<c:if test="${blist.menu eq  '공지'}">
				<tr>
					<td>[공지]</td>
					<td>
						<a href="view.jsp?no=${blist.no}&menu=notice">${blist.title}
							<c:choose>
								<c:when test="${pageList.photo eq 'Y'}">
									<img src="/anbd2/img/이미지.png" style="width:20px;">
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
						</a>
					</td>
					<td>시도 표시</td>
					<td>시군구 표시</td>
					<td>${blist.wdate}</td>
				</tr>
			</c:if>
		</c:forEach>
		<!--============================ 공지 상단 고정 끝 -->
		
		<!-- 목록 불러오기 시작=========================== -->
		<c:forEach items="${pgList}" var="pageList">
			<tr>
				<c:choose>
					<c:when test="${pageList.menu ne '공지'}">
						<td>[${pageList.menu}]</td>
						<td>
						<c:if test="${pageList.menu eq '아나'}">
							<a href="javascript:doGoPage('view.jsp','<%= currentPage %>','${pageList.no}');">
							<c:choose>
								<c:when test="${pageList.status eq 'done'}">
									<span id="status">[거래완료]</span>
								</c:when>
								<c:when test="${pageList.status eq 'cancel'}">
									<span id="status">[거래완료취소]</span>
								</c:when>
								<c:otherwise>
									<span id="status"></span>
								</c:otherwise>
							</c:choose>
							${pageList.title}
							<c:choose>
								<c:when test="${pageList.photo eq 'Y'}">
									<img src="/anbd2/img/이미지.png" style="width:20px;">
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
							</a>
						</c:if>
						<c:if test="${pageList.menu eq '바다'}">
							<!--기존: <a href="view.jsp?menu=reuse&no=${pageList.no}&key=${key}"> -->
							<a href="javascript:doGoPage('view.jsp','<%= currentPage %>','${pageList.no}');">
							<c:choose>
								<c:when test="${pageList.status eq 'done'}">
									<span id="status">[거래완료]</span>
								</c:when>
								<c:when test="${pageList.status eq 'cancel'}">
									<span id="status">[거래완료취소]</span>
								</c:when>
								<c:otherwise>
									<span id="status"></span>
								</c:otherwise>
							</c:choose>
							${pageList.title}
							<c:choose>
								<c:when test="${pageList.photo eq 'Y'}">
									<img src="/anbd2/img/이미지.png" style="width:20px;">
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
							</a>
						</c:if>
						</td>
						<td>시도</td>
						<td>시군구</td>
						<td>${pageList.wdate}</td>
					</c:when>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
	<!--=========================== 목록 불러오기 끝 -->
	<%
	//세션 변수에 저장된 userId값이 비어있으면 로그인 안한것
	if(session.getAttribute("loginId")==null)
	{
		%>
			<input type="button" id="write" class="Wrt2" value="글쓰기" onclick="javascript:doAlert();">
		<%
	}
	else
	{
		String loginId = (String)session.getAttribute("loginId");
		//out.print(session.getAttribute("loginId"));
		%>
		<input type="button" id="write" class="Wrt2" value="글쓰기" onclick="location.href='write.jsp'">
		<%
	}
	%>
</div>
<!--페이지 번호 -->
<div class="site-pagination" align="center">
<%
	//페이징 시작 블럭 계산
	int startBlock = (((currentPage-1)/10)*10) +1;
	//페이징 종료 블럭 계산
	int endBlock  = startBlock + (10-1); 			  
	//페이징 종료 블럭이 최대 페이지 번호보다 큰 경우 처리
	if(endBlock >maxPageNo){
		endBlock = maxPageNo;
	}
	//첫페이지는 이전블럭 없애기
	if(currentPage >10){  
		%><a href="javascript:doGoPage('main.jsp','<%= startBlock - 10 %>','');"> &lt;이전 </a><% 
	}
	for(int i=startBlock; i<=endBlock; i++)
	{
		if(i==currentPage){
			%><a href="javascript:doGoPage('main.jsp','<%= i %>','');" class="active"> <%= i %>.</a><%
		}else{
			%><a href="javascript:doGoPage('main.jsp','<%= i %>','');"> <%= i %>.</a><%
		}
	}
	//마지막블럭 다음은 안나오게
	if ( endBlock < maxPageNo ){
		%><a href="javascript:doGoPage('main.jsp','<%= endBlock+1 %>','');"> 다음&gt; </a><%
	}
%>
</div>
<script language="javascript">
	function doGoPage(url,pageno,seqno) //seqno는 글번호
	{
		var f = document.pageForm;
		var mParam = "";
		
		mParam += "page=" + pageno;
		mParam += "&";
		mParam += "no=" + seqno;		
		mParam += "&";
		mParam += "menu=" + f.smenu.value;
		mParam += "&";
		mParam += "option=" + f.soption.value;
		mParam += "&";
		mParam += "key=" + f.skey.value;	
		
		page = url + "?" + mParam;
		document.location = page;
	}
	function doAlert(){
		alert("글쓰기는 로그인 후 가능합니다");
		location.href="../common/login.jsp";
	}
	function doSubmitPage(url, formName)
	{
		var f = document.pageForm;
		var mParam = "";
		
		mParam += "page=" + f.spage.value;
		mParam += "&";
		mParam += "menu=" + f.smenu.value;
		mParam += "&";
		mParam += "option=" + f.soption.value;
		mParam += "&";
		mParam += "key=" + f.skey.value;	
		page = url + "?" + mParam;
		formName.action = page;
		formName.submit();  
	}
	$(document).ready(function()
		{
		$("#sido").change(function()
				{
				//선택한 시도 값 구하기
				var changeSigun = $("#sido").val();
				$.ajax
					({
						type:"GET",
						url:"/anbd2/main/selSigun.jsp",
						data: "sido=" + encodeURIComponent(changeSigun),
						dataType: "html",
						success: function (data) 
						{
							//alert(data);
							$("#sigun").html(data);
			         },
		            error: function(xhr, status, error)
		            {
		            	alert("지역 정보를 조회할 수 없습니다");
		            }
					});//ajax FLOW
					
				});//시/도 변화 시 시군구 변화
		$("#jusoSort").click(function(){
			var sido = $("#sido option:selected").val();
			var sigun = $("#sigun option:selected").val();
				 sigun = sigun.split(":");
			var jusoNo = sigun[0];
			$("#jusoNo").val(jusoNo);
			//alert(jusoNo);
			var path = '${pageContext.request.contextPath}';
			//document.jusoForm.action = path+"/jusoSer"; 
			//document.jusoForm.submit();
			doSubmitPage(path+"/jusoSer", jusoForm)
		});
		$("#noDone").click(function(){
			var path = '${pageContext.request.contextPath}';
			doGoPage(path+"/noDoneSer")
		});
	})
</script>
<!--  doGoPage()에 값을 보내기 위한 hidden 값  -->
<form id="pageForm" name="pageForm" method="post" action="/anbd2/main/main.jsp">
	<input type="hidden" id="spage" name="page" value="">
	<input type="hidden" id="smenu" name="menu" value="<%=menu%>">
	<input type="hidden" id="soption" name="soption" value="<%=option%>">
	<input type="hidden" id="skey" name="skey" value="<%=mEncodeKey%>">
</form>
<%@include file="../include/footer.jsp"%>