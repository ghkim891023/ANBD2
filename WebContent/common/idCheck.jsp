<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="EUC-KR" %>
<%@ page import="anbd.AnbdDAO" %>
<%	request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="idc" class="anbd.AnbdVO" scope="page"/>

<script type="text/javascript">
function doUse(){
	opener.isMemberCheck = 1; //�θ�â�� �� ����
	window.close();
}
</script>

<!DOCTYPE html>
<html lang="en">
<title>���̵� �ߺ��˻�</title>
<%
	String id = request.getParameter("id");  //�Է� ���� �Ķ���ͷ� �Ѱܹ޾� id�� ����
	anbd.AnbdDAO mDAO = new anbd.AnbdDAO();

	boolean result = mDAO.selIdCheck(id);      //id���� �Ű������� confirmId �޼ҵ带 ȣ���Ͽ� �� ������� result�� ����

	if(result){ 
		%>  
		<div style="text-align:center;">
		<br><br><br>
		<h4>�̹� ��� ���� ���̵��Դϴ�.</h4>
		</div>
		<center><a href="JavaScript:window.close()">���̵� �ٽ� �Է�</a></center>
		<%
		
	}else{ 
		%>
		<div style="text-align:center;">
		<br><br><br>
		<h4>�Է��Ͻ� <%= id %>�� ����� �� �ִ� <br>���̵��Դϴ�.</h4>
		</div>
		<center><a href="JavaScript:doUse()">����ϱ�</a></center>
		<%
	}
%> 

</html>
