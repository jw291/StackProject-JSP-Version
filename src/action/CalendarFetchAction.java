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

public class CalendarFetchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Connection conn = null;
		HttpSession session = request.getSession(true);
		String temp = "";
		String jsonMapping = "";
		response.setContentType("text/html;charset=UTF-8");
		try{
			String threadname = (String) session.getAttribute("user_threadname");
			PrintWriter out = response.getWriter();
			conn = MySQLConnection.getConnection();
			CalendarDAO dao = CalendarDAO.getInstance();
			List<CalendarDataBean> pList = dao.selectList(conn,threadname);
			for(int i = 0; i < pList.size(); i++) {
				temp +="{\"id\":"+"\""+pList.get(i).getId()+"\""
						+",\"title\":"+"\""+pList.get(i).getTitle()+"\""
						+ ",\"start\":"+"\""+pList.get(i).getStart()+"\""
						+ ",\"end\":"+"\""+pList.get(i).getEnd()+"\"";
				
				if(i == pList.size()-1) {
					temp += "}";
				}else {
					temp += "},";
				}
			}
		
			System.out.println(temp);
			jsonMapping = "[" + temp + "]";
			out.print(jsonMapping);
			out.flush();
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