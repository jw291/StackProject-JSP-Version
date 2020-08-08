<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>ThBoard</title>
</head>
<body>
<script type="text/javascript">
	function deleteConfirm(number){
		if(confirm("삭제하시겠습니까") == true)
			location.href = "ThBoardDeleteAction.do?number="+ number;
		else
			return;			
	}
</script>
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
		<div id="boardView">

			<!--&bno가 존재한다면 b_id를 뽑아온다. 존재하지 않으면 인풋으로 입력할 수 있게 한다 하지만 수정할때 아이디를 바꿀 필요는 없으니 사실상 무쓸모-->
				<table id="boardWrite">
					<tbody>
						<tr>
							<th scope="row"><label for="bID">아이디</label></th>
							<td class="id">${id}
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="bTitle">제목</label></th>
							<td class="title">${title}</td>
						</tr>
						<tr>
							<th scope="row"><label for="bContent">내용</label></th>
							<td class="content"><textarea name="bContent" id="bContent" style="width:605px; height:412px;">${content}</textarea>
</td>
						</tr>
					</tbody>
				</table>
					<div class="btnSet">
		<!--php echo $bno로 클릭한 게시물의 bno를 보내준다. 다른 곳에서 GET으로 받는다.-->
		<c:if test="${id eq user_id}">
		<a style ="border-right-width: 0.15em; border-right-style: solid; border-right-color:#cdcdcd" href="./ThBoardEditViewAction.do?number=${number}">수정 </a>
		<a style ="border-right-width: 0.15em; border-right-style: solid; border-right-color:#cdcdcd" onclick="deleteConfirm('${number}')">삭제 </a>
		</c:if>
		<a href="./ThBoardPaginationAction.do">목록</a> 
	<!--목록 href는 자유게시판에서는 자유게시판, rpg는 rpg로 나중에 변경해주자.-->
	</div>
			<hr style="color:#999999; border-style:dotted">

	<!--댓글 php를 include시켜준다.-->
	<div id="boardComment">
		<jsp:include page="./ThBoard_Comment.jsp"/>

	</div>
			
		</div>
	</article>
	</div>
	</div>
<jsp:include page="footer.jsp" /> <%-- 정적 include --%>
</body>
</html>