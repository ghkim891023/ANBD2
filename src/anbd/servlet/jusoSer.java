package anbd.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;

import anbd.AnbdDAO;
import anbd.AnbdVO;
import anbd.PageDAO;

@WebServlet("/jusoSer")
public class jusoSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public jusoSer() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int jusoNo = Integer.parseInt(request.getParameter("jusoNo"));
		int startRow = Integer.parseInt(request.getParameter("startRow"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		System.out.println(jusoNo);
		
		PageDAO p = new PageDAO();
		//selMainListJuso( ArrayList<AnbdVO> mainList, int startRow, int pageSize, HttpServletRequest request, int JusoNo)
		ArrayList<AnbdVO> mainList = new ArrayList<AnbdVO>();
		p.selMainListJuso(mainList, startRow, pageSize, request, jusoNo);
		

		String path = "/main/main.jsp"; //절대경로 '/'는 웹사이트의 루트 폴더
		String path2 = "/anbd2/main/main.jsp"; //sendRedirect
		RequestDispatcher dis = request.getRequestDispatcher(path); //클라이언트 요청을 
		
//		HttpSession session = request.getSession();
//		session.setAttribute("pgList", mainList);
		
		request.setAttribute("pgList", mainList);

		//response.sendRedirect(path2); 
		dis.forward(request, response);
		
		
		
		
		
		
	}

}
