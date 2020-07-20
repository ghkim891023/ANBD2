package mvc2.db;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbInfo {
	
//학원
	String 			  url 	 = "jdbc:mysql://192.168.0.77/anbd?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
	String 			  driver = "com.mysql.cj.jdbc.Driver";
	String 			  id  	 = "kanu";
	String 			  pw  	 = "1234";
//집
//	String 			  url 	 = "jdbc:mysql://127.0.0.1:3307/anbd?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
//	String 			  url 	 = "jdbc:mysql://127.0.0.1:3306/anbd?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
//	String 			  driver = "com.mysql.cj.jdbc.Driver";
//	String 			  id  	 = "root";
//	String 			  pw  	 = "111111";
	
	protected Statement 	    state  = null; 
	protected ResultSet 	    rs	   = null; 
	protected Connection	    con    = null; 
	protected PreparedStatement pstate = null;

	public Connection getConnection(){
		try{
		Class.forName(driver);
		con = DriverManager.getConnection(url, id, pw);
		System.out.println("===DB 연결===");
		}
		catch(SQLException se){   
			se.printStackTrace();
			System.out.println("DB 엑세스 에러: "+se.getMessage() );
		}catch(Exception e){
			System.out.println("getConnection() 에러: "+e.getMessage() );
		}
		return con;
	}//getConnection
	
 	public void prepareStatement(String sql){ 
		try{
			pstate = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		}catch (SQLException e){
			System.out.println("prepareStatement() 에러: "+e.getMessage() );
		}
	}//prepareStatement
 	
 	public void createStatement(){ 
		try{
			state = con.createStatement();
		} catch (SQLException e){
			System.out.println("createStatement() 에러: "+e.getMessage() );
		}
	}//createStatement
 	
	//Insert, Update, Delete 쿼리
	public void executeUpdate(){ 
		try {
			pstate.executeUpdate();
		} catch (SQLException e) {
			System.out.println("executeUpdate() 에러: "+e.getMessage() );
		}
	}//executeUpdate
	
	//Select 쿼리
	public void executeQuery() {
		try {
			rs = pstate.executeQuery(); //쿼리를 실행하고 결과를 ResultSet 객체에 담는다.
		} catch (SQLException e) {
			System.out.println("executeQuery() 에러: "+e.getMessage() );
		}
	}//executeQuery
	
	//닫기
	public void stateClose() {
		try {
			if (state != null) {
				state.close();
			}
		} catch (SQLException e) {
			System.out.println("stateClose() 에러: "+e.getMessage() );
		}
	}//stateClose
	
	public void pstateClose() {
		try 
		{
			if(pstate!=null||!pstate.isClosed()){
				pstate.close();
			}
		} catch (SQLException e) {
			System.out.println("pstateClose() 에러: "+e.getMessage() );
		}
	}//pstateClose
	
	public void conClose() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("conClose() 에러: "+e.getMessage() );
		}
	}//conClose
	
	public void rsClose(){
		try{
			if(rs!=null||!rs.isClosed()){
				rs.close();
			}
		}catch (SQLException e){
			System.out.println("rsClose() 에러: "+e.getMessage() );
		}
	}//rsClose
		
	
	//책 복붙
	/*
	public static Connection getConnection(){
		Connection con=null;
		try {
			Context initCtx = new InitialContext(); //JNDI 서버 객체 새성
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
/* DataSource
 *   커넥션 풀의 Connection을 관리하기 위한 객체..
 *   DataSource 객체를 통해서 필요한 Connection을 획득, 반납 등의 작업을 한다.
 *   
 * DataSource를 이용하려면 다음의 절차를 따릅니다.
 *   JNDI Server에서 lookup( ) 메소드를 통해 DataSource 객체를 획득한다.
 *   DataSource 객체의 getConnection( ) 메소드를 통해서 Connection Pool에서 Free 상태의 Connection 객체를 획득한다.
 *   Connection 객체를 통한 DBMS 작업을 수행한다.
 *   모든 작업이 끝나면 DataSource 객체를 통해서 Connection Pool에 Connection을 반납한다. - rs/stmt/con close
 https://opentutorials.org/module/3569/21223 */
/*			DataSource ds = (DataSource)envCtx.lookup("jdbc/MySQLDB");//xml에 db연결 정보 읽는, 우린 연결되는거 확인했으니 생략가능 
			con = ds.getConnection();
			con.setAutoCommit(false); // 오토커밋을 false로 지정
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con){
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt){
		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs){
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//트랜잭션(Transaction)의 commit 을 수행, 기본적으로는 auto 커밋됨 - https://hyeonstorage.tistory.com/113
	public static void commit(Connection con){
		try {
			con.commit(); 
			System.out.println("commit success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con){
		try {
			con.rollback(); //트랜잭션(Transaction)의 rollback, Exception 발생시 rollback 
			System.out.println("rollback success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
