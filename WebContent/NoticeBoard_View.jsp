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
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>STACK</title>
  <!-- 태그 클릭 시 색깔 바뀌게 하기 -->
  <style type="text/css">
  a:link{text-decoration: none; color: #2E64FE;}
  a:visited{
  text-decoration: underline;
  font-style:italic;
  color: #682692;
  }
  </style>
  <!-- Bootstrap core CSS-->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Page level plugin CSS-->
  <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">
  <link rel="stylesheet" href="./css1/normalize.css" />
  <link rel="stylesheet" href="./css1/board.css" />


  <script src="./js1/jquery-2.1.3.min.js"></script>
  <title>View</title>
</head>
<%
 request.setCharacterEncoding("UTF-8");
 List<NoticeBoardDataBean> pList = (List<NoticeBoardDataBean>) application.getAttribute("pList");
 boolean isGet = false;
 
 int number = Integer.parseInt(request.getParameter("number"));
 String title = null;
 String id = null;
 String date = null;
 int hit= 0;
 String content = null;
%>
<%
Connection conn = null;
try{
	conn = MySQLConnection.getConnection();//getConnection이 exception
	NoticeBoardDataDAO dao = NoticeBoardDataDAO.getInstance();
	//쿠키변수를 만들어서 값을 주정. 쿠키 변수에 값이 있으면 조회수 증가 실행하지 않
	Cookie[] cookies = request.getCookies();
	System.out.println("isGet : "+ isGet +"쿠키 : " + cookies);
	String numberstr = String.valueOf(number);
	if(cookies!= null){
		for(Cookie c:cookies){
			//number쿠키가 있는 경우
			System.out.println("numberstr : " + numberstr);
			System.out.println("c.getname : " + c.getName());
			if(c.getName().equals(numberstr)){
				isGet = true;
			}
		}
	}
	//number쿠키가 없는 경우
	if(!isGet){
		dao.UpdateHit(conn, number);
		Cookie c1 = new Cookie(numberstr,numberstr);
		c1.setMaxAge(1*24*60*60);//하루 저장
		response.addCookie(c1);
	}
	NoticeBoardDataBean data = dao.select(conn, number); //selectdata
	id = data.getBid();
	title = data.getBtitle();
	date = data.getBdate();
	hit= data.getBhit();
	content = data.getBcontent();
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
%>	
<body>
<jsp:include page="header.jsp" /> <%-- 정적 include --%>
<div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="#">STACK</a>
        </li>
        <li class="breadcrumb-item active">글 보기</li>
      </ol>
	<!-- free board-->
	<article class="boardArticle">
		<h3>글 보기</h3>
		<div id="boardWrite">
			<form action=./NoticeBoard_Write_Update.jsp method=POST>
			<!--form을 submit할때 bno값도 같이 전달돼야 하기 때문에 해놓음.-->

			<!--&bno가 존재한다면 b_id를 뽑아온다. 존재하지 않으면 인풋으로 입력할 수 있게 한다 하지만 수정할때 아이디를 바꿀 필요는 없으니 사실상 무쓸모-->
				<table id="boardWrite">
					<tbody>
						<tr>
							<th scope="row"><label for="bID">아이디</label></th>
							<td class="id"><%=id %>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="bTitle">제목</label></th>
							<td class="title"><%=title %></td>
						</tr>
						<tr>
							<th scope="row"><label for="bContent">내용</label></th>
							<td class="content"><textarea name="bContent" id="bContent" style="width:605px; height:412px;"><%= content %></textarea>
</td>
						</tr>
					</tbody>
				</table>
				<div class="btnSet">
					<button type="submit" class="btnSubmit btn" onclick="submitContents(this)">작성
					</button>
					<a href="./BoardPaginationAction.do" class="btnList btn">목록</a>
				</div>
			</form>
			<hr style="color:#999999; border-style:dotted">

	<!--댓글 php를 include시켜준다.-->
	<div id="boardComment">
		<jsp:include page="./NoticeBoard_Comment.jsp"/>

	</div>
			
		</div>
	</article>
	</div>
	</div>
<jsp:include page="footer.jsp" /> <%-- 정적 include --%>
</body>
</html>