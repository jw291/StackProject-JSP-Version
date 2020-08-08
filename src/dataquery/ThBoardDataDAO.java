package dataquery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabean.ThBoardDataBean;

public class ThBoardDataDAO {

	public ThBoardDataDAO() { 
	}
	
	private static ThBoardDataDAO instance = new ThBoardDataDAO();
	public static ThBoardDataDAO getInstance(){
		return instance;
	} 

	public ArrayList<ThBoardDataBean> getList(Connection conn,String threadname,int startRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	    ArrayList<ThBoardDataBean> list = new ArrayList<ThBoardDataBean>();
	     
	    try {
	       
	      String sql = "select * from board_thread where threadname= '"+threadname+"' order by b_no desc limit "+startRow+", "+endRow;
	      System.out.println(sql);
	      pstmt = conn.prepareStatement(sql);
	      rs = pstmt.executeQuery(sql);
	       
	      while(rs.next()) {
	    	list.add(createFromResultSet(rs));
	      }
	       
	    } catch (Exception e){
	      e.printStackTrace();
	    } finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
	    }
	    return list;
	  }
	// insert
	public int insert(Connection conn, ThBoardDataBean pe) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into board_thread values (?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pe.getBnum());
			pstmt.setString(2, pe.getBtitle());
			pstmt.setString(3, pe.getBid());
			pstmt.setString(4, pe.getBdate());
			pstmt.setInt(5, pe.getBhit());
			pstmt.setString(6, pe.getBcontent());
			pstmt.setString(7, pe.getThreadname());
			return pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}

	// delete
	public int delete(Connection conn, int number) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from board_thread where b_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			return pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}

	// edit
	public int edit(Connection conn, int number, String title, String content) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "update board_thread set b_title=?, b_content=? where b_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, number);
			return pstmt.executeUpdate();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}
	
	public int UpdateHit(Connection conn, int number) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "update board_thread set b_hit=b_hit+1 where b_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			return pstmt.executeUpdate();
		}finally {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
		}
	}
	// select(find/get)
	public ThBoardDataBean select(Connection conn, int number) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from board_thread where b_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return createFromResultSet(rs);
			}
			else {
				return null;
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}
	public int getTotalCount(Connection conn,String threadname) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) as count from board_thread where threadname='"+threadname+"'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("count");
			}else {
				return 0;
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}	
	
	public ThBoardDataBean createFromResultSet(ResultSet rs) throws SQLException {
		
		int number = rs.getInt("b_no");
		String title = rs.getString("b_title");
		String name = rs.getString("b_id");
		String date = rs.getString("b_date");
		int hit = rs.getInt("b_hit");
		String content = rs.getString("b_content");
		String threadname = rs.getString("threadname");
		ThBoardDataBean pe = new ThBoardDataBean(number, title, name,date,hit,content,threadname);
		return pe;
	}
	
	// selectList
	public List<ThBoardDataBean> selectList(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from board_thread ORDER BY b_no DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<ThBoardDataBean> pList = new ArrayList<>();
			while (rs.next()) {
				pList.add(createFromResultSet(rs));
			}
			return pList;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}
}
