package dataquery;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabean.CalendarDataBean;
import javabean.FreeBoardDataBean;


public class CalendarDAO {
	private CalendarDAO() { 
	}
	
	private static CalendarDAO instance = new CalendarDAO();
	public static CalendarDAO getInstance(){
		return instance;
	} 

	// insert
	public int insert(Connection conn, CalendarDataBean CD) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into tbl_events values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, CD.getId());
			pstmt.setString(2, CD.getTitle());
			pstmt.setString(3, CD.getStart());
			pstmt.setString(4, CD.getEnd());
			pstmt.setString(5, CD.getThreadname());
			return pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}
	
	// edit
	public int edit(Connection conn, CalendarDataBean CD) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE tbl_events SET title = ?, start=?,end=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,CD.getTitle());
			pstmt.setString(2, CD.getStart());
			pstmt.setString(3, CD.getEnd());
			pstmt.setInt(4, CD.getId());
			return pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}
	
	// delete
		public int delete(Connection conn, int id) throws SQLException {
			PreparedStatement pstmt = null;
			try {
				String sql = "delete from tbl_events where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				return pstmt.executeUpdate();
			} finally {
				if (pstmt != null) {
					pstmt.close();
				}
			}
		}
		
	public CalendarDataBean createFromResultSet(ResultSet rs) throws SQLException {
		
		int id = rs.getInt("id");
		String title = rs.getString("title");
		String start = rs.getString("start");
		String end = rs.getString("end");
		String threadname = rs.getString("threadname");
		CalendarDataBean pe = new CalendarDataBean(id, title, start, end,threadname);
		return pe;
	}
	
	// selectList
	public List<CalendarDataBean> selectList(Connection conn,String threadname) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from tbl_events where threadname='"+threadname+"'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<CalendarDataBean> pList = new ArrayList<>();
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
