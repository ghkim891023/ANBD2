package anbd;

import java.sql.SQLException;
import java.util.ArrayList;

public class PageDAO extends DbInfo{
	
	public int count = 0;  //전체 게시물 갯수
	
	public void selMainList( ArrayList<AnbdVO> mainList, int startRow, int pageSize, String mKey) {
		String SQL  = "select count(*) as count from board ";
		System.out.println("mKey: "+mKey);
		if( !mKey.equals("") || mKey!=null){ //검색어가 있으면
			SQL += "where title like '%" + mKey +"%' ";
			System.out.println("mKey: "+mKey);
		}
		try {
			getConnection();
			prepareStatement(SQL);
			executeQuery();
			rs.next();
			count = Integer.parseInt(rs.getString("count"));
			System.out.println("총 게시글 수: "+count);
		} catch (Exception e) {
			System.out.println("select count(*) 에러: "+e.getMessage());
			e.printStackTrace();
		}
		rsClose();
		pstateClose();
		
		SQL  = "select no, menu, status, title, photo, wdate from board ";
		if( !mKey.equals("")){ //검색어가 있으면
			SQL += "where title like '%" + mKey +"%' ";
		}
	   	SQL += "order by no desc limit " + startRow + "," + pageSize + "  ";
	   	System.out.println("10개씩 목록 select 구문  : "+SQL);
		try {
			prepareStatement(SQL);
			executeQuery();
			while(rs.next()) { 
				AnbdVO vo = new AnbdVO();
				vo.setNo(rs.getInt("no")); 
				vo.setMenu(rs.getString("menu")); 
				vo.setStatus(rs.getString("status")); 
				vo.setTitle(rs.getString("title"));
				vo.setPhoto(rs.getString("photo")); 
				vo.setWdate(rs.getString("wdate"));
				mainList.add(vo);
			}
		}catch (Exception e) {
			System.out.println("selMainList 에러: "+e.getMessage()); 
		}
		rsClose();
		pstateClose();
		conClose();
	}
	
	//글 목록만(검색어 적용 안한거)
	public void selMainList_v1( ArrayList<AnbdVO> mainList, int startRow, int pageSize ) {
		String SQL  = "select count(*) as count from board ";
		try {
			getConnection();
			prepareStatement(SQL);
			executeQuery();
			rs.next();
			count = Integer.parseInt(rs.getString("count"));
			System.out.println("총 게시글 수: "+count);
		} catch (Exception e) {
			System.out.println("select count(*) 에러: "+e.getMessage());
			e.printStackTrace();
		}
		rsClose();
		pstateClose();
		
		SQL  = "select no, menu, status, title, photo, wdate from board ";
	   	SQL += "order by no desc limit " + startRow + "," + pageSize + "  ";
	   	System.out.println("10개씩 목록 select 구문  : "+SQL);
		try {
			prepareStatement(SQL);
			executeQuery();
			while(rs.next()) { 
				AnbdVO vo = new AnbdVO();
				vo.setNo(rs.getInt("no")); 
				vo.setMenu(rs.getString("menu")); 
				vo.setStatus(rs.getString("status")); 
				vo.setTitle(rs.getString("title"));
				vo.setPhoto(rs.getString("photo")); 
				vo.setWdate(rs.getString("wdate"));
				mainList.add(vo);
			}
		}catch (Exception e) {
			System.out.println("selMainList 에러: "+e.getMessage()); 
		}
		rsClose();
		pstateClose();
		conClose();
	}


}
