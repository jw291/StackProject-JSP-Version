package action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataquery.ThBoardDataDAO;
import dataquery.ThDataDAO;
import dataquery.UserDataDAO;
import javabean.ThBoardDataBean;
import javabean.ThDataBean;
import jdbc.MySQLConnection;

public class ThConstructUpdate implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Connection conn = null;
		HttpSession session = request.getSession(true);
		try {
			conn = MySQLConnection.getConnection();
			request.setCharacterEncoding("UTF-8");
				int number = 0;
				String threadname = request.getParameter("thread_name");
				String t_master = (String) session.getAttribute("user_id");
				String t_category = request.getParameter("thread_category");
				String t_intro = request.getParameter("thread_description");
				ThDataBean pe = new ThDataBean(number, threadname,t_master,t_category,t_intro);
				ThDataDAO dao = ThDataDAO.getInstance();
				UserDataDAO udao = UserDataDAO.getInstance();
				udao.UpdateThreadname(conn, t_master, threadname);
				dao.insert(conn, pe);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('스레드 개설 완료.')");
				out.println("location.href = './BoardPaginationAction.do';"); 
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