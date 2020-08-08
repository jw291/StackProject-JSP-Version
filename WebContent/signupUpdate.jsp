<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="jdbc.MySQLConnection" %>
<%@ page import="java.util.*" %>
<%@ page import="dataquery.*" %>
<%@ page import="javabean.*" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="utf-8">
<title>회원가입</title>
</head>
<!---->
<body>
<%
request.setCharacterEncoding("UTF-8");
String user_id = request.getParameter("user_id");
String user_name = request.getParameter("user_email");
String user_email = request.getParameter("user_email");
String user_password = request.getParameter("user_pw");
String user_pw_question = request.getParameter("find_account_question");
String user_pw_answer = request.getParameter("find_account_answer");


UserDataBean uq = new UserDataBean(user_id,user_name,user_email,user_password,user_pw_question,user_pw_answer,null);
%>

<%
Connection conn = null;
try{
	conn = MySQLConnection.getConnection();//getConnection이 exception
	UserDataDAO dao = UserDataDAO.getInstance();
	dao.insert(conn, uq); //insert data
	out.println("<script>alert('회원가입을 완료하였습니다.'); location.href='BoardPaginationAction.do';</script>");
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