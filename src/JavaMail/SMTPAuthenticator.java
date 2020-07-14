package JavaMail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {

	public static void main(String[] args) 
	{
	}//end of main method
	
	//구글은 본인인증함
	protected PasswordAuthentication getPasswordAuthentication() 
	{
		String userName = "progJdoh302@gmail.com";
		String password = "zeroOEO0";
		
		return new PasswordAuthentication(userName, password);
	}

}
