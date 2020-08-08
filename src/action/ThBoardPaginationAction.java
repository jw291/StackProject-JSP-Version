package action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataquery.ThBoardDataDAO;
import dataquery.ThCommentDataDAO;
import dataquery.ThDataDAO;
import dataquery.UserDataDAO;
import javabean.ThBoardDataBean;
import javabean.ThDataBean;
import javabean.UserDataBean;
import javabean.Paging;
import jdbc.MySQLConnection;

public class ThBoardPaginationAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Connection conn = null;
		HttpSession session = request.getSession(true);
		try{
			String user_id = (String) session.getAttribute("user_id");
			String threadname = (String) session.getAttribute("user_threadname");	
			conn = MySQLConnection.getConnection();//getConnection이 exception
			ThBoardDataDAO dao = new ThBoardDataDAO();
			ThDataDAO tdao = new ThDataDAO();
			ThDataBean data = tdao.selectFromThreadName(conn, threadname);
			String t_master = data.getT_master();
			String t_category = data.getT_category();
			String t_intro = data.getT_intro();
			
		    int totalCount = dao.getTotalCount(conn,threadname);
		    System.out.println("totalcount"+totalCount);
		    int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));

		    Paging paging = new Paging();
		    paging.setPageNo(page); //get방식의 parameter값으로 반은 page변수, 현재 페이지 번호
		    paging.setPageSize(5); // 한페이지에 불러낼 게시물의 개수 지정
		    paging.setTotalCount(totalCount);
		     
		    page = (page-1) * 5; //select해오는 기준을 구한다.
		    System.out.println("page :" + page + " paging.getPageSize : " +paging.getPageSize()+"paging.start , end"+paging.getStartPageNo() + " " + paging.getEndPageNo());
		    List<ThBoardDataBean> list = dao.getList(conn,threadname,page, paging.getPageSize());
		    System.out.println(list);
		    
		    request.setAttribute("t_master", t_master);
		    request.setAttribute("t_category", t_category);
		    request.setAttribute("t_intro", t_intro);
		    request.setAttribute("threadname", threadname);
		    request.setAttribute("list", list);
		    request.setAttribute("paging", paging);
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