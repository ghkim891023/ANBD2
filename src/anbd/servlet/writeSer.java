package anbd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import anbd.*;

/**
 * Servlet implementation class writeSer
 */
@WebServlet("/writeSer")
public class writeSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public writeSer() {super();}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//doGet(request, response);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		AnbdVO vo = new AnbdVO();
		AnbdDAO dao = new AnbdDAO();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		String id = (String)session.getAttribute("loginId");
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		vo.setUploadPath(uploadPath);
		
		String path = "/main/write.jsp";
		RequestDispatcher dis = request.getRequestDispatcher(path); //클라이언트 요청을 
		dis.forward(request, response);
		
		dao.inWrite(vo, request, vo.getLoginUserNo());
	}

}
