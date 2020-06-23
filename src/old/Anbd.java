package old;

import javax.servlet.http.HttpServletResponse;

public class Anbd extends DB {
	
	public Anbd() {//생성자
		//DB db = new DB();
		//db.getConnection();
		//getConnection(); //useBean만 선언해도 DB연결함
	}
	
	// User
	private int userNo;      //회원 - DB에 들어가는 변수명
	private String id;
	private String pw;
	private String name;
	private String email;
	// Board
	private int no;
	private String title;
	private String content;
	private String photo;  //Y/N - String? Boolean?
	private String wdate;
	private String menu;
	private String notice; //Y/N
	// File
	private int fileNo;
	private String fileName;
	// Comment
	private int coNo;
	private String cContent;
	private String cWdate;
	
	// Get
	public int getUserNo() {
		return userNo;
	}
	public String getId() {
		return id;
	}
	public String getPw() {
		return pw;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public int getNo() {
		return no;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getPhoto() {
		return photo;
	}
	public String getWdate() {
		return wdate;
	}
	public String getMenu() {
		return menu;
	}
	public String getNotice() {
		return notice;
	}
	public int getFileNo() {
		return fileNo;
	}
	public String getFileName() {
		return fileName;
	}
	public int getCoNo() {
		return coNo;
	}
	public String getcContent() {
		return cContent;
	}
	public String getcWdate() {
		return cWdate;
	}
	// Set
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setCoNo(int coNo) {
		this.coNo = coNo;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public void setcWdate(String cWdate) {
		this.cWdate = cWdate;
	}

}
