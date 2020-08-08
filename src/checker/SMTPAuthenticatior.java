package checker;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticatior extends Authenticator{  
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("ggwebsite@naver.com","skwodnjs12");
    }
}