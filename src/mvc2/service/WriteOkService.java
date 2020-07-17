package mvc2.service;

import static mvc2.db.DbInfo.*;
import java.sql.Connection;
import mvc2.dao.BoardDAO;
import mvc2.vo.BoardVO;

public class WriteOkService {

	public boolean registArticle(BoardVO boardVo) throws Exception{
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		//getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int insertCount = boardDAO.insertArticle(boardVo);
		
		if(insertCount > 0){
			commit(con);
			isWriteSuccess = true;
		}else{
			rollback(con); //?
		}
		
		close(con);
		return isWriteSuccess;
		
	}
}
