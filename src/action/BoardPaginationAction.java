package action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataquery.NoticeBoardDataDAO;
import javabean.NoticeBoardDataBean;
import javabean.Paging;
import jdbc.MySQLConnection;

public class BoardPaginationAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try{

			conn = MySQLConnection.getConnection();//getConnection이 exception
			NoticeBoardDataDAO dao = new NoticeBoardDataDAO();
		    int totalCount = dao.getTotalCount(conn);
		    System.out.println("totalcount"+totalCount);
		    int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));

		    Paging paging = new Paging();
		    paging.setPageNo(page); //get방식의 parameter값으로 반은 page변수, 현재 페이지 번호
		    paging.setPageSize(5); // 한페이지에 불러낼 게시물의 개수 지정
		    paging.setTotalCount(totalCount);
		     
		    page = (page-1) * 5; //select해오는 기준을 구한다.
		    System.out.println("page :" + page + " paging.getPageSize : " +paging.getPageSize());
		    List<NoticeBoardDataBean> list = dao.getList(conn,page, paging.getPageSize());
		    
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

