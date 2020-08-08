package dataquery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabean.ThCommentDataBean;
import javabean.ThDataBean;

public class ThDataDAO {

	public ThDataDAO() { 
	}
	
	private static ThDataDAO instance = new ThDataDAO();
	public static ThDataDAO getInstance(){
		return instance;
	} 

	// insert
	public int insert(Connection conn, ThDataBean pe) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into Thread values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pe.getT_no());
			pstmt.setString(2, pe.getThreadname());
			pstmt.setString(3, pe.getT_master());
			pstmt.setString(4, pe.getT_category());
			pstmt.setString(5, pe.getT_intro());
			return pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}
	// select(find/get)
		public ThDataBean select(Connection conn, int number) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from thread where t_no = ?";
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
		public ThDataBean createFromResultSet(ResultSet rs) throws SQLException {		
			int number = rs.getInt("t_no");
			String threadname = rs.getString("threadname");
			String t_master = rs.getString("t_master");
			String t_category = rs.getString("t_category");
			String t_intro = rs.getString("t_intro");
			ThDataBean pe = new ThDataBean(number,threadname,t_master,t_category,t_intro);
			return pe;
		}
		
		
		// selectList
		public List<ThDataBean> selectList (Connection conn, int number) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from thread where t_no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, number);
				rs = pstmt.executeQuery();
				List<ThDataBean> pList = new ArrayList<>();
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
		
		public ThDataBean selectFromThreadName(Connection conn, String threadname) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from thread where threadname = '"+threadname+"'";
				pstmt = conn.prepareStatement(sql);
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
}
