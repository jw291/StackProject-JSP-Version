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
request.setCharacterEncoding("utf-8");
String w = null;
int co_no = 0;
int b_no = 0;
String co_content = null;
String co_id = null;
String msg = null;
if(request.getParameterMap().containsKey("w"))
{
	System.out.println("들어옴");
     w = request.getParameter("w");
     System.out.println(w);
     co_no = Integer.parseInt(request.getParameter("co_no"));
}

b_no = Integer.parseInt(request.getParameter("bno"));
System.out.println(b_no);

if(!"d".equals(w)){
	System.out.println("d가 아니고");
	co_content = request.getParameter("coContent");
	if(!"u".equals(w)){
		System.out.println("u가 아니고");
		co_id = (String)session.getAttribute("user_id"); // 수정의 경우에는 세션 아이디가 불필
	}
}

System.out.println("w : "+w+" bno : "+b_no + " cono : "+co_no+" co_content : "+co_content+" coid : "+co_id);

if(w == null || w.equals("w")){
	msg = "작성";
	ThCommentDataBean pe = new ThCommentDataBean(0,b_no,co_no,co_content,co_id);
	Connection conn = null;
	try{
		conn = MySQLConnection.getConnection();//getConnection이 exception
		ThCommentDataDAO dao = ThCommentDataDAO.getInstance();
		dao.insert(conn,pe); //selectdata
		if(w == null) {
			
			dao.newcomment(conn, pe.getCo_no());
			
		}
		
	}catch(SQLException e){
		e.printStackTrace();
	}finally {
		//finally block used to close resources
		try {
			if(conn!=null) conn.close();
		} catch(SQLException se){
			se.printStackTrace();
		}//end finally try

	}//end try>
}else if(w.equals("u")){
	msg = "수정";
	ThCommentDataBean pe = new ThCommentDataBean(co_no,b_no,co_no,co_content,co_id);
	Connection conn = null;
	try{
		conn = MySQLConnection.getConnection();//getConnection이 exception
		ThCommentDataDAO dao = ThCommentDataDAO.getInstance();
		dao.edit(conn,pe); //selectdata
	}catch(SQLException e){
		e.printStackTrace();
	}finally {
		//finally block used to close resources
		try {
			if(conn!=null) conn.close();
		} catch(SQLException se){
			se.printStackTrace();
		}//end finally try

	}//end try>
}else if(w.equals("d")){
	msg = "삭제";
	Connection conn = null;
	try{
		conn = MySQLConnection.getConnection();//getConnection이 exception
		ThCommentDataDAO dao = ThCommentDataDAO.getInstance();
		dao.delete(conn,co_no); //selectdata
	}catch(SQLException e){
		e.printStackTrace();
	}finally {
		//finally block used to close resources
		try {
			if(conn!=null) conn.close();
		} catch(SQLException se){
			se.printStackTrace();
		}//end finally try

	}//end try>
}else{
	out.println("<script> alert('정상적인 경로를 이용해주세요.'); history.back();</script>");
}

System.out.println("d?");
out.println("<script>alert('댓글이 정상적으로 " + msg + "되었습니다.');</script>");
out.println("<script>location.replace('./ThBoardViewAction.do?number=" + b_no + "')</script>");

%>
</body>
</html>