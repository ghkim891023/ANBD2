<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../include/header.jsp"%>
<%
	response.setStatus(HttpServletResponse.SC_OK);
%>
<script>
	document.title="ANBD | 400 ERROR";
</script>
<section class="contact-section">
	<div class="container">
		<h2>400 ERROR</h2><br/>
			<table align="center">
				<tr>
					<td>
						<img src="../img/404icon.svg" width="200%">
					</td>
				</tr>
				<tr>
					<td>
						죄송합니다<br/>
						요청하신 페이지를 찾을 수 없습니다.
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<a class="site-btn sb-big" href="../main/main.jsp">메인으로 돌아가기</a>
					</td>
				</tr>
			</table>
	</div><!--=====container 클래스 마지막-->
</section><!--=====Contact Section end -->

	<%@include file="../include/footer.jsp"%>

