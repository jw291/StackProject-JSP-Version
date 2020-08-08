package dataquery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabean.FreeCommentDataBean;

public class FreeCommentDataDAO {

	public FreeCommentDataDAO() { 
	}
	
	private static FreeCommentDataDAO instance = new FreeCommentDataDAO();
	public static FreeCommentDataDAO getInstance(){
		return instance;
	} 

	// insert
	public int insert(Connection conn, FreeCommentDataBean pe) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into comment_free values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pe.getCo_no());
			pstmt.setInt(2, pe.getB_no());
			pstmt.setInt(3, pe.getCo_order());
			pstmt.setString(4, pe.getCo_content());
			pstmt.setString(5, pe.getCo_id());
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
			String sql = "delete from comment_free where co_no=?";
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
	public int edit(Connection conn, FreeCommentDataBean pe) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "update comment_free set co_content=? where co_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pe.getCo_content());
			pstmt.setInt(2, pe.getCo_no());
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
	
	public int maxid(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT max(co_no) as max FROM stackDB.comment_free";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("max");
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
	// edit
	public int newcomment(Connection conn, int co_no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "update comment_free set co_order = co_no where co_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, maxid(conn));
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
	// select(find/get)
	public FreeCommentDataBean select(Connection conn, int number) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from comment_free where co_no=co_order and b_no = ?";
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
	
	public int commentcount(Connection conn,int number) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) as cnt from comment_free where b_no= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("cnt");
			}
			else {
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
	
	public FreeCommentDataBean createFromResultSet(ResultSet rs) throws SQLException {
		
		int co_number = rs.getInt("co_no");
		int b_number = rs.getInt("b_no");
		int co_order = rs.getInt("co_order");
		String co_content = rs.getString("co_content");
		String co_id = rs.getString("co_id");
		FreeCommentDataBean pe = new FreeCommentDataBean(co_number,b_number,co_order,co_content,co_id );
		return pe;
	}
	// selectList
		public List<FreeCommentDataBean> OdepthselectList(Connection conn, int number) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from comment_free where co_no=co_order and b_no= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, number);
				rs = pstmt.executeQuery();
				List<FreeCommentDataBean> pList = new ArrayList<>();
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
	
	// selectList
	public List<FreeCommentDataBean> TdepthselectList(Connection conn, int number) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from comment_free where co_no!=co_order and co_order = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			List<FreeCommentDataBean> pList = new ArrayList<>();
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
