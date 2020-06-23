package old;

import java.sql.*;

public class Board extends DB{
	//ResultSet rs = null;
	
	public void saveBoard(Anbd an){
		getConnection();
		String SQL  = "insert into board (userNo, title, content, menu) ";
		  	   SQL += "values (1,'"+an.getTitle()+"','"+an.getContent()+"','"+an.getMenu()+"')";
		insert(SQL);
		//close();
	}
	
	public String getBoardList(Anbd an){ 
		String HTML = "";
		getConnection();
		String SQL  = "select title, name, menu from board NATURAL JOIN user ";
			   SQL += "order by no desc";
		select(SQL);
		try {
			while(rs.next()) {   
				an.setMenu(rs.getString("menu"));
				System.out.println(an.getMenu());
				an.setTitle(rs.getString("title"));
				an.setName(rs.getString("name"));
				
				HTML +="<tr>";
				HTML +="<td>" + an.getMenu() +"</td>";
				HTML +="<td>" + an.getTitle() +"</td>";
				HTML +="<td>" + an.getName() +"</td>";
				HTML +="</tr>";
			}
		} catch (SQLException e) {
			System.out.println("rs.next() 에러: "+e.getMessage() );
		}
		close();
		return HTML;
	}
	
	public void getBoardList0(Anbd an){ //안됨
		getConnection();
		String SQL  = "select title, name, menu from board NATURAL JOIN user";
		select(SQL);
		try {
			while(rs.next()) {   //while(rs != null)
				an.setMenu(rs.getString("menu"));
				System.out.println(an.getMenu());
				an.setTitle(rs.getString("title"));
				an.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			System.out.println("rs.next() 에러: "+e.getMessage() );
		}
		close();
	}
	
	public static void main(String[] args) {

	}

}
