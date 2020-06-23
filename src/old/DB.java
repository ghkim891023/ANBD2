package old;
// 참고 - https://reizeo.tistory.com/entry/JSP-java빈을-활용한-db연결하기

//import java.sql.*;
import java.sql.Connection;    //DB와의 연결 관리하는 객체
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DB {

	String url = "jdbc:mysql://127.0.0.1/anbd?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
	String id  = "root";
	String pw  = "mySQL1234";
	String driver = "com.mysql.cj.jdbc.Driver";
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs =null;

	public Connection getConnection(){ //DB연결
		try{
		Class.forName(driver);
		conn = DriverManager.getConnection(url, id, pw);
		System.out.println("DB에 연결되었습니다.");
		}catch(SQLException se){
		 se.printStackTrace();
		}catch(Exception e){
		 e.printStackTrace();
		}
		return conn;
	}
 
	public void insert(String sql) {
		try {
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate();
			conn.close();
			System.out.println("레코드를 등록하였습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void select(String sql) {
		try {
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery();
			System.out.println("select 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("select 메소드 에러: "+e.getMessage() );
		}
	}
	
	public void close() {
		try {
			//rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DB db = new DB();
		//db.getConnection();
	}
}
