package mvc2.action;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import anbd.AnbdDAO;
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

		//첨부파일 ★★★
		String savePath = request.getSession().getServletContext().getRealPath("/upload");
		int size = 10*1024*1024;
		MultipartRequest multi = new MultipartRequest(request, savePath, size, "UTF-8", new DefaultFileRenamePolicy());
		
		vo.setNo(no);
		vo.setMenu(multi.getParameter("menu")); //multi로 해야나???? get으로 넘어오는거 말고는 multi로?--★★
		System.out.println("menu: "+request.getParameter("menu"));
		vo.setTitle(multi.getParameter("title")); //request.
		vo.setContent(multi.getParameter("content"));
		vo.setPhoto(multi.getParameter("photo"));
		vo.setJusoNo(Integer.parseInt(multi.getParameter("jusoNo")));
		
		int preFileCount = Integer.parseInt(multi.getParameter("fileCount")); //기존 파일 갯수
		
		//기존 파일 삭제시, 삭제 파일 리스트에 add
		for(int i=1; i<=preFileCount; i++) {
			String preFile = multi.getParameter("filename"+i);//이전 파일을 val을 name값을 통해 가져오기
				System.out.println("기존 filename "+i+"번재 파일명 : "+preFile);
			if(preFile==null || preFile.equals("")){ //기존 파일을 삭제해서 파일명(val)이 공백이면
				String hiddenfilename = multi.getParameter("hiddenfilename"+i);//input hidden의 해당 i번째 val(파일명)을 가져와서 삭제
				vo.addDelFileList(hiddenfilename);
			}
		}
		int remainFileCount = preFileCount - vo.getDelFileCount(); //남은 파일 =기존 파일 갯수-삭제 파일 갯수
			System.out.println("preFileCount: "+preFileCount);
			System.out.println("delCount: "+vo.getDelFileCount());
			System.out.println("remainFileCount: "+remainFileCount);
		if(remainFileCount == 0) { //기존파일 다 삭제시 photo N
			vo.setPhoto("N");
		}
		//추가한 파일 insert
		Enumeration inputFileNames = multi.getFileNames();  //input file태그의  name 속성값을 모두 가져옴
		while(inputFileNames.hasMoreElements()) { //inputFileNames의 요소가 있으면 true, 아니면 false 반환
			String inputFileName = (String)inputFileNames.nextElement(); //name들 중에 name 한개
				System.out.println("name='"+inputFileName+ "' : 파일있음");
			String serverSaveName = (String)multi.getFilesystemName(inputFileName);//실제 저장된 파일명
				//추가된 파일 있거나 or 남은 파일 있으면 (--> and? 기존파일에서 남기만 한거면 insert안해줘도?)
				System.out.println(inputFileName + " : " +serverSaveName );
				if( serverSaveName != null || remainFileCount>0 ) { 
					vo.setPhoto("Y");
					vo.addModifyFile(serverSaveName);
					System.out.println("if here");
				}else {
					vo.setPhoto("N");
					System.out.println("else here");
				}
		}
		
		//XSS
		AnbdDAO dao = new AnbdDAO();
		vo.setContent(dao.xssReplaceAll(multi.getParameter("content")));
		
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
