package action;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataquery.ThBoardDataDAO;
import javabean.ThBoardDataBean;
import jdbc.MySQLConnection;

public class ThBoardEditViewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Connection conn = null;
		try {
			conn = MySQLConnection.getConnection();

			int number = Integer.parseInt(request.getParameter("number"));
			ThBoardDataDAO dao = ThBoardDataDAO.getInstance();
			
			ThBoardDataBean data = dao.select(conn, number); //selectdata
			String id = data.getBid();
			String title = data.getBtitle();
			String date = data.getBdate();
			int hit= data.getBhit();
			String content = data.getBcontent();
			request.setAttribute("number", number);
			request.setAttribute("id", id);
			request.setAttribute("title", title);
			request.setAttribute("content", content);
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
