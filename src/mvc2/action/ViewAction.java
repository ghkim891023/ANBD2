package mvc2.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc2.service.ViewService;
import mvc2.vo.ActionForward;

public class ViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//책
//		int board_num=Integer.parseInt(request.getParameter("board_num"));
//		String page = request.getParameter("page");
//		BoardDetailService boardDetailService = new BoardDetailService();
//		BoardBean article = boardDetailService.getArticle(board_num);
//		ActionForward forward = new ActionForward();
//		request.setAttribute("page", page);
//	   	request.setAttribute("article", article);
//   	forward.setPath("/board/qna_board_view.jsp");
//   	return forward;
		
		int no=Integer.parseInt(request.getParameter("no"));
		System.out.println("no: "+no);
		String page = request.getParameter("page");
		//menu 등등 파라미터 전달 추가
		//ViewService viewService = new ViewService();//조회수 없으니 필요없을듯
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("no", no);
	   	//forward.setPath("/main/view.jsp?no="+no);
	   	forward.setPath("/main/view.jsp");
		return forward;
		
		
	}

}
