package action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataquery.ThCommentDataDAO;
import jdbc.MySQLConnection;

public class ThCommentCountAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Connection conn = null;
		int num = 0;
		try {
			conn = MySQLConnection.getConnection();
			ThCommentDataDAO dao = ThCommentDataDAO.getInstance();
			
			num = Integer.parseInt(request.getParameter("number"));
			if( num != 0) {
		    	request.setAttribute("cv", dao.commentcount(conn, num));
		    }else {
		    	request.setAttribute("cv", dao.commentcount(conn, num));
		    }
		
			
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