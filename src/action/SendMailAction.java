package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.Session;
import javax.mail.Authenticator;
import java.util.Properties;
import checker.SMTPAuthenticatior;
import dataquery.UserDataDAO;
import javabean.UserDataBean;
import jdbc.MySQLConnection;
public class SendMailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Connection conn = null;
		HttpSession session = request.getSession(true);
		
		response.setContentType("text/html;charset=UTF-8");
		try{
			
			String user_id = request.getParameter("user_id");
			String find_question = request.getParameter("find_question");
			String find_answer = request.getParameter("find_answer");
			PrintWriter out = response.getWriter();
			conn = MySQLConnection.getConnection();
			UserDataDAO dao = UserDataDAO.getInstance();
			UserDataBean data = dao.selectFromID(conn, user_id);
			int count = dao.cntselectFromID(conn, user_id);
			if(count > 0 && user_id != null && data.getUser_question().equals(find_question) && data.getUser_answer().equals(find_answer)){
				Properties p = new Properties(); // 정보를 담을 객체
				String temppw = data.getUser_password();
				String temppw2 = temppw.substring(0,temppw.length()-3);
				String pw = temppw2 + "***";
				p.put("mail.smtp.host","smtp.naver.com");
				p.put("mail.smtp.port", "465");
				p.put("mail.smtp.starttls.enable", "true");
				p.put("mail.smtp.auth", "true");
				p.put("mail.smtp.debug", "true");
				p.put("mail.smtp.socketFactory.port", "465");
				p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				p.put("mail.smtp.socketFactory.fallback", "false");
				
				try{
					Authenticator auth = new SMTPAuthenticatior();
				    Session ses = Session.getInstance(p, auth);
				      
				    ses.setDebug(true);
				    MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체 
				 
				    msg.setSubject("비밀번호 답변입니다."); //  제목
				 
				    StringBuffer buffer = new StringBuffer();
				    buffer.append("비밀번호는 ");
				    buffer.append(pw);
				    buffer.append("입니다 <br>");
				    buffer.append("다시 로그인하시려면 <a href='http://localhost:8085/Stack/login.jsp'>");
				    buffer.append("클릭하세요. <br>");
				    Address fromAddr = new InternetAddress("ggwebsite@naver.com");
				    msg.setFrom(fromAddr); 
				    Address toAddr = new InternetAddress(data.getUser_email());
				    msg.addRecipient(Message.RecipientType.TO,toAddr); // 받는 사람
				     
				    msg.setContent(buffer.toString(), "text/html;charset=UTF-8"); // 내용
				    Transport.send(msg); // 전송  
				    request.setAttribute("msg", "success");
				} catch(Exception e){
				    e.printStackTrace();
				    return;
				}
			}else if(count == 0 && !user_id.trim().isEmpty()){
				request.setAttribute("msg", "failedID");

			}else if(!find_question.equals(data.getUser_question())|| !find_answer.equals(data.getUser_answer())){
				request.setAttribute("msg", "failedQA");

			}
		}catch(SQLException | IOException e){
			e.printStackTrace();
		}finally {
			//finally block used to close resources
			try {
				if(conn!=null) conn.close();
			} catch(SQLException se){
				se.printStackTrace();
			}//end finally try

		}//end try
	}	

}
