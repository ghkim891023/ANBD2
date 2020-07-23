package JavaMail;

import java.security.Key;

import javax.mail.MessagingException;

public class PostMan 
{
	public static void main(String[] args) 
	{
		
	}//end of main method
	
	public static void postMain(String email, String id) 
	{
		anbd.AnbdVO vo = new anbd.AnbdVO();
		vo.setEmail(email);
		vo.setId(id);
		String from = "progJdoh302@gmail.com";
		String to = vo.getEmail();
		String cc = to;
		String subject = "html 링크 보내기";
		String content = "링크를 클릭하면 본인인증을 마칩니다.\n";
			   content += "<a href=\"http://192.168.0.65:8090/anbd2/main/autenthicate.jsp?id="+vo.getId()+"\">본인인증 완료하기</a>\n";
		
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
				System.out.print("메일 전송 실패\n실패사유 : ");
				System.out.println(e.getMessage());
				
			}
			catch (Exception e) 
			{
				System.out.println("메일 전송 실패");
			}
		}
	}//end of postMain method
	
	
}//end of PostMan
