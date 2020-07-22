package mvc2.vo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BoardVO { //UserVO, CommentVO 별도
	
	/* ******************************
	 * Board						*
	 ********************************/
	private int    	 no;
	private String 	 title 	 = "";
	private String 	 content = "";
	private String 	 photo 	 = "";
	private String 	 wdate 	 = ""; //책: private Date BOARD_DATE;
	private String 	 menu 	 = "";
	private String 	 status	 = ""; 
	private String	 key	 = ""; //?
	private int		 userNo; 	   //추가
	
	/* ******************************
	 * File							*
	 ********************************/
	private int				  fileNo;
	private int 			  size		     = 10*1024*1024; //10MB
	private ArrayList<String> fileList 	     = null;  //글보기 파일리스트
	private ArrayList<String> modifyFileList = null;  //글수정 파일리스트
	private ArrayList<String> delFileList    = null;  //글수정 파일 삭제리스트
	private ArrayList<String> SaveFileName   = null;  //글쓰기 파일리스트
	private String 			  fileName	     = "";
	private String 			  uploadPath     = "";
	private String 			  tagName	     = "";
	private String 			  saveName	     = "";
	private int 			  delFileCount;  //글수정에서 삭제하는 파일 갯수
	
	/* ******************************
	 * 그 외 변수						*
	 ********************************/
	private String strDate = "";
	private String sigun   = "";
	private String sido    = "";
	private int    jusoNo  = 0;
	
	/* ******************************
	 * 생성자							*
	 ********************************/
	public BoardVO(){
		fileList = new <String>ArrayList();
		SaveFileName = new <String>ArrayList();   //글쓰기 파일리스트 생성
		modifyFileList = new <String>ArrayList(); //글수정 파일리스트 생성
		delFileList = new <String>ArrayList();    //글수정에서 파일 삭제한거 리스트
		//날짜 생성
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy/MM/dd");
		strDate = sDate.format(Calendar.getInstance().getTime());
	}
	
	// 글쓰기 파일리스트
	public int getFileSize(){
		return SaveFileName.size();
	}
	//mvc2 글쓰기 - 첨부파일 실제 파일명들 저장
	public void addSaveFileName(String File){
		SaveFileName.add(File); 
	}
	//mvc2 글쓰기 - 첨부파일 실제 파일명들 가져오기
	public String getSaveFileName(int i){ 
		return (String)SaveFileName.get(i); 
	}
	
	// key
	public String getKey(){
		return tagName;
	}
	public String setKey(String key){
		return this.key = key;
	}
	// 파일 이름
	public String getFileName(){
		return fileName;
	}
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	// 파일 크기
	public int getSize(){
		return size;
	}
	public void setSize(int size){
		this.size = size;
	}
	// 배열에 넣기
	public void AddFile(String FileName){
		fileList.add(FileName); 
	}
	// 배열 갯수
	public int GetFileCount(){
		return fileList.size(); 
	}
	// jsp에서 for문으로 파일명 가져오기
	public String GetFile(int i){
		return (String)fileList.get(i); 
	}
	public ArrayList GetFileList(){
		return fileList;
	}

	//tagName - input file의 name값
	//정도희 사용 - jsp의 name 이름
	public String getTagName(){ 
		return tagName;
	}
	public String setTagName(String tagName){
		return this.tagName = tagName;
	}
	//saveName - 실제 저장된 파일명
	public String getSaveName(int i){ 
		return (String)SaveFileName.get(i); 
		//return saveName;
	}
	public String setSaveName(String saveName){
		return this.saveName = saveName;
	}
	// 글수정 파일
	public void addModifyFile(String modifyFile){
		modifyFileList.add(modifyFile); 
	}
	public int getModifyFileCount(){
		return modifyFileList.size(); 
	}
	public String getModifyFile(int i){
		return (String)modifyFileList.get(i); //jsp에서 for문으로 파일명 가져오기
	}
	public ArrayList getModifyFileList(){
		return modifyFileList;
	}
	
	// 글수정 삭제할 파일 리스트 - delFileList
	public void addDelFileList(String delFile){
		delFileList.add(delFile); 
	}
	public int getDelFileCount(){
		return delFileList.size(); 
	}
	public String getDelFile(int i){
		return (String)delFileList.get(i); //jsp에서 for문으로 파일명 가져오기
	}
	public ArrayList getDelFileList(){
		return delFileList;
	}
	
	// 삭제할 파일 갯수 delFileCount
//	public int getDelFileCount(){
//		return delFileCount;
//	}
//	public void addDelFileCount(int delFile){
//		this.delFileCount = delFile;
//	}
	
	// 글쓰기 - 실제 파일 위치
	public String getUploadPath(){
		return uploadPath;
	}
	public void setUploadPath(String uploadPath){
		this.uploadPath = uploadPath;
	}
	// Board Get
	public int getNo(){
		return no;
	}
//	public int getLoginUserNo(){
//		return loginUserNo;
//	}
	public String getTitle(){
		return title;
	}
	public String getContent(){
		return content;
	}
	public String getPhoto(){
		return photo;
	}
	public String getWdate(){
		return wdate;
	}
	public String getMenu(){
		return menu;
	}
	public String getStatus(){
		return status;
	}
	public int getUserNo(){
		return userNo;
	}
	// Board Set
	public void setNo(int no){
		this.no = no;
	}
//	public void setLoginUserNo(int loginUserNo){
//		this.loginUserNo = loginUserNo;
//	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setContent(String content){
		this.content = content;
	}
	public void setPhoto(String photo){
		this.photo = photo;
	}
	public void setWdate(String wdate){
		this.wdate = wdate;
	}
	public void setMenu(String menu){
		this.menu = menu;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public void setUserNo(int userNo){
		this.userNo = userNo;
	}
	// File
	public int getFileNo(){
		return fileNo;
	}
	public void setFileNo(int fileNo){
		this.fileNo = fileNo;
	}
	// 시군구
	public String getSigun(){
		return sigun;
	}
	public void setSigun(String sigun){
		this.sigun = sigun;
	}
	// 시도
	public String getSido(){
		return sido;
	}
	public void setSido(String sido){
		this.sido = sido;
	}
	// 주소 번호
	public int getJusoNo(){
		return jusoNo;
	}
	public void setJusoNo(int jusoNo){
		this.jusoNo = jusoNo;
	}
}
