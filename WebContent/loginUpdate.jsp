<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="jdbc.MySQLConnection" %>
<%@ page import="java.util.*" %>
<%@ page import="dataquery.*" %>
<%@ page import="javabean.*" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
String user_id = request.getParameter("user_id");
String user_pw = request.getParameter("user_pw");
String threadname;
boolean loginregister = false;
%>
<%
Connection conn = null;
try{
	conn = MySQLConnection.getConnection();//getConnection이 exception
	UserDataDAO dao = UserDataDAO.getInstance();
	loginregister = dao.SelectForLogin(conn, user_id, user_pw);
	threadname = dao.SelectThreadname(conn, user_id);
	
	if(loginregister == true){
		out.println("<script>alert('로그인을 완료하였습니다.');location.href='BoardPaginationAction.do';</script>");
		session.setAttribute("user_id", user_id);
		session.setAttribute("user_threadname",threadname);
	}else{
		out.println("<script>alert('아이디 혹은 비밀번호를 확인해주세요.'); location.href='login.jsp';</script>");
	}
	
	out.flush();

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
%>
</body>
</html>