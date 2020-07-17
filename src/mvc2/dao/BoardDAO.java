package mvc2.dao;

import static mvc2.db.DbInfo.*; //변수,메소드를 패키지, 클래스명 없이 사용가능

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import anbd.AnbdVO;
import mvc2.vo.BoardVO;

public class BoardDAO {
	//책 복붙..
	DataSource ds;
	Connection con;
	private static BoardDAO boardDAO;

	public static BoardDAO getInstance(){
		if(boardDAO == null){
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}

	public void setConnection(Connection con){
		this.con = con;
	}
	
	//글 등록.
	public int insertArticle(BoardVO bVo){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql="";
		int insertCount=0;

		try{pstmt=con.prepareStatement("select max(board_num) from board");
			rs = pstmt.executeQuery();

			if(rs.next())
				num =rs.getInt(1)+1;
			else
				num=1;

			sql  = "insert into board (menu, title, content, userNo, wdate, photo, jusoNo)";
			sql += "VALUES (?, ?, ?, ?, curdate(), ?, ?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bVo.getNo());
			pstmt.setString(2, bVo.getMenu());
			pstmt.setString(3, bVo.getTitle());
			pstmt.setString(4, bVo.getContent());
			pstmt.setString(5, bVo.getPhoto());
			pstmt.setInt(6, bVo.getJusoNo());
			pstmt.setInt(7, bVo.getUserNo());

			insertCount=pstmt.executeUpdate();

		}catch(Exception ex){
			System.out.println("boardInsert 에러 : "+ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}
	
	//우리꺼 글쓰기 메소드
//	public boolean inWrite(AnbdVO vo, HttpServletRequest request, int userNo)
//	{
//		MultipartRequest multi;
//		vo.SaveFileName = new ArrayList<>();
//		System.out.println("uploadPath: " +vo.uploadPath);
//		//파일 경로 없을 때
//		if(vo.uploadPath == null || vo.uploadPath == ""){
//			System.out.println("파일 경로를 찾을 수 없음");
//			return false;
//		}//if
//		
//		try{multi = new MultipartRequest(request,vo.uploadPath,vo.size,"UTF-8",new DefaultFileRenamePolicy());
//			
//			//name에 담긴 값을 enumeration 집합에 집어넣음
//			Enumeration contents = multi.getFileNames();
//			
//			//contents에 요소가 있는지 검사
//			while(contents.hasMoreElements()){
//				//태그의 name 값을 가져옴
//				vo.tagName  = (String)contents.nextElement();
//				vo.saveName = (String) multi.getFilesystemName(vo.tagName);
//				if(vo.saveName != null){
//					//태그의 name값이 ~인 것에 담긴 value값을 가져옴
//					vo.SaveFileName.add(vo.saveName);
//				}//if
//			}//while
//		}//try
//		catch (IOException e){
//			e.printStackTrace();
//			System.out.println("첨부파일 에러" +e.getMessage());
//			return false;
//		}//catch
//		
//		//쿼리 구문
//		try{getConnection();
//			//==글 insert 시작
//			String menu   = multi.getParameter("menu");
//			String title  = multi.getParameter("title");
//			String sigun  = multi.getParameter("sigun");
//			String sido  = multi.getParameter("sido");
//			String content= multi.getParameter("content");
//			
//			String[] jusoNoArray = sigun.split(":");
//			String jusoNo = jusoNoArray[0];
//			if(sido.equals("기타")){
//				jusoNo = "251";
//			}
//			vo.setMenu(menu);
//			vo.setTitle(title);
//			vo.setContent(content);
//			vo.setSigun(sigun);
//			if(vo.getSigun().equals("")||vo.getSigun() == null){
//				vo.setSigun("251");
//			}
//			
//			if(vo.saveName == null){
//				vo.setPhoto("N");
//			}else{
//				vo.setPhoto("Y");
//			}
//			
//			//XSS 대책 [시작]
//			String xssContent = xssReplaceAll(content);
//			vo.setContent(xssContent);
//			//XSS 대책 [종료]
//			
//			String insertBoardSql  = "INSERT INTO board ";
//				   insertBoardSql += "(menu, title, content, userNo, wdate, photo, jusoNo) ";
//				   insertBoardSql += "VALUES (?, ?, ?, ?, curdate(), ?, ?)";
//
//			prepareStatement(insertBoardSql);
//			
//			pstate.setString(1, menu);
//			pstate.setString(2, title);
//			pstate.setString(3, vo.getContent());
//			pstate.setInt(4, userNo);
//			pstate.setString(5, vo.getPhoto());
//			pstate.setString(6, jusoNo);
//
//			System.out.println(insertBoardSql);
//			System.out.println("saveName = "+vo.saveName);
//			System.out.println("SaveFileName = "+vo.SaveFileName);
//			System.out.println("vo.SaveFileName.size() = "+vo.SaveFileName.size());
//			
//			executeUpdate();//==글 insert 종료
//			
//			//==글 번호 구하기 시작
//			int insertNo = selLastInsert();
//			vo.setNo(insertNo);
//			//==글 번호 구하기 종료
//			
//			//==파일 insert 시작
//			
//			//if(vo.SaveFileName.isEmpty())
//			if(vo.SaveFileName != null || !vo.SaveFileName.equals("") || vo.SaveFileName.size() != 1){
//				System.out.println("===========파일을 첨부하지 않았음, null===========");
//			}
//			if(!vo.SaveFileName.isEmpty() ){
//				System.out.println("===========파일을 첨부했음, not null===========");
//				
//				for(int i=0; i<vo.SaveFileName.size(); i++){
//					String insertFileSql  = "INSERT INTO file ";
//					insertFileSql += "(saveFileName, no) ";
//					insertFileSql += "VALUES (?, ?)"; 
//					prepareStatement(insertFileSql);
//					pstate.setString(1, vo.getSaveName(i));
//					pstate.setInt(2, vo.getNo());
//					executeUpdate();
//					System.out.println(insertFileSql);
//				}//for
//			}//if
//		}//try
//		catch (SQLException e){
//			System.out.println("insert 쿼리 실행 불가");
//			e.printStackTrace();
//			return false;
//		}finally{
//			pstateClose();		
//			conClose();	
//		}
//		return true;
//	}//inWrite
	

}//class
