package action;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataquery.AdBoardDataDAO;
import javabean.AdBoardDataBean;
import jdbc.MySQLConnection;

public class AdBoardViewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Connection conn = null;
		try {
			conn = MySQLConnection.getConnection();

			int number = Integer.parseInt(request.getParameter("number"));

			AdBoardDataDAO dao = AdBoardDataDAO.getInstance();
			boolean isGet = false;
			Cookie[] cookies = request.getCookies();
			System.out.println("isGet : "+ isGet +"쿠키 : " + cookies);
			String numberstr = String.valueOf("Ad"+number);
			if(cookies!= null){
				for(Cookie c:cookies){
					//number쿠키가 있는 경우
					System.out.println("numberstr : " + numberstr);
					System.out.println("c.getname : " + c.getName());
					if(c.getName().equals(numberstr)){
						isGet = true;
					}
				}
			}
			//number쿠키가 없는 경우
			if(!isGet){
				dao.UpdateHit(conn, number);
				Cookie c1 = new Cookie(numberstr,numberstr);
				c1.setMaxAge(1*24*60*60);//하루 저장
				response.addCookie(c1);
			}
			AdBoardDataBean data = dao.select(conn, number); //selectdata
			String id = data.getBid();
			String title = data.getBtitle();
			String date = data.getBdate();
			int hit= data.getBhit();
			String content = data.getBcontent();
			String threadname = data.getThreadname();
			request.setAttribute("id", id);
			request.setAttribute("title", title);
			request.setAttribute("date", date);
			request.setAttribute("hit", hit);
			request.setAttribute("content", content);
			request.setAttribute("threadname", threadname);
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
