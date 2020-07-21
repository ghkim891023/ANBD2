package mvc2.service;

import mvc2.dao.BoardDAO;
import mvc2.db.DbInfo;
import mvc2.vo.BoardVO;

public class viewModifyOkService extends DbInfo{

	//책
//	public boolean isArticleWriter(int board_num) throws Exception {
		
//		boolean isArticleWriter = false; //글쓴이가 맞는지 확인, 로그인 안하고 글쓰기 가능한 경우..
//		getConnection();
//		
//		BoardDAO dao = BoardDAO.getInstance();
//		boardDAO.setConnection(con);
//		isArticleWriter = boardDAO.isArticleBoardWriter(board_num, pass);
//		close(con);
//		return isArticleWriter;
//	}

	public boolean modifyArticle(BoardVO vo) throws Exception {
		
		boolean isModifySuccess = false;
		
		BoardDAO dao = new BoardDAO();
		int updateCount = dao.upModifyBoard(vo); //int형 메소드
		
		if(updateCount > 0){
			isModifySuccess=true;
		}else{
			//rollback(con);
		}
		
		return isModifySuccess;
	}
}
