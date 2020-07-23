package mvc2.dao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import anbd.AnbdVO;
import mvc2.db.DbInfo;
import mvc2.vo.BoardVO;
//import static mvc2.db.DbInfo.*; //책 - 변수,메소드를 패키지, 클래스명 없이 사용가능..하다는 데 안됨

public class BoardDAO extends DbInfo{
	
	public boolean inWriteMVC2(BoardVO vo){ //mvc2용 글쓰기 메소드
		try{
			getConnection();
			//글 insert 시작
			String insertBoardSql  = "INSERT INTO board ";
			insertBoardSql += "(menu, title, content, userNo, wdate, photo, jusoNo) ";
			insertBoardSql += "VALUES (?, ?, ?, ?, curdate(), ?, ?)";
			
			prepareStatement(insertBoardSql);
			pstate.setString(1, vo.getMenu());
			pstate.setString(2, vo.getTitle());
			pstate.setString(3, vo.getContent());
			pstate.setInt(4, vo.getUserNo());
			pstate.setString(5, vo.getPhoto());
			pstate.setInt(6, vo.getJusoNo());
			executeUpdate();
			
			//글 번호 구하기 - DB connect가 새로 되서, 마지막으로 인서트한 번호를 찾을 수 없음
//			AnbdDAO dao = new AnbdDAO();
//			int insertNo = dao.selLastInsert();
//			vo.setNo(insertNo);
			
			String selSQL = "SELECT LAST_INSERT_ID() as insertNo";
			prepareStatement(selSQL);
			executeQuery();
			if(rs.next()){
				int insertNo = rs.getInt("insertNo");
				vo.setNo(insertNo);
			}
			
			//파일 insert
			if(vo.getFileSize() >0) { //파일이 있을때만 file insert 실행
				for(int i=0; i<vo.getFileSize(); i++) {
					String SQL  = "INSERT INTO file (saveFileName, no) ";
					SQL += "VALUES (?, ?) ";
					prepareStatement(SQL);
					pstate.setString(1, vo.getSaveFileName(i));
					pstate.setInt(2, vo.getNo());
					executeUpdate();
				}
			}
		}catch (SQLException e) {
			System.out.println("inWriteMVC2() 에러: "+e.getMessage());
			return false;
		}finally{
			pstateClose();		
			conClose();	
		}
		return true;
	}//inWriteMVC2
	
	//책 - 글 수정
//	public int updateArticle(BoardBean article){
//		int updateCount = 0;
//		PreparedStatement pstmt = null;
//		String sql="update board set BOARD_SUBJECT=?,BOARD_CONTENT=? where BOARD_NUM=?";
//
//		try{
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, article.getBOARD_SUBJECT());
//			pstmt.setString(2, article.getBOARD_CONTENT());
//			pstmt.setInt(3, article.getBOARD_NUM());
//			updateCount = pstmt.executeUpdate();
//		}catch(Exception ex){
//			System.out.println("boardModify 에러 : " + ex);
//		}finally{
//			close(pstmt);
//		}
//		return updateCount;
//	}
	
	public int upModifyBoard(BoardVO vo) {
		int updateCount = 0;
		getConnection();
		
		//1.파일 수정
		//1-1.지운 파일 삭제
		if(vo.getDelFileCount() > 0) { //지울파일 갯수가 0이상이면
			for(int i=0; i<vo.getDelFileCount(); i++) { //지울파일 갯수만큼 delete 실행
				String SQL  = "delete from file where saveFileName=? ";
				try {
					prepareStatement(SQL);
					pstate.setString(1, vo.getDelFile(i));
					executeUpdate();
					System.out.println("===파일 삭제 완료===");
				}catch (SQLException e) {
					System.out.println("BoardDAO: upModifyBoard() 파일 삭제 에러: "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		//1-2.추가한 파일 추가
		if(vo.getModifyFileCount() >0 ) { //글수정시 추가된 파일이 있으면 (남은 파일 갯수가 0이상이면-> 그냥 나둬도 될듯?)
			for(int i=0; i<vo.getModifyFileCount(); i++) {
				String SQL  = "INSERT INTO file (saveFileName, no) VALUES (?, ?) ";
				System.out.println("파일 추가 insert: "+SQL);
				try {
					prepareStatement(SQL);
					pstate.setString(1, vo.getModifyFile(i));
					pstate.setInt(2, vo.getNo());
					executeUpdate();
					System.out.println("===파일 추가 완료===");
				}catch (SQLException e) {
					System.out.println("BoardDAO: upModifyBoard() 파일 추가 에러: "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		//3.글 수정 시작
		String SQL  = "UPDATE board SET menu=?, title=?, content=?, photo=? "; //jusoNo=?
			   SQL += "where no=?";
		prepareStatement(SQL);
		try {
			pstate.setString(1, vo.getMenu());
			pstate.setString(2, vo.getTitle());
			pstate.setString(3, vo.getContent());
			pstate.setString(4, vo.getPhoto());
			//pstate.setInt(5, vo.getJusoNo());
			pstate.setInt(5, vo.getNo());
			System.out.println("글수정 SQL: "+SQL);
			updateCount = executeUpdateInt();
		} catch (SQLException e) {
			System.out.println("BoardDAO: upModifyBoard() 글수정 에러 "+e.getMessage());
			e.printStackTrace();
		}finally{ //마지막에서만 되겠지?
			pstateClose();		
			conClose();	
		}
		return updateCount;
	}

}//class
