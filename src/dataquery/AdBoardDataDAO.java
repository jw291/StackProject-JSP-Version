package dataquery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabean.AdBoardDataBean;

public class AdBoardDataDAO {

	public AdBoardDataDAO() { 
	}
	
	private static AdBoardDataDAO instance = new AdBoardDataDAO();
	public static AdBoardDataDAO getInstance(){
		return instance;
	} 

	public ArrayList<AdBoardDataBean> getList(Connection conn,int startRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	    ArrayList<AdBoardDataBean> list = new ArrayList<AdBoardDataBean>();
	     
	    try {
	       
	      String sql = "select * from board_advertisement order by b_no desc limit "+startRow+", "+endRow;
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
	public int insert(Connection conn, AdBoardDataBean pe) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into board_advertisement values (?,?,?,?,?,?,?)";
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
			String sql = "delete from board_advertisement where b_no=?";
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
	public int edit(Connection conn, AdBoardDataBean pe) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "update board_advertisement set b_title=?, b_content=? where b_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pe.getBtitle());
			pstmt.setString(2, pe.getBcontent());
			pstmt.setInt(3, pe.getBnum());
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
			String sql = "update board_advertisement set b_hit=b_hit+1 where b_no=?";
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
	public AdBoardDataBean select(Connection conn, int number) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from board_advertisement where b_no=?";
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
	public int getTotalCount(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) as count from board_advertisement ";
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
	
	public AdBoardDataBean createFromResultSet(ResultSet rs) throws SQLException {
		
		int number = rs.getInt("b_no");
		String title = rs.getString("b_title");
		String name = rs.getString("b_id");
		String date = rs.getString("b_date");
		int hit = rs.getInt("b_hit");
		String content = rs.getString("b_content");
		String threadname = rs.getString("threadname");
		AdBoardDataBean pe = new AdBoardDataBean(number, title, name,date,hit,content,threadname );
		return pe;
	}
	
	// selectList
	public List<AdBoardDataBean> selectList(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from board_advertisement ORDER BY b_no DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<AdBoardDataBean> pList = new ArrayList<>();
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
