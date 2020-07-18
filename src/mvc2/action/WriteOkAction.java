package mvc2.action;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc2.service.WriteOkService;
import mvc2.vo.ActionForward;
import mvc2.vo.BoardVO;

public class WriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//책 복붙 후 수정
		ActionForward forward=null;
		BoardVO boardVo = null;
		String realFolder="";
		String saveFolder="/upload";
		int size=10*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);  
		
		MultipartRequest multi=new MultipartRequest(request, realFolder, size, "UTF-8", new DefaultFileRenamePolicy());
		boardVo = new BoardVO();
		//boardVo.setNo(Integer.parseInt(multi.getParameter("no")));
		boardVo.setMenu(multi.getParameter("menu"));
		boardVo.setTitle(multi.getParameter("title"));
		boardVo.setContent(multi.getParameter("content"));
		boardVo.setPhoto(multi.getParameter("photo"));
		String[] SigunNo = multi.getParameter("sigun").split(":");
		int jusoNo = Integer.parseInt(SigunNo[0]);
		boardVo.setJusoNo(jusoNo);
		//UserVo userVo = new UserVo();
		//userVo.setUserNo(multi.getParameter("userNo"));
		boardVo.setUserNo(Integer.parseInt(multi.getParameter("userNo")));
		//boardVo.setFILE(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		
		WriteOkService writeOk = new WriteOkService();
		boolean isWriteSuccess = writeOk.registArticle(boardVo);

		if(!isWriteSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글쓰기 등록에 실패하였습니다.')");
			out.println("history.back();");
			out.println("</script>");
		}else{ //글쓰기 성공하면
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("main.do"); //--> 글쓰기로 가야되는데...
		}
		
		return forward;
	}

}
