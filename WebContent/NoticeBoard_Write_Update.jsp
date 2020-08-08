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
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");

String id = "";

int number = 0;
id = (String)session.getAttribute("user_id");
String title = request.getParameter("bTitle");
String name = request.getParameter("name");
int hit = 1;
String date = formatter.format(new java.util.Date());
String content = request.getParameter("bContent");
NoticeBoardDataBean pe = new NoticeBoardDataBean(number, title,id, date, hit,content);

Connection conn = null;
try{
	conn = MySQLConnection.getConnection();//getConnection이 exception
	NoticeBoardDataDAO dao = NoticeBoardDataDAO.getInstance();
	dao.insert(conn, pe); //insert data
	out.println("<script>alert('글쓰기 완료.'); location.href='BoardPaginationAction.do';</script>");
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