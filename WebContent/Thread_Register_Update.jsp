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
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
String user_id = (String)session.getAttribute("user_id");
String threadname = request.getParameter("threadname");
%>

<%
Connection conn = null;
try{
	conn = MySQLConnection.getConnection();//getConnection이 exception
	UserDataDAO dao = UserDataDAO.getInstance();
	dao.UpdateThreadname(conn, user_id, threadname);

out.println("<script>alert('가입이 정상적으로 처리되었습니다.');</script>");
out.println("<script>location.replace('./BoardPaginationAction.do')</script>");
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