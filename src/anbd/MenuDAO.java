package anbd;

import java.sql.SQLException;
import java.util.ArrayList;

public class MenuDAO extends DbInfo{
	
	public int count = 0;  //전체 게시물 갯수
	
	//근데 이 메소드에 검색 option이 적용이 안되있네... + String menu, String option
	public void selMainList( ArrayList<AnbdVO> mainList, int startRow, int pageSize, String mKey, String menu) {
		String SQL  = "select count(*) as count from board ";
		
		//if( !menu.equals("") || menu!=null) {  //메뉴가 있으면
		if( menu.equals("아나") || menu.equals("바다") ) {  //메뉴가 있으면
			SQL += "where menu= '"+menu+"' ";
		}else{ SQL +=""; }
		
//		if( !mKey.equals("") || mKey!=null){ //검색어가 있으면 --> 검색어도 있고, 메뉴도 있으면.. 조건 수정해야 함
//			SQL += "where title like '%" + mKey +"%' ";
//			System.out.println("mKey: "+mKey);
//		}else{ SQL +=""; }
		
		System.out.println("selMainList SQL"+SQL);
		
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
		
		//if( !menu.equals("") || menu!=null) {  //메뉴가 있으면
		if( menu.equals("아나") || menu.equals("바다") ) {  //메뉴가 있으면
			SQL += "where menu= '"+menu+"' ";
		}else{ SQL +=""; }
		
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
	
	public boolean selBoardList(ArrayList<AnbdVO> blist, String menu)  //메뉴 v1..(limit을 생각 안함..)
	//BoardParam의 정보를 ArrayList 형식으로 담겠다
	//리스트는 어떤 타입이 올지 모른다 - int를 가져와도 좋고, String을 가져와도 좋다
	//담고 싶은 내용을 <> 사이에 표현한다
	//제네릭, 템플릿을 알아보자
	//boolean으로 한 이유는 try한 결과가 true라면 값을 반환하는 의미
	//= 검색한 결과를 모두 봐야 하고, 하나의 레코드만 있는 것이 아님 = 
	//= 반복해야 함 = for 구문 사용하면 좋음 = list를 for 구문으로 받을 수 있음
	{	
		try 
		{
			getConnection();
			createStatement();
			String selectSql = "";
				  selectSql += "SELECT no, menu, title, photo, wdate, status, content ";
				  selectSql += "FROM board ";
			if(menu!=null) {
				  selectSql += "where menu= '"+menu+"'";
			}
				  selectSql += "ORDER BY no desc ";
			System.out.println(selectSql);

			rs = state.executeQuery(selectSql);
			if(rs.next()) 
			{
				do
				{
					//BoardParam를 새로 1개만 만들겠어
					AnbdVO vo = new AnbdVO();
					
					//BoardParam boardList = blist.get(i);
					vo.setNo(rs.getInt("no"));
					vo.setMenu(rs.getString("menu"));
					vo.setTitle(rs.getString("title"));
					vo.setPhoto(rs.getString("photo"));
					vo.setWdate(rs.getString("wdate"));
					vo.setStatus(rs.getString("status"));
					vo.setContent(rs.getString("content"));
					
					//세팅한 no, menu 등의 정보를 blist에 담겠다
					blist.add(vo);
						
				}//do FLOW
				while(rs.next());
				//do를 실행한 후 while에서 조건을 확인
				//다음 결과가 있으면 true, do를 다시 실행
				//다음 결과가 없으면 false, 멈춤
			}//====if FLOW
			rsClose();
			stateClose();
			conClose();
		} //=======try FLOW
		catch (SQLException e) 
		{
			System.out.println("목록 select 쿼리 실행 불가");
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
