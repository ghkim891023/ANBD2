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
		System.out.println("This is doGet()...");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("This is doPost()...");
		//doGet(request, response);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json;charset=utf-8");
		
		AnbdVO vo = new AnbdVO();
		AnbdDAO dao = new AnbdDAO();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		vo.setUserNo(userNo);

		String id = (String)session.getAttribute("loginId");
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		vo.setUploadPath(uploadPath);
		
		String path = "/main/write.jsp";
		RequestDispatcher dis = request.getRequestDispatcher(path); //클라이언트 요청을 
		dis.forward(request, response);
		
		dao.inWrite(vo, request, vo.getLoginUserNo());
		out.print("글 번호 = "+vo.getNo());
		out.print("메뉴 = "+vo.getMenu());
		out.print("유저 번호 = "+userNo);

		if(vo.getMenu().equals("아나"))
		{
			out.print("메뉴 = share");
		}
		else if(!vo.getMenu().equals("아나")) 
		{
			out.print("메뉴 = reuse");
		}
		System.out.println("글 번호 = "+vo.getNo());
		System.out.println("메뉴 = "+vo.getMenu());
		
		
		/*
		 * String url = "write.jsp";
		 *  RequestDispatcher dispatcher =
		 * request.getRequestDispatcher(url); dispatcher.forward(request, response);
		 */
		
		String url = "write.jsp";
		request.setAttribute((vo.getNo()+""), vo.getNo());
		request.setAttribute((vo.getMenu()), vo.getMenu());
		response.sendRedirect(url);
		
	}//doPost METHOD

}
