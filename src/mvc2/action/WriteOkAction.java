package mvc2.action;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import anbd.AnbdDAO;
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
		Enumeration inputFileNames = multi.getFileNames();//file태그  name 값들, Enumeration객체에 String형태로 담아 반환
		
		boardVo = new BoardVO();
		
		while(inputFileNames.hasMoreElements()) { //inputFileNames 요소가 있으면 true
			String inputFileName = (String)inputFileNames.nextElement(); //name들 중에 name 한개를 받아와 string으로 저장
			String serverSaveName;
				serverSaveName = (String)multi.getFilesystemName(inputFileName);//filename1번의 업로드된 파일명
				if( serverSaveName == null) { 
					boardVo.setPhoto("N"); 
				}else { //첨부파일 있으면
					System.out.println("업로드된 파일명: "+serverSaveName);
					boardVo.setPhoto("Y");
					boardVo.addSaveFileName(serverSaveName);
				}
		}
		
		boardVo.setMenu(multi.getParameter("menu"));
		boardVo.setTitle(multi.getParameter("title"));
		
		//글내용 XSS 공격 방지
		//boardVo.setContent(multi.getParameter("content"));
		AnbdDAO dao = new AnbdDAO();
		boardVo.setContent(dao.xssReplaceAll(multi.getParameter("content")));
		
		String[] SigunNo = multi.getParameter("sigun").split(":");
		int jusoNo = Integer.parseInt(SigunNo[0]);
		boardVo.setJusoNo(jusoNo);
		//UserVo userVo = new UserVo();
		boardVo.setUserNo(Integer.parseInt(multi.getParameter("userNo")));
		
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
			forward.setPath("main.do"); //--> 글보기로 가야되는데...
		}
		return forward;
	}

}
