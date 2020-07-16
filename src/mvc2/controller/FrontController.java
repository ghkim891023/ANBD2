package mvc2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc2.action.Action;
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
		
		if(command.equals("/write.do")) {
			forward = new ActionForward();
			forward.setPath("/main/write.jsp");
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
