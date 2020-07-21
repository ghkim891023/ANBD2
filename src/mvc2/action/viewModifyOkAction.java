package mvc2.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc2.service.viewModifyOkService;
import mvc2.vo.ActionForward;
import mvc2.vo.BoardVO;

public class viewModifyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardVO vo = new BoardVO();
		viewModifyOkService service = new viewModifyOkService();

		vo.setNo(no);
		vo.setMenu(request.getParameter("menu"));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		vo.setPhoto(request.getParameter("photo"));
		
		//첨부파일 ★★★
		String savePath = request.getSession().getServletContext().getRealPath("/upload");
		
		
		
		
		
		isModifySuccess = service.modifyArticle(vo);
		
		if(!isModifySuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 수정에 실패하였습니다.');");
			out.println("history.back()");
			out.println("</script>");
		}else{
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("view.do?no="+vo.getNo()); 
		}
		
		return forward;
	}

}
