package action;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataquery.CalendarDAO;
import dataquery.NoticeBoardDataDAO;
import javabean.CalendarDataBean;
import javabean.NoticeBoardDataBean;
import javabean.Paging;
import jdbc.MySQLConnection;

public class CalendarAddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try{
			HttpSession session = request.getSession(true);
			conn = MySQLConnection.getConnection();//getConnection이 exception
		    CalendarDAO calendardao = CalendarDAO.getInstance();
		    String id = (String)session.getAttribute("user_id");
			String title = request.getParameter("title");
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			String threadname = (String) session.getAttribute("user_threadname");
			System.out.println("title : " + title + " start : " + start);
			
			CalendarDataBean calendardatabean = new CalendarDataBean(0,title,start,end,threadname);
			calendardao.insert(conn, calendardatabean);
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
