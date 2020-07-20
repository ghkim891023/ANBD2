package mvc2.service;

import static mvc2.db.DbInfo.*;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import anbd.AnbdDAO;
import mvc2.dao.BoardDAO;
import mvc2.db.DbInfo;
import mvc2.vo.BoardVO;

public class WriteOkService extends DbInfo{

	//나중에 책처럼 HttpServletRequest request, int userNo 빼는 메소드로 수정
	public boolean registArticle(BoardVO boardVo) throws Exception{
		
		boolean isWriteSuccess = false;
		//Connection con = getConnection();
		getConnection();
		
		AnbdDAO dao = new AnbdDAO();
		boolean inWrite = dao.inWriteMVC2(boardVo);
		System.out.println("inWrite 결과: "+inWrite);

		return inWrite;
		
		
	}
	//책
//	public boolean registArticle(BoardVO boardVo) throws Exception{
//		
//		boolean isWriteSuccess = false;
//		Connection con = getConnection();
//		//getConnection();
//		BoardDAO boardDAO = BoardDAO.getInstance();
//		boardDAO.setConnection(con);
//		int insertCount = boardDAO.insertArticle(boardVo);
//		
//		if(insertCount > 0){
//			commit(con);
//			isWriteSuccess = true;
//		}else{
//			rollback(con); //?
//		}
//		
//		close(con);
//		return isWriteSuccess;
//		
//	}
}
