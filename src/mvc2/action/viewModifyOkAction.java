package mvc2.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc2.service.viewModifyOkService;
import mvc2.vo.ActionForward;
import mvc2.vo.BoardVO;

public class viewModifyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

//		책
//		ActionForward forward = null;
//		boolean isModifySuccess = false;
//		int board_num=Integer.parseInt(request.getParameter("BOARD_NUM"));
//		BoardBean article=new BoardBean();
//		BoardModifyProService boardModifyProService = new BoardModifyProService();
//		boolean isRightUser=boardModifyProService.isArticleWriter(board_num, request.getParameter("BOARD_PASS"));
//
//		if(!isRightUser){
//			response.setContentType("text/html;charset=UTF-8");
//			PrintWriter out=response.getWriter();
//			out.println("<script>");
//			out.println("alert('수정할 권한이 없습니다.');");
//			out.println("history.back();");
//			out.println("</script>");
//		}
//		else{
//			article.setBOARD_NUM(board_num);
//			article.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
//			article.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT")); 
//			isModifySuccess = boardModifyProService.modifyArticle(article);
//
//			if(!isModifySuccess){
//				response.setContentType("text/html;charset=UTF-8");
//				PrintWriter out=response.getWriter();
//				out.println("<script>");
//				out.println("alert('수정실패');");
//				out.println("history.back()");
//				out.println("</script>");
//			}
//			else{
//				forward = new ActionForward();
//				forward.setRedirect(true);
//				forward.setPath("boardDetail.bo?board_num="+article.getBOARD_NUM()); 
//			}
//
//		}
//		return forward;
		
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardVO boardVo = new BoardVO();
		viewModifyOkService service = new viewModifyOkService();
		boolean isRightUser=service.isArticleWriter(no, request.getParameter("BOARD_PASS"));
		
		
		return forward;
	}

}
