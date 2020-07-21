package mvc2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc2.action.Action;
import mvc2.action.ViewAction;
import mvc2.action.WriteOkAction;
import mvc2.action.viewModifyOkAction;
import mvc2.vo.ActionForward;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	
	protected void doProcess (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI     = request.getRequestURI();
		String contextPath    = request.getContextPath(); //출력: /anbd2
		
		String command	      = RequestURI.substring(contextPath.length());
		System.out.println("command: "+command);
		ActionForward forward = null;
		Action action 		  = null;
		
		//매핑 주소별 -> Action 클래스, 메소드 실행
		if(command.equals("/write.do")) { //http://localhost:8090/anbd2/write.do 중간에 /main/있으면 실행안됨
			forward = new ActionForward();
			forward.setPath("/main/write.jsp");
		}
		else if(command.equals("/writeOk.do")) {
			action = new WriteOkAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("FC:writeOk.do 에러: "+e.getMessage());
				e.printStackTrace();
			}
		}else if(command.equals("/main.do")) { //메인 - 일단 jsp로 연결, header수정 ★★
//			action = new MainAction();
//			try {
//				forward = action.execute(request, response);
//			}catch(Exception e) {
//				System.out.println("FC:main.do 에러: "+e.getMessage());
//			}
			forward = new ActionForward();
			forward.setPath("/main/main.jsp");
		}else if(command.equals("/view.do")) { //글보기 - 현재 페이지 전달기능만 함
			action = new ViewAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("FC:view.do 에러: "+e.getMessage());
			}
		}else if(command.equals("/viewModify.do")) { //글수정 폼 - 페이지 전달만..
			forward = new ActionForward();
			forward.setPath("/main/viewModify.jsp"); 
//			action = new viewModifyAction();
//			try {
//				forward = action.execute(request, response);
//			}catch(Exception e) {
//				System.out.println("FC:viewModify.do 에러: "+e.getMessage());
//			}
		}else if(command.equals("/viewModifyOk.do")) { //글수정 처리
			action = new viewModifyOkAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("FC:viewModifyOk.do 에러: "+e.getMessage());
			}
		}else if(command.equals("/viewRemove.do")) { //글삭제
			//action = new viewRemoveAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("FC:viewRemove.do 에러: "+e.getMessage());
			}
		}else if(command.equals("/coWriteOk.do")) { //댓글쓰기 처리
			//action = new coWriteOkAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("FC:coWriteOk.do 에러: "+e.getMessage());
			}
		}else if(command.equals("/viewCoModifyOk.do")) { //댓글수정 처리
			//action = new viewCoModifyOkAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("FC:viewCoModifyOk.do 에러: "+e.getMessage());
			}
		}else if(command.equals("/viewCoRemove.do")) { //댓글삭제 처리
			//action = new viewCoRemoveAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("FC:viewCoRemove.do 에러: "+e.getMessage());
			}
		}else if(command.equals("/viewDone.do")) { //거래완료 처리
			//action = new viewDoneAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("FC:viewDone.do 에러: "+e.getMessage());
			}
		}else if(command.equals("/viewCancel.do")) { //거래완료취소 처리
			//action = new viewCancelAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("FC:viewCancel.do 에러: "+e.getMessage());
			}
		}else if(command.equals("/viewAfter.do")) { //다음글
			//action = new viewAfterAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("FC:viewAfter.do 에러: "+e.getMessage());
			}
		}else if(command.equals("/viewBefore.do")) { //이전글
			//action = new viewBeforeAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("FC:viewBefore.do 에러: "+e.getMessage());
			}
		}//매핑 추가 + loginOk, joinOk, logoutOk ★★★
		else if(command.equals("/login.do")) {
			forward = new ActionForward();
			forward.setPath("/common/login.jsp");
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}//doProcess

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
}//end of class
