package action;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataquery.CalendarDAO;
import javabean.CalendarDataBean;
import jdbc.MySQLConnection;

public class CalendarEditAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Connection conn = null;
		HttpSession session = request.getSession(true);
		try{
			conn = MySQLConnection.getConnection();//getConnection이 exception
		    CalendarDAO calendardao = CalendarDAO.getInstance();
			
		    int id = Integer.parseInt(request.getParameter("id"));
		    String title = request.getParameter("title");
		    String start = request.getParameter("start");
		    String end = request.getParameter("end");
		    String threadname = (String) session.getAttribute("user_threadname");
		    CalendarDataBean calendardatabean = new CalendarDataBean(id,title,start,end,threadname);
			calendardao.edit(conn, calendardatabean);
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