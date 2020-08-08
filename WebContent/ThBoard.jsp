<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  
  <link rel="stylesheet" href="css/paginationcss.css">
  <link href="css/sb-admin.css" rel="stylesheet">
  <link rel="stylesheet" href="./css1/normalize.css" />
  <link rel="stylesheet" href="./css1/board.css" />


  <script src="./js1/jquery-2.1.3.min.js"></script>
<title>Ad Board</title>
</head>
<body>
<%
String user_id = request.getParameter("user_id");
String threadname = request.getParameter("threadname");
%>
<jsp:include page="header.jsp" /> <%-- 정적 include --%>
 	<div class="content-wrapper">
    <div class="container-fluid">
				  <!-- Breadcrumbs-->
		      <ol class="breadcrumb">
		        <li class="breadcrumb-item">
		          <a href="home.php" >STACK</a>
		        </li>
		        <li class="breadcrumb-item active">ThBoard</li>
		      </ol>
				      <!-- Thread-community start-->
								<article class="boardArticle" style="margin-left:0;">
										<div class="card mb-3" style="width:1200px;">
											<div class="card-header">
												<i class="fa fa-table"></i> 스레드 게시판</div>
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
						<td align="center"><a href="ThBoardViewAction.do?number=${pe.getBnum()}" style="cursor:pointer;">${pe.getBtitle()}</a></td>
						<td align="center">${pe.getBid()}</td>
						<td align="center">${pe.getBdate()}</td>
						<td align="center">${pe.getBhit()}</td>
					</tr>
					</c:forEach>
													</table>
									</div>
									<div class="btnSet">
								
									<a class="btnWrite btn" href="./ThBoard_Write.jsp">글쓰기</a>
									
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
    location.href = "ThBoardPaginationAction.do?page="+page+"&threadname="+threadname;
  }
  
function callFunction(){
    alert("로그인 후 이용해주세요");
  }
</script>
</body>
</html>