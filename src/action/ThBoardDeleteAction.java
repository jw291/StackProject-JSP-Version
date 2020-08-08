package action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataquery.ThBoardDataDAO;
import jdbc.MySQLConnection;

public class ThBoardDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Connection conn = null;
		HttpSession session = request.getSession(true);
		try {
			conn = MySQLConnection.getConnection();
			int number = Integer.parseInt(request.getParameter("number"));
			ThBoardDataDAO dao = ThBoardDataDAO.getInstance();
			dao.delete(conn, number);
			
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
