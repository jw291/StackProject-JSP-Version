<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  <script type="text/javascript" src="./nse_files/js/HuskyEZCreator.js"></script>
  <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>

  <script src="./js1/jquery-2.1.3.min.js"></script>
<title>AdBoard</title>
</head>
<body>
<%
String id = "";
id = (String)session.getAttribute("user_id");
String threadname = (String) session.getAttribute("user_threadname");
%>
<jsp:include page="header.jsp" /> <%-- 정적 include --%>
<div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="#">STACK</a>
        </li>
        <li class="breadcrumb-item active">게시판 글쓰기</li>
      </ol>
	<!-- free board-->
	<article class="boardArticle">
		<h3>게시판 글쓰기</h3>
		<div id="boardWrite">
			<form action=./AdBoardWriteUpdate.do method=POST>
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
							<th scope="row"><label for="threadname">모임명</label></th>
							<td class="id"><%= threadname %></td>
						</tr>
						<tr>
							<th scope="row"><label for="bTitle">제목</label></th>
							<td class="title"><input type="text" name="bTitle" id="bTitle"></td>
						</tr>
						<tr>
							<th scope="row"><label for="bContent">내용</label></th>
							<td class="content"><textarea name="bContent" id="bContent" style="width:605px; height:412px;"></textarea>
<script type="text/javascript">
var oEditors = []; $(
function(){ nhn.husky.EZCreator.createInIFrame({
oAppRef: oEditors, elPlaceHolder: "bContent",
//SmartEditor2Skin.html 파일이 존재하는 경로
sSkinURI: "./nse_files/SmartEditor2Skin.html",
htParams : { // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
bUseToolbar : true,
// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
bUseVerticalResizer : true,
// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
bUseModeChanger : true,
fOnBeforeUnload : function(){
 }
},
fOnAppLoad : function(){
//기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
oEditors.getById["ir1"].exec("PASTE_HTML", [""]);
},
fCreator: "createSEditor2" });
});
function submitContents(elClickedObj) {
  // 에디터의 내용이 textarea에 적용됩니다.
  oEditors.getById["bContent"].exec("UPDATE_CONTENTS_FIELD", []);
  // 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.

  try {
  elClickedObj.form.submit();
  } catch(e) {}
  }
</script>
</td>
						</tr>
					</tbody>
				</table>
				<div class="btnSet">
					<button type="submit" class="btnSubmit btn" onclick="submitContents(this)">작성
					</button>
					<a href="./AdBoardPaginationAction.do" class="btnList btn">목록</a>
				</div>
			</form>
		</div>
	</article>
	</div>
	</div>
<jsp:include page="footer.jsp" /> <%-- 정적 include --%>
</body>
</html>