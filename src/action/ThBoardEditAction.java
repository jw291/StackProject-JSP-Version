package action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataquery.ThBoardDataDAO;
import javabean.ThBoardDataBean;
import jdbc.MySQLConnection;

public class ThBoardEditAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Connection conn = null;
		HttpSession session = request.getSession(true);
		try {
			conn = MySQLConnection.getConnection();
		
			request.setCharacterEncoding("UTF-8");
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");

			int number = Integer.parseInt(request.getParameter("number"));
			String title = request.getParameter("bTitle");
			String content = request.getParameter("bContent");
			ThBoardDataDAO dao = ThBoardDataDAO.getInstance();
			dao.edit(conn, number,title,content);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("alert('글수정 완료.')");
			
			out.println("location.href = './ThBoardViewAction.do?number="+number+" ' "); 
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