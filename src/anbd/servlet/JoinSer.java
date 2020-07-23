package anbd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import anbd.AnbdDAO;
import anbd.AnbdVO;

@WebServlet("/joinBean_ok")
public class JoinSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinSer() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("===JoinSer : doPost() 시작");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		AnbdDAO dao = new AnbdDAO();
		AnbdVO vo = new AnbdVO();
		vo.setId(id);
		vo.setPw(pw);
		vo.setName(name);
		vo.setEmail(email);
		int join = dao.inJoin(vo);
		api.JavaMail.sendEmail(email, id);
		if(join== 1){
			out.println("<script>");
			out.println("alert('이메일을 확인해주세요.이메일 인증시 회원가입이 완료됩니다.')");
			out.println("location.href='/anbd2/common/login.jsp';");
			out.println("</script>");
		}else{
			out.println("<script>");
			out.println("alert('회원가입에 실패하였습니다.')");
			out.println("history.back();");
			out.println("</script>");
		}
		
	}

}
