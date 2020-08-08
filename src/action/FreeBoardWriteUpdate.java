package action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataquery.FreeBoardDataDAO;
import javabean.FreeBoardDataBean;
import jdbc.MySQLConnection;


public class FreeBoardWriteUpdate implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Connection conn = null;
		HttpSession session = request.getSession(true);
		try {
			conn = MySQLConnection.getConnection();
		
			request.setCharacterEncoding("UTF-8");
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");

			int number = 0;
			String id = (String) session.getAttribute("user_id");
			String title = request.getParameter("bTitle");
			String name = request.getParameter("name");
			int hit = 1;
			String date = formatter.format(new java.util.Date());
			System.out.println(date);
			String content = request.getParameter("bContent");
			FreeBoardDataBean pe = new FreeBoardDataBean(number, title,id, date, hit,content);
			FreeBoardDataDAO dao = FreeBoardDataDAO.getInstance();
			dao.insert(conn, pe);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("alert('글쓰기 완료.')");
			out.println("location.href = './FreeBoardPaginationAction.do';"); 
			out.println("</script>"); 
			out.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}