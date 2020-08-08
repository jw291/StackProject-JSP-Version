<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="jdbc.MySQLConnection" %>
<%@ page import="java.util.*" %>
<%@ page import="dataquery.*" %>
<%@ page import="javabean.*" %>
<%@ page import="java.sql.*" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

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
  <link rel="stylesheet" href="css/paginationcss.css">
  <link href="css/sb-admin.css" rel="stylesheet">
  <link rel="stylesheet" href="./css1/normalize.css" />
  <link rel="stylesheet" href="./css1/board.css" />


  <script src="./js1/jquery-2.1.3.min.js"></script>
  <title>HOMEPAGE</title>
</head>
<body>
<%
String user_id = (String) session.getAttribute("user_id");
String threadname;
%>
<%
Connection conn = null;
try{
	if(session.getAttribute("user_threadname") == null){
		conn = MySQLConnection.getConnection();//getConnection이 exception
		UserDataDAO dao = UserDataDAO.getInstance();
		threadname = dao.SelectThreadname(conn, user_id);
	
		session.setAttribute("user_threadname",threadname);
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

}//end try
%>
<jsp:include page="header.jsp" /> <%-- 정적 include --%>
  	<div class="content-wrapper">
    <div class="container-fluid">
				  <!-- Breadcrumbs-->
		      <ol class="breadcrumb">
		        <li class="breadcrumb-item">
		          <a href="home.php" >STACK</a>
		        </li>
		        <li class="breadcrumb-item active">home</li>
		      </ol>

					<!-- image-->
						<div class="col-lg-10">
		          <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
		            <ol class="carousel-indicators">
		              <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
		              <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
		              <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
		            </ol>
		            <div class="carousel-inner" role="listbox">
					<center>
		              <div class="carousel-item active">
		                <img class="d-block img-fluid" src="firthi.jpeg" alt="First slide" >
		              </div>
		              <div class="carousel-item">
		                <img class="d-block img-fluid" src="thirdi.jpeg" alt="Second slide" >
		              </div>
		              <div class="carousel-item">
		                <img class="d-block img-fluid" src="firsti.jpeg" alt="Third slide" >
		              </div>
					</center>
		            </div>
		            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
		              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		              <span class="sr-only">Previous</span>
		            </a>
		            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
		              <span class="carousel-control-next-icon" aria-hidden="true"></span>
		              <span class="sr-only">Next</span>
		            </a>
		          </div>
						</div>
				      <!-- free-community start-->
								<article class="boardArticle" style="margin-left:0;">
										<div class="card mb-3" style="width:1200px;">
											<div class="card-header">
												<i class="fa fa-table"></i> 공지사항 게시판</div>
										<div id="boardList">
											<div class="card-body">
												<div class="table-responsive">
													<table class="table table-bordered"  style="width:100%" cellspacing="0">
														<thead>
															<tr>
																<th style="text-align: center;">번호</th>
																<th style="text-align: center;">제목</th>
																<th style="text-align: center;">작성자</th>
																<th style="text-align: center;">작성일</th>
																<th style="text-align: center;">조회수</th>
															</tr>
														</thead>
<c:forEach var="pe" items="${list}">
					<tr>
						<td align="center">${pe.getBnum()}</td>
						<td align="center"><a href="NoticeBoard_View.jsp?number=${pe.getBnum()}" style="cursor:pointer;">${pe.getBtitle()}</a></td>
						<td align="center">${pe.getBid()}</td>
						<td align="center">${pe.getBdate()}</td>
						<td align="center">${pe.getBhit()}</td>
					</tr>
					</c:forEach>
													</table>
									</div>
									<div class="btnSet">
									<%if(user_id == null) {%>
									<a class="btnWrite btn" href="javascript:callFunction()">글쓰기</a>
									<%}else{%>
									<a class="btnWrite btn" href="./NoticeBoard_Write.jsp">글쓰기</a>
									<%} %>
									</div>
											</div>
									 <div class="toolbar mt-lg">
    <div class="sorter" align="center">
      <ul class="pagination">
        <li><a href="javascript:PageMove(${paging.firstPageNo})">맨앞으로</a></li>
        <li><a href="javascript:PageMove(${paging.prevPageNo})">앞으로</a></li>
 
              <c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
                  <c:choose>
                      <c:when test="${i eq paging.pageNo}">
                <li class="active"><a href="javascript:PageMove(${i})">${i}</a></li>
                      </c:when>
                      <c:otherwise>
                        <li><a href="javascript:PageMove(${i})">${i}</a></li>
                      </c:otherwise>
                  </c:choose>
              </c:forEach>
        <li><a href="javascript:PageMove(${paging.nextPageNo})">뒤로</a></li>
        <li><a href="javascript:PageMove(${paging.finalPageNo})">맨뒤로</a></li>
      </ul>
    </div>
  </div>
										<div class="searchBox">
											<form action="" method="get">
												<select name="searchColumn">
													<option>제목</option>
													<option>내용</option>
													<option>작성자</option>
												</select>
												<input type="text" name="searchText" value="">
												<button type="submit">검색</button>
											</form>
										</div>
										</div>
								 	</div>
								</article>
							</div>
						</div>

<jsp:include page="footer.jsp" /> <%-- 정적 include --%>
<script type="text/javascript">
function PageMove(page){
    location.href = "BoardPaginationAction.do?page="+page;
  }
function callFunction(){
    alert("로그인 후 이용해주세요");
  }
</script>
</body>

</html>