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

@WebServlet("/noDoneSer")
public class noDoneSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public noDoneSer() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		
		int jusoNo = 0;
		boolean noDone = true;
		int startRow = Integer.parseInt(request.getParameter("startRow"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		System.out.println(jusoNo);
		System.out.println("startRow: "+startRow);
		
		PageDAO p = new PageDAO();
		ArrayList<AnbdVO> mainList = new ArrayList<AnbdVO>();
		p.selMainList2noDone(mainList, startRow, pageSize, request, noDone);
		
		String path = "/main/main.jsp"; //절대경로 '/'는 웹사이트의 루트 폴더
		String path2 = "/anbd2/main/main.jsp"; //sendRedirect
		RequestDispatcher dis = request.getRequestDispatcher(path); //클라이언트 요청을 
		
//		HttpSession session = request.getSession();
//		session.setAttribute("pgList", mainList);
		
		request.setAttribute("pgList", mainList);
		
		//response.sendRedirect(path2); 
		dis.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		
	}

}
