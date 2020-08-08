package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataquery.CalendarDAO;
import javabean.CalendarDataBean;
import jdbc.MySQLConnection;

public class CalendarDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try{
			HttpSession session = request.getSession(true);
			conn = MySQLConnection.getConnection();//getConnection이 exception
		    CalendarDAO calendardao = CalendarDAO.getInstance();
			
		    int id = Integer.parseInt(request.getParameter("id"));
			calendardao.delete(conn, id);
		}catch(SQLException e){
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