package api;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {

	public static void sendEmail(String email, String id) {

		String mailId 		= "anbdmaster@gmail.com";  // 구글계정
		String mailPassword = "anbd1234"; 			   // 구글계정 비밀번호 
		String fromName 	= "ANBD";				   // 보내는사람 이름
		String fromEmail 	= "anbdmaster@gmail.com";  // 보내는사람 메일
		String toName 		= id;					   // 받는사람 이름
		String toEmail 		= email; 			       // 받는사람 메일 "cromgh@naver.com";
		String mailTitle 	= "ANBD 회원가입 인증 메일입니다.";
		String mailContents = "ANBD에 오신걸 환영합니다.!<br>이메일 인증을 완료하려면, 다음 링크를 클릭하세요. <br>"
						     +"<a href='http://192.168.0.68:8090/anbd2/main/main.jsp'>이메일 인증 완료하기</a>"; //집 127.0.0.1:8080
		String debugMode	= "false";
		String authMode		= "true";

		try {
			boolean debug = Boolean.valueOf(debugMode).booleanValue();
			
			//Property에 SMTP 서버 정보를 설정
			Properties prop = new Properties();
			prop.put("mail.smtp.starttls.enable", "true"); //ssl, starttls(보안 종류)
			prop.setProperty("mail.transport.protocol", "smtp"); 
			prop.put("mail.debug", debugMode);
			prop.put("mail.smtp.host", "smtp.gmail.com"); //이메일 발송을 처리해줄 STMP 서버
			prop.put("mail.smtp.port", "587"); 			  //포트번호
			prop.put("mail.smtp.connectiontimeout", "5000");
			prop.put("mail.smtp.timeout", "5000");  
			prop.put("mail.smtp.auth", authMode);
			
			//SMTP 서버 정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스를 생성
			Session msgSession = null;

			if(authMode.equals("true")) {
		        Authenticator auth = new MyAuthentication(mailId, mailPassword);
				msgSession = Session.getInstance(prop, auth);
			} else {
				msgSession = Session.getInstance(prop, null); 
			}
			
			msgSession.setDebug(debug);
			
			//Message 클래스의 객체를 사용하여 수신자와 내용, 제목의 메시지를 작성
			MimeMessage msg = new MimeMessage(msgSession);
			msg.setFrom(new InternetAddress(fromEmail, fromName)); 							  //발신인 설정
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, toName)); //수신인 설정
			msg.setSubject(mailTitle);
			msg.setContent(mailContents, "text/html; charset=euc-kr");

			//메일 전송, 스태틱함수로 직접 보내지 않고 객체를 이용해서 보내고 객체를 닫아준다. 
			Transport trans = msgSession.getTransport("smtp");
			try {
				trans.connect();
				trans.sendMessage(msg, msg.getAllRecipients());
			} finally {
				trans.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		JavaMail.sendEmail("cromgh@naver.com","dd"); //(받는사람 이메일, 이름) 회원 이메일, id
	}
}

class MyAuthentication extends Authenticator {
    PasswordAuthentication pwAuth;
    public MyAuthentication(String mailId, String mailPass) {
    	pwAuth = new PasswordAuthentication(mailId, mailPass);  
    }
    public PasswordAuthentication getPasswordAuthentication() { //시스템에서 사용하는 인증정보
        return pwAuth;
    }
}

