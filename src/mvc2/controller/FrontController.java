package mvc2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc2.action.Action;
import mvc2.action.WriteOkAction;
import mvc2.vo.ActionForward;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
//	  private static final long serialVersionUID = 1L;
//    public FrontController() {
//        super();
//    }
	
	protected void doProcess (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI   = request.getRequestURI();
		String contextPath  = request.getContextPath();
		System.out.println("contextPath: "+contextPath);
		
		String command	    = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action 		= null;
		
		//매핑 주소 = 책에 있는거 + viewDone, viewAfter, viewCancel 등등.. 
		if(command.equals("/write.do")) {
			forward = new ActionForward();
			forward.setPath("/main/write.jsp");
		}else if(command.equals("/writeOk.do")) {
			action = new WriteOkAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("FrontController: writeOk.do 에러: "+e.getMessage());
			}
		}
		
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
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
