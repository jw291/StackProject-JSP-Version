package dataquery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javabean.UserDataBean;

public class UserDataDAO {

	public UserDataDAO() {
		
	}
	private static UserDataDAO instance = new UserDataDAO();
	public static UserDataDAO getInstance() {
		return instance;
	}
	
	public int insert(Connection conn, UserDataBean uq) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into user values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, uq.getUser_id());
			pstmt.setString(3, uq.getUser_name());
			pstmt.setString(4, uq.getUser_email());
			pstmt.setString(5, uq.getUser_password());
			pstmt.setString(6, uq.getUser_question());
			pstmt.setString(7, uq.getUser_answer());
			pstmt.setString(8, uq.getUser_threadname());
			return pstmt.executeUpdate();
		}finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
	}
	
	public String SelectThreadname(Connection conn, String user_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select threadname from user where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
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
	
	public int UpdateThreadname(Connection conn, String user_id,String threadname) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "update user set threadname=? where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, threadname);
			pstmt.setString(2, user_id);
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
		public UserDataBean selectFromID(Connection conn, String user_id) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from user where user_id=? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_id);
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
		
		public int cntselectFromID(Connection conn, String user_id) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select count(*) as cnt from user where user_id= ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_id);
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
		
		public int RowCountFromID(Connection conn, String user_id) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select count(*) as count from user where user_id = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_id);
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
		
		public int RowCountFromEMAIL(Connection conn, String user_email) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select count(*) as count from user where user_email = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_email);
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
				
		// select(find/get)
		public UserDataBean selectFromEmail(Connection conn, String user_email) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
				try {
					String sql = "select * from userwhere user_email=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, user_email);
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
		
		public boolean SelectForLogin(Connection conn, String user_id, String user_pw) throws SQLException{
			PreparedStatement pstmt = null;
			ResultSet rs = null;
				try {
					String sql = "select count(*) as count from user where user_id=? and user_pw=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, user_id);
					pstmt.setString(2, user_pw);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						if(rs.getInt("count")>0) {
						   return true;	
						}else {
							return false;
						}
					}else {
						return false;
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
	public UserDataBean createFromResultSet(ResultSet rs) throws SQLException {
		
		String user_id = rs.getString("user_id");
		String user_name = rs.getString("user_name");
		String user_email = rs.getString("user_email");
		String user_pw = rs.getString("user_pw");
		String user_pw_question = rs.getString("user_pw_question");
		String user_pw_answer = rs.getString("user_pw_answer");
		String user_threadname = rs.getString("threadname");
		UserDataBean uq = new UserDataBean(user_id,user_name,user_email,user_pw,user_pw_question,user_pw_answer,user_threadname);
		
		return uq;
	}
}
