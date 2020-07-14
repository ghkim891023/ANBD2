package JavaMail;

import java.security.Key;

import javax.mail.MessagingException;

public class PostMan 
{
	public static void main(String[] args) 
	{
		String from = "progJdoh302@gmail.com";
		String to = "jdoh302@naver.com";
		String cc = to;
		String subject = "html 형식으로 받았음?";
		String content = "링크를 줄 테니 여기를 클릭해서 본인인증을 완료해보렴";
		content += "<a href=\"http://192.168.0.65:8090/anbd2/main/main.jsp\">본인인증 완료하기</a>";
		
		if(from.trim().equals(""))
		{
			System.out.println("보내는 사람을 입력하지 않았습니다.");
		}
		else if(to.trim().equals(""))
		{
			System.out.println("받는 사람을 입력하지 않았습니다.");
		}
		else 
		{
			MailTest mt = new MailTest();
			try 
			{
				mt.sendEmail(from, to, cc, subject, content);
				System.out.println("메일 전송 성공");
			}
			catch (MessagingException e) 
			{
				System.out.println("메일 전송 실패\n실패원인 : ");
				System.out.println(e.getMessage());
				
			}
			catch (Exception e) 
			{
				System.out.println("메일 전송 실패");
			}
		}
	}//end of main method
	
	
}//end of PostMan
