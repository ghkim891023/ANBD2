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
	
	//책
//	DataSource ds;
//	Connection con;
//	private static BoardDAO boardDAO;
//
//	public static BoardDAO getInstance(){
//		if(boardDAO == null){
//			boardDAO = new BoardDAO();
//		}
//		return boardDAO;
//	}
//
//	public void setConnection(Connection con){
//		this.con = con;
//	}
//	
//	public int insertArticle(BoardVO bVo){
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		int num =0;
//		String sql="";
//		int insertCount=0;
//
//		try{pstmt=con.prepareStatement("select max(board_num) from board");
//			rs = pstmt.executeQuery();
//
//			if(rs.next())
//				num =rs.getInt(1)+1;
//			else
//				num=1;
//
//			sql  = "insert into board (menu, title, content, userNo, wdate, photo, jusoNo)";
//			sql += "VALUES (?, ?, ?, ?, curdate(), ?, ?)";
//
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, bVo.getNo());
//			pstmt.setString(2, bVo.getMenu());
//			pstmt.setString(3, bVo.getTitle());
//			pstmt.setString(4, bVo.getContent());
//			pstmt.setString(5, bVo.getPhoto());
//			pstmt.setInt(6, bVo.getJusoNo());
//			pstmt.setInt(7, bVo.getUserNo());
//
//			insertCount=pstmt.executeUpdate();
//			
//		}catch(Exception ex){
//			System.out.println("boardInsert 에러 : "+ex);
//		}finally{
//			close(rs);
//			close(pstmt);
//		}
//		return insertCount;
//	}
	

}//class
